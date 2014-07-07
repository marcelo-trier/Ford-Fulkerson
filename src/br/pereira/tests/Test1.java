package br.pereira.tests;

import java.io.File;

import br.grafo.Grafo;
import br.grafo.ford_fulkerson.CaminhoP;
import br.grafo.ford_fulkerson.FordFulkerson;
import br.pereira.util.GerenteArquivos;

public class Test1 {

	public static void main(String[] args) throws Exception {
		System.out.println( "RUNNING: " + Test1.class.getName() + "\n\n" );

		File f = GerenteArquivos.getInstance().getOpenFile();
		Grafo g = new Grafo();
		g.loadFromFile( f );
		System.out.println( g );
		
		FordFulkerson ff = new FordFulkerson( g );
		ff.execute();

		System.out.println( ff.lp );
		System.out.println( ff );
	}
}

/*
ff.initialize();
CaminhoP p = new CaminhoP();
if( ff.visite( ff.inicio, p ) ) {
	p.updateMaxFlow( ff.inicio );
	System.out.println( p );
}
p = new CaminhoP();
ff.initialize();
if( ff.visite( ff.inicio, p ) ) {
	p.updateMaxFlow( ff.inicio );
	System.out.println( p );
}
*/
