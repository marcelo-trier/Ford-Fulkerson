package br.grafo;

import java.awt.Color;


public class VerticeInfo implements Comparable< VerticeInfo >{
	public float du = 0f;		// shortest path-estimate	Cormen, pg. 608 - cap. relaxation
	public float fu = 0f;
	public Vertice pi = null; // indice do vertice anterior
	public Color color;
	
	boolean visited = false;
	
	public void clear() {
		color = Color.WHITE;
		pi = null;
		du = 0f;
		fu = 0f;
	}
	
	public String toString() {
//		String msg = "[du:"+du+",pi:";
//		msg += ( pi == null ) ? "null" : pi.label;
//		msg += "]";
//		return msg;
		return "";
	}
	
	public boolean equals( VerticeInfo vi ) {
		if( du == vi.du && pi == vi.pi )
			return true;
		return false;
	}
	
	@Override
	public int compareTo(VerticeInfo o) {
		if( du == o.du )
			return 0;
		else if( du < o.du ) 
			return -1;
		else
			return 1;
	}

}
