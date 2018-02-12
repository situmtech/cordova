//
//  SITDirectionsError.h
//  SitumSDK
//
//  Created by A Barros on 24/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#ifndef SITDirectionsError_h
#define SITDirectionsError_h

extern NSString *const kSITDirectionsErrorDomain;

/**
 Error of the directions module

 - kSITDirectionsErrorNetwork: a network error
 - kSITDirectionsErrorUnauthorized: you are not allowed to use that information
 - kSITDirectionsErrorBadRequest: parameters are invalid
 */
typedef NS_ENUM(NSInteger, SITDirectionsError) {
    kSITDirectionsErrorNetwork = 0,
    kSITDirectionsErrorUnauthorized,
    kSITDirectionsErrorBadRequest,
};

#endif /* SITDirectionsError_h */
