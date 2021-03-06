package br.grafo.ford_fulkerson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.grafo.Aresta;
import br.grafo.Vertice;

public class CaminhoP {
	static int contadorCaminhos = 1;
	
	int id = 0;
	float fluxoMaximo = 999;
	List<Aresta> caminho = new ArrayList<Aresta>();

	public CaminhoP() {
		id = contadorCaminhos++;
	}
	
	public String toString() {
		String msg;
		msg = "\nP" + id + " [FM:" + fluxoMaximo + "]: " + caminho;
		return msg;
	}
	
	public void add( Aresta a, Vertice v ) {
		caminho.add(a);
		if( v.equals( a.v[1] ) ) {
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
