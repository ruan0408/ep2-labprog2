	PUSH 1
	STO 0
loop1:
	RCL 0
	LOOK
	PUSH 2
	ISA


	JIT andar

	RCL 0
	PUSH 1
	ADD
	DUP
	PUSH 7
	EQ
	JIF fimdoloop
	POP
	PUSH 1

fimdoloop:

	STO 0
	JMP loop1

andar:
	PUSH HUE1
	PRN
	RCL 0
	WALK
		PUSH HUE2
	PRN
	JMP loop1
