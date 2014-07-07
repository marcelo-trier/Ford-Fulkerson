package br.grafo.ford_fulkerson;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.grafo.Aresta;
import br.grafo.Grafo;
import br.grafo.Vertice;

public class FordFulkerson extends Grafo {
//	protected int vi = 0;
//	protected int vf = 0;
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

/*	
	public boolean findPath( Aresta umaA ) {
		if( this.equals( umaA.v[0] ) && ) {
			umP.add( t );
		}
		info.color = Color.GRAY;
		List<Aresta> la = getAdjacentes();
		for( Aresta a : getAdjacentes() ) {
			Vertice v = a.v[0];
			if( v.equals( this ) ) {
				
			}
			
		}
		
		return false;
		
	}
*/

	/*
	u.info.color = Color.GRAY;
	u.info.du = ++time;
	for( Aresta a : u.getAdjacentes() ) {
		Vertice v = null;

		v = a.v[0];
		if( v.equals( u ) ) {				
			v = a.v[1];
			if( directed ) {
				a.reverse = true;
			}
		}

		if( v.info.color != Color.WHITE )
			continue;
		v.info.pi = u;
		visite( v );
	}
	u.info.color = Color.BLACK;
	u.info.fu = ++time; */	
	
	public boolean visite( Vertice u, CaminhoP umP ) {

		if( u.equals( fim ) )
			return true;

		u.info.color = Color.GRAY;
		List<Aresta> la = u.getAdjacentes();
		Collections.sort( la );
		Collections.reverse( la ); // ordena do maior para o menor... ;o)
		boolean reverse = false;
		for( Aresta a : la ) {  // pega cada um dos adjacentess..
			Vertice v = a.v[1];
			reverse = false;
			
			if( v.equals( u ) ) { // precisa verificar se eh uma aresta direta ou reversa
				v = a.v[0];
				if( directed ) {
					reverse = true;
				}
			}
			
			// caso o vertice jah esteja visitado, vai para o próximo
			if( v.info.color != Color.WHITE )
				continue;

			if( a.cuv <= a.fuv ) {
				// caso a capacidade da aresta for menor/igual ao fluxo, não faz nada...
				continue;
			}

			v.info.pi = u;
			v.info.color = Color.GRAY;
			if( visite( v, umP ) ) {
				umP.add( a, reverse );
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

	public boolean isSpecialChar(char c) {
		return super.isSpecialChar(c) || "*&".indexOf( c ) >= 0;
	}

	public void executeSpecialChar(String line) throws Exception {
		super.executeSpecialChar( line );
		switch( line.charAt( 0 ) ) {
		}
	}
}
