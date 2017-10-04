// clang -arch armv7 -isysroot `xcrun --sdk iphoneos --show-sdk-path` -framework Foundation -o MainBinary main.m 
#include <stdio.h>
#include <dlfcn.h>
#import <Foundation/Foundation.h> 
extern void TestFunction0(void)
{
NSLog(@"iOSRE: %u", arc4random_uniform(0));
}
extern void TestFunction1(void) {
NSLog(@"iOSRE: %u", arc4random_uniform(1));
}
extern void TestFunction2(void) {
NSLog(@"iOSRE: %u", arc4random_uniform(2));
}
extern void TestFunction3(void) {
NSLog(@"iOSRE: %u", arc4random_uniform(3));
}
int main(int argc, char **argv) {
TestFunction3(); return 0;
}
