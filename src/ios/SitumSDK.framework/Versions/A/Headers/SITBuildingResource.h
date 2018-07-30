//
//  SITBuildingResource.h
//  SitumSDK
//
//  Created by A Barros on 2/3/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import "SITResource.h"

/**
 Resource that has a one-to-one relationship with a SITBuilding.
 */
@interface SITBuildingResource : SITResource


/**
 Unique identifier of the building related with this resource
 */
@property (nonatomic, strong) NSString *buildingIdentifier;

@end
