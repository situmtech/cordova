//
//  SITRealTimeError.h
//  SitumSDK
//
//  Created by A Barros on 8/6/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#ifndef SITRealTimeError_h
#define SITRealTimeError_h

/**
 Domain error
 */
extern NSString *const kSITRealTimeErrorDomain;


/**
 Type of error the real time manager can return.

 - kSITRealTimeErrorBadRequest: this happends when the SITRealTimeRequest is invalid
 - kSITRealTimeErrorUnableToFindBuilding: either the building does not exist or you are not allowed (you are not authorized) to access it
 */
typedef NS_ENUM(NSInteger, SITRealTimeError) {
    /**
     An invalid request was provided
     */
    kSITRealTimeErrorBadRequest = 0,
    /**
     The identifier of the building cannot be found on the account
     */
    kSITRealTimeErrorUnableToFindBuilding,
};


#endif /* SITRealTimeError_h */
