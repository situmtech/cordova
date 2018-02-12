//
//  SITLocationInterface.h
//  SitumSDK
//
//  Created by A Barros on 27/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#ifndef SITLocationInterface_h
#define SITLocationInterface_h

#import "SITLocationRequest.h"
#import "SITLocation.h"

/**
 *  SITLocationState
 *
 *  @discussion: Represents the current state of the SITLocationManager.
 */
typedef NS_ENUM(int, SITLocationState){
    /**
     *  Localization is not running.
     */
    kSITLocationStopped = 0,
    
    /*
     System is still calculating.
     */
    kSITLocationCalculating,
    
    /*
     System cannot determine where the user is in that building.
     */
    kSITLocationUserNotInBuilding,
    
    /**
     *  Localization is running.
     */
    kSITLocationStarted,
};


/**
 General interface every object providing location should conform to.
 */
@protocol SITLocationInterface <NSObject>

/**
 Provides APIs for requesting users current location.

 @param request SITLocationRequest object
 */
- (void)requestLocationUpdates:(nonnull SITLocationRequest *)request;


/**
 State of the shared instance.

 @return SITLocationState value that indicates the actual state of the system
 */
- (SITLocationState)state;


/**
 Stops the delivery of location updates.
 
 @discussion This method has no efect if the system is not running.
 */
- (void)removeUpdates;

@end


/**
 Delegate for SITLocationManager. Use this to listen for location related callbacks.
 */
@protocol SITLocationDelegate <NSObject>

#pragma mark - location related events


/**
 Called when user’s location changes.

 @param locationManager location provider
 @param location current location
 */
- (void)locationManager:(nonnull id<SITLocationInterface>)locationManager
      didUpdateLocation:(nonnull SITLocation *)location;


/**
 Called when an error is encountered.

 @param locationManager location provider
 @param error error description
 @discussion Invoked when an error has occurred. Error types are defined in "SITLocationError.h".
 */
- (void)locationManager:(nonnull id<SITLocationInterface>)locationManager
       didFailWithError:(nonnull NSError *)error;

/**
 Called when the system changes it's internal state

 @param locationManager location provider
 @param state state of the system (one of SITLocationState enum values)
 */
- (void)locationManager:(nonnull id<SITLocationInterface>)locationManager
         didUpdateState:(SITLocationState)state;
@end

#endif /* SITLocationInterface_h */
