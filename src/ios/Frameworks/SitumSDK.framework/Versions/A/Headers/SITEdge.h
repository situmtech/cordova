//
//  SITEdge.h
//  SitumSDK
//
//  Created by A Barros on 1/4/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITNode.h"
#import "SITLink.h"
/**
 *  This class is an intermiadiate class between SITNode and SITLink to work with PESGraph and routes algorithm
 *  @discussion You should not use this class directly. Instead, you should use the methods provides in SITGraph
 */
@interface SITEdge : NSObject

@property (nonatomic, strong) NSString *name;
@property (nonatomic, strong) SITNode *fromNode;
@property (nonatomic, strong) SITNode *toNode;

@property (nonatomic, strong) NSString *origin;

@property (nonatomic, strong) NSNumber *cost;


/**
 *  Designated initializer
 *
 *  @param fromNode source node of a SITLink objetc
 *  @param toNode   destination node of a SITLink object
 *
 *  @return instance of class ready to work.
 */
- (instancetype)initWithFromNode:(SITNode *)fromNode
                          toNode:(SITNode *)toNode
                          origin:(NSString *)origin
    distanceBetweenVirtualPoints:(float)distanceBetweenVirtualPoints;


- (NSDictionary *)virtualNodes;
@end
