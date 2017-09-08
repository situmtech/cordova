//
//  SITKeyValueFilter.h
//  SitumSDK
//
//  Created by A Barros on 14/6/16.
//  Copyright © 2016 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

/**
 *  Determine the available fields to filter
 */
typedef NS_ENUM(int, kSITKeyValueFilterBy) {
    /**
     *  It means that the filter will be applied both the keys and values of a CustomField property
     */
    kSITKeyValueFilterByBoth = 0,
    /**
     *  It means that the filter will be applied only to keys of a CustomField property
     */
    kSITKeyValueFilterByKey,
    /**
     *  It means that the filter will be applied only to values of a CustomField property
     */
    kSITKeyValueFilterByValue,
};

/**
 *  Auxiliar class that determine how to filter the Strings of a dictionary (customFields) included in different classes (for example, SITPOIBase)
 */
@interface SITKeyValueFilter : NSObject

/**
 *  One of the values in kSITKeyValueFilterBy
 */
@property (nonatomic, readonly) kSITKeyValueFilterBy filterBy; // filterBy

/**
 *  regular expression
 */
@property (nonatomic, strong, readonly) NSString *pattern;

/**
 *  Initializer
 *
 *  @param pattern  regular expression
 *  @param filterBy one of the values in kSITKeyValueFilterBy
 *
 *  @return initialized instance of class SITKeyValueFilter
 */
- (instancetype)initWithPattern:(NSString *)pattern
                       filterBy:(kSITKeyValueFilterBy)filterBy;

/**
 *  Getter for built regular expression
 *
 *  @return NSRegularExpression or nil if the patter is not valid
 */
- (NSRegularExpression *)regularExpression;

/**
 *  Determines if the filter is valid
 *
 *  @return BOOL value indicating if the filter is valid YES (pattern is valid) or no (NO).
 *  NOTE: This only checks if the pattern is Sincatically valid, nor Semantically valid
 */
- (BOOL)isValid; // error

@end
