package es.situm.plugin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.telecom.Call;
import android.util.Log;
import android.webkit.WebSettings;
import android.widget.Toast;
import es.situm.sdk.SitumSdk;
import es.situm.sdk.directions.DirectionsManager;
import es.situm.sdk.directions.DirectionsRequest;
import es.situm.sdk.error.Error;
import es.situm.sdk.location.LocationListener;
import es.situm.sdk.location.LocationRequest;
import es.situm.sdk.model.location.Angle;
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
import es.situm.sdk.model.cartography.Point;
import es.situm.sdk.model.directions.Route;
import es.situm.sdk.model.location.BeaconFilter;
import es.situm.sdk.model.location.CartesianCoordinate;
import es.situm.sdk.model.location.Location;
import es.situm.sdk.utils.Handler;
import es.situm.sdk.v1.SitumEvent;
import es.situm.sdk.navigation.NavigationRequest;
import es.situm.sdk.navigation.NavigationListener;
import es.situm.sdk.model.navigation.NavigationProgress;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.datatype.DatatypeFactory;

public class PluginHelper {

    private static final String TAG = "PluginHelper";

    private static LocationListener locationListener;
    private static LocationRequest locationRequest;
    private static NavigationListener navigationListener;
    private static NavigationRequest navigationRequest;

    public static final float MIN_SNR = 10;
    public static final float MAX_SNR = 40;

    private static Route computedRoute;
    private static Location computedLocation;

    public static void fetchBuildings(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
            SitumSdk.communicationManager().fetchBuildings(new Handler<Collection<Building>>() {
                public void onSuccess(Collection<Building> buildings) {
                    try {
                        Log.d(PluginHelper.TAG, "onSuccess: Buildings fetched successfully.");
                        JSONArray jsonaBuildings = new JSONArray();

                        for (Building building : buildings) {
                            Log.i(PluginHelper.TAG,
                                    "onSuccess: " + building.getIdentifier() + " - " + building.getName());
                            JSONObject jsonoBuilding = LocationWrapper.buildingToJsonObject(building);
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

    public static void fetchFloorsFromBuilding(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoBuilding = args.getJSONObject(0);
            Building building = LocationWrapper.buildingJsonObjectToBuilding(jsonoBuilding);
            SitumSdk.communicationManager().fetchFloorsFromBuilding(building, new Handler<Collection<Floor>>() {
                @Override
                public void onSuccess(Collection<Floor> floors) {
                    try {
                        Log.d(PluginHelper.TAG, "onSuccess: Floors fetched successfully.");
                        JSONArray jsonaFloors = new JSONArray();

                        for (Floor floor : floors) {
                            Log.i(PluginHelper.TAG, "onSuccess: " + floor.getIdentifier());
                            JSONObject jsonoFloor = LocationWrapper.floorToJsonObject(floor);
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

    public static void fetchIndoorPOIsFromBuilding(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoBuilding = args.getJSONObject(0);
            Building building = LocationWrapper.buildingJsonObjectToBuilding(jsonoBuilding);
            SitumSdk.communicationManager().fetchIndoorPOIsFromBuilding(building, new HashMap<String, Object>(),
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
                                    JSONObject jsonoPoi = LocationWrapper.poiToJsonObject(poi);
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

    public static void fetchOutdoorPOIsFromBuilding(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoBuilding = args.getJSONObject(0);
            Building building = LocationWrapper.buildingJsonObjectToBuilding(jsonoBuilding);
            SitumSdk.communicationManager().fetchOutdoorPOIsFromBuilding(building, new HashMap<String, Object>(),
                    new Handler<Collection<Poi>>() {
                        @Override
                        public void onSuccess(Collection<Poi> pois) {
                            try {
                                Log.d(PluginHelper.TAG, "onSuccess: Floors fetched successfully.");
                                JSONArray jsonaPois = new JSONArray();

                                for (Poi poi : pois) {
                                    Log.i(PluginHelper.TAG,
                                            "onSuccess: " + poi.getIdentifier() + " - " + poi.getName());
                                    JSONObject jsonoPoi = LocationWrapper.poiToJsonObject(poi);
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

    public static void fetchPoiCategories(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        SitumSdk.communicationManager().fetchPoiCategories(new Handler<Collection<PoiCategory>>() {
            @Override
            public void onSuccess(Collection<PoiCategory> poiCategories) {
                try {
                    Log.d(PluginHelper.TAG, "onSuccess: POI Categories fetched successfully.");
                    JSONArray jsonaPoiCategories = new JSONArray();
                    for (PoiCategory poiCategory : poiCategories) {
                        Log.i(PluginHelper.TAG, "onSuccess: " + poiCategory.getCode() + " - " + poiCategory.getName());
                        JSONObject jsonoPoiCategory = LocationWrapper.poiCategoryToJsonObject(poiCategory);
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

    public static void fetchPoiCategoryIconNormal(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoCategory = args.getJSONObject(0);
            PoiCategory category = LocationWrapper.poiCategoryFromJsonObject(jsonoCategory);
            SitumSdk.communicationManager().fetchPoiCategoryIconNormal(category, new Handler<Bitmap>() {
                @Override
                public void onSuccess(Bitmap bitmap) {
                    try {
                        Log.d(PluginHelper.TAG, "onSuccess: Poi icon fetched successfully");
                        JSONObject jsonoMap = LocationWrapper.bitmapToString(bitmap);
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

    public static void fetchPoiCategoryIconSelected(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoCategory = args.getJSONObject(0);
            PoiCategory category = LocationWrapper.poiCategoryFromJsonObject(jsonoCategory);
            SitumSdk.communicationManager().fetchPoiCategoryIconNormal(category, new Handler<Bitmap>() {
                @Override
                public void onSuccess(Bitmap bitmap) {
                    try {
                        Log.d(PluginHelper.TAG, "onSuccess: Poi icon fetched successfully");
                        JSONObject jsonoMap = LocationWrapper.bitmapToString(bitmap);
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

    public static void fetchEventsFromBuilding(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoBuilding = args.getJSONObject(0);
            Building building = LocationWrapper.buildingJsonObjectToBuilding(jsonoBuilding);
            SitumSdk.communicationManager().fetchEventsFromBuilding(building, new HashMap<String, Object>(),
                    new Handler<Collection<es.situm.sdk.v1.SitumEvent>>() {
                        @Override
                        public void onSuccess(Collection<SitumEvent> situmEvents) {
                            try {
                                Log.d(PluginHelper.TAG, "onSuccess: Floors fetched successfully.");
                                JSONArray jsonaEvents = new JSONArray();
                                for (SitumEvent situmEvent : situmEvents) {
                                    Log.i(PluginHelper.TAG,
                                            "onSuccess: " + situmEvent.getId() + " - " + situmEvent.getName());
                                    JSONObject jsonoSitumEvent = LocationWrapper.situmEventToJsonObject(situmEvent);
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

    public static void fetchMapFromFloor(CordovaInterface cordova, CordovaWebView webView, final JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoFloor = args.getJSONObject(0);
            Floor floor = LocationWrapper.floorJsonObjectToFloor(jsonoFloor);
            SitumSdk.communicationManager().fetchMapFromFloor(floor, new Handler<Bitmap>() {
                @Override
                public void onSuccess(Bitmap bitmap) {
                    try {
                        Log.d(PluginHelper.TAG, "onSuccess: Map fetched successfully");
                        JSONObject jsonoMap = LocationWrapper.bitmapToString(bitmap);
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

    public static OutdoorLocationOptions buildOutdoorLocationOptions(JSONObject outdoorLocationOptions) throws JSONException{
        OutdoorLocationOptions.Builder optionsBuilder = new OutdoorLocationOptions.Builder();

        if (outdoorLocationOptions.has(LocationWrapper.CONTINUOUS_MODE)) {
            Boolean continuousMode = outdoorLocationOptions.getBoolean(LocationWrapper.CONTINUOUS_MODE);
            optionsBuilder.continuousMode(continuousMode);
            Log.i(TAG, "continuousMode: " + continuousMode);
        }

        if (outdoorLocationOptions.has(LocationWrapper.USER_DEFINED_THRESHOLD)) {
            Boolean userDefinedThreshold = outdoorLocationOptions.getBoolean(LocationWrapper.USER_DEFINED_THRESHOLD);
            optionsBuilder.userDefinedThreshold(userDefinedThreshold);
            Log.i(TAG, "userDefinedThreshold: " + userDefinedThreshold);
        }

        if (outdoorLocationOptions.has(LocationWrapper.BURST_INTERVAL)) {
            Integer burstInterval = outdoorLocationOptions.getInt(LocationWrapper.BURST_INTERVAL);
            if (burstInterval != null && burstInterval >= 1) {
                optionsBuilder.burstInterval(burstInterval);
                Log.i(TAG, "burstInterval: " + burstInterval);
            }
        }

        if (outdoorLocationOptions.has(LocationWrapper.AVERAGE_SNR_THRESHOLD));
        Float averageSnrThreshold = new Float(outdoorLocationOptions.getDouble(LocationWrapper.AVERAGE_SNR_THRESHOLD));
        if (averageSnrThreshold != null && averageSnrThreshold >= MIN_SNR && averageSnrThreshold <= MAX_SNR) {
            optionsBuilder.averageSnrThreshold(averageSnrThreshold);
            Log.i(TAG, "averageSnrThreshold: " + averageSnrThreshold);
        }
     return optionsBuilder.build();
    }

    public static LocationRequest buildLocationRequest(JSONArray args) throws JSONException {
        Builder locationBuilder = new Builder();
        JSONObject jsonoBuilding = args.getJSONObject(0);
        String sBuildingId = jsonoBuilding.getString(LocationWrapper.BUILDING_IDENTIFIER);
        if (args.length() > 1) {
            JSONObject request = args.getJSONObject(1);
            if (request.has(LocationWrapper.BUILDING_IDENTIFIER)) {
                String buildingIdentifier = request.getString(LocationWrapper.BUILDING_IDENTIFIER);
                locationBuilder.buildingIdentifier(buildingIdentifier);
                Log.i(TAG, "buildingIdentifier: " + buildingIdentifier);
            }

            if (request.has(LocationWrapper.INTERVAL)) {
                Integer interval = request.getInt(LocationWrapper.INTERVAL);
                if (interval != null && interval >= 1000) {
                    locationBuilder.interval(interval);
                    Log.i(TAG, "interval: " + interval);
                }
            }

            if (request.has(LocationWrapper.INDOOR_PROVIDER)) {
                String indoorProvider = request.getString(LocationWrapper.INDOOR_PROVIDER);
                if (indoorProvider != null && !indoorProvider.isEmpty()) {
                    if (indoorProvider.equals(IndoorProvider.SUPPORT.name())) {
                        locationBuilder.indoorProvider(IndoorProvider.SUPPORT);
                    } else {
                        locationBuilder.indoorProvider(IndoorProvider.INPHONE); 
                    }
                    Log.i(TAG, "indoorProvider: " + indoorProvider);
                }
            }

            if (request.has(LocationWrapper.USE_BLE)) {
                Boolean useBle = request.getBoolean(LocationWrapper.USE_BLE);
                locationBuilder.useBle(useBle);
                Log.i(TAG, "useBle: " + useBle);
            }

            if (request.has(LocationWrapper.USE_WIFI)) {
                Boolean useWifi = request.getBoolean(LocationWrapper.USE_WIFI);
                locationBuilder.useWifi(useWifi);
                Log.i(TAG, "useWifi: " + useWifi);
            }
            
            if (request.has(LocationWrapper.MOTION_MODE)) {
                String motionMode = request.getString(LocationWrapper.MOTION_MODE);
                if (motionMode != null) {
                    if (motionMode.equals(MotionMode.BY_FOOT.name())) {
                        locationBuilder.motionMode(MotionMode.BY_FOOT);
                    } else if (motionMode.equals(MotionMode.BY_CAR.name())) {
                        locationBuilder.motionMode(MotionMode.BY_CAR);
                    }
                    Log.i(TAG, "motionMode: " + motionMode);
                }
            }

            if (request.has(LocationWrapper.USE_FOREGROUND_SERVICE)) {
                Boolean useForegroundService = request.getBoolean(LocationWrapper.USE_FOREGROUND_SERVICE);
                locationBuilder.useForegroundService(useForegroundService);
                Log.i(TAG, "useForegroundService: " + useForegroundService);
            }

            if (request.has(LocationWrapper.USE_DEAD_RECKONING)) {
                Boolean useDeadReckoning = request.getBoolean(LocationWrapper.USE_DEAD_RECKONING);
                locationBuilder.useDeadReckoning(useDeadReckoning);
                Log.i(TAG, "useDeadReckoning: " + useDeadReckoning);
            }

            if (request.has(LocationWrapper.OUTDOOR_LOCATION_OPTIONS)) {
                JSONObject outdoorLocationOptions = request.getJSONObject(LocationWrapper.OUTDOOR_LOCATION_OPTIONS);
                if (outdoorLocationOptions != null) {
                    locationBuilder.outdoorLocationOptions(buildOutdoorLocationOptions(outdoorLocationOptions));
                }
            }

            if (request.has(LocationWrapper.BEACON_FILTERS)) {
                JSONArray beaconFilters = request.getJSONArray(LocationWrapper.BEACON_FILTERS);
                List<BeaconFilter> filtersList = new ArrayList<BeaconFilter>();
                for (int i = 0; i < beaconFilters.length(); i++) {
                    JSONObject beaconFilter = beaconFilters.getJSONObject(i);
                    if (beaconFilter.has(LocationWrapper.UUID)) {
                        String uuid = beaconFilter.getString(LocationWrapper.UUID);
                        if (uuid != null && !uuid.isEmpty()) {
                            BeaconFilter.Builder builder = new BeaconFilter.Builder().uuid(uuid);
                            filtersList.add(builder.build());
                            Log.i(TAG, "beaconFilter: " + uuid);
                        }
                    }
                }

                locationBuilder.addBeaconFilters(filtersList);
            }

            if (request.has(LocationWrapper.SMALLEST_DISPLACEMENT)) {
                Float smallestDisplacement = new Float(request.getDouble(LocationWrapper.SMALLEST_DISPLACEMENT));
                if (smallestDisplacement != null && smallestDisplacement > 0) {
                    locationBuilder.smallestDisplacement(smallestDisplacement);
                    Log.i(TAG, "smallestDisplacement: " + smallestDisplacement);
                }    
            }

            if (request.has(LocationWrapper.REALTIME_UPDATE_INTERVAL)) {
                Integer realtimeUpdateInterval = request.getInt(LocationWrapper.REALTIME_UPDATE_INTERVAL);
                if (realtimeUpdateInterval != null) {
                    if (realtimeUpdateInterval.equals(RealtimeUpdateInterval.REALTIME)) {
                        locationBuilder.realtimeUpdateInterval(RealtimeUpdateInterval.REALTIME);
                    } else if (realtimeUpdateInterval.equals(RealtimeUpdateInterval.FAST)) {
                        locationBuilder.realtimeUpdateInterval(RealtimeUpdateInterval.FAST);
                    } else if (realtimeUpdateInterval.equals(RealtimeUpdateInterval.NORMAL)) {
                        locationBuilder.realtimeUpdateInterval(RealtimeUpdateInterval.NORMAL);
                    } else if (realtimeUpdateInterval.equals(RealtimeUpdateInterval.SLOW)) {
                        locationBuilder.realtimeUpdateInterval(RealtimeUpdateInterval.SLOW);
                    }
                    Log.i(TAG, "realtimeUpdateInterval: " + realtimeUpdateInterval);
                }
            }
        } else {
           locationBuilder.buildingIdentifier(sBuildingId);
        }

        LocationRequest locationRequest = locationBuilder.build();
        return locationRequest;
    }

    public static void startPositioning(final CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoBuilding = args.getJSONObject(0);
            String sBuildingName = jsonoBuilding.getString(LocationWrapper.BUILDING_NAME);
            if (locationListener == null) {
                LocationRequest locationRequest = buildLocationRequest(args);

                Log.i(TAG, "startPositioning: starting positioning in " + sBuildingName);
                locationListener = new LocationListener() {
                    public void onLocationChanged(Location location) {
                        try {
                            PluginHelper.computedLocation = location; // This is for testing purposes
                            Log.i(PluginHelper.TAG, "onLocationChanged() called with: location = [" + location + "]");
                            CartesianCoordinate cartesianCoordinate = location.getCartesianCoordinate();
                            String locationMessage = "building: " + location.getBuildingIdentifier() + "\nfloor: "
                                    + location.getFloorIdentifier() + "\nx: " + cartesianCoordinate.getX() + "\ny: "
                                    + cartesianCoordinate.getY() + "\nyaw: " + location.getCartesianBearing()
                                    + "\naccuracy: " + location.getAccuracy();
                            JSONObject jsonObject = LocationWrapper.locationToJsonObject(location);
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
                            JSONObject jsonObject = LocationWrapper.locationStatusToJsonObject(status);
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
                            PluginHelper.requestLocationPermission(cordova);
                            return;
                        case 8002:
                            PluginHelper.showLocationSettings(cordova);
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

    public static void stopPositioning(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
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

    private static void showLocationSettings(CordovaInterface cordova) {
        Toast.makeText(cordova.getActivity(), "You must enable location", Toast.LENGTH_LONG).show();
        cordova.getActivity().startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 0);
    }

    private static void requestLocationPermission(CordovaInterface cordova) {
        ActivityCompat.requestPermissions(cordova.getActivity(),
                new String[] { "android.permission.ACCESS_COARSE_LOCATION" }, 0);
    }

    public static void returnDefaultResponse(CallbackContext callbackContext) {
        String message = "Error function name not found";
        Log.e(TAG, message);
        callbackContext.sendPluginResult(new PluginResult(Status.OK, message));
    }

    public static void invalidateCache(CallbackContext callbackContext) {
        SitumSdk.communicationManager().invalidateCache();
        callbackContext.sendPluginResult(new PluginResult(Status.OK, "Cache invalidated"));
    }

    public static void requestNavigationUpdates(CordovaInterface cordova,
     CordovaWebView webView, 
     JSONArray args, 
     final CallbackContext callbackContext) {
            // 1) Parse and check arguments

            if (PluginHelper.computedRoute == null) {
                Log.d(TAG, "Situm >> There is not an stored route so you are not allowed to navigate");
                callbackContext.sendPluginResult(new PluginResult(Status.ERROR, "Compute a valid route with requestDirections before trying to navigate within a route"));
                return;
            }
            // try??
            Route route = PluginHelper.computedRoute; // args.getJSONObject(0); // Retrieve route from arguments, we do this since Route object has internal properties that we do not want to expose
            // 2) Build Navigation Arguments
            // 2.1) Build Navigation Request
            Log.d(TAG,"requestNavigationUpdates executed: passed route: " +  route);
            NavigationRequest.Builder builder = new NavigationRequest.Builder().route(route);

            try {
                JSONObject navigationJSONOptions = args.getJSONObject(0); // Route should be the first parameter

                if (navigationJSONOptions.has(LocationWrapper.DISTANCE_TO_IGNORE_FIRST_INDICATION)) {
                    Double distanceToIgnoreFirstIndication = navigationJSONOptions.getDouble(LocationWrapper.DISTANCE_TO_IGNORE_FIRST_INDICATION);
                    builder.distanceToIgnoreFirstIndication(distanceToIgnoreFirstIndication);
                }

                if (navigationJSONOptions.has(LocationWrapper.OUTSIDE_ROUTE_THRESHOLD)) {
                    Double outsideRouteThreshold = navigationJSONOptions.getDouble(LocationWrapper.OUTSIDE_ROUTE_THRESHOLD);
                    builder.outsideRouteThreshold(outsideRouteThreshold);
                }

                if (navigationJSONOptions.has(LocationWrapper.DISTANCE_TO_GOAL_THRESHOLD)) {
                    Double distanceToGoalThreshold = navigationJSONOptions.getDouble(LocationWrapper.DISTANCE_TO_GOAL_THRESHOLD);
                    builder.distanceToGoalThreshold(distanceToGoalThreshold);
                }

            } catch (Exception e) {
                //TODO: handle exception
                Log.d(TAG, "Situm >> Unable to retrieve navigation options. Applying default ones");
            }

            navigationRequest = builder.build();

            // 2.2) Build Navigation Callback
            navigationListener = new NavigationListener() {
                public void onProgress(NavigationProgress progress) {
                    Log.d(TAG, "On progress received: " + progress);
                    try {
                        JSONObject jsonProgress = LocationWrapper.navigationProgressToJsonObject(progress);
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
                    PluginResult result = new PluginResult(Status.OK, "Destination reached");
                    result.setKeepCallback(true);
                    callbackContext.sendPluginResult(result);        
                };

                public void onUserOutsideRoute() {
                    Log.d(TAG, "On user outside route: " );
                    PluginResult result = new PluginResult(Status.OK, "User outside route");
                    result.setKeepCallback(true);
                    callbackContext.sendPluginResult(result);        
                }
            };
            
            // 3)  Connect interfaces and connect callback back to js
            SitumSdk.navigationManager().requestNavigationUpdates(navigationRequest, navigationListener); // Be carefull with exceptions

            PluginResult result = new PluginResult(Status.OK, "Requested navigation successfully"); // TODO: Change this to return an object with valid information
            result.setKeepCallback(true);
            callbackContext.sendPluginResult(result);

    }

    // Initialize Navigation Component 

    public static void updateNavigationWithLocation(CordovaInterface cordova,
    CordovaWebView webView, 
    JSONArray args, 
    final CallbackContext callbackContext) {
        try {
                // 1) Check for location arguments
                JSONObject jsonLocation = args.getJSONObject(0); // What if json is not specified?

                // 2) Create a Location Object from argument
                Location actualLocation = LocationWrapper.jsonLocationObjectToLocation(jsonLocation); // Location Objet from JSON
                // Location actualLocation = PluginHelper.computedLocation;
                Log.i(TAG, "UpdateNavigation with Location: " + actualLocation);

                // 3) Connect interfaces
                SitumSdk.navigationManager().updateWithLocation(actualLocation); // TODO: Return a message (PluginResult)
            } catch (Exception e) {
                e.printStackTrace();
                callbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.getMessage()));
            }
        
    }

    public static void removeNavigationUpdates(CordovaInterface cordova,
    CordovaWebView webView, 
    JSONArray args, 
    final CallbackContext callbackContext) {
        // 
        Log.i(TAG, "Remove navigation updates");
        SitumSdk.navigationManager().removeUpdates(); // TODO: Incorporate sending a result to the exterior
    }

    public static void requestDirections(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoBuilding = args.getJSONObject(0);
            JSONObject jsonoFrom = args.getJSONObject(1);
            JSONObject jsonoTo = args.getJSONObject(2);
            Point from = LocationWrapper.pointJsonObjectToPoint(jsonoFrom, jsonoBuilding);
            Point to = LocationWrapper.pointJsonObjectToPoint(jsonoTo, jsonoBuilding);
            Boolean accessibleRoute = false;
            double startingAngle = 0.0;
            if (args.length() > 2) {
                JSONObject options = args.getJSONObject(3);
                Log.i(TAG, "request directions options" + options);

                if (options.has(LocationWrapper.ACCESSIBLE)) {
                    accessibleRoute = options.getBoolean(LocationWrapper.ACCESSIBLE);
                }
                if (options.has(LocationWrapper.STARTING_ANGLE)) {
                    startingAngle = options.getDouble(LocationWrapper.STARTING_ANGLE);
                }
            }
            DirectionsRequest directionRequest = new DirectionsRequest.Builder().from(from, Angle.fromDegrees(startingAngle)).to(to).isAccessible(accessibleRoute).build();
            SitumSdk.directionsManager().requestDirections(directionRequest, new Handler<Route>() {
                @Override
                public void onSuccess(Route route) {
                    
                    // TODO: Remove this line before going to public (Just for development purposes)
                    PluginHelper.computedRoute = route;
                    try {
                        JSONObject jsonoRoute = LocationWrapper.routeToJsonObject(route);
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
