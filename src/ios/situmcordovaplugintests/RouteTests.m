//
//  RouteTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 13/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface RouteTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation RouteTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/route";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testRoute {
    SITRoute *route1 = [SitumCreatorTests createRouteBuildingWithDegreesPointWithCoordinates];
    NSDictionary *routeJO1 = [SitumLocationWrapper.shared routeToJsonObject:route1];
    NSString *fileName1 =  @"route1";
    //read from json object in resources
    NSDictionary *jsonRoute1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _filePath];
    [_helper assertRoute: jsonRoute1: routeJO1];
}

@end
