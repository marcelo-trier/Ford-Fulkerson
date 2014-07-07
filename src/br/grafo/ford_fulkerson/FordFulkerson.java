package br.grafo.ford_fulkerson;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.grafo.Aresta;
import br.grafo.Grafo;
import br.grafo.Vertice;

public class FordFulkerson extends Grafo {
	public Vertice inicio = null;
	protected Vertice fim = null;
	public List<CaminhoP> lp = new ArrayList<CaminhoP>();	
	
	public FordFulkerson( Grafo g ) throws Exception {
		super( g );
		boolean tmp = directed;
		directed = false; // diz q o grafo é nao direcionado para construir os adjacentes fuv e fvu =)
		buildAdjacentes(); // constroi os adjacentes em grafo nao direcionado
		directed = tmp; // hahah.... volta a ser direcionado de novo... =)  simples, não? ;o)
		setPath( g.vi, g.vf );
	}

	public void setVerticeInicial( int id ) {
		for( Vertice v : vertices ) {
			if( v.getId() == id ) {
				inicio = v;
				return;
			}
		}
	}
	
	public void setVerticeFinal( int id ) {
		for( Vertice v : vertices ) {
			if( v.getId() == id ) {
				fim = v;
				return;
			}
		}
	}
	
	public void initialize() {
		for( Aresta a : arestas ) {
			a.clear();
		}

		for( Vertice v : vertices ) {
			v.clear();
		}
	}
	
	public void setPath( int s, int t ) {
		setVerticeInicial( s );
		setVerticeFinal( t );
	}
	
	public boolean visite( Vertice u, CaminhoP umP ) {

		if( u.equals( fim ) )
			return true;

		u.visited = true;
		List<Aresta> la = u.getAdjacentes();
		Collections.sort( la );
		Collections.reverse( la ); // ordena do maior para o menor... ;o)
		for( Aresta a : la ) {  // pega cada um dos adjacentess..

			Vertice v = a.v[1];
			if( v.equals( u ) ) { // precisa verificar se eh uma aresta direta ou reversa
				v = a.v[0];
			}
			
			// caso o vertice jah esteja visitado, vai para o próximo
			// caso a capacidade da aresta for menor/igual ao fluxo, não faz nada...
			if( v.visited || a.cuv <= a.fuv )
				continue;

			if( visite( v, umP ) ) {
				umP.add( a, v );
				return true;
			}
		}
		
		return false;
	}
	
	
	// fazer uma lista de caminhos... 
	// cada caminho tem uma lista de arestassss =0)
	public void execute() {
		lp.clear();
		CaminhoP p;
		p = new CaminhoP();
		initialize();
		while( visite( inicio, p ) ) {
			p.updateMaxFlow( inicio );
			lp.add( p );
			p = new CaminhoP();
			initialize();
		}
	}

}
