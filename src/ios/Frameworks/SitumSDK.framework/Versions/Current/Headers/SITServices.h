//
//  SITServices.h
//  SitumSDK
//
//  Created by Abraham on 3/3/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

@class SITToken;

/*!
 *  Service class for the Situm SDK for iOS
 */

@interface SITServices : NSObject

typedef enum {
    NONE,
    USERPASS,
    APIKEY,
    TOKEN
} Auth;

@property Auth auth;

/*!
 *  Provides your API key to the Situm SDK for iOS.  This key is generated
 *  for your application via the Situm APIs Console at https://dashboard.situm.es/accounts/profile
 *  This should be called exactly once by your application, e.g., in application: didFinishLaunchingWithOptions:.
 *
 *  @param APIKey APIKey is the string as developer you can see at the backend at the developer section like you can see on the following image. Follow the URL: https://dashboard.situm.es/accounts/profile
 *  @return YES if the APIKey was successfully provided
 *  @warning This method has been deprecated since v0.1.0-beta.
 *  @warning Use provideAPIKey:forEmail: instead
 *
 */
+ (BOOL)provideAPIKey:(NSString *)APIKey DEPRECATED_MSG_ATTRIBUTE("Use provideAPIKey:forEmail:");

/**
 *  Provides your API key to the Situm SDK for iOS. This key is generated for your application
 *
 *
 *  @param APIKey APIKey is the string as developer you can see at the backend at the developer section like you can see on the following image.
 *      Follow the URL: https://dashboard.situm.es/accounts/profile
 *  @param email  Email used to sign up as a developer at https://dashboard.situm.es/accounts/profile
 *
 *  @return YES if the APIKey was successfully provided
 */
+ (BOOL)provideAPIKey:(NSString *)APIKey
             forEmail:(NSString *)email;

/*!
 * Returns the version for this release of the Situm SDK for iOS.
 */
+ (NSString *)SDKVersion;

/**
 *  Returns the current value
 *
 *  @return BOOL value (default is NO)
 */
+ (BOOL)allowsInvalidSSLCertificate;

/**
 *  Tell the system to trust the SSL certificate of the server (even though the Authority is not valid)
 *
 *  @param allowsInvalidSSLCertificate BOOL value (if YES, communications with the server may not be secure)
 */
+ (void)setAllowsInvalidSSLCertificate:(BOOL)allowsInvalidSSLCertificate;

/*!
 *  Returns the license information for Situm SDK for iOS.
 *
 *  @return String containing the licence informacion of this realease of the Situm SDK for iOS.
 *  @discussion This information must be made available within the application.
 */
+ (NSString *)licenseInfo;

/**
 Identifier of the smartphone 
 */
+ (NSString *)deviceID;

+ (BOOL)isValidAPIKey:(NSString *)APIKey;

+ (NSString *)APIKey;

+ (NSString *)email;

+ (NSString *)user;

+ (NSString *)password;

+ (BOOL)provideUser:(NSString *)user
           password:(NSString *)password;

#pragma mark - Private Methods

+ (NSString *)parse: (NSString *) token;

+ (SITToken *)token;

+ (Auth) auth;

+ (NSString *) authHeader;

+ (NSString *) tokenHeader;

+ (void) clearData;

+ (void) clearAllData;

+ (BOOL) provideToken:(SITToken *)token;

+ (void) provideAuth:(Auth) authMode;

+ (BOOL)isConfigured;

@end
