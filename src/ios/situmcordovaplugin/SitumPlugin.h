#import <Cordova/CDVPlugin.h>
#import <SitumSDK/SitumSDK.h>


@interface SitumPlugin : CDVPlugin<SITDirectionsDelegate, SITLocationDelegate, SITNavigationDelegate, SITRealTimeDelegate, SITGeofencesDelegate> {
    
    NSMutableDictionary *buildingsStored;
    NSMutableDictionary *floorStored;
    NSMutableDictionary *eventStored;
    NSMutableDictionary *categoryStored;
    NSMutableDictionary<NSString *, SITPOI*> *poisStored;
    NSMutableDictionary *routesStored;
    
    NSString *locationCallbackId, *routeCallbackId, *navigationProgressCallbackId, *realtimeCallbackId, *enterGeofencesCallbackId, *exitGeofencesCallbackId;
}

// The hooks for our plugin commands
- (void)setApiKey:(CDVInvokedUrlCommand *)command;
- (void)setUserPass:(CDVInvokedUrlCommand *)command;
- (void)getDeviceId:(CDVInvokedUrlCommand *)command;
- (void)startPositioning:(CDVInvokedUrlCommand *)command;
- (void)setUseRemoteConfig:(CDVInvokedUrlCommand *)command;
- (void)stopPositioning:(CDVInvokedUrlCommand *)command;
- (void)onEnterGeofences:(CDVInvokedUrlCommand *)command;
- (void)onExitGeofences:(CDVInvokedUrlCommand *)command;
- (void)fetchBuildings:(CDVInvokedUrlCommand *)command;
- (void)fetchBuildingInfo:(CDVInvokedUrlCommand *)command;
- (void)fetchGeofencesFromBuilding:(CDVInvokedUrlCommand *)command;
- (void)fetchFloorsFromBuilding:(CDVInvokedUrlCommand *)command;
- (void)fetchIndoorPOIsFromBuilding:(CDVInvokedUrlCommand *)command;
- (void)fetchOutdoorPOIsFromBuilding:(CDVInvokedUrlCommand *)command;
- (void)fetchEventsFromBuilding:(CDVInvokedUrlCommand *)command;
- (void)fetchPoiCategories:(CDVInvokedUrlCommand *)command;
- (void)fetchMapFromFloor:(CDVInvokedUrlCommand *)command;
- (void)fetchPoiCategoryIconNormal:(CDVInvokedUrlCommand *)command;
- (void)fetchPoiCategoryIconSelected:(CDVInvokedUrlCommand *)command;
- (void)invalidateCache:(CDVInvokedUrlCommand *)command;
- (void)requestDirections:(CDVInvokedUrlCommand *)command;
// Realtime
- (void)requestRealTimeUpdates:(CDVInvokedUrlCommand *)command;
- (void)removeRealTimeUpdates:(CDVInvokedUrlCommand *)command;
@end
