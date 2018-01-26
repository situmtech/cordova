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
import es.situm.sdk.location.LocationRequest.Builder;
import es.situm.sdk.location.LocationStatus;
import es.situm.sdk.model.cartography.Building;
import es.situm.sdk.model.cartography.Floor;
import es.situm.sdk.model.cartography.Poi;
import es.situm.sdk.model.cartography.PoiCategory;
import es.situm.sdk.model.cartography.Point;
import es.situm.sdk.model.directions.Route;
import es.situm.sdk.model.location.CartesianCoordinate;
import es.situm.sdk.model.location.Location;
import es.situm.sdk.utils.Handler;
import es.situm.sdk.v1.SitumEvent;

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
                                            "onSuccess: " + poi.getIdentifier() + " - " + poi.getName() + "-" + poi.customFields());
                                    
                                    Log.d("Some log that should appear");
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

    public static void startPositioning(final CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoBuilding = args.getJSONObject(0);
            String sBuildingId = jsonoBuilding.getString(LocationWrapper.BUILDING_IDENTIFIER);
            String sBuildingName = jsonoBuilding.getString(LocationWrapper.BUILDING_NAME);
            if (locationListener == null) {
                LocationRequest locationRequest = new Builder().buildingIdentifier(sBuildingId).build();
                Log.i(TAG, "startPositioning: starting positioning in " + sBuildingName);
                locationListener = new LocationListener() {
                    public void onLocationChanged(Location location) {
                        try {
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
            SitumSdk.locationManager().removeUpdates(locationListener);
            locationListener = null;
        } else {
            Log.i(TAG, "stopPositioning: location listener is not started.");
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

    public static void requestDirections(CordovaInterface cordova, CordovaWebView webView, JSONArray args,
            final CallbackContext callbackContext) {
        try {
            JSONObject jsonoBuilding = args.getJSONObject(0);
            JSONObject jsonoFrom = args.getJSONObject(1);
            JSONObject jsonoTo = args.getJSONObject(2);
            Point from = LocationWrapper.pointJsonObjectToPoint(jsonoFrom, jsonoBuilding);
            Point to = LocationWrapper.pointJsonObjectToPoint(jsonoTo, jsonoBuilding);
            DirectionsRequest directionRequest = new DirectionsRequest.Builder().from(from, null).to(to).build();
            SitumSdk.directionsManager().requestDirections(directionRequest, new Handler<Route>() {
                @Override
                public void onSuccess(Route route) {
                    try {
                        JSONObject jsonoRoute = LocationWrapper.routeToJsonObject(route);
                        Log.i(TAG, "onSuccess: Route calculated successfully");
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
