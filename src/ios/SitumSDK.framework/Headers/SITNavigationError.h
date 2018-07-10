//
//  SITNavigationError.h
//  SitumSDK
//
//  Created by A Barros on 27/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#ifndef SITNavigationError_h
#define SITNavigationError_h

extern NSString *const kSITNavigationErrorDomain;

typedef NS_ENUM(NSInteger, SITNavigationError) {
    kSITNavigationErrorNetwork = 0,
    kSITNavigationErrorUnauthorized,
    kSITNavigationErrorBadRequest,
};

#endif /* SITNavigationError_h */
