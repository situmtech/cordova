//
//  SitumTests.m
//
//
//  Created by Situm 01-07-2018.
//  Copyright © 2018 Situm. All rights reserved.
//

#import "SitumTests.h"

@implementation SitumTests


//utility method to assert a location object
- (void) assertLocation: (NSDictionary *) jsonLocationFile : (NSDictionary *) locationJO;
{
    XCTAssertEqualObjects(jsonLocationFile[@"accuracy"], locationJO[@"accuracy"]);
    XCTAssertEqualObjects(jsonLocationFile[@"provider"], locationJO[@"provider"]);
    //always returning true when creating object from file due to ios sdk restriction in hasBearing functionality
    XCTAssertTrue(jsonLocationFile[@"hasBearing"]);
}

- (void)setUp {
    [super setUp];
    // Put setup code here. This method is called before the invocation of each test method in the class.
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void)testLocation {
    SITLocation *location1 = [SitumCreatorTests createLocationWithBuildingFloorAndCartesianCoordinates];
    NSDictionary *locationJO1 = [SitumLocationWrapper.shared locationToJsonObject:location1];
    // ### LOCATION1.JSON ###
    NSString *fileName =  @"location1";
    NSString *filePath = @"resources/location";
    //read from json object in resources
    NSDictionary *jsonLocation1 = [TestingHelper dataFromJSONFileNamed: fileName inDirectory : filePath];
    [self assertLocation: jsonLocation1: locationJO1];
}


@end
