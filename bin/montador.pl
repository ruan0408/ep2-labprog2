#! /usr/bin/perl -w

# Próximos dois use's úteis para usar o diretório do arquivo desse código fonte como
# base para os paths usados
use FindBin qw($Bin);	
use lib "$Bin";

use Comando;
use Programa;

#use Maquina;
package main;
			
#Programa principal.
#Cada linha é interpretada e vira um comando, ao termino do while, temos um programa ($prog),
#que será executado pela maquina ($maq);

my $erro = 0;
my $mapa = shift(@ARGV);
$n = @ARGV;
open (SAIDA, ">", "$Bin/../src/Main.java") or die "Erro ao tentar iniciar o programa";
print SAIDA <<INTRO;
public class Main{
	public static void main(String args[]){
		Programa[] programas = new Programa[$n];
		Mapa mapa = new Mapa("$Bin/../data/map/$mapa");
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
		interpretaArquivo("$Bin/../test/$arq");
	}

	print SAIDA "arena.insereExercito(programas, $time);\n";
	$time = $proxTime;
}

print SAIDA <<FIM;

		int larguraJanela, alturaJanela;
		if(mapa.largura()%2 == 1) larguraJanela = ((mapa.largura()-1)/2)*90 + 60;
		else larguraJanela = mapa.largura()*90/2 + 15;
		alturaJanela = (int)((mapa.altura()+1)*Math.sqrt(3)*30);

		MapaVisual mv = new MapaVisual(mapa, alturaJanela, larguraJanela, 30);

		mv.abreJanela();


		while(arena.atualiza())
		{
			mv.atualiza();

			try 
			{
   				 Thread.sleep(500);
			} 
			catch(InterruptedException ex) {Thread.currentThread().interrupt();}
		}

			mv.gameOver();
		
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


