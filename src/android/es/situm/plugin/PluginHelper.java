package es.situm.plugin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;
import es.situm.sdk.SitumSdk;
import es.situm.sdk.error.Error;
import es.situm.sdk.location.LocationListener;
import es.situm.sdk.location.LocationRequest;
import es.situm.sdk.location.LocationRequest.Builder;
import es.situm.sdk.location.LocationStatus;
import es.situm.sdk.model.cartography.Building;
import es.situm.sdk.model.location.CartesianCoordinate;
import es.situm.sdk.model.location.Location;
import es.situm.sdk.utils.Handler;
import java.util.Collection;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PluginHelper {

    private static final String TAG = "PluginHelper";

    private static LocationListener locationListener;
    private static LocationRequest locationRequest;

    public static void fetchBuildings(CordovaInterface cordova, CordovaWebView webView, JSONArray args, final CallbackContext callbackContext) {
        SitumSdk.communicationManager().fetchBuildings(new Handler<Collection<Building>>() {
            public void onSuccess(Collection<Building> buildings) {
                Log.d(PluginHelper.TAG, "onSuccess: Buildings fetched successfully.");
                JSONArray jsonaBuildings = new JSONArray();
                for (Building building : buildings) {
                    Log.i(PluginHelper.TAG, "onSuccess: " + building.getIdentifier() + " - " + building.getName());
                    JSONObject jsonoBuilding = LocationWrapper.buildingToJsonObject(building);
                    jsonaBuildings.put(jsonoBuilding);
                }
                if (buildings.isEmpty()) {
                    Log.e(PluginHelper.TAG, "onSuccess: you have no buildings. Create one in the Dashboard");
                }
                callbackContext.sendPluginResult(new PluginResult(Status.OK, jsonaBuildings));
            }

            public void onFailure(Error error) {
                Log.e(PluginHelper.TAG, "onFailure:" + error);
                callbackContext.sendPluginResult(new PluginResult(Status.ERROR, error.getMessage()));
            }
        });
    }

    public static void startPositioning(final CordovaInterface cordova, CordovaWebView webView, JSONArray args, final CallbackContext callbackContext) {
        try {
            JSONObject jsonoBuilding = args.getJSONObject(0);
            String sBuildingId = jsonoBuilding.getString(LocationWrapper.BUILDING_IDENTIFIER);
            String sBuildingName = jsonoBuilding.getString(LocationWrapper.BUILDING_NAME);
            if (locationListener == null) {
                LocationRequest locationRequest = new Builder().buildingIdentifier(sBuildingId).build();
                Log.i(TAG, "startPositioning: starting positioning in " + sBuildingName);
                locationListener = new LocationListener() {
                    public void onLocationChanged(Location location) {
                        JSONException e;
                        Log.i(PluginHelper.TAG, "onLocationChanged() called with: location = [" + location + "]");
                        CartesianCoordinate cartesianCoordinate = location.getCartesianCoordinate();
                        String locationMessage = "building: " + location.getBuildingIdentifier() + "\nfloor: " + location.getFloorIdentifier() + "\nx: " + cartesianCoordinate.getX() + "\ny: " + cartesianCoordinate.getY() + "\nyaw: " + location.getCartesianBearing() + "\naccuracy: " + location.getAccuracy();
                        JSONObject jsonObject = LocationWrapper.locationToJsonObject(location);
                        PluginResult result = new PluginResult(Status.OK, jsonObject);
                        result.setKeepCallback(true);
                        callbackContext.sendPluginResult(result);
                    }

                    public void onStatusChanged(@NonNull LocationStatus status) {
                        JSONException e;
                        Log.i(PluginHelper.TAG, "onStatusChanged() called with: status = [" + status + "]");
                        JSONObject jsonObject = LocationWrapper.locationStatusToJsonObject(status);
                        PluginResult result = new PluginResult(Status.OK, jsonObject);
                        result.setKeepCallback(true);
                        callbackContext.sendPluginResult(result);
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
                SitumSdk.locationManager().requestLocationUpdates(locationRequest, locationListener);
                return;
            }
            Log.i(TAG, "startPositioning: location listener is already started.");
        } catch (JSONException e) {
            Log.e(TAG, "Unexpected error building response", e.getCause());
        }
    }

    public static void stopPositioning(CordovaInterface cordova, CordovaWebView webView, JSONArray args, CallbackContext callbackContext) {
        if (locationListener != null) {
            SitumSdk.locationManager().removeUpdates(locationListener);
        } else {
            Log.i(TAG, "stopPositioning: location listener is not started.");
        }
    }

    private static void showLocationSettings(CordovaInterface cordova) {
        Toast.makeText(cordova.getActivity(), "You must enable location", Toast.LENGTH_LONG).show();
        cordova.getActivity().startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 0);
    }

    private static void requestLocationPermission(CordovaInterface cordova) {
        ActivityCompat.requestPermissions(cordova.getActivity(), new String[]{"android.permission.ACCESS_COARSE_LOCATION"}, 0);
    }
}
