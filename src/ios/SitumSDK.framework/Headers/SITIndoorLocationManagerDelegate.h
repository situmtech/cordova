//
//  SITIndoorLocationManagerDelegate.h
//  SitumSDK
//
//  Created by A Barros on 5/10/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

@class SITIndoorLocationManager;

/**
 *  Protocol to provide additional information of what happens inside the SITIndoorLocationManager
 */
@protocol SITIndoorLocationManagerDelegate <NSObject>

@optional

/**
 *  Positioning fails
 *
 *  @param indoorLocationManager instance object of SITIndoorLocationManager class
 *  @param error                 describes the problem and possibly the cause and solution of it
 */
- (void)indoorLocationManager:(SITIndoorLocationManager *)indoorLocationManager
             didFailWithError:(NSError *)error;

/**
 *  Called whenever the state of the SITIndoorLocationManager changes
 *
 *  @param indoorLocationManager instance object of SITIndoorLocationManager class
 *  @param state state of the SITIndoorLocationManager object
 */
- (void)indoorLocationManager:(SITIndoorLocationManager *)indoorLocationManager
              didChangeState:(NSInteger)state; // from stopped, started, paused(the same as stopped, whitout cleaning internal state)
@end
