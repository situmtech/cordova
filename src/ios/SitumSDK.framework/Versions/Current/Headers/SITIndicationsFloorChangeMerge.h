//
//  SITIndicationsFloorChangeMerge.h
//  SitumSDK
//
//  Created by Adrián Rodríguez on 24/5/18.
//  Copyright © 2018 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "SITIndication.h"
#import "SITIndicationsPostprocess.h"

/**
 This class applies the indications postprocess to merge consecutive floor changes
 */
@interface SITIndicationsFloorChangeMerge: NSObject <SITIndicationsPostprocess>

/**
 Returns the postprocessed list of indications.
 
 @param indications List of indications to be postprocessed
 
 @return A list of indications with the consecutive floor changes merging postprocess applied
 */
- (NSArray<SITIndication *> *)applyTo: (NSArray<SITIndication *> *)indications;

@end
