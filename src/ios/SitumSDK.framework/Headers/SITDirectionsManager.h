//
//  SITDirectionsManager.h
//  SitumSDK
//
//  Created by A Barros on 21/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITDirectionsInterface.h"


/**
 Central component that provides guidances and routes to travel from a SITLocation or SITPoint to a destination place.
 Currently only works for route inside indoor areas.
 */
@interface SITDirectionsManager : NSObject <SITDirectionsInterface>

/**
 The object conforming to the SITDirectionsDelegate protocol where updates will be provided
 */
@property (nonatomic, weak) id<SITDirectionsDelegate> delegate;
@end
