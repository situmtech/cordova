#import "SitumPlugin.h"
#import "SitumLocationWrapper.h"


#import <Cordova/CDVAvailability.h>

@implementation SitumPlugin

- (void)setApiKey:(CDVInvokedUrlCommand *)command {
  NSString* email = [command.arguments objectAtIndex:0];
  NSString* apiKey = [command.arguments objectAtIndex:1];
  [SITServices provideAPIKey:apiKey forEmail:email];
  
}

- (void)setUserPass:(CDVInvokedUrlCommand *)command {
    NSString* email = [command.arguments objectAtIndex:0];
    NSString* password = [command.arguments objectAtIndex:1];
    [SITServices provideUser:email password:password];
}

- (void)fetchBuildings:(CDVInvokedUrlCommand*)command
{
    if (buildingsStored == nil) {
        buildingsStored = [[NSMutableDictionary alloc] init];
    }
    
    [[SITCommunicationManager sharedManager]
     fetchIndoorBuildingsWithOptions:nil
     withCompletion:^(NSArray *indoorBuildings, NSError *error) {
         if (!error) {
             NSMutableArray *ja = [[NSMutableArray alloc] init];
             for (SITIndoorBuilding *obj in indoorBuildings) {
                 [ja addObject:[SitumLocationWrapper.shared buildingIndoorToJsonObject:obj]];
                 [buildingsStored setObject:obj forKey:[NSString stringWithFormat:@"%@", obj.identifier]];
             }
             CDVPluginResult* pluginResult = nil;
             if (indoorBuildings.count == 0) {
                 pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"You have no buildings. Create one in the Dashboard"];
             } else {
                 pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArray:ja.copy];
             }
             [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
             
         } else {
             [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description] callbackId:command.callbackId];
         }
     }];
}


- (void)fetchFloorsFromBuilding:(CDVInvokedUrlCommand*)command
{
    NSDictionary* buildingJO = (NSDictionary*)[command.arguments objectAtIndex:0];
    
    if (floorStored == nil) {
        floorStored = [[NSMutableDictionary alloc] init];
    }
    
    NSString *buildingId = [buildingJO valueForKey:@"identifier"];
    
    //SITIndoorBuilding *selectedBuilding = (SITIndoorBuilding *) [buildingsStored objectForKey:buildingId];
    [[SITCommunicationManager sharedManager] fetchFloorsForBuilding:buildingId withOptions:nil success:^(NSDictionary *mapping) {
        NSMutableArray *ja = [[NSMutableArray alloc] init];
        NSArray *indoorLevels = [mapping objectForKey:@"results"];
        for (SITIndoorLevel *obj in indoorLevels) {
            [ja addObject:[SitumLocationWrapper.shared floorToJsonObject:obj]];
            [floorStored setObject:obj forKey:[NSString stringWithFormat:@"%@", obj.level]];
        }
        CDVPluginResult* pluginResult = nil;
        if (indoorLevels.count == 0) {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"You have no floors. Create one in the Dashboard"];
        } else {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArray:ja.copy];
        }
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    } failure:^(NSError *error) {
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
    NSDictionary* categoryJO = (NSDictionary*)[command.arguments objectAtIndex:0];
    
    if (categoryStored == nil) {
        categoryStored = [[NSMutableDictionary alloc] init];
    }
    
    [[SITCommunicationManager sharedManager] fetchCategoriesWithOptions:[categoryJO valueForKey:@"identifier"] withCompletion:^(NSArray *categories, NSError *error) {
        if (!error) {
            NSMutableArray *ja = [[NSMutableArray alloc] init];
            for (SITPOICategory *obj in categories) {
                [ja addObject:[SitumLocationWrapper.shared categoryToJsonObject:obj]];
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
                [ja addObject:[SitumLocationWrapper.shared categoryToJsonObject:obj]];
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
                [ja addObject:[SitumLocationWrapper.shared categoryToJsonObject:obj]];
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
//    NSDictionary* floorJO = (NSDictionary*)[command.arguments objectAtIndex:0];
//    
//    if (floorStored == nil) {
//        floorStored = [[NSMutableDictionary alloc] init];
//    }
//    
//    [[SITCommunicationManager sharedManager] fetchMapFromFloor:[floorStored valueForKey:@"identifier"]  withOptions:nil success:^(NSDictionary *mapping) {
//        NSArray *list = [mapping objectForKey:@"results"];
//        NSMutableArray *ja = [[NSMutableArray alloc] init];
//        for (SITPOI *obj in list) {
//            [ja addObject:[SitumLocationWrapper.shared poiToJsonObject:obj]];
//            [poisStored setObject:obj forKey:obj.name];
//        }
//        CDVPluginResult* pluginResult = nil;
//        if (list.count == 0) {
//            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"You have no poi. Create one in the Dashboard"];
//        } else {
//            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArray:ja.copy];
//        }
//        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
//    } failure:^(NSError *error) {
//        [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description] callbackId:command.callbackId];
//    }];
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
    
    NSDictionary* building = (NSDictionary*)[command.arguments objectAtIndex:0];
    NSDictionary* fromLocation = (NSDictionary*)[command.arguments objectAtIndex:1];
    NSDictionary* toPOI = (NSDictionary*)[command.arguments objectAtIndex:2];
    
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
    
    SITDirectionsRequest *directionsRequest = [[SITDirectionsRequest alloc] initWithRequestID:0 location:location destination:endPoint options:nil];
    [[SITDirectionsManager sharedInstance] requestDirections:directionsRequest];
    [[SITDirectionsManager sharedInstance] setDelegate:self];
}

- (void)startNavigation:(CDVInvokedUrlCommand*)command
{
    navigationProgressCallbackId = command.callbackId;
    
    NSDictionary* route = (NSDictionary*)[command.arguments objectAtIndex:0];
    
    SITRoute *routeObj = (SITRoute*)[routesStored objectForKey:[route valueForKey:@"timeStamp"]];
    if (routeObj) {
        SITNavigationRequest *navigationRequest = [[SITNavigationRequest alloc] initWithRoute:routeObj];
        
        [[SITNavigationManager sharedManager] requestNavigationUpdates:navigationRequest];
        [[SITNavigationManager sharedManager]  setDelegate:self];
    }
}

- (void) updateWithLocation:(SITLocation *)location {
    [[SITNavigationManager sharedManager] updateWithLocation:location];
}

- (void) removeUpdates {
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
        [self updateWithLocation:location];
        NSDictionary *locationJO = [SitumLocationWrapper.shared locationToJsonObject:location];
        NSMutableDictionary *locationChanged = [[NSMutableDictionary alloc] init];
        [locationChanged setValue:@"locationChanged" forKey:@"type"];
        [locationChanged setValue:locationJO.copy forKey:@"value"];
        
        CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:locationChanged.copy];
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
    NSMutableDictionary *locationChanged = [[NSMutableDictionary alloc] init];
    [locationChanged setValue:@"statusChanged" forKey:@"type"];
    [locationChanged setValue:[SitumLocationWrapper.shared locationStateToString:state] forKey:@"value"];
    
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:locationChanged.copy];
    pluginResult.keepCallback = [NSNumber numberWithBool:true];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:locationCallbackId];
}


// SITDirectionsDelegate

- (void)directionsManager:(id<SITDirectionsInterface>)manager
 didFailProcessingRequest:(SITDirectionsRequest *)request
                withError:(NSError *)error {
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:routeCallbackId];
}

- (void)directionsManager:(id<SITDirectionsInterface>)manager
        didProcessRequest:(SITDirectionsRequest *)request
             withResponse:(SITRoute *)route {
    NSString * timestamp = [NSString stringWithFormat:@"%f",[[NSDate date] timeIntervalSince1970] * 1000];
    
    NSMutableDictionary *routeJO = [[SitumLocationWrapper.shared routeToJsonObject:route] mutableCopy];
    [routeJO setValue:timestamp forKey:@"timeStamp"];
    [routesStored setObject:route forKey:timestamp];
    
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
    NSMutableDictionary *navigationJO = [[NSMutableDictionary alloc] init];
    [navigationJO setValue:@"progress" forKey:@"type"];
    [navigationJO setValue:[SitumLocationWrapper.shared navigationProgressToJsonObject:progress] forKey:@"value"];
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:navigationJO.copy];
    pluginResult.keepCallback = [NSNumber numberWithBool:true];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:navigationProgressCallbackId];
}

- (void)navigationManager:(id<SITNavigationInterface>)navigationManager
destinationReachedOnRoute:(SITRoute *)route {
    [self removeUpdates];
    
    NSMutableDictionary *navigationJO = [[NSMutableDictionary alloc] init];
    [navigationJO setValue:@"destinationReached" forKey:@"type"];
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:navigationJO.copy];
    pluginResult.keepCallback = [NSNumber numberWithBool:true];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:navigationProgressCallbackId];
}


- (void)navigationManager:(id<SITNavigationInterface>)navigationManager
         userOutsideRoute:(SITRoute *)route {
    NSMutableDictionary *navigationJO = [[NSMutableDictionary alloc] init];
    [navigationJO setValue:@"userOutsideRoute" forKey:@"type"];
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:navigationJO.copy];
    pluginResult.keepCallback = [NSNumber numberWithBool:true];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:navigationProgressCallbackId];
}



@end
