//
//  SitumTests.h
//  situmcordovapluginTests
//
//  Created by Situm on 15/7/18.
//  Copyright © 2018 Situm. All rights reserved.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "TestingHelper.h"
#import "SitumLocationWrapper.h"
#import <SitumSDK/SitumSDK.h>

@interface SitumTests: XCTestCase

- (void) setUp;
- (void) tearDown;
- (void) testAngle;
- (void) testBound;
- (void) testCartesianCoordinate;
- (void) testCoordinate;
- (void) testDimension;
- (void) testEvent;
- (void) testFloor;
- (void) testIndication;
- (void) testLocation;
- (void) testLocationStatus;
- (void) testNavigationProgress;
- (void) testPoiCategory;
- (void) testPoint;
- (void) testRoute;
- (void) testRouteStep;
- (void) testSitumConversionArea;

@end
