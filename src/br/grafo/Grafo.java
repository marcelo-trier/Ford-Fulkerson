package br.grafo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Grafo {
	private String nomes;
	protected boolean directed = true; // este grafo eh direcionado??
	public int vi = 0;
	public int vf = 0;
	
	protected List<Vertice> vertices = new ArrayList<Vertice>();
	protected List<Aresta> arestas = new ArrayList<Aresta>();

	public Grafo() {
		
	}
	
	public Grafo( Grafo g ) throws Exception {
		init( g );
	}
	
	public String toString() {
		String msg = "Printing G = { V, E }\n";
		msg += "V[" + vertices.size() + "] = " + vertices + "\n";
		msg += "E[" + arestas.size() + "] = " + arestas + "\n";
		return msg;
	}

	public List<Vertice> getVertices() {
		return vertices;
	}

	public List<Aresta> getArestas() {
		return arestas;
	}

	public void transpose() {
		for (Aresta a : arestas) {
			a.transpose();
		}
	}
	
	public void init( Grafo g ) throws Exception {
		init( g.getArestas(), g.getVertices() );
		nomes = g.nomes;
		directed = g.directed;
		
	}
	
	public void init(List<Aresta> la, List<Vertice> lv) throws Exception {
		if (la == null || lv == null)
			return;

		arestas.clear();
		vertices.clear();
		arestas.addAll( la );
		vertices.addAll( lv );
	}

	public Aresta getAresta(Vertice v1, Vertice v2, float capacidade ) {
		for( Aresta a : arestas ) {
			int id1 = a.verticeId( v1 );
			int id2 = a.verticeId( v2 );
			if( a.cuv == capacidade ) {
				if( directed ) {
					if( id1==0 && id2==1 )
						return a;
				} else {
					if( id1!=-1 && id2 !=-1 )
						return a;
				}
			}
		}
		return null;
	}

	public void addAresta(Vertice v1, Vertice v2, float w ) throws Exception {
		if (getAresta(v1, v2, w) == null) {
			Aresta a = new Aresta( v1, v2, w );
			//a.setInfo(v1, v2, w);
			v1.addAdj(a);
			if (!directed)
				v2.addAdj(a);
			arestas.add(a);
		}
	}

	public void buildAdjacentes() throws Exception {
		for( Vertice v : vertices ) {
			v.adjacentes.clear();
		}
		
		for( Aresta a : arestas ) {
			Vertice u = a.v[0];
			Vertice v = a.v[1];
			u.addAdj( a );
			if( !directed )
				v.addAdj( a );
		}
	}

	public void criaVertices() throws Exception {
		vertices.clear();
		String label;
		for( int i=0; i<nomes.length(); i++ ) {
			label = "" + nomes.charAt( i );
			Vertice v = new Vertice( i, label );
			vertices.add( v );
		}
	}

	public boolean isSpecialChar( char c ) {
		return ( "#@*&".indexOf( c ) >= 0 );
	}

	public void executeSpecialChar( String line ) throws Exception {
		String tmp;
		switch( line.charAt( 0 ) ) {
		case '#':
			return;
		case '@':
			nomes = line.substring( 1 );
			if (vertices.size() <= 0)
				criaVertices();
			return; 
		case '*':
			tmp = line.substring( 1 );
			//setVerticeInicial( Integer.parseInt( tmp ) );
			vi = Integer.parseInt( tmp );
			return;
		case '&':
			tmp = line.substring( 1 );
			//setVerticeFinal( Integer.parseInt( tmp ) );
			vf = Integer.parseInt( tmp );
			return;
		}
	}
	
	public void loadFromFile(File f) throws Exception {
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String linha;
		int numeroLinha = -1;
		String tmp;
		while ((linha = br.readLine()) != null) {
			linha = linha.trim();
			if( linha.equals( "" ) )
				continue;
			
			if( isSpecialChar( linha.charAt( 0 ) ) ) {
				executeSpecialChar( linha );
				continue;
			}

			numeroLinha++;
			String[] tokens = linha.split("\\s+"); // pega qualquer coisa:
													// espaço, tab, quebra de
													// linha, etc..

			for (int numeroColuna = 0; numeroColuna < tokens.length; numeroColuna++) {
				float valor = Float.parseFloat(tokens[numeroColuna]);
				if ( valor > 0 ) {
					Vertice v1, v2;
					v1 = vertices.get(numeroLinha);
					v2 = vertices.get(numeroColuna);
					addAresta( v1, v2, valor );
				}
			}
		}

		br.close();
		//buildAdjacentes(); <<--- não precisa mais, pq ao adicionar a aresta, jah adiciona na lista de adjacentes
	}
	
	
}
