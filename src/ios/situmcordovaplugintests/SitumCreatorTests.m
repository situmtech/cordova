//
//  situmcordovapluginCreatorTests.m
//  situmcordovapluginTests
//
//  Created by Situm on 01/7/18.
//  Copyright © 2018 Situm. All rights reserved.
//

#import "SitumCreatorTests.h"

@implementation SitumCreatorTests

//location1.json
+ (SITLocation *) createLocationWithBuildingFloorAndCartesianCoordinates {
    NSTimeInterval timestamp = 14676784;
    //empty coordinate 0,0
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(0, 0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:45 y:46];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    float bearing = 0; //degrees
    float cartesianBearing = 0; //radians
    kSITQualityValues quality = kSITLow;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//location2.json
+ (SITLocation *) createLocationWithCoordinate {
    NSTimeInterval timestamp = 14676784;
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(8.6, 10.5);
    SITPoint *position  = [[SITPoint alloc]  initWihtCoordinate:coordinate];
    float bearing = 0; //degrees
    float cartesianBearing = 0; //radians
    kSITQualityValues quality = kSITLow;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//location3.json
+ (SITLocation *) createLocationWithBuildingAndCoordinate {
    NSTimeInterval timestamp = 14676784;
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(8.6, 10.5);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:0 y:0];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"-1" cartesianCoordinate: cartesianCoordinate];
    float bearing = 0; //degrees
    float cartesianBearing = 0; //radians
    kSITQualityValues quality = kSITLow;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//location4.json
+ (SITLocation *) locationWithCartesianBearing {
    NSTimeInterval timestamp = 14676784;
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(0, 0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:45 y:46];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    float bearing = 92; //degrees
    float cartesianBearing = 2; //radians
    kSITQualityValues quality = kSITHigh;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//location5.json
+ (SITLocation *) locationWithoutCartesianBearing {
    NSTimeInterval timestamp = 14676784;
    //empty coordinate 0,0
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(0, 0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:45 y:46];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    float bearing = 0; //degrees
    float cartesianBearing = 0; //radians
    kSITQualityValues quality = kSITLow;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//location6.json
+ (SITLocation *) locationWithBearing {
    NSTimeInterval timestamp = 14676784;
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(8.6, 10.5);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:0 y:0];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"-1" floorIdentifier:@"-1" cartesianCoordinate: cartesianCoordinate];
    float bearing = 92; //degrees
    float cartesianBearing = 0; //radians
    kSITQualityValues quality = kSITLow;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//location7.json
+ (SITLocation *) locationWithouthBearing {
    NSTimeInterval timestamp = 14676784;
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(0, 0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:45 y:46];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    float bearing = 0; //degrees
    float cartesianBearing = 0; //radians
    kSITQualityValues quality = kSITLow;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//location8.json
+ (SITLocation *) indoorLocationWithIndoorBearingQualityLow {
    NSTimeInterval timestamp = 14676784;
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(0, 0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:45 y:46];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    float bearing = 92; //degrees
    float cartesianBearing = 2; //radians
    kSITQualityValues quality = kSITLow;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//location9.json
+ (SITLocation *) indoorLocationWithIndoorBearingQualityHigh {
    NSTimeInterval timestamp = 14676784;
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(0, 0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:45 y:46];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    float bearing = 92; //degrees
    float cartesianBearing = 2; //radians
    kSITQualityValues quality = kSITHigh;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//location10.json
+ (SITLocation *) outdoorLocation {
    NSTimeInterval timestamp = 14676784;
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(8.6, 10.5);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:0 y:0];
    SITPoint *position  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"-1" floorIdentifier:@"-1" cartesianCoordinate: cartesianCoordinate];
    float bearing = 92; //degrees
    float cartesianBearing = 0; //radians
    kSITQualityValues quality = kSITHigh;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

@end

