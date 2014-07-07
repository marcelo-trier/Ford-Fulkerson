package br.grafo.mst;

import java.util.ArrayList;
import java.util.List;

import br.grafo.Vertice;

public class VerticePool {

	List<Vertice>[] poolList = null;

	public List<Vertice>[] getPool() {
		return poolList;
	}
	
	public String toString() {
		String msg = "[";
		for( List<Vertice> lv : poolList ) {
			msg += lv.toString() + ", ";
		}
		msg = msg.substring( 0, msg.length() - 2 ) + "]";
		return msg;
	}
	
	public List<Vertice> get( int i ) {
		return poolList[ i ];
	}
	
	public VerticePool( int size ) {
		poolList = new List[ size ];
		for( int i=0; i<poolList.length; i++ ) {
			poolList[ i ] = new ArrayList< Vertice >();
		}
	}
	
	public int size() {
		return poolList.length;
	}
	
	public List<Vertice> getEmptyItem() {
		for( List<Vertice> lv : poolList ) {
			if( lv.isEmpty() ) {
				return lv;
			}
		}
		return null;
	}
	
	public void clear() {
		for( List<Vertice> lv : poolList ) {
			lv.clear();
		}
	}
	
	public void init( Vertice[] osV ) {
		for( int i=0; i<osV.length; i++ ) {
			poolList[i].add( osV[i] );
		}
	}
	
}
