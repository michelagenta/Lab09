package it.polito.tdp.borders.model;

import java.util.List;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;


public class Model {
	
	private BordersDAO dao; 
	private List<Country> countries; 
	private CountryIdMap map; 
	private SimpleGraph<Country, DefaultEdge> graph; 

	public Model() {
		
		dao = new BordersDAO();

	}
	
	public void creaGrafo(int anno) {
		map =  new CountryIdMap();
		countries= dao.loadAllCountries(map);
		
		List<Border> confini= dao.getCountryPairs(map, anno);
		
		if(confini.isEmpty()) {
			throw new RuntimeException("No country"); 
		}
		
		graph= new SimpleGraph<>(DefaultEdge.class); 
		for(Border b: confini) {
			graph.addVertex(b.getC1()); 
			graph.addVertex(b.getC2());
			graph.addEdge(b.getC1(), b.getC2()); 
			
			//System.out.println("%d vertici,  %d archi\n ", graph.vertexSet().size(), graph.edgeSet().size());
			
			
			
			
			
			
		}
		
	}

}
