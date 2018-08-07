//
//  SITURL.h
//  SitumSDK
//
//  Created by A Barros on 2/3/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface SITURL : NSObject

@property (nonatomic, readonly) BOOL isAbsolute;

@property (nonatomic, readonly) NSString *direction;

- (instancetype)initWithDirection:(NSString *)direction;

@end
