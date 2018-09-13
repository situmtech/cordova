//
//  SitumTests.m
//
//
//  Created by Situm 01-07-2018.
//  Copyright © 2018 Situm. All rights reserved.
//

#import "SitumTests.h"

@implementation SitumTests


//utility method to assert a ConversionArea
- (void) assertConversionArea:(NSDictionary *) jsonConversionAreaFile : (NSDictionary *) conversionAreaJO;
{
    XCTAssertEqualWithAccuracy([jsonConversionAreaFile[@"bottomLeft"][@"x"] doubleValue], [conversionAreaJO[@"bottomLeft"][@"x"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonConversionAreaFile[@"bottomLeft"][@"y"] doubleValue], [conversionAreaJO[@"bottomLeft"][@"y"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonConversionAreaFile[@"bottomRight"][@"x"] doubleValue], [conversionAreaJO[@"bottomRight"][@"x"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonConversionAreaFile[@"bottomRight"][@"y"] doubleValue], [conversionAreaJO[@"bottomRight"][@"y"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonConversionAreaFile[@"topLeft"][@"x"] doubleValue], [conversionAreaJO[@"topLeft"][@"x"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonConversionAreaFile[@"topLeft"][@"y"] doubleValue], [conversionAreaJO[@"topLeft"][@"y"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonConversionAreaFile[@"topRight"][@"x"] doubleValue], [conversionAreaJO[@"topRight"][@"x"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonConversionAreaFile[@"topRight"][@"y"] doubleValue], [conversionAreaJO[@"topRight"][@"y"] doubleValue], 0.0001);
    //TODO floorIdentifier is set in indoorPoint
    //XCTAssertEqualWithAccuracy([jsonConversionAreaFile[@"floorIdentifier"] doubleValue], [conversionAreaJO[@"floorIdentifier"] doubleValue], 0.0001);
}

- (void)setUp {
    [super setUp];
    _helper = [TestingHelper new];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testSitumConversionArea {
    NSString *filePath = @"resources/situmConversionArea";
    // ### POINT1.JSON ###
    SITRectangularArea *conversionArea1 = [SitumCreatorTests createSitumConversionArea];
    NSDictionary *conversionAreaJO1 = [SitumLocationWrapper.shared conversionAreaToJsonObject:conversionArea1];
    NSString *fileName1 =  @"situmConversionArea1";
    //read from json object in resources
    NSDictionary *jsonConversionArea1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : filePath];
    [self assertConversionArea: jsonConversionArea1: conversionAreaJO1];
}

@end
