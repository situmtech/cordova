//
//  SITPOICategory.h
//  SitumSDK
//
//  Created by A Barros on 14/6/16.
//  Copyright © 2016 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITResource.h"
#import "SITMultilanguageString.h"
#import "SITURL.h"
/**
 *  It represents the types of POI
 */
@interface SITPOICategory : SITResource


@property (nonatomic, strong) SITMultilanguageString *name;

/**
 *  Name of the category in English
 */
@property (nonatomic, strong) NSString *nameEn DEPRECATED_MSG_ATTRIBUTE("Use -valueForLocale method of the name property instead.");

/**
 *  Name of the category in Spanish
 */
@property (nonatomic, strong) NSString *nameEs DEPRECATED_MSG_ATTRIBUTE("Use -valueForLocale method of the name property instead.");

/**
 *  Complementary text identifier of a category
 */
@property (nonatomic, strong) NSString *code;

/**
 *  Relative URL where the icon of the category for the normal state can be retrieved
 */
@property (nonatomic, strong) SITURL *iconURL;

/**
 *  Relative URL where the icon of the category for the selected state can be retrieved
 */
@property (nonatomic, strong) SITURL *selectedIconURL;

/**
 *  Determines the visibility of a category (YES means available to all users, NO means available to a particular user). 
 */
@property (nonatomic) BOOL isPublic;


@end
