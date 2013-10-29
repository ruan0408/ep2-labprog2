/*Classe disponibilizada pelo Gubi no paca*/

import java.util.*;

// Esta classe implementa um iterador aleatório sobre um Vector de Strings
// A ideia é gerar um vetor com os índices embaralhados
public class RndIterator<T> 
{
	private int[] indices;		// conterá os índices embaralhados
	private int pos = 0;		// percorrerá indices
	private List<T> base;	// Vector na ordem original
	
	RndIterator(List<T> n) 
	{
		base = n;
		reset();				// reconstroi uma ordem aleatória dos índices
	}
	
	public boolean hasNext() 
	{
		return pos < indices.length; // se chegou no final, acabou
	}

	public T next() 
	{	
		// pega o próximo, se houver
		if (pos == indices.length) return null;
		return base.get(indices[pos++]);
	}
	
	public void reset() 
	{		
		// popula indices com valores aleatórios
		int s = base.size();	// número de elementos
		indices = new int[s];	// cria o vetor
		for (int i = 0; i < s; i++) {indices[i] = i;} // inicializa na ordem
		for (int k = 0; k < s/5; k++)				  // embaralha bastante
			for (int i = 0; i < s; i++) 
			{
				int j = (int)(s*Math.random());// sorteia uma posição de troca
				// troca o elemento atual por algum outro do vetor
				int x = indices[i];
				indices[i] = indices[j];
				indices[j] = x;
			}
		pos = 0;	// reinicializa o ponteiro
	}
}
