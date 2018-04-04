//
//  SITFloor.h
//  SitumSDK
//
//  Created by A Barros on 2/3/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import "SITBuildingResource.h"
#import "SITMultilanguageString.h"
#import "SITURL.h"

@interface SITFloor : SITBuildingResource

/**
 Numeric value representing the ground level.
 This value can be negative to designate floors below the ground floor
 */
@property (nonatomic) NSInteger level;

/**
 Scale of the floor image in px/meters
 */
@property (nonatomic) double scale;

/**
 Vertical altitude of the floor inside the building (in meters)
 */
@property (nonatomic) double altitude;


/**
 URL where the floorplan can be downloaded
 @see Use -fetchMapFromFloor:withCompletion: instead of retrieving the image directly, as this will store the image on cache, so it's available to you later on.
 */
@property (nonatomic) SITURL *mapURL;

@end
