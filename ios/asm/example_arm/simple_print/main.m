// clang -arch arm64 -isysroot `xcrun --sdk iphoneos --show-sdk-path` -o main.bin main.m
#include <stdio.h>

int main(int argc, char**argv){
    printf("%d,%d,%d,%d,%d",1,2,3,4,5);
    return 6;
}
