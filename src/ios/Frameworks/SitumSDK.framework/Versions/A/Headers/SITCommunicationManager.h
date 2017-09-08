//
//  SITCommunicationManager.h
//  SitumSDK
//
//  Created by Abraham on 6/3/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#ifndef SITCommunicationManager_h
#define SITCommunicationManager_h


#import <Foundation/Foundation.h>

#import "SITCommunicationInterface.h"
#import "SITCommunicationConstants.h"

#import "SITFloor.h"

/*!
 * This class is the interface to perform operations related with Situm Servers.
 */
@interface SITCommunicationManager : NSObject

/**
 *  Call this method to receive a reference to an initialized object of this class
 *
 *  @return The shared manager object. Initially, this method stablish a value of 24 hours as the CAHCE_MAX_AGE for the cache system.
 *  @discussion You should not try to initialize multiple objects of this class using alloc:init because it would result in unexpected behaviour.
 */
+ (instancetype)sharedManager;

/**
 *  Retrieve the time when data on cache was retrieved
 *
 *  @return the date when the information was successfully fetched or nil if there is not cache.
 */
- (NSDate *)cacheDate;

/**
 *  Establish the time when the stored data on cache expires
 *
 *  @param cacheMaxAge Integer value in seconds. If a negative value is inserted, a value of 0 will be established.
 */
- (void)setCacheMaxAge:(NSInteger)cacheMaxAge;

/**
 *  Retrieve the value when the stored data on cache expires.
 *
 *  @return Integer value in seconds.
 */
- (NSInteger)cacheMaxAge;

/**
 *  Clear the contents of the cache for a particular user
 */
- (void)clearCache;

/**
 *  Retrieve the information of the available categories
 *
 *  @param completion           the kind of block that will be performed after the operation has been completed. Declaration in SITCommunicationInterface
 *  @return BOOL value indicating if the operation will be performed or not
 */
- (BOOL)fetchCategoriesWithCompletion:(SITPOICategoriesFetchHandler)completion DEPRECATED_MSG_ATTRIBUTE("Use - (BOOL)fetchCategoriesWithOptions:withCompletion:");

/**
 *  Retrieve the information of the available categories
 *
 *  @param options              additional parameters to customize the internal operation of the method declared in SITCommunicationConstants.h
 *  @param completion           the kind of block that will be performed after the operation has been completed
 *  @return BOOL value indicating if the operation will be performed or not
 *  @discussion Valid options for this method are: SITForceRequestKey. The value of this key is a NSNumber with a bool value on it. The value of YES means that the request will be directed to the network system directly without checking the cache system. The value of NO means the request will be directed to the cache system and if not found it will be redirected to the network system.
 */
- (BOOL)fetchCategoriesWithOptions:(NSDictionary *)options
                    withCompletion:(SITPOICategoriesFetchHandler)completion;

/*!
 *  Retrieve the buildings you are allowed to see. This is an asynchronous operation.
 *
 *  @param  buildindFetchHandler This is the block which will be called after the operation has been completed.
 *  @return This method gives the user a flag that indicates if the operation has been performed or not.
 *  @see SITBuildingFetchHandler.
 */
- (BOOL)fetchIndoorBuildingsWithCompletion:(SITBuildingFetchHandler)buildindFetchHandler DEPRECATED_MSG_ATTRIBUTE("Use - (BOOL)fetchIndoorBuildingsWithOptions:withCompletion:");


/**
 Retrieve the list of floors associated with a building

 @param buildingIdentifier unique identifier of the building
 @param options parameters that modify the internal behaviour
 @param success the kind of block that will be executed when the operation has successfully been performed
 @param failure the kind of block that will be executed when the operation fails
 @return error error that describes why the operation could not be executed (a nil value means the request is valid and will be executed)
 @discussion Keys of the options dictionary: forceRequest boolean value that determines if the operation should hit the server (YES) or the cachesystem (NO)
 */
- (NSError *)fetchFloorsForBuilding:(NSString *)buildingIdentifier
                        withOptions:(NSDictionary *)options
                            success:(SITSuccessHandler)success
                            failure:(SITFailureCompletion)failure;


/**
 Retrieve the list of buildings

 @param options parameters that modify the internal behaviour
 @param success the kind of block that will be executed when the operation has successfully been performed. Information can be accessed through the results key on the mapping result dictionary
 @param failure the kind of block that will be executed when the operation fails with an error describing what has failed
 @return error error that describes why the operation could not be executed (a nil value means the request is valid and will be executed)
 @discussion Keys of the options dictionary: forceRequest boolean value that determines if the operation should hit the server (YES) or the cachesystem (NO)
 */
- (NSError *)fetchBuildingsWithOptions:(NSDictionary *)options
                          success:(SITSuccessHandler)success
                          failure:(SITFailureCompletion)failure;


/**
 Retrieve the list of indoor points of interest associated with a building

 @param buildingIdentifier unique identifier of the building
 @param options parameters that modify the internal behaviour (see discussion section)
 @param success the kind of block that will be executed when the operation has successfully been performed. Information can be accessed through the results key on the mapping result dictionary
 @param failure the kind of block that will be executed when the operation fails with an error describing what has failed
 @return error that describes why the operation could not be executed (a nil value means the request is valid and will be executed)
 @discussion Keys of the options dictionary: forceRequest boolean value that determines if the operation should hit the server (YES) or the cachesystem (NO)
 */
- (NSError *)fetchPoisOfBuilding:(NSString *)buildingIdentifier
                     withOptions:(NSDictionary *)options
                         success:(SITSuccessHandler)success
                         failure:(SITFailureCompletion)failure;

/**
 Retrieve the list of outdoor points of interest associated with a building

 @param buildingIdentifier unique identifier of the building
 @param options parameters that modify the internal behaviour (see discussion section)
 @param success the kind of block that will be executed when the operation has successfully been performed. Information can be accessed through the results key on the mapping result dictionary
 @param failure the kind of block that will be executed when the operation fails with an error describing what has failed
 @return error that describes why the operation could not be executed (a nil value means the request is valid and will be executed)
 @discussion Keys of the options dictionary: forceRequest boolean value that determines if the operation should hit the server (YES) or the cachesystem (NO)
 */
- (NSError *)fetchOutdoorPoisOfBuilding:(NSString *)buildingIdentifier
                            withOptions:(NSDictionary *)options
                                success:(SITSuccessHandler)success
                                failure:(SITFailureCompletion)failure;


/**
 Retrieve the information of a building.

 @param buildingIdentifier unique identifier of the building.
 @param options parameters that modify the internal behaviour (see discussion section)
 @param success the kind of block that will be executed when the operation has successfully been performed. Information can be accessed through the results key on the mapping result dictionary
 @param failure failure the kind of block that will be executed when the operation fails with an error describing what has failed
 @return error that describes why the operation could not be executed (a nil value means the request is valid and will be executed)
 @discussion Keys of the options dictionary: forceRequest boolean value that determines if the operation should hit the server (YES) or the cachesystem (NO)
 */
- (NSError *)fetchBuildingInfo:(NSString *)buildingIdentifier
                   withOptions:(NSDictionary *)options
                       success:(SITSuccessHandler)success
                       failure:(SITFailureCompletion)failure;


/**
 Retrieve the floorplan of a floor

 @param floor SITFloor object
 @param imageFetchHandler the kind of block that will be executed when the operation has successfully been performed (a nil data would mean the operation has failed)
 @return error that describes why the operation could not be executed (a nil value means the request is valid and will be executed)
 */
- (BOOL)fetchMapFromFloor:(SITFloor *)floor
           withCompletion:(SITImageFetchHandler)imageFetchHandler; // This should check cache first

/**
 *  Retrieve the buildings you are allowed to see. This is an asynchronous operation.
 *
 *  @param buildindFetchHandler buildindFetchHandler This is the block which will be called after the operation has been completed.
 *  @param options              additional parameters to customize the internal operation of the method
 *
 *  @return This method gives the user a flag that indicates if the operation has been performed (YES) or not (NO).
 *  @discussion Valid options for this method are: SITForceRequestKey. The value of this key is a NSNumber with a bool value on it. The value of YES means that the request will be directed to the network system directly without checking the cache system. The value of NO means the request will be directed to the cache system and if not found it will be redirected to the network system.
 */
- (BOOL)fetchIndoorBuildingsWithOptions:(NSDictionary *)options
                         withCompletion:(SITBuildingFetchHandler)buildindFetchHandler;


- (BOOL)fetchInfoOfIndoorBuilding:(SITIndoorBuilding *)indoorBuilding
                      withOptions:(NSDictionary *)options
                   withCompletion:(SITBuildingInfoFetchHandler)completion;


- (BOOL)fetchInfoOfIndoorBuilding:(SITIndoorBuilding *)indoorBuilding
                   withCompletion:(SITBuildingInfoFetchHandler)completion;

/*!
 *  Retrieve levels of a building.
 *
 *  @param indoorBuilding SITIndoorBuilding object.
 *  @param indoorLevelFetchHandler SITIndoorLevelsFetchHandler handler.
 *
 *  @return This method gives the user a flag that indicates if the operation has been performed or not.
 */
- (BOOL)fetchIndoorLevelsFromIndoorBuilding:(SITIndoorBuilding *)indoorBuilding
                             withCompletion:(SITIndoorLevelFetchHandler)indoorLevelFetchHandler DEPRECATED_MSG_ATTRIBUTE("Use - (BOOL)fetchIndoorLevelsFromIndoorBuilding:withOptions:withCompletion:");

/*!
 *  Retrieve levels of a building.
 *
 *  @param indoorBuilding SITIndoorBuilding object.
 *  @param options              additional parameters to customize the internal operation of the method
 *  @param indoorLevelFetchHandler SITIndoorLevelsFetchHandler handler.
 *
 *  @return This method gives the user a flag that indicates if the operation has been performed or not.
 *  @discussion Valid options for this method are: SITForceRequestKey. The value of this key is a NSNumber with a bool value on it. The value of YES means that the request will be directed to the network system directly without checking the cache system. The value of NO means the request will be directed to the cache system and if not found it will be redirected to the network system.
 */

- (BOOL)fetchIndoorLevelsFromIndoorBuilding:(SITIndoorBuilding *)indoorBuilding
                                withOptions:(NSDictionary *)options
                             withCompletion:(SITIndoorLevelFetchHandler)indoorLevelFetchHandler DEPRECATED_MSG_ATTRIBUTE("Use - fetchFloorsForBuilding:withOptions:success:failure instead");


/*!
 *  Retrieve the map of an indoor level of a building.
 *
 *  @param indoorLevel           SITIndoorLevel object.
 *  @param indoorLevelMapHandler SITIndoorLevelMapFetchHandler handler.
 *
 *  @return This method gives the user a flag that indicates if the operation has been performed or not.
 */
- (BOOL)fetchIndoorLevelMapFromIndoorLevel:(SITIndoorLevel *)indoorLevel
                            withCompletion:(SITIndoorLevelMapFetchHandler)indoorLevelMapHandler DEPRECATED_MSG_ATTRIBUTE("Use - fetchMapFromFloor:withCompletion: instead");;

/*!
 *  Retrieve POIs from a building.
 *
 *  @param indoorBuilding  SITIndoorBuilding object.
 *  @param POIFetchHandler SITPOIFetchHandler handler.
 *
 *  @return This method gives the user a flag that indicates if the operation has been performed or not.
 */

- (BOOL)fetchPOIsFromIndoorBuilding:(SITIndoorBuilding *)indoorBuilding
                     withCompletion:(SITPOIFetchHandler)POIFetchHandler DEPRECATED_MSG_ATTRIBUTE("Use - (BOOL)fetchPOIsFromIndoorBuilding:withOptions:withCompletion:");

/**
 *  Retrieve POIs from a building.
 *
 *  @param indoorBuilding  SITIndoorBuilding object.
 *  @param options         additional parameters to customize the internal operation of the method
 *  @param POIFetchHandler SITPOIFetchHandler handler.
 *
 *  @return This method gives the user a flag that indicates if the operation has been performed or not.
 *  @discussion Valid options for this method are: SITForceRequestKey, SITCustomFiltersKey and SITKeyValueFilterMode. SITForceRequestKey: The value of this key is a NSNumber with a bool value on it. The value of YES means that the request will be directed to the network system directly without checking the cache system. The value of NO means the request will be directed to the cache system and if not found it will be redirected to the network system. SITCustomFiltersKey: The value of this key is an array of objects of the class SITKeyValueFilter objects. AS a result, it will select only the POIs that meet those filters. SITKeyValueFilterMode: The value of this key is an NSNumber with values defined in the enum kSITKeyValueFilterBy. It expresses how to combine results from multiple filters.
 *
 */
- (BOOL)fetchPOIsFromIndoorBuilding:(SITIndoorBuilding *)indoorBuilding
                     withOptions:(NSDictionary *)options
                     withCompletion:(SITPOIFetchHandler)POIFetchHandler DEPRECATED_MSG_ATTRIBUTE("Use - (BOOL)fetchPoisOfBuilding:withOptions:success:failure:");;

/**
 *  Retrieve the contents of an icon of a category
 *
 *  @param selected   BOOL parameter indicating the kind of icon to retrieve (YES means the selected one, NO means the regular or normal one)
 *  @param category   SITPOICategory object. Normally you do not instantiate objects of this class, instead you should use the one obtained by -fetchCategoriesWithCompletion:
 *  @param completion the kind of callback that will be called after the operation has been completed
 *
 *  @return BOOL value indicating if the operation is well formed and if it will be executed (YES) or not (NO)
 */
- (BOOL)fetchSelected:(BOOL)selected
      iconForCategory:(SITPOICategory *)category
       withCompletion:(SITPOICategoryIconFetchCompletion)completion;

/**
 *
 *
 *  @param POIs        array of POIs retrieved with the fetchPOIsFromIndoorBuilding:withCompletion: method
 *  @param indoorLevel SITIndoorLevel      object
 *
 *  @return An array with POIs filtered by the indoorLevel
 */
- (NSArray *)filterPOIs:(NSArray *)POIs
          byIndoorLevel:(SITIndoorLevel *)indoorLevel DEPRECATED_MSG_ATTRIBUTE("Use - (NSArray *)filterPOIs:byCategory:byIndoorLevel");

/**
 *  This methods selects the POIs verifying certain conditions
 *
 *  @param POIs        original array of POIs (of the class SITPOI)
 *  @param category    category name
 *  @param indoorLevel level
 *
 *  @return array of POIs filtered by a category and a level
 *  @see SITPOI to find the category name and level properties
 *  @discussion In this method, if a category or a level is nil, then that parameter will not affect the result. This filter is case sensitive.
 *
 */
- (NSArray *)filterPOIs:(NSArray *)POIs
             byCategory:(NSString *)category
          byIndoorLevel:(SITIndoorLevel *)indoorLevel;

/**
 *  Retrieve Outdoor POIs from a building
 *
 *  @param indoorBuilding          SITIndoorBuilding object.
 *  @param exteriorPOIFetchHandler SITExteriorPOIFetchHandler handler
 *
 *  @return This method gives the user a flag that indicates if the operation has been performed or not.
 */
- (BOOL)fetchExteriorPOIsFromIndoorBuilding:(SITIndoorBuilding *)indoorBuilding
                             withCompletion:(SITExteriorPOIFetchHandler)exteriorPOIFetchHandler DEPRECATED_MSG_ATTRIBUTE("Use - (BOOL)fetchOutdoorPOIsFromIndoorBuilding:withOptions:withCompletion:");

/*!
 *  Retrieve Outdoor POIs from a building
 *
 *  @param indoorBuilding          SITIndoorBuilding object.
 *  @param options                 additional parameters to customize the internal operation of the method
 *  @param exteriorPOIFetchHandler SITExteriorPOIFetchHandler handler
 *
 *  @return This method gives the user a flag that indicates if the operation has been performed or not.
 *  @discussion Valid options for this method are: SITForceRequestKey, SITCustomFiltersKey and SITKeyValueFilterMode. SITForceRequestKey: The value of this key is a NSNumber with a bool value on it. The value of YES means that the request will be directed to the network system directly without checking the cache system. The value of NO means the request will be directed to the cache system and if not found it will be redirected to the network system. SITCustomFiltersKey: The value of this key is an array of objects of the class SITKeyValueFilter objects. AS a result, it will select only the POIs that meet those filters. SITKeyValueFilterMode: The value of this key is an NSNumber with values defined in the enum kSITKeyValueFilterBy. It expresses how to combine results from multiple filters.
 */

- (BOOL)fetchOutdoorPOIsFromIndoorBuilding:(SITIndoorBuilding *)indoorBuilding
                                withOptions:(NSDictionary *)options
                             withCompletion:(SITExteriorPOIFetchHandler)exteriorPOIFetchHandler;


/*!
 *  Retrieve the graph of a SITIndoorBuilding
 *
 *  @param indoorBuilding    SITIndoorBuilding object
 *  @param graphFetchHandler SITGraphFetchHandler handler
 *
 *  @return This method gives the user a flag that indicates if the operation has been performed or not.
 *  @discussion You can use the SITGraph object returned on successful operations to compute routes between two SITIndoorPoint 's of a SITIndoorBuilding
 */
- (BOOL)fetchGraphFromIndoorBuilding:(SITIndoorBuilding *)indoorBuilding
                      withCompletion:(SITGraphFetchHandler)graphFetchHandler DEPRECATED_MSG_ATTRIBUTE("Use - (BOOL)fetchGraphFromIndoorBuilding:withOptions:withCompletion:");

/*!
 *  Retrieve the graph of a SITIndoorBuilding
 *
 *  @param indoorBuilding    SITIndoorBuilding object
 *  @param options           additional parameters to customize the internal operation of the method
 *  @param graphFetchHandler SITGraphFetchHandler handler
 *
 *  @return This method gives the user a flag that indicates if the operation has been performed or not.
 *  @discussion You can use the SITGraph object returned on successful operations to compute routes between two SITIndoorPoint 's of a SITIndoorBuilding
 *  @discussion Valid options for this method are: SITForceRequestKey. The value of this key is a NSNumber with a bool value on it. The value of YES means that the request will be directed to the network system directly without checking the cache system. The value of NO means the request will be directed to the cache system and if not found it will be redirected to the network system.
 */
- (BOOL)fetchGraphFromIndoorBuilding:(SITIndoorBuilding *)indoorBuilding
                         withOptions:(NSDictionary *)options
                      withCompletion:(SITGraphFetchHandler)graphFetchHandler;

/**
 *  Retrieve the events of a SITIndoorBuilding
 *
 *  @param indoorBuilding     SITIndoorBuilding object
 *  @param eventsFetchHandler SITEventsFetchHandler
 *
 *  @return This method gives the user a flag that indicates if the operation has been performed or not.
 */
- (BOOL)fetchEventsFromIndoorBuilding:(SITIndoorBuilding *)indoorBuilding
                       withCompletion:(SITEventsFetchHandler)eventsFetchHandler DEPRECATED_MSG_ATTRIBUTE("Use - (BOOL)fetchEventsFromIndoorBuilding:withOptions:withCompletion:");


/**
 *  Retrieve the events of a SITIndoorBuilding
 *
 *  @param indoorBuilding     SITIndoorBuilding object
 *  @param options            additional parameters to customize the internal operation of the method
 *  @param eventsFetchHandler SITEventsFetchHandler
 *
 *  @return This method gives the user a flag that indicates if the operation has been performed or not.
 *  @discussion Valid options for this method are: SITForceRequestKey. The value of this key is a NSNumber with a bool value on it. The value of YES means that the request will be directed to the network system directly without checking the cache system. The value of NO means the request will be directed to the cache system and if not found it will be redirected to the network system.
 */
- (BOOL)fetchEventsFromIndoorBuilding:(SITIndoorBuilding *)indoorBuilding
                          withOptions:(NSDictionary *)options
                       withCompletion:(SITEventsFetchHandler)eventsFetchHandler;

#pragma mark - Events management. As a developer, you should not use this methods. They are for internal use only.

- (BOOL)loginWithUser:(NSString *)user
             password:(NSString *)password
       withCompletion:(SITLoginHandler)loginHandler;

- (BOOL)logoutWithCompletion:(SITLogoutHandler)logoutHandler;

/**
 *  Update calibrations made with the example application
 *
 *  @param indoorLevelIdentifier   Identifier of the SITIndoorLevel object
 *  @param paths                   Paths of the files upload as bulk for the indoorLevelIdentifier level
 *  @param calibrationsPushHandler SITCalibrationsPushHandler object.
 *
 *  @return This method returns a bool value indicating wheter the operation was executed or not.
 *  @discussion This method is restricted to private use.
 */

- (BOOL)pushCalibrationsForFloorIdentifier:(NSInteger )indoorLevelIdentifier
                              filesAtPaths:(NSArray *)paths
                            withCompletion:(SITCalibrationsPushHandler)calibrationsPushHandler;

- (BOOL)createOccurrenceForEvent:(nonnull SITEvent *)event
                     withSuccess:(nonnull SITOccurrenceCompletion)successCompletion
                         failure:(nonnull SITErrorCompletion)errorCompletion;

/**
 *  Update an occurrence
 *
 *  @param occurrence        occurrence to update
 *  @param successCompletion the kind of block that gets performed when a success occurs
 *  @param errorCompletion   the kind of block that gets performed when a failure occurs
 *
 *  @return BOOL value indicating if the operation gets performed (YES) or not (NO)
 */
- (BOOL)updateOccurrence:(nonnull SITOccurrence *)occurrence
                forEvent:(kSITOccurrenceAction)action
             withSuccess:(nonnull SITOccurrenceCompletion)successCompletion
                 failure:(nonnull SITErrorCompletion)errorCompletion;

- (BOOL)retrieveModelForBuilding:(nonnull SITIndoorBuilding *)building
                     withSuccess:(nonnull SITSuccessRetrievingModelCompletion)successCompletion
                         failure:(nonnull SITFailureCompletion)failureCompletion;




@end
#endif
