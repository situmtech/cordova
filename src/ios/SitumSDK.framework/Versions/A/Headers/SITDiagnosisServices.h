//
//  SITDiagnosis.h
//  SitumSDK
//
//  Created by A Barros on 22/6/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>
/**
 *  Server types. Defines the type of location server to connect to
 */
typedef NS_ENUM(int, SITLocationServerType) {
    /**
     *  This is the default option if nothing is selected. It chooses the best available server based on different conditions
     */
    kSITHybridServer = 0,
    
    /**
     *  The requests goes to a server on the cloud
     */
    kSITCloudServer,
    
    /**
     *  The phone will act as a server
     */
    kSITPhoneServer,
};

/**
 *  Diagnosis class for the Situm SDK for iOS
 */
@interface SITDiagnosisServices : NSObject

+ (instancetype)sharedManager;


/**
 *  Obtain the connection status
 *
 *  @return BOOL value indicating if the Situm SDK will connect to a local server
 */
- (BOOL)connectToLocalServer;

/**
 *  Obtain the diagnosis status
 *
 *  @return BOOL value indicating if the Situm SDK is in diagnosis mode
 */
- (BOOL)diagnosisMode;

/**
 *  Dictionary containing the IPAddress and port to the server where the Situm SDK is going to connect
 *
 *  @return dictionary. if not nil, use the key host and port to retrieve both parameters
 */
- (NSDictionary *)hostPortDictionary;

/**
 *  Check availability of explicit location
 *
 *  @return BOOL value representing whether the service is available or not
 *  @discussion In order this method returns YES, both the explicit location and diagnosis mode options should be activated. Otherwise it will return NO.
 */
- (BOOL)isExplicitLocationAvailable;

/**
 *  Check availability of storing interesting values 
 *
 *  @return BOOL value representing whether the service is available or not
 *  @discussion In order this method returns YES, both the store internal data and diagnosis mode options should be activated. Otherwise it will return NO.
 */
- (BOOL)isStoreInternalDataAvailable;

/**
 *  In diagnosis mode, the Situm SDK can connect to a specific server. By enabling this flag, you should provide a valid IPAddress and a port
 *
 *  @param connectToLocalServer BOOL value (default is NO)
 *  @see   setLocalServerIPAddress: and setLocalServerPort:
 */
- (void)setConnectToLocalServer:(BOOL)connectToLocalServer;

/**
 *  Establish the diagnostic mode
 *
 *  @param diagnosisMode BOOL value (default is NO)
 *  @discussion When diagnosis enabled, the Situm SDK stores information for debugging purposes
 *
 */
- (void)setDiagnosisMode:(BOOL)diagnosisMode;

/**
 *  In diagnosis mode, the Situm SDK enables the user to provide an explicit position (the location where the user is inside a building)
 *
 *  @param      explicitPosition BOOL value (default is NO)
 *  @discussion this parameter would be considered only when storeInternalData is enabled
 *  @see        setStoreInternalData:
 */
- (void)setExplicitPosition:(BOOL)explicitPosition;

/**
 *  In diagnosis mode, the Situm SDK can connect to a different server. You can provide an IP address to connect to.
 *
 *  @param IPAddress String containing an IP Address
 *  @discussion Do not establish this parameter unless a member of Situm told you so. Otherwise the system could not work.
 *
 */
- (void)setLocalServerIPAddress:(NSString *)IPAddress;

/**
 *  In diagnosis mode, the Situm SDK can connect to a different server. You can provide a port to connect to.
 *
 *  @param port Integer value
 *  @discussion Do not establish this parameter unless a member of Situm told you so. Otherwise the system could not work.
 *
 */
- (void)setLocalServerPort:(NSString *)port;

/**
 *  In diagnosis mode, the Situm SDK can store information related to the lozalization service.
 *
 *  @param storeInternalData BOOL value (default is NO)
 *  @discussion With this flag enabled the Situm SDK will store important information for the library
 */
- (void)setStoreInternalData:(BOOL)storeInternalData;


/**
 *  Retrieve the state of the library in terms of internal data storage
 *
 *  @return BOOL value
 */
- (BOOL)storeInternalData;

- (BOOL)explicitPosition;

- (NSString *)localServerPort;

- (NSString *)localServerIPAddress;

/**
 *  Check if the user has stablish options to select a particular server
 *
 *  @return BOOL value indicating if the user wants to customize the location servere (YES) or not (NO)
 */
- (BOOL)isDetermineLocationServerAvailable;

/**
 *  Establish the location server type
 *
 *  @param serverType one of the SITServerType options
 */
- (void)setComputeLocationServerType:(SITLocationServerType)serverType;

/**
 *  Returns the actual location server type
 *
 *  @return one of the SITServerType options
 */
- (SITLocationServerType)computeLocationServerType;

- (void)setComputeLocationServer:(BOOL)computeLocationServer;

@end
