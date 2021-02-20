package com.espe.cicte.ws.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.espe.cicte.ws.dto.Catalogo;
import com.espe.cicte.ws.dto.CatalogoResponse;
import com.espe.cicte.ws.dto.Message;
import com.espe.cicte.ws.utils.Config;

public class CatalogoDB {

	private static Logger logger = org.apache.log4j.Logger.getLogger(CatalogoDB.class);

	private Connection conn;

	private void setConnection() {
		try {
			// create our mysql database connection			
			String myDriver = "com.mysql.jdbc.Driver";
			String myUrl = String.format("jdbc:mysql://%s:%s/%s?useSSL=false", Config.hostDB, Config.portDB,
					Config.nameDB);
			Class.forName(myDriver);

			conn = DriverManager.getConnection(myUrl, Config.userDB, Config.passwordDB);
		} catch (ClassNotFoundException ex) {
			logger.error("Exepcion al conectarce con la base de datos" + ex);
			logger.error("" + ex);
		} catch (SQLException ex) {
			logger.error("SQL ex: " + ex);
		}

	}

	

	public CatalogoResponse getCatalogoByTable(Catalogo request) {
		CatalogoResponse response = new CatalogoResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getCatalogoByName");
		try {
			this.setConnection();
			List<Catalogo> CatalogoList = new ArrayList<Catalogo>();
			String query = "SELECT c.valor, c.detalle  \n" + 
					"FROM cicte.cic_tabla t, cic_catalogo c \n" + 
					"WHERE t.ta_codigo = c.tabla \n" + 
					"AND t.ta_nombre = '"+request.getTabla()+"';";
			// create the java statement
			Statement st = conn.createStatement(); 
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				Catalogo Catalogo = new Catalogo();
				Catalogo.setValor(rs.getString("valor"));
				Catalogo.setDetalle(rs.getString("detalle"));
				CatalogoList.add(Catalogo);
			}
			response.setCatalogo((Catalogo[]) CatalogoList.toArray(new Catalogo[CatalogoList.size()]));

			if (null != response.getCatalogo() && response.getCatalogo().length > 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("804");
				messageResponse.setMessage("No se encontraron registros de catalogos");
				response.setMessage(messageResponse);
				response.setSuccess(false);
			}
			st.close();
			conn.close();
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
		}
		return response;
	}

}
