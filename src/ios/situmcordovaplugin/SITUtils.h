#import <SitumSDK/SitumSDK.h>

@interface SITUtils : NSObject

+ (NSArray<NSDictionary *> *) toArrayDict:(NSArray<NSObject<SITMapperProtocol> *> *) objects;

@end