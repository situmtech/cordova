//
//  SITDirectionsResponse.h
//  SitumSDK
//
//  Created by A Barros on 21/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITRoute.h"

@interface SITDirectionsResponse : NSObject

@property (nonatomic, readonly) SITRoute *route;

- (instancetype)initWithRoute:(SITRoute *)route;

@end
