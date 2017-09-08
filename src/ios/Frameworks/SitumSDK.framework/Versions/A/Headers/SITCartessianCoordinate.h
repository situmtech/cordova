//
//  SITCartessianCoordinate.h
//  SitumSDK
//
//  Created by A Barros on 21/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

/*
 *  SITCartessianMeters
 *
 *  Discussion:
 *    Type used to represent the x or y axis value of a cartessian coordinate in meters under a reference
 *    frame with // TODO: Complete this
 */
typedef double SITCartessianMeters;

@interface SITCartessianCoordinate : NSObject

@property (nonatomic) SITCartessianMeters x;
@property (nonatomic) SITCartessianMeters y;

- (instancetype)initWithX:(SITCartessianMeters)x
                        y:(SITCartessianMeters)y;

+ (instancetype)coordinateWithX:(SITCartessianMeters)x
                              y:(SITCartessianMeters)y;

@end
