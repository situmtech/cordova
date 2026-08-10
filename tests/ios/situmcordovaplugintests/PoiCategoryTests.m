//
//  PoiCategoryTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 13/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface PoiCategoryTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation PoiCategoryTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/poiCategory";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testPoiCategory {
    // ### POICATEGORY1.JSON ###
    SITPOICategory *poiCategory1 = [SitumCreatorTests createPoiCategory];
    NSDictionary *poiCategoryJO1 = [SitumLocationWrapper.shared poiCategoryToJsonObject:poiCategory1];
    NSString *fileName1 =  @"poiCategory1";
    //read from json object in resources
    NSDictionary *jsonPoiCategory1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _filePath];
    [_helper assertPoint: jsonPoiCategory1: poiCategoryJO1];
}
@end
