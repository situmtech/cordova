//
//  SITPOIExterior.h
//  SitumSDK
//
//  Created by A Barros on 14/4/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITPOIBase.h"

/**
 *  This class represents a Point Of Interest outside a SITIndoorBuilding
 */
@interface SITPOIExterior : SITPOIBase DEPRECATED_MSG_ATTRIBUTE("Use SITPOI instead.");

/**
 *  Latitude coordinate of the Exterior POI.
 */
@property (nonatomic, strong) NSNumber *latitude DEPRECATED_MSG_ATTRIBUTE("Use -position.coordinate.latitude value of a SITPOI object instead.");
/**
 *  Longitude coordinate of the Exterior POI.
 */
@property (nonatomic, strong) NSNumber *longitude DEPRECATED_MSG_ATTRIBUTE("Use -position.coordinate.longitude value of a SITPOI object instead.");



@end
