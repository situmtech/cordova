//
//  SITIndoorLocation.h
//  SitumSDK
//
//  Created by Abraham on 4/3/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITIndoorPoint.h"

/**
 *  SITIndoorLocation represents position information received from SITIndoorLocationManager.
 */
@interface SITIndoorLocation : NSObject
/**
 *  Milliseconds since epoch representing the last update reported by SITIndoorLocationManager
 */
@property (nonatomic, strong) NSNumber *time;
/**
 *  Position over x-axis.
 */
@property (nonatomic, strong) NSNumber *x;
/**
 *  Position over y-axis.
 */
@property (nonatomic, strong) NSNumber *y;
/**
 *  Heading toward the user is moving.
 */
@property (nonatomic, strong) NSNumber *yaw;
/**
 *  The number of the floor the user is in.
 *  @discussion The number match the identifier property of SITIndoorLevel object
 */
@property (nonatomic, strong) NSNumber *floor;
/**
 *  Flag that indicates if the path between the actual location and the previous one is valid.
 *  @discussion The position is still valid even if this flag is false
 */
@property (nonatomic, strong) NSNumber *validTransition;
/**
 *  Incertainty value.
 */
@property (nonatomic, strong) NSNumber *radius;
/**
 *  Flag that indicated if the value of yaw is valid.
 */
@property (nonatomic, strong) NSNumber *validOrientation;

- (instancetype)initWithTime:(long long)time
                           X:(float)x
                           Y:(float)y
                         Yaw:(float)yaw
                       Floor:(int)floor
             validTransition:(int)validTransition
                      radius:(float)radius
            validOrientation:(int)validOrientation;

/**
 *  Method to construct a SITIndoorPoint to use  as a point to calcule routes to/from it
 *
 *  @return SITIndoorPoint object
 */
- (SITIndoorPoint *)indoorPoint;
@end
