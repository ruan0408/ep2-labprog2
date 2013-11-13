#! /usr/bin/perl -w

use Comando;
use Programa;	
#use Maquina;
package main;
			
#Programa principal.
#Cada linha é interpretada e vira um comando, ao termino do while, temos um programa ($prog),
#que será executado pela maquina ($maq);



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

my @arquivos;
my $proxTime;

my @args = @ARGV;
push @args,0;

my $time = shift(@args);
die "Erro de sintaxe na linha de comando" if($time !~ /\d+/);
while($time > 0 && @args)
{
	$i = 0;
	$n = 0;
	my $arq = 0 ;
	while(@args)
	{
		$arq =  shift @args;
		last if($arq =~ /\d+/);
		push @arquivos, $arq;

	}
	$proxTime = $arq;
	$n = @arquivos;
	print SAIDA "programas = new Programa[$n];\n";
	while(@arquivos)
	{
		my $arq =  shift @arquivos;
		interpretaArquivo($arq);
	}

	print SAIDA "arena.insereExercito(programas, $time);\n";
	$time = $proxTime;
}

print SAIDA <<FIM;

		MapaVisual mv = new MapaVisual(mapa, (mapa.largura()*60),(int)(mapa.altura()*Math.sqrt(3)*30), 30);

		mv.abreJanela();


		while(arena.atualiza())
		{
			mv.atualiza();

			try 
			{
   				 Thread.sleep(100);
			} 
			catch(InterruptedException ex) {Thread.currentThread().interrupt();}
		}
		
	}
}

FIM


sub interpretaArquivo
{
 
	my $arq = shift @_;
	my $prog = novo Programa;
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


