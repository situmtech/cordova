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
*  @param isAccessible     determines if the route should be computed having into account accessible alternatives 
*
*  @return A SITIndoorRoute object containing the points and the distance between them to travel from the source point to the destination point. This method can
*  return nil if either sourcePoint or destinationPoint does not belong to the building.
*  NOTE: This method is executed on the same thread the is called. It can be an expensive operation so you should perform it outside the main thread to avoid UI related issues.
*/
- (SITIndoorRoute *)shortestRouteFromIndoorPoint:(SITIndoorPoint *)sourcePoint
                                   toIndoorPoint:(SITIndoorPoint *)destinationPoint
                                    isAccessible:(BOOL)isAccessible;



#pragma mark - private methods
- (NSArray *)links;

- (NSArray *)nodes;

- (void)prepareGraph;


#pragma mark - Hide this methods before commit
- (void)setNodes:(NSArray *)nodes;

- (void)setLinks:(NSArray *)links;

- (PESGraph *)internalGraphAccessible:(BOOL)accessible;

@end
#endif
