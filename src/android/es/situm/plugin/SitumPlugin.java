/**
 */
package es.situm.plugin;

import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import es.situm.sdk.SitumSdk;

public class SitumPlugin extends CordovaPlugin {

  private static final String TAG = "SitumPlugin";

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
    } else if (action.equalsIgnoreCase("fetchBuildings")) {
      PluginHelper.fetchBuildings(cordova, webView, args, callbackContext);
    } else if (action.equalsIgnoreCase("startPositioning")) {
      PluginHelper.startPositioning(cordova, webView, args, callbackContext);
    } else if (action.equalsIgnoreCase("stopPositioning")) {
      PluginHelper.stopPositioning(cordova, webView, args, callbackContext);
    } else if (action.equalsIgnoreCase("fetchPoiCategories")) {
      PluginHelper.fetchPoiCategories(cordova, webView, args, callbackContext);
    }
    //else if(action.equalsIgnoreCase("requestDirections")) {
    //PluginHelper.requestDirections(cordova, webView, args, callbackContext);
    //}
    return true;
  }

}