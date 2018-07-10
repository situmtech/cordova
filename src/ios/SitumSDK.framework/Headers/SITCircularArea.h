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

@interface SITCircularArea : SITArea

@property (nonatomic, strong) SITIndoorPoint *center;

@property (nonatomic, strong) NSNumber *radius;

@end
