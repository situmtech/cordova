//
//  SitumTests.h
//  situmcordovapluginTests
//
//  Created by Situm on 15/7/18.
//  Copyright © 2018 Situm. All rights reserved.
//

#import <XCTest/XCTest.h>
#import "SitumCreatorTests.h"
#import "TestingHelper.h"
#import "SitumLocationWrapper.h"
#import <SitumSDK/SitumSDK.h>

@interface SitumTests: XCTestCase

@property (nonatomic, strong) TestingHelper *helper;
@property (nonatomic, strong)  NSString *stepFilePath;

- (void) setUp;
- (void) tearDown;
- (void) testSitumConversionArea;

@end
