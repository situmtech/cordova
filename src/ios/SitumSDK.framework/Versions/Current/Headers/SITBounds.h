//
//  SITBounds.h
//  SitumSDK
//
//  Created by A Barros on 9/3/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#ifndef SITBounds_h
#define SITBounds_h

#import <CoreLocation/CoreLocation.h>

/**
 *  SITBounds represents a rectangular box containing the geographic coordinates of the corners of the region
 */
typedef struct {
    CLLocationCoordinate2D southWest;
    CLLocationCoordinate2D southEast;
    CLLocationCoordinate2D northEast;
    CLLocationCoordinate2D northWest;
} SITBounds;


#endif /* SITBounds_h */
