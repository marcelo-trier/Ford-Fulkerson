package br.grafo;


public class Aresta implements Comparable<Aresta> {
	
	public Vertice v[] = { null, null };
//	public ArestaInfo info = new ArestaInfo();
//	public float peso = 0;
	public float fuv = 0;
//	public float tmp = 0;
	public float cuv = 0;
//	public boolean reverse = false; // esta aresta eh reversa??
	
	public Aresta( Vertice v1, Vertice v2, float w ) {
		setInfo( v1, v2, w );
	}

	public void clear() {
//		fuv = 0;
		//tmp = 0;
		//cuv = 0;
	}
	
	public int verticeId( Vertice umV ) {
		for( int i=0; i<v.length; i++ ) {
			if( umV == v[i] )
				return i;
		}
		return -1;
	}
	
	public String toString() {
		String msg;
//		msg = "[" + v[0].label + "--" + v[1].label + "]:"+peso;
		msg = v[0].label + "-|"+ cuv + "/" +fuv +"|-" + v[1].label;
		return msg;
	}
	
	public Aresta setInfo( Vertice v1, Vertice v2, float w ) {
		v[0] = v1;
		v[1] = v2;
		cuv = w;
		return this;
	}
	
	public void transpose() {
		Vertice tmp;
		tmp = v[0];
		v[0] = v[1];
		v[1] = tmp;
	}
	
	public int compareTo(Aresta a) {
		// ordenação pela diferença de CUV com FUV! Porque? 
		// Sei lá! foi o melhor jeito... talvez pensar mais sobre isso!
		float d1 = cuv - fuv;
		float d2 = a.cuv - a.fuv;
		if( d1 > d2 )
			return 1;
		else if( d1 == d2 )
			return 0;
		else
			return -1;
	}
}
