//
//  PoiTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 14/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface PoiTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation PoiTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/poi";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testOutdoorPoiWithCategory {
    SITPOI *poi = [SitumCreatorTests createOutdoorPoiWithCategory];
    
    NSDictionary *poiJO = [SitumLocationWrapper.shared poiToJsonObject:poi];
    NSString *fileName =  @"poi1";
    //read from json object in resources
    NSDictionary *jsonPoi = [TestingHelper dataFromJSONFileNamed: fileName inDirectory : _filePath];
    [_helper assertPoi: jsonPoi: poiJO];
}

- (void) testPoiWithBuildingFloorAndCoordinateWithCategory {
    SITPOI *poi = [SitumCreatorTests createPoiWithBuildingFloorAndCoordinateWithCategory];
    
    NSDictionary *poiJO = [SitumLocationWrapper.shared poiToJsonObject:poi];
    NSString *fileName =  @"poi2";
    //read from json object in resources
    NSDictionary *jsonPoi = [TestingHelper dataFromJSONFileNamed: fileName inDirectory : _filePath];
    [_helper assertPoi: jsonPoi: poiJO];
}

- (void) testPoiWithCoordinateAndBuildingId {
    SITPOI *poi = [SitumCreatorTests createPoiWithCoordinateAndBuildingId];
    
    NSDictionary *poiJO = [SitumLocationWrapper.shared poiToJsonObject:poi];
    NSString *fileName =  @"poi3";
    //read from json object in resources
    NSDictionary *jsonPoi = [TestingHelper dataFromJSONFileNamed: fileName inDirectory : _filePath];
    [_helper assertPoi: jsonPoi: poiJO];
}

- (void) testPoiWithBuildingFloorCoordinateAndCartesian {
    SITPOI *poi = [SitumCreatorTests createPoiWithBuildingFloorCoordinateAndCartesian];
    
    NSDictionary *poiJO = [SitumLocationWrapper.shared poiToJsonObject:poi];
    NSString *fileName =  @"poi4";
    //read from json object in resources
    NSDictionary *jsonPoi = [TestingHelper dataFromJSONFileNamed: fileName inDirectory : _filePath];
    [_helper assertPoi: jsonPoi: poiJO];
}

- (void) testPoiWithBuildingFloorAndCoordinates {
    SITPOI *poi = [SitumCreatorTests createPoiWithBuildingFloorAndCoordinates];
    
    NSDictionary *poiJO = [SitumLocationWrapper.shared poiToJsonObject:poi];
    NSString *fileName =  @"poi5";
    //read from json object in resources
    NSDictionary *jsonPoi = [TestingHelper dataFromJSONFileNamed: fileName inDirectory : _filePath];
    [_helper assertPoi: jsonPoi: poiJO];
}

@end
