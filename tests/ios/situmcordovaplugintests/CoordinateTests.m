//
//  CoordinateTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 13/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface CoordinateTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation CoordinateTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/coordinate";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testCoordinate {
    CLLocationCoordinate2D coordinate1 = [SitumCreatorTests createCoordinate];
    NSDictionary *coordinateJO1 = [SitumLocationWrapper.shared coordinateToJsonObject:coordinate1];
    NSString *fileName1 =  @"coordinate1";
    //read from json object in resources
    NSDictionary *jsonCoordinate1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _filePath];
    [_helper assertCoordinate: jsonCoordinate1: coordinateJO1];
}

@end
