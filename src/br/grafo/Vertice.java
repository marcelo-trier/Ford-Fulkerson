package br.grafo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import br.grafo.ford_fulkerson.CaminhoP;

public class Vertice {
	protected int id = 0;
	public String label = "";
	public boolean visited = false;
	List<Aresta> adjacentes = new ArrayList<Aresta>();
	
	public int getId() {
		return id;
	}
	
	public void clear() {
		visited = false;
	}
	
	public boolean equals( Vertice v ) {
		if( id==v.id && label.equals( v.label ) )
			return true;
		return false;
	}
	
	public List<Aresta> getAdjacentes() {
		return adjacentes;
	}
	
	public Vertice( int i, String l ) {
		id = i;
		label = l;
	}
	
	public void addAdj( Aresta umaA ) {
		if( umaA == null )
			return;
		if( !adjacentes.contains( umaA ) )
			adjacentes.add( umaA );
	}
	
	public String toString() {
		String msg = label;
		return msg;
	}
}
