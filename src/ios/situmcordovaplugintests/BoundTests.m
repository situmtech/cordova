//
//  BoundTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 13/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface BoundTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation BoundTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/bounds";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testBound1 {
    SITBounds bound1 = [SitumCreatorTests createBoundsWithArray];
    NSDictionary *boundJO1 = [SitumLocationWrapper.shared boundsToJsonObject:bound1];
    NSString *fileName1 =  @"bounds1";
    //read from json object in resources
    NSDictionary *jsonBound1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _filePath];
    [_helper assertBound: jsonBound1: boundJO1];
}

- (void) testBound2 {
    SITBounds bound2 = [SitumCreatorTests createBounds];
    NSDictionary *boundJO2 = [SitumLocationWrapper.shared boundsToJsonObject:bound2];
    NSString *fileName2 =  @"bounds2";
    //read from json object in resources
    NSDictionary *jsonBound2 = [TestingHelper dataFromJSONFileNamed: fileName2 inDirectory : _filePath];
    [_helper assertBound: jsonBound2: boundJO2];
}

@end
