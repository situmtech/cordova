//
//  TestingHelper.m
//  situmcordovapluginTests
//
//  Created by Situm on 24/7/18.
//  Copyright © 2018 Situm. All rights reserved.
//
#import "TestingHelper.h"
#import <XCTest/XCTest.h>



@implementation TestingHelper

+ (id)dataFromJSONFileNamed:(NSString *)fileName inDirectory: (NSString *)pathResources {
    //get file path relative to project for avoiding CI integration problems
    NSString *filePath = [[NSBundle bundleForClass:[self class]] pathForResource:fileName
                                                         ofType:@"json"
                                                    inDirectory:pathResources];
    NSDictionary *json = [[NSMutableDictionary alloc] init];
    //check file exists
    if (filePath) {
        //retrieve file content
        NSData *data = [[NSData alloc] initWithContentsOfFile:filePath];
        
        //convert JSON NSData to a usable NSDictionary
        NSError *error;
        json = [NSJSONSerialization JSONObjectWithData:data
                                               options:0
                                                 error:&error];
        if (error) {
            NSLog(@"Something went wrong! %@", error.localizedDescription);
        }
    }
    else {
        NSLog(@"Couldn't find file!");
    }
    return json.copy;
}

- (void) assertCoordinate: (NSDictionary *) jsonCoordinateFile : (NSDictionary *) coordinateJO {
    XCTAssertEqualWithAccuracy([jsonCoordinateFile[@"longitude"] doubleValue], [coordinateJO[@"longitude"] doubleValue], 0.001);
    XCTAssertEqualWithAccuracy([jsonCoordinateFile[@"latitude"] doubleValue], [coordinateJO[@"latitude"] doubleValue], 0.001);
}

- (void) assertCartesianCoordinate: (NSDictionary *) jsonCartesianCoordinateFile : (NSDictionary *) cartesianCoordinateJO {
    XCTAssertEqualWithAccuracy([jsonCartesianCoordinateFile[@"x"] doubleValue], [cartesianCoordinateJO[@"x"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonCartesianCoordinateFile[@"y"] doubleValue], [cartesianCoordinateJO[@"y"] doubleValue], 0.0001);
}

- (void) assertAngle: (NSDictionary *) jsonAngleFile : (NSDictionary *) angleJO;
{
    XCTAssertEqualWithAccuracy([jsonAngleFile[@"radiansMinusPiPi"] doubleValue], [angleJO[@"radiansMinusPiPi"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonAngleFile[@"radians"] doubleValue], [angleJO[@"radians"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonAngleFile[@"degreesClockwise"] doubleValue], [angleJO[@"degreesClockwise"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonAngleFile[@"degrees"] doubleValue], [angleJO[@"degrees"] doubleValue], 0.0001);
}

- (void) assertBound: (NSDictionary *) jsonBoundFile : (NSDictionary *) boundJO {
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"northWest"][@"latitude"] doubleValue], [boundJO[@"northWest"][@"latitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"northWest"][@"longitude"] doubleValue], [boundJO[@"northWest"][@"longitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"northEast"][@"latitude"] doubleValue], [boundJO[@"northEast"][@"latitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"northEast"][@"longitude"] doubleValue], [boundJO[@"northEast"][@"longitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"southWest"][@"latitude"] doubleValue], [boundJO[@"southWest"][@"latitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"southWest"][@"longitude"] doubleValue], [boundJO[@"southWest"][@"longitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"southEast"][@"latitude"] doubleValue], [boundJO[@"southEast"][@"latitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"southEast"][@"longitude"] doubleValue], [boundJO[@"southEast"][@"longitude"] doubleValue], 0.0001);
}

- (void) assertBuilding: (NSDictionary *) jsonBuildingFile : (NSDictionary *) buildingJO {
    XCTAssertEqualObjects(jsonBuildingFile[@"address"], buildingJO[@"address"]);
    [self assertBound: jsonBuildingFile[@"bounds"][@"northEast"]: buildingJO[@"bounds"][@"northEast"]];
    [self assertBound: jsonBuildingFile[@"bounds"][@"northWest"]: buildingJO[@"bounds"][@"northWest"]];
    [self assertBound: jsonBuildingFile[@"bounds"][@"southEast"]: buildingJO[@"bounds"][@"southEast"]];
    [self assertBound: jsonBuildingFile[@"bounds"][@"southWest"]: buildingJO[@"bounds"][@"southWest"]];
    [self assertBound: jsonBuildingFile[@"boundsRotated"][@"northEast"]: buildingJO[@"boundsRotated"][@"northEast"]];
    [self assertBound: jsonBuildingFile[@"boundsRotated"][@"northWest"]: buildingJO[@"boundsRotated"][@"northWest"]];
    [self assertBound: jsonBuildingFile[@"boundsRotated"][@"southEast"]: buildingJO[@"boundsRotated"][@"southEast"]];
    [self assertBound: jsonBuildingFile[@"boundsRotated"][@"southWest"]: buildingJO[@"boundsRotated"][@"southWest"]];
    [self assertBound: jsonBuildingFile[@"center"]: buildingJO[@"center"]];
    [self assertDimension: jsonBuildingFile[@"dimensions"]: buildingJO[@"dimensions"]];
    XCTAssertEqualObjects(jsonBuildingFile[@"infoHtml"], buildingJO[@"infoHtml"]);
    XCTAssertEqualObjects(jsonBuildingFile[@"pictureThumbUrl"], buildingJO[@"pictureThumbUrl"]);
    XCTAssertEqualObjects(jsonBuildingFile[@"pictureUrl"], buildingJO[@"pictureUrl"]);
    XCTAssertEqualWithAccuracy([jsonBuildingFile[@"pictureUrl"] doubleValue], [buildingJO[@"pictureUrl"] doubleValue], 0.0001);
    XCTAssertEqualObjects(jsonBuildingFile[@"name"], buildingJO[@"name"]);
    XCTAssertEqualWithAccuracy([jsonBuildingFile[@"rotation"] doubleValue], [buildingJO[@"rotation"] doubleValue], 0.0001);
    XCTAssertEqualObjects(jsonBuildingFile[@"updatedAt"], buildingJO[@"updatedAt"]);
    XCTAssertEqualObjects(jsonBuildingFile[@"createdAt"], buildingJO[@"createdAt"]);
    XCTAssertEqualObjects(jsonBuildingFile[@"userIdentifier"], buildingJO[@"userIdentifier"]);
    XCTAssertEqualObjects(jsonBuildingFile[@"customFields"], buildingJO[@"customFields"]);
}

- (void) assertDimension: (NSDictionary *) jsonDimensionFile : (NSDictionary *) dimensionJO {
    XCTAssertEqualWithAccuracy([jsonDimensionFile[@"width"] doubleValue], [dimensionJO[@"width"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonDimensionFile[@"height"] doubleValue], [dimensionJO[@"height"] doubleValue], 0.0001);
}

- (void) assertEvent: (NSDictionary *) jsonEventFile : (NSDictionary *) eventJO {
    XCTAssertEqualObjects(jsonEventFile[@"identifier"], eventJO[@"identifier"]);
    XCTAssertEqualObjects(jsonEventFile[@"buildingIdentifier"], eventJO[@"buildingIdentifier"]);
    XCTAssertEqualObjects(jsonEventFile[@"infoHtml"], eventJO[@"infoHtml"]);
    [self assertCircularArea:jsonEventFile[@"trigger"] :eventJO[@"trigger"]];
    [self assertCircularArea:jsonEventFile[@"conversion"] :eventJO[@"conversion"]];
    XCTAssertEqualObjects(jsonEventFile[@"name"], eventJO[@"name"]);
    XCTAssertEqualObjects(jsonEventFile[@"customFields"], eventJO[@"customFields"]);
}

- (void) assertCircularArea: (NSDictionary *) jsonCircularAreaFile : (NSDictionary *) circularAreaJO {
    [self assertPoint:jsonCircularAreaFile[@"center"] :circularAreaJO[@"center"]];
    XCTAssertEqualObjects(jsonCircularAreaFile[@"radius"], circularAreaJO[@"radius"]);
}

- (void) assertFloor: (NSDictionary *) jsonFloorFile : (NSDictionary *) floorJO {
    XCTAssertEqualWithAccuracy([jsonFloorFile[@"altitude"] doubleValue], [floorJO[@"altitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonFloorFile[@"level"] doubleValue], [floorJO[@"level"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonFloorFile[@"scale"] doubleValue], [floorJO[@"scale"] doubleValue], 0.0001);
    XCTAssertEqualObjects(jsonFloorFile[@"floorIdentifier"], jsonFloorFile[@"floorIdentifier"]);
    XCTAssertEqualObjects(jsonFloorFile[@"mapUrl"], jsonFloorFile[@"mapUrl"]);
    XCTAssertEqualObjects(jsonFloorFile[@"buildingIdentifier"], jsonFloorFile[@"buildingIdentifier"]);
    XCTAssertEqualObjects(jsonFloorFile[@"createdAt"], jsonFloorFile[@"createdAt"]);
    XCTAssertEqualObjects(jsonFloorFile[@"updatedAt"], jsonFloorFile[@"updatedAt"]);
}

- (void) assertIndication: (NSDictionary *) jsonIndicationFile : (NSDictionary *) indicationJO {
    XCTAssertEqualWithAccuracy([jsonIndicationFile[@"orientation"] doubleValue], [indicationJO[@"orientation"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonIndicationFile[@"stepIdxDestination"] doubleValue], [indicationJO[@"stepIdxDestination"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonIndicationFile[@"distance"] doubleValue], [indicationJO[@"distance"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonIndicationFile[@"stepIdxOrigin"] doubleValue], [indicationJO[@"stepIdxOrigin"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonIndicationFile[@"distanceToNextLevel"] doubleValue], [indicationJO[@"distanceToNextLevel"] doubleValue], 0.0001);
    XCTAssertEqualObjects(jsonIndicationFile[@"orientationType"], indicationJO[@"orientationType"]);
    XCTAssertEqualObjects(jsonIndicationFile[@"indicationType"], indicationJO[@"indicationType"]);
    XCTAssertEqualObjects(jsonIndicationFile[@"stepIdxOrigin"], indicationJO[@"stepIdxOrigin"]);
    XCTAssertEqualObjects(jsonIndicationFile[@"buildingIdentifier"], indicationJO[@"buildingIdentifier"]);
    XCTAssertEqualObjects(jsonIndicationFile[@"neededLevelChange"], indicationJO[@"neededLevelChange"]);
}

- (void) assertLocation: (NSDictionary *) jsonLocationFile : (NSDictionary *) locationJO {
    XCTAssertEqualObjects(jsonLocationFile[@"accuracy"], locationJO[@"accuracy"]);
    XCTAssertEqualObjects(jsonLocationFile[@"provider"], locationJO[@"provider"]);
    XCTAssertEqualObjects(jsonLocationFile[@"buildingIdentifier"], locationJO[@"buildingIdentifier"]);
    XCTAssertEqualObjects(jsonLocationFile[@"floorIdentifier"], locationJO[@"floorIdentifier"]);
    [self assertPoint:jsonLocationFile[@"position"]:locationJO[@"position"]];
    [self assertCoordinate:jsonLocationFile[@"coordinate"]:locationJO[@"coordinate"]];
    [self assertCartesianCoordinate:jsonLocationFile[@"cartesianCoordinate"]:locationJO[@"cartesianCoordinate"]];
    [self assertAngle:jsonLocationFile[@"cartesianBearing"]:locationJO[@"cartesianBearing"]];
    
    //always returning true when creating object from file due to ios sdk restriction in hasBearing functionality
    XCTAssertTrue(jsonLocationFile[@"hasBearing"]);
}

- (void) assertLocationStatus: (NSDictionary *) jsonLocationStatusFile : (NSDictionary *) locationStatusJO;
{
    XCTAssert([jsonLocationStatusFile[@"statusName"] isEqualToString: locationStatusJO[@"statusName"]]);
}

- (void) assertNavigationProgress: (NSDictionary *) jsonNavigationProgressFile : (NSDictionary *) navigationProgressJO;
{
    [self assertIndication:jsonNavigationProgressFile[@"currentIndication"]:navigationProgressJO[@"currentIndication"]];
    [self assertIndication:jsonNavigationProgressFile[@"nextIndication"]:navigationProgressJO[@"nextIndication"]];
    XCTAssertEqualWithAccuracy([jsonNavigationProgressFile[@"distanceToEndStep"] doubleValue], [navigationProgressJO[@"distanceToEndStep"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonNavigationProgressFile[@"distanceToClosestPointInRoute"] doubleValue], [navigationProgressJO[@"distanceToClosestPointInRoute"] doubleValue], 0.0001);
    XCTAssertEqualObjects(jsonNavigationProgressFile[@"closestPointInRoute"], jsonNavigationProgressFile[@"closestPointInRoute"]);
    XCTAssertEqualWithAccuracy([jsonNavigationProgressFile[@"timeToEndStep"] doubleValue], [navigationProgressJO[@"timeToEndStep"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonNavigationProgressFile[@"timeToGoal"] doubleValue], [navigationProgressJO[@"timeToGoal"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonNavigationProgressFile[@"currentStepIndex"] doubleValue], [navigationProgressJO[@"currentStepIndex"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonNavigationProgressFile[@"distanceToGoal"] doubleValue], [navigationProgressJO[@"distanceToGoal"] doubleValue], 0.0001);
}

- (void) assertPoi: (NSDictionary *) jsonPoiFile : (NSDictionary *) poiJO {
    XCTAssertEqualObjects(jsonPoiFile[@"identifier"], poiJO[@"identifier"]);
    [self assertCoordinate:jsonPoiFile[@"coordinate"]:poiJO[@"coordinate"]];
    XCTAssertEqualObjects(jsonPoiFile[@"poiName"], poiJO[@"poiName"]);
    XCTAssertEqualObjects(jsonPoiFile[@"customFields"], poiJO[@"customFields"]);
    XCTAssertEqualObjects(jsonPoiFile[@"isIndoor"], poiJO[@"isIndoor"]);
    XCTAssertEqualObjects(jsonPoiFile[@"infoHtml"], poiJO[@"infoHtml"]);
    XCTAssertEqualObjects(jsonPoiFile[@"buildingIdentifier"], poiJO[@"buildingIdentifier"]);
    XCTAssertEqualObjects(jsonPoiFile[@"isOutdoor"], poiJO[@"isOutdoor"]);
    XCTAssertEqualObjects(jsonPoiFile[@"createdAt"], poiJO[@"createdAt"]);
    XCTAssertEqualObjects(jsonPoiFile[@"floorIdentifier"], poiJO[@"floorIdentifier"]);
    [self assertCartesianCoordinate:jsonPoiFile[@"cartesianCoordinate"] :poiJO[@"cartesianCoordinate"]];
    [self assertPoint: jsonPoiFile[@"position"]:poiJO[@"position"]];
    XCTAssertEqualObjects(jsonPoiFile[@"category"], poiJO[@"category"]);
    XCTAssertEqualObjects(jsonPoiFile[@"updatedAt"], poiJO[@"updatedAt"]);
}

- (void) assertImage: (NSDictionary *) jsonImageFile : (NSDictionary *) imageJO {
    XCTAssert([jsonImageFile[@"data"] isEqualToString: imageJO[@"data"]]);
}

- (void) assertPoint: (NSDictionary *) jsonPointFile : (NSDictionary *) pointJO {
    [self assertCoordinate:jsonPointFile[@"coordinate"]:pointJO[@"coordinate"]];
    [self assertCartesianCoordinate:jsonPointFile[@"cartesianCoordinate"]:pointJO[@"cartesianCoordinate"]];
    XCTAssertEqualObjects(jsonPointFile[@"floorIdentifier"], pointJO[@"floorIdentifier"]);
    XCTAssert(jsonPointFile[@"isIndoor"] == pointJO[@"isIndoor"]);
    XCTAssertEqualObjects(jsonPointFile[@"buildingIdentifier"], pointJO[@"buildingIdentifier"]);
    XCTAssert(jsonPointFile[@"isOutdoor"] == pointJO[@"isOutdoor"]);
}

- (void) assertRoute: (NSDictionary *) jsonRouteFile : (NSDictionary *) routeJO {
    [self assertRouteStep:[jsonRouteFile[@"steps"] objectAtIndex:0]:[routeJO[@"steps"] objectAtIndex:0]];
    [self assertRouteStep:[jsonRouteFile[@"steps"] objectAtIndex:1]:[routeJO[@"steps"] objectAtIndex:1]];
    [self assertRouteStep:[jsonRouteFile[@"steps"] objectAtIndex:2]:[routeJO[@"steps"] objectAtIndex:2]];
    [self assertIndication:[jsonRouteFile[@"indications"] objectAtIndex:0]:[routeJO[@"indications"] objectAtIndex:0]];
    [self assertIndication:[jsonRouteFile[@"indications"] objectAtIndex:1]:[routeJO[@"indications"] objectAtIndex:1]];
    [self assertIndication:[jsonRouteFile[@"indications"] objectAtIndex:2]:[routeJO[@"indications"] objectAtIndex:2]];
    [self assertPoint:jsonRouteFile[@"from"]:routeJO[@"from"]];
    //TODO review TO vs to
    [self assertPoint:jsonRouteFile[@"TO"]:routeJO[@"to"]];
}

- (void) assertRouteStep: (NSDictionary *) jsonRouteStepFile : (NSDictionary *) routeStepJO;
{
    XCTAssertEqualObjects(jsonRouteStepFile[@"isFirst"], routeStepJO[@"isFirst"]);
    //TODO review constructor for SITRouteStep and stepDistance parameter passed
    XCTAssertEqualWithAccuracy([jsonRouteStepFile[@"distance"] doubleValue], [routeStepJO[@"distance"] doubleValue], 0.0001);
    XCTAssertEqualObjects(jsonRouteStepFile[@"isLast"], routeStepJO[@"isLast"]);
    XCTAssertEqualWithAccuracy([jsonRouteStepFile[@"distanceToGoal"] doubleValue], [routeStepJO[@"distanceToGoal"] doubleValue], 0.0001);
    XCTAssertEqualObjects(jsonRouteStepFile[@"id"], routeStepJO[@"id"]);
    [self assertPoint:jsonRouteStepFile[@"from"]:routeStepJO[@"from"]];
    [self assertPoint:jsonRouteStepFile[@"TO"]:routeStepJO[@"TO"]];
}

- (void) assertConversionArea:(NSDictionary *) jsonConversionAreaFile : (NSDictionary *) conversionAreaJO;
{
    XCTAssertEqualWithAccuracy([jsonConversionAreaFile[@"bottomLeft"][@"x"] doubleValue], [conversionAreaJO[@"bottomLeft"][@"x"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonConversionAreaFile[@"bottomLeft"][@"y"] doubleValue], [conversionAreaJO[@"bottomLeft"][@"y"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonConversionAreaFile[@"bottomRight"][@"x"] doubleValue], [conversionAreaJO[@"bottomRight"][@"x"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonConversionAreaFile[@"bottomRight"][@"y"] doubleValue], [conversionAreaJO[@"bottomRight"][@"y"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonConversionAreaFile[@"topLeft"][@"x"] doubleValue], [conversionAreaJO[@"topLeft"][@"x"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonConversionAreaFile[@"topLeft"][@"y"] doubleValue], [conversionAreaJO[@"topLeft"][@"y"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonConversionAreaFile[@"topRight"][@"x"] doubleValue], [conversionAreaJO[@"topRight"][@"x"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonConversionAreaFile[@"topRight"][@"y"] doubleValue], [conversionAreaJO[@"topRight"][@"y"] doubleValue], 0.0001);
    //TODO floorIdentifier is set in indoorPoint
    //XCTAssertEqualWithAccuracy([jsonConversionAreaFile[@"floorIdentifier"] doubleValue], [conversionAreaJO[@"floorIdentifier"] doubleValue], 0.0001);
}

- (void) assertCoordinate: (CLLocationCoordinate2D) coordinateA isEqualToCoordinate: (CLLocationCoordinate2D) coordinateB {
    XCTAssertEqualWithAccuracy(coordinateA.latitude, coordinateB.latitude, 0.001, @"Coordinate: latitude wasn't equal.");
    XCTAssertEqualWithAccuracy(coordinateA.longitude, coordinateB.longitude, 0.001, @"Coordinate: longitude wasn't equal.");
}

- (void) assertCartesianCoordinate: (SITCartesianCoordinate*) coordinateA isEqualToCartesianCoordinate: (SITCartesianCoordinate*) coordinateB {
    XCTAssertEqualWithAccuracy(coordinateA.x, coordinateB.x, 0.0001, @"SITCartesianCoordinate: X value wasn't equal.");
    XCTAssertEqualWithAccuracy(coordinateA.y, coordinateB.y, 0.0001, @"SITCartesianCoordinate: Y value wasn't equal.");
}

- (void) assertAngle: (SITAngle*) angleA isEqualToAngle: (SITAngle*) angleB {
    XCTAssertEqualWithAccuracy(angleA.degrees, angleB.degrees, 0.0001);
    XCTAssertEqualWithAccuracy(angleA.radians, angleB.radians, 0.0001);
    XCTAssertEqualWithAccuracy(angleA.degressClockwise, angleB.degressClockwise, 0.0001);
    XCTAssertEqualWithAccuracy(angleA.radiansMinusPiPi, angleB.radiansMinusPiPi, 0.0001);
}

- (void) assertPoint: (SITPoint*) pointA isEqualToPoint: (SITPoint*) pointB {
    [self assertCoordinate: pointA.coordinate isEqualToCoordinate: pointB.coordinate];
    [self assertCartesianCoordinate: pointA.cartesianCoordinate isEqualToCartesianCoordinate: pointB.cartesianCoordinate];
    XCTAssertEqualObjects(pointA.buildingIdentifier, pointB.buildingIdentifier, @"SITPoint: building identifier wasn't equal.");
    XCTAssertEqualObjects(pointA.floorIdentifier, pointB.floorIdentifier, @"SITPoint: floor identifier wasn't equal.");
    XCTAssertTrue(pointA.isIndoor == pointB.isIndoor, @"SITPoint: isIndoor bool wasn't equal.");
    XCTAssertTrue(pointA.isOutdoor == pointB.isOutdoor, @"SITPoint: isOutdoor bool  wasn't equal.");
}

- (void) assertLocation: (SITLocation *) locationA isEqualToLocation:(SITLocation *)locationB {
    XCTAssertEqualWithAccuracy(locationA.accuracy, locationB.accuracy, 0.001);
    XCTAssertEqualObjects(locationA.provider, locationB.provider);
    XCTAssertEqualObjects(locationA.deviceId, locationB.deviceId);
    XCTAssertEqual(locationA.quality, locationB.quality);
    XCTAssertEqual(locationA.hasBearing, locationB.hasBearing);
    [self assertPoint: locationA.position isEqualToPoint: locationB.position];
    [self assertAngle: locationA.bearing isEqualToAngle: locationB.bearing];
    [self assertAngle: locationA.cartesianBearing isEqualToAngle: locationB.cartesianBearing];
}

@end
