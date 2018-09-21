//
//  InputPoiCategoryTests.m
//  situmcordovaplugintests
//
//  Created by Adrián Rodríguez on 20/9/18.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface InputPoiCategoryTests : XCTestCase

@property(nonatomic, strong) NSString *pointFilePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation InputPoiCategoryTests

- (void)setUp {
    [super setUp];
    _pointFilePath = @"resources/poiCategory";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    [super tearDown];
}

- (void) testPoiCategory {
    // Create the object to convert and test
    NSString *filename =  @"poiCategory1";
    NSDictionary *jsonPoiCategory = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    // TODO: This conversion doesn't set isOutdoor
    SITPOICategory* convertedCategory = [[SitumLocationWrapper shared] poiCategoryFromJsonObject: jsonPoiCategory];
    
    // Create the reference valid object to compare against
    SITPOICategory* referenceCategory = [SitumCreatorTests createPoiCategory];
    
    // Compare both poiCategory
    [_helper assertPoiCategory: convertedCategory isEqualToPoiCategory: referenceCategory];
}

@end
