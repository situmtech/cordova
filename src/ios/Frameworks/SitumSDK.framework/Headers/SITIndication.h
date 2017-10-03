//
//  SITIndication.h
//  SitumSDK
//
//  Created by A Barros on 21/2/17.
//  Copyright © 2017 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>


/**
 The type of action the user should perform in order to arrive to the destination.

 - kSITInvalidAction: invalid action
 - kSITTurn: the user needs to turn
 - kSITGoAhead: the user needs to keep the same direction
 - kSITChangeFloor: the user needs to change floor
 - kSITEnd: the user has arrived to the destination
 */
typedef NS_ENUM(NSInteger, kSITIndicationActions) {
    kSITInvalidAction,
    kSITTurn,
    kSITGoAhead,
    kSITChangeFloor,
    kSITEnd

};


/**
 The type of turn (if any) the user should perform in order to arrive to the destination.

 - kSITInvalidOrientation: invalid orientation.
 - kSITStraight: keep the same direction.
 - kSITVeerRight: turn slightly to the right.
 - kSITRight: turn right.
 - kSITSharpRight: turn heavily to right.
 - kSITVeerLeft: turn slightly to the left.
 - kSITLeft: turn left.
 - kSITSharpLeft: turn heavily to the left.
 - kSITBackward: turn around.
 */
typedef NS_ENUM(NSInteger, kSITIndicationOrientation) {
    kSITInvalidOrientation,
    kSITStraight,
    kSITVeerRight,
    kSITRight,
    kSITSharpRight,
    kSITVeerLeft,
    kSITLeft,
    kSITSharpLeft,
    kSITBackward,
};


/**
 Represents the instruction that a user should follow when on a SITRouteStep to continue the route.
 */
@interface SITIndication : NSObject


/**
 The index of the step where the indication starts (positive integer).
 */
@property (nonatomic, readonly) NSInteger originStepIndex;


/**
 The index of the step where the indication ends (positive integer).
 */
@property (nonatomic, readonly) NSInteger destinationStepIndex;

/**
 Distance between origin and destination (in meters).
 */
@property (nonatomic, readonly) float horizontalDistance;

/**
 Number of levels between the origin and destination.
 @discussion value representing the number of levels to go upstairs (positive values) or downstairs (negative values).
 */
@property (nonatomic, readonly) float verticalDistance;

/**
 Check if the user should change the level in order to arrive to destination.
 */
@property (nonatomic, readonly) BOOL needLevelChange;

/**
 The angle between the previous destination and the line formed by the origin and destination of the current step (in radians).
 */
@property (nonatomic, readonly) float orientationChange;


/**
 The type of action the user should perform in order to arrive to the destination.
 @discussion one of kSITIndicationActions.
 */
@property (nonatomic, readonly) kSITIndicationActions action;

/**
 The type of turn (if any) the user should perform in order to arrive to the destination.
 @discussion one of kSITIndicationOrientation.
 */
@property (nonatomic, readonly) kSITIndicationOrientation orientation;


/**
 Human readable text

 @return string that describes the indication in a more human friendly way
 */
- (NSString *)humanReadableMessage; // Provide option to insert the locale here

/**
 Private method.
 Initialize a SITIndication object.

 @param stepIndex index of the origin SITRouteStep
 @param orientationChange the angle between the virtual line of (in radians)
 @param horizontalDistance the distance between the origin and destination SITRouteStep
 @param verticalDistance the number of levels that needs to go up (positive value) or down (negative value) in order to arrive to the destination.
 @return an indication
 @discussion You should not use this method directly. Instead you should use the indications provided by SITDirectionsManager or SITNavigationManager.
 */
- (instancetype)initWithStepIndex:(NSInteger)stepIndex
                orientationChange:(float)orientationChange
                         horizontalDistance:(float)horizontalDistance
                 verticalDistance:(float)verticalDistance;

/**
 Private method.
 
 @param originStepIndex index of the origin SITRouteStep.
 @param destinationStepIndex index of the destination SITRouteStep.
 @param action the type of action the user should perform in order to arrive to the destination.
 @param horizontalDistance distance between origin and destination (in meters).
 @param orientation The type of turn (if any) the user should perform in order to arrive to the destination.
 @param orientationChange The angle between the previous destination and the line formed by the origin and destination of the current step (in radians).
 @param verticalDistance Number of levels between the origin and destination.
 @return an indication
 @discussion You should not use this method directly. Instead you should use the indications provided by SITDirectionsManager or SITNavigationManager.
 */
- (instancetype)initWithOriginStepIndex:(NSInteger)originStepIndex
                   destinationStepIndex:(NSInteger)destinationStepIndex
                                 action:(kSITIndicationActions)action
                     horizontalDistance:(float)horizontalDistance
                            orientation:(kSITIndicationOrientation)orientation
                            orientationChange:(float)orientationChange
                       verticalDistance:(float)verticalDistance;

/**
 Private method to check if two indication can be chunked.

 @param indication the indication that needs to be chuncked.
 @return BOOL value that indicates if the indication can be chunked (YES) or not (NO)
 @discussion You should not use this method directly. Instead you should use the indications provided by SITDirectionsManager or SITNavigationManager.
 */
- (BOOL)isChunckableWithIndication:(SITIndication *)indication;

/**
 Private method to compress two indications into one.

 @param indication the indication to be chunked.
 @return a compress indication.
 @discussion You should not use this method directly. Instead you should use the indications provided by SITDirectionsManager or SITNavigationManager.
 */
- (SITIndication *)chuckWithIndication:(SITIndication *)indication;


@end
