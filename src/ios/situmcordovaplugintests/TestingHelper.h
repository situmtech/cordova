//
//  TestingHelper.h
//  situmcordovapluginTests
//
//  Created by Situm on 24/7/18.
//  Copyright © 2018 Situm. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface TestingHelper : NSObject {
}


+(id)dataFromJSONFileNamed:(NSString *)fileName inDirectory:(NSString *) pathResources;
//-(id)assertLocation:(NSDictionary *)jsonLocation :(NSDictionary*) locationJO;

@end
