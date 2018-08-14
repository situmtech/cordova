//
//  SITRealTimeManager.h
//  SitumSDK
//
//  Created by A Barros on 7/6/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITRealTimeInterface.h"

/**
 Central point for accessing the location of the users in real time
 */
@interface SITRealTimeManager : NSObject <SITRealTimeInterface>


/**
 Call this method to receive a reference to an initialized object of this class

 @return shared manager object
 @discussion You should not try to initialized multiple objects of this class using alloc:init
 */
+ (instancetype)sharedManager;


/**
 Delegate property to receive callbacks
 */
@property(nonatomic, assign) id <SITRealTimeDelegate> delegate;

/**
 Rate at which information will be refreshed (in seconds)
 
 @discussion Check SITRealTimeRequest to know minimun and maximum values of this parameter
 */
- (NSInteger)updateInterval;

@end
