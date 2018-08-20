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
    kSITQualityValues quality = kSITHigh;
    NSString *provider = @"TEST_PROVIDER";
    float accuracy = 5;
    SITLocation *location = [[SITLocation alloc] initWithTimestamp:timestamp position:position bearing: bearing cartesianBearing:cartesianBearing quality:quality accuracy:accuracy provider:provider];
    return location;
}

//location2.json
//+ (SITLocation *) createLocationWithCoordinate {
    
//}

@end

