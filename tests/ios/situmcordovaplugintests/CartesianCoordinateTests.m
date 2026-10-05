//
//  CartesianCoordinateTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 13/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface CartesianCoordinateTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation CartesianCoordinateTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/cartesianCoordinate";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testCartesianCoordinate {
    SITCartesianCoordinate *cartesianCoordinate1 = [SitumCreatorTests createCartesianCoordinate];
    NSDictionary *cartesianCoordinateJO1 = [SitumLocationWrapper.shared cartesianCoordinateToJsonObject:cartesianCoordinate1];
    NSString *fileName1 =  @"cartesianCoordinate1";
    //read from json object in resources
    NSDictionary *jsoncartesianCoordinate1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _filePath];
    [_helper assertCartesianCoordinate: jsoncartesianCoordinate1: cartesianCoordinateJO1];
}

@end
