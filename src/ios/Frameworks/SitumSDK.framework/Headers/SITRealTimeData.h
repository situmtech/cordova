//
//  SITRealTimeData.h
//  SitumSDK
//
//  Created by A Barros on 7/6/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITLocation.h"

/**
 A data object that provides information about the location of users in real time
 */
@interface SITRealTimeData : NSObject

/**
 Array of locations at the moment.
 @discussion Note that each SITLocation object contains a SITLocation#deviceId property that enables to update locations from the same user.
 @discussion If the phone asking for realtime updates is being located, it will be included on the SITRealTimeData#locations array. You can discard it by filtering by deviceId. The deviceId of the own phone is available at SITServices
 */
@property (nonatomic, strong) NSArray<SITLocation *> *locations;

@end
