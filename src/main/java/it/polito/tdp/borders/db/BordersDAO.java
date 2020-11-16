package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.CountryIdMap;

public class BordersDAO {

	public List<Country> loadAllCountries(CountryIdMap idMap) {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country c= new Country(rs.getInt("code"), rs.getString("StateAbb"), rs.getString("StateNme"));
				result.add(idMap.get(c));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(CountryIdMap idMap, int anno) {

		String sql="select state1no, state2no from contiguity where contiguity.conttype=1 and contiguity.year <= ?"; 
		
List<Border> result = new ArrayList<Border>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			
			st.setInt(1, anno );
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int c1code=rs.getInt("state1no");
				int c2code= rs.getInt("state2no");
						
				Country c1= idMap.get(c1code); 
				Country c2=idMap.get(c2code); 
				
				if(c1!= null && c2!= null) {
					result.add(new Border(c1,c2));
				}else {
					System.out.println("Errore");
				}
				Border b= new Border(c1,c2);
				result.add(b);
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
}
