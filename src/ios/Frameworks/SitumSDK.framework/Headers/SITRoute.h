//
//  SITRoute.h
//  SitumSDK
//
//  Created by A Barros on 21/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITRouteStep.h"
#import "SITIndication.h"


/**
 Route between two points. From and destination must be inside the same building.
 */
@interface SITRoute : NSObject


/**
 The initial point of the route.
 */
@property (nonatomic, readonly) SITPoint *origin;

/**
 The destination point of the route.
 */
@property (nonatomic, readonly) SITPoint *destination;


/**
 Parameters associated with the request. 
 Use accessible key to see if the route has been
 */
@property (nonatomic, readonly) NSDictionary *options;

/**
 Steps of the route to arrive from origin to destination.
 */
@property (nonatomic, strong, readonly) NSArray *routeSteps;

/**
 Indications to follow.
 */
@property (nonatomic, strong, readonly) NSArray *indications;


/**
 Constructor.

 @param origin from point.
 @param destination destination point.
 @param routeSteps steps to arrive from origin to destination.
 @param indications indications the user should follow to arrive from origin to destination.
 @param distance distance of the whole route
 @param options additional information of the route.
 @return initialized object.
 @discussion You should not use this method. Instead you should user the route objects provided by SITDirectionsManager.
 */
- (instancetype)initWithOrigin:(SITPoint *)origin
                   destination:(SITPoint *)destination
                    routeSteps:(NSArray *)routeSteps
                   indications:(NSArray *)indications
                      distance:(float)distance
                       options:(NSDictionary *)options;


/**
 Retrieve the indication for a particular step of the route.

 @param step step inside the route
 @return indication associated with the step (if valid step).
 */
- (SITIndication *)indicationForStep:(SITRouteStep *)step;


/**
 Complete distance of the route.

 @return distance of the route (meters).
 */
- (float)distance;


/**
 Estimated time of the route.

 @return time to the goal (seconds)
 */
- (float)timeToGoal;
@end
