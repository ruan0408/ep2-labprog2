import java.util.Vector;

// Símbolo: pode ser nome de variável ou função
class Símbolo {
	Endereco pos;				// posição na memória: Endereco
	Símbolo(int n) {
		pos = new Endereco(n);
	}
	void SetPos(int n) {pos = new Endereco(n);}
}

// Função
class Função extends Símbolo {
	Vector<String> args = new Vector<String>(); // lista dos argumentos
	TabSim Vars = new TabSim();					// nomes dos argumentos

	Função(int n) {
		super(n);
	}

	// inclui um argumento
	void addarg(String a) {
		Variável v = new Variável();
		args.add(a);
		Vars.add(a,v);
	}

	// pega o nome do argumento na posição n
	String getarg(int n) {
		return args.get(n);
	}

	// retorna a variável de nome a
	Variável get(String a) {
		return (Variável) Vars.get(a);
	}

	// verifica a existência
	Boolean exists(String a) {
		return  Vars.exists(a);
	}
}

// Variável
class Variável extends Símbolo {
	static int nvars=0;			// número de variáveis globais

	Variável() {super(nvars++);}
}
