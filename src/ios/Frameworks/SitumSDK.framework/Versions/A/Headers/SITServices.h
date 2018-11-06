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
    kSITNONE,
    kSITUSERPASS,
    kSITAPIKEY,
    kSITTOKEN
} SITAuth;

@property SITAuth auth;

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

/*
 *  Returns the license information for Situm SDK for iOS.
 *
 *  @return String containing the licence informacion of this realease of the Situm SDK for iOS.
 *  @discussion This information must be made available within the application.
 */
+ (NSString *)licenseInfo;

/*
 * Identifier of the smartphone
 *
 * @return Internal unique string used to identify the user's device.
 */
+ (NSString *)deviceID;

/*
 * Checks whether the APIKEY is valid for use.
 *
 * @param APIKey String containing the api key to be validated.
 *
 * @return
 */
+ (BOOL)isValidAPIKey:(NSString *)APIKey;

/*
 * API key used to access dashboard resources.
 *
 * @return String containing the api key used to authorize the requests to dashboard.
 */
+ (NSString *)APIKey;

/*
 * Email identifying the user.
 *
 * @return String containing the email to identify the user.
 */
+ (NSString *)email;

/*
 * Name of the user.
 *
 * @return String containing the name of the user.
 */
+ (NSString *)user;

/*
 * Password used to access dashboard resources.
 *
 * @return String containing the password used to authorize the requests to dashboard.
 */
+ (NSString *)password;

/*
 * Method used to set the user and password of the current session.
 *
 * @param user String with the name of the user.
 * @param password String with the password associated with the user.
 *
 * @return Boolean indicating the result of the auth operation.
 */
+ (BOOL)provideUser:(NSString *)user
           password:(NSString *)password;

#pragma mark - Private Methods

/*
 * Function used to parse a token to the correct format.
 *
 * @param token String containing the token to be parsed.
 *
 * @return String containing the parsed token.
 */
+ (NSString *)parse:(NSString *)token;

/*
 * Token used in the network requests.
 *
 * @return String containing the stored token.
 */
+ (SITToken *)token;

/*
 * Type of authentication used.
 *
 * @return Value of SITAuth enum indicating the type of authentication used.
 */
+ (SITAuth)auth;

+ (NSString *)authHeader;

+ (NSString *)tokenHeader;

/*
 * URL used to access the dashboard.
 *
 * @return String containing the url used to access the dashboard.
 */
+ (NSString*) dashboardURL;

+ (void)clearData;

+ (void)clearAllData;

+ (BOOL)provideToken:(SITToken *)token;

+ (void)provideAuth:(SITAuth)authMode;

+ (BOOL)isConfigured;

/*
 * Sets the URL used to access the dashboard.
 *
 * @param dashboardURL String containing the url used to access the dashboard.
 */
+ (void) setDashboardURL: (NSString*) dashboardURL;



@end
