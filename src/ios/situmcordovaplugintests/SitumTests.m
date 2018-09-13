//
//  SitumTests.m
//
//
//  Created by Situm 01-07-2018.
//  Copyright © 2018 Situm. All rights reserved.
//

#import "SitumTests.h"

@implementation SitumTests

//utility method to assert a Cartesian Coordinate
- (void) assertEvent: (NSDictionary *) jsonEventFile : (NSDictionary *) eventJO;
{
    XCTAssertEqualWithAccuracy([jsonEventFile[@"identifier"] doubleValue], [eventJO[@"identifier"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonEventFile[@"buildingIdentifier"] doubleValue], [eventJO[@"buildingIdentifier"] doubleValue], 0.0001);
    XCTAssertEqualObjects(jsonEventFile[@"infoHtml"], eventJO[@"infoHtml"]);
}

//utility method to assert a floor
- (void) assertFloor: (NSDictionary *) jsonFloorFile : (NSDictionary *) floorJO;
{
    XCTAssertEqualWithAccuracy([jsonFloorFile[@"altitude"] doubleValue], [floorJO[@"altitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonFloorFile[@"level"] doubleValue], [floorJO[@"level"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonFloorFile[@"scale"] doubleValue], [floorJO[@"scale"] doubleValue], 0.0001);
    XCTAssertEqualObjects(jsonFloorFile[@"floorIdentifier"], jsonFloorFile[@"floorIdentifier"]);
    XCTAssertEqualObjects(jsonFloorFile[@"mapUrl"], jsonFloorFile[@"mapUrl"]);
    XCTAssertEqualObjects(jsonFloorFile[@"buildingIdentifier"], jsonFloorFile[@"buildingIdentifier"]);
}

//utility method to assert an indication
- (void) assertIndication: (NSDictionary *) jsonIndicationFile : (NSDictionary *) indicationJO;
{
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

//utility method to assert a location object
- (void) assertLocation: (NSDictionary *) jsonLocationFile : (NSDictionary *) locationJO;
{
    XCTAssertEqualObjects(jsonLocationFile[@"accuracy"], locationJO[@"accuracy"]);
    XCTAssertEqualObjects(jsonLocationFile[@"provider"], locationJO[@"provider"]);
    XCTAssertEqualObjects(jsonLocationFile[@"buildingIdentifier"], locationJO[@"buildingIdentifier"]);
    XCTAssertEqualObjects(jsonLocationFile[@"floorIdentifier"], locationJO[@"floorIdentifier"]);
    [_helper assertPoint:jsonLocationFile[@"position"]:locationJO[@"position"]];
    [_helper assertCoordinate:jsonLocationFile[@"coordinate"]:locationJO[@"coordinate"]];
    [_helper assertCartesianCoordinate:jsonLocationFile[@"cartesianCoordinate"]:locationJO[@"cartesianCoordinate"]];
    [_helper assertAngle:jsonLocationFile[@"cartesianBearing"]:locationJO[@"cartesianBearing"]];

    //always returning true when creating object from file due to ios sdk restriction in hasBearing functionality
    XCTAssertTrue(jsonLocationFile[@"hasBearing"]);
}

//utility method to assert a Coordinate
- (void) assertLocationStatus: (NSDictionary *) jsonLocationStatusFile : (NSDictionary *) locationStatusJO;
{
    XCTAssert([jsonLocationStatusFile[@"statusName"] isEqualToString: locationStatusJO[@"statusName"]]);
}

//utility method to assert a NavigationProgress
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

//utility method to assert a Point


//utility method to assert a Route
- (void) assertRoute: (NSDictionary *) jsonRouteFile : (NSDictionary *) routeJO;
{
    [self assertRouteStep:[jsonRouteFile[@"steps"] objectAtIndex:0]:[routeJO[@"steps"] objectAtIndex:0]];
    [self assertRouteStep:[jsonRouteFile[@"steps"] objectAtIndex:1]:[routeJO[@"steps"] objectAtIndex:1]];
    [self assertRouteStep:[jsonRouteFile[@"steps"] objectAtIndex:2]:[routeJO[@"steps"] objectAtIndex:2]];
    [self assertIndication:[jsonRouteFile[@"indications"] objectAtIndex:0]:[routeJO[@"indications"] objectAtIndex:0]];
    [self assertIndication:[jsonRouteFile[@"indications"] objectAtIndex:1]:[routeJO[@"indications"] objectAtIndex:1]];
    [self assertIndication:[jsonRouteFile[@"indications"] objectAtIndex:2]:[routeJO[@"indications"] objectAtIndex:2]];
    [_helper assertPoint:jsonRouteFile[@"from"]:routeJO[@"from"]];
    //TODO review TO vs to
    [_helper assertPoint:jsonRouteFile[@"TO"]:routeJO[@"to"]];
}

//utility method to assert a Route Step
- (void) assertRouteStep: (NSDictionary *) jsonRouteStepFile : (NSDictionary *) routeStepJO;
{
    XCTAssertEqualObjects(jsonRouteStepFile[@"isFirst"], routeStepJO[@"isFirst"]);
    //TODO review constructor for SITRouteStep and stepDistance parameter passed
    XCTAssertEqualWithAccuracy([jsonRouteStepFile[@"distance"] doubleValue], [routeStepJO[@"distance"] doubleValue], 0.0001);
    XCTAssertEqualObjects(jsonRouteStepFile[@"isLast"], routeStepJO[@"isLast"]);
    XCTAssertEqualWithAccuracy([jsonRouteStepFile[@"distanceToGoal"] doubleValue], [routeStepJO[@"distanceToGoal"] doubleValue], 0.0001);
    XCTAssertEqualObjects(jsonRouteStepFile[@"id"], routeStepJO[@"id"]);
    [_helper assertPoint:jsonRouteStepFile[@"from"]:routeStepJO[@"from"]];
    [_helper assertPoint:jsonRouteStepFile[@"TO"]:routeStepJO[@"TO"]];
}

//utility method to assert a ConversionArea
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

- (void)setUp {
    [super setUp];
    _helper = [TestingHelper new];
    _stepFilePath = @"resources/routeStep";
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testEvent {
    NSString *filePath = @"resources/events";
    // ### EVENT1.JSON ###
    SITEvent *event1 = [SitumCreatorTests createEvent];
    NSDictionary *eventJO1 = [SitumLocationWrapper.shared eventToJsonObject:event1];
    NSString *fileName1 =  @"event1";
    //read from json object in resources
    NSDictionary *jsonEvent1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : filePath];
    [self assertEvent: jsonEvent1: eventJO1];
}

- (void) testFloor {
    NSString *filePath = @"resources/floor";
    // ### FLOOR1.JSON ###
    SITFloor *floor1 = [SitumCreatorTests createFloorWithAltitude];
    NSDictionary *floorJO1 = [SitumLocationWrapper.shared floorToJsonObject:floor1];
    NSString *fileName1 =  @"floor1";
    //read from json object in resources
    NSDictionary *jsonFloor1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : filePath];
    [self assertFloor: jsonFloor1: floorJO1];
    
    // ### FLOOR2.JSON ###
    SITFloor *floor2 = [SitumCreatorTests createFloorWithoutAltitude];
    NSDictionary *floorJO2 = [SitumLocationWrapper.shared floorToJsonObject:floor2];
    NSString *fileName2 =  @"floor2";
    //read from json object in resources
    NSDictionary *jsonFloor2 = [TestingHelper dataFromJSONFileNamed: fileName2 inDirectory : filePath];
    [self assertFloor: jsonFloor2: floorJO2];
}

- (void) testIndication {
    NSString *filePath = @"resources/indication";
    // ### INDICATION1.JSON ###
    SITIndication *indication1 = [SitumCreatorTests createIndication];
    NSDictionary *indicationJO1 = [SitumLocationWrapper.shared indicationToJsonObject:indication1];
    NSString *fileName1 =  @"indication1";
    //read from json object in resources
    NSDictionary *jsonIndication1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : filePath];
    [self assertIndication: jsonIndication1: indicationJO1];
}

- (void)testLocation {
    NSString *filePath = @"resources/location";
    // ### LOCATION1.JSON ###
    SITLocation *location1 = [SitumCreatorTests createLocationWithBuildingFloorAndCartesianCoordinates];
    NSDictionary *locationJO1 = [SitumLocationWrapper.shared locationToJsonObject:location1];
    NSString *fileName1 =  @"location1";
    //read from json object in resources
    NSDictionary *jsonLocation1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : filePath];
    [self assertLocation: jsonLocation1: locationJO1];
    
    // ### LOCATION2.JSON ###
    SITLocation *location2 = [SitumCreatorTests createLocationWithCoordinate];
    NSDictionary *locationJO2 = [SitumLocationWrapper.shared locationToJsonObject:location2];
    NSString *fileName2 =  @"location2";
    NSDictionary *jsonLocation2 = [TestingHelper dataFromJSONFileNamed: fileName2 inDirectory : filePath];
    [self assertLocation: jsonLocation2: locationJO2];
    
    // ### LOCATION3.JSON ###
    SITLocation *location3 = [SitumCreatorTests createLocationWithBuildingAndCoordinate];
    NSDictionary *locationJO3 = [SitumLocationWrapper.shared locationToJsonObject:location3];
    NSString *fileName3 =  @"location3";
    NSDictionary *jsonLocation3 = [TestingHelper dataFromJSONFileNamed: fileName3 inDirectory : filePath];
    [self assertLocation: jsonLocation3: locationJO3];
    
    // ### LOCATION4.JSON ###
    SITLocation *location4 = [SitumCreatorTests locationWithCartesianBearing];
    NSDictionary *locationJO4 = [SitumLocationWrapper.shared locationToJsonObject:location4];
    NSString *fileName4 =  @"location4";
    NSDictionary *jsonLocation4 = [TestingHelper dataFromJSONFileNamed: fileName4 inDirectory : filePath];
    [self assertLocation: jsonLocation4: locationJO4];
    
    // ### LOCATION5.JSON ###
    SITLocation *location5 = [SitumCreatorTests locationWithoutCartesianBearing];
    NSDictionary *locationJO5 = [SitumLocationWrapper.shared locationToJsonObject:location5];
    NSString *fileName5 =  @"location5";
    NSDictionary *jsonLocation5 = [TestingHelper dataFromJSONFileNamed: fileName5 inDirectory : filePath];
    [self assertLocation: jsonLocation5: locationJO5];
    
    // ### LOCATION6.JSON ###
    SITLocation *location6 = [SitumCreatorTests locationWithBearing];
    NSDictionary *locationJO6 = [SitumLocationWrapper.shared locationToJsonObject:location6];
    NSString *fileName6 =  @"location6";
    NSDictionary *jsonLocation6 = [TestingHelper dataFromJSONFileNamed: fileName6 inDirectory : filePath];
    [self assertLocation: jsonLocation6: locationJO6];
    
    // ### LOCATION7.JSON ###
    SITLocation *location7 = [SitumCreatorTests locationWithouthBearing];
    NSDictionary *locationJO7 = [SitumLocationWrapper.shared locationToJsonObject:location7];
    NSString *fileName7 =  @"location7";
    NSDictionary *jsonLocation7 = [TestingHelper dataFromJSONFileNamed: fileName7 inDirectory : filePath];
    [self assertLocation: jsonLocation7: locationJO7];
    
    // ### LOCATION8.JSON ###
    SITLocation *location8 = [SitumCreatorTests indoorLocationWithIndoorBearingQualityLow];
    NSDictionary *locationJO8 = [SitumLocationWrapper.shared locationToJsonObject:location8];
    NSString *fileName8 =  @"location8";
    NSDictionary *jsonLocation8 = [TestingHelper dataFromJSONFileNamed: fileName8 inDirectory : filePath];
    [self assertLocation: jsonLocation8: locationJO8];
    
    // ### LOCATION9.JSON ###
    SITLocation *location9 = [SitumCreatorTests indoorLocationWithIndoorBearingQualityHigh];
    NSDictionary *locationJO9 = [SitumLocationWrapper.shared locationToJsonObject:location9];
    NSString *fileName9 =  @"location9";
    NSDictionary *jsonLocation9 = [TestingHelper dataFromJSONFileNamed: fileName9 inDirectory : filePath];
    [self assertLocation: jsonLocation9: locationJO9];
    
    // ### LOCATION10.JSON ###
    
    SITLocation *location10 = [SitumCreatorTests outdoorLocation];
    NSDictionary *locationJO10 = [SitumLocationWrapper.shared locationToJsonObject:location10];
    NSString *fileName10 =  @"location10";
    NSDictionary *jsonLocation10 = [TestingHelper dataFromJSONFileNamed: fileName10 inDirectory : filePath];
    [self assertLocation: jsonLocation10: locationJO10];
     
}

- (void)testLocationStatus {
    NSString *filePath = @"resources/locationStatus";
    // ### LOCATIONSTATUS1.JSON ###
    SITLocationState locationStatus1 = [SitumCreatorTests createLocationStatusStarting];
    NSDictionary *locationStatusJO1 = [SitumLocationWrapper.shared locationStateToJsonObject:locationStatus1];
    NSString *fileName1 =  @"locationStatus1";
    //read from json object in resources
    NSDictionary *jsonLocationStatus1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : filePath];
    [self assertLocationStatus: jsonLocationStatus1: locationStatusJO1];

    // ### LOCATIONSTATUS3.JSON ###
    SITLocationState locationStatus3 = [SitumCreatorTests createLocationStatusCalculating];
    NSDictionary *locationStatusJO3 = [SitumLocationWrapper.shared locationStateToJsonObject:locationStatus3];
    NSString *fileName3 =  @"locationStatus3";
    //read from json object in resources
    NSDictionary *jsonLocationStatus3 = [TestingHelper dataFromJSONFileNamed: fileName3 inDirectory : filePath];
    [self assertLocationStatus: jsonLocationStatus3: locationStatusJO3];

    // ### LOCATIONSTATUS13.JSON ###
    SITLocationState locationStatus13 = [SitumCreatorTests createLocationStatusUserNotInBuilding];
    NSDictionary *locationStatusJO13 = [SitumLocationWrapper.shared locationStateToJsonObject:locationStatus13];
    NSString *fileName13 =  @"locationStatus13";
    //read from json object in resources
    NSDictionary *jsonLocationStatus13 = [TestingHelper dataFromJSONFileNamed: fileName13 inDirectory : filePath];
    [self assertLocationStatus: jsonLocationStatus13: locationStatusJO13];
}

- (void) testNavigationProgress {
    NSString *filePath = @"resources/navigationProgress";
    // ### NAVIGATIONPROGRESS1.JSON ###
    SITNavigationProgress *navigationProgress1 = [SitumCreatorTests createNavigationProgressOutdoor];
    NSDictionary *navigationProgressJO1 = [SitumLocationWrapper.shared navigationProgressToJsonObject:navigationProgress1];
    NSString *fileName1 =  @"navigationProgress1";
    //read from json object in resources
    NSDictionary *jsonNavigationProgress1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : filePath];
    [self assertNavigationProgress: jsonNavigationProgress1: navigationProgressJO1];
    // ### NAVIGATIONPROGRESS2.JSON ###
    SITNavigationProgress *navigationProgress2 = [SitumCreatorTests createNavigationProgressIndoor];
    NSDictionary *navigationProgressJO2 = [SitumLocationWrapper.shared navigationProgressToJsonObject:navigationProgress2];
    NSString *fileName2 =  @"navigationProgress2";
    //read from json object in resources
    NSDictionary *jsonNavigationProgress2 = [TestingHelper dataFromJSONFileNamed: fileName2 inDirectory : filePath];
    [self assertNavigationProgress: jsonNavigationProgress2: navigationProgressJO2];
}

- (void) testPoiCategory {
    NSString *filePath = @"resources/poiCategory";
    // ### POICATEGORY1.JSON ###
    SITPOICategory *poiCategory1 = [SitumCreatorTests createPoiCategory];
    NSDictionary *poiCategoryJO1 = [SitumLocationWrapper.shared poiCategoryToJsonObject:poiCategory1];
    NSString *fileName1 =  @"poiCategory1";
    //read from json object in resources
    NSDictionary *jsonPoiCategory1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : filePath];
    [_helper assertPoint: jsonPoiCategory1: poiCategoryJO1];
}



- (void) testRoute {
    NSString *filePath = @"resources/route";
    // ### ROUTE1.JSON ###
    SITRoute *route1 = [SitumCreatorTests createRouteBuildingWithDegreesPointWithCoordinates];
    NSDictionary *routeJO1 = [SitumLocationWrapper.shared routeToJsonObject:route1];
    NSString *fileName1 =  @"route1";
    //read from json object in resources
    NSDictionary *jsonRoute1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : filePath];
    [self assertRoute: jsonRoute1: routeJO1];
 }

- (void) testRouteStep1 {
    SITRouteStep *routeStep1 = [SitumCreatorTests createRouteStepWithCoordinate];
    NSDictionary *routeStepJO1 = [SitumLocationWrapper.shared routeStepToJsonObject:routeStep1];
    NSString *fileName1 =  @"routeStep1";
    //read from json object in resources
    NSDictionary *jsonRouteStep1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _stepFilePath];
    [self assertRouteStep: jsonRouteStep1: routeStepJO1];
}

- (void) testRouteStep2 {
    SITRouteStep *routeStep2 = [SitumCreatorTests createRouteStepWithCoordinateAndBuildingId];
    NSDictionary *routeStepJO2 = [SitumLocationWrapper.shared routeStepToJsonObject:routeStep2];
    NSString *fileName2 =  @"routeStep2";
    //read from json object in resources
    NSDictionary *jsonRouteStep2 = [TestingHelper dataFromJSONFileNamed: fileName2 inDirectory : _stepFilePath];
    [self assertRouteStep: jsonRouteStep2: routeStepJO2];
}

- (void) testRouteStep3 {
    SITRouteStep *routeStep3 = [SitumCreatorTests createRouteStepWithCoordinateBuildingIdAndFloor];
    NSDictionary *routeStepJO3 = [SitumLocationWrapper.shared routeStepToJsonObject:routeStep3];
    NSString *fileName3 =  @"routeStep3";
    //read from json object in resources
    NSDictionary *jsonRouteStep3 = [TestingHelper dataFromJSONFileNamed: fileName3 inDirectory : _stepFilePath];
    [self assertRouteStep: jsonRouteStep3: routeStepJO3];
}

- (void) testRouteStep4 {
    SITRouteStep *routeStep4 = [SitumCreatorTests createRouteStepWithBuildingWithAngleFromDegree];
    NSDictionary *routeStepJO4 = [SitumLocationWrapper.shared routeStepToJsonObject:routeStep4];
    NSString *fileName4 =  @"routeStep4";
    //read from json object in resources
    NSDictionary *jsonRouteStep4 = [TestingHelper dataFromJSONFileNamed: fileName4 inDirectory : _stepFilePath];
    [self assertRouteStep: jsonRouteStep4: routeStepJO4];
}

- (void) testRouteStep5 {
    SITRouteStep *routeStep5 = [SitumCreatorTests createRouteStepWithBuildingWithAngleFromRadians];
    NSDictionary *routeStepJO5 = [SitumLocationWrapper.shared routeStepToJsonObject:routeStep5];
    NSString *fileName5 =  @"routeStep5";
    //read from json object in resources
    NSDictionary *jsonRouteStep5 = [TestingHelper dataFromJSONFileNamed: fileName5 inDirectory : _stepFilePath];
    [self assertRouteStep: jsonRouteStep5: routeStepJO5];
}

- (void) testSitumConversionArea {
    NSString *filePath = @"resources/situmConversionArea";
    // ### POINT1.JSON ###
    SITRectangularArea *conversionArea1 = [SitumCreatorTests createSitumConversionArea];
    NSDictionary *conversionAreaJO1 = [SitumLocationWrapper.shared conversionAreaToJsonObject:conversionArea1];
    NSString *fileName1 =  @"situmConversionArea1";
    //read from json object in resources
    NSDictionary *jsonConversionArea1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : filePath];
    [self assertConversionArea: jsonConversionArea1: conversionAreaJO1];
}

@end
