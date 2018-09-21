//
//  InputFloorTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 20/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface InputFloorTests : XCTestCase

@property(nonatomic, strong) NSString *pointFilePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation InputFloorTests

- (void)setUp {
    [super setUp];
    _pointFilePath = @"resources/floor";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testInputFloorWithAltitude {
    // Create the object to convert and test
    NSString *filename =  @"floor1";
    NSDictionary *jsonFloor = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    SITFloor* convertedFloor = [[SitumLocationWrapper shared] jsonObjectToFloor: jsonFloor];
    
    // Create the reference valid object to compare against
    SITFloor* referenceFloor = [SitumCreatorTests createFloorWithAltitude];
    
    // Compare both points
    [_helper assertFloor: convertedFloor isEqualToFloor: referenceFloor];
}

- (void) testInputFloorWithoutAltitude {
    // Create the object to convert and test
    NSString *filename =  @"floor2";
    NSDictionary *jsonFloor = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    SITFloor* convertedFloor = [[SitumLocationWrapper shared] jsonObjectToFloor: jsonFloor];
    
    // Create the reference valid object to compare against
    SITFloor* referenceFloor = [SitumCreatorTests createFloorWithoutAltitude];
    
    // Compare both points
    [_helper assertFloor: convertedFloor isEqualToFloor: referenceFloor];
}

@end
