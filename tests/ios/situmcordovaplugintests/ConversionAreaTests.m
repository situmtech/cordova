//
//  ConversionAreaTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 13/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface ConversionAreaTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation ConversionAreaTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/situmConversionArea";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testSitumConversionArea {
    // ### POINT1.JSON ###
    SITRectangularArea *conversionArea1 = [SitumCreatorTests createSitumConversionArea];
    NSDictionary *conversionAreaJO1 = [SitumLocationWrapper.shared conversionAreaToJsonObject:conversionArea1];
    NSString *fileName1 =  @"situmConversionArea1";
    //read from json object in resources
    NSDictionary *jsonConversionArea1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _filePath];
    [_helper assertConversionArea: jsonConversionArea1: conversionAreaJO1];
}

@end
