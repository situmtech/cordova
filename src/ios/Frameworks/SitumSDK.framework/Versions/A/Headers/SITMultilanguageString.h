//
//  SITMultilanguageString.h
//  SitumSDK
//
//  Created by A Barros on 2/3/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

/**
 * A string with a different value depending of the language.
 * It's recommended to support a default value {@link #defaultValue()} (optional) for non supported languages.
 * <p>
 * ISO 639-2 is used to identify the languages.
 *
 * @see {@link ISO3Language()}
 */
@interface SITMultilanguageString : NSObject

- (NSString *)value;

- (NSString *)valueForLocale:(NSLocale *)locale;

- (NSLocale *)defaultLocale;

- (void)setDefaultLocale:(NSLocale *)locale;

- (void)setValue:(NSString *)string
       forLocale:(NSLocale *)locale;

- (instancetype)initWithValue:(NSString *)value
                defaultLocale:(NSLocale *)defaultLocale;

@end
