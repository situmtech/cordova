//
//  InputPointTests.m
//  situmcordovaplugintests
//
//  Created by Adrián Rodríguez on 19/9/18.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface InputPointTests : XCTestCase

@property(nonatomic, strong) NSString *pointFilePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation InputPointTests

- (void)setUp {
    [super setUp];
    _pointFilePath = @"resources/point";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    [super tearDown];
}

- (void) testInputPointWithCoordinate {
    // Create the object to convert and test
    NSString *filename =  @"point1";
    NSDictionary *jsonPoint = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    // TODO: This conversion doesn't set isOutdoor
    SITPoint* convertedPoint = [[SitumLocationWrapper shared] pointJsonObjectToPoint: jsonPoint];

    // Create the reference valid object to compare against
    SITPoint* referencePoint = [SitumCreatorTests createPointWithCoordinate];

    // Compare both points
    [_helper assertPoint: convertedPoint isEqualToPoint: referencePoint];
}

- (void) testInputPointWithCoordinateAndBuilding {
    // Create the object to convert and test
    NSString* filename = @"point2";
    NSDictionary *jsonPoint = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    SITPoint* convertedPoint = [[SitumLocationWrapper shared] pointJsonObjectToPoint: jsonPoint];
    
    // Create the reference valid object to compare against
    SITPoint* referencePoint = [SitumCreatorTests createPointWithCoordinateAndBuildingId];
    
    // Compare both points
    [_helper assertPoint: convertedPoint isEqualToPoint: referencePoint];
}

- (void) testInputPointWithBuildingAndFloor {
    // Create the object to convert and test
    NSString* filename = @"point3";
    NSDictionary *jsonPoint = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    SITPoint* convertedPoint = [[SitumLocationWrapper shared] pointJsonObjectToPoint: jsonPoint];
    
    // Create the reference valid object to compare against
    SITPoint* referencePoint = [SitumCreatorTests createPointWithBuildingIdAndFloor];
    
    // Compare both points
    [_helper assertPoint: convertedPoint isEqualToPoint: referencePoint];
}

- (void) testInputPointWithBuildingAndAngleRadians {
    // Create the object to convert and test
    NSString* filename = @"point4";
    NSDictionary *jsonPoint = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    SITPoint* convertedPoint = [[SitumLocationWrapper shared] pointJsonObjectToPoint: jsonPoint];
    
    // Create the reference valid object to compare against
    SITPoint* referencePoint = [SitumCreatorTests createPointWithBuildingWithAngleFromRadians];
    
    // Compare both points
    [_helper assertPoint: convertedPoint isEqualToPoint: referencePoint];
}

- (void) testInputPointWithBuildingAndAngleDegrees {
    // Create the object to convert and test
    NSString* filename = @"point5";
    NSDictionary *jsonPoint = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    SITPoint* convertedPoint = [[SitumLocationWrapper shared] pointJsonObjectToPoint: jsonPoint];

    // Create the reference valid object to compare against
    SITPoint* referencePoint = [SitumCreatorTests createPointWithBuildingWithAngleFromDegrees];

    // Compare both points
    [_helper assertPoint: convertedPoint isEqualToPoint: referencePoint];
}

- (void) testInputPointWithBuildingAndAddress {
    // Create the object to convert and test
    NSString* filename = @"point6";
    NSDictionary *jsonPoint = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    SITPoint* convertedPoint = [[SitumLocationWrapper shared] pointJsonObjectToPoint: jsonPoint];

    // Create the reference valid object to compare against
    SITPoint* referencePoint = [SitumCreatorTests createPointWithBuildingWithAddress];

    // Compare both points
    [_helper assertPoint: convertedPoint isEqualToPoint: referencePoint];
}

- (void) testInputPointWithBuildingAndInfo {
    // Create the object to convert and test
    NSString* filename = @"point7";
    NSDictionary *jsonPoint = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    SITPoint* convertedPoint = [[SitumLocationWrapper shared] pointJsonObjectToPoint: jsonPoint];

    // Create the reference valid object to compare against
    SITPoint* referencePoint = [SitumCreatorTests createPointWithBuildingWithInfo];

    // Compare both points
    [_helper assertPoint: convertedPoint isEqualToPoint: referencePoint];
}

- (void) testInputPointWithBuildingAndPicture {
    // Create the object to convert and test
    NSString* filename = @"point8";
    NSDictionary *jsonPoint = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    SITPoint* convertedPoint = [[SitumLocationWrapper shared] pointJsonObjectToPoint: jsonPoint];

    // Create the reference valid object to compare against
    SITPoint* referencePoint = [SitumCreatorTests createPointWithBuildingWithPicture];

    // Compare both points
    [_helper assertPoint: convertedPoint isEqualToPoint: referencePoint];
}

@end
