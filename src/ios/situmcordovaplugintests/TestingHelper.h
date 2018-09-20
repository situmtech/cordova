//
//  TestingHelper.h
//  situmcordovapluginTests
//
//  Created by Situm on 24/7/18.
//  Copyright © 2018 Situm. All rights reserved.
//

#import <XCTest/XCTest.h>
#import <Foundation/Foundation.h>
#import "SitumLocationWrapper.h"

@interface TestingHelper : XCTestCase 


+ (id)dataFromJSONFileNamed:(NSString *)fileName inDirectory:(NSString *) pathResources;
- (void) assertCoordinate: (NSDictionary *) jsonCoordinateFile : (NSDictionary *) coordinateJO;
- (void) assertCartesianCoordinate: (NSDictionary *) jsonCartesianCoordinateFile : (NSDictionary *) cartesianCoordinateJO;
- (void) assertAngle: (NSDictionary *) jsonAngleFile : (NSDictionary *) angleJO;
- (void) assertBound: (NSDictionary *) jsonBoundFile : (NSDictionary *) boundJO;
- (void) assertBuilding: (NSDictionary *) jsonBuildingFile : (NSDictionary *) buildingJO;
- (void) assertDimension: (NSDictionary *) jsonDimensionFile : (NSDictionary *) dimensionJO;
- (void) assertEvent: (NSDictionary *) jsonEventFile : (NSDictionary *) eventJO;
- (void) assertFloor: (NSDictionary *) jsonFloorFile : (NSDictionary *) floorJO;
- (void) assertIndication: (NSDictionary *) jsonIndicationFile : (NSDictionary *) indicationJO;
- (void) assertLocation: (NSDictionary *) jsonLocationFile : (NSDictionary *) locationJO;
- (void) assertLocationStatus: (NSDictionary *) jsonLocationStatusFile : (NSDictionary *) locationStatusJO;
- (void) assertNavigationProgress: (NSDictionary *) jsonNavigationProgressFile : (NSDictionary *) navigationProgressJO;
- (void) assertPoi: (NSDictionary *) jsonPoiFile : (NSDictionary *) poiJO;
- (void) assertImage: (NSDictionary *) jsonImageFile : (NSDictionary *) imageJO;
- (void) assertPoint: (NSDictionary *) jsonPointFile : (NSDictionary *) pointJO;
- (void) assertRoute: (NSDictionary *) jsonRouteFile : (NSDictionary *) routeJO;
- (void) assertRouteStep: (NSDictionary *) jsonRouteStepFile : (NSDictionary *) routeStepJO;
- (void) assertConversionArea:(NSDictionary *) jsonConversionAreaFile : (NSDictionary *) conversionAreaJO;

#pragma MARK Input tests methods

- (void) assertCoordinate: (CLLocationCoordinate2D) coordinateA isEqualToCoordinate: (CLLocationCoordinate2D) coordinateB;
- (void) assertCartesianCoordinate: (SITCartesianCoordinate*) coordinateA isEqualToCartesianCoordinate: (SITCartesianCoordinate*) coordinateB;
- (void) assertPoint: (SITPoint*) pointA isEqualToPoint: (SITPoint*) pointB;
- (void) assertLocation: (SITLocation*) locationA isEqualToLocation: (SITLocation*) locationB;
- (void) assertFloor: (SITFloor *) floorA isEqualToFloor :(SITFloor *) floorB;

@end
