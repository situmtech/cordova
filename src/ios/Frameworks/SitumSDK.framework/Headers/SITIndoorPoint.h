//
//  SITIndoorPoint.h
//  SitumSDK
//
//  Created by A Barros on 16/4/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

/**
 *  This class represent a point inside a building
 */
@interface SITIndoorPoint : NSObject

/**
 *  Position over x-axis.
 */
@property (nonatomic, strong) NSNumber *x;

/**
 *  Position over y-axis.
 */
@property (nonatomic, strong) NSNumber *y;

/**
 *  Identifier of the level (SITIndoorLevel)
 */
@property (nonatomic, strong) NSNumber *level_identifier;

/**
 *  Designated initializer of the class
 *
 *  @param x                Position over x-axis
 *  @param y                Position over y-axis
 *  @param level_identifier Identifier of the level (SITIndoorLevel)
 *
 *  @return an initialized object of this class
 *  @discussion Instead of creating your own objects, you can use the indoorPoint methods of the classes SITPOI or SITIndoorLocation
 */
- (instancetype)initWithX:(NSNumber *)x
                        y:(NSNumber *)y
         level_identifier:(NSNumber *)level_identifier;


- (CGPoint)point;

/**
 *  It Checks if the values are not null
 *
 *  @return true or false
 */
- (BOOL)isValid;

@end
