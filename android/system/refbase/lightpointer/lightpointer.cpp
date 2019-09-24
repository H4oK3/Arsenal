#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <utils/RefBase.h>
      
using  namespace android;

class LightClass : public LightRefBase<LightClass>
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
 //    Test *t1 = new Test();
 //    Test t2;

 //    printf("t1 : %p, t2: %p", (&t1, &t2));
    
 //    void *a;
 //    void *b;
 //    void *c;
	// a = malloc(2);
	// b = malloc(8);
	// c = malloc(16);

 //    memset(a, 'a', 2);
 //    memset(b, 'b', 8);
 //    memset(c, 'c', 16);

	// printf("a : %p\n",a);
	// printf("b : %p\n",b);
	// printf("c : %p\n",c);

	printf("Init sp stuff..");
	LightClass* pLightClass = new LightClass();
	sp<LightClass> lpOut = pLightClass;
    lpOut -> lightPrint();

	printf("Light Ref Count: %d.\n", pLightClass->getStrongCount());



	// {
	// 	sp<LightClass> lpInner = lpOut;

	// 	printf("Light Ref Count: %d.\n", pLightClass->getStrongCount());
	// }

	// printf("Light Ref Count: %d.\n", pLightClass->getStrongCount());

	return 0;
}
