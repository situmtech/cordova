//
//  SITLocationManager.h
//  SitumSDK
//
//  Created by A Barros on 27/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITLocationInterface.h"
#import "SITLocationError.h"


/**
 SITLocationManager
 The SITLocationManager class is the central point for configuring the delivery of location- and headind
 related events to your app. You use the shared instance of this class to establish the parameters that determine when
 location and heading events should be delivered and to start and stop the actual delivery of those events.
 
 Set delegate to listen for location updates.
 */
@interface SITLocationManager : NSObject <SITLocationInterface>


/**
 Singleton instance

 @return An initialized object
 @discussion You should always use this method to obtain the manager object and should not try to create instances directly
 */
+ (instancetype)sharedInstance;


/**
 Object that conforms to the SITLocationDelegate Protocol
 */
@property (nonatomic, weak) id<SITLocationDelegate> delegate;


@end
