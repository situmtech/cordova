//
//  SitumTests.m
//
//
//  Created by Situm 01-07-2018.
//  Copyright © 2018 Situm. All rights reserved.
//

#import "SitumTests.h"

@implementation SitumTests


//utility method to assert an angle object
- (void) assertAngle: (NSDictionary *) jsonAngleFile : (NSDictionary *) angleJO;
{
    XCTAssertEqualWithAccuracy([jsonAngleFile[@"radiansMinusPiPi"] doubleValue], [angleJO[@"radiansMinusPiPi"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonAngleFile[@"radians"] doubleValue], [angleJO[@"radians"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonAngleFile[@"degreesClockwise"] doubleValue], [angleJO[@"degreesClockwise"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonAngleFile[@"degrees"] doubleValue], [angleJO[@"degrees"] doubleValue], 0.0001);
}

//utility method to assert a bound object
- (void) assertBound: (NSDictionary *) jsonBoundFile : (NSDictionary *) boundJO;
{
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"northWest"][@"latitude"] doubleValue], [boundJO[@"northWest"][@"latitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"northWest"][@"longitude"] doubleValue], [boundJO[@"northWest"][@"longitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"northEast"][@"latitude"] doubleValue], [boundJO[@"northEast"][@"latitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"northEast"][@"longitude"] doubleValue], [boundJO[@"northEast"][@"longitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"southWest"][@"latitude"] doubleValue], [boundJO[@"southWest"][@"latitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"southWest"][@"longitude"] doubleValue], [boundJO[@"southWest"][@"longitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"southEast"][@"latitude"] doubleValue], [boundJO[@"southEast"][@"latitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"southEast"][@"longitude"] doubleValue], [boundJO[@"southEast"][@"longitude"] doubleValue], 0.0001);
}

//utility method to assert a Cartesian Coordinate
- (void) assertCartesianCoordinate: (NSDictionary *) jsonCartesianCoordinateFile : (NSDictionary *) cartesianCoordinateJO;
{
    XCTAssertEqualWithAccuracy([jsonCartesianCoordinateFile[@"x"] doubleValue], [cartesianCoordinateJO[@"x"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonCartesianCoordinateFile[@"y"] doubleValue], [cartesianCoordinateJO[@"y"] doubleValue], 0.0001);
}

//utility method to assert a Coordinate
- (void) assertCoordinate: (NSDictionary *) jsonCoordinateFile : (NSDictionary *) coordinateJO;
{
    XCTAssertEqualWithAccuracy([jsonCoordinateFile[@"longitude"] doubleValue], [coordinateJO[@"longitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonCoordinateFile[@"latitude"] doubleValue], [coordinateJO[@"latitude"] doubleValue], 0.0001);
}

//utility method to assert a location object
- (void) assertLocation: (NSDictionary *) jsonLocationFile : (NSDictionary *) locationJO;
{
    XCTAssertEqualObjects(jsonLocationFile[@"accuracy"], locationJO[@"accuracy"]);
    XCTAssertEqualObjects(jsonLocationFile[@"provider"], locationJO[@"provider"]);
    //always returning true when creating object from file due to ios sdk restriction in hasBearing functionality
    XCTAssertTrue(jsonLocationFile[@"hasBearing"]);
}

//utility method to assert a Cartesian Coordinate
- (void) assertDimension: (NSDictionary *) jsonDimensionFile : (NSDictionary *) dimensionJO;
{
    XCTAssertEqualWithAccuracy([jsonDimensionFile[@"width"] doubleValue], [dimensionJO[@"width"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonDimensionFile[@"height"] doubleValue], [dimensionJO[@"height"] doubleValue], 0.0001);
}

//utility method to assert a floor
- (void) assertFloor: (NSDictionary *) jsonFloorFile : (NSDictionary *) floorJO;
{
    //XCTAssertEqualWithAccuracy([jsonFloorFile[@"altitude"] doubleValue], [floorJO[@"altitude"] doubleValue], 0.0001);
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

    
    /*{
        "orientationType": "STRAIGHT",
        "orientation": 14.5,
        "stepIdxDestination": 5,
        "distance": 5,
        "stepIdxOrigin": 4,
        "indicationType": "GO_AHEAD",
        "distanceToNextLevel": 16,
        "neededLevelChange": true
    }*/
}

- (void)setUp {
    [super setUp];
    // Put setup code here. This method is called before the invocation of each test method in the class.
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testAngle {
    NSString *filePath = @"resources/angle";
    // ### ANGLE1.JSON ###
    SITAngle *angle1 = [SitumCreatorTests createAngleFromDegrees];
    NSDictionary *angleJO1 = [SitumLocationWrapper.shared angleToJsonObject:angle1];
    NSString *fileName1 =  @"angle1";
    //read from json object in resources
    NSDictionary *jsonAngle1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : filePath];
    [self assertAngle: jsonAngle1: angleJO1];
    
    // ### ANGLE2.JSON ###
    SITAngle *angle2 = [SitumCreatorTests createAngleFromRadians];
    NSDictionary *angleJO2 = [SitumLocationWrapper.shared angleToJsonObject:angle2];
    NSString *fileName2 =  @"angle2";
    //read from json object in resources
    NSDictionary *jsonAngle2 = [TestingHelper dataFromJSONFileNamed: fileName2 inDirectory : filePath];
    [self assertAngle: jsonAngle2: angleJO2];
}

- (void) testBound {
    NSString *filePath = @"resources/bounds";
    // ### BOUNDS1.JSON ###
    SITBounds bound1 = [SitumCreatorTests createBoundsWithArray];
    NSDictionary *boundJO1 = [SitumLocationWrapper.shared boundsToJsonObject:bound1];
    NSString *fileName1 =  @"bounds1";
    //read from json object in resources
    NSDictionary *jsonBound1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : filePath];
    [self assertBound: jsonBound1: boundJO1];
    
    // ### BOUNDS2.JSON ###
    SITBounds bound2 = [SitumCreatorTests createBounds];
    NSDictionary *boundJO2 = [SitumLocationWrapper.shared boundsToJsonObject:bound2];
    NSString *fileName2 =  @"bounds2";
    //read from json object in resources
    NSDictionary *jsonBound2 = [TestingHelper dataFromJSONFileNamed: fileName2 inDirectory : filePath];
    [self assertBound: jsonBound2: boundJO2];
}

- (void) testCartesianCoordinate {
    NSString *filePath = @"resources/cartesianCoordinate";
    // ### CARTESIANCOORDINATE1.JSON ###
    SITCartesianCoordinate *cartesianCoordinate1 = [SitumCreatorTests createCartesianCoordinate];
    NSDictionary *cartesianCoordinateJO1 = [SitumLocationWrapper.shared cartesianCoordinateToJsonObject:cartesianCoordinate1];
    NSString *fileName1 =  @"cartesianCoordinate1";
    //read from json object in resources
    NSDictionary *jsoncartesianCoordinate1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : filePath];
    [self assertCartesianCoordinate: jsoncartesianCoordinate1: cartesianCoordinateJO1];
}

- (void) testCoordinate {
    NSString *filePath = @"resources/coordinate";
    // ### COORDINATE1.JSON ###
    CLLocationCoordinate2D coordinate1 = [SitumCreatorTests createCoordinate];
    NSDictionary *coordinateJO1 = [SitumLocationWrapper.shared coordinateToJsonObject:coordinate1];
    NSString *fileName1 =  @"coordinate1";
    //read from json object in resources
    NSDictionary *jsonCoordinate1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : filePath];
    [self assertCoordinate: jsonCoordinate1: coordinateJO1];
}

- (void) testDimension {
    NSString *filePath = @"resources/dimensions";
    // ### DIMENSIONS1.JSON ###
    SITDimensions *dimension1 = [SitumCreatorTests createDimensions];
    NSDictionary *dimensionJO1 = [SitumLocationWrapper.shared dimensionsToJsonObject:dimension1];
    NSString *fileName1 =  @"dimensions1";
    //read from json object in resources
    NSDictionary *jsonDimension1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : filePath];
    [self assertDimension: jsonDimension1: dimensionJO1];
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


@end
