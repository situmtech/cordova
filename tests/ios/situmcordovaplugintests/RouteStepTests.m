//
//  RouteStepTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 13/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface RouteStepTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation RouteStepTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/routeStep";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testRouteStepWithCoordinate {
    SITRouteStep *routeStep1 = [SitumCreatorTests createRouteStepWithCoordinate];
    NSDictionary *routeStepJO1 = [SitumLocationWrapper.shared routeStepToJsonObject:routeStep1];
    NSString *fileName1 =  @"routeStep1";
    //read from json object in resources
    NSDictionary *jsonRouteStep1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _filePath];
    [_helper assertRouteStep: jsonRouteStep1: routeStepJO1];
}

- (void) testRouteStepWithCoordinateAndBuildingId {
    SITRouteStep *routeStep2 = [SitumCreatorTests createRouteStepWithCoordinateAndBuildingId];
    NSDictionary *routeStepJO2 = [SitumLocationWrapper.shared routeStepToJsonObject:routeStep2];
    NSString *fileName2 =  @"routeStep2";
    //read from json object in resources
    NSDictionary *jsonRouteStep2 = [TestingHelper dataFromJSONFileNamed: fileName2 inDirectory : _filePath];
    [_helper assertRouteStep: jsonRouteStep2: routeStepJO2];
}

- (void) testRouteStepWithCoordinateBuildingIdAndFloor {
    SITRouteStep *routeStep3 = [SitumCreatorTests createRouteStepWithCoordinateBuildingIdAndFloor];
    NSDictionary *routeStepJO3 = [SitumLocationWrapper.shared routeStepToJsonObject:routeStep3];
    NSString *fileName3 =  @"routeStep3";
    //read from json object in resources
    NSDictionary *jsonRouteStep3 = [TestingHelper dataFromJSONFileNamed: fileName3 inDirectory : _filePath];
    [_helper assertRouteStep: jsonRouteStep3: routeStepJO3];
}

- (void) testRouteStepWithBuildingWithAngleFromDegree {
    SITRouteStep *routeStep4 = [SitumCreatorTests createRouteStepWithBuildingWithAngleFromDegree];
    NSDictionary *routeStepJO4 = [SitumLocationWrapper.shared routeStepToJsonObject:routeStep4];
    NSString *fileName4 =  @"routeStep4";
    //read from json object in resources
    NSDictionary *jsonRouteStep4 = [TestingHelper dataFromJSONFileNamed: fileName4 inDirectory : _filePath];
    [_helper assertRouteStep: jsonRouteStep4: routeStepJO4];
}

- (void) testRouteStepWithBuildingWithAngleFromRadians {
    SITRouteStep *routeStep5 = [SitumCreatorTests createRouteStepWithBuildingWithAngleFromRadians];
    NSDictionary *routeStepJO5 = [SitumLocationWrapper.shared routeStepToJsonObject:routeStep5];
    NSString *fileName5 =  @"routeStep5";
    //read from json object in resources
    NSDictionary *jsonRouteStep5 = [TestingHelper dataFromJSONFileNamed: fileName5 inDirectory : _filePath];
    [_helper assertRouteStep: jsonRouteStep5: routeStepJO5];
}

@end
