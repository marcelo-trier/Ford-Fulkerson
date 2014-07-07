package br.grafo.mst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.grafo.Aresta;
import br.grafo.Grafo;
import br.grafo.Vertice;

public class MST extends Grafo {
	ConjuntoArvores subArvores = null;
	VerticePool pool = null;
	ArrayList<Aresta> Q = new ArrayList<Aresta>(); // a lista q conterah as respostas

	public MST( Grafo g ) throws Exception {
		super( g );
//		init( g );
		pool = new VerticePool( g.getVertices().size() );
		subArvores = new ConjuntoArvores();
	}

	public void clear() {
		Q.clear();
		subArvores.clear();
		pool.clear();
	}

	public void reset( Grafo g ) throws Exception {
		this.clear();
		init( g );
	}
	
	public String toString() {
		String msg = "MST::\n" + super.toString();
		msg += "subArvores=" + subArvores.toString() + "\n";
		msg += "pool=" + pool.toString() + "\n";
		msg += "Q=" + Q + "\n";
		return msg;
	}

	public void makeSet() {
		
		subArvores.init( pool.getPool() );

		for( int i=0; i<getVertices().size(); i++ ) {
			List<Vertice> l = pool.get( i );
			l.clear();
			l.add( getVertices().get( i ) );
			//subArvores.add( l );
		}
		Q.clear();
	}
	
	public void runKruskal() throws Exception {
		makeSet();
		// ordenar pelas arestas com menor peso
		Collections.sort( arestas );

		for( Aresta a : arestas ) {
			List l1 = subArvores.findConjunto( a.v[0] );
			List l2 = subArvores.findConjunto( a.v[1] );
			if( l1 != l2 ) {
				Q.add( a );
				subArvores.union( l1, l2 );
			}
		}
		arestas.clear();
		arestas.addAll( Q ); // ao final, teremos um grafo com menor caminho
		buildAdjacentes();
	}
}
