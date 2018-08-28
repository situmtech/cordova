//
//  SITCircularArea.h
//  SitumSDK
//
//  Created by A Barros on 22/9/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITArea.h"
#import "SITIndoorPoint.h"
#import "SITPoint.h"

@interface SITCircularArea : SITArea

/**
 * Center of the circle
 */
@property (nonatomic, strong) SITPoint *center;

/**
 * Radius of the circle
 */
@property (nonatomic, strong) NSNumber *radius;

@end
