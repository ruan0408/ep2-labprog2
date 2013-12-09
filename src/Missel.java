import java.util.*;

public class Missel implements Programavel
{
	Arena arena;
	Posicao pos;
	Maquina vm;

	public Missel(Direcao dir, Arena arena, Posicao pos)
	{
		this.arena = arena;
		Vector<Comando> progTemp = new Vector<Comando>();
		
		progTemp.add(new Comando("PUSH",new Numero(dir.getValue())));
		progTemp.add(new Comando("WALK",null));
		progTemp.add(new Comando("JIF", new Numero(4)));
		progTemp.add(new Comando("GETROBO",new Numero(0)));
		progTemp.add(new Comando("DUP", null));
		progTemp.add(new Comando("ISA",new Numero(4)));
		progTemp.add(new Comando("JIF",new Numero(-6)));
		progTemp.add(new Comando("GETTIME", new Numero(0)));
		progTemp.add(new Comando("TIMEID",null));
		progTemp.add(new Comando("MYTIME", null));
		progTemp.add(new Comando("TIMEID",null));
		progTemp.add(new Comando("EQ",null));
		progTemp.add(new Comando("JIT", new Numero(-12)));
		progTemp.add(new Comando("EXPLD", null));
		this.vm = new Maquina(arena, this);
		vm.carregaPrograma(new Programa(progTemp));
		this.pos = pos;
	}


	public boolean executaAcao()
	{
		boolean resp = vm.executaProx();
		if(resp) this.explode();
		return resp;
	}

	public Posicao getPos()
	{
		return pos;
	}

	public void explode()
	{
		arena.causaDano(pos, 100);
	}

	public void move(Posicao pos)
	{
		this.pos = pos;
	}

	public boolean temAcao()
	{
		return vm.temProx();
	}

	public void push(Empilhavel emp)
	{
		this.vm.pushDados(emp);
	}

	public Empilhavel pop()
	{
		return this.vm.popDados();
	}
}