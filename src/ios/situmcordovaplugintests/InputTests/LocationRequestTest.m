//
//  LocationRequestTest.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 22/10/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface LocationRequestTest : XCTestCase

@property(nonatomic, strong) NSString *pointFilePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation LocationRequestTest

- (void)setUp {
    [super setUp];
    _pointFilePath = @"resources/locationRequest";
    _helper = [TestingHelper new];
}

- (void) testLegacyLocationRequest {
    // Create the object to convert and test
    NSString *filename =  @"locationRequest1";
    NSArray *jsonLocationRequest = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    SITLocationRequest* convertedLocationRequest = [[SitumLocationWrapper shared] jsonObjectToLocationRequest: jsonLocationRequest];
    
    // Create the reference valid object to compare against
    SITLocationRequest* referenceLocationRequest = [SitumCreatorTests createLegacyLocationRequest];
    
    // Compare both indications
    [_helper assertLocationRequest: convertedLocationRequest isEqualToLocationRequest: referenceLocationRequest];
}

- (void) testLocationRequestWithoutGpsNorDeadReckoning {
    // Create the object to convert and test
    NSString *filename =  @"locationRequest2";
    NSArray *jsonLocationRequest = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    SITLocationRequest* convertedLocationRequest = [[SitumLocationWrapper shared] jsonObjectToLocationRequest: jsonLocationRequest];
    
    // Create the reference valid object to compare against
    SITLocationRequest* referenceLocationRequest = [SitumCreatorTests createLocationRequestWithoutGpsNorDeadReckoning];
    
    // Compare both indications
    [_helper assertLocationRequest: convertedLocationRequest isEqualToLocationRequest: referenceLocationRequest];
}

- (void) testLocationRequestWithGpsAndDeadReckoning {
    // Create the object to convert and test
    NSString *filename =  @"locationRequest3";
    NSArray *jsonLocationRequest = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    SITLocationRequest* convertedLocationRequest = [[SitumLocationWrapper shared] jsonObjectToLocationRequest: jsonLocationRequest];
    
    // Create the reference valid object to compare against
    SITLocationRequest* referenceLocationRequest = [SitumCreatorTests createLocationRequestWithGpsAndDeadReckoning];
    
    // Compare both indications
    [_helper assertLocationRequest: convertedLocationRequest isEqualToLocationRequest: referenceLocationRequest];
}
@end
