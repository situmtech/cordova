//
//  SITIndoorRouteStep.h
//  SitumSDK
//
//  Created by A Barros on 16/4/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITIndoorPoint.h"

/**
 *  An object that describes a step in a route between two SITIndoorPoint 's
 */
@interface SITIndoorRouteStep : NSObject

/**
 *  A SITIndoorPoint of a SITIndoorBuilding
 */
@property (nonatomic, strong) SITIndoorPoint *indoorPoint;

/**
 *  The distance to the next step
 */
@property (nonatomic, strong) NSNumber *cost;

/**
 *  The SITIndoorPoint where this steps ends
 */
@property (nonatomic, strong) SITIndoorPoint *destination;

/**
 *  Initializer to create SITIndoorRouteStep objects
 *  @discussion You should not use this class directly. Instead you should use the ones returned when computing routes (methods on class SITGraph).
 *
 *  @param indoorPoint SITIndoorPoint object
 *  @param cost        Distance to the next step
 */
- (instancetype)initWithIndoorPoint:(SITIndoorPoint *)indoorPoint
                               cost:(NSNumber *)cost;

/**
 *  Designated initializer to create SITIndoorRouteStep objects
 *  @discussion You should not use this class directly. Instead you should use the ones returned when computing routes (methods on class SITGraph).
 *
 *  @param indoorPoint SITIndoorPoint object
 *  @param destination SITIndoorPoint object
 *  @param cost        Distance to the next step
 */
- (instancetype)initWithIndoorPoint:(SITIndoorPoint *)indoorPoint
                        destination:(SITIndoorPoint *)destination
                               cost:(NSNumber *)cost;

@end
