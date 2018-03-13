#import "SitumPlugin.h"
#import "SitumLocationWrapper.h"


#import <Cordova/CDVAvailability.h>

static NSString *ResultsKey = @"results";

static NSString *StartingAngle=@"startingAngle";
static NSString *Accessible=@"accessible";

static BOOL IS_LOG_ENABLED = NO;

static NSString *DEFAULT_SITUM_LOG = @"SitumSDK >>: ";

@interface SitumPlugin() {}
        
@property (nonatomic, strong) SITRoute *computedRoute;

@end

@implementation SitumPlugin

@synthesize computedRoute = _computedRoute;

- (void)setApiKey:(CDVInvokedUrlCommand *)command {
    NSString* email = [command.arguments objectAtIndex:0];
    NSString* apiKey = [command.arguments objectAtIndex:1];
    [SITServices provideAPIKey:apiKey forEmail:email];

    if (IS_LOG_ENABLED)
    {
        /* code */
        NSArray *allPaths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
        NSString *documentsDirectory = [allPaths objectAtIndex:0];
        NSString *pathForLog = [documentsDirectory stringByAppendingPathComponent:@"logging.txt"];
        freopen([pathForLog cStringUsingEncoding:NSASCIIStringEncoding],"a+",stderr);

        NSLog([NSString stringWithFormat: @"%@ Logging ios calls", DEFAULT_SITUM_LOG]);
    }

}

- (void)setUserPass:(CDVInvokedUrlCommand *)command {
    NSString* email = [command.arguments objectAtIndex:0];
    NSString* password = [command.arguments objectAtIndex:1];
    [SITServices provideUser:email password:password];
}

- (void)setCacheMaxAge:(CDVInvokedUrlCommand *)command {
    NSNumber* cacheMaxAge = [command.arguments objectAtIndex:0]; // on iOS this value 
    [[SITCommunicationManager sharedManager] setCacheMaxAge:[cacheMaxAge integerValue]]; 
    NSString *operation = [NSString stringWithFormat:@"Setting cache max age to :%@ seconds", cacheMaxAge];

    if (IS_LOG_ENABLED)
    {
        NSLog([NSString stringWithFormat: @"%@ %@ ", DEFAULT_SITUM_LOG, operation]);
        NSLog([NSString stringWithFormat: @"%@ Cache max age is %d seconds", DEFAULT_SITUM_LOG, [[SITCommunicationManager sharedManager] cacheMaxAge]]);
        
    }
}

- (void)fetchBuildings:(CDVInvokedUrlCommand*)command
{
    if (buildingsStored == nil) {
        buildingsStored = [[NSMutableDictionary alloc] init];
    }

    NSString *operation = @"Fetching buildings request";
    if (IS_LOG_ENABLED)
    {
        NSLog([NSString stringWithFormat: @"%@ %@ ", DEFAULT_SITUM_LOG, operation]);
    }
    
    // Forcing requests to go to the network instead of cache
    NSDictionary *options = @{@"forceRequest":@YES,};
    
    [[SITCommunicationManager sharedManager] fetchBuildingsWithOptions:options success:^(NSDictionary *mapping) {
        if (IS_LOG_ENABLED)
        {
            NSLog([NSString stringWithFormat: @"%@ %@ Fetching buildings returned values", DEFAULT_SITUM_LOG, operation]);
        }
        NSArray *buildings = [mapping valueForKey:ResultsKey];
        CDVPluginResult* pluginResult = nil;
        if (buildings.count == 0) {
            if (IS_LOG_ENABLED)
            {
                NSLog([NSString stringWithFormat: @"%@ %@ No buildings were retrieved", DEFAULT_SITUM_LOG, operation]);
            }
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"There are no buildings on the account. Please go to dashboard http://dashboard.situm.es and learn more about the first step with Situm technology"];
        }
        else {
            NSMutableArray *ja = [[NSMutableArray alloc] init];
            for (SITBuilding *obj in buildings) {
                [ja addObject:[SitumLocationWrapper.shared buildingToJsonObject:obj]];
                [buildingsStored setObject:obj forKey:[NSString stringWithFormat:@"%@", obj.identifier]];
            }
            if (IS_LOG_ENABLED)
            {
                NSLog([NSString stringWithFormat: @"%@ %@ Retrieved the following buildings: %@", DEFAULT_SITUM_LOG, operation, buildings]);
            }
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArray:ja.copy];
        }
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }
                                                               failure:^(NSError *error) {
                                                                   if (IS_LOG_ENABLED)
                                                                    {
                                                                        NSLog([NSString stringWithFormat: @"%@ %@ Error retrieving buildings: %@", DEFAULT_SITUM_LOG, operation, error]);
                                                                    }
                                                                   [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description] callbackId:command.callbackId];
                                                               }];
}


- (void)fetchFloorsFromBuilding:(CDVInvokedUrlCommand*)command
{
    NSDictionary* buildingJO = (NSDictionary*)[command.arguments objectAtIndex:0];
    
    NSString *operation = @"Fetching floors request";
    if (IS_LOG_ENABLED)
    {
        NSLog([NSString stringWithFormat: @"%@ %@ with parameters: %@", DEFAULT_SITUM_LOG, operation, buildingJO]);
    }

    if (floorStored == nil) {
        floorStored = [[NSMutableDictionary alloc] init];
    }
    
    NSString *buildingId = [buildingJO valueForKey:@"identifier"];
    
    [[SITCommunicationManager sharedManager] fetchFloorsForBuilding:buildingId withOptions:nil success:^(NSDictionary *mapping) {
        if (IS_LOG_ENABLED)
        {
            NSLog([NSString stringWithFormat: @"%@ %@ responded", DEFAULT_SITUM_LOG, operation]);
        }

        NSMutableArray *ja = [[NSMutableArray alloc] init];
        NSArray *floors = [mapping objectForKey:@"results"];
        if (IS_LOG_ENABLED)
        {
            NSLog([NSString stringWithFormat: @"%@ %@ results: %@", DEFAULT_SITUM_LOG, operation, floors]);
        }
        for (SITFloor *obj in floors) {
            NSDictionary *floorJson = [SitumLocationWrapper.shared floorToJsonObject:obj];
            if (IS_LOG_ENABLED)
            {
                NSLog([NSString stringWithFormat: @"%@ %@ parsed floor: %@", DEFAULT_SITUM_LOG, operation, floorJson]);
            }
            [ja addObject:floorJson];
            if (IS_LOG_ENABLED)
            {
                NSLog([NSString stringWithFormat: @"%@ %@ json array has : %@", DEFAULT_SITUM_LOG, operation, ja]);
            }
            [floorStored setObject:obj forKey:[NSString stringWithFormat:@"%@", obj.identifier]];
            if (IS_LOG_ENABLED)
            {
                NSLog([NSString stringWithFormat: @"%@ %@ added: %@ to dictionary results", DEFAULT_SITUM_LOG, operation, obj]);
            }
        }
        CDVPluginResult* pluginResult = nil;
        if (floors.count == 0) {
            if (IS_LOG_ENABLED)
            {
                NSLog([NSString stringWithFormat: @"%@ %@ no floors on building: %@", DEFAULT_SITUM_LOG, operation, buildingJO]);
            }
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"The selected building does not have floors. Correct that on http://dashboard.situm.es"];
        } else {
            if (IS_LOG_ENABLED)
            {
                NSLog([NSString stringWithFormat: @"%@ %@ retrieved floors: %@ on building: %@: %@", DEFAULT_SITUM_LOG, operation, floors, buildingJO]);
            }
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArray:ja.copy];
        }
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    } failure:^(NSError *error) {
        if (IS_LOG_ENABLED)
        {
            NSLog([NSString stringWithFormat: @"%@ %@ error : %@ retrieving floors on building: %@", DEFAULT_SITUM_LOG, operation, error, buildingJO]);
        }
        [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description] callbackId:command.callbackId];
    }];
}


- (void)fetchIndoorPOIsFromBuilding:(CDVInvokedUrlCommand*)command
{
    NSDictionary* buildingJO = (NSDictionary*)[command.arguments objectAtIndex:0];
    
    if (poisStored == nil) {
        poisStored = [[NSMutableDictionary alloc] init];
    }
    
    [[SITCommunicationManager sharedManager] fetchPoisOfBuilding:[buildingJO valueForKey:@"identifier"]  withOptions:nil success:^(NSDictionary *mapping) {
        NSArray *list = [mapping objectForKey:@"results"];
        NSMutableArray *ja = [[NSMutableArray alloc] init];
        for (SITPOI *obj in list) {
            [ja addObject:[SitumLocationWrapper.shared poiToJsonObject:obj]];
            [poisStored setObject:obj forKey:obj.name];
        }
        CDVPluginResult* pluginResult = nil;
        if (list.count == 0) {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"You have no poi. Create one in the Dashboard"];
        } else {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArray:ja.copy];
        }
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    } failure:^(NSError *error) {
        [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description] callbackId:command.callbackId];
    }];
}

- (void)fetchOutdoorPOIsFromBuilding:(CDVInvokedUrlCommand*)command
{
    NSDictionary* buildingJO = (NSDictionary*)[command.arguments objectAtIndex:0];
    
    if (poisStored == nil) {
        poisStored = [[NSMutableDictionary alloc] init];
    }
    
    [[SITCommunicationManager sharedManager] fetchOutdoorPoisOfBuilding:[buildingJO valueForKey:@"identifier"]  withOptions:nil success:^(NSDictionary *mapping) {
        NSArray *list = [mapping objectForKey:@"results"];
        NSMutableArray *ja = [[NSMutableArray alloc] init];
        for (SITPOI *obj in list) {
            [ja addObject:[SitumLocationWrapper.shared poiToJsonObject:obj]];
            [poisStored setObject:obj forKey:obj.name];
        }
        CDVPluginResult* pluginResult = nil;
        if (list.count == 0) {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"You have no poi. Create one in the Dashboard"];
        } else {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArray:ja.copy];
        }
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    } failure:^(NSError *error) {
        [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description] callbackId:command.callbackId];
    }];
}

- (void)fetchEventsFromBuilding:(CDVInvokedUrlCommand*)command
{
    NSDictionary* buildingJO = (NSDictionary*)[command.arguments objectAtIndex:0];
    
    if (eventStored == nil) {
        eventStored = [[NSMutableDictionary alloc] init];
    }
    
    [[SITCommunicationManager sharedManager] fetchEventsFromIndoorBuilding:[buildingJO valueForKey:@"identifier"] withOptions:nil withCompletion:^(NSArray *events, NSError *error) {
        if (!error) {
            NSMutableArray *ja = [[NSMutableArray alloc] init];
            for (SITEvent *obj in events) {
                [ja addObject:[SitumLocationWrapper.shared eventToJsonObject:obj]];
                [eventStored setObject:obj forKey:[NSString stringWithFormat:@"%@", obj.name]];
            }
            CDVPluginResult* pluginResult = nil;
            if (events.count == 0) {
                pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"You have no events. Create one in the Dashboard"];
            } else {
                pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArray:ja.copy];
            }
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        } else {
            [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description] callbackId:command.callbackId];
        }
    }];
}

- (void)fetchPoiCategories:(CDVInvokedUrlCommand *)command
{
    //NSDictionary* categoryJO = (NSDictionary*)[command.arguments objectAtIndex:0];
    
    if (categoryStored == nil) {
        categoryStored = [[NSMutableDictionary alloc] init];
    }
    
    [[SITCommunicationManager sharedManager] fetchCategoriesWithOptions:nil withCompletion:^(NSArray *categories, NSError *error) {
        if (!error) {
            NSMutableArray *ja = [[NSMutableArray alloc] init];
            for (SITPOICategory *obj in categories) {
                [ja addObject:[SitumLocationWrapper.shared poiCategoryToJsonObject:obj]];
                [categoryStored setObject:obj forKey:[NSString stringWithFormat:@"%@", obj.name]];
            }
            CDVPluginResult* pluginResult = nil;
            if (categories.count == 0) {
                pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"You have no categories. Create one in the Dashboard"];
            } else {
                pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArray:ja.copy];
            }
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        } else {
            [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description] callbackId:command.callbackId];
        }
    }];
}

- (void)fetchPoiCategoryIconNormal:(CDVInvokedUrlCommand *)command
{
    NSDictionary* buildingJO = (NSDictionary*)[command.arguments objectAtIndex:0];
    
    if (categoryStored == nil) {
        categoryStored = [[NSMutableDictionary alloc] init];
    }
    
    [[SITCommunicationManager sharedManager] fetchCategoriesWithOptions:[buildingJO valueForKey:@"identifier"] withCompletion:^(NSArray *categories, NSError *error) {
        if (!error) {
            NSMutableArray *ja = [[NSMutableArray alloc] init];
            for (SITPOICategory *obj in categories) {
                [ja addObject:[SitumLocationWrapper.shared poiCategoryToJsonObject:obj]];
                [categoryStored setObject:obj forKey:[NSString stringWithFormat:@"%@", obj.name]];
            }
            CDVPluginResult* pluginResult = nil;
            if (categories.count == 0) {
                pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"You have no categories. Create one in the Dashboard"];
            } else {
                pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArray:ja.copy];
            }
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        } else {
            [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description] callbackId:command.callbackId];
        }
    }];
}

- (void)fetchPoiCategoryIconSelected:(CDVInvokedUrlCommand *)command
{
    NSDictionary* buildingJO = (NSDictionary*)[command.arguments objectAtIndex:0];
    
    if (categoryStored == nil) {
        categoryStored = [[NSMutableDictionary alloc] init];
    }
    
    [[SITCommunicationManager sharedManager] fetchCategoriesWithOptions:[buildingJO valueForKey:@"identifier"] withCompletion:^(NSArray *categories, NSError *error) {
        if (!error) {
            NSMutableArray *ja = [[NSMutableArray alloc] init];
            for (SITPOICategory *obj in categories) {
                [ja addObject:[SitumLocationWrapper.shared poiCategoryToJsonObject:obj]];
                [categoryStored setObject:obj forKey:[NSString stringWithFormat:@"%@", obj.name]];
            }
            CDVPluginResult* pluginResult = nil;
            if (categories.count == 0) {
                pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"You have no categories. Create one in the Dashboard"];
            } else {
                pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArray:ja.copy];
            }
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        } else {
            [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description] callbackId:command.callbackId];
        }
    }];
}

- (void)fetchMapFromFloor:(CDVInvokedUrlCommand *)command
{
    NSDictionary* floorJO = (NSDictionary*)[command.arguments objectAtIndex:0];
    
    if (floorStored == nil) {
        floorStored = [[NSMutableDictionary alloc] init];
    }
    SITFloor* floor = [SitumLocationWrapper.shared jsonObjectToFloor:floorJO];
    
    [[SITCommunicationManager sharedManager] fetchMapFromFloor: floor withCompletion:^(NSData *imageData) {
        NSMutableDictionary *jaMap = [[NSMutableDictionary alloc] init];
        NSString *imageBase64Encoded = [imageData base64EncodedStringWithOptions:0];
        [jaMap setObject:[NSString stringWithFormat:@"%@", imageBase64Encoded] forKey:@"data"];
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:jaMap];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}


- (void)startPositioning:(CDVInvokedUrlCommand *)command {
    NSDictionary* buildingJO = (NSDictionary*)[command.arguments objectAtIndex:0];
    locationCallbackId = command.callbackId;
    selectedBuildingJO = buildingJO;
    
    SITLocationRequest *locationRequest = [[SITLocationRequest alloc] initWithPriority:kSITHighAccuracy provider:kSITHybridProvider updateInterval:2 buildingID:[buildingJO valueForKey:@"identifier"] operationQueue:[NSOperationQueue mainQueue] options:nil];
    [[SITLocationManager sharedInstance] requestLocationUpdates:locationRequest];
    [[SITLocationManager sharedInstance] setDelegate:self];
}

- (void)stopPositioning:(CDVInvokedUrlCommand *)command {
    locationCallbackId = command.callbackId;
    [[SITLocationManager sharedInstance] removeUpdates];
}


- (void)requestDirections:(CDVInvokedUrlCommand*)command
{
    
    routeCallbackId = command.callbackId;
    
    NSDictionary* building = (NSDictionary*)[command.arguments objectAtIndex:0]; //not used
    NSDictionary* fromLocation = (NSDictionary*)[command.arguments objectAtIndex:1];
    NSDictionary* toPOI = (NSDictionary*)[command.arguments objectAtIndex:2];
    NSDictionary* options = (NSDictionary*)[command.arguments objectAtIndex:3];
    
    if (routesStored == nil) {
        routesStored = [[NSMutableDictionary alloc] init];
    }
    
    
    SITLocation *location = [SitumLocationWrapper.shared locationJsonObjectToLocation:fromLocation];
    SITPOI *poi = (SITPOI*)[poisStored objectForKey:@"name"];
    SITPoint *endPoint;
    if (poi) {
        endPoint = poi.position;
    } else {
        endPoint = [SitumLocationWrapper.shared pointJsonObjectToPoint:[toPOI objectForKey:@"position"]];
    }

    /*
    // Not neccessary
    BOOL accessible = NO;
    if ([options valueForKey:Accessible] != nil) {
        accessible = [[options valueForKey:Accessible] boolValue];
    }

    float startingAngle = 0.0;
    if ([options valueForKey:StartingAngle] != nil) {
        startingAngle = [[options valueForKey:StartingAngle] floatValue];
    }*/
    
    SITDirectionsRequest *directionsRequest = [[SITDirectionsRequest alloc] initWithRequestID:0 location:location destination:endPoint options:options];
    [[SITDirectionsManager sharedInstance] setDelegate:self];
    [[SITDirectionsManager sharedInstance] requestDirections:directionsRequest];
}

- (void)requestNavigationUpdates:(CDVInvokedUrlCommand*)command
{
    navigationProgressCallbackId = command.callbackId;
    
    // Insert here configuration options
    /*if (command.arguments.length > 0) {
        // Processing configuration parameters
        NSDictionary *options = (NSDictionary*)[command.arguments objectAtIndex:0];
 
    }*/
    // SITRoute *routeObj = (SITRoute*)[routesStored objectForKey:[route valueForKey:@"timeStamp"]];
    SITRoute *routeObj = self.computedRoute;
    if (routeObj) {
        SITNavigationRequest *navigationRequest = [[SITNavigationRequest alloc] initWithRoute:routeObj];

        // Configure distanceToGoalThreshold
        // Configure outsideRouteThreshold

        [[SITNavigationManager sharedManager]  setDelegate:self]; // Configure delegation first
        [[SITNavigationManager sharedManager] requestNavigationUpdates:navigationRequest];
    }
}

- (void)updateNavigationWithLocation:(CDVInvokedUrlCommand *)command {
    
    SITLocation *location = [SitumLocationWrapper.shared locationJsonObjectToLocation:(NSDictionary*)[command.arguments objectAtIndex:0]];
    
    [[SITNavigationManager sharedManager] updateWithLocation:location];
}

- (void) removeNavigationUpdates {
    [[SITNavigationManager sharedManager] removeUpdates];
}

- (void) invalidateCache:(CDVInvokedUrlCommand *)command {
    NSMutableDictionary *obj = [[NSMutableDictionary alloc] init];
    [[SITCommunicationManager sharedManager] clearCache];
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:obj.copy];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:locationCallbackId];
}


// SITLocationDelegate methods

- (void)locationManager:(nonnull id<SITLocationInterface>)locationManager
      didUpdateLocation:(nonnull SITLocation *)location {
    if (location) {
        // [self updateWithLocation:location];
        NSDictionary *locationJO = [SitumLocationWrapper.shared locationToJsonObject:location];
        // NSMutableDictionary *locationChanged = [[NSMutableDictionary alloc] init];
        // [locationChanged setValue:@"locationChanged" forKey:@"type"];
        // [locationChanged setValue:locationJO.copy forKey:@"value"];
        
        CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:locationJO.copy];
        pluginResult.keepCallback = [NSNumber numberWithBool:true];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:locationCallbackId];
    }
}

- (void)locationManager:(nonnull id<SITLocationInterface>)locationManager
       didFailWithError:(nonnull NSError *)error {
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
    pluginResult.keepCallback = [NSNumber numberWithBool:true];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:locationCallbackId];
}

- (void)locationManager:(nonnull id<SITLocationInterface>)locationManager
         didUpdateState:(SITLocationState)state {
    NSMutableDictionary *locationChanged = [[SitumLocationWrapper.shared locationStateToString:state] mutableCopy];
    
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:locationChanged.copy];
    pluginResult.keepCallback = [NSNumber numberWithBool:true];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:locationCallbackId];
}


// SITDirectionsDelegate

- (void)directionsManager:(id<SITDirectionsInterface>)manager
 didFailProcessingRequest:(SITDirectionsRequest *)request
                withError:(NSError *)error {


    self.computedRoute = nil; // if something fails then the previous computedRoute is clean

    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:routeCallbackId];
}

- (void)directionsManager:(id<SITDirectionsInterface>)manager
        didProcessRequest:(SITDirectionsRequest *)request
             withResponse:(SITRoute *)route {
    NSString * timestamp = [NSString stringWithFormat:@"%f",[[NSDate date] timeIntervalSince1970] * 1000];
    
    NSMutableDictionary *routeJO = [[SitumLocationWrapper.shared routeToJsonObject:route] mutableCopy];
    //[routeJO setValue:timestamp forKey:@"timeStamp"];
    [routesStored setObject:route forKey:timestamp];

    self.computedRoute = route; // We store the computed route in order to insert it into the navigation component if neccessary
    
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:routeJO.copy];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:routeCallbackId];
}

// SITNavigationDelegate


- (void)navigationManager:(id<SITNavigationInterface>)navigationManager
         didFailWithError:(NSError *)error {
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
    pluginResult.keepCallback = [NSNumber numberWithBool:true];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:navigationProgressCallbackId];
}

- (void)navigationManager:(id<SITNavigationInterface>)navigationManager
        didUpdateProgress:(SITNavigationProgress *)progress
                  onRoute:(SITRoute *)route {
    NSMutableDictionary *navigationJO = [NSMutableDictionary dictionaryWithDictionary:[SitumLocationWrapper.shared navigationProgressToJsonObject:progress]];
    [navigationJO setValue:@"progress" forKey:@"type"];
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:navigationJO.copy];
    pluginResult.keepCallback = [NSNumber numberWithBool:true];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:navigationProgressCallbackId];
}

- (void)navigationManager:(id<SITNavigationInterface>)navigationManager
destinationReachedOnRoute:(SITRoute *)route {
    NSMutableDictionary *navigationJO = [[NSMutableDictionary alloc] init];
    [navigationJO setValue:@"destinationReached" forKey:@"type"];
    [navigationJO setValue:@"User outside route" forKey:@"message"];
    
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:navigationJO.copy];
    pluginResult.keepCallback = [NSNumber numberWithBool:true];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:navigationProgressCallbackId];
}


- (void)navigationManager:(id<SITNavigationInterface>)navigationManager
         userOutsideRoute:(SITRoute *)route {
    NSMutableDictionary *navigationJO = [[NSMutableDictionary alloc] init];
    [navigationJO setValue:@"userOutsideRoute" forKey:@"type"];
    [navigationJO setValue:@"User outside route" forKey:@"message"];

    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:navigationJO.copy];
    pluginResult.keepCallback = [NSNumber numberWithBool:true];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:navigationProgressCallbackId];
}



@end
