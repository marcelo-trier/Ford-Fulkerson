package br.grafo.mst;

import java.util.ArrayList;
import java.util.List;

import br.grafo.Vertice;

public class ConjuntoArvores {
	List<List<Vertice>> subArvores = new ArrayList<List<Vertice>>();
	
	public String toString() {
		return subArvores.toString();
	}
	
	public List findConjunto( Vertice v ) {
		for( List<Vertice> lv : subArvores ) {
			if( lv.contains( v ) )
				return lv;
		}
		return null;
	}
	
	public void add( List<Vertice> umaL ) {
		subArvores.add( umaL );
	}
	
	public List<Vertice> get( int i ) {
		return subArvores.get( i );
	}
	
	public void union( List l1, List l2 ) {
		l1.addAll( l2 );
		l2.clear();
		subArvores.remove( l2 );
	}
	
	void clear() {
		subArvores.clear();
	}
	
	void addAll( List<Vertice>[] pool ) {
		for( List<Vertice> l : pool ) {
			subArvores.add( l );
		}
	}
	
	public void init( List<Vertice>[] pool ) {
		clear();
		addAll( pool );
		for( List l : pool ) {
			l.clear();
		}
	}
}