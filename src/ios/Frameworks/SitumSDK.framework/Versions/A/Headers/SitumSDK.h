//
//  SitumSDK.h
//  SitumSDK
//
//  Created by Abraham on 2/3/15.
//  Copyright (c) 2015 Situm. All rights reserved.
//

#ifndef SitumSDK_h
#define SitumSDK_h

#import <Foundation/Foundation.h>

#import "SITBuildingInfo.h"

#import "SITIndoorLocationManager.h"
#import "SITServices.h"

#import "SITIndoorBuilding.h"
#import "SITIndoorLevel.h"
#import "SITIndoorLocation.h"
#import "SITIndoorLocationManagerDelegate.h"

#import "SITPOIBase.h"
#import "SITPOI.h"
#import "SITPOIExterior.h"

#import "SITGraph.h"

#import "SITNode.h"
#import "SITLink.h"

#import "SITEvent.h"

#import "SITAngle.h"
#import "SITDimensions.h"
#import "SITMultilanguageString.h"
#import "SITResource.h"
#import "SITURL.h"
#import "SITBuilding.h"
#import "SITBuildingResource.h"
#import "SITFloor.h"
#import "SITFloorResource.h"

#import "SITCommunicationConstants.h"
#import "SITCommunicationInterface.h"
#import "SITCommunicationManager.h"

#import "SITIndoorPoint.h"
#import "SITIndoorRoute.h"
#import "SITIndoorRouteStep.h"

#import "SITDiagnosisServices.h"
#import "SITCalibrationManager.h"
#import "SITBeaconManager.h"

#import "SITOccurrence.h"
#import "SITKeyValueFilter.h"
#import "SITCustomField.h"
#import "SITPOICategory.h"

#import "SITSearchManager.h"

#import "SITCoordinateConverter.h"

#import "SITLocation.h"
#import "SITLocationRequest.h"
#import "SITLocationError.h"
#import "SITLocationInterface.h"
#import "SITLocationManager.h"

#import "SITDirectionsRequest.h"
#import "SITCartesianCoordinate.h"
#import "SITPoint.h"
#import "SITRouteStep.h"
#import "SITRoute.h"

#import "SITDirectionsInterface.h"
#import "SITDirectionsManager.h"

#import "SITNavigationProgress.h"
#import "SITNavigationInterface.h"
#import "SITNavigationError.h"
#import "SITNavigationManager.h"
#import "SITNavigationRequest.h"

#import "SITBounds.h"


/*!
 *  The Situm Framework (SitumSDK.framework) provides information about the buildings supporting indoor location services as well as location data of a user inside a building. The framework uses a multi-sensor approach to find the user's location inside a building.
 *
 *  @discussion As a developer, if you follow the **Up and Running** section, you only need to
 *  include the SitumSDK.h file in your proyect
 *  in order to work with the APIs. This can be done with the following **#import <SitumSDK/SitumSDK.h>**
 *
 *  
 *  1. Up and Running
 *  ===================
 *  In the next section you will see how to include and configure your project so you can work with the SitumSDK framework.
 *
 *  Adding SitumSDK framework to your project
 *  -----------------------------------------
 *  Drag and drop the framework to your project file structure. 
 *  Make sure you enable **Copy Items if needed** option. 
 *  Also you should check the **Add to targets** option and verify that your project target is selected.
 *
 *  You will also need to include the Core Motion framework.
 *
 *  External Dependencies
 *  ---------------------
 *  Additionally, you will have to include some external dependencies.
 *  
 *  **RestKit** - The quickest way is to install it through Cocoapods.  In order to install RestKit (https://github.com/RestKit/RestKit) manually  you should check the installation page and follow its instructions (https://github.com/RestKit/RestKit/wiki/Installing-RestKit-v0.20.x-as-a-Git-Submodule) 
 *
 *  **GCDAsyncUdpSocket** - Under the external-dependencies folder inside the general framework folder you can see the header and implementation files you should add to your project. Drag and drop them to your project and make sure you enable the **Copy Items if needed** option.
 *
 *  Configuring your API Key 
 *  ------------------------
 *  In order to work with the APIs you should insert your developer API Key:
 *
 *  1. Select **AppDelegate.h** from the project navigator.
 *  2. include the line: **#import <Situm/Situm.h>** before the interface section.
 *  3. In your **didFinishLaunchingWithOptions:** method insert the call **[SITServices provideAPIKey:@"APIKey" forEmail:@"email"]** where you should replace the **@"APIKey"** string with your developer API Key you can find at https://dashboard.situm.es/accounts/profile
 
 * IMPORTANT: Additional configuration:
 * Add libc++.tbd, libz.tbd, CoreLocation and CoreMotion to Link Binary With Libraries section of the build phases tab of the Settings pane.
 * Additionally you should include the following key in your Info.plist file:
 * NSLocationWhenInUseUsageDescription or NSLocationAlwaysUsageDescription with the following value "Location is required to find out where you are" or a custom message that you like.

 */

@interface SitumSDK : NSObject

@end

#endif
