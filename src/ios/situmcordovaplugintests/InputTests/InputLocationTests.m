//
//  InputLocationTests.m
//  situmcordovaplugintests
//
//  Created by Adrián Rodríguez on 20/9/18.
//

#import <XCTest/XCTest.h>
#import "TestingHelper.h"
#import "SitumCreatorTests.h"
#import "SitumLocationWrapper.h"

@interface InputLocationTests : XCTestCase

@property (nonatomic, strong) NSString *filePath;
@property (nonatomic, strong) TestingHelper *helper;


@end

@implementation InputLocationTests

- (void)setUp {
    [super setUp];
    _filePath = @"resources/location";
    _helper = [TestingHelper new];
}

- (void)tearDown {
    [super tearDown];
}

- (void) testLocationWithFloorAndCartesianCoordinate {
    // Create the object to convert and test
    NSString *filename =  @"location1";
    NSDictionary *jsonLocation = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _filePath];
    SITLocation* convertedLocation = [[SitumLocationWrapper shared] locationJsonObjectToLocation: jsonLocation];
    
    // Create the reference valid object to compare against
    SITLocation* referenceLocation = [SitumCreatorTests createLocationWithBuildingFloorAndCartesianCoordinates];
    
    // Compare both coordinates
    [_helper assertLocation: convertedLocation isEqualToLocation: referenceLocation];
}

- (void) testLocationWithCoordinate {
    // Create the object to convert and test
    NSString *filename =  @"location2";
    NSDictionary *jsonLocation = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _filePath];
    SITLocation* convertedLocation = [[SitumLocationWrapper shared] locationJsonObjectToLocation: jsonLocation];
    
    // Create the reference valid object to compare against
    SITLocation* referenceLocation = [SitumCreatorTests createLocationWithCoordinate];
    
    // Compare both coordinates
    [_helper assertLocation: convertedLocation isEqualToLocation: referenceLocation];
}

- (void) testLocationWithBuildingAndCoordinate {
    // Create the object to convert and test
    NSString *filename =  @"location3";
    NSDictionary *jsonLocation = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _filePath];
    SITLocation* convertedLocation = [[SitumLocationWrapper shared] locationJsonObjectToLocation: jsonLocation];
    
    // Create the reference valid object to compare against
    SITLocation* referenceLocation = [SitumCreatorTests createLocationWithBuildingAndCoordinate];
    
    // Compare both coordinates
    [_helper assertLocation: convertedLocation isEqualToLocation: referenceLocation];
}

- (void) testLocationWithCartesianBearing {
    // Create the object to convert and test
    NSString *filename =  @"location4";
    NSDictionary *jsonLocation = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _filePath];
    SITLocation* convertedLocation = [[SitumLocationWrapper shared] locationJsonObjectToLocation: jsonLocation];
    
    // Create the reference valid object to compare against
    SITLocation* referenceLocation = [SitumCreatorTests locationWithCartesianBearing];
    
    // Compare both coordinates
    [_helper assertLocation: convertedLocation isEqualToLocation: referenceLocation];
}

- (void) testLocationWithoutCartesianBearing {
    // Create the object to convert and test
    NSString *filename =  @"location5";
    NSDictionary *jsonLocation = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _filePath];
    SITLocation* convertedLocation = [[SitumLocationWrapper shared] locationJsonObjectToLocation: jsonLocation];
    
    // Create the reference valid object to compare against
    SITLocation* referenceLocation = [SitumCreatorTests locationWithoutCartesianBearing];
    
    // Compare both coordinates
    [_helper assertLocation: convertedLocation isEqualToLocation: referenceLocation];
}

- (void) testLocationWithBearing {
    // Create the object to convert and test
    NSString *filename =  @"location6";
    NSDictionary *jsonLocation = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _filePath];
    SITLocation* convertedLocation = [[SitumLocationWrapper shared] locationJsonObjectToLocation: jsonLocation];
    
    // Create the reference valid object to compare against
    SITLocation* referenceLocation = [SitumCreatorTests locationWithBearing];
    
    // Compare both coordinates
    [_helper assertLocation: convertedLocation isEqualToLocation: referenceLocation];
}

- (void) testLocationWithoutBearing {
    // Create the object to convert and test
    NSString *filename =  @"location7";
    NSDictionary *jsonLocation = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _filePath];
    SITLocation* convertedLocation = [[SitumLocationWrapper shared] locationJsonObjectToLocation: jsonLocation];
    
    // Create the reference valid object to compare against
    SITLocation* referenceLocation = [SitumCreatorTests locationWithouthBearing];
    
    // Compare both coordinates
    [_helper assertLocation: convertedLocation isEqualToLocation: referenceLocation];
}

- (void) testLocationWithBearingQualityLow {
    // Create the object to convert and test
    NSString *filename =  @"location8";
    NSDictionary *jsonLocation = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _filePath];
    SITLocation* convertedLocation = [[SitumLocationWrapper shared] locationJsonObjectToLocation: jsonLocation];
    
    // Create the reference valid object to compare against
    SITLocation* referenceLocation = [SitumCreatorTests indoorLocationWithIndoorBearingQualityLow];
    
    // Compare both coordinates
    [_helper assertLocation: convertedLocation isEqualToLocation: referenceLocation];
}

- (void) testLocationWithBearingQualityHigh {
    // Create the object to convert and test
    NSString *filename =  @"location9";
    NSDictionary *jsonLocation = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _filePath];
    SITLocation* convertedLocation = [[SitumLocationWrapper shared] locationJsonObjectToLocation: jsonLocation];
    
    // Create the reference valid object to compare against
    SITLocation* referenceLocation = [SitumCreatorTests indoorLocationWithIndoorBearingQualityHigh];
    
    // Compare both coordinates
    [_helper assertLocation: convertedLocation isEqualToLocation: referenceLocation];
}

- (void) testLocationOutdoor {
    // Create the object to convert and test
    NSString *filename =  @"location10";
    NSDictionary *jsonLocation = [TestingHelper dataFromJSONFileNamed: filename inDirectory : _filePath];
    SITLocation* convertedLocation = [[SitumLocationWrapper shared] locationJsonObjectToLocation: jsonLocation];
    
    // Create the reference valid object to compare against
    SITLocation* referenceLocation = [SitumCreatorTests outdoorLocation];
    
    // Compare both coordinates
    [_helper assertLocation: convertedLocation isEqualToLocation: referenceLocation];
}

@end
