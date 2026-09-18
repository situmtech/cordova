//
//  LocationStatusTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 13/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface LocationStatusTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation LocationStatusTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/locationStatus";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void)testLocationStatus1 {
    SITLocationState locationStatus1 = [SitumCreatorTests createLocationStatusStarting];
    NSDictionary *locationStatusJO1 = [SitumLocationWrapper.shared locationStateToJsonObject:locationStatus1];
    NSString *fileName1 =  @"locationStatus1";
    //read from json object in resources
    NSDictionary *jsonLocationStatus1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _filePath];
    [_helper assertLocationStatus: jsonLocationStatus1: locationStatusJO1];
}

- (void) testLocationStatus3 {
    SITLocationState locationStatus3 = [SitumCreatorTests createLocationStatusCalculating];
    NSDictionary *locationStatusJO3 = [SitumLocationWrapper.shared locationStateToJsonObject:locationStatus3];
    NSString *fileName3 =  @"locationStatus3";
    //read from json object in resources
    NSDictionary *jsonLocationStatus3 = [TestingHelper dataFromJSONFileNamed: fileName3 inDirectory : _filePath];
    [_helper assertLocationStatus: jsonLocationStatus3: locationStatusJO3];
}

- (void) testLocationStatus13 {
    SITLocationState locationStatus13 = [SitumCreatorTests createLocationStatusUserNotInBuilding];
    NSDictionary *locationStatusJO13 = [SitumLocationWrapper.shared locationStateToJsonObject:locationStatus13];
    NSString *fileName13 =  @"locationStatus13";
    //read from json object in resources
    NSDictionary *jsonLocationStatus13 = [TestingHelper dataFromJSONFileNamed: fileName13 inDirectory : _filePath];
    [_helper assertLocationStatus: jsonLocationStatus13: locationStatusJO13];
}

@end
