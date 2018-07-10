//
//  SITNavigationProgress.h
//  SitumSDK
//
//  Created by A Barros on 21/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <AvailabilityMacros.h>

#import "SITIndication.h"
#import "SITPoint.h"
#import "SITRouteStep.h"
#import "SITLocation.h"


/**
 It provides information of the progress of a user while following a route.
 */
@interface SITNavigationProgress : NSObject

/**
 Unique identifier of the step inside a route.
 */
@property (nonatomic) NSInteger currentStepIndex;

/**
 Distance from from point to end of step.
 */
@property (nonatomic) float distanceToEndStep;

/**
 Time from from point to end of route.
 */
@property (nonatomic) float timeToEndStep;

/**
 Distance from from point to end of route.
 */
@property (nonatomic) float distanceToGoal;

/**
 Time estimation from from point to end of step.
 */
@property (nonatomic) float timeToGoal;

/**
 Closest point inside the route closer to the user's location.
 */
@property (nonatomic, strong) SITPoint *closestPointToRoute __attribute__((deprecated))DEPRECATED_MSG_ATTRIBUTE("Use closestLocationInRoute.position instead");

/**
 Closest location inside the route closer to the user's location.
 */
@property (nonatomic, strong) SITLocation *closestLocationInRoute;

/**
 The indication a user should follow in order to arrive the destination following a route.
 */
@property (nonatomic, strong) SITIndication *currentIndication;

/**
 The next indication a user should follow in order to arrive the destination following a route.
 */
@property (nonatomic, strong) SITIndication *nextIndication;

/**
 Distance in meters to the closest point in route
 */
@property (nonatomic) float distanceToClosestPointInRoute;

/**
 Current route step
 */
@property (nonatomic, strong) SITRouteStep *routeStep;

@end
