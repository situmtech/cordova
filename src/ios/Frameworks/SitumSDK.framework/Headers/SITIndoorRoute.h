//
//  SITIndoorRoute.h
//  SitumSDK
//
//  Created by A Barros on 16/4/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

@class SITIndoorRouteStep;

/**
 *  Class that represents a the route between two SITIndoorPoints of a SITIndoorBuilding
 *  @discussion You should not use try to instantiate objects of this class. Instead you should use the ones returned with the execution of shortestRouteFromIndoorPoint:toIndoorPoint: in SITGraph objects
 */
@interface SITIndoorRoute : NSObject

/**
 *  An array of SITIndoorRouteSteps
 */
@property (nonatomic, readonly) NSMutableArray *steps;

/**
 *  Calculate the total distance of the SITIndoorRoute considering all the steps
 *
 *  @return float value in meters.
 */
- (float)length;

- (BOOL)isFirstStep:(SITIndoorRouteStep *)step;

- (BOOL)isLastStep:(SITIndoorRouteStep *)step;

- (NSArray *)stepsAtLevel:(NSNumber *)levelIdentifier;

/**
 *  Determines if the route is a valid one
 *
 *  @return BOOL value indicating if the route is valid (YES) or not (NO).
 */
- (BOOL)isValid;
@end
