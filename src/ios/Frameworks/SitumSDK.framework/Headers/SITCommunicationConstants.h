//
//  SITCommunicationConstants.h
//  SitumSDK
//
//  Created by A Barros on 30/8/16.
//  Copyright © 2016 Situm. All rights reserved.
//

#ifndef SITCommunicationConstants_h
#define SITCommunicationConstants_h

/**
 *  \memberof SITCommunicationManager
 *  The presence of this key determines if a request is directed to the network system or the file system (cache).
 *  The value of this key should be an NSNumber with a BOOL value on it.
 *  If YES the request will be directed to the network system. Otherwise (NO or absence of key) it will be directed to the file system.
 */
extern NSString *const SITForceRequestKey; 

/**
 *  \memberof SITCommunicationManager
 *  The presence of this key indicates that a filter process will be performed before returning the result of fetching POIs (indoor or outdoor).
 *  The value of this key should be an array of SITKeyValueFilter.
 */
extern NSString *const SITCustomFiltersKey;

/**
 *  \memberof SITCommunicationManager
 *  The presence of this key indicates how to combine the results of filtering POIs (indoor/outdoor) by its custom fields when multiple filters are applied.
 *  The value of this key is an NSNumber with one of the values defined on the enum kSITKeyValueFilterBy
 */
extern NSString *const SITKeyValueFilterMode;

#endif /* SITCommunicationConstants_h */
