#! /usr/bin/perl -w

use Comando;
use Programa;	
#use Maquina;
package main;
			
#Programa principal.
#Cada linha é interpretada e vira um comando, ao termino do while, temos um programa ($prog),
#que será executado pela maquina ($maq);
$prog = novo Programa;

my $erro = 0;

while(<>)
{
	#Inperpreta linha retorna 0 caso algum erro de sintaxe 
	#tenha sido detectado
	$erro = 1 if(!$prog->interpretaLinha($_));
}
if(!$erro)
{
	$prog->printVetorJava();
	#$maq = novo Maquina($prog);
	#$maq->executa();
}

