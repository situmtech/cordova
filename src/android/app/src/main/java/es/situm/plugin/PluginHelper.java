package es.situm.plugin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import es.situm.sdk.SitumSdk;
import es.situm.sdk.communication.CommunicationManager;
import es.situm.sdk.directions.DirectionsRequest;
import es.situm.sdk.error.Error;
import es.situm.sdk.location.LocationListener;
import es.situm.sdk.location.LocationRequest;
import es.situm.sdk.location.LocationRequest.Builder;
import es.situm.sdk.location.LocationRequest.IndoorProvider;
import es.situm.sdk.location.LocationRequest.MotionMode;
import es.situm.sdk.location.LocationRequest.RealtimeUpdateInterval;
import es.situm.sdk.location.LocationStatus;
import es.situm.sdk.location.OutdoorLocationOptions;
import es.situm.sdk.model.cartography.Building;
import es.situm.sdk.model.cartography.Floor;
import es.situm.sdk.model.cartography.Poi;
import es.situm.sdk.model.cartography.PoiCategory;
import es.situm.sdk.model.directions.Route;
import es.situm.sdk.model.location.BeaconFilter;
import es.situm.sdk.model.location.Location;
import es.situm.sdk.model.navigation.NavigationProgress;
import es.situm.sdk.navigation.NavigationListener;
import es.situm.sdk.navigation.NavigationManager;
import es.situm.sdk.navigation.NavigationRequest;
import es.situm.sdk.utils.Handler;
import es.situm.sdk.v1.SitumEvent;

public class PluginHelper {

    private static final String TAG = "PluginHelper";

    private LocationListener locationListener;
    private LocationRequest locationRequest;
    private NavigationListener navigationListener;
    private NavigationRequest navigationRequest;

    private volatile CommunicationManager cmInstance;

    private volatile NavigationManager nmInstance;

    public static final float MIN_SNR = 10;
    public static final float MAX_SNR = 40;

    private Route computedRoute;
    private Location computedLocation;

    private CommunicationManager getCommunicationManagerInstance() {
        if (cmInstance == null) { //Check for the first time
            synchronized (CommunicationManager.class) {   //Check for the second time.
                //if there is no instance available... create new one
                if (cmInstance == null) cmInstance = SitumSdk.communicationManager();
            }
        }
        return cmInstance;
    }

    public void setCommunicationManager(CommunicationManager communicationManager) {
        cmInstance = communicationManager;
    }

    private NavigationManager getNavigationManagerInstance() {
        if (nmInstance == null) { //Check for the first time
            synchronized (NavigationManager.class) {   //Check for the second time.
                //if there is no instance available... create new one
                if (nmInstance == null) nmInstance = SitumSdk.navigationManager();
            }
        }
        return nmInstance;
    }

    public void setNavigationManager(NavigationManager navigationManager) {
        nmInstance = navigationManager;
    }

    public void fetchBuildings(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
           getCommunicationManagerInstance().fetchBuildings(new Handler<Collection<Building>>() {
                public void onSuccess(Collection<Building> buildings) {
                    try {
                        Log.d(PluginHelper.TAG, "onSuccess: Buildings fetched successfully.");
                        JSONArray jsonaBuildings = new JSONArray();

                        for (Building building : buildings) {
                            Log.i(PluginHelper.TAG,
                                    "onSuccess: " + building.getIdentifier() + " - " + building.getName());
                            JSONObject jsonoBuilding = SitumMapper.buildingToJsonObject(building);
                            jsonaBuildings.put(jsonoBuilding);
                        }
                        if (buildings.isEmpty()) {
                            Log.e(PluginHelper.TAG, "onSuccess: you have no buildings. Create one in the Dashboard");
                        }
                        callbackContext.sendPluginResult(new PluginResult(Status.OK, jsonaBuildings));
                    } catch (JSONException e) {
                        callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
                    }
                }

                public void onFailure(Error error) {
                    Log.e(PluginHelper.TAG, "onFailure:" + error);
                    callbackContext.sendPluginResult(new PluginResult(Status.ERROR, error.getMessage()));
                }
            });
        } catch (Exception e) {
            callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
        }
    }

    public void fetchFloorsFromBuilding(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoBuilding = args.getJSONObject(0);
            Building building = SitumMapper.buildingJsonObjectToBuilding(jsonoBuilding);
            getCommunicationManagerInstance().fetchFloorsFromBuilding(building, new Handler<Collection<Floor>>() {
                @Override
                public void onSuccess(Collection<Floor> floors) {
                    try {
                        Log.d(PluginHelper.TAG, "onSuccess: Floors fetched successfully.");
                        JSONArray jsonaFloors = new JSONArray();

                        for (Floor floor : floors) {
                            Log.i(PluginHelper.TAG, "onSuccess: " + floor.getIdentifier());
                            JSONObject jsonoFloor = SitumMapper.floorToJsonObject(floor);
                            jsonaFloors.put(jsonoFloor);
                        }
                        if (floors.isEmpty()) {
                            Log.e(PluginHelper.TAG, "onSuccess: you have no floors defined for this building");
                        }
                        callbackContext.sendPluginResult(new PluginResult(Status.OK, jsonaFloors));
                    } catch (JSONException e) {
                        callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
                    }
                }

                @Override
                public void onFailure(Error error) {
                    Log.e(PluginHelper.TAG, "onFailure:" + error);
                    callbackContext.sendPluginResult(new PluginResult(Status.ERROR, error.getMessage()));
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error in floor response", e.getCause());
            callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
        }
    }

    public void fetchIndoorPOIsFromBuilding(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoBuilding = args.getJSONObject(0);
            Building building = SitumMapper.buildingJsonObjectToBuilding(jsonoBuilding);
            getCommunicationManagerInstance().fetchIndoorPOIsFromBuilding(building, new HashMap<String, Object>(),
                    new Handler<Collection<Poi>>() {
                        @Override
                        public void onSuccess(Collection<Poi> pois) {
                            try {
                                Log.d(PluginHelper.TAG, "onSuccess: Pois fetched successfully.");
                                JSONArray jsonaPois = new JSONArray();

                                for (Poi poi : pois) {
                                    Log.i(PluginHelper.TAG,
                                            "onSuccess: " + poi.getIdentifier() + " - " + poi.getName() + "-" + poi.getCustomFields());
                                    
                                    Log.d(PluginHelper.TAG, "Some log that should appear");
                                    JSONObject jsonoPoi = SitumMapper.poiToJsonObject(poi);
                                    jsonaPois.put(jsonoPoi);
                                }
                                if (pois.isEmpty()) {
                                    Log.e(PluginHelper.TAG,
                                            "onSuccess: you have no indoor pois defined for this building");
                                }
                                callbackContext.sendPluginResult(new PluginResult(Status.OK, jsonaPois));
                            } catch (JSONException e) {
                                callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
                            }
                        }

                        @Override
                        public void onFailure(Error error) {
                            Log.e(PluginHelper.TAG, "onFailure:" + error);
                            callbackContext.sendPluginResult(new PluginResult(Status.ERROR, error.getMessage()));
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error in poi response", e.getCause());
            callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
        }
    }

    public void fetchOutdoorPOIsFromBuilding(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoBuilding = args.getJSONObject(0);
            Building building = SitumMapper.buildingJsonObjectToBuilding(jsonoBuilding);
            getCommunicationManagerInstance().fetchOutdoorPOIsFromBuilding(building, new HashMap<String, Object>(),
                    new Handler<Collection<Poi>>() {
                        @Override
                        public void onSuccess(Collection<Poi> pois) {
                            try {
                                Log.d(PluginHelper.TAG, "onSuccess: Floors fetched successfully.");
                                JSONArray jsonaPois = new JSONArray();

                                for (Poi poi : pois) {
                                    Log.i(PluginHelper.TAG,
                                            "onSuccess: " + poi.getIdentifier() + " - " + poi.getName());
                                    JSONObject jsonoPoi = SitumMapper.poiToJsonObject(poi);
                                    jsonaPois.put(jsonoPoi);
                                }
                                if (pois.isEmpty()) {
                                    Log.e(PluginHelper.TAG,
                                            "onSuccess: you have no outdoor pois defined for this building");
                                }
                                callbackContext.sendPluginResult(new PluginResult(Status.OK, jsonaPois));
                            } catch (JSONException e) {
                                callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
                            }
                        }

                        @Override
                        public void onFailure(Error error) {
                            Log.e(PluginHelper.TAG, "onFailure:" + error);
                            callbackContext.sendPluginResult(new PluginResult(Status.ERROR, error.getMessage()));
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error in poi response", e.getCause());
            callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
        }
    }

    public void fetchPoiCategories(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        getCommunicationManagerInstance().fetchPoiCategories(new Handler<Collection<PoiCategory>>() {
            @Override
            public void onSuccess(Collection<PoiCategory> poiCategories) {
                try {
                    Log.d(PluginHelper.TAG, "onSuccess: POI Categories fetched successfully.");
                    JSONArray jsonaPoiCategories = new JSONArray();
                    for (PoiCategory poiCategory : poiCategories) {
                        Log.i(PluginHelper.TAG, "onSuccess: " + poiCategory.getCode() + " - " + poiCategory.getName());
                        JSONObject jsonoPoiCategory = SitumMapper.poiCategoryToJsonObject(poiCategory);
                        jsonaPoiCategories.put(jsonoPoiCategory);
                    }
                    if (poiCategories.isEmpty()) {
                        Log.e(PluginHelper.TAG, "onSuccess: you have no categories defined for POIs");
                    }
                    callbackContext.sendPluginResult(new PluginResult(Status.OK, jsonaPoiCategories));
                } catch (JSONException e) {
                    callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
                }
            }

            @Override
            public void onFailure(Error error) {
                Log.e(PluginHelper.TAG, "onFailure:" + error);
                callbackContext.sendPluginResult(new PluginResult(Status.ERROR, error.getMessage()));
            }
        });
    }

    public void fetchPoiCategoryIconNormal(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoCategory = args.getJSONObject(0);
            PoiCategory category = SitumMapper.poiCategoryFromJsonObject(jsonoCategory);
            getCommunicationManagerInstance().fetchPoiCategoryIconNormal(category, new Handler<Bitmap>() {
                @Override
                public void onSuccess(Bitmap bitmap) {
                    try {
                        Log.d(PluginHelper.TAG, "onSuccess: Poi icon fetched successfully");
                        JSONObject jsonoMap = SitumMapper.bitmapToString(bitmap);
                        callbackContext.sendPluginResult(new PluginResult(Status.OK, jsonoMap));
                    } catch (JSONException e) {
                        callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
                    }
                }

                @Override
                public void onFailure(Error error) {
                    Log.e(PluginHelper.TAG, "onFailure: " + error);
                    callbackContext.sendPluginResult(new PluginResult(Status.ERROR, error.getMessage()));
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error in situm POI response", e.getCause());
            callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
        }
    }

    public void fetchPoiCategoryIconSelected(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoCategory = args.getJSONObject(0);
            PoiCategory category = SitumMapper.poiCategoryFromJsonObject(jsonoCategory);
            getCommunicationManagerInstance().fetchPoiCategoryIconSelected(category, new Handler<Bitmap>() {
                @Override
                public void onSuccess(Bitmap bitmap) {
                    try {
                        Log.d(PluginHelper.TAG, "onSuccess: Poi icon fetched successfully");
                        JSONObject jsonoMap = SitumMapper.bitmapToString(bitmap);
                        callbackContext.sendPluginResult(new PluginResult(Status.OK, jsonoMap));
                    } catch (JSONException e) {
                        callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
                    }
                }

                @Override
                public void onFailure(Error error) {
                    Log.e(PluginHelper.TAG, "onFailure: " + error);
                    callbackContext.sendPluginResult(new PluginResult(Status.ERROR, error.getMessage()));
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error in situm POI response", e.getCause());
            callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
        }
    }

    public void fetchEventsFromBuilding(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoBuilding = args.getJSONObject(0);
            Building building = SitumMapper.buildingJsonObjectToBuilding(jsonoBuilding);
            getCommunicationManagerInstance().fetchEventsFromBuilding(building, new HashMap<String, Object>(),
                    new Handler<Collection<es.situm.sdk.v1.SitumEvent>>() {
                        @Override
                        public void onSuccess(Collection<SitumEvent> situmEvents) {
                            try {
                                Log.d(PluginHelper.TAG, "onSuccess: Floors fetched successfully.");
                                JSONArray jsonaEvents = new JSONArray();
                                for (SitumEvent situmEvent : situmEvents) {
                                    Log.i(PluginHelper.TAG,
                                            "onSuccess: " + situmEvent.getId() + " - " + situmEvent.getName());
                                    JSONObject jsonoSitumEvent = SitumMapper.situmEventToJsonObject(situmEvent);
                                    jsonaEvents.put(jsonoSitumEvent);
                                }
                                if (situmEvents.isEmpty()) {
                                    Log.e(PluginHelper.TAG, "onSuccess: you have no events defined for this building");
                                }
                                callbackContext.sendPluginResult(new PluginResult(Status.OK, jsonaEvents));
                            } catch (JSONException e) {
                                callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
                            }
                        }

                        @Override
                        public void onFailure(Error error) {
                            Log.e(PluginHelper.TAG, "onFailure:" + error);
                            callbackContext.sendPluginResult(new PluginResult(Status.ERROR, error.getMessage()));
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error in situm event response", e.getCause());
            callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
        }
    }

    public void fetchMapFromFloor(CordovaInterface cordova, CordovaWebView webView, final JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoFloor = args.getJSONObject(0);
            Floor floor = SitumMapper.floorJsonObjectToFloor(jsonoFloor);
            getCommunicationManagerInstance().fetchMapFromFloor(floor, new Handler<Bitmap>() {
                @Override
                public void onSuccess(Bitmap bitmap) {
                    try {
                        Log.d(PluginHelper.TAG, "onSuccess: Map fetched successfully");
                        JSONObject jsonoMap = SitumMapper.bitmapToString(bitmap);
                        callbackContext.sendPluginResult(new PluginResult(Status.OK, jsonoMap));
                    } catch (JSONException e) {
                        callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
                    }
                }

                @Override
                public void onFailure(Error error) {
                    Log.e(PluginHelper.TAG, "onFailure: " + error);
                    callbackContext.sendPluginResult(new PluginResult(Status.ERROR, error.getMessage()));
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error in map download", e.getCause());
            callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
        }
    }

    public OutdoorLocationOptions buildOutdoorLocationOptions(JSONObject outdoorLocationOptions) throws JSONException{
        OutdoorLocationOptions.Builder optionsBuilder = new OutdoorLocationOptions.Builder();

        if (outdoorLocationOptions.has(SitumMapper.CONTINUOUS_MODE)) {
            Boolean continuousMode = outdoorLocationOptions.getBoolean(SitumMapper.CONTINUOUS_MODE);
            optionsBuilder.continuousMode(continuousMode);
            Log.i(TAG, "continuousMode: " + continuousMode);
        }

        if (outdoorLocationOptions.has(SitumMapper.USER_DEFINED_THRESHOLD)) {
            Boolean userDefinedThreshold = outdoorLocationOptions.getBoolean(SitumMapper.USER_DEFINED_THRESHOLD);
            optionsBuilder.userDefinedThreshold(userDefinedThreshold);
            Log.i(TAG, "userDefinedThreshold: " + userDefinedThreshold);
        }

        if (outdoorLocationOptions.has(SitumMapper.BURST_INTERVAL)) {
            Integer burstInterval = outdoorLocationOptions.getInt(SitumMapper.BURST_INTERVAL);
            if (burstInterval != null && burstInterval >= 1) {
                optionsBuilder.burstInterval(burstInterval);
                Log.i(TAG, "burstInterval: " + burstInterval);
            }
        }

        if (outdoorLocationOptions.has(SitumMapper.AVERAGE_SNR_THRESHOLD));
        Float averageSnrThreshold = new Float(outdoorLocationOptions.getDouble(SitumMapper.AVERAGE_SNR_THRESHOLD));
        if (averageSnrThreshold != null && averageSnrThreshold >= MIN_SNR && averageSnrThreshold <= MAX_SNR) {
            optionsBuilder.averageSnrThreshold(averageSnrThreshold);
            Log.i(TAG, "averageSnrThreshold: " + averageSnrThreshold);
        }
     return optionsBuilder.build();
    }

    public LocationRequest buildLocationRequest(JSONArray args) throws JSONException {
        Builder locationBuilder = new Builder();
        JSONObject jsonoBuilding = args.getJSONObject(0);
        String sBuildingId = jsonoBuilding.getString(SitumMapper.BUILDING_IDENTIFIER);
        if (args.length() > 1) {
            JSONObject request = args.getJSONObject(1);
            if (request.has(SitumMapper.BUILDING_IDENTIFIER)) {
                String buildingIdentifier = request.getString(SitumMapper.BUILDING_IDENTIFIER);
                locationBuilder.buildingIdentifier(buildingIdentifier);
                Log.i(TAG, "buildingIdentifier: " + buildingIdentifier);
            }

            if (request.has(SitumMapper.INTERVAL)) {
                Integer interval = request.getInt(SitumMapper.INTERVAL);
                if (interval != null && interval >= 1000) {
                    locationBuilder.interval(interval);
                    Log.i(TAG, "interval: " + interval);
                }
            }

            if (request.has(SitumMapper.INDOOR_PROVIDER)) {
                String indoorProvider = request.getString(SitumMapper.INDOOR_PROVIDER);
                if (indoorProvider != null && !indoorProvider.isEmpty()) {
                    if (indoorProvider.equals(IndoorProvider.SUPPORT.name())) {
                        locationBuilder.indoorProvider(IndoorProvider.SUPPORT);
                    } else {
                        locationBuilder.indoorProvider(IndoorProvider.INPHONE); 
                    }
                    Log.i(TAG, "indoorProvider: " + indoorProvider);
                }
            }

            if (request.has(SitumMapper.USE_BLE)) {
                Boolean useBle = request.getBoolean(SitumMapper.USE_BLE);
                locationBuilder.useBle(useBle);
                Log.i(TAG, "useBle: " + useBle);
            }

            if (request.has(SitumMapper.USE_WIFI)) {
                Boolean useWifi = request.getBoolean(SitumMapper.USE_WIFI);
                locationBuilder.useWifi(useWifi);
                Log.i(TAG, "useWifi: " + useWifi);
            }

            if (request.has(SitumMapper.USE_GPS)) {
                Boolean useGps = request.getBoolean(SitumMapper.USE_GPS);
                locationBuilder.useGps(useGps);
                Log.i(TAG, "useGps: " + useGps);
            }
            
            if (request.has(SitumMapper.MOTION_MODE)) {
                String motionMode = request.getString(SitumMapper.MOTION_MODE);
                if (motionMode != null) {
                    if (motionMode.equals(MotionMode.BY_FOOT.name())) {
                        locationBuilder.motionMode(MotionMode.BY_FOOT);
                    } else if (motionMode.equals(MotionMode.BY_CAR.name())) {
                        locationBuilder.motionMode(MotionMode.BY_CAR);
                    }
                    Log.i(TAG, "motionMode: " + motionMode);
                }
            }

            if (request.has(SitumMapper.USE_FOREGROUND_SERVICE)) {
                Boolean useForegroundService = request.getBoolean(SitumMapper.USE_FOREGROUND_SERVICE);
                locationBuilder.useForegroundService(useForegroundService);
                Log.i(TAG, "useForegroundService: " + useForegroundService);
            }

            if (request.has(SitumMapper.USE_DEAD_RECKONING)) {
                Boolean useDeadReckoning = request.getBoolean(SitumMapper.USE_DEAD_RECKONING);
                locationBuilder.useDeadReckoning(useDeadReckoning);
                Log.i(TAG, "useDeadReckoning: " + useDeadReckoning);
            }

            if (request.has(SitumMapper.OUTDOOR_LOCATION_OPTIONS)) {
                JSONObject outdoorLocationOptions = request.getJSONObject(SitumMapper.OUTDOOR_LOCATION_OPTIONS);
                if (outdoorLocationOptions != null) {
                    locationBuilder.outdoorLocationOptions(buildOutdoorLocationOptions(outdoorLocationOptions));
                }
            }

            if (request.has(SitumMapper.BEACON_FILTERS)) {
                JSONArray beaconFilters = request.getJSONArray(SitumMapper.BEACON_FILTERS);
                List<BeaconFilter> filtersList = new ArrayList<BeaconFilter>();
                for (int i = 0; i < beaconFilters.length(); i++) {
                    JSONObject beaconFilter = beaconFilters.getJSONObject(i);
                    if (beaconFilter.has(SitumMapper.UUID)) {
                        String uuid = beaconFilter.getString(SitumMapper.UUID);
                        if (uuid != null && !uuid.isEmpty()) {
                            BeaconFilter.Builder builder = new BeaconFilter.Builder().uuid(uuid);
                            filtersList.add(builder.build());
                            Log.i(TAG, "beaconFilter: " + uuid);
                        }
                    }
                }

                locationBuilder.addBeaconFilters(filtersList);
            }

            if (request.has(SitumMapper.SMALLEST_DISPLACEMENT)) {
                Float smallestDisplacement = new Float(request.getDouble(SitumMapper.SMALLEST_DISPLACEMENT));
                if (smallestDisplacement != null && smallestDisplacement > 0) {
                    locationBuilder.smallestDisplacement(smallestDisplacement);
                    Log.i(TAG, "smallestDisplacement: " + smallestDisplacement);
                }    
            }

            if (request.has(SitumMapper.REALTIME_UPDATE_INTERVAL) &&
                    request.get(SitumMapper.REALTIME_UPDATE_INTERVAL) instanceof String) {
                String realtimeUpdateInterval = request.getString(SitumMapper.REALTIME_UPDATE_INTERVAL);
                if (realtimeUpdateInterval != null) {
                    if (realtimeUpdateInterval.equals(RealtimeUpdateInterval.REALTIME.name())) {
                        locationBuilder.realtimeUpdateInterval(RealtimeUpdateInterval.REALTIME);
                    } else if (realtimeUpdateInterval.equals(RealtimeUpdateInterval.FAST.name())) {
                        locationBuilder.realtimeUpdateInterval(RealtimeUpdateInterval.FAST);
                    } else if (realtimeUpdateInterval.equals(RealtimeUpdateInterval.NORMAL.name())) {
                        locationBuilder.realtimeUpdateInterval(RealtimeUpdateInterval.NORMAL);
                    } else if (realtimeUpdateInterval.equals(RealtimeUpdateInterval.SLOW.name())) {
                        locationBuilder.realtimeUpdateInterval(RealtimeUpdateInterval.SLOW);
                    } else if (realtimeUpdateInterval.equals(RealtimeUpdateInterval.BATTERY_SAVER.name())) {
                        locationBuilder.realtimeUpdateInterval(RealtimeUpdateInterval.BATTERY_SAVER);
                    }
                    Log.i(TAG, "realtimeUpdateInterval: " + realtimeUpdateInterval);
                }
            }
        } else {
           locationBuilder.buildingIdentifier(sBuildingId);
        }

        return locationBuilder.build();
    }

    public void startPositioning(final CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoBuilding = args.getJSONObject(0);
            String sBuildingName = jsonoBuilding.getString(SitumMapper.BUILDING_NAME);
            if (locationListener == null) {
                LocationRequest locationRequest = buildLocationRequest(args);

                Log.i(TAG, "startPositioning: starting positioning in " + sBuildingName);
                locationListener = new LocationListener() {
                    public void onLocationChanged(Location location) {
                        try {
                            PluginHelper.this.computedLocation = location; // This is for testing purposes
                            Log.i(PluginHelper.TAG, "onLocationChanged() called with: location = [" + location + "]");
                            JSONObject jsonObject = SitumMapper.locationToJsonObject(location);
                            PluginResult result = new PluginResult(Status.OK, jsonObject);
                            result.setKeepCallback(true);
                            callbackContext.sendPluginResult(result);
                        } catch (JSONException e) {
                            callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
                        }
                    }

                    public void onStatusChanged(@NonNull LocationStatus status) {
                        try {
                            Log.i(PluginHelper.TAG, "onStatusChanged() called with: status = [" + status + "]");
                            JSONObject jsonObject = SitumMapper.locationStatusToJsonObject(status);
                            PluginResult result = new PluginResult(Status.OK, jsonObject);
                            result.setKeepCallback(true);
                            callbackContext.sendPluginResult(result);
                        } catch (JSONException e) {
                            callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
                        }
                    }

                    public void onError(@NonNull Error error) {
                        Log.e(PluginHelper.TAG, "onError() called with: error = [" + error + "]");
                        PluginResult result = new PluginResult(Status.ERROR, error.getMessage());
                        result.setKeepCallback(true);
                        callbackContext.sendPluginResult(result);
                        switch (error.getCode()) {
                        case 8001:
                            requestLocationPermission(cordova);
                            return;
                        case 8002:
                            showLocationSettings(cordova);
                            return;
                        default:
                            return;
                        }
                    }
                };
                try {
                    SitumSdk.locationManager().requestLocationUpdates(locationRequest, locationListener);
                } catch (Exception e) {
                    Log.e(PluginHelper.TAG, "onError() called with: error = [" + e + "]");
                }
                return;
            }
            Log.i(TAG, "startPositioning: location listener is already started.");
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error building response", e.getCause());
            callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
        }
    }

    public void stopPositioning(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            CallbackContext callbackContext) {
        if (locationListener != null) {
            try {
                SitumSdk.locationManager().removeUpdates(locationListener);
                locationListener = null;
                callbackContext.sendPluginResult(new PluginResult(Status.OK, "Success"));
            } catch (Exception e) {
                callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
            }
        } else {
            Log.i(TAG, "stopPositioning: location listener is not started.");
            callbackContext.sendPluginResult(new PluginResult(Status.OK, "Allready disabled"));
        }
    }

    private void showLocationSettings(CordovaInterface cordova) {
        Toast.makeText(cordova.getActivity(), "You must enable location", Toast.LENGTH_LONG).show();
        cordova.getActivity().startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 0);
    }

    private void requestLocationPermission(CordovaInterface cordova) {
        ActivityCompat.requestPermissions(cordova.getActivity(),
                new String[] { "android.permission.ACCESS_COARSE_LOCATION" }, 0);
    }

    public void returnDefaultResponse(CallbackContext callbackContext) {
        String message = "Error function name not found";
        Log.e(TAG, message);
        callbackContext.sendPluginResult(new PluginResult(Status.OK, message));
    }

    public void invalidateCache(CallbackContext callbackContext) {
        getCommunicationManagerInstance().invalidateCache();
        callbackContext.sendPluginResult(new PluginResult(Status.OK, "Cache invalidated"));
    }

    public void requestNavigationUpdates(final CordovaInterface cordova,
     CordovaWebView webView, 
     JSONArray args, 
     final CallbackContext callbackContext) {
            // 1) Parse and check arguments

            if (this.computedRoute == null) {
                Log.d(TAG, "Situm >> There is not an stored route so you are not allowed to navigate");
                callbackContext.sendPluginResult(new PluginResult(Status.ERROR, "Compute a valid route with requestDirections before trying to navigate within a route"));
                return;
            }
            // try??
            Route route = this.computedRoute; // args.getJSONObject(0); // Retrieve route from arguments, we do this since Route object has internal properties that we do not want to expose
            // 2) Build Navigation Arguments
            // 2.1) Build Navigation Request
            Log.d(TAG,"requestNavigationUpdates executed: passed route: " +  route);
            NavigationRequest.Builder builder = new NavigationRequest.Builder().route(route);

            try {
                JSONObject navigationJSONOptions = args.getJSONObject(0); // Route should be the first parameter

                if (navigationJSONOptions.has(SitumMapper.DISTANCE_TO_IGNORE_FIRST_INDICATION)) {
                    Double distanceToIgnoreFirstIndication = navigationJSONOptions.getDouble(SitumMapper.DISTANCE_TO_IGNORE_FIRST_INDICATION);
                    builder.distanceToIgnoreFirstIndication(distanceToIgnoreFirstIndication);
                }

                if (navigationJSONOptions.has(SitumMapper.OUTSIDE_ROUTE_THRESHOLD)) {
                    Double outsideRouteThreshold = navigationJSONOptions.getDouble(SitumMapper.OUTSIDE_ROUTE_THRESHOLD);
                    builder.outsideRouteThreshold(outsideRouteThreshold);
                }

                if (navigationJSONOptions.has(SitumMapper.DISTANCE_TO_GOAL_THRESHOLD)) {
                    Double distanceToGoalThreshold = navigationJSONOptions.getDouble(SitumMapper.DISTANCE_TO_GOAL_THRESHOLD);
                    builder.distanceToGoalThreshold(distanceToGoalThreshold);
                }

                if (navigationJSONOptions.has(SitumMapper.DISTANCE_TO_CHANGE_FLOOR_THRESHOLD)) {
                    Double distanceToChangeFloorThreshold = navigationJSONOptions.getDouble(SitumMapper.DISTANCE_TO_CHANGE_FLOOR_THRESHOLD);
                    builder.distanceToChangeFloorThreshold(distanceToChangeFloorThreshold);
                }

                if (navigationJSONOptions.has(SitumMapper.DISTANCE_TO_CHANGE_INDICATION_THRESHOLD)) {
                    Double distanceToChangeIndicationThreshold = navigationJSONOptions.getDouble(SitumMapper.DISTANCE_TO_CHANGE_INDICATION_THRESHOLD);
                    builder.distanceToChangeIndicationThreshold(distanceToChangeIndicationThreshold);
                }

                if (navigationJSONOptions.has(SitumMapper.INDICATIONS_INTERVAL)) {
                    Long indicationsInterval = navigationJSONOptions.getLong(SitumMapper.INDICATIONS_INTERVAL);
                    builder.indicationsInterval(indicationsInterval);
                }

                if (navigationJSONOptions.has(SitumMapper.TIME_TO_FIRST_INDICATION)) {
                    Long timeToFirstIndication = navigationJSONOptions.getLong(SitumMapper.TIME_TO_FIRST_INDICATION);
                    builder.timeToFirstIndication(timeToFirstIndication);
                }

                if (navigationJSONOptions.has(SitumMapper.ROUND_INDICATION_STEP)) {
                    Integer roundIndicationsStep = navigationJSONOptions.getInt(SitumMapper.ROUND_INDICATION_STEP);
                    builder.roundIndicationsStep(roundIndicationsStep);
                }

                if (navigationJSONOptions.has(SitumMapper.TIME_TO_IGNORE_UNEXPECTED_FLOOR_CHANGES)) {
                    Integer timeToIgnoreUnexpectedFloorChanges = navigationJSONOptions.getInt(SitumMapper.TIME_TO_IGNORE_UNEXPECTED_FLOOR_CHANGES);
                    builder.timeToIgnoreUnexpectedFloorChanges(timeToIgnoreUnexpectedFloorChanges);
                }

            } catch (Exception e) {
                //TODO: handle exception
                Log.d(TAG, "Situm >> Unable to retrieve navigation options. Applying default ones");
            }

            navigationRequest = builder.build();

            // 2.2) Build Navigation Callback
            navigationListener = new NavigationListener()   {
                public void onProgress(NavigationProgress progress) {
                    Log.d(TAG, "On progress received: " + progress);
                    try {
                        JSONObject jsonProgress = SitumMapper.navigationProgressToJsonObject(progress, cordova.getActivity());
                        try {
                            jsonProgress.put("type", "progress");
                        } catch (JSONException e) {
                            Log.e(TAG, "error inserting type in navigation progress");
                        }
                        PluginResult result = new PluginResult(Status.OK, jsonProgress ); // TODO: Change this to return an object with valid information
                        result.setKeepCallback(true);
                        callbackContext.sendPluginResult(result);        
    
                    } catch (Exception e) {
                        //TODO: handle exception
                        Log.d(TAG, "On Error parsing progress: " + progress);
                        PluginResult result = new PluginResult(Status.ERROR, e.getMessage());
                        result.setKeepCallback(true);
                        callbackContext.sendPluginResult(result);
                    }
                };

                public void onDestinationReached() {
                    Log.d(TAG, "On destination reached: ");
                    JSONObject jsonResult = new JSONObject();
                    try {
                        jsonResult.put("type", "destinationReached");
                        jsonResult.put("message", "Destination reached");
                        } catch (JSONException e) {
                        Log.e(TAG, "error inserting type in destination reached");
                    }
                    PluginResult result = new PluginResult(Status.OK,jsonResult);
                    result.setKeepCallback(true);
                    callbackContext.sendPluginResult(result);        
                };

                public void onUserOutsideRoute() {
                    Log.d(TAG, "On user outside route: " );
                    JSONObject jsonResult = new JSONObject();
                    try {
                        jsonResult.put("type", "userOutsideRoute");
                        jsonResult.put("message", "User outside route");
                    } catch (JSONException e) {
                        Log.e(TAG, "error inserting type in user outside route");
                    }
                    PluginResult result = new PluginResult(Status.OK,jsonResult);
                    result.setKeepCallback(true);
                    callbackContext.sendPluginResult(result);        
                }
            };
            
            // 3)  Connect interfaces and connect callback back to js
            getNavigationManagerInstance().requestNavigationUpdates(navigationRequest, navigationListener); // Be carefull with exceptions

            PluginResult result = new PluginResult(Status.OK, "Requested navigation successfully"); // TODO: Change this to return an object with valid information
            result.setKeepCallback(true);
            callbackContext.sendPluginResult(result);

    }

    // Initialize Navigation Component 

    public void updateNavigationWithLocation(CordovaInterface cordova,
    CordovaWebView webView, 
    JSONArray args, 
    final CallbackContext callbackContext) {
        try {
            // 1) Check for location arguments
            JSONObject jsonLocation = args.getJSONObject(0); // What if json is not specified?

            // 2) Create a Location Object from argument
            Location actualLocation = SitumMapper.jsonLocationObjectToLocation(jsonLocation); // Location Objet from JSON
                // Location actualLocation = PluginHelper.computedLocation;
            Log.i(TAG, "UpdateNavigation with Location: " + actualLocation);

            // 3) Connect interfaces
            getNavigationManagerInstance().updateWithLocation(actualLocation); 
            callbackContext.sendPluginResult(new PluginResult(Status.OK, "Navigation updated"));
        } catch (Exception e) {
            e.printStackTrace();
            callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
        }
    }

    public void removeNavigationUpdates(CordovaInterface cordova,
    CordovaWebView webView, 
    JSONArray args, 
    final CallbackContext callbackContext) {
        // 
        Log.i(TAG, "Remove navigation updates");
        getNavigationManagerInstance().removeUpdates(); // TODO: Incorporate sending a result to the exterior
    }

    public void requestDirections(final CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoBuilding = args.getJSONObject(0);
            JSONObject jsonoFrom = args.getJSONObject(1);
            JSONObject jsonoTo = args.getJSONObject(2);
            JSONObject jsonoOptions = null;
            if (args.length() > 2) {
                jsonoOptions = args.getJSONObject(3);
            }
            DirectionsRequest directionRequest =
                    SitumMapper.jsonObjectToDirectionsRequest(jsonoBuilding, jsonoFrom, jsonoTo, jsonoOptions);
            SitumSdk.directionsManager().requestDirections(directionRequest, new Handler<Route>() {
                @Override
                public void onSuccess(Route route) {
                    try {
                        PluginHelper.this.computedRoute = route;
                        JSONObject jsonoRoute = SitumMapper.routeToJsonObject(route, cordova.getActivity());
                        Log.i(TAG, "onSuccess: Route calculated successfully" + route);
                        callbackContext.sendPluginResult(new PluginResult(Status.OK, jsonoRoute));
                    } catch (JSONException e) {
                        callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
                    }
                }

                @Override
                public void onFailure(Error error) {
                    Log.e(TAG, "onError:" + error.getMessage());
                    callbackContext.sendPluginResult(new PluginResult(Status.ERROR, error.getMessage()));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
        }
    }
}