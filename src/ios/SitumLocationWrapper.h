#import <SitumSDK/SitumSDK.h>

@interface SitumLocationWrapper : NSObject {
    // Member variables go here.
}
+ (SitumLocationWrapper *)shared;

//Building

- (NSDictionary *) buildingToJsonObject:(SITBuilding *) building;

//Building (should be deprecated)

- (NSDictionary *) buildingIndoorToJsonObject:(SITIndoorBuilding *) building __deprecated;


//Floor

- (NSDictionary *) floorToJsonObject:(SITFloor *) floor;

- (SITFloor *) jsonObjectToFloor:(NSDictionary *) joFloor;


//Event

- (NSDictionary *) eventToJsonObject:(SITEvent *) event;

// Category

- (NSDictionary *) poiCategoryToJsonObject:(SITPOICategory *) category;

// POI

- (NSDictionary *) poiToJsonObject:(SITPOI *) poi;


// Location

- (NSDictionary *) locationToJsonObject:(SITLocation *) location;
- (NSString*) locationStateToString:(SITLocationState) state;
- (NSDictionary *) locationStateToJsonObject:(SITLocationState) state;

- (SITLocation *) locationJsonObjectToLocation:(NSDictionary *) jo;

// Coordinate

- (NSDictionary *) coordinateToJsonObject:(CLLocationCoordinate2D) coordinate;

- (CLLocationCoordinate2D) coordinateJsonObjectToCoordinate:(NSDictionary *) jo;

// Point

- (NSDictionary *) pointToJsonObject:(SITPoint *) point;

- (SITPoint *) pointJsonObjectToPoint:(NSDictionary *) jo;

// CartesianCoordinate

- (NSDictionary *) cartesianCoordinateToJsonObject:(SITCartesianCoordinate *) cartesianCoordinate;

- (SITCartesianCoordinate *) cartesianCoordinateJsonObjectToCartesianCoordinate:(NSDictionary *) jo;

// Dimensions

- (NSDictionary *) dimensionsToJsonObject:(SITDimensions *) dimensions;

// Bounds

- (NSDictionary *) boundsToJsonObject:(SITBounds) bounds;

// Angle

- (NSDictionary *) angleToJsonObject:(SITAngle *) angle;

// Route

- (NSDictionary *) routeToJsonObject:(SITRoute *) route;

//RouteStep

- (NSDictionary *) routeStepToJsonObject:(SITRouteStep *) routeStep;

- (SITRouteStep *) routeStepJsonObjectToRouteStep:(NSDictionary *) jo;

// Indication

- (NSDictionary *) indicationToJsonObject:(SITIndication *) indication;

- (SITIndication *) indicationJsonObjectToIndication:(NSDictionary *) jo;

// NavigationProgress

- (NSDictionary *) navigationProgressToJsonObject:(SITNavigationProgress *) navigationProgress;

@end
