//
//  SITCoordinateConverter.h
//  SitumSDK
//
//  Created by A Barros on 2/3/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITAngle.h"
#import "SITLocation.h"
#import "SITPoint.h"
#import "SITDimensions.h"
#import "SITBounds.h"

@interface SITCoordinateConverter : NSObject

- (instancetype)initWithDimensions:(SITDimensions *)dimensions
                            center:(CLLocationCoordinate2D)center
                             rotation:(SITAngle *)rotation;

- (SITCartesianCoordinate *)toCartesianCoordinate:(CLLocationCoordinate2D)coordinate;

- (CLLocationCoordinate2D)toLocationCoordinate:(SITCartesianCoordinate *)cartesianCoordinate;

- (SITAngle *)toAngle:(SITAngle *)cartesianAngle;

- (SITAngle *)toCartesianAngle:(SITAngle *)angle;

- (SITBounds)bounds;

- (SITBounds)rotatedBounds;

@end
