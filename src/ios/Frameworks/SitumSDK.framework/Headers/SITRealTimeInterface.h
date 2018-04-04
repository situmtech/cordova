//
//  SITRealTimeInterface.h
//  SitumSDK
//
//  Created by A Barros on 7/6/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#ifndef SITRealTimeInterface_h
#define SITRealTimeInterface_h

#import "SITRealTimeRequest.h"
#import "SITRealTimeData.h"


/**
 Generic interface to control realtime operations
 */
@protocol SITRealTimeInterface <NSObject>


/**
 Provides a method for requesting users location in real time
 
 @param request SITRealTimeRequest object
 */
- (void)requestRealTimeUpdates:(SITRealTimeRequest *)request;


/**
 Stops delivering realtime location updates of the users
 */
- (void)removeRealTimeUpdates;

@end


/**
 Generic interface that provides information about the location of the users in Real Time
 */
@protocol SITRealTimeDelegate <NSObject>


/**
 Delegate method that provides information of the user

 @param realTimeManager manager object
 @param realTimeData encapsulate object with locations of the users
 */
- (void)realTimeManager:(id<SITRealTimeInterface>)realTimeManager
 didUpdateUserLocations:(SITRealTimeData *)realTimeData;


/**
 Delegate method that informs an internal error happends while requesting information

 @param realTimeManager manager object
 @param error error object
 */
- (void)realTimeManager:(id<SITRealTimeInterface>)realTimeManager
 didFailWithError:(NSError *)error;

@end


#endif /* SITRealTimeInterface_h */
