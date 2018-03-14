#import "SitumLocationWrapper.h"

NSString *DATEFORMAT = @"yyyy-MM-dd'T'HH:mm:ss.SSSZZZZ";

NSString* emptyStrCheck(NSString *str) {
    if (!str || str == nil) {
        return @"";
    }
    return str;
}

NSString* orientationTypeToString(kSITIndicationOrientation orientation) {
    NSString *type = @"";
    switch (orientation) {
        case kSITInvalidOrientation:
            type = @"Invalid";
            break;
        case kSITStraight:
            type = @"Straight";
            break;
        case kSITVeerRight:
            type = @"VeerRight";
            break;
        case kSITRight:
            type = @"Right";
            break;
        case kSITSharpRight:
            type = @"SharpRight";
            break;
        case kSITVeerLeft:
            type = @"VeerLeft";
            break;
        case kSITLeft:
            type = @"Left";
            break;
        case kSITSharpLeft:
            type = @"SharpLeft";
            break;
        case kSITBackward:
            type = @"Backward";
            break;
            
        default:
            break;
    }
    return type;
}

kSITIndicationOrientation stringToOrientationType(NSString *orientation) {
    kSITIndicationOrientation type = kSITInvalidOrientation;
    if ([orientation isEqualToString:@"Invalid"]) {
        type = kSITInvalidOrientation;
    } else if ([orientation isEqualToString:@"Straight"]) {
        type = kSITStraight;
    } else if ([orientation isEqualToString:@"VeerRight"]) {
        type = kSITVeerRight;
    } else if ([orientation isEqualToString:@"Right"]) {
        type = kSITRight;
    } else if ([orientation isEqualToString:@"SharpRight"]) {
        type = kSITSharpRight;
    } else if ([orientation isEqualToString:@"VeerLeft"]) {
        type = kSITVeerLeft;
    } else if ([orientation isEqualToString:@"Left"]) {
        type = kSITLeft;
    } else if ([orientation isEqualToString:@"SharpLeft"]) {
        type = kSITSharpLeft;
    } else if ([orientation isEqualToString:@"Backward"]) {
        type = kSITBackward;
    }
    return type;
}

// indicationType

NSString* indicationTypeToString(kSITIndicationActions action) {
    NSString *type = @"";
    switch (action) {
        case kSITInvalidAction:
            type = @"Invalid";
            break;
        case kSITTurn:
            type = @"Turn";
            break;
        case kSITGoAhead:
            type = @"GoAhead";
            break;
        case kSITChangeFloor:
            type = @"ChangeFloor";
            break;
        case kSITEnd:
            type = @"End";
            break;
            
            
        default:
            break;
    }
    return type;
}

kSITIndicationActions stringToIndicationType(NSString* action) {
    kSITIndicationActions type = kSITInvalidAction;
    if ([action isEqualToString:@"Invalid"]) {
        type = kSITInvalidAction;
    } else if ([action isEqualToString:@"Turn"]) {
        type = kSITTurn;
    } else if ([action isEqualToString:@"GoAhead"]) {
        type = kSITGoAhead;
    } else if ([action isEqualToString:@"ChangeFloor"]) {
        type = kSITChangeFloor;
    } else if ([action isEqualToString:@"End"]) {
        type = kSITEnd;
    }
    return type;
}

// locationState

SITLocationState stringToLocationState(NSString* state){
    SITLocationState type = kSITLocationStopped;
    if ([state isEqualToString:@"STOPPED"]) {
        type = kSITLocationStopped;
    } else if ([state isEqualToString:@"CALCULATING"]) {
        type = kSITLocationCalculating;
    } else if ([state isEqualToString:@"USER_NOT_IN_BUILDING"]) {
        type = kSITLocationUserNotInBuilding;
    } else if ([state isEqualToString:@"STARTING"]) {
        type = kSITLocationStarted;
    }
    return type;
}

static SitumLocationWrapper *singletonSitumLocationWrapperObj;

@implementation SitumLocationWrapper

+ (SitumLocationWrapper *)shared {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        singletonSitumLocationWrapperObj = [[SitumLocationWrapper alloc] init];
    });
    return singletonSitumLocationWrapperObj;
}

//Building

- (NSDictionary *) buildingToJsonObject:(SITBuilding *) building {
    
    NSMutableDictionary *jo  = [[NSMutableDictionary alloc] init];
    [jo setObject:emptyStrCheck(building.address) forKey:@"address"];
    [jo setObject:[self boundsToJsonObject:building.bounds] forKey:@"bounds"];
    [jo setObject:[self dimensionsToJsonObject:building.dimensions] forKey:@"dimensions"];
    [jo setObject:[self coordinateToJsonObject:building.center] forKey:@"center"];
    [jo setObject:emptyStrCheck(building.name) forKey:@"name"];
    [jo setObject:emptyStrCheck(building.infoHTML) forKey:@"infoHtml"];
    [jo setObject:emptyStrCheck(building.pictureThumbURL.direction) forKey:@"pictureThumbUrl"];
    [jo setObject:emptyStrCheck(building.pictureURL.direction) forKey:@"pictureUrl"];
    // [jo setObject:building.rotation forKey:@"rotation"];
    [jo setObject:[NSNumber numberWithFloat:[building.rotation degrees]] forKey:@"rotationDegrees"];
    [jo setObject:[NSNumber numberWithFloat:[building.rotation radians]] forKey:@"rotationRadians"];
    [jo setObject:emptyStrCheck(building.userIdentifier) forKey:@"userIdentifier"];
    [jo setObject:emptyStrCheck(building.identifier) forKey:@"identifier"];

    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc]init];
    [dateFormatter setDateFormat:DATEFORMAT];

    [jo setObject:emptyStrCheck([dateFormatter stringFromDate:building.createdAt])
           forKey:@"createdAt"];

    [jo setObject:emptyStrCheck([dateFormatter stringFromDate:building.updatedAt])
           forKey:@"updatedAt"];

    if (building.customFields) {
         [jo setObject:building.customFields forKey:@"customFields"];
    }

    return jo.copy;
}

- (NSString*) locationStateToString:(SITLocationState) state {
    NSString *type = @"";
    switch (state) {
        case kSITLocationStopped:
            type = @"STOPPED";
            break;
        case kSITLocationCalculating:
            type = @"CALCULATING";
            break;
        case kSITLocationUserNotInBuilding:
            type = @"USER_NOT_IN_BUILDING";
            break;
        case kSITLocationStarted:
            type = @"STARTING";
            break;
            
        default:
            break;
    }
    return type;
}

//deprecated method

- (NSDictionary *) buildingIndoorToJsonObject:(SITIndoorBuilding *) building __deprecated{
    
    NSMutableDictionary *jo  = [[NSMutableDictionary alloc] init];
    [jo setObject:emptyStrCheck(building.address) forKey:@"address"];
    [jo setObject:[self boundsToJsonObject:building.bounds] forKey:@"bounds"];
    [jo setObject:[self boundsToJsonObject:building.boundsRotated] forKey:@"boundsRotated"];
    [jo setObject:[self coordinateToJsonObject:building.coordinate] forKey:@"center"];
    //    [jo setObject:[self dimensionsToJsonObject:building.dimensions] forKey:@"dimensions"];
    [jo setObject:emptyStrCheck(building.name) forKey:@"name"];
    [jo setObject:emptyStrCheck(building.picture_thumb_url) forKey:@"pictureThumbUrl"];
    [jo setObject:emptyStrCheck(building.picture_url) forKey:@"pictureUrl"];
    [jo setObject:building.rotation forKey:@"rotation"];
    //TODO check
    //[jo setObject:emptyStrCheck(building.user_identifier) forKey:@"userIdentifier"];
    [jo setObject:emptyStrCheck([NSString stringWithFormat:@"%@", building.identifier]) forKey:@"identifier"];
    
    // if (building.customFields) {
    //     [jo setObject:building.customFields forKey:@"customFields"];
    // }
    
    return jo.copy;
}

//Floor

- (NSDictionary *) floorToJsonObject:(SITFloor *) floor {
    NSMutableDictionary *jo  = [[NSMutableDictionary alloc] init];
    //    [jo setObject:[NSNumber numberWithDouble:floor.altitude] forKey:@"altitude"];
    [jo setObject:emptyStrCheck([NSString stringWithFormat:@"%@", floor.buildingIdentifier]) forKey:@"buildingIdentifier"];
    [jo setObject:[NSNumber numberWithInteger: floor.level] forKey:@"level"];
    [jo setObject:floor.mapURL.direction forKey:@"mapUrl"];
    [jo setObject:[NSNumber numberWithDouble:floor.scale] forKey:@"scale"];
    [jo setObject:[NSString stringWithFormat:@"%@", floor.identifier] forKey:@"floorIdentifier"];
    [jo setObject:emptyStrCheck(floor.identifier) forKey:@"identifier"];

    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc]init];
    [dateFormatter setDateFormat:DATEFORMAT];

    [jo setObject:emptyStrCheck([dateFormatter stringFromDate:floor.createdAt])
           forKey:@"createdAt"];

    [jo setObject:emptyStrCheck([dateFormatter stringFromDate:floor.updatedAt])
           forKey:@"updatedAt"];

    return jo.copy;
}

- (SITFloor *) jsonObjectToFloor:(NSDictionary *) nsFloor {
    SITFloor *floor  = [[SITFloor alloc] init];

    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc]init];
    [dateFormatter setDateFormat:DATEFORMAT];

    floor.createdAt = [dateFormatter dateFromString:floor.createdAt];

    floor.updatedAt = [dateFormatter dateFromString:floor.updatedAt];

    floor.scale = [[nsFloor objectForKey:@"scale"] doubleValue];
    floor.mapURL = [[SITURL alloc] initWithDirection:[nsFloor objectForKey:@"mapUrl"]];;
    floor.level = [[nsFloor objectForKey:@"level"] intValue];
    floor.identifier = [nsFloor objectForKey:@"floorIdentifier"];
    floor.buildingIdentifier = [nsFloor objectForKey:@"buildingIdentifier"];
    return floor;
}

//Event

- (NSDictionary *) eventToJsonObject:(SITEvent *) event {
    NSMutableDictionary *jo  = [[NSMutableDictionary alloc] init];
    [jo setObject:[NSString stringWithFormat:@"%@", event.identifier] forKey:@"identifier"];
    [jo setObject:[NSString stringWithFormat:@"%@", event.project_identifier] forKey:@"buildingIdentifier"];
    [jo setObject:[NSString stringWithFormat:@"%@", event.info] forKey:@"infoHtml"];
    //[jo setObject:[NSNumber numberWithDouble:event.conversionArea] forKey:@"radius"];

    //floorIdentifier not presented in ios platform
    //radius not presented in ios platform

    return jo.copy;
}

//Category

//Floor

- (NSDictionary *) poiCategoryToJsonObject:(SITPOICategory *) category {
    NSMutableDictionary *jo  = [[NSMutableDictionary alloc] init];
    [jo setObject:[NSString stringWithFormat:@"%@", category.identifier] forKey:@"identifier"];
    [jo setObject:[NSNumber numberWithBool:category.isPublic] forKey:@"public"];
    [jo setObject:[NSString stringWithFormat:@"%@", category.code] forKey:@"poiCategoryCode"];
    [jo setObject:[NSString stringWithFormat:@"%@", category.name] forKey:@"poiCategoryName"];
    [jo setObject:category.iconURL.direction forKey:@"icon_deselected"];
    [jo setObject:category.selectedIconURL.direction forKey:@"icon_selected"];
    return jo.copy;
}

// POI

- (NSDictionary *) poiToJsonObject:(SITPOI *) poi {
    NSMutableDictionary *jo  = [[NSMutableDictionary alloc] init];
    [jo setObject:emptyStrCheck(poi.identifier) forKey:@"identifier"];
    [jo setObject:emptyStrCheck(poi.buildingIdentifier) forKey:@"buildingIdentifier"];
    [jo setObject:[self cartesianCoordinateToJsonObject:poi.position.cartesianCoordinate] forKey:@"cartesianCoordinate"];
    [jo setObject:[self coordinateToJsonObject:poi.position.coordinate] forKey:@"coordinate"];
    [jo setObject:emptyStrCheck(poi.position.floorIdentifier) forKey:@"floorIdentifier"];
    [jo setObject:emptyStrCheck(poi.name) forKey:@"poiName"];
    [jo setObject:[self pointToJsonObject:poi.position] forKey:@"position"];
    [jo setObject:[NSNumber numberWithBool:poi.position.isIndoor] forKey:@"isIndoor"];
    [jo setObject:[NSNumber numberWithBool:poi.position.isOutdoor] forKey:@"isOutdoor"];
    if (poi.customFields) {
        [jo setObject:poi.customFields forKey:@"customFields"];
    }
    return jo.copy;
}

// Location

- (NSDictionary *) locationToJsonObject:(SITLocation *) location {
    NSMutableDictionary *jo  = [[NSMutableDictionary alloc] init];
    [jo setObject:[NSNumber numberWithFloat:location.accuracy] forKey:@"accuracy"];
    [jo setObject:[self angleToJsonObject:location.bearing] forKey:@"bearing"];
    [jo setObject:[self qualityStringFromQuality:location.bearingQuality] forKey:@"bearingQuality"];
    [jo setObject:emptyStrCheck(location.position.buildingIdentifier) forKey:@"buildingIdentifier"];
    [jo setObject:[self angleToJsonObject:location.cartesianBearing] forKey:@"cartesianBearing"];
    [jo setObject:[self cartesianCoordinateToJsonObject:location.position.cartesianCoordinate] forKey:@"cartesianCoordinate"];
    [jo setObject:[self coordinateToJsonObject:location.position.coordinate] forKey:@"coordinate"];
    [jo setObject:emptyStrCheck(location.position.floorIdentifier) forKey:@"floorIdentifier"];
    [jo setObject:[self pointToJsonObject:location.position] forKey:@"position"];
    [jo setObject:emptyStrCheck(location.provider) forKey:@"provider"];
    [jo setObject:[self qualityStringFromQuality:location.quality] forKey:@"quality"];
    [jo setObject:[NSNumber numberWithBool:location.hasBearing] forKey:@"hasBearing"];
    [jo setObject:[NSNumber numberWithDouble:location.timestamp] forKey:@"timestamp"];
    [jo setObject:[NSNumber numberWithBool:location.position.isIndoor] forKey:@"isIndoor"];
    [jo setObject:[NSNumber numberWithBool:location.position.isOutdoor] forKey:@"isOutdoor"];
    [jo setObject:emptyStrCheck(location.deviceId) forKey:@"deviceId"];
    [jo setValue:@"locationChanged" forKey:@"type"];
    return jo.copy;
}

- (NSString *)qualityStringFromQuality:(kSITQualityValues)quality {
    return quality == kSITHigh ? @"HIGH":@"LOW";
}

- (SITLocation *) locationJsonObjectToLocation:(NSDictionary *) jo {
    NSTimeInterval timestamp = [(NSNumber*)[jo valueForKey:@"timestamp"] doubleValue];
    SITPoint *position = [self pointJsonObjectToPoint:[jo objectForKey:@"position"]];
    
    float bearing = [[[jo objectForKey:@"bearing"] valueForKey:@"degrees"] floatValue];
    float cartesianBearing = [[[jo objectForKey:@"cartesianBearing"] valueForKey:@"radians"] floatValue];
    
    kSITQualityValues quality = kSITHigh;

    float accuracy = [(NSNumber*)[jo objectForKey:@"accuracy"] floatValue];
    
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing:bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:[jo objectForKey:@"provider"]];
    return location;
}

- (NSDictionary *) coordinateToJsonObject:(CLLocationCoordinate2D) coordinate {
    NSMutableDictionary *jo  = [[NSMutableDictionary alloc] init];
    [jo setObject:[NSNumber numberWithDouble:coordinate.latitude] forKey:@"latitude"];
    [jo setObject:[NSNumber numberWithDouble:coordinate.longitude] forKey:@"longitude"];
    return jo.copy;
}

// Coordinate

- (NSDictionary *) indoorPointToJsonObject:(SITIndoorPoint *) iPoint {
    NSMutableDictionary *jo  = [[NSMutableDictionary alloc] init];
    //[jo setObject:[iPoint.x doubleValue  forKey:@"x"];
    //[jo setObject:[iPoint.y doubleValue  forKey:@"y"];
    return jo.copy;
}

- (CLLocationCoordinate2D) coordinateJsonObjectToCoordinate:(NSDictionary *) jo {
    CLLocationDegrees latitude = [(NSNumber*)[jo objectForKey:@"latitude"] doubleValue];
    CLLocationDegrees longitude = [(NSNumber*)[jo objectForKey:@"longitude"] doubleValue];
    
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(latitude, longitude);
    return coordinate;
}


// Point

- (NSDictionary *) pointToJsonObject:(SITPoint *) point {
    NSMutableDictionary *jo  = [[NSMutableDictionary alloc] init];
    [jo setObject:emptyStrCheck(point.buildingIdentifier) forKey:@"buildingIdentifier"];
    [jo setObject:[self cartesianCoordinateToJsonObject:point.cartesianCoordinate] forKey:@"cartesianCoordinate"];
    [jo setObject:[self coordinateToJsonObject:point.coordinate] forKey:@"coordinate"];
    [jo setObject:emptyStrCheck(point.floorIdentifier) forKey:@"floorIdentifier"];
    [jo setObject:[NSNumber numberWithBool:point.isIndoor] forKey:@"isIndoor"];
    [jo setObject:[NSNumber numberWithBool:point.isOutdoor] forKey:@"isOutdoor"];
    return jo.copy;
    
}

- (SITPoint *) pointJsonObjectToPoint:(NSDictionary *) jo {
    SITPoint *point = [[SITPoint alloc] initWithCoordinate:[self coordinateJsonObjectToCoordinate:[jo objectForKey:@"coordinate"]] buildingIdentifier:[jo valueForKey:@"buildingIdentifier"] floorIdentifier:[jo valueForKey:@"floorIdentifier"] cartesianCoordinate:[self cartesianCoordinateJsonObjectToCartesianCoordinate:[jo objectForKey:@"cartesianCoordinate"]]];
    return point;
}

// CartesianCoordinate

- (NSDictionary *) cartesianCoordinateToJsonObject:(SITCartesianCoordinate *) cartesianCoordinate {
    NSMutableDictionary *jo  = [[NSMutableDictionary alloc] init];
    [jo setObject:[NSNumber numberWithDouble:cartesianCoordinate.x] forKey:@"x"];
    [jo setObject:[NSNumber numberWithDouble:cartesianCoordinate.y] forKey:@"y"];
    return jo.copy;
    
}

- (SITCartesianCoordinate *) cartesianCoordinateJsonObjectToCartesianCoordinate:(NSDictionary *) jo {
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:[[jo valueForKey:@"x"] doubleValue] y:[[jo valueForKey:@"y"] doubleValue]];
    return cartesianCoordinate;
}

// Dimensions

- (NSDictionary *) dimensionsToJsonObject:(SITDimensions *) dimensions {
    NSMutableDictionary *jo  = [[NSMutableDictionary alloc] init];
    [jo setObject:[NSNumber numberWithDouble:dimensions.width] forKey:@"width"];
    [jo setObject:[NSNumber numberWithDouble:dimensions.height] forKey:@"height"];
    return jo.copy;
}

// Bounds

- (NSDictionary *) boundsToJsonObject:(SITBounds) bounds {
    NSMutableDictionary *jo  = [[NSMutableDictionary alloc] init];
    [jo setObject:[self coordinateToJsonObject:bounds.northEast] forKey:@"northEast"];
    [jo setObject:[self coordinateToJsonObject:bounds.northWest] forKey:@"northWest"];
    [jo setObject:[self coordinateToJsonObject:bounds.southEast] forKey:@"southEast"];
    [jo setObject:[self coordinateToJsonObject:bounds.southWest] forKey:@"southWest"];
    return jo.copy;
}

- (NSDictionary *) conversionAreaToJsonObject:(SITRectangularArea *) ca {
    NSMutableDictionary *jo  = [[NSMutableDictionary alloc] init];
    //[jo setObject:[self coordinateToJsonObject:ca.topLeft] forKey:@"topLeft"];
    //[jo setObject:[self coordinateToJsonObject:bounds.northWest] forKey:@"top"];
    //[jo setObject:[self coordinateToJsonObject:bounds.southEast] forKey:@"center"];
    //[jo setObject:[self coordinateToJsonObject:bounds.southWest] forKey:@"right"];
    return jo.copy;
}

// Angle

- (NSDictionary *) angleToJsonObject:(SITAngle *) angle {
    NSMutableDictionary *jo  = [[NSMutableDictionary alloc] init];
    [jo setObject:[NSNumber numberWithDouble:angle.degrees] forKey:@"degrees"];
    [jo setObject:[NSNumber numberWithDouble:angle.radians] forKey:@"radians"];
    [jo setObject:[NSNumber numberWithDouble:angle.degressClockwise] forKey:@"degressClockwise"];
    [jo setObject:[NSNumber numberWithDouble:angle.radiansMinusPiPi] forKey:@"radiansMinusPiPi"];
    return jo.copy;
}

// Route

- (NSDictionary *) routeToJsonObject:(SITRoute *) route {
    NSMutableDictionary *jo  = [[NSMutableDictionary alloc] init];
    
    NSMutableArray *pointsJsonArray = [[NSMutableArray alloc] init];
    NSMutableArray *stepsJsonArray = [[NSMutableArray alloc] init];
    for (SITRouteStep *routeStep in route.routeSteps) {
        [stepsJsonArray addObject:[self routeStepToJsonObject:routeStep]];
        [pointsJsonArray addObject:[self pointToJsonObject:routeStep.to]];
    }
    [pointsJsonArray addObject:[self pointToJsonObject:route.destination]];
    
    NSMutableArray *indicationsJsonArray = [[NSMutableArray alloc] init];
    for (SITIndication *indication in route.indications) {
        [indicationsJsonArray addObject:[self indicationToJsonObject:indication]];
    }

    [jo setObject:[self pointToJsonObject:route.origin] forKey:@"from"];
    [jo setObject:[self pointToJsonObject:route.destination] forKey:@"to"];
    [jo setObject:stepsJsonArray.copy forKey:@"steps"];
    [jo setObject:indicationsJsonArray forKey:@"indications"];

    if (route.routeSteps.count == 0) return jo; // No steps on the route

    
    [jo setObject:stepsJsonArray.copy forKey:@"edges"];
    [jo setObject:stepsJsonArray.firstObject forKey:@"firstStep"];
    [jo setObject:stepsJsonArray.lastObject forKey:@"lastStep"];
    [jo setObject:pointsJsonArray forKey:@"nodes"];
    [jo setObject:pointsJsonArray forKey:@"points"];
    return jo.copy;
    
}

//RouteStep

- (NSDictionary *) routeStepToJsonObject:(SITRouteStep *) routeStep {
    NSMutableDictionary *jo  = [[NSMutableDictionary alloc] init];
    [jo setObject:[NSNumber numberWithDouble:routeStep.stepDistance] forKey:@"distance"];
    [jo setObject:[NSNumber numberWithDouble:routeStep.distanceToGoal] forKey:@"distanceToGoal"];
    [jo setObject:[self pointToJsonObject:routeStep.from] forKey:@"from"];
    [jo setObject:[NSNumber numberWithInteger:routeStep.nextStepIndex] forKey:@"nextStepIndex"];
    [jo setObject:[self pointToJsonObject:routeStep.from] forKey:@"to"];
    [jo setObject:[NSNumber numberWithInteger:routeStep.index] forKey:@"id"];
    [jo setObject:[NSNumber numberWithBool:routeStep.isFirst] forKey:@"isFirst"];
    [jo setObject:[NSNumber numberWithBool:routeStep.isLast] forKey:@"isLast"];
    return jo.copy;
}

- (SITRouteStep *) routeStepJsonObjectToRouteStep:(NSDictionary *) jo {
    SITPoint *fromPoint = (SITPoint*)[jo objectForKey:@"from"];
    SITPoint *toPoint = (SITPoint*)[jo objectForKey:@"to"];
    
    SITRouteStep *routeStep = [[SITRouteStep alloc] initWithIndex:[(NSNumber*)[jo valueForKey:@"id"] integerValue] from:fromPoint to:toPoint isFirst:[(NSNumber*)[jo valueForKey:@"isFirst"] boolValue] isLast:[(NSNumber*)[jo valueForKey:@"isLast"] boolValue] nextStepIndex:[(NSNumber*)[jo valueForKey:@"nextStepIndex"] integerValue] stepDistance:[(NSNumber*)[jo valueForKey:@"distance"] doubleValue] distanceToGoal:[(NSNumber*)[jo valueForKey:@"distanceToGoal"] doubleValue]];
    
    return routeStep;
}

// Indication

- (NSDictionary *) indicationToJsonObject:(SITIndication *) indication {
    NSMutableDictionary *jo  = [[NSMutableDictionary alloc] init];
    [jo setObject:[NSNumber numberWithDouble:indication.horizontalDistance] forKey:@"distance"];
    [jo setObject:[NSNumber numberWithFloat:indication.verticalDistance] forKey:@"distanceToNextLevel"];
    [jo setObject:indicationTypeToString(indication.action) forKey:@"indicationType"];
    [jo setObject:[NSNumber numberWithFloat:indication.orientationChange] forKey:@"orientation"];
    [jo setObject:orientationTypeToString(indication.orientation) forKey:@"orientationType"];
    [jo setObject:[NSNumber numberWithInteger:indication.destinationStepIndex] forKey:@"stepIdxDestination"];
    [jo setObject:[NSNumber numberWithInteger:indication.originStepIndex] forKey:@"stepIdxOrigin"];
    [jo setObject:[NSNumber numberWithBool:indication.needLevelChange] forKey:@"neededLevelChange"];
    [jo setObject:[indication humanReadableMessage] forKey:@"humanReadableMessage"];
    return jo.copy;
}

- (SITIndication *) indicationJsonObjectToIndication:(NSDictionary *) jo {
    NSInteger stepIdxOrigin = [(NSNumber*)[jo valueForKey:@"stepIdxOrigin"] integerValue];
    NSInteger stepIdxDestination = [(NSNumber*)[jo valueForKey:@"stepIdxDestination"] integerValue];
    float horizontalDistance = [(NSNumber*)[jo valueForKey:@"distance"] floatValue];
    float orientationChange = [(NSNumber*)[jo valueForKey:@"orientation"] floatValue];
    float verticalDistance = [(NSNumber*)[jo valueForKey:@"distanceToNextLevel"] floatValue];
    kSITIndicationActions action = stringToIndicationType([jo valueForKey:@"indicationType"]);
    kSITIndicationOrientation orientation = stringToOrientationType([jo valueForKey:@"orientationType"]);
    
    SITIndication *indication = [[SITIndication alloc] initWithOriginStepIndex:stepIdxOrigin destinationStepIndex:stepIdxDestination action:action horizontalDistance:horizontalDistance orientation:orientation orientationChange:orientationChange verticalDistance:verticalDistance];
    
    return indication;
}

// NavigationProgress

- (NSDictionary *) navigationProgressToJsonObject:(SITNavigationProgress *) navigationProgress {
    NSMutableDictionary *jo  = [[NSMutableDictionary alloc] init];
    [jo setObject:[self pointToJsonObject:navigationProgress.closestPointToRoute] forKey:@"closestPointInRoute"];
    [jo setObject:[self indicationToJsonObject:navigationProgress.currentIndication] forKey:@"currentIndication"];
    [jo setObject:[NSNumber numberWithInteger:navigationProgress.currentStepIndex] forKey:@"currentStepIndex"];
    [jo setObject:[NSNumber numberWithFloat:navigationProgress.distanceToGoal] forKey:@"distanceToGoal"];
    [jo setObject:[NSNumber numberWithFloat:navigationProgress.distanceToEndStep] forKey:@"distanceToEndStep"];
    [jo setObject:[NSNumber numberWithFloat:navigationProgress.timeToEndStep] forKey:@"timeToEndStep"];
    [jo setObject:[NSNumber numberWithFloat:navigationProgress.timeToGoal] forKey:@"timeToGoal"];
    return jo.copy;
}

// check nil string

@end
