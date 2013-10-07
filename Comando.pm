#! /usr/bin/perl -w


package Comando;

use warnings;
use strict;

#Hash criado para armazenar todos os comandos existentes na nossa linguagem.
#O seu valor será 1 quando o comando pedir um parâmetro e 0 caso contrário.
my %hashComandos = (
	PUSH => 1,
	POP => 0,
	DUP => 0,
	ADD => 0,
	SUB => 0,
	MUL => 0,
	DIV => 0,
	JMP => 1,
	JIT => 1,
	JIF => 1,
	EQ => 0,
	GT => 0,
	GE => 0, 
	LT => 0,
	LE => 0,
	NE => 0,
	STO => 1,
	RCL => 1,
	END => 0,
	PRN => 0
);

#Construtor do objeto comando.
#Todo comando de um codigo (opcode) e um valor(argumento).
#Caso o comando não exista ou haja erro de sintaxe, a função 'novo' retorna undef.
sub novo
{
  my $class = shift;
	my ($opcode, $valor) = (@_);
  my $self = 
  {
    opcode => shift,
    valor => shift,
  };
	bless $self, $class;
	return $self if($self->existeComando());
	
	return undef;
	
	
}


sub existeComando 
{
	my $self = shift;
	my($opcode, $valor) = ($self->{opcode}, $self->{valor});


	#Verificamos se o comando existe na lista de comandos.
	if(exists $hashComandos{$opcode}){
		#Se ele existir, verificaremos se o comando cumpre seu formato.
		#Caso ele peça um parâmetro, o mesmo deve existir, caso contrário a sintaxe está incorreta.
		#O mesmo ocorre para o caso em que o comando não pede um parâmetro, se o parametro existir,
		#a sintaxe está incorreta.
		if($hashComandos{$opcode} == 1){
			if(defined $valor) {
				return 1;
			}
			else {
				print "Erro de Sintaxe: O Comando $opcode pede um parâmetro.\n";
				return 0;
			}
		}
		elsif($hashComandos{$opcode} == 0) {
	  	if (defined $valor) {
				print "Erro de Sintaxe: O Comando $opcode não pede um parâmetro.\n";
				return 0;
			} 
			else {
				
				return 1;
			}
		}
	}
	#Caso o comando não exista, um erro é impresso.
	else {
		print "Erro de Sintaxe: O Comando $opcode não existe.\n";
		return 0;
	}

}


#Recebe um objeto comando e retorna o codigo desse objeto.
sub getCode
{
	my $self = shift;
  #print "$$self{opcode}\n";
	return $self->{opcode};
}

#Recebe um objeto comando e retorna o valor desse objeto.
sub getValor
{
	my $self = shift;
	return $self->{valor};
}
1;	
