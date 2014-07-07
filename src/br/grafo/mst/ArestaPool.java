package br.grafo.mst;

import java.util.ArrayList;
import java.util.List;

import br.grafo.Aresta;

public class ArestaPool {
	List<Aresta> pool = new ArrayList<Aresta>();
	List<Aresta> emprestados = new ArrayList<Aresta>();
	
	protected ArestaPool( int size ) {
		for( int i=0; i<size; i++ ) {
			Aresta a = new Aresta( null, null, 0f );
			pool.add( a );
		}
	}

	protected static ArestaPool instance = new ArestaPool( 60 );

	public static ArestaPool getInstance() {
		return instance;
	}

	public Aresta getAresta() {
		if( pool.size() == 0 ) {
			return null;
		}
		Aresta a = pool.remove( 0 );
		emprestados.add( a );
		a.setInfo( null,  null,  0f );
		return a;
	}
	
	public void giveBack( Aresta a ) throws Exception {
		if( pool.contains( a ) ) {
			throw new Exception( "tentativa de devolver uma aresta que não foi emprestada??" );
		}
		if( emprestados.contains( a ) ) {
			emprestados.remove( a );
			pool.add( a );
			a.setInfo( null,  null,  0f );
		}
	}
	
	public void reset() {
		pool.addAll( emprestados );
		emprestados.clear();
	}
	
}
