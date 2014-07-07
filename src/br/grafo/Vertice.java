package br.grafo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import br.grafo.ford_fulkerson.CaminhoP;

public class Vertice implements Comparable<Vertice>{
	protected int id = 0;
	public String label = "";
	public VerticeInfo info = new VerticeInfo();
	List<Aresta> adjacentes = new ArrayList<Aresta>();
	
	public int getId() {
		return id;
	}
	
	public void clear() {
		info.clear();
	}
	
	public boolean equals( Vertice v ) {
		if( id==v.id && info.equals(v.info) && label.equals( v.label ) )
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
//		for( Aresta a : adjacentes ) {
//			msg += a.v[1].label + ",";
//		}
//		msg = msg.substring( 0, msg.length() - 1 );
//		msg += ")"+info;
		return msg;
	}

	@Override
	public int compareTo(Vertice v) {
		return info.compareTo( v.info );
	}
}
