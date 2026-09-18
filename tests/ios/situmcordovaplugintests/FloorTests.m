//
//  FloorTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 13/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface FloorTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation FloorTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/floor";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testFloor1 {
    // ### FLOOR1.JSON ###
    SITFloor *floor1 = [SitumCreatorTests createFloorWithAltitude];
    NSDictionary *floorJO1 = [SitumLocationWrapper.shared floorToJsonObject:floor1];
    NSString *fileName1 =  @"floor1";
    //read from json object in resources
    NSDictionary *jsonFloor1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _filePath];
    [_helper assertFloor: jsonFloor1: floorJO1];
}

- (void) testFloor2 {
    SITFloor *floor2 = [SitumCreatorTests createFloorWithoutAltitude];
    NSDictionary *floorJO2 = [SitumLocationWrapper.shared floorToJsonObject:floor2];
    NSString *fileName2 =  @"floor2";
    //read from json object in resources
    NSDictionary *jsonFloor2 = [TestingHelper dataFromJSONFileNamed: fileName2 inDirectory : _filePath];
    [_helper assertFloor: jsonFloor2: floorJO2];
}

@end
