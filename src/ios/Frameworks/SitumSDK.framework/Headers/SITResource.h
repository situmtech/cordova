//
//  SITResource.h
//  SitumSDK
//
//  Created by A Barros on 2/3/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>


/**
 Abstract base class with common properties across Resources
 */
@interface SITResource : NSObject

/**
 Unique identifier of the resource
 */
@property (nonatomic, strong) NSString *identifier;


/**
 Date containing the time when a resource was first created.
 */
@property (nonatomic, strong) NSDate *createdAt;


/**
 Date containing the time when a resource was last updated.
 */
@property (nonatomic, strong) NSDate *updatedAt;

/**
 A dictionary containing additional information not managed by Situm SDK
 @discussion You can use this property to insert personalized content for your application. It will be loaded with the contents inserted on the web app.
 */
@property (nonatomic, strong) NSDictionary *customFields;

- (instancetype)initWithIdentifier:(NSString *)identifier
                         createdAt:(NSDate *)createdAt
                         updatedAt:(NSDate *)updatedAt
                      customFields:(NSDictionary *)customFields;

@end
