//
//  SITNode.h
//  SitumSDK
//
//  Created by A Barros on 1/4/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITIndoorPoint.h"

/**
 *  Class that represent a node inside a SITGraph
 *  @discussion You should not use this class directly. Instead, you should use the methods provides in SITGraph
 */
@interface SITNode : NSObject
/**
 *  identifier of the node
 */
@property (nonatomic, strong) NSNumber *identifier;
/**
 *  indoor level identifier
 */
@property (nonatomic, strong) NSNumber *indoorLevelIdentifier;
/**
 *  Position over x-axis.
 */
@property (nonatomic, strong) NSNumber *x;
/**
 *  Position over y-axis.
 */
@property (nonatomic, strong) NSNumber *y;

@property (nonatomic, strong) NSString *stringID;

- (NSString *)stringIdentifier;

- (float)distanceToNode:(SITNode *)node;

- (float)distanceToIndoorLocationWithXCoordinate:(NSNumber *)x yCoordinate:(NSNumber *)y;

#pragma mark - private methods

- (void)prepareNode;

- (instancetype)initWithIndoorLevelIdentifier:(NSNumber *)indoorLevelIdentifier
                                            x:(NSNumber *)x
                                            y:(NSNumber *)y
                                     stringID:(NSString *)stringID;

- (SITIndoorPoint *)indoorPoint;

@end
