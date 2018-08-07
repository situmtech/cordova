//
//  SITPOI.h
//  SitumSDK
//
//  Created by Abraham on 9/3/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITPOIBase.h"
#import "SITIndoorPoint.h"

#import "SITPoint.h"

#import "SITBuildingResource.h"

#import "SITPOICategory.h"

/**
 *  This class represents a Point Of Interest inside a SITIndoorBuilding
 *  @see See SITPOIBase to check base properties
 */
@interface SITPOI : SITBuildingResource

/**
 *  Identifier of the floor in which the POI resides
 *  @warning This field has been deprecated. In case of indoor POIs use position.floorID instead.
 */
@property (nonatomic, strong) NSNumber *level_identifier DEPRECATED_MSG_ATTRIBUTE("Use position.floorIdentifier instead");

/**
 *  Relationship to an SITIndoorBuilding object
 */
@property (nonatomic, strong) NSNumber *project_identifier __attribute__((deprecated))DEPRECATED_MSG_ATTRIBUTE("Use buildingIdentifier property of the SITResource base class instead");

/**
 *  x coordinate of the POI
 *  @warning This field has been deprecated. Use position.cartesianCoordinate.x instead.
 */
@property (nonatomic, strong) NSNumber *x DEPRECATED_MSG_ATTRIBUTE("Use position.cartesianCoordinate.x instead");
/**
 *  y coordinate of the POI
 *  @warning This field has been deprecated. Use position.cartesianCoordinate.y instead.
 */
@property (nonatomic, strong) NSNumber *y DEPRECATED_MSG_ATTRIBUTE("Use position.cartesianCoordinate.y instead");

/**
 *  radius (m) of the cylindrical area of the POI around the (x,y) point
 */
@property (nonatomic, strong) NSNumber *radius;

/**
 *  sergasID custom ID property
 */
@property (nonatomic, strong) NSNumber *sergasID DEPRECATED_MSG_ATTRIBUTE("Use customFields of the SITResource base class instead");

/**
 *  hasShifts custom property for Sergas
 */
@property (nonatomic) BOOL hasShifts DEPRECATED_MSG_ATTRIBUTE("Use customFields of the SITResource base class instead");
;

/**
 *  Method to construct a SITIndoorPoint to use  as a point to calcule routes to/from it
 *
 *  @return SITIndoorPoint object
 *  @warning This method has been deprecated
 */
- (SITIndoorPoint *)indoorPoint DEPRECATED_MSG_ATTRIBUTE("Use position instead");
;

/**
 *  Name
 */
@property (nonatomic, strong) NSString *name;

/**
 *  Additional information about the POI in HTML format
 */
@property (nonatomic, strong) NSString *info DEPRECATED_MSG_ATTRIBUTE("Use infoHTML instead");


/**
 *  Additional information about the POI in HTML format
 */
@property (nonatomic, strong) NSString *infoHTML;

/**
 Unique identifier of the category of this poi
 */
@property (nonatomic, strong) NSString *categoryIdentifier;

/**
 *  Relationship to a SITPOICategory object
 */
@property (nonatomic, strong) SITPOICategory *category;

/**
 Describes the location of the Point of interest (geographical and cartesianCoordinates)
 */
- (SITPoint *)position;

@end
