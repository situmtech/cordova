//
//  SITUserLocation.h
//  SitumSDK
//
//  Created by A Barros on 7/6/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITLocation.h"


/**
 Provides information about the position of a device
 */
@interface SITUserLocation : NSObject


/**
 Identifier of the device
 */
@property (nonatomic, strong) NSString *deviceId;


/**
 Position of the device
 */
@property (nonatomic, strong) SITLocation *location;

@end
