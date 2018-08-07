//
//  SITBuilding.h
//  SitumSDK
//
//  Created by A Barros on 1/3/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreLocation/CoreLocation.h>

#import "SITAngle.h"
#import "SITDimensions.h"
#import "SITResource.h"
#import "SITURL.h"

#import "SITBounds.h"

/**
 Provides information about the places where indoor location is supported.
 */
@interface SITBuilding : SITResource

/**
 Identifier of the user who created the building
 */
@property (nonatomic, strong) NSString *userIdentifier;

/**
 *  Name of the building.
 */
@property (nonatomic, strong) NSString *name;


/**
 *  Address of the building.
 */
@property (nonatomic, strong) NSString *address;

/**
 *  Detailed information in HTML format
 */
@property (nonatomic, strong) NSString *infoHTML;

/**
 Rotation angle of the building's base, relative to the west-east axis,
 increasing in counter-clockwise, being 0 the west-east axis.
 */
@property (nonatomic, strong) SITAngle *rotation;

@property (nonatomic, strong) SITURL *pictureURL;

@property (nonatomic, strong) SITURL *pictureThumbURL; // nail TODO: Complete names??

- (instancetype)initWithIdentifier:(NSString *)identifier
                         createdAt:(NSDate *)createdAt
                         updatedAt:(NSDate *)updatedAt
                      customFields:(NSDictionary *)customFields
                    userIdentifier:(NSString *)userIdentifier
                              name:(NSString *)name
                            center:(CLLocationCoordinate2D)center
                              info:(NSString *)info
                        dimensions:(SITDimensions *)dimensions
                          rotation:(SITAngle *)rotation
                        pictureURL:(SITURL *)pictureURL
                   pictureThumbURL:(SITURL *)pictureThumbURL;


// TODO: Complete these methods

- (SITBounds)bounds;

- (SITBounds)rotatedBounds;


/**
 Size of the building

 @return structure containing the widht and height of the building 
 */
- (SITDimensions *)dimensions;

/**
 Geographical coordinate of the building
 */
- (CLLocationCoordinate2D)center;



@end
