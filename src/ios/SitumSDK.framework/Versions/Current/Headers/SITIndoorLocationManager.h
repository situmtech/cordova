//
//  SITIndoorLocationManager.h
//  SITIndoorLocation
//
//  Created by Abraham on 23/2/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITIndoorLocationError.h"
#import "SITIndoorLocationInterface.h"

/*!
 * This class provide you with methods to  of entry to the SitumSDK.
 */
__attribute__ ((deprecated))
@interface SITIndoorLocationManager : NSObject DEPRECATED_MSG_ATTRIBUTE("Use SITLocationManager instead");

/**
 *  Indoor Location Manager State
 *
 *  @return the current state of the indoor location manager
 */
@property (nonatomic, readonly) SITIndoorLocationManagerStates state ;

/**
 *  Object implementing SITIndoorLocationManagerDelegate Protocol
 */
@property (nonatomic, weak) id<SITIndoorLocationManagerDelegate> delegate DEPRECATED_MSG_ATTRIBUTE("Use SITLocationManager delegate property instead");;


/*!
 *  Call this method to receive a reference to an initialized object of this class
 *  @discussion You should always use this method to obtain the manager object and should not try to create instances directly.
 */
+ (instancetype)sharedManager DEPRECATED_MSG_ATTRIBUTE("Use sharedManager of the SITLocationManager class instead");

/*!
 *  Start reporting location
 *
 *  @param building              The building to which
 *  @param operationQueue        The queue to which the handler will be executed
 *  @param options               Dictionary containing options parameter that could modify how location works
 *  @param indoorLocationHandler Block of instructions to perform every time a new position is calculated
 *
 *  @return A Boolean value meaning if the operation was performed successfully or not.
 *  @discussion As a developer you should expect a new location every second.
 */
- (BOOL)startReportingIndoorLocationForBuilding:(SITIndoorBuilding *)building
                                        toQueue:(NSOperationQueue *)operationQueue
                                    withOptions:(NSDictionary *)options
                                    withHandler:(SITIndoorLocationHandler)indoorLocationHandler DEPRECATED_MSG_ATTRIBUTE("Use requestLocationUpdates of the SITLocationManager class instead");


/*!
 *  @brief  This method stops reporting locations
 *  @discussion Calling this method when the shared manager is in inactive state will produce NO results
 *
 *  @return A Boolean value meaning if the operation was performed successfully or not.
 */
- (BOOL)stopReportingIndoorLocation DEPRECATED_MSG_ATTRIBUTE("Use removeUpdates of the SITLocationManager class instead");

/**
 *  Tell the system to adjust following location updates to a particular route
 *
 *  @param route a SITIndoorRoute object
 *
 *  @return BOOL value indicating if the operation has been successfull (YES) or not (NO)
 */
- (BOOL)adjustLocationToRoute:(SITIndoorRoute *)route DEPRECATED_MSG_ATTRIBUTE("Use requestNavigationUpdates: of the SITNavigationManager to retrive progress of a route instead");

/**
 *  Tell the system to cancel adjusting location updates to a particular route
 *
 *  @return BOOL value indicating if the operating has been successfull (YES) or not (NO).
 */
- (BOOL)stopAdjustingLocation DEPRECATED_MSG_ATTRIBUTE("Use removeUpdates: of the SITNavigationManager to stop receiving progress updates");

/**
 *  Check if the system is adjusting locations to a particular route
 *
 *  @return BOOL value indicating if the location is being adjusted (YES) or not (NO)
 */
- (BOOL)isAdjustingLocation DEPRECATED_MSG_ATTRIBUTE("Use isRunning of the SITNavigationManager  to check receiving progress updates");
@end
