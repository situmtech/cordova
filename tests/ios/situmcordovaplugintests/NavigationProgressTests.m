//
//  NavigationProgressTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 13/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface NavigationProgressTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation NavigationProgressTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/navigationProgress";
    _helper = [TestingHelper new];
}
- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testNavigationProgress1 {
    SITNavigationProgress *navigationProgress1 = [SitumCreatorTests createNavigationProgressOutdoor];
    NSDictionary *navigationProgressJO1 = [SitumLocationWrapper.shared navigationProgressToJsonObject:navigationProgress1];
    NSString *fileName1 =  @"navigationProgress1";
    //read from json object in resources
    NSDictionary *jsonNavigationProgress1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _filePath];
    [_helper assertNavigationProgress: jsonNavigationProgress1: navigationProgressJO1];
}

- (void) testNavigationProgress2 {
    SITNavigationProgress *navigationProgress2 = [SitumCreatorTests createNavigationProgressIndoor];
    NSDictionary *navigationProgressJO2 = [SitumLocationWrapper.shared navigationProgressToJsonObject:navigationProgress2];
    NSString *fileName2 =  @"navigationProgress2";
    //read from json object in resources
    NSDictionary *jsonNavigationProgress2 = [TestingHelper dataFromJSONFileNamed: fileName2 inDirectory : _filePath];
    [_helper assertNavigationProgress: jsonNavigationProgress2: navigationProgressJO2];
}

@end
