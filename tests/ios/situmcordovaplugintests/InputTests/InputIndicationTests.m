//
//  InputIndicationTests.m
//  situmcordovaplugintests
//
//  Created by Adrián Rodríguez on 20/9/18.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface InputIndicationTests : XCTestCase

@property(nonatomic, strong) NSString *pointFilePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation InputIndicationTests

- (void)setUp {
    [super setUp];
    _pointFilePath = @"resources/indication";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    [super tearDown];
}

- (void) testInputIndication {
    // Create the object to convert and test
    NSString *filename =  @"indication1";
    NSDictionary *jsonIndication = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _pointFilePath];
    SITIndication* convertedIndication = [[SitumLocationWrapper shared] indicationJsonObjectToIndication: jsonIndication];
    
    // Create the reference valid object to compare against
    SITIndication* referenceIndication = [SitumCreatorTests createIndication];
    
    // Compare both indications
    [_helper assertIndication: convertedIndication isEqualToIndication: referenceIndication];
}

@end
