//
//  SITPoint.h
//  SitumSDK
//
//  Created by A Barros on 21/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreLocation/CoreLocation.h>

#import "SITCartesianCoordinate.h"


/**
 Associate geographical coordinate (Location) with Building and Floor (Cartography) and cartesian coordinate relative to that building.
 */
@interface SITPoint : NSObject


/**
 Unique identifier of the SITBuilding.
 */
@property (nonatomic, strong) NSString *buildingIdentifier;


/**
 Unique identifier of the SITFloor.
 */
@property (nonatomic, strong) NSString *floorIdentifier;


/**
 Returns cartesian coordinate (in meters) relative to the bounds of building's base.
 
 */
@property (nonatomic, strong) SITCartesianCoordinate *cartesianCoordinate;


/**
 Name associated with the point (can be nil).
 */
@property (nonatomic, strong) NSString *name;


/**
 Check if the point refers to an indoor position.

 @return BOOL value that indicates if the point is indoor (YES) or not (NO).
 */
- (BOOL)isIndoor;

/**
 Check if the point refers to an outdoor position.

 @return BOOL value that indicates if the point is outdoor (YES) or not (NO).
 */
- (BOOL)isOutdoor;


/**
 Compute the horizontal distance between two points.

 @param point the point to compute the distance to.
 @return distance between points (in meters).
 */
- (float)distanceToPoint:(SITPoint *)point;


/**
 Create an indoor point

 @param coordinate Geographical coordinate of the point
 @param buildingIdentifier unique identifier of the building
 @param floorIdentifier unique identifier of the floor
 @param cartesianCoordinate coordinate in cartesian coordinate system
 @return initialized indoor point
 */
- (instancetype)initWithCoordinate:(CLLocationCoordinate2D)coordinate
                buildingIdentifier:(NSString *)buildingIdentifier
                   floorIdentifier:(NSString *)floorIdentifier
              cartesianCoordinate:(SITCartesianCoordinate *)cartesianCoordinate;


/**
 Create an outdoor point with a coordinate

 @param coordinate Geographical coordinate of the point
 @return initialized point
 */
- (instancetype)initWihtCoordinate:(CLLocationCoordinate2D)coordinate;

/**
 Geographical coordinate

 @return CLLocationCoordinate2D struct
 */
- (CLLocationCoordinate2D)coordinate;

@end
