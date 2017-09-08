//
//  SITBeaconManager.h
//  SITIndoorPositioning
//
//  Created by Abraham on 23/2/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

@import CoreLocation;

#import "SITProductFlags.h"

@class SITMSitumMessage;

/*
#import "ESTBeaconManager.h"
#import "ESTBeaconRegion.h"
*/
@class SITBeacon;
@class SITBeaconManager;
@protocol SITBeaconManagerDelegate <NSObject>

@optional

- (void)beaconManager:(SITBeaconManager *)beaconManager
    didFailsWithError:(NSError *)error;

//// Critical messages
//- (void)beaconManagerBluetoothIsNotAvailable; // Bluetooth is not available
//- (void)beaconManagerBluetoothIsOff; // Bluetooth is Turned off
//- (void)beaconManagerBluetoothIsNotAllowed; // Permissions not given
//
//// Warning messages
//- (void)beaconManagerDidNotRangeBeaconsInLast:(NSTimeInterval )seconds;
//
//// State Messages
//- (void)beaconManagerBluetoothActive;


@end




typedef enum : NSUInteger {
    SitumBeaconManagerStateUnknown = 0,
    SitumBeaconManagerStateStopped,
    SitumBeaconManagerStateStarted,
} SitumBeaconManagerStates;

typedef void (^SITBeaconManagerBeaconReportBeaconsHandler)(CLRegion *region, NSArray *beacons, NSArray *sitBeacons, NSDate *timestamp);

@interface SITBeaconManager : NSObject

@property (nonatomic, weak) id<SITBeaconManagerDelegate> delegate;

- (void)startReportingBeaconMeasuresWithHandler:(SITBeaconManagerBeaconReportBeaconsHandler)beaconManagerBeaconReportBeaconsHandler;

- (void)stopReportingBeaconMeasures;

- (void)start;

- (void)stop;

#ifdef SITUM_PROTOBUF

- (void)completeDataInMessage:(SITMSitumMessage *)message
            withInfoSinceDate:(NSDate *)date;

#else 

- (NSData *)lastUpdatedDataSince:(NSDate *)date;

#endif

- (void)removeDataSince:(NSDate *)date;

//- (void)generateMacsFile;

- (NSDictionary *)contentsOfBlemacsAndBleFiles;

- (NSData *)contentsOfBleFile;

- (BOOL)checkBuildingCoordinate:(CLLocationCoordinate2D)buildingCoordinate;

- (BOOL)checkings;

@end
