//
//  PointTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 13/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface PointTests : XCTestCase

@property(nonatomic, strong) NSString *pointFilePath;
@property (nonatomic, strong) TestingHelper *helper;


@end

@implementation PointTests

- (void)setUp {
    [super setUp];
    _pointFilePath = @"resources/point";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testPoint1 {
    // ### POINT1.JSON ###
    SITPoint *point1 = [SitumCreatorTests createPointWithCoordinate];
    NSDictionary *pointJO1 = [SitumLocationWrapper.shared pointToJsonObject:point1];
    NSString *fileName1 =  @"point1";
    //read from json object in resources
    NSDictionary *jsonPoint1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _pointFilePath];
    [_helper assertPoint: jsonPoint1: pointJO1];
}

- (void) testPoint2 {
    SITPoint *point2 = [SitumCreatorTests createPointWithCoordinateAndBuildingId];
    NSDictionary *pointJO2 = [SitumLocationWrapper.shared pointToJsonObject:point2];
    NSString *fileName2 =  @"point2";
    //read from json object in resources
    NSDictionary *jsonPoint2 = [TestingHelper dataFromJSONFileNamed: fileName2 inDirectory : _pointFilePath];
    [_helper assertPoint: jsonPoint2: pointJO2];
}

- (void) testPoint3 {
    SITPoint *point3 = [SitumCreatorTests createPointWithBuildingIdAndFloor];
    NSDictionary *pointJO3 = [SitumLocationWrapper.shared pointToJsonObject:point3];
    NSString *fileName3 =  @"point3";
    //read from json object in resources
    NSDictionary *jsonPoint3 = [TestingHelper dataFromJSONFileNamed: fileName3 inDirectory : _pointFilePath];
    [_helper assertPoint: jsonPoint3: pointJO3];
}

- (void) testPoint4 {
    SITPoint *point4 = [SitumCreatorTests createPointWithBuildingWithAngleFromRadians];
    NSDictionary *pointJO4 = [SitumLocationWrapper.shared pointToJsonObject:point4];
    NSString *fileName4 =  @"point4";
    //read from json object in resources
    NSDictionary *jsonPoint4 = [TestingHelper dataFromJSONFileNamed: fileName4 inDirectory : _pointFilePath];
    [_helper assertPoint: jsonPoint4: pointJO4];
}

- (void) testPoint5 {
    SITPoint *point5 = [SitumCreatorTests createPointWithBuildingWithAngleFromDegrees];
    NSDictionary *pointJO5 = [SitumLocationWrapper.shared pointToJsonObject:point5];
    NSString *fileName5 =  @"point5";
    //read from json object in resources
    NSDictionary *jsonPoint5 = [TestingHelper dataFromJSONFileNamed: fileName5 inDirectory : _pointFilePath];
    [_helper assertPoint: jsonPoint5: pointJO5];
}

- (void) testPoint6 {
    SITPoint *point6 = [SitumCreatorTests createPointWithBuildingWithAddress];
    NSDictionary *pointJO6 = [SitumLocationWrapper.shared pointToJsonObject:point6];
    NSString *fileName6 =  @"point6";
    //read from json object in resources
    NSDictionary *jsonPoint6 = [TestingHelper dataFromJSONFileNamed: fileName6 inDirectory : _pointFilePath];
    [_helper assertPoint: jsonPoint6: pointJO6];
}

- (void) testPoint7 {
    SITPoint *point7 = [SitumCreatorTests createPointWithBuildingWithInfo];
    NSDictionary *pointJO7 = [SitumLocationWrapper.shared pointToJsonObject:point7];
    NSString *fileName7 =  @"point7";
    //read from json object in resources
    NSDictionary *jsonPoint7 = [TestingHelper dataFromJSONFileNamed: fileName7 inDirectory : _pointFilePath];
    [_helper assertPoint: jsonPoint7: pointJO7];
}

- (void) testPoint8 {
    SITPoint *point8 = [SitumCreatorTests createPointWithBuildingWithPicture];
    NSDictionary *pointJO8 = [SitumLocationWrapper.shared pointToJsonObject:point8];
    NSString *fileName8 =  @"point8";
    //read from json object in resources
    NSDictionary *jsonPoint8 = [TestingHelper dataFromJSONFileNamed: fileName8 inDirectory : _pointFilePath];
    [_helper assertPoint: jsonPoint8: pointJO8];
}

@end
