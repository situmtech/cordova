//
//  LocationTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 13/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface LocationTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation LocationTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/location";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void)testLocation1 {
    SITLocation *location1 = [SitumCreatorTests createLocationWithBuildingFloorAndCartesianCoordinates];
    NSDictionary *locationJO1 = [SitumLocationWrapper.shared locationToJsonObject:location1];
    NSString *fileName1 =  @"location1";
    //read from json object in resources
    NSDictionary *jsonLocation1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _filePath];
    [_helper assertLocation: jsonLocation1: locationJO1];
}

- (void) testLocation2 {
    SITLocation *location2 = [SitumCreatorTests createLocationWithCoordinate];
    NSDictionary *locationJO2 = [SitumLocationWrapper.shared locationToJsonObject:location2];
    NSString *fileName2 =  @"location2";
    NSDictionary *jsonLocation2 = [TestingHelper dataFromJSONFileNamed: fileName2 inDirectory : _filePath];
    [_helper assertLocation: jsonLocation2: locationJO2];
}

- (void) testLocation3 {
    SITLocation *location3 = [SitumCreatorTests createLocationWithBuildingAndCoordinate];
    NSDictionary *locationJO3 = [SitumLocationWrapper.shared locationToJsonObject:location3];
    NSString *fileName3 =  @"location3";
    NSDictionary *jsonLocation3 = [TestingHelper dataFromJSONFileNamed: fileName3 inDirectory : _filePath];
    [_helper assertLocation: jsonLocation3: locationJO3];
}

- (void) testLocation4 {
    SITLocation *location4 = [SitumCreatorTests locationWithCartesianBearing];
    NSDictionary *locationJO4 = [SitumLocationWrapper.shared locationToJsonObject:location4];
    NSString *fileName4 =  @"location4";
    NSDictionary *jsonLocation4 = [TestingHelper dataFromJSONFileNamed: fileName4 inDirectory : _filePath];
    [_helper assertLocation: jsonLocation4: locationJO4];
}

- (void) testLocation5 {
    SITLocation *location5 = [SitumCreatorTests locationWithoutCartesianBearing];
    NSDictionary *locationJO5 = [SitumLocationWrapper.shared locationToJsonObject:location5];
    NSString *fileName5 =  @"location5";
    NSDictionary *jsonLocation5 = [TestingHelper dataFromJSONFileNamed: fileName5 inDirectory : _filePath];
    [_helper assertLocation: jsonLocation5: locationJO5];
}

- (void) testLocation6 {
    SITLocation *location6 = [SitumCreatorTests locationWithBearing];
    NSDictionary *locationJO6 = [SitumLocationWrapper.shared locationToJsonObject:location6];
    NSString *fileName6 =  @"location6";
    NSDictionary *jsonLocation6 = [TestingHelper dataFromJSONFileNamed: fileName6 inDirectory : _filePath];
    [_helper assertLocation: jsonLocation6: locationJO6];
}

- (void) testLocation7 {
    SITLocation *location7 = [SitumCreatorTests locationWithouthBearing];
    NSDictionary *locationJO7 = [SitumLocationWrapper.shared locationToJsonObject:location7];
    NSString *fileName7 =  @"location7";
    NSDictionary *jsonLocation7 = [TestingHelper dataFromJSONFileNamed: fileName7 inDirectory : _filePath];
    [_helper assertLocation: jsonLocation7: locationJO7];
}

- (void) testLocation8 {
    SITLocation *location8 = [SitumCreatorTests indoorLocationWithIndoorBearingQualityLow];
    NSDictionary *locationJO8 = [SitumLocationWrapper.shared locationToJsonObject:location8];
    NSString *fileName8 =  @"location8";
    NSDictionary *jsonLocation8 = [TestingHelper dataFromJSONFileNamed: fileName8 inDirectory : _filePath];
    [_helper assertLocation: jsonLocation8: locationJO8];
}

- (void) testLocation9 {
    SITLocation *location9 = [SitumCreatorTests indoorLocationWithIndoorBearingQualityHigh];
    NSDictionary *locationJO9 = [SitumLocationWrapper.shared locationToJsonObject:location9];
    NSString *fileName9 =  @"location9";
    NSDictionary *jsonLocation9 = [TestingHelper dataFromJSONFileNamed: fileName9 inDirectory : _filePath];
    [_helper assertLocation: jsonLocation9: locationJO9];
}

- (void) testLocation10 {
    SITLocation *location10 = [SitumCreatorTests outdoorLocation];
    NSDictionary *locationJO10 = [SitumLocationWrapper.shared locationToJsonObject:location10];
    NSString *fileName10 =  @"location10";
    NSDictionary *jsonLocation10 = [TestingHelper dataFromJSONFileNamed: fileName10 inDirectory : _filePath];
    [_helper assertLocation: jsonLocation10: locationJO10];
}

@end
