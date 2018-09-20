//
//  InputCartesianCoordinateTests.m
//  situmcordovaplugintests
//
//  Created by Adrián Rodríguez on 19/9/18.
//

#import <XCTest/XCTest.h>
#import "TestingHelper.h"
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"

@interface InputCartesianCoordinateTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation InputCartesianCoordinateTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/cartesianCoordinate";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    [super tearDown];
}

- (void) testInputCartesianCoordinate {
    // Create the object to convert and test
    NSString *filename =  @"cartesianCoordinate1";
    NSDictionary *jsonCartesianCoordinate = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _filePath];
    // TODO: This conversion doesn't set isOutdoor
    SITCartesianCoordinate* convertedCoordinate = [[SitumLocationWrapper shared] cartesianCoordinateJsonObjectToCartesianCoordinate: jsonCartesianCoordinate];
    
    // Create the reference valid object to compare against
    SITCartesianCoordinate* referenceCoordinate = [SitumCreatorTests createCartesianCoordinate];
    
    // Compare both cartesian coordinates
    [_helper assertCartesianCoordinate: convertedCoordinate isEqualToCartesianCoordinate: referenceCoordinate];
}

@end
