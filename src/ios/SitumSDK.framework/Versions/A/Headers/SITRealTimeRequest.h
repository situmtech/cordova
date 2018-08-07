//
//  SITRealTimeRequest.h
//  SitumSDK
//
//  Created by A Barros on 7/6/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>


/**
 A data object that contains parameters for the real time service (SITRealTimeManager) 
 */
@interface SITRealTimeRequest : NSObject


/**
 The identifier of the building (can be obtained on SITResource identifier property of a SITBuilding object)
 */
@property (nonatomic, strong) NSString *buildingIdentifier;


/**
 Integer value (in seconds) that determine the interval rate of refreshing
 @discussion This value cannot be smaller than 3 seconds nor greater than 20 seconds (In case you provide a number smaller than 3 seconds, this minimum will be applied. This is also de default value. In case you provide a value greater than 20 seconds, 20 seconds will be applied.
 */
@property (nonatomic) NSInteger updateInterval;

@end
