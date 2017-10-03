//
//  SITRouteStep.h
//  SitumSDK
//
//  Created by A Barros on 21/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITPoint.h"

/**
 A fragment of a route, described by an initial SITPoint (from) and a destination SITPoint (to). It also includes additional information about the fragment.
 */
@interface SITRouteStep : NSObject


/**
 Unique integer identifier of the step.
 @discussion it is unique between the steps of a particular route, not between different routes.
 */
@property (nonatomic) NSInteger index;


/**
 Start point of the step
 */
@property (nonatomic, strong) SITPoint *from;


/**
 End point of the step
 */
@property (nonatomic, strong) SITPoint *to;


/**
 Indicates if this is the first step of the route
 */
@property (nonatomic) BOOL isFirst;


/**
 Indicates if this is the last step of the route
 */
@property (nonatomic) BOOL isLast;


/**
 Integer identifier of the next step of the route (if any)
 */
@property (nonatomic) NSInteger nextStepIndex;


/**
 Distance between from and to.

 @return distance in meters between from and to.
 */
- (float)stepDistance;


/**
 Distance to destination.

 @return distance between from and the last point of the route.
 */
- (float)distanceToGoal;


/**
 Indicates if the route has more steps

 @return BOOL value that indicates if the route has more steps (YES) or not (NO)
 */
- (BOOL)hasNext;

/**
 Private method

 @param index index of the step.
 @param from origin of the step.
 @param to destination of the step.
 @param isFirst indicates if the step is the first of the route.
 @param isLast indicates if the step is the last of the route.
 @param nextStepIndex index of the next step of the route (if any).
 @param stepDistance Distance between from and to.
 @param distanceToGoal distance between from of the step and destination of the route
 @return SITRouteStep object
 @discussion You should not create instances of this class directly. You you use the ones returned by SITDirectionsManager instead.
 */
- (instancetype)initWithIndex:(NSInteger)index
                         from:(SITPoint *)from
                           to:(SITPoint *)to
                      isFirst:(BOOL)isFirst
                       isLast:(BOOL)isLast
                nextStepIndex:(NSInteger)nextStepIndex
                 stepDistance:(float)stepDistance
               distanceToGoal:(float)distanceToGoal;



@end
