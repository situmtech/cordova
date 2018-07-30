//
//  SITIndoorLocationError.h
//  SitumSDK
//
//  Created by A Barros on 9/10/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

extern NSString *const SITIndoorLocationErrorDomain;

extern NSString *const SITIndoorLocationErrorProblemKey;
extern NSString *const SITIndoorLocationErrorKindOfKey;
extern NSString *const SITIndoorLocationErrorReasonKey;
extern NSString *const SITIndoorLocationErrorSuggestionsKey;




/*
 *  KSITIndoorLocationError
 */
typedef NS_ENUM(int, KSITIndoorLocationError){
    
    /**
     *  Bluetooth errors
     */
    KSITIndoorLocationBluetoothNotAvailable = 1001, /* Bluetooth is not available */
    KSITIndoorLocationBluetoothOff = 1002, /* Bluetooth is Off*/
    
    /**
     *  Location errors
     */
    KSITIndoorLocationLocationDisabled = 2001, /* Location is disabled*/
    KSITIndoorLocationLocationNotAllowed = 2002, /* Location is not allowed */
    
    /**
     *  Internet errors
     */
    KSITIndoorLocationDidNotSendUpdate = 3001, /* Update could not be send */
    KSITIndoorLocationDidNotReceiveUpdate = 3002, /* Did not receive update */
    KSITIndoorLocationNetworkNotAvailable = 3003, /* Internet is not available */
    KSITIndoorLocationNetworkNotAllowed = 3004, /* Internet is not allowed */
    
    /**
     *  Other errors
     */
    KSITIndoorLocationUserNotInBuilding = 5001, /* User is not in the building */
    
    /**
     *  System error
     */
    kSITIndoorLocationSystemAlreadyStarted = 6001,
    
};