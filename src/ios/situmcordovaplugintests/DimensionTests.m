//
//  DimensionTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 13/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"


@interface DimensionTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation DimensionTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/dimensions";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testDimension {
    SITDimensions *dimension1 = [SitumCreatorTests createDimensions];
    NSDictionary *dimensionJO1 = [SitumLocationWrapper.shared dimensionsToJsonObject:dimension1];
    NSString *fileName1 =  @"dimensions1";
    //read from json object in resources
    NSDictionary *jsonDimension1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _filePath];
    [_helper assertDimension: jsonDimension1: dimensionJO1];
}

@end
