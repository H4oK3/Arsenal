//
//     Generated by class-dump 3.5 (64 bit).
//
//     class-dump is Copyright (C) 1997-1998, 2000-2001, 2004-2013 by Steve Nygard.
//

#import "NSObject.h"

#import "NSStreamDelegate.h"

@class ICDDevice, NSInputStream, NSMutableData, NSString;

@interface ICPeerInputStream : NSObject <NSStreamDelegate>
{
    _Bool _isMessage;
    NSInputStream *_inputStream;
    id <ICPeerInputStreamDelegate> _delegate;
    ICDDevice *_device;
    NSMutableData *_data;
    unsigned long long _length;
    unsigned long long _maxLength;
}

- (void).cxx_destruct;
@property(retain, nonatomic) NSMutableData *data; // @synthesize data=_data;
- (void)dealloc;
@property(nonatomic) __weak id <ICPeerInputStreamDelegate> delegate; // @synthesize delegate=_delegate;
@property(nonatomic) __weak ICDDevice *device; // @synthesize device=_device;
- (id)initWithInputStream:(id)arg1;
@property(readonly, nonatomic) NSInputStream *inputStream; // @synthesize inputStream=_inputStream;
@property(nonatomic) _Bool isMessage; // @synthesize isMessage=_isMessage;
@property(nonatomic) unsigned long long length; // @synthesize length=_length;
@property(nonatomic) unsigned long long maxLength; // @synthesize maxLength=_maxLength;
- (void)readDataFrom:(id)arg1;
- (void)startReadLength;
- (void)startReadMessage:(unsigned long long)arg1;
- (void)stream:(id)arg1 handleEvent:(unsigned long long)arg2;

// Remaining properties
@property(readonly, copy) NSString *debugDescription;
@property(readonly, copy) NSString *description;
@property(readonly) unsigned long long hash;
@property(readonly) Class superclass;

@end
