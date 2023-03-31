#import "SITUtils.h"

@implementation SITUtils

+ (NSArray<NSDictionary *> *) toArrayDict:(NSArray<NSObject<SITMapperProtocol> *> *) objects {
    NSMutableArray *exportedArray = [NSMutableArray new];
    for (NSObject<SITMapperProtocol> * o in objects) {
        [exportedArray addObject:o.toDictionary];
    }
    return exportedArray;
}

@end