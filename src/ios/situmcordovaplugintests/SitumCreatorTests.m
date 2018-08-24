//
//  situmcordovapluginCreatorTests.m
//  situmcordovapluginTests
//
//  Created by Situm on 01/7/18.
//  Copyright © 2018 Situm. All rights reserved.
//

#import "SitumCreatorTests.h"

@implementation SitumCreatorTests

//angle1.json
+ (SITAngle *) createAngleFromDegrees {
    SITAngle *angle = [[SITAngle alloc] initWithDegrees:47];
    return angle;
}

//angle2.json
+ (SITAngle *) createAngleFromRadians {
    SITAngle *angle = [[SITAngle alloc] initWithRadians:1.4];
    return angle;
}

//bounds1.json
+ (SITBounds) createBoundsWithArray {
    SITBounds Bounds;
    Bounds.southWest = CLLocationCoordinate2DMake(2, 4);
    Bounds.southEast = CLLocationCoordinate2DMake(6, 3);
    Bounds.northWest = CLLocationCoordinate2DMake(2, 6);
    Bounds.northEast = CLLocationCoordinate2DMake(5, 8);
    return Bounds;
}

//bounds2.json
//NOTE: it is redundant in ios, it is equals to previous bounds1.json
+ (SITBounds) createBounds {
    SITBounds Bounds;
    Bounds.southWest = CLLocationCoordinate2DMake(2, 4);
    Bounds.southEast = CLLocationCoordinate2DMake(6, 3);
    Bounds.northWest = CLLocationCoordinate2DMake(2, 6);
    Bounds.northEast = CLLocationCoordinate2DMake(5, 8);
    return Bounds;
}

//cartesianCoordinate1.json
+ (SITCartesianCoordinate *) createCartesianCoordinate {
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:5 y:7];
    return cartesianCoordinate;
}

//coordinate1.json
+ (CLLocationCoordinate2D) createCoordinate {
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(23, 43);
    return coordinate;
}

//dimensions1.json
+ (SITDimensions *) createDimensions {
    SITDimensions *dimensions = [[SITDimensions alloc] initWithWidth:5 height:7];
    return dimensions;
}

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

//point1.json
+ (SITPoint *) createPointWithCoordinate {
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(3, 6);
    SITPoint *point = [[SITPoint alloc] initWihtCoordinate:coordinate];
    return point;
}

//point2.json
+ (SITPoint *) createPointWithCoordinateAndBuildingId {
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(54, 64);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:0 y:0];
    SITPoint *point  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"-1" cartesianCoordinate: cartesianCoordinate];
    return point;
}

//point3.json
+ (SITPoint *) createPointWithBuildingIdAndFloor {
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(0, 0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:5 y:7];
    SITPoint *point  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"101" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    return point;
}

//point4.json
+ (SITPoint *) createPointWithBuildingWithAngleFromDegrees {
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(12.3,14.0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:4 y:7];
    SITPoint *point  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"-1" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    return point;
}

//point5.json
+ (SITPoint *) createPointWithBuildingWithAngleFromRadians {
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(12.3,14.0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:4 y:7];
    SITPoint *point  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"-1" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    return point;
}

//point6.json
+ (SITPoint *) createPointWithBuildingWithAddress {
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(12.3,14.0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:4 y:7];
    SITPoint *point  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"-1" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    return point;
}

//point7.json
+ (SITPoint *) createPointWithBuildingWithInfo {
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(12.3,14.0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:4 y:7];
    SITPoint *point  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"-1" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    return point;
}

//point8.json
+ (SITPoint *) createPointWithBuildingWithPicture {
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(12.3,14.0);
    SITCartesianCoordinate *cartesianCoordinate = [[SITCartesianCoordinate alloc] initWithX:4 y:7];
    SITPoint *point  = [[SITPoint alloc]  initWithCoordinate:coordinate buildingIdentifier:@"-1" floorIdentifier:@"12" cartesianCoordinate: cartesianCoordinate];
    return point;
}

@end

