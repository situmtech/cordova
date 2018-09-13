//
//  TestingHelper.h
//  situmcordovapluginTests
//
//  Created by Situm on 24/7/18.
//  Copyright © 2018 Situm. All rights reserved.
//

#import <XCTest/XCTest.h>
#import <Foundation/Foundation.h>

@interface TestingHelper : XCTestCase 


+ (id)dataFromJSONFileNamed:(NSString *)fileName inDirectory:(NSString *) pathResources;
- (void) assertPoint: (NSDictionary *) jsonPointFile : (NSDictionary *) pointJO;
- (void) assertCoordinate: (NSDictionary *) jsonCoordinateFile : (NSDictionary *) coordinateJO;
- (void) assertCartesianCoordinate: (NSDictionary *) jsonCartesianCoordinateFile : (NSDictionary *) cartesianCoordinateJO;
- (void) assertAngle: (NSDictionary *) jsonAngleFile : (NSDictionary *) angleJO;
- (void) assertBound: (NSDictionary *) jsonBoundFile : (NSDictionary *) boundJO;
- (void) assertDimension: (NSDictionary *) jsonDimensionFile : (NSDictionary *) dimensionJO;
- (void) assertEvent: (NSDictionary *) jsonEventFile : (NSDictionary *) eventJO;
- (void) assertFloor: (NSDictionary *) jsonFloorFile : (NSDictionary *) floorJO;
- (void) assertIndication: (NSDictionary *) jsonIndicationFile : (NSDictionary *) indicationJO;

@end
