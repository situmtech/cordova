//
//  SITPOIBase.h
//  SitumSDK
//
//  Created by A Barros on 14/4/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITPOICategory.h"

@interface SITPOIBase : NSObject

/**
 *  Unique integer identifier
 */
@property (nonatomic, strong) NSNumber *identifier __attribute__((deprecated)) DEPRECATED_MSG_ATTRIBUTE("Use identifier property of the SITResource base class instead");
;

/**
 *  Relationship to an SITIndoorBuilding object
 */
@property (nonatomic, strong) NSNumber *project_identifier __attribute__((deprecated))DEPRECATED_MSG_ATTRIBUTE("Use buildingIdentifier property of the SITResource base class instead");


/**
 *  Name
 */
@property (nonatomic, strong) NSString *name;

/**
 *  Additional information about the POI in an HTML format
 */
@property (nonatomic, strong) NSString *info;

/**
 *  Identifier of a SITPOICategory object
 */
@property (nonatomic, strong) NSNumber *categoryIdentifier DEPRECATED_MSG_ATTRIBUTE("Use category.identifier property of the category field instead");
;

/**
 *  Relationship to a SITPOICategory object
 */
@property (nonatomic, strong) SITPOICategory *category;

@property (nonatomic, strong) NSNumber *typeIdentifier __attribute__((deprecated));

@property (nonatomic, strong) NSString *typeName __attribute__((deprecated));

/**
 *  Key-value property with addicional information. 
 *  NOTE: You can use this property to associate any arbitrary info with this POI. Situm SDK for iOS does not make operations with this field.
 */
@property (nonatomic, strong) NSDictionary *customFields;

@end
