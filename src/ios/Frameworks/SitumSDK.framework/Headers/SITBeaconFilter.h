//
//  SITBeaconFilter.h
//  SitumSDK
//
//  Created by A Barros on 5/9/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

/**
 The purpose of this class is to hold information of custom uuids
 */
@interface SITBeaconFilter : NSObject

/**
 Initialize a new object with the uuid string
 
 @param uuidString string containing an UUID
 
 @return a new initialized SITBeaconFilter object
 */
- (instancetype)initWithUUID:(NSString *)uuidString;

/**
 Initialize a new object with the uuid string and a custom identifier
 
 @param uuidString string containing an UUID
 @param identifier custom identifier (for example: com.situm.beacon)
 
 @return a new initialized SITBeaconFilter object
 */
- (instancetype)initWithUUID:(NSString *)uuidString
                  identifier:(NSString *)identifier;

/**
 Retrieve data of the store uuid
 */
- (NSString *)uuid;

/**
 Check syntax validation (UUID String must comform to the following format: 7AXB7475-6D73-6974-756D-736974756D15)
 */
- (BOOL)isValid;

/**
 Retrieve data of the identifier of the Filter (for example: com.situm.beacon)
 */
- (NSString *)identifier;

@end
