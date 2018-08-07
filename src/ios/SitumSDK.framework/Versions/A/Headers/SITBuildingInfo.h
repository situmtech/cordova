//
//  SITBuildingInfo.h
//  SitumSDK
//
//  Created by A Barros on 13/3/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITBuilding.h"
#import "SITFloor.h"
#import "SITPOI.h"
#import "SITEvent.h"

#import "SITGraph.h"

@interface SITBuildingInfo : NSObject

@property (nonatomic, strong) SITBuilding *building;
@property (nonatomic, strong) NSArray<SITFloor *> *floors;
@property (nonatomic, strong) NSArray<SITPOI *> *indoorPois;
@property (nonatomic, strong) NSArray<SITPOI *> *outdoorPois;
@property (nonatomic, strong) NSArray<SITEvent *> *events;

@property (nonatomic, strong) SITGraph *graph;

@end
