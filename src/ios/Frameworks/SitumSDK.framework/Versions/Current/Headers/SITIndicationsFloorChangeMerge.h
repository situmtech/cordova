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

@interface SITIndicationsFloorChangeMerge: NSObject <SITIndicationsPostprocess>

- (NSArray<SITIndication *> *)applyTo: (NSArray<SITIndication *> *)indications;

@end
