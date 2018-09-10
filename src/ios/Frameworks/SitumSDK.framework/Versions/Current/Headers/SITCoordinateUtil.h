//
//  SITCoordinateUtil.h
//  SitumSDK

//  Created by: Cristina S. Barreiro on 10/09/2018
//  Copyright © 2018 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreLocation/CoreLocation.h>

@interface SITCoordinateUtil : NSObject

+ (double) distanceBetweenCoordinate: (CLLocationCoordinate2D) coordinate1
                       andCoordinate: (CLLocationCoordinate2D) coordinate2;

@end
