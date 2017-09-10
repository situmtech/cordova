#import <Cordova/CDVPlugin.h>
#import <SitumSDK/SitumSDK.h>


@interface SitumPlugin : CDVPlugin<SITDirectionsDelegate, SITLocationDelegate, SITNavigationDelegate> {
    
    NSMutableDictionary *buildingsStored;
    NSMutableDictionary *floorStored;
    NSMutableDictionary *eventStored;
    NSMutableDictionary *categoryStored;
    NSMutableDictionary *poisStored;
    NSMutableDictionary *routesStored;
    
    NSDictionary *selectedBuildingJO;
    
    NSString *locationCallbackId, *routeCallbackId, *navigationProgressCallbackId;
}

// The hooks for our plugin commands
- (void)setApiKey:(CDVInvokedUrlCommand *)command;
- (void)setUserPass:(CDVInvokedUrlCommand *)command;
- (void)startPositioning:(CDVInvokedUrlCommand *)command;
- (void)stopPositioning:(CDVInvokedUrlCommand *)command;
- (void)fetchBuildings:(CDVInvokedUrlCommand *)command;
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


@end
