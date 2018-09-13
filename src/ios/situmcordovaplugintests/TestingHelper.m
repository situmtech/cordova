//
//  TestingHelper.m
//  situmcordovapluginTests
//
//  Created by Situm on 24/7/18.
//  Copyright © 2018 Situm. All rights reserved.
//
#import "TestingHelper.h"


@implementation TestingHelper

+ (id)dataFromJSONFileNamed:(NSString *)fileName inDirectory: (NSString *)pathResources {
    //get file path relative to project for avoiding CI integration problems
    NSString *filePath = [[NSBundle bundleForClass:[self class]] pathForResource:fileName
                                                         ofType:@"json"
                                                    inDirectory:pathResources];
    NSDictionary *json = [[NSMutableDictionary alloc] init];
    //check file exists
    if (filePath) {
        //retrieve file content
        NSData *data = [[NSData alloc] initWithContentsOfFile:filePath];
        
        //convert JSON NSData to a usable NSDictionary
        NSError *error;
        json = [NSJSONSerialization JSONObjectWithData:data
                                               options:0
                                                 error:&error];
        if (error) {
            NSLog(@"Something went wrong! %@", error.localizedDescription);
        }
    }
    else {
        NSLog(@"Couldn't find file!");
    }
    return json.copy;
}

- (void) assertPoint: (NSDictionary *) jsonPointFile : (NSDictionary *) pointJO {
    [self assertCoordinate:jsonPointFile[@"coordinate"]:pointJO[@"coordinate"]];
    [self assertCartesianCoordinate:jsonPointFile[@"cartesianCoordinate"]:pointJO[@"cartesianCoordinate"]];
    XCTAssertEqualObjects(jsonPointFile[@"floorIdentifier"], pointJO[@"floorIdentifier"]);
    XCTAssert(jsonPointFile[@"isIndoor"] == pointJO[@"isIndoor"]);
    XCTAssertEqualObjects(jsonPointFile[@"buildingIdentifier"], pointJO[@"buildingIdentifier"]);
    XCTAssert(jsonPointFile[@"isOutdoor"] == pointJO[@"isOutdoor"]);
}

- (void) assertCoordinate: (NSDictionary *) jsonCoordinateFile : (NSDictionary *) coordinateJO {
    XCTAssertEqualWithAccuracy([jsonCoordinateFile[@"longitude"] doubleValue], [coordinateJO[@"longitude"] doubleValue], 0.001);
    XCTAssertEqualWithAccuracy([jsonCoordinateFile[@"latitude"] doubleValue], [coordinateJO[@"latitude"] doubleValue], 0.001);
}

- (void) assertCartesianCoordinate: (NSDictionary *) jsonCartesianCoordinateFile : (NSDictionary *) cartesianCoordinateJO {
    XCTAssertEqualWithAccuracy([jsonCartesianCoordinateFile[@"x"] doubleValue], [cartesianCoordinateJO[@"x"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonCartesianCoordinateFile[@"y"] doubleValue], [cartesianCoordinateJO[@"y"] doubleValue], 0.0001);
}

- (void) assertAngle: (NSDictionary *) jsonAngleFile : (NSDictionary *) angleJO;
{
    XCTAssertEqualWithAccuracy([jsonAngleFile[@"radiansMinusPiPi"] doubleValue], [angleJO[@"radiansMinusPiPi"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonAngleFile[@"radians"] doubleValue], [angleJO[@"radians"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonAngleFile[@"degreesClockwise"] doubleValue], [angleJO[@"degreesClockwise"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonAngleFile[@"degrees"] doubleValue], [angleJO[@"degrees"] doubleValue], 0.0001);
}

- (void) assertBound: (NSDictionary *) jsonBoundFile : (NSDictionary *) boundJO {
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"northWest"][@"latitude"] doubleValue], [boundJO[@"northWest"][@"latitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"northWest"][@"longitude"] doubleValue], [boundJO[@"northWest"][@"longitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"northEast"][@"latitude"] doubleValue], [boundJO[@"northEast"][@"latitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"northEast"][@"longitude"] doubleValue], [boundJO[@"northEast"][@"longitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"southWest"][@"latitude"] doubleValue], [boundJO[@"southWest"][@"latitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"southWest"][@"longitude"] doubleValue], [boundJO[@"southWest"][@"longitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"southEast"][@"latitude"] doubleValue], [boundJO[@"southEast"][@"latitude"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonBoundFile[@"southEast"][@"longitude"] doubleValue], [boundJO[@"southEast"][@"longitude"] doubleValue], 0.0001);
}

- (void) assertDimension: (NSDictionary *) jsonDimensionFile : (NSDictionary *) dimensionJO {
    XCTAssertEqualWithAccuracy([jsonDimensionFile[@"width"] doubleValue], [dimensionJO[@"width"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonDimensionFile[@"height"] doubleValue], [dimensionJO[@"height"] doubleValue], 0.0001);
}

- (void) assertEvent: (NSDictionary *) jsonEventFile : (NSDictionary *) eventJO {
    XCTAssertEqualWithAccuracy([jsonEventFile[@"identifier"] doubleValue], [eventJO[@"identifier"] doubleValue], 0.0001);
    XCTAssertEqualWithAccuracy([jsonEventFile[@"buildingIdentifier"] doubleValue], [eventJO[@"buildingIdentifier"] doubleValue], 0.0001);
    XCTAssertEqualObjects(jsonEventFile[@"infoHtml"], eventJO[@"infoHtml"]);
}

@end
