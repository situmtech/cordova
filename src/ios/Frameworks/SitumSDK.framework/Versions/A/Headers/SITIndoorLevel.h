//
//  SITIndoorLevel.h
//  SitumSDK
//
//  Created by Abraham on 3/3/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#ifndef SITIndoorLevel_h
#define SITIndoorLevel_h

#import <Foundation/Foundation.h>

/**
 *  This class describes the properties from a level of a building.
 *  @discussion You should not create objects of this class. Instead you should retrieve them using the
 *  appropiate method of the SITCommunicationManager class.
 */
@interface SITIndoorLevel : NSObject DEPRECATED_MSG_ATTRIBUTE("Use SITFloor object instead.");


/**
 Unique identifier of the level
 */
@property (nonatomic, strong) NSNumber *identifier DEPRECATED_MSG_ATTRIBUTE("Use identifier value of a SITFloor object instead.");


/**
 Identifier of the Building this level belongs to
 */
@property (nonatomic, strong) NSNumber *project_identifier DEPRECATED_MSG_ATTRIBUTE("Use buildingIdentifier value of a SITFloor object instead.");


/**
 *  Number
 */
@property (nonatomic, strong) NSNumber *level DEPRECATED_MSG_ATTRIBUTE("Use level value of a SITFloor object instead.");
/**
 *  Name
 */
@property (nonatomic, strong) NSString *name DEPRECATED_MSG_ATTRIBUTE("Information not publicly available. Use level property of a SITFloor object.");;
/**
 *  Details
 */
@property (nonatomic, strong) NSString *detail DEPRECATED_MSG_ATTRIBUTE("Information not publicly available. The SDK will handle this information internally for you.");;

/**
 *  Scale of the map image (px/meter)
 */
@property (nonatomic, strong) NSNumber *scale DEPRECATED_MSG_ATTRIBUTE("Use scale value of a SITFloor object instead.");

@property (nonatomic, strong) NSString *map_url DEPRECATED_MSG_ATTRIBUTE("Use mapURL value of a SITFloor object instead.");

@property (nonatomic, strong) NSString *server_map_url DEPRECATED_MSG_ATTRIBUTE("Information not publicly available. The SDK will handle this information internally for you.");

/**
 *  Convinient method to create a string with the numeric information of the level property
 *
 *  @return String with the numeric information of the level property
 */
- (NSString *)shortName;

@end
#endif
