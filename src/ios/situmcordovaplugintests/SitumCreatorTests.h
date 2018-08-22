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

@end
