//
//  SITEvent.h
//  SitumSDK
//
//  Created by A Barros on 22/9/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITCircularArea.h"

#import "SITRectangularArea.h"

@interface SITEvent : NSObject

/**
 *  This is a unique identifier of each event
 */
@property (nonatomic, strong) NSNumber *identifier;

/**
 *  The unique identifier indicating the SITIndoorBuilding which belongs to
 */
@property (nonatomic, strong) NSNumber *project_identifier;

/**
 *  Name
 */
@property (nonatomic, strong) NSString *name;

/**
 *  Additional information about the SITEvent
 */
@property (nonatomic, strong) NSString *info;

/**
 *  Link to a website displaying relating info about the event
 */
@property (nonatomic, strong) NSString *url;

/**
 *  The area inside the SITIndoorBuilding where the event should be fired
 */
@property (nonatomic, strong) SITCircularArea *positionArea;

/**
 *  The area inside the SITIndoorBuilding where the event should be considered as converted
 */
@property (nonatomic, strong) SITRectangularArea *conversionArea;

@end
