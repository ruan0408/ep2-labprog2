PUSH 1
STO 0


loop:
	RCL 0
	PUSH 7
	EQ
	JIF cont
	PUSH 1
	STO 0

cont:
	RCL 0	
	GETROBO
	PUSH 3
	ISA
	JIF soma
	RCL 0
	ATK
soma:
	RCL 0
	PUSH 1
	ADD
	STO 0
	JMP loop

