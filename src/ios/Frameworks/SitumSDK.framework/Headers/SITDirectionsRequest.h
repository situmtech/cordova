//
//  SITDirectionsRequest.h
//  SitumSDK
//
//  Created by A Barros on 21/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITPoint.h"
#import "SITLocation.h"


/**
 Parameters to request a route with SITDirectionsManager.
 */
@interface SITDirectionsRequest : NSObject


/**
 Identifier of the request. (You should not fill this property, it will be completed automatically for you).
 */
@property (nonatomic, readonly) NSInteger requestID;


/**
 Initial point of the route (Outdoor points are not allowed for now. Both origin and destination should belong to the same building).
 */
@property (nonatomic, readonly) SITPoint *origin;

/**
 Destination point of the route (Outdoor points are not allowed for now. Both origin and destination should belong to the same building).
 */
@property (nonatomic, readonly) SITPoint *destination;


/**
 Additional options to be considered when computing a route.
 You can use the following keys to modify the behaviour of computing a route:
 key: accessible -> BOOL value that indicates if route has to be suitable for wheel chairs (YES) or not (NO) (this is the default case)
 */
@property (nonatomic, readonly) NSDictionary *options;


/**
 Constructor.

 @param requestID should be empty (0).
 @param location SITLocation object (usually an object provided by SITLocationManager).
 @param destination the end point of the route.
 @param options additional parameters to modify the internal behaviour of the route (see options).
 @return initialized object.
 */
- (instancetype)initWithRequestID:(NSInteger)requestID
                         location:(SITLocation *)location
                      destination:(SITPoint *)destination
                          options:(NSDictionary *)options;


/**
 Constructor.

 @param requestID requestID should be empty (0).
 @param origin initial point of the route.
 @param destination the end point of the route.
 @param options additional parameters to modify the internal behaviour of the route (see options).
 @return initialized object.
 */
- (instancetype)initWithRequestID:(NSInteger)requestID
                           origin:(SITPoint *)origin
                      destination:(SITPoint *)destination
                          options:(NSDictionary *)options;



@end
