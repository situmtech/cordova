//
//  SITLink.h
//  SitumSDK
//
//  Created by A Barros on 1/4/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#ifndef SITLink_h
#define SITLink_h

#import <Foundation/Foundation.h>

#import "SITProductFlags.h"

#ifdef SITUM_PRODUCT

typedef NS_ENUM(int, kSITDirectionalLinkOptions)
{
    kSITBidirectionalLink = 0,
    kSITFromSourceToDestinationLink,
    kSITFromDestinationToSourceLink,
};

#endif

/**
 *  Class that represents a link between two SITNode objects
 *  @discussion You should not use this class directly. Instead, you should use the methods provides in SITGraph
 */
@interface SITLink : NSObject
/**
 *  Identifier of the source SITNode
 */
@property (nonatomic, strong) NSNumber *sourceNodeIdentifier;
/**
 *  Identifier of the destination SITNode
 */
@property (nonatomic, strong) NSNumber *destinationNodeIdentifier;

/**
 *  Accessibility of the SITNode
 */
@property (nonatomic, strong) NSNumber *accessible;

#ifdef SITUM_PRODUCT

/**
 *  Determines the directionality of the link
 */
@property (nonatomic, strong) NSString *origin;

#endif

@property (nonatomic, strong) NSString *sourceNodeStringID;

@property (nonatomic, strong) NSString *destinationNodeStringID;

- (instancetype)initWithSourceStringID:(NSString *)sourceNodeStringID
               destinationNodeStringID:(NSString *)destinationNodeStringID
                                origin:(NSString *)origin;


#pragma mark - private methods
- (void)prepareLink;

@end

#endif