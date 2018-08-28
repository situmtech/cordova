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
 *  The unique identifier indicating the SITIndoorBuilding which belongs to. Deprecated, use trigger.center.buildingIdentifier
 */
@property (nonatomic, strong) NSNumber *project_identifier DEPRECATED_MSG_ATTRIBUTE("Deprecated, use trigger.center.buildingIdentifier");

/**
 *  Name
 */
@property (nonatomic, strong) NSString *name;

/**
 Date containing the time when a resource was first created.
 */
@property (nonatomic, strong) NSDate *createdAt;

/**
 Date containing the time when a resource was last updated.
 */
@property (nonatomic, strong) NSDate *updatedAt;

/**
 *  Additional information about the SITEvent
 */
@property (nonatomic, strong) NSString *info;

/**
 *  Link to a website displaying relating info about the event
 */
@property (nonatomic, strong) NSString *url;

/**
 *  The area inside the SITIndoorBuilding where the event should be fired.
 */
@property (nonatomic, strong) SITCircularArea *trigger;

/**
 *  The area inside the SITIndoorBuilding where the event should be considered as converted
 */
@property (nonatomic, strong) SITCircularArea *conversion;

/**
 *  The area inside the SITIndoorBuilding where the event should be considered as converted. Deprecated, use conversion instead
 */
@property (nonatomic, strong) SITRectangularArea *conversionArea DEPRECATED_MSG_ATTRIBUTE("Deprecated, use conversion instead");

/**
 * Custom fields that can be added from the Dashboard
 */
@property (nonatomic, strong) NSDictionary<NSString*, NSString*> *customFields;

@end
