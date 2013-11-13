PUSH 1
STO 1 

procuraCristal:
	PUSH 1
	STO 0
loopProcura:
	RCL 0
	LOOK
	PUSH 4
	ISA
	JIT coleta
	RCL 0
	PUSH 1
	ADD
	STO 0
	RCL 0
	PUSH 7
	EQ
	JIT anda
	JMP loopProcura

anda:
	RCL 1
	WALK 
	JIT procuraCristal
	RCL 1
	PUSH 1 
	ADD
	DUP
	STO 1
	PUSH 7
	EQ
	JIF anda
	PUSH 1
	STO 1
	JMP anda

coleta:
	RCL 0
	COLLECT
	JMP daRole
dropa:	
	PUSH 1
	DROP
	PUSH PARTIU
	PRN
	END

daRole:
	PUSH 5
	WALK
	PUSH 6
	WALK
	PUSH 5
	WALK
	PUSH 6
	WALK
	PUSH 5
	WALK
	PUSH 6
	WALK
	JMP dropa