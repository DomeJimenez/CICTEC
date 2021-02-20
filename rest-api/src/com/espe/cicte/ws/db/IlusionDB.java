package com.espe.cicte.ws.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.espe.cicte.ws.dto.IlusionRequest;
import com.espe.cicte.ws.dto.IlusionResponse;
import com.espe.cicte.ws.dto.Message;
import com.espe.cicte.ws.dto.Ilusion;
import com.espe.cicte.ws.utils.Config;

public class IlusionDB {

	private static Logger logger = org.apache.log4j.Logger.getLogger(IlusionDB.class);

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
	
	public IlusionResponse addIlusion(IlusionRequest request) {
		IlusionResponse response = new IlusionResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo addIlusion");
		try {
			this.setConnection();
			String query = "INSERT INTO cicte.cic_ilusion \n" +
					"(il_nombre, il_tipo, il_simulador)\n" +
					"VALUES(?, ?, ?);";
			PreparedStatement value = conn.prepareStatement(query);
			value.setString(1, request.getNombre());
			value.setString(2, request.getTipo());
			value.setString(3, request.getSimulador().getCodigo());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("012");
			messageResponse.setMessage("Error al insertar el Ilusion");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	public IlusionResponse getIlusion() {
		IlusionResponse response = new IlusionResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getIlusion");
		try {
			this.setConnection();
			List<Ilusion> ilusionList = new ArrayList<Ilusion>();
			String query = "SELECT idcic_ilusion, il_nombre, il_tipo, il_simulador \r\n" + 
					"	 FROM cicte.cic_ilusion i";
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				Ilusion ilusion = new Ilusion();
				ilusion.setCodigo(rs.getInt("idcic_ilusion"));
				ilusion.setNombre(rs.getString("il_nombre"));
				ilusion.setTipo(rs.getString("il_tipo"));
				ilusion.setSimulador(rs.getString("il_simulador"));
				ilusionList.add(ilusion);
			}
			response.setIlusion((Ilusion[]) ilusionList.toArray(new Ilusion[ilusionList.size()]));

			if (null != response.getIlusion() && response.getIlusion().length > 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("010");
				messageResponse.setMessage("No se encuetran los Ilusiones");
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

	public IlusionResponse updateIlusion(IlusionRequest request) {
		IlusionResponse response = new IlusionResponse();
		Message messageResponse = new Message();
		
		logger.info("Iniciando metodo updateIlusion");
		try {
			this.setConnection();
			String query = "UPDATE cicte.cic_ilusion \n" +
					       "SET il_nombre = ?, il_ubicacion = ? \n"+
					       "WHERE idcic_ilusion = ?";
			PreparedStatement value = conn.prepareStatement(query);
			value.setString(1, request.getNombre());
			value.setString(2, request.getTipo());
			value.setInt(3, request.getCodigo());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("011");
			messageResponse.setMessage("Error en actualizar el Ilusion");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	public IlusionResponse getIlusionByName(IlusionRequest request) {
		IlusionResponse response = new IlusionResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getIlusionByName");
		try {
			this.setConnection();
			List<Ilusion> IlusionList = new ArrayList<Ilusion>();
			String query = "SELECT idcic_ilusion, il_nombre, il_tipo,\r\n" + 
					"	(select s.si_nombre from cicte.cic_simulador s  \r\n" + 
					"	 where i.il_simulador = s.si_codigo ) as il_si_nombre \r\n" + 
					"	 FROM cicte.cic_ilusion i \r\n"+
				    "	 WHERE il_nombre like '%"+request.getNombre()+"%'";
			// create the java statement
			Statement st = conn.createStatement(); 
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				Ilusion Ilusion = new Ilusion();
				Ilusion.setCodigo(rs.getInt("idcic_ilusion"));
				Ilusion.setNombre(rs.getString("il_nombre"));
				Ilusion.setTipo(rs.getString("il_tipo"));
				Ilusion.setSimulador(rs.getString("il_si_nombre"));
				IlusionList.add(Ilusion);
			}
			response.setIlusion((Ilusion[]) IlusionList.toArray(new Ilusion[IlusionList.size()]));

			if (null != response.getIlusion() && response.getIlusion().length > 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("014");
				messageResponse.setMessage("No se encontraron registros");
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

	public IlusionResponse getIlusionById(IlusionRequest request) {
		IlusionResponse response = new IlusionResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getIlusionByName");
		try {
			this.setConnection();
			List<Ilusion> IlusionList = new ArrayList<Ilusion>();
			String query = "SELECT idcic_ilusion, il_nombre, il_tipo,\r\n" + 
					"	(select s.si_nombre from cicte.cic_simulador s  \r\n" + 
					"	 where i.il_simulador = s.si_codigo ) as il_si_nombre \r\n" + 
					"	 FROM cicte.cic_ilusion i \r\n"+
				    "	 WHERE il_simulador = '"+request.getSimulador().getCodigo()+"'";
			// create the java statement
			Statement st = conn.createStatement(); 
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				Ilusion Ilusion = new Ilusion();
				Ilusion.setCodigo(rs.getInt("idcic_ilusion"));
				Ilusion.setNombre(rs.getString("il_nombre"));
				Ilusion.setTipo(rs.getString("il_tipo"));
				Ilusion.setSimulador(rs.getString("il_si_nombre"));
				IlusionList.add(Ilusion);
			}
			response.setIlusion((Ilusion[]) IlusionList.toArray(new Ilusion[IlusionList.size()]));

			if (null != response.getIlusion() && response.getIlusion().length > 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("014");
				messageResponse.setMessage("No se encontraron registros");
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
	

	public IlusionResponse deleteIlusion(IlusionRequest request) {
		IlusionResponse response = new IlusionResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo deleteIlusion");
		try {
			this.setConnection();
			String query = "DELETE FROM cicte.cic_ilusion \n" +					     
					       "WHERE idcic_ilusion = ?";
			PreparedStatement value = conn.prepareStatement(query);
			value.setInt(1, request.getCodigo());
			value.execute();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("013");
			messageResponse.setMessage("Error en eliminar el Ilusion");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}
}
