//
//  SITLocationRequest.h
//  SitumSDK
//
//  Created by A Barros on 27/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

/**
 *  SITLocationPriority
 *
 *  @discussion: Represent the tradeoff between battery comsumption and accuracy (Coming soon).
 */
typedef NS_ENUM(int, SITLocationPriority){

    kSITHighAccuracy = 0,
    
    kSITBalancedPowerAccuracy,
    
    kSITLowPower
};

/**
 *  SITLocationProvider
 *
 *  @discussion: Multiple providers can determined the location. You can force to work with one. (Coming soon).
 */
typedef NS_ENUM(int, SITLocationProvider){
    /**
     *  Localization can use the best provider available at any given moment (default).
     */
    kSITHybridProvider = 0,
    
    /**
     *  Use only cloud provider.
     */
    kSITCloudProvider,
    
    /**
     Use only phone provider. (coming soon).
     */
    kSITInPhoneProvider
};


/**
 A data object that contains parameters for the location service (SITLocationManager)
 */
@interface SITLocationRequest : NSObject


/**
 One of SITLocationPriority (coming soon).
 */
@property (nonatomic, readwrite) SITLocationPriority priority;


/**
 One of SITLocationProvider (coming soon).
 */
@property (nonatomic, readwrite) SITLocationProvider provider;


/**
 The time interval at which location will be delivered, default is 1 second (coming soon).
 */
@property (nonatomic, readwrite) NSInteger updateInterval;


/**
 Unique identifier of the building in which you want to locate a user. (This property is mandatory).
 */
@property (nonatomic, strong) NSString *buildingID;


/**
 The thread where the location updates will be provided. If not provided, the main thread will be used.
 */
@property (nonatomic, strong) NSOperationQueue *operationQueue;


/**
 Additional options to modify the internal operation of the Location provider (private usage only).
 */
@property (nonatomic, strong) NSDictionary *options;


/**
 Constructor.

 @param priority one of SITLocationPriority
 @param provider one of SITLocationProvider
 @param updateInterval The time interval at which location will be delivered
 @param buildingID unique identifier of the building in which you want to locate a user. (This property is mandatory).
 @param operationQueue the thread where the location updates will be provided. If not provided, the main thread will be used.
 @param options additional options to modify the internal operation of the Location provider (private usage only).
 @return initialized object.
 */
- (instancetype)initWithPriority:(SITLocationPriority)priority
                provider:(SITLocationProvider)provider
                  updateInterval:(NSInteger)updateInterval
                      buildingID:(NSString *)buildingID
                  operationQueue:(NSOperationQueue *)operationQueue
                         options:(NSDictionary *)options;

/**
 Check if the request is valid.

 @return BOOL value that indicates if the request is valid (YES) or not (NO).
 */
- (BOOL)isValid;
@end
