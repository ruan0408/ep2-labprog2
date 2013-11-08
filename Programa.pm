#! /usr/bin/perl -w
package Programa;
use Comando;

#Construtor do objeto programa.
#Todo programa tem como atributos um vetor de comandos, um hash (label => linha programa),
#e um contador de linhas (posi).
sub novo 
{
	my $class = shift;
	my $self =
	{
		label => {},
		vetor =>  [],
		posi => 0,
	};
	bless $self, $class;
	return $self;
}

#Recebe uma linha e retornar um Programa.
sub interpretaLinha
{
	#Pega argumentos 
	my $self = shift;
	my $linha = shift;

	#Retira comentarios do codigo
	$linha =~ s/#.*//;	

	# Verifica o formato da sintaxe
	if( $linha !~ /\s*([a-zA-Z0-9]+\:)? \s* ( (([a-zA-Z]+)\s+([a-zA-Z0-9]+)) |([a-zA-Z]+) )?/x)
	{
		print "Syntax error\n";
	}
	else
	{
		if($1)
		{
			my $rotulo = $1;
			chop ($rotulo); #tira os : do final.

			#Cria nova entrada no hash de labels caso o label ainda nao exista
			if(!$self->existeLabel($rotulo))
			{
				$self->novoLabel($rotulo);
			}
			else
			{
				print "Redefinição da label: $rotulo\n";
				return 0;
			} 
		}
		if($3)
		{
			#Insere novo comando (codigo, argumento)
			my $cmd = novo Comando($4, $5);
			if (defined $cmd){
				$self->insereComando($cmd);
			}
			else{
				return 0;
			} 
		} 
		
		if($6)
		{
			#Insere novo comando (codigo, undef)
			my $cmd = novo Comando($6, undef);
			$self->insereComando($cmd); 
		}
	}
	return $self; #Recomendado.
}

#Insere um comando no vetor de comandos de uma instancia de Programa
sub insereComando
{
	$self = shift;
	$self->{posi}++;
	$vetor = $self->{vetor};
	push(@$vetor,shift);

	return $self;
}

#Verifica a existencia de uma chave no hash de labels
sub existeLabel
{
	(my $self,my $label) = (@_);

	my $labelsHash = $self->{label};

	return exists $labelsHash->{($label)};
}

#Cria uma nova chave no hash de labels e atribui o numero da linha respectiva
sub novoLabel
{
	(my $self,my $label) = (@_);
	my $labelsHash = $self->{label};

	$labelsHash->{$label} = $self->{posi};

	return $self;
}

#Retornar o tamanho do vetor de comandos
sub getTam
{
	my $self = shift;
	my $vetor =	$self->{vetor};
	return scalar @$vetor;
}

#Retorna o comando do indice "$ind" do vetor de comandos
sub getCmd
{
	(my $prog, my $ind) = (@_);
	return $prog->{vetor}->[$ind];

}

#Retornar a linha de ocorrencia de keyPossible, se essa existir
sub getLabel
{
	(my $self, my $keyPossible) = (@_);
	my $labelsHash = $self->{label};
	if(exists $labelsHash->{$keyPossible})
	{
		return $labelsHash->{$keyPossible};
	}
	return undef; 
}

sub printVetorJava
{
	my $prog = shift;
	my $ARQ  = shift;
	my $cmd;
	my $b = 0;
	print $ARQ qq|programa = new Programa();\n|;
	for(my $i = 0; $i < $prog->getTam(); $i++)
	{
		
		$cmd = $prog->getCmd($i);
		if($cmd->getCode() eq 'JMP' || $cmd->getCode() eq'JIF' || $cmd->getCode() eq'JIT')
		{
			$a = $cmd->getCode();
			print $ARQ qq|programa.add( new Comando("$a", new Numero( $prog->{label}->{$cmd->getValor()} )));\n|;
			#print $cmd->getCode();
		}
		elsif($cmd->getCode() eq'PUSH' || $cmd->getCode() eq'STO' || $cmd->getCode() eq'RCL')
			#$cmd->getCode() eq'WALK' ||$cmd->getCode() eq'ATK'||$cmd->getCode() eq'DROP'||$cmd->getCode() eq'COLLECT')
		{
			$a = $cmd->getCode();
			$b = $cmd->getValor();
			
			if($b =~ /^(\d+)$/) #é número
			{
				print $ARQ qq|programa.add( new Comando("$a", new Numero($b) ));\n|;	
			}
			elsif ($b =~ /[^(\s+)]/) 
			{
				print $ARQ qq|programa.add( new Comando("$a", new Frase("$b") ));\n|;		
			}
			#else #é string
			#{
			#	print $ARQ qq|programa.add( new Comando("$a", new Frase("$b") ));\n|;		
			#}
			
		}
		else
		{
			$a = $cmd->getCode();
			print $ARQ qq|programa.add( new Comando("$a", null));\n|;	
		}
	}
}
1;
