//
//  SITAccessibilityMode.h
//  SitumSDK
//
//  Created by Cristina Sánchez Barreiro on 20/11/2018.
//  Copyright © 2018 Situm. All rights reserved.
//

#ifndef SITAccessibilityMode_h
#define SITAccessibilityMode_h

enum {
    /** The route should choose the best route, without taking into account if it is accessible ot not */
    kSITChooseShortest = 0,
    /** The route should always use accessible nodes */
    kSITOnlyAccessible = 1,
    /** The route should never use accessible nodes */
    kSITOnlyNotAccessibleFloorChanges = 2,
}; typedef NSUInteger SITAccessibilityMode;

#endif /* SITAccessibility_h */
