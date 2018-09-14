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

- (void) testPoi {
    SITPOI *poi = [SitumCreatorTests createOutdoorPoiWithCategory];
    
    NSDictionary *poiJO1 = [SitumLocationWrapper.shared poiToJsonObject:poi];
    NSString *fileName1 =  @"poi1";
    //read from json object in resources
    NSDictionary *jsonPoi1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _filePath];
    [_helper assertPoi: jsonPoi1: poiJO1];
}

@end
