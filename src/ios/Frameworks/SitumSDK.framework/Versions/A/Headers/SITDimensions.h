//
//  SITDimensions.h
//  SitumSDK
//
//  Created by A Barros on 2/3/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITCartesianCoordinate.h"

@interface SITDimensions : NSObject

@property (nonatomic, readonly) SITCartesianMeters width;

@property (nonatomic, readonly) SITCartesianMeters height;

- (instancetype)initWithWidth:(SITCartesianMeters)width
                       height:(SITCartesianMeters)height;

@end
