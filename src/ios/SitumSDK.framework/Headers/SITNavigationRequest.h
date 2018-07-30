//
//  SITNavigationRequest.h
//  SitumSDK
//
//  Created by A Barros on 22/3/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#ifndef SITNavigationRequest_h
#define SITNavigationRequest_h

#import <Foundation/Foundation.h>

#import "SITRoute.h"

/**
 Class that stores relevant information to request navigation updates.
 */
@interface SITNavigationRequest : NSObject

/**
 Distance in meters at which will be consider that the user has arrived to the destination of a route
 
 @discussion The minimum value for this parameter is 3 meters and maximum is 15 meters. Default value is 10 meters.
 */
@property (nonatomic) NSInteger distanceToGoalThreshold;

/**
 Distance in meters at which the Navigation Component should consider that the user is outside a route and it's recommended to recompute a new one.
 
 @return distance in meters.
 @discussion The minimum value is 3 meters and maximum is 15 meters. Default value is 9 meters.
 */
@property (nonatomic) NSInteger outsideRouteThreshold;

/**
 Distance in meters at which will be consider that the user has arrived to a change floor indication
 
@return distance in meters.
 @discussion Default value is 10 meters.
 */
@property (nonatomic) NSInteger distanceToFloorChangeThreshold;

/**
 Distance in meters at which will be consider that the user has arrived to the next indication
 
 @return distance in meters.
 @discussion Default value is 5 meters.
 */
@property (nonatomic) NSInteger distanceToChangeIndicationThreshold;

/**
 SITRoute object considered to navigate
 */
@property (nonatomic, strong) SITRoute *route;

/**
 Time to wait between indications
 
 @return time in milliseconds
 @discussion Default value is 0 milliseconds, which means a new indication will be returned every time a new position is computed.
 If the value is different than 0 the indications may not be synchronized with the SITRouteStep in SITNavigationProgress.
 */
@property (nonatomic) NSInteger indicationsInterval;

/**
 Time to wait until the first indication is returned
 
 @return time in milliseconds
 @discussion Default value is 0 milliseconds, which means the first indication will be returned as soon as it is calculated.
 */
@property (nonatomic) NSInteger timeToFirstIndication;

/**
 The step that will be used to round indications distance.
 
 @return step in meters
 @discussion Default value is 0, which means the indications will not be rounded.
 */
@property (nonatomic) NSInteger roundIndicationsStep;

/**
 The distance that will be taken into account in order to return the next N indications
 
 @return distance in meters
 @discussion Default value is 0. The next indication will always be returned, no matter the value of this parameter. This will only be taken into account successive indications.
 */
//@property (nonatomic) NSInteger maxIndicationsDistance;



/**
 Initializes a request to navigate through a route

 @param route the route (returned by SITDirectionManager) you want to navigate
 @return initialized request
 @discussion this request will have default values for the distanceToGoalThreshold and outsideRouteThreshold.
 */
- (instancetype)initWithRoute:(SITRoute *)route;

/**
 Set a threshold within which the next indication will be returned
 
 @param distanceToChangeIndicationThreshold distance to change indication threshold
 */
-(void)setDistanceToChangeIndicationThreshold:(NSInteger)distanceToChangeIndicationThreshold;

/**
 Set a threshold within which the next change floor indication will be returned
 
 @param distanceToChangeFloorThreshold distance to change floor threshold
 */
-(void)setDistanceToChangeFloorThreshold:(NSInteger)distanceToChangeFloorThreshold;

/**
 Set a threshold within which the goal change floor indication will be considered as reached
 
 @param distanceToGoalThreshold distance to change goal threshold
 */
- (void)setDistanceToGoalThreshold:(NSInteger)distanceToGoalThreshold;

/**
 Set a threshold outise which the position will be considered out of the route
 
 @param outsideRouteThreshold outside route threshold
 */
- (void)setOutsideRouteThreshold:(NSInteger)outsideRouteThreshold;

/**
 Sets indications frequency. By default, indications will be delivered every time a new position is calculated.
 
 @param millis desired indications interval in milliseconds.
 */
- (void)setIndicationsInterval:(NSInteger)millis;

/**
 Sets the time to wait before returning the first indication. A value of 5000 or more is recommended when positioning is started along with navigation, in order to provide more accurate and stable indications.
 
 @param millis desired time to wait before returning the first indication.
 */
- (void) setTimeToFirstIndication:(NSInteger)millis;

/**
 Sets the step that will be used to round indications distance. For example, with a∫ step of 5, a 13 meters indication will be transformed into a 15 meters indication.
 
 @param meters desired step for indications distance in meters.
 */
- (void) setRoundIndicationsStep:(NSInteger)meters;

/**
 Sets de distance that will be taken into account in order to return the next N indications.
 
 @param meters desired amount of meters for which next indications will be returned.
 */
//- (void) setMaxIndicationsDistance:(NSInteger)meters;

@end
#endif
