//
//  TestingHelper.h
//  situmcordovapluginTests
//
//  Created by Situm on 24/7/18.
//  Copyright © 2018 Situm. All rights reserved.
//

#import <XCTest/XCTest.h>
#import <Foundation/Foundation.h>

@interface TestingHelper : XCTestCase 


+ (id)dataFromJSONFileNamed:(NSString *)fileName inDirectory:(NSString *) pathResources;
- (void) assertPoint: (NSDictionary *) jsonPointFile : (NSDictionary *) pointJO;
- (void) assertCoordinate: (NSDictionary *) jsonCoordinateFile : (NSDictionary *) coordinateJO;
- (void) assertCartesianCoordinate: (NSDictionary *) jsonCartesianCoordinateFile : (NSDictionary *) cartesianCoordinateJO;

@end
