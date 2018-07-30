//
//  SITSearchManager.h
//  SitumSDK
//
//  Created by A Barros on 10/6/16.
//  Copyright © 2016 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "SITKeyValueFilter.h"
#import "SITPOIBase.h"

/**
 *  Search error codes. It defines the kind of error codes available
 */
typedef NS_ENUM(int, kSITSearchErrorCodes) {
    /**
     *  Invalid entry parameter
     */
    kSITSearchErrorBadArgumentCode = 1,
    /**
     *  Invalid handler
     */
    kSITSearchErrorInvalidCompletionCode,
};

/**
 *  It specifies the available mode when it comes to concatenate results during a filter operation
 */
typedef NS_ENUM(int, kSITSearchFilterModes) {
    
    /**
     *  Every element must meet all conditions
     */
    kSITSearchFilterModeAND = 0,
    
    /**
     *  Every element must meet at least one condition
     */
    kSITSearchFilterModeOR,
};

/**
 *  The kind of callback that will be called when the method + filterCustomFieldsOfElementsbyRegexes:error:options:withCompletion: gets executed
 *
 *  @param filteredResults the results obtained after performed the query
 */
typedef void (^SITSearchManagerResultsCompletion) ( NSArray<SITPOIBase *> *filteredResults);

/**
 *  SITSearchManager it the central component of Situm SDK that performs queries.
 */
@interface SITSearchManager : NSObject

/**
 *  Determines the elements of an array that meet an array of filters. Specifically, this method filter the property customFields of the introduced objects
 *
 *  @param elements       the initial array
 *  @param regexes    the array of SITKeyValueFilter objects to filter by. NOTE: If more than one filter is supplied, and OR operation will be performed among results of each particular filter
 *  @param error      error describing why the operation could not be executed. It can be nil.
 *  @param options    NSDictionary containing parameters to modify the internal operation of the method. Available keys are:
 *  kSITSearchOptionOperationQueue (NSOperationQueue) : If provided the callback will be called into the NSOperationQueue. If not the main thread is selected
 *  kSITSearchFilterMode NSNumber containing of the values on the kSITSearchFilterModes set. Defaul is kSITSearchFilterModeAND.
 *  @param completion SITSearchManagerResultsCompletion completion callback
 *
 *  @return BOOL value indicating if the operation will be performed (YES) or not (NO)
 */
+ (BOOL)filterCustomFieldsOfElements:(NSArray<SITPOIBase *> *)elements
         byRegexes:(NSArray<SITKeyValueFilter *> *)regexes
             error:(NSError **)error
           options:(NSDictionary *)options
    withCompletion:(SITSearchManagerResultsCompletion)completion;



@end
