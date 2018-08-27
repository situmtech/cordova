//
//  situmcordovapluginCreatorTests.m
//  situmcordovapluginTests
//
//  Created by Situm on 01/7/18.
//  Copyright © 2018 Situm. All rights reserved.
//

#import "SitumCreatorTests.h"

@implementation SitumCreatorTests

//angle1.json
+ (SITAngle *) createAngleFromDegrees {
    SITAngle *angle = [[SITAngle alloc] initWithDegrees:47];
    return angle;
}

//angle2.json
+ (SITAngle *) createAngleFromRadians {
    SITAngle *angle = [[SITAngle alloc] initWithRadians:1.4];
    return angle;
}

//bounds1.json
+ (SITBounds) createBoundsWithArray {
    SITBounds Bounds;
    Bounds.southWest = CLLocationCoordinate2DMake(2, 4);
    Bounds.southEast = CLLocationCoordinate2DMake(6, 3);
    Bounds.northWest = CLLocationCoordinate2DMake(2, 6);
    Bounds.northEast = CLLocationCoordinate2DMake(5, 8);
    return Bounds;
}

//bounds2.json
//NOTE: it is redundant in ios, it is equals to previous bounds1.json
+ (SITBounds) createBounds {
    SITBounds Bounds;
    Bounds.southWest = CLLocationCoordinate2DMake(2, 4);
    Bounds.southEast = CLLocationCoordinate2DMake(6, 3);
    Bounds.northWest = CLLocationCoordinate2DMake(2, 6);
    Bounds.northEast = CLLocationCoordinate2DMake(5, 8);
    return Bounds;
}

//cartesianCoordinate1.json
+ (SITCartesianCoordinate *) createCartesianCoordinate {
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:5 y:7];
    return cartesianCoordinate;
}

//coordinate1.json
+ (CLLocationCoordinate2D) createCoordinate {
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(23, 43);
    return coordinate;
}

//dimensions1.json
+ (SITDimensions *) createDimensions {
    SITDimensions *dimensions = [[SITDimensions alloc] initWithWidth:5 height:7];
    return dimensions;
}

//floor1.json
+ (SITFloor *) createFloorWithAltitude {
    SITFloor *floor = [[SITFloor alloc] init];
    /*NSDateFormatter *dateFormatter = [[NSDateFormatter alloc]init];
    [dateFormatter setDateFormat:DATEFORMAT];
    
    floor.createdAt = [dateFormatter dateFromString:floor.createdAt];
    
    floor.updatedAt = [dateFormatter dateFromString:floor.updatedAt];*/
    floor.altitude = 2.5;
    floor.scale = 10.2;
    floor.mapURL = [[SITURL alloc] initWithDirection:@"TEST_URL"];
    floor.level = 1;
    floor.identifier = @"-1";
    floor.buildingIdentifier = @"101";
    return floor;
}

//floor2.json
+ (SITFloor *) createFloorWithoutAltitude {
    SITFloor *floor = [[SITFloor alloc] init];
    
    /*NSDateFormatter *dateFormatter = [[NSDateFormatter alloc]init];
    [dateFormatter setDateFormat:DATEFORMAT];
    floor.createdAt = [dateFormatter dateFromString:floor.createdAt];
    floor.updatedAt = [dateFormatter dateFromString:floor.updatedAt];*/
    
    floor.altitude = 0;
    floor.scale = 10.2;
    floor.mapURL = [[SITURL alloc] initWithDirection:@"TEST_URL"];
    floor.level = 1;
    floor.identifier = @"-1";
    floor.buildingIdentifier = @"101";
    return floor;
}

//indication1.json
+ (SITIndication *) createIndication {
    NSInteger stepIdxOrigin = 4;
    NSInteger stepIdxDestination = 5;
    float horizontalDistance = 5;
    float orientationChange = 14.5;
    float verticalDistance = 16;
    NSNumber* nextLevel = nil;
    kSITIndicationActions action = kSITGoAhead;
    kSITIndicationOrientation orientation = kSITStraight;
    SITIndication *indication = [[SITIndication alloc] initWithOriginStepIndex:stepIdxOrigin destinationStepIndex:stepIdxDestination action:action horizontalDistance:horizontalDistance orientation:orientation orientationChange:orientationChange verticalDistance:verticalDistance nextLevel:nextLevel];
    
    return indication;
}


//kSITLocationStopped,kSITLocationStarted not implemented in tests.
//-1 default value for values not implemented in ios

//locationStatus1.json
+ (SITLocationState) createLocationStatusStarting {
 return -1;
}

//locationStatus2.json
+ (SITLocationState) createLocationStatusBLENotAvailable {
    return -1;
}

//locationStatus3.json
+ (SITLocationState) createLocationStatusCalculating {
    return kSITLocationCalculating;
}

//locationStatus4.json
+ (SITLocationState) createLocationStatusCompassCalibrationNeeded {
    return -1;
}

//locationStatus5.json
+ (SITLocationState) createLocationStatusCompassCalibrationNotNeeded {
    return -1;
}

//locationStatus6.json
+ (SITLocationState) createLocationStatusNoConnection {
    return -1;
}

//locationStatus7.json
+ (SITLocationState) createLocationStatusPreparingPositioningModel {
    return -1;
}

//locationStatus8.json
+ (SITLocationState) createLocationStatusProcessingPositioningModel {
    return -1;
}

//locationStatus9.json
+ (SITLocationState) createLocationStatusRetryDownloadPositioningModel {
    return -1;
}

//locationStatus10.json
+ (SITLocationState) createLocationStatusStartDownloadPositioningModel {
    return -1;
}

//locationStatus11.json
+ (SITLocationState) createLocationStatusStartingPositioning {
    return -1;
}

//locationStatus12.json
+ (SITLocationState) createLocationStatusTimeSettingsManual {
    return -1;
}

//locationStatus13.json
+ (SITLocationState) createLocationStatusUserNotInBuilding {
    return kSITLocationUserNotInBuilding;
}

//location1.json
+ (SITLocation *) createLocationWithBuildingFloorAndCartesianCoordinates {
    NSTimeInterval timestamp = 14676784;
    //empty coordinate 0,0
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(0, 0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:45 y:46];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    float bearing = 0; //degrees
    float cartesianBearing = 0; //radians
    kSITQualityValues quality = kSITLow;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//location2.json
+ (SITLocation *) createLocationWithCoordinate {
    NSTimeInterval timestamp = 14676784;
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(8.6, 10.5);
    SITPoint *position  = [[SITPoint alloc]  initWihtCoordinate:coordinate];
    float bearing = 0; //degrees
    float cartesianBearing = 0; //radians
    kSITQualityValues quality = kSITLow;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//location3.json
+ (SITLocation *) createLocationWithBuildingAndCoordinate {
    NSTimeInterval timestamp = 14676784;
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(8.6, 10.5);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:0 y:0];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"-1" cartesianCoordinate: cartesianCoordinate];
    float bearing = 0; //degrees
    float cartesianBearing = 0; //radians
    kSITQualityValues quality = kSITLow;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//location4.json
+ (SITLocation *) locationWithCartesianBearing {
    NSTimeInterval timestamp = 14676784;
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(0, 0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:45 y:46];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    float bearing = 92; //degrees
    float cartesianBearing = 2; //radians
    kSITQualityValues quality = kSITHigh;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//location5.json
+ (SITLocation *) locationWithoutCartesianBearing {
    NSTimeInterval timestamp = 14676784;
    //empty coordinate 0,0
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(0, 0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:45 y:46];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    float bearing = 0; //degrees
    float cartesianBearing = 0; //radians
    kSITQualityValues quality = kSITLow;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//location6.json
+ (SITLocation *) locationWithBearing {
    NSTimeInterval timestamp = 14676784;
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(8.6, 10.5);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:0 y:0];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"-1" floorIdentifier:@"-1" cartesianCoordinate: cartesianCoordinate];
    float bearing = 92; //degrees
    float cartesianBearing = 0; //radians
    kSITQualityValues quality = kSITLow;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//location7.json
+ (SITLocation *) locationWithouthBearing {
    NSTimeInterval timestamp = 14676784;
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(0, 0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:45 y:46];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    float bearing = 0; //degrees
    float cartesianBearing = 0; //radians
    kSITQualityValues quality = kSITLow;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//location8.json
+ (SITLocation *) indoorLocationWithIndoorBearingQualityLow {
    NSTimeInterval timestamp = 14676784;
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(0, 0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:45 y:46];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    float bearing = 92; //degrees
    float cartesianBearing = 2; //radians
    kSITQualityValues quality = kSITLow;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//location9.json
+ (SITLocation *) indoorLocationWithIndoorBearingQualityHigh {
    NSTimeInterval timestamp = 14676784;
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(0, 0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:45 y:46];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    float bearing = 92; //degrees
    float cartesianBearing = 2; //radians
    kSITQualityValues quality = kSITHigh;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//location10.json
+ (SITLocation *) outdoorLocation {
    NSTimeInterval timestamp = 14676784;
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(8.6, 10.5);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:0 y:0];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"-1" floorIdentifier:@"-1" cartesianCoordinate: cartesianCoordinate];
    float bearing = 92; //degrees
    float cartesianBearing = 0; //radians
    kSITQualityValues quality = kSITHigh;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//navigationProgress1.json
+ (SITNavigationProgress *) createNavigationProgressOutdoor {
    SITNavigationProgress *navigationProgress = [[SITNavigationProgress alloc] init];
    
    //currentIndication
    NSInteger currentStepIdxOrigin = 4;
    NSInteger currentStepIdxDestination = 5;
    float currentHorizontalDistance = 11.4;
    float currentOrientationChange = 14.5;
    float currentVerticalDistance = 15;
    NSNumber* currentNextLevel = nil;
    kSITIndicationActions currentAction = kSITGoAhead;
    kSITIndicationOrientation currentOrientation = kSITStraight;
    SITIndication *currentIndication = [[SITIndication alloc] initWithOriginStepIndex:currentStepIdxOrigin destinationStepIndex:currentStepIdxDestination action:currentAction horizontalDistance:currentHorizontalDistance orientation:currentOrientation orientationChange:currentOrientationChange verticalDistance:currentVerticalDistance nextLevel:currentNextLevel];
    navigationProgress.currentIndication = currentIndication;
    
    //nextIndication
    NSInteger nextStepIdxOrigin = 4;
    NSInteger nextStepIdxDestination = 5;
    float nextHorizontalDistance = 8.6;
    float nextOrientationChange = 3.5;
    float nextVerticalDistance = 24;
    NSNumber* nextNextLevel = nil;
    kSITIndicationActions nextAction = kSITGoAhead;
    kSITIndicationOrientation nextOrientation = kSITStraight;
    SITIndication *nextIndication = [[SITIndication alloc] initWithOriginStepIndex:nextStepIdxOrigin destinationStepIndex:nextStepIdxDestination action:nextAction horizontalDistance:nextHorizontalDistance orientation:nextOrientation orientationChange:nextOrientationChange verticalDistance:nextVerticalDistance nextLevel:nextNextLevel];
    navigationProgress.nextIndication = nextIndication;
    
    navigationProgress.distanceToClosestPointInRoute = 12;
    navigationProgress.currentStepIndex = 1;
    navigationProgress.distanceToGoal = 24;
    navigationProgress.distanceToEndStep = 16;
    navigationProgress.timeToEndStep = 16
    navigationProgress.timeToGoal = 24;
    
    //routeStep
    //from
    CLLocationCoordinate2D fromCoordinate = CLLocationCoordinate2DMake(2, 5);
    SITCartesianCoordinate *fromCartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:0 y:0];
    SITPoint *from  = [[SITPoint alloc]  initWithCoordinate:fromCoordinate buildingIdentifier:@"101" floorIdentifier:@"-1" cartesianCoordinate: fromCartesianCoordinate];
    //to
    CLLocationCoordinate2D toCoordinate = CLLocationCoordinate2DMake(3, 4);
    SITCartesianCoordinate *toCartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:0 y:0];
    SITPoint *to  = [[SITPoint alloc]  initWithCoordinate:toCoordinate buildingIdentifier:@"101" floorIdentifier:@"-1" cartesianCoordinate: toCartesianCoordinate];
    
    SITRouteStep *routeStep = [[SITRouteStep alloc] initWithIndex:1 from:from to:to isFirst:true isLast:false nextStepIndex:1 stepDistance:23.4 distanceToGoal:27];
    navigationProgress.routeStep = routeStep;
    
    //closestLocation
    NSTimeInterval timestamp = 1242142142;
    //empty coordinate 0,0
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(2, 4);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:0 y:0];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    float bearing = 0; //degrees
    float cartesianBearing = 0; //radians
    kSITQualityValues quality = kSITLow;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 0;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    navigationProgress.closestLocationInRoute = location;
    
    return navigationProgress;
}

//navigationProgress2.json
+ (SITNavigationProgress *) createNavigationProgressIndoor {
    SITNavigationProgress *navigationProgress = [[SITNavigationProgress alloc] init];
    
    //currentIndication
    NSInteger currentStepIdxOrigin = 4;
    NSInteger currentStepIdxDestination = 5;
    float currentHorizontalDistance = 11.4;
    float currentOrientationChange = 14.5;
    float currentVerticalDistance = 15;
    NSNumber* currentNextLevel = nil;
    kSITIndicationActions currentAction = kSITGoAhead;
    kSITIndicationOrientation currentOrientation = kSITStraight;
    SITIndication *currentIndication = [[SITIndication alloc] initWithOriginStepIndex:currentStepIdxOrigin destinationStepIndex:currentStepIdxDestination action:currentAction horizontalDistance:currentHorizontalDistance orientation:currentOrientation orientationChange:currentOrientationChange verticalDistance:currentVerticalDistance nextLevel:currentNextLevel];
    navigationProgress.currentIndication = currentIndication;
    
    //nextIndication
    NSInteger nextStepIdxOrigin = 4;
    NSInteger nextStepIdxDestination = 5;
    float nextHorizontalDistance = 8.6;
    float nextOrientationChange = 3.5;
    float nextVerticalDistance = 24;
    NSNumber* nextNextLevel = nil;
    kSITIndicationActions nextAction = kSITGoAhead;
    kSITIndicationOrientation nextOrientation = kSITStraight;
    SITIndication *nextIndication = [[SITIndication alloc] initWithOriginStepIndex:nextStepIdxOrigin destinationStepIndex:nextStepIdxDestination action:nextAction horizontalDistance:nextHorizontalDistance orientation:nextOrientation orientationChange:nextOrientationChange verticalDistance:nextVerticalDistance nextLevel:nextNextLevel];
    navigationProgress.nextIndication = nextIndication;
    
    navigationProgress.distanceToClosestPointInRoute = 12;
    navigationProgress.currentStepIndex = 1;
    navigationProgress.distanceToGoal = 24;
    navigationProgress.distanceToEndStep = 16;
    navigationProgress.timeToEndStep = 16;
    navigationProgress.timeToGoal = 24;
    
    //routeStep
    //from
    CLLocationCoordinate2D fromCoordinate = CLLocationCoordinate2DMake(0, 0);
    SITCartesianCoordinate *fromCartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:5 y:6];
    SITPoint *from  = [[SITPoint alloc]  initWithCoordinate:fromCoordinate buildingIdentifier:@"101" floorIdentifier:@"12" cartesianCoordinate: fromCartesianCoordinate];
    //to
    CLLocationCoordinate2D toCoordinate = CLLocationCoordinate2DMake(0, 0);
    SITCartesianCoordinate *toCartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:3 y:5];
    SITPoint *to  = [[SITPoint alloc]  initWithCoordinate:toCoordinate buildingIdentifier:@"101" floorIdentifier:@"12" cartesianCoordinate: toCartesianCoordinate];
    
    SITRouteStep *routeStep = [[SITRouteStep alloc] initWithIndex:1 from:from to:to isFirst:true isLast:false nextStepIndex:1 stepDistance:23.4 distanceToGoal:27];
    navigationProgress.routeStep = routeStep;
    
    //closestLocation
    NSTimeInterval timestamp = 1242142142;
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(2, 4);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:2 y:5];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    float bearing = 0; //degrees
    float cartesianBearing = 0; //radians
    kSITQualityValues quality = kSITLow;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 0;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    navigationProgress.closestLocationInRoute = location;
    
    return navigationProgress;
}

//point1.json
+ (SITPoint *) createPointWithCoordinate {
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(3, 6);
    SITPoint *point = [[SITPoint alloc] initWihtCoordinate:coordinate];
    return point;
}

//point2.json
+ (SITPoint *) createPointWithCoordinateAndBuildingId {
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(54, 64);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:0 y:0];
    SITPoint *point  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"-1" cartesianCoordinate: cartesianCoordinate];
    return point;
}

//point3.json
+ (SITPoint *) createPointWithBuildingIdAndFloor {
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(0, 0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:5 y:7];
    SITPoint *point  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    return point;
}

//point4.json
+ (SITPoint *) createPointWithBuildingWithAngleFromDegrees {
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(12.3,14.0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:4 y:7];
    SITPoint *point  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"-1" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    return point;
}

//point5.json
+ (SITPoint *) createPointWithBuildingWithAngleFromRadians {
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(12.3,14.0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:4 y:7];
    SITPoint *point  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"-1" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    return point;
}

//point6.json
+ (SITPoint *) createPointWithBuildingWithAddress {
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(12.3,14.0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:4 y:7];
    SITPoint *point  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"-1" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    return point;
}

//point7.json
+ (SITPoint *) createPointWithBuildingWithInfo {
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(12.3,14.0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:4 y:7];
    SITPoint *point  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"-1" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    return point;
}

//point8.json
+ (SITPoint *) createPointWithBuildingWithPicture {
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(12.3,14.0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:4 y:7];
    SITPoint *point  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"-1" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    return point;
}

@end

