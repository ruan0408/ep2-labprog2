import java.util.*;

/** Tabela de simbolos.*/
public class TabSim {
    HashMap<String,Símbolo> H;
	int pos=0;
	
    TabSim() {
		H = new HashMap<String,Símbolo>(128);
    }

	public void add(String s, Símbolo o) {
		H.put(s,o);
		if (o instanceof Variável)
			o.SetPos(pos++);
	}
	
    public Símbolo get(String s) {
		return (Símbolo) H.get(s);
    }
	
    public boolean exists(String s) {
		return H.containsKey(s);
    }
}


