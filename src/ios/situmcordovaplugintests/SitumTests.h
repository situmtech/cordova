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

@property (nonatomic, strong) NSString *pointFilePath;
@property (nonatomic, strong)  NSString *stepFilePath;

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
- (void) testPoint1;
- (void) testPoint2;
- (void) testPoint3;
- (void) testPoint4;
- (void) testPoint5;
- (void) testPoint6;
- (void) testPoint7;
- (void) testRoute8;
- (void) testRouteStep1;
- (void) testRouteStep2;
- (void) testRouteStep3;
- (void) testRouteStep4;
- (void) testRouteStep5;
- (void) testSitumConversionArea;

@end
