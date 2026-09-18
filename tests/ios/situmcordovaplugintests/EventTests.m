//
//  EventTests.m
//  situmcordovaplugintests
//
//  Created by Cristina Sánchez Barreiro on 13/09/2018.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"
#import "TestingHelper.h"

@interface EventTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;

@end

@implementation EventTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/events";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void) testEvent {
    SITEvent *event1 = [SitumCreatorTests createEvent];
    NSDictionary *eventJO1 = [SitumLocationWrapper.shared eventToJsonObject:event1];
    NSString *fileName1 =  @"event1";
    //read from json object in resources
    NSDictionary *jsonEvent1 = [TestingHelper dataFromJSONFileNamed: fileName1 inDirectory : _filePath];
    [_helper assertEvent: jsonEvent1: eventJO1];
}

@end
