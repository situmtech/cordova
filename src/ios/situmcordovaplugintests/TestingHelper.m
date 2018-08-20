//
//  TestingHelper.m
//  situmcordovapluginTests
//
//  Created by Situm on 24/7/18.
//  Copyright © 2018 Situm. All rights reserved.
//
#import "TestingHelper.h"


@implementation TestingHelper

+ (id)dataFromJSONFileNamed:(NSString *)fileName inDirectory: (NSString *)pathResources {
    //get file path relative to project for avoiding CI integration problems
    NSString *filePath = [[NSBundle bundleForClass:[self class]] pathForResource:fileName
                                                         ofType:@"json"
                                                    inDirectory:pathResources];
    NSDictionary *json = [[NSMutableDictionary alloc] init];
    //check file exists
    if (filePath) {
        //retrieve file content
        NSData *data = [[NSData alloc] initWithContentsOfFile:filePath];
        
        //convert JSON NSData to a usable NSDictionary
        NSError *error;
        json = [NSJSONSerialization JSONObjectWithData:data
                                               options:0
                                                 error:&error];
        if (error) {
            NSLog(@"Something went wrong! %@", error.localizedDescription);
        }
    }
    else {
        NSLog(@"Couldn't find file!");
    }
    return json.copy;
}


@end
