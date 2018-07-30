//
//  SITOccurrence.h
//  SitumSDK
//
//  Created by A Barros on 29/3/16.
//  Copyright © 2016 Situm. All rights reserved.
//

#import "SITAPIBaseModel.h"


/**
 Action of an event

 - kSITOccurrenceViewed: an event has been seen
 - kSITOccurrenceClicked: an event has been clicked
 - kSITOccurrenceConverted: an event has been converted
 */
typedef NS_ENUM(NSInteger, kSITOccurrenceAction)
{
    kSITOccurrenceViewed = 0,
    kSITOccurrenceClicked,
    kSITOccurrenceConverted,
    
};

/**
 *  Describes importante situations around events
 */
@interface SITOccurrence : SITAPIBaseModel

/**
 *  Unique identifier of the ocurrence (this property can be nil if it's not created)
 */
@property (nonatomic, strong) NSNumber *identifier;

/**
 *  // Many to One relationship. Event in which the occurrence happended.
 */
@property (nonatomic, strong) NSNumber *eventIdentifier;

/**
 *  Identifier of the device
 */
@property (nonatomic, strong) NSNumber *deviceIdentifier;

/**
 *  The time when an event has been opened for more info (in iOS, this happends when the user press on the detail button or on the getMeThere button).
 */
@property (nonatomic, strong) NSDate *clickedAt;

/**
 *  The time when a user entered the conversion area of an event.
 */
@property (nonatomic, strong) NSDate *convertedAt;

@end
