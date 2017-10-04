## Here I drop some notes about arm assembly:
- In general:

    Instructions basic format: `op{cond}{s} Rd,Rn,Op2`;
    - `cond` means op will excute under condition `cond` (such as `EQ,NE,LT,GT, etc`).
    - `s` means `op` set flag under condition `s`: `N: negative; Z: zero; C: carry; V: overflow`.
    - e.g. `ADD	R0，R1，R2		；R0 <= R1+R2`

- Mem access:

	`op{cond}{type} Rd, [Rn,?Op2]` (generic).
	- `type`: operate on what data type.
	
	```
	B unsigned Byte 
	SB Signed Byte 
	H unsigned Halfword 
	SH Signed Halfword 
	```
	
	
- Misc

	```
	# CMP
	CMP R0,0 ; IF R0==0 -> Z=1; ELSE Z=0
	BNE Lable ; IF Z==0 THEN JUMP
	
	# LD/ST
	LDR Rt, [Rn {, #offset}] ; Rt = *(Rn {+ offset})
	
	STR Rt, [Rn {, #offset}] ; *(Rn {+ offset}) = Rt
	
	STRD R4, R5, [R9,#offset] ; *(R9 + offset) = R4; *(R9 + offset + 4) = R5
	
	LDRD R4, R5, [R9,#offset] ; R4 = *(R9 + offset); R5 = *(R9 + offset + 4)
	
	# BRANCH
	B Label ; PC = Label
	BL Label ; LR = PC – 4; PC = Label A link register is a special-purpose register which holds the address to return to when a function call completes. This is more efficient than the more traditional scheme of storing return addresses on a call stack, sometimes called a machine stack.
	BX Rd ; PC = Rd and Switch mode(arm/thumb)
	
	# Condition
	cond flag
	EQ Z=1
	NE Z=0
	CS C=1
	HS C=1
	CC C=0
	LO C=0
	MI N=1
	PL N=0
	VS V=1
	VC V=0
	HI C=1&Z=0 LS C=0|Z=1 GE N=V
	LT N!=V
	GT Z=0&N=V LE Z=1|N!=V
	```