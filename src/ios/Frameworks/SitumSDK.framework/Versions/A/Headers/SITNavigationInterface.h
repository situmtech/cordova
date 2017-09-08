//
//  SITNavigationInterface.h
//  SitumSDK
//
//  Created by A Barros on 21/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#ifndef SITNavigationInterface_h
#define SITNavigationInterface_h

#import "SITLocation.h"
#import "SITRoute.h"
#import "SITNavigationProgress.h"
#import "SITNavigationRequest.h"

/**
 Interface that every object that provides navigation must implement.
 */
@protocol SITNavigationInterface <NSObject>

/**
 Set the navigation parameters to receive navigation updates.

 @param request SITNavigationRequest object.
 */
- (void)requestNavigationUpdates:(SITNavigationRequest *)request;

/**
 Update the current location to compute the progress of a user with respect to a route.

 @param location current location. You should introduced the locations provided by the SITLocationManager through its delegate interface.
 */
- (void)updateWithLocation:(SITLocation *)location;

/**
 Stops providing navigation updates and deletes 
 */
- (void)removeUpdates;

/**
 Check if navigation manager is locaded with a valid request

 @return boolean value that determines if the SITNavigationManager is loaded with a valid request (YES) or not (NO)
 */
- (BOOL)isRunning;

@end

/**
 Protocol for SITNavigationManager. Use this to listen for route progression related callbacks.
 */
@protocol SITNavigationDelegate <NSObject>


/**
 Called when an error is encountered.

 @param navigationManager navigation provider.
 @param error error that describes the problematic situation.
 */
- (void)navigationManager:(id<SITNavigationInterface>)navigationManager
         didFailWithError:(NSError *)error;


/**
 Called when a user progresses on a route

 @param navigationManager navigation provider (object that called this method)
 @param progress object containing information about current progress on route
 @param route SITRoute that the user is following
 */
- (void)navigationManager:(id<SITNavigationInterface>)navigationManager
        didUpdateProgress:(SITNavigationProgress *)progress
                  onRoute:(SITRoute *)route;


/**
 Called when location has reached the last point on a route.

 @param navigationManager navigation provider (object that called this method).
 @param route SITRoute that the user is following.
 @discussion arriving to the destination means the user is less than distanceToGoalThreshold meters (defined in SITNavigationRequest) to the goal.
 */
- (void)navigationManager:(id<SITNavigationInterface>)navigationManager
destinationReachedOnRoute:(SITRoute *)route;


/**
 Called when location has deviated from route enough to warrant a re-routing.

 @param navigationManager navigation provider (object that called this method).
 @param route SITRoute that the user is following.
 @discussion outside the route means the user is more the outsideRouteThreshold meters (defined in SITNavigationRequest) to the route.
 */
- (void)navigationManager:(id<SITNavigationInterface>)navigationManager
         userOutsideRoute:(SITRoute *)route;
@end

#endif /* SITNavigationInterface_h */
