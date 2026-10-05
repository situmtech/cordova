//
//  indicationTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 13/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface indicationTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation indicationTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/indication";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testIndication {
    // ### INDICATION1.JSON ###
    SITIndication *indication1 = [SitumCreatorTests createIndication];
    NSDictionary *indicationJO1 = [SitumLocationWrapper.shared indicationToJsonObject:indication1];
    NSString *fileName1 =  @"indication1";
    //read from json object in resources
    NSDictionary *jsonIndication1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _filePath];
    [_helper assertIndication: jsonIndication1: indicationJO1];
}

@end
