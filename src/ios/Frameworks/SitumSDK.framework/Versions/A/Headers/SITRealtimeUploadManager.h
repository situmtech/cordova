//
//  SITRealtimeUploadManager.h
//  SitumSDK
//
//  Created by Adrián Rodríguez on 18/6/18.
//  Copyright © 2018 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "SITLocation.h"

typedef NS_ENUM(NSInteger, SITRealtimeUpdateInterval) {
    SITUpdateIntervalRealtime = 1,
    SITUpdateIntervalFast = 5,
    SITUpdateIntervalNormal = 15,
    SITUpdateIntervalSlow = 25
};

/**
 This class is the main manager for uploading Realtime positions. It uses two different loggers to upload the realtime positions: Realtime Logger and Historic Logger.
 The first one runs a loop which executes an upload of the positions stored in the logger to the server with a fixed delay between uploads. The second one serves as storage
 when the internet connection is lost and uploads that positions when the connection is recovered.
 */
@interface SITRealtimeUploadManager : NSObject

/**
 This constructor returns a shared instance of the Realtime Upload Manager.
 
 @return Returns an initialized instance of the class.
 */
+ (instancetype) sharedInstance;

/**
 Starts the uploading process with an interval specified in the params.
 
 @param interval A value of SITRealtimeUpdateInterval that specifies the number of seconds between uploads.
 */
- (void) startWithUpdateInterval: (SITRealtimeUpdateInterval) interval;

/**
 Stops the locations uploading process.
 */
- (void) stop;

/**
 Stores a new location to be uploaded to the realtime server.
 
 @param locationToUpload A valid location object to be stored.
 */
- (void) storeLocation: (SITLocation*) locationToUpload;

@end
