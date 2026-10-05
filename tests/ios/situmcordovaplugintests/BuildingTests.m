//
//  BuildingTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 13/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface BuildingTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation BuildingTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/building";
    _helper = [TestingHelper new];
}
- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testBuilding1 {
    SITBuilding *building1 = [SitumCreatorTests createBuilding];
    NSDictionary *buildingJO1 = [SitumLocationWrapper.shared buildingToJsonObject:building1];
    NSString *fileName1 =  @"building1";
    //read from json object in resources
    NSDictionary *jsonBuilding1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _filePath];
    [_helper assertBuilding: jsonBuilding1: buildingJO1];
}

@end
