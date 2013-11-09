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

$n = @ARGV;
open (SAIDA, ">Main.java") or die "Erro ao tentar iniciar o programa";
print SAIDA <<INTRO;
public class Main{
	public static void main(String args[]){
		Programa[] programas = new Programa[$n];
		Mapa mapa = new Mapa("mapa.txt");
	    Arena arena = new Arena(mapa);
	    Programa programa;


INTRO

my $i = 0;
while(@ARGV)
{
	my $arq = shift @ARGV;
	open (IN, $arq) or die "Erro ao tentar abrir o arquivo $arq\n";
	while(<IN>)
	{
		#Inperpreta linha retorna 0 caso algum erro de sintaxe 
		#tenha sido detectado
		$erro = 1 if(!$prog->interpretaLinha($_));
	}
	if(!$erro)
	{
		$prog->printVetorJava(\*SAIDA);
		#$maq = novo Maquina($prog);
		#$maq->executa();
	}
	
#	print SAIDA "posicoes[$i] = new Posicao($i,$i);\n";
	print SAIDA "programas[$i] = programa;\n";
	$i++;
	close IN
}

print SAIDA <<FIM;

		MapaVisual mv = new MapaVisual(mapa, 800, 800,30);

		mv.abreJanela();


		arena.insereExercito(programas, 1);

		while(arena.atualiza())
		{
			mv.atualiza();

			try {
   				 Thread.sleep(100);
			} catch(InterruptedException ex) {
   				 Thread.currentThread().interrupt();
			}
		}
		
	}
}

FIM


