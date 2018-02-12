//
//  SITLocationError.h
//  SitumSDK
//
//  Created by A Barros on 27/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#ifndef SITLocationError_h
#define SITLocationError_h

/**
 Domain error
 */
extern NSString *const kSITLocationErrorDomain;


/**
 Type of error the location manager can return.

 - kSITLocationErrorNetwork: there was a network error.
 - kSITLocationErrorUnauthorized: you have not permissions access the information because you are not allowed to see it.
 - kSITLocationErrorBadRequest: there is a problem with parameters.
 - kSITLocationErrorPermissionDenied: you have no permissions to use the location of the user.
 - kSITLocationErrorAlreadyStarted: you can only start the system once.
 - kSITLocationErrorSystemStopped: you can only stop the system when is previosly started.
 */
typedef NS_ENUM(NSInteger, SITLocationError) {
    kSITLocationErrorNetwork = 0,
    kSITLocationErrorUnauthorized,
    kSITLocationErrorBadRequest,
    kSITLocationErrorPermissionDenied,
    kSITLocationErrorAlreadyStarted,
    kSITLocationErrorSystemStopped
};


#endif /* SITLocationError_h */
