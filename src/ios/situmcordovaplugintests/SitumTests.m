//
//  SitumTests.m
//
//
//  Created by Situm 01-07-2018.
//  Copyright © 2018 Situm. All rights reserved.
//

#import "SitumTests.h"

@implementation SitumTests


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


- (void) testRouteStep1 {
    SITRouteStep *routeStep1 = [SitumCreatorTests createRouteStepWithCoordinate];
    NSDictionary *routeStepJO1 = [SitumLocationWrapper.shared routeStepToJsonObject:routeStep1];
    NSString *fileName1 =  @"routeStep1";
    //read from json object in resources
    NSDictionary *jsonRouteStep1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _stepFilePath];
    [_helper assertRouteStep: jsonRouteStep1: routeStepJO1];
}

- (void) testRouteStep2 {
    SITRouteStep *routeStep2 = [SitumCreatorTests createRouteStepWithCoordinateAndBuildingId];
    NSDictionary *routeStepJO2 = [SitumLocationWrapper.shared routeStepToJsonObject:routeStep2];
    NSString *fileName2 =  @"routeStep2";
    //read from json object in resources
    NSDictionary *jsonRouteStep2 = [TestingHelper dataFromJSONFileNamed: fileName2 inDirectory : _stepFilePath];
    [_helper assertRouteStep: jsonRouteStep2: routeStepJO2];
}

- (void) testRouteStep3 {
    SITRouteStep *routeStep3 = [SitumCreatorTests createRouteStepWithCoordinateBuildingIdAndFloor];
    NSDictionary *routeStepJO3 = [SitumLocationWrapper.shared routeStepToJsonObject:routeStep3];
    NSString *fileName3 =  @"routeStep3";
    //read from json object in resources
    NSDictionary *jsonRouteStep3 = [TestingHelper dataFromJSONFileNamed: fileName3 inDirectory : _stepFilePath];
    [_helper assertRouteStep: jsonRouteStep3: routeStepJO3];
}

- (void) testRouteStep4 {
    SITRouteStep *routeStep4 = [SitumCreatorTests createRouteStepWithBuildingWithAngleFromDegree];
    NSDictionary *routeStepJO4 = [SitumLocationWrapper.shared routeStepToJsonObject:routeStep4];
    NSString *fileName4 =  @"routeStep4";
    //read from json object in resources
    NSDictionary *jsonRouteStep4 = [TestingHelper dataFromJSONFileNamed: fileName4 inDirectory : _stepFilePath];
    [_helper assertRouteStep: jsonRouteStep4: routeStepJO4];
}

- (void) testRouteStep5 {
    SITRouteStep *routeStep5 = [SitumCreatorTests createRouteStepWithBuildingWithAngleFromRadians];
    NSDictionary *routeStepJO5 = [SitumLocationWrapper.shared routeStepToJsonObject:routeStep5];
    NSString *fileName5 =  @"routeStep5";
    //read from json object in resources
    NSDictionary *jsonRouteStep5 = [TestingHelper dataFromJSONFileNamed: fileName5 inDirectory : _stepFilePath];
    [_helper assertRouteStep: jsonRouteStep5: routeStepJO5];
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
