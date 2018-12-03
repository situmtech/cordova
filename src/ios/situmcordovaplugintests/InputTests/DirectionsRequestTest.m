//
//  DirectionsRequestTest.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 30/11/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface DirectionsRequestTest : XCTestCase

@property(nonatomic, strong) NSString *pointFilePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation DirectionsRequestTest

- (void)setUp {
    [super setUp];
    _pointFilePath = @"resources/directionsRequest";
    _helper = [TestingHelper new];
}

- (void) testDirectionsRequest {
    // Create the object to convert and test
    NSString *filename =  @"directionsRequest1";
    NSArray *jsonDirectionsRequest = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    SITDirectionsRequest* convertedDirectionsRequest = [[SitumLocationWrapper shared] jsonObjectToDirectionsRequest: jsonDirectionsRequest];
    
    // Create the reference valid object to compare against
    SITDirectionsRequest* referenceDirectionsRequest = [SitumCreatorTests createDirectionsRequestWithLocation];
    
    // Compare both indications
    [_helper assertDirectionsRequest: convertedDirectionsRequest isEqualToDirectionsRequest: referenceDirectionsRequest];
}

- (void) testDirectionsRequestWithAccessibleAndAccessibilityMode {
    // Create the object to convert and test
    NSString *filename =  @"directionsRequest2";
    NSArray *jsonDirectionsRequest = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    SITDirectionsRequest* convertedDirectionsRequest = [[SitumLocationWrapper shared] jsonObjectToDirectionsRequest: jsonDirectionsRequest];
    
    // Create the reference valid object to compare against
    SITDirectionsRequest* referenceDirectionsRequest = [SitumCreatorTests createDirectionsRequestWithLocation];
    
    // Compare both indications
    [_helper assertDirectionsRequest: convertedDirectionsRequest isEqualToDirectionsRequest: referenceDirectionsRequest];
}

- (void) testDirectionsRequestWithAccessible {
    // Create the object to convert and test
    NSString *filename =  @"directionsRequest3";
    NSArray *jsonDirectionsRequest = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    SITDirectionsRequest* convertedDirectionsRequest = [[SitumLocationWrapper shared] jsonObjectToDirectionsRequest: jsonDirectionsRequest];
    
    // Create the reference valid object to compare against
    SITDirectionsRequest* referenceDirectionsRequest = [SitumCreatorTests createDirectionsRequestWithAccessible];
    
    // Compare both indications
    [_helper assertDirectionsRequest: convertedDirectionsRequest isEqualToDirectionsRequest: referenceDirectionsRequest];
}


- (void) testDirectionsRequestWithAccessibleRoute {
    // Create the object to convert and test
    NSString *filename =  @"directionsRequest4";
    NSArray *jsonDirectionsRequest = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    SITDirectionsRequest* convertedDirectionsRequest = [[SitumLocationWrapper shared] jsonObjectToDirectionsRequest: jsonDirectionsRequest];
    
    // Create the reference valid object to compare against
    SITDirectionsRequest* referenceDirectionsRequest = [SitumCreatorTests createDirectionsRequestWithAccessible];
    
    // Compare both indications
    [_helper assertDirectionsRequest: convertedDirectionsRequest isEqualToDirectionsRequest: referenceDirectionsRequest];
}

- (void) testDirectionsRequestWithAccessibleRouteAndAccessible {
    // Create the object to convert and test
    NSString *filename =  @"directionsRequest5";
    NSArray *jsonDirectionsRequest = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    SITDirectionsRequest* convertedDirectionsRequest = [[SitumLocationWrapper shared] jsonObjectToDirectionsRequest: jsonDirectionsRequest];
    
    // Create the reference valid object to compare against
    SITDirectionsRequest* referenceDirectionsRequest = [SitumCreatorTests createDirectionsRequestWithAccessible];
    
    // Compare both indications
    [_helper assertDirectionsRequest: convertedDirectionsRequest isEqualToDirectionsRequest: referenceDirectionsRequest];
}

- (void) testDirectionsRequestWithPOI {
    // Create the object to convert and test
    NSString *filename =  @"directionsRequest6";
    NSArray *jsonDirectionsRequest = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    SITDirectionsRequest* convertedDirectionsRequest = [[SitumLocationWrapper shared] jsonObjectToDirectionsRequest: jsonDirectionsRequest];
    
    // Create the reference valid object to compare against
    SITDirectionsRequest* referenceDirectionsRequest = [SitumCreatorTests createDirectionsRequestWithPOI];
    
    // Compare both indications
    [_helper assertDirectionsRequest: convertedDirectionsRequest isEqualToDirectionsRequest: referenceDirectionsRequest];
}

@end

