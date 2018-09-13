//
//  AngleTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 13/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "TestingHelper.h"
#import "SitumLocationWrapper.h"

@interface AngleTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation AngleTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/angle";
    _helper = [TestingHelper new];
    // Put setup code here. This method is called before the invocation of each test method in the class.
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testAngle1 {
    
    SITAngle *angle1 = [SitumCreatorTests createAngleFromDegrees];
    NSDictionary *angleJO1 = [SitumLocationWrapper.shared angleToJsonObject:angle1];
    NSString *fileName1 =  @"angle1";
    //read from json object in resources
    NSDictionary *jsonAngle1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _filePath];
    [_helper assertAngle: jsonAngle1: angleJO1];
}

- (void) testAngle2 {
    SITAngle *angle2 = [SitumCreatorTests createAngleFromRadians];
    NSDictionary *angleJO2 = [SitumLocationWrapper.shared angleToJsonObject:angle2];
    NSString *fileName2 =  @"angle2";
    //read from json object in resources
    NSDictionary *jsonAngle2 = [TestingHelper dataFromJSONFileNamed: fileName2 inDirectory : _filePath];
    [_helper assertAngle: jsonAngle2: angleJO2];
}

@end
