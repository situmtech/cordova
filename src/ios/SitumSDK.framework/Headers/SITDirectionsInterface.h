//
//  SITDirectionsInterface.h
//  SitumSDK
//
//  Created by A Barros on 21/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#ifndef SITDirectionsInterface_h
#define SITDirectionsInterface_h

#import "SITDirectionsError.h"
#import "SITDirectionsRequest.h"
#import "SITRoute.h"

/**
 Protocol that describes the interface every object providing directions should conform to.
 */
@protocol SITDirectionsInterface <NSObject>


/**
 Call this method to receive a reference to an initialized object of this class.
 
 @return You should always use this method to obtain the manager object and should not try to create instances directly.
 */
+ (instancetype)sharedInstance;

/**
 Request to compute the shortest route and indications from two points inside a building.

 @param request object that encapsulates information about the origin and the destination of the route.
 @discussion For now, this component only process requests where origin and destination are inside indoor areas at the same building.
 */
- (void)requestDirections:(SITDirectionsRequest *)request;

/**
 Clear all information of the requests being processed at the moment.

 @discussion This method cancel every pending SITDirectionRequest.
 */
- (void)reset;

@end

/**
 Delegate for SITDirectionsManager. Use this to listen for directions related callbacks.
 */
@protocol SITDirectionsDelegate <NSObject>

/**
 Called when an error is encountered.

 @param manager directions provider (object that called this method).
 @param request the SITDirectionsRequest that caused the error.
 @param error error that describes the problem.
 */
- (void)directionsManager:(id<SITDirectionsInterface>)manager
 didFailProcessingRequest:(SITDirectionsRequest *)request
                withError:(NSError *)error;

/**
 Called when a SITDirectionsRequest is being processed.

 @param manager directions provider (object that called this method).
 @param request the SITDirectionsRequest that has been processed.
 @param route SITRoute that best match the request.
 */
- (void)directionsManager:(id<SITDirectionsInterface>)manager
        didProcessRequest:(SITDirectionsRequest *)request
             withResponse:(SITRoute *)route;

@end

#endif /* SITDirectionsInterface_h */
