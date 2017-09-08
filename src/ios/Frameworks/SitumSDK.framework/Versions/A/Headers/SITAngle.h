//
//  SITAngle.h
//  SitumSDK
//
//  Created by A Barros on 2/3/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface SITAngle : NSObject

- (instancetype)initWithRadians:(float)radians;

- (instancetype)initWithDegrees:(float)degrees;

- (float)degrees;

- (float)radians;

@end
