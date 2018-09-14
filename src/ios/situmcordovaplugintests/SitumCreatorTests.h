//
//  situmcordovapluginCreatorTests.h
//  situmcordovapluginTests
//
//  Created by Situm 03/7/18.
//  Copyright © 2018 Situm. All rights reserved.
//
#import <Foundation/Foundation.h>
#import <SitumSDK/SitumSDK.h>

@interface SitumCreatorTests : NSObject

//angle
+ (SITAngle *) createAngleFromDegrees; //angle1.json
+ (SITAngle *) createAngleFromRadians; //angle2.json
//bounds
+ (SITBounds) createBoundsWithArray; //bounds1.json
+ (SITBounds) createBounds; //bounds2.json
//building
+ (SITBuilding *) createBuilding;
//cartesianCoordinate
+ (SITCartesianCoordinate *) createCartesianCoordinate; //cartesianCoordinate1.json
//coordinate
+ (CLLocationCoordinate2D) createCoordinate; //coordinate1.json
//dimensions
+ (SITDimensions *) createDimensions; //dimensions1.json
//dimensions
+ (SITEvent *) createEvent; //event1.json
//floor
+ (SITFloor *) createFloorWithAltitude; //floor1.json
+ (SITFloor *) createFloorWithoutAltitude; //floor2.json
//indication
+ (SITIndication *) createIndication;
//location
+ (SITLocation *) createLocationWithBuildingFloorAndCartesianCoordinates; //location1.json
+ (SITLocation *) createLocationWithCoordinate; //location2.json
+ (SITLocation *) createLocationWithBuildingAndCoordinate; //location3.json
+ (SITLocation *) locationWithCartesianBearing; //location4.json
+ (SITLocation *) locationWithoutCartesianBearing; //location5.json
+ (SITLocation *) locationWithBearing; //location6.json
+ (SITLocation *) locationWithouthBearing; //location7.json
+ (SITLocation *) indoorLocationWithIndoorBearingQualityLow; //location8.json
+ (SITLocation *) indoorLocationWithIndoorBearingQualityHigh; //location9.json
+ (SITLocation *) outdoorLocation; //location10.json
//locationStatus
+ (SITLocationState) createLocationStatusStarting; //locationStatus1.json
+ (SITLocationState) createLocationStatusCalculating; //locationStatus3.json
+ (SITLocationState) createLocationStatusUserNotInBuilding; //locationStatus13.json
//navigationProgress
+ (SITNavigationProgress *) createNavigationProgressOutdoor; //navigationProgress1.json
+ (SITNavigationProgress *) createNavigationProgressIndoor; //navigationProgress2.json
//poi
+ (SITPOI *) createOutdoorPoiWithCategory;
+ (SITPOI *) createPoiWithBuildingFloorAndCoordinateWithCategory;
+ (SITPOI *) createPoiWithCoordinateAndBuildingId;
+ (SITPOI *) createPoiWithBuildingFloorCoordinateAndCartesian;
+ (SITPOI *) createPoiWithBuildingFloorAndCoordinates;
//poiCategory
+ (SITPOICategory *) createPoiCategory; //poiCategory1.json
//point
+ (SITPoint *) createPointWithCoordinate; //point1.json
+ (SITPoint *) createPointWithCoordinateAndBuildingId; //point2.json
+ (SITPoint *) createPointWithBuildingIdAndFloor; //point3.json
+ (SITPoint *) createPointWithBuildingWithAngleFromRadians; //point4.json
+ (SITPoint *) createPointWithBuildingWithAngleFromDegrees; //point5.json
+ (SITPoint *) createPointWithBuildingWithAddress; //point6.json
+ (SITPoint *) createPointWithBuildingWithInfo; //point7.json
+ (SITPoint *) createPointWithBuildingWithPicture; //point8.json
//route
+ (SITRoute *) createRouteBuildingWithDegreesPointWithCoordinates; //route1.json
//routeStep
+ (SITRouteStep *) createRouteStepWithCoordinate; //routeStep1.json
+ (SITRouteStep *) createRouteStepWithCoordinateAndBuildingId; //routeStep2.json
+ (SITRouteStep *) createRouteStepWithCoordinateBuildingIdAndFloor; //routeStep3.json
+ (SITRouteStep *) createRouteStepWithBuildingWithAngleFromDegree; //routeStep4.json
+ (SITRouteStep *) createRouteStepWithBuildingWithAngleFromRadians; //routeStep5.json
//situmConversionArea
+ (SITRectangularArea *) createSitumConversionArea; //situmConversionArea1.json
@end
