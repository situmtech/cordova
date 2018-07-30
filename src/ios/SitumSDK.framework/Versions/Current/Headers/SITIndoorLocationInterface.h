//
//  SITIndoorLocationInterface.h
//  SitumSDK
//
//  Created by A Barros on 27/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#ifndef SITIndoorLocationInterface_h
#define SITIndoorLocationInterface_h

/**
 *  SITIndoorLocationManagerState
 *
 *  @discussion: Represents the current state of the indoor localization manager.
 */
typedef NS_ENUM(int, SITIndoorLocationManagerStates){
    /**
     *  Indoor Localization is not running.
     */
    kSITIndoorLocationManagerStateStopped = 0,
    
    /**
     *  Indoor Localization is running.
     */
    kSITIndoorLocationManagerStateStarted,
};

@class SITIndoorBuilding, SITIndoorLevel, SITIndoorLocation, SITIndoorRoute;
@protocol SITIndoorLocationManagerDelegate;

/**
 *  The type of block callback for handling indoor locations data.
 *
 *  @param indoorLocation SITIndoorLocation object.
 *  @param error          NSError object in case there was a problem generating locations.
 */
typedef void (^SITIndoorLocationHandler)(SITIndoorLocation *indoorLocation, NSError *error);

#endif /* SITIndoorLocationInterface_h */
