//
//  SITCalibrationManager.h
//  SitumSDK
//
//  Created by A Barros on 24/3/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import <SitumSDK/SitumSDK.h>
//#import "SITIndoorBuilding.h"
//#import "SITIndoorLevel.h"

typedef void (^SITCalibrationManagerOperationCalibrationCompletion)(BOOL success, NSArray *errorCalibrations, NSArray *successfulCalibrations);

@interface SITCalibrationManager : NSObject

- (instancetype)initWithIndoorLevels:(NSArray *)indoorLevels;


- (BOOL)startCalibrationForIndoorBuilding:(SITIndoorBuilding *)indoorBuilding
                                    level:(SITIndoorLevel *)indoorLevel;

- (BOOL)stopCalibration;

//- (void)updateWithProgress:(float)progress;

- (void)tappedAtX:(float)x
                Y:(float)y;

- (BOOL)undoLastOperation;

- (void)uploadCalibrations;

- (NSInteger)numberOfCalibrations:(NSArray *)indoorLevels;

- (NSArray *)calibrations:(NSArray *)indoorLevels;

- (BOOL)cancelCalibration;

- (NSInteger)numberOfPoints;


// Methods to upload selected calibrations
- (void)uploadCalibrations:(NSArray *)calibrations
            withCompletion:(SITCalibrationManagerOperationCalibrationCompletion)completion;

// Methods to remove selected calibrations
- (void)removeCalibrations:(NSArray *)calibrations
            withCompletion:(SITCalibrationManagerOperationCalibrationCompletion)completion;

@end
