//
//  PoiCategoryIconTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 17/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface PoiCategoryIconTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end


@implementation PoiCategoryIconTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/poiCategoryIcon";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testPoiCategoryIcon {
    UIImage *image = [SitumCreatorTests createPoiCategoryIcon];
    
    NSDictionary *imageJO = [SitumLocationWrapper.shared bitmapToJsonObject:image];
    NSString *fileName = @"poiCategoryIcon1";
    //read from json object in resources
    NSDictionary *jsonImage = [TestingHelper dataFromJSONFileNamed: fileName inDirectory : _filePath];
    NSLog(@"file")
    [_helper assertImage: jsonImage: imageJO];
}

@end
