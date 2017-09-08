//
//  SITCommunicationInterface.h
//  SitumSDK
//
//  Created by A Barros on 15/6/16.
//  Copyright © 2016 Situm. All rights reserved.
//

#ifndef SITCommunicationInterface_h
#define SITCommunicationInterface_h

#import "SITIndoorBuilding.h"
#import "SITIndoorLevel.h"

#import "SITOccurrence.h"
#import "SITEvent.h"
#import "SITPOICategory.h"
#import "SITPOI.h"
#import "SITPOIExterior.h"

static NSString* const kSITBuildingInfoKey = @"indoorBuilding";
static NSString* const kSITFloorsInfoKey = @"levels";
static NSString* const kSITIndoorPOIsInfoKey = @"indoor_pois";
static NSString* const kSITOutdoorPOIsInfoKey = @"outdoorPois";
static NSString* const kSITEventsInfoKey = @"events";
static NSString* const kSITGraphInfoKey = @"graph";

@class SITGraph;

typedef void (^SITImageFetchHandler)(NSData *imageData);


typedef void (^SITFailureOperationHandler)(NSError *error);

/**
 The type of block callback that will get executed when a repository operation has successfully been performed

 @param mapping create a class instead of passing a dictionary??
 @discussion discuss about the keys of the dictionary
 */
typedef void (^SITSuccessHandler)(NSDictionary *mapping);


/*!=
 *  The type of block callback for handling indoor buildings data
 *
 *  @param indoorBuildings An array of objects that encapsulates a collection of SITIndoorBuilding objects based on Authentication
 *  @param error    An error object representing a problem while retrieving information from Internet
 */
typedef void (^SITBuildingFetchHandler)(NSArray *indoorBuildings, NSError *error);


typedef void (^SITBuildingInfoFetchHandler)(NSDictionary *info, NSError *error);

/*!
 *  The type of block callback for handling indoor level data
 *
 *  @param indoorLevels An array encapsulating SITIndoorLevel objects
 *  @param error        An error object representing a problem while retrieving information from Internet
 *
 *  @warning TBC
 */
typedef void (^SITIndoorLevelFetchHandler)(NSArray *indoorLevels, NSError *error);


/*!
 *  The type of block callback for handling indoor level map data
 *
 *  @param indoorLevelMapData An NSData object with information of an indoor level map
 *  @discussion You can use this object to create UIImageView in order to display them on screen.
 */
typedef void (^SITIndoorLevelMapFetchHandler)(NSData *indoorLevelMapData);

/*!
 *  The type of block callback for handling POIs (Point Of Interest) data
 *
 *  @param POIs  An array encapsulating SITPOI objects
 *  @param error An error object representing a problem while retrieving information from Internet
 
 */
typedef void (^SITPOIFetchHandler)(NSArray *POIs, NSError *error);

/**
 *  The tyoe of block callback for handling External POIs (Points Of Interest) data
 *
 *  @param exteriorPOIS An array encapsulating SITPOIExterior objects
 *  @param error        An error object representing a problem while retrieving information from Internet
 */
typedef void (^SITExteriorPOIFetchHandler)(NSArray *exteriorPOIS, NSError *error);

/**
 *  The type of block callback for handling the graph of a SITIndoorBuilding object
 *
 *  @param graphs An array of object of class SITGraph
 *  @param error An error object representing a problem while retrieving information from Internet
 */
typedef void (^SITGraphFetchHandler)(NSArray *graphs, NSError *error);

/**
 *  The type of block for handling events of a SITIndoorBuilding object
 *
 *  @param events array containing the events inside a building
 *  @param error  An error object representing a problem while retrieving information from Internet
 */
typedef void (^SITEventsFetchHandler)(NSArray *events, NSError *error);

/**
 *  The type of block that will be called after the method - fetchCategoriesWithCompletion: is executed
 *
 *  @param categories An array SITPOICategory class objects
 *  @param error      An error object representing a problem while retrieving information from Internet
 */
typedef void (^SITPOICategoriesFetchHandler)(NSArray *categories, NSError *error);

/**
 *  The type of block that will be called after an icon of a category has been requested
 *
 *  @param iconData An NSData object containing the data of the icon
 *  @param error    An error object representing a problem while retrieving the data
 */
typedef void (^SITPOICategoryIconFetchCompletion)(NSData *iconData, NSError *error);

typedef void (^SITLoginHandler)(NSError *error);

typedef void (^SITLogoutHandler)(NSError *error);

/**
 *  The type of block callback for handling calibrations updates for a specific floor
 *
 *  @param error An error object representing a problem while performing the operation.
 */
typedef void (^SITCalibrationsPushHandler)(NSError *error);

typedef void (^SITOccurrenceCompletion)(SITOccurrence *occurrence);

typedef void (^SITErrorCompletion)(NSError *error);

typedef void (^SITSuccessRetrievingModelCompletion)(NSData *data);

typedef void (^SITFailureCompletion)(NSError *error);

#endif /* SITCommunicationInterface_h */
