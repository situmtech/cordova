//
//  SITRectangularArea.h
//  SitumSDK
//
//  Created by A Barros on 22/9/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#import "SITArea.h"

#import "SITIndoorPoint.h"

@interface SITRectangularArea : SITArea

@property (nonatomic ,strong) NSNumber *level_identifier;

@property (nonatomic, strong) SITIndoorPoint *topLeft;
@property (nonatomic, strong) SITIndoorPoint *topRight;
@property (nonatomic, strong) SITIndoorPoint *bottomLeft;
@property (nonatomic, strong) SITIndoorPoint *bottomRight;

- (SITIndoorPoint *)center;

@end
