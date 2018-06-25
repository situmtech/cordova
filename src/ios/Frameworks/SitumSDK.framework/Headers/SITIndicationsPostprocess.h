//
//  SITPostprocessProtocol.h
//  SitumSDK
//
//  Created by Adrián Rodríguez on 24/5/18.
//  Copyright © 2018 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "SITIndication.h"

/**
 Protocol that describes the interface every object applying postprocessing to indications should conform to.
 */
@protocol SITIndicationsPostprocess

/**
 Returns the postprocessed list of indications.
 
 @param indications List of indications to be postprocessed
 
 @return A list of indications with the postprocess operations applied
 */
- (NSArray<SITIndication *> *)applyTo: (NSArray<SITIndication *> *)indications;

@end
