inicio:
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
	DUP 
	PRN
	COLLECT
	JIF procuraCristal
	PUSH GG
	PRN
	JMP levaBase
	END


levaBase: #supondo que o time inimigo seja o time 2
	PUSH 2
	GETTIME
	GETBASE
	GETPOS
	STO 3
	RCL 3
	GETX
	PUSH 0
	LOOK
	GETPOS 
	GETX
	SUB
	DUP
	PUSH 0
	LT
	JIT baseAcima
	PUSH 0
	GT
	JIT baseAbaixo
	PUSH WATAFUQ
	PRN
	JMP linhaDaBase

baseAcima:
	PUSH CIMA
	PRN
	PUSH 1
	STO 4 
	JMP baseLoop

baseAbaixo:
	PUSH BAIXO
	PRN
	PUSH 4
	STO 4
	
baseLoop:
	RCL 4 # Direção definida acima
	WALK
	PUSH 0
	LOOK 
	GETPOS
	GETX
	RCL 3
	GETX
	EQ
	JIF baseLoop

linhaDaBase:
	PUSH QUASELA
	PRN
	RCL 3
	GETY
	DUP
	PRN
	PUSH 0
	LOOK
	GETPOS
	GETY
	DUP
	PRN
	DUP
	STO 5 # GUARDA O Y DO ROBO
	SUB
	DUP
	DUP
	PRN
	PUSH 0
	GT #SEGUNDO MAIOR QUE O PRIMEIRO
	JIT direita
	PUSH 0 
	LT
	JIT esquerda
	JMP dropar

# 6 - direção caso coluna impar
# 7 - direção caso coluna par

direita:
	PUSH DIREITA
	PRN
	PUSH 2
	STO 6 
	PUSH 3
	STO 7
	JMP andaDeLado

esquerda:
	PUSH ESQUERDA
	PRN
	PUSH 6
	STO 6
	PUSH 5
	STO 7

andaDeLado:
	PUSH 0
	LOOK
	GETPOS
	GETY
	PUSH 2
	MOD
	DUP
	PRN
	PUSH 1
	EQ
	JIT impar
par:
	RCL 7
	WALK
	JMP seraQueChegou

impar:
	RCL 6
	WALK
	#JMP seraQueChegou
# 3 guarda a pos da base
seraQueChegou:
	RCL 3
	GETY
	PUSH 0
	LOOK
	GETPOS
	GETY
	EQ
	JIT dropar
	JMP andaDeLado

dropar:
	PUSH 0
	DROP
	PUSH GG2
	PRN
	PUSH TENHO
	PRN
	PUSH QUE
	PRN
	PUSH PEGAR
	PRN
	PUSH MAIS
	PRN
	JMP inicio
	



procuraBase: #E coloca na posicao 3 da memoria

	MYTIME
	STO 3


