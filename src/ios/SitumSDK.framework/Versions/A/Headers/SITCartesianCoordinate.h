//
//  SITCartesianCoordinate.h
//  SitumSDK
//
//  Created by A Barros on 21/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

/*
 *  SITCartesianMeters
 *
 *  Discussion:
 *    Type used to represent the x or y axis value of a cartesian coordinate in meters under a reference
 *    frame with // TODO: Complete this
 */
typedef double SITCartesianMeters;

@interface SITCartesianCoordinate : NSObject

@property (nonatomic) SITCartesianMeters x;
@property (nonatomic) SITCartesianMeters y;

- (instancetype)initWithX:(SITCartesianMeters)x
                        y:(SITCartesianMeters)y;

+ (instancetype)coordinateWithX:(SITCartesianMeters)x
                              y:(SITCartesianMeters)y;

@end
