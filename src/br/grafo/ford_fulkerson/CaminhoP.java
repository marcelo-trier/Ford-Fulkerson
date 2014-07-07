package br.grafo.ford_fulkerson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.grafo.Aresta;
import br.grafo.Vertice;

public class CaminhoP {
	float fluxoMaximo = 999;
	List<Aresta> caminho = new ArrayList<Aresta>();

	public String toString() {
		String msg;
		msg = "\nP[FM:" + fluxoMaximo + "]: " + caminho;
		return msg;
	}
	
	public void add(Aresta a, boolean rev) {
		caminho.add(a);
		if (rev) {
			
		} else {
			float capResult = a.cuv - a.fuv;
			if (capResult < fluxoMaximo) {
				fluxoMaximo = capResult;
			}
		}
	}
	
	public void updateMaxFlow( Vertice u ) {
		Collections.reverse( caminho );
		for( Aresta a : caminho ) {
			float valor = fluxoMaximo;
			if( a.v[1].equals( u ) ) {
				valor = -valor; // se for aresta reversa, diminui o valor..
			}
			a.fuv += valor;
			u = a.v[1]; // u aponta para o próximo!!
		}
	}

}
