//
//  SITIndoorBuilding.h
//  SitumSDK
//
//  Created by Abraham on 3/3/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#ifndef SITIndoorBuilding_h
#define SITIndoorBuilding_h

#import <Foundation/Foundation.h>
#import <MapKit/MapKit.h>

#import "SITBounds.h"

@class SITIndoorBuildingModel;

/**
 *  SITBounds represents a rectangular box containing the geographic coordinates of the corners of the region
 */
/*typedef struct {
    CLLocationCoordinate2D southWest;
    CLLocationCoordinate2D southEast;
    CLLocationCoordinate2D northEast;
    CLLocationCoordinate2D northWest;
} SITBounds;*/

/**
 *  This class provide details on the building with support for indoor location.
 *  @discussion You should not create objects of this class. Instead you should retrieve them using the
 *  appropiate method of the SITCommunicationManager class.
 */

@interface SITIndoorBuilding : NSObject DEPRECATED_MSG_ATTRIBUTE("Use SITBuilding instead.");

// use this as a private property

/**
 Unique identifier of the building.
 */
@property (nonatomic, strong) NSNumber *identifier DEPRECATED_MSG_ATTRIBUTE("Use identifier value of a SITBuilding object instead.");


/**
 Unique identifier of the user that has created the building
 */
@property (nonatomic, strong) NSNumber *user_identifier DEPRECATED_MSG_ATTRIBUTE("Use userIdentifier value of a SITBuilding object instead.");
;

/**
 *  Name of the building.
 */
@property (nonatomic, strong) NSString *name DEPRECATED_MSG_ATTRIBUTE("Use name value of a SITBuilding object instead.");
;
/**
 *  Address string of the building.
 */
@property (nonatomic, strong) NSString *address DEPRECATED_MSG_ATTRIBUTE("Use address value of a SITBuilding object instead.");
;
/**
 *  Latitude coordinate of the building.
 */
@property (nonatomic, strong) NSNumber *latitude DEPRECATED_MSG_ATTRIBUTE("Use center.latitude value of a SITBuilding object instead.");
/**
 *  Longitude coordinate of the building.
 */
@property (nonatomic, strong) NSNumber *longitude DEPRECATED_MSG_ATTRIBUTE("Use center.longitude value of a SITBuilding object instead.");

/**
 *  This property is the url of the picture of a SITIndoorBuilding at high quality
 */
@property (nonatomic, strong) NSString *picture_url DEPRECATED_MSG_ATTRIBUTE("Use pictureURL value of a SITBuilding object instead.");

/**
 *  This property is the url of the picture of a SITIndoorBuilding at low quality
 */
@property (nonatomic, strong) NSString *picture_thumb_url DEPRECATED_MSG_ATTRIBUTE("Use pictureThumbURL value of a SITBuilding object instead.");

@property (nonatomic, strong) NSString *server_url DEPRECATED_MSG_ATTRIBUTE("Information not publicly available. The SDK will handle this information internally for you.");

@property (nonatomic, strong) NSNumber *width DEPRECATED_MSG_ATTRIBUTE("Use dimensions.width value of a SITBuilding object instead.");


@property (nonatomic, strong) NSNumber *length DEPRECATED_MSG_ATTRIBUTE("Use dimensions.height value of a SITBuilding object instead.");
;

// customID
@property (nonatomic, strong) NSString *sergasID DEPRECATED_MSG_ATTRIBUTE("Use customFields value of a SITBuilding object to integrate with other systems instead.");
;
// Array of SITCorner
@property (nonatomic, strong) NSArray *corners DEPRECATED_MSG_ATTRIBUTE("Use bounds value of a SITBuilding object instead.");

@property (nonatomic, strong) NSNumber *rotation DEPRECATED_MSG_ATTRIBUTE("Use rotation of a CoordinateConverter object instead.");
;

/**
 *  Detailed information in an HTML format
 */
@property (nonatomic, strong) NSString *info DEPRECATED_MSG_ATTRIBUTE("Use infoHTML property of a SITBuilding object instead.");

/**
 *  Convinient method to restore a CLLocationCoordinate2D object from latitude and longitude coordinates
 *
 *  @return CLLocationCoordinate2D
 */
- (CLLocationCoordinate2D)coordinate DEPRECATED_MSG_ATTRIBUTE("Use -center method of a SITBuilding object instead.");

- (NSDictionary *)hostAndPortFromServerURL DEPRECATED_MSG_ATTRIBUTE("Information not publicly available. The SDK will handle this information internally for you.");

/**
 *  Converts the orientation from floorplan coordinates to geographic coordinates
 *
 *  @param yaw  value of the orientation in floorplan coordinate. Normally this value is retrieved from SITIndoorLocation
 *  @return float Number with angle (radians)
 *  @discussion If you want to use this value in degrees you should convert it by applying the conversion 180/M_PI
 */
- (NSNumber *)angleFromYaw:(NSNumber *)yaw DEPRECATED_MSG_ATTRIBUTE("Use -toAngle: method of a CoordinateConverter object instead.");

/**
 *  Converts a floorplan coordinate in a geographic coordinate
 *
 *  @param point CGPoint with x and y floorplan coordinates
 *
 *  @return Geographic coordinate
 */
- (CLLocationCoordinate2D)coordinateFromPoint:(CGPoint)point DEPRECATED_MSG_ATTRIBUTE("Use -toLocationCoordinate: method of a CoordinateConverter object instead.");

/**
 *  Returns a CGPoint for where coordinates sit on the floorplan
 *
 *  @param coordinate Geographic coordinate inside a SITBounds region
 *
 *  @return CGPoint with x and y coordinates
 */
- (CGPoint)pointFromCoordinate:(CLLocationCoordinate2D)coordinate DEPRECATED_MSG_ATTRIBUTE("Use - toCartesianCoordinate: method of a CoordinateConverter object instead.");

/**
 *  Represent the region of a SITIndoorLevel map
 *
 *  @return SITBounds structure with NortWest, NorthEast, SouthWest and SouthEast coordinates
 */
- (SITBounds)bounds DEPRECATED_MSG_ATTRIBUTE("Use -bounds method of a CoordinateConverter object instead.");

- (SITBounds)boundsRotated DEPRECATED_MSG_ATTRIBUTE("Use -boundsRotated method of a CoordinateConverter object instead.");


/**
 *  Determines if the building has a valid model
 *
 *  @return BOOL value that determines if the model of the building is valid (YES) or not (NO)
 */
- (BOOL)hasValidModel DEPRECATED_MSG_ATTRIBUTE("Information not publicly available. The SDK will handle this information internally for you.");

- (SITIndoorBuildingModel *)model DEPRECATED_MSG_ATTRIBUTE("Information not publicly available. The SDK will handle this information internally for you.");

- (void)setModel:(SITIndoorBuildingModel *)model DEPRECATED_MSG_ATTRIBUTE("Information not publicly available. The SDK will handle this information internally for you.");

@end
#endif
