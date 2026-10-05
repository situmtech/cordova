//
//  InputCoordinateTests.m
//  situmcordovaplugintests
//
//  Created by Adrián Rodríguez on 20/9/18.
//

#import <XCTest/XCTest.h>
#import "TestingHelper.h"
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"

@interface InputCoordinateTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation InputCoordinateTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/coordinate";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    [super tearDown];
}

- (void) testInputCoordinate {
    // Create the object to convert and test
    NSString *filename =  @"coordinate1";
    NSDictionary *jsonCoordinate = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _filePath];
    CLLocationCoordinate2D convertedCoordinate = [[SitumLocationWrapper shared] coordinateJsonObjectToCoordinate: jsonCoordinate];
    
    // Create the reference valid object to compare against
    CLLocationCoordinate2D referenceCoordinate = [SitumCreatorTests createCoordinate];
    
    // Compare both coordinates
    [_helper assertCoordinate: convertedCoordinate isEqualToCoordinate: referenceCoordinate];
}

@end
