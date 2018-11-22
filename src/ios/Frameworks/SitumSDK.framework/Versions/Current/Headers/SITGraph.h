//
//  SITGraph.h
//  SitumSDK
//
//  Created by A Barros on 1/4/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#ifndef SITGraph_h
#define SITGraph_h

#import <Foundation/Foundation.h>

#import "SITIndoorPoint.h"
#import "SITIndoorRoute.h"
#import "SITAccessibilityMode.h"
#import "SITLink.h"
#import "SITNode.h"
#import "PESGraph.h"

@class PESGraph;

/**
 *  This model describes a graph representation of the paths of a building where a user can walk.
 */
@interface SITGraph : NSObject

/**
*  This method compute the quicket path between two SITIndoorPoints of a building.
*
*  @param sourcePoint      source SITIndoorPoint.
*  @param destinationPoint destination SITIndoorPoint.
*  @param accessibility     determines the accessibility options that should be taken into account. See SITAccessibility for more information
*
*  @return A SITIndoorRoute object containing the points and the distance between them to travel from the source point to the destination point. This method can
*  return nil if either sourcePoint or destinationPoint does not belong to the building.
*  NOTE: This method is executed on the same thread the is called. It can be an expensive operation so you should perform it outside the main thread to avoid UI related issues.
*/
- (SITIndoorRoute *)shortestRouteFromIndoorPoint:(SITIndoorPoint *)sourcePoint
                                   toIndoorPoint:(SITIndoorPoint *)destinationPoint
                                    accessibility:(SITAccessibilityMode)accessibility;

- (SITIndoorRoute*) shortestRouteFromIndoorPoint: (SITIndoorPoint*) sourcePoint
                                   toIndoorPoint: (SITIndoorPoint*) destinationPoint
                                   accessibility: (SITAccessibilityMode) accessibility
                                     withOptions: (NSDictionary*) options;

- (NSArray*) getNodesWithAccessibility: (SITAccessibilityMode) accessibility;

- (NSArray*) getLinksWithAccessibility: (SITAccessibilityMode) accessibility;

- (SITNode *)closestNodeToIndoorPoint:(SITIndoorPoint *)indoorPoint accessibility:(SITAccessibilityMode)accessibility;

- (void) removeLink: (SITLink*) linkToRemove withGraph: (PESGraph*) graph;

- (int) calculateFloorChangesWithFloor: (NSString*) floorIdentifier;

- (NSArray *)links;

- (NSArray *)nodes;

- (void)prepareGraph;

- (void)setNodes:(NSArray *)nodes;

- (void)setLinks:(NSArray *)links;

- (PESGraph *)internalGraphWithAccessibility:(SITAccessibilityMode)accessibility;

@end

#endif
