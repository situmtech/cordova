//
//  SITNavigationManager.h
//  SitumSDK
//
//  Created by A Barros on 21/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITNavigationInterface.h"

/**
 Default implementation of SITNavigationInterface
 */
@interface SITNavigationManager : NSObject <SITNavigationInterface>

/**
 Delegate for progress callbacks
 */
@property (nonatomic, strong) id<SITNavigationDelegate> delegate;

/**
 Call this method to receive a reference to an initialized object of this class

 @return initialized and ready to use instance of this class
 @discussion You should always use this method to obtain the manager object and should not try to create instances directly.
 */
+ (instancetype)sharedManager;

@end
