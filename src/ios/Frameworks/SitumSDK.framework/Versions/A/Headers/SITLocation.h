//
//  SITLocation.h
//  SitumSDK
//
//  Created by A Barros on 21/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITPoint.h"
#import "SITAngle.h"


/**
 The quality of the location. Is only used indoor.

 - kSITLow: The quality of the location is low.
 - kSITHigh: The quality of the location is high.
 */
typedef NS_ENUM(NSInteger, kSITQualityValues)
{
    kSITLow = 0,
    kSITHigh,
};



/**
 Describes a geographical position of the user, either indoor or outdoor.
 A valid indoor location has floorIdentifier and cartesianCoordinate in its position property.
 */
@interface SITLocation : NSObject


/**
 The time at which this location was determined.
 */
@property (nonatomic) NSTimeInterval timestamp;


/**
 Geographical coordinate information.
 */
@property (nonatomic, strong) SITPoint *position;


/**
 The direction in which the device is traveling in geographical coordinate system (can be directly applied to a map provider)
 */
@property (nonatomic, strong) SITAngle *bearing;


/**
 The direction in which the device is traveling in cartesian coordinate system.
 */
@property (nonatomic, strong) SITAngle *cartesianBearing;


/**
 Return an indicative of quality calculated with accuracy and building size.
 @discussion Only used in indoor.
 */
@property (nonatomic) kSITQualityValues quality;


/**
 Return the accuracy radius (in meters).
 */
@property (nonatomic) float accuracy;


/**
 Multiple providers can create locations.
 This property determines which one has determined the location.
 */
@property (nonatomic, strong) NSString *provider;


/**
 Private method. Contructor.

 @param timestamp when the location was determined.
 @param position geographical coordinate information.
 @param bearing the direction in which the device is traveling in geographical coordinate system.
 @param cartesianBearing The direction in which the device is traveling in cartesian coordinate system.
 @param quality indicative of quality calculated with accuracy and building size.
 @param accuracy ccuracy radius (in meters).
 @param provider multiple providers can create locations. Determines which one has determined the location.
 @return initialized location
 @discussion You should not use this method. Instead you should work with locations provided by SITLocationManager. 
 */
- (instancetype)initWithTimestamp:(NSTimeInterval)timestamp
                         position:(SITPoint *)position
                          bearing:(float)bearing // Need to be degrees
                cartesianBearing:(float)cartesianBearing // Need to be radians
                          quality:(kSITQualityValues)quality
                         accuracy:(float)accuracy
                         provider:(NSString *)provider;

@end
