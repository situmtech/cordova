/**
 */
package es.situm.plugin;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import es.situm.sdk.SitumSdk;
import es.situm.sdk.communication.CommunicationManager;
import es.situm.sdk.navigation.NavigationManager;

public class SitumPlugin extends CordovaPlugin {

  private static final String TAG = "SitumPlugin";

  private static volatile PluginHelper pluginInstance;

  private static PluginHelper getPluginInstance() {
    if (pluginInstance == null) { //Check for the first time
      synchronized (CommunicationManager.class) {   //Check for the second time.
        //if there is no instance available... create new one
        if (pluginInstance == null) pluginInstance = new PluginHelper();
      }
    }
    return pluginInstance;
  }

  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);
    Log.d(TAG, "Initializing Situm Plugin");
    SitumSdk.init(cordova.getActivity());
  }

  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    Log.d(TAG, "execute: " + action);
    if (action.equalsIgnoreCase("setApiKey")) {
      String email = args.getString(0);
      String apiKey = args.getString(1);
      es.situm.sdk.SitumSdk.configuration().setApiKey(email, apiKey);
    } else if (action.equalsIgnoreCase("setUserPass")) {
      String email = args.getString(0);
      String password = args.getString(1);
      es.situm.sdk.SitumSdk.configuration().setUserPass(email, password);
    } else if (action.equalsIgnoreCase("setCacheMaxAge")) {    
      Integer cacheAge = args.getInt(0);
      Log.d(TAG,"Setting cache max age to " + cacheAge + " seconds");
      es.situm.sdk.SitumSdk.configuration().setCacheMaxAge(cacheAge, TimeUnit.SECONDS);
    } else if (action.equalsIgnoreCase("fetchBuildings")) {
      getPluginInstance().fetchBuildings(cordova, webView, args, callbackContext);
    } else if (action.equalsIgnoreCase("startPositioning")) {
      getPluginInstance().startPositioning(cordova, webView, args, callbackContext);
    } else if (action.equalsIgnoreCase("stopPositioning")) {
      getPluginInstance().stopPositioning(cordova, webView, args, callbackContext);
    } else if (action.equalsIgnoreCase("fetchPoiCategories")) {
      getPluginInstance().fetchPoiCategories(cordova, webView, args, callbackContext);
    } else if (action.equalsIgnoreCase("fetchFloorsFromBuilding")) {
      getPluginInstance().fetchFloorsFromBuilding(cordova, webView, args, callbackContext);
    } else if (action.equalsIgnoreCase("fetchIndoorPOIsFromBuilding")) {
      getPluginInstance().fetchIndoorPOIsFromBuilding(cordova, webView, args, callbackContext);
    } else if (action.equalsIgnoreCase("fetchOutdoorPOIsFromBuilding")) {
      getPluginInstance().fetchOutdoorPOIsFromBuilding(cordova, webView, args, callbackContext);
    } else if (action.equalsIgnoreCase("fetchEventsFromBuilding")) {
      getPluginInstance().fetchEventsFromBuilding(cordova, webView, args, callbackContext);
    } else if (action.equalsIgnoreCase("fetchMapFromFloor")) {
      getPluginInstance().fetchMapFromFloor(cordova, webView, args, callbackContext);
    } else if (action.equalsIgnoreCase("fetchPoiCategoryIconNormal")) {
      getPluginInstance().fetchPoiCategoryIconNormal(cordova, webView, args, callbackContext);
    } else if (action.equalsIgnoreCase("fetchPoiCategoryIconSelected")) {
      getPluginInstance().fetchPoiCategoryIconSelected(cordova, webView, args, callbackContext);
    } else if (action.equalsIgnoreCase("invalidateCache")) {
      getPluginInstance().invalidateCache(callbackContext);
    } else if (action.equalsIgnoreCase("requestDirections")) {
      getPluginInstance().requestDirections(cordova, webView, args, callbackContext);
    } else  if(action.equalsIgnoreCase("requestNavigationUpdates")) {
      getPluginInstance().requestNavigationUpdates(cordova, webView, args, callbackContext);
    } else  if(action.equalsIgnoreCase("updateNavigationWithLocation")) {
      getPluginInstance().updateNavigationWithLocation(cordova, webView, args, callbackContext);
    } else  if(action.equalsIgnoreCase("removeNavigationUpdates")) {
      getPluginInstance().removeNavigationUpdates(cordova, webView, args, callbackContext);
    } else {
      getPluginInstance().returnDefaultResponse(callbackContext);
    }
    return true;
  }

}