package com.espe.cicte.ws.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.espe.cicte.ws.dto.Message;
import com.espe.cicte.ws.dto.Simulador;
import com.espe.cicte.ws.dto.SimuladorRequest;
import com.espe.cicte.ws.dto.SimuladorResponse;
import com.espe.cicte.ws.utils.Config;
import java.sql.PreparedStatement;

public class SimuladorDB {

	private static Logger logger = org.apache.log4j.Logger.getLogger(SimuladorDB.class);

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

	public SimuladorResponse addSimulador(SimuladorRequest request) {
		SimuladorResponse response = new SimuladorResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo addSimulador");
		try {
			this.setConnection();
			String query = "INSERT INTO cicte.cic_simulador \n" +
					"(si_nombre, si_ubicacion, si_codigo)\n" +
					"VALUES(?, ?, ?);";
			PreparedStatement value = conn.prepareStatement(query);
			value.setString(1, request.getNombre());
			value.setString(2, request.getUbicacion());
			value.setString(3, request.getCodigo());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("012");
			messageResponse.setMessage("Error al insertar el simulador");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	public SimuladorResponse getSimulador() {
		SimuladorResponse response = new SimuladorResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getSimulador");
		try {
			this.setConnection();
			List<Simulador> simuladorList = new ArrayList<Simulador>();
			String query = "SELECT si_nombre, si_ubicacion, si_codigo FROM cicte.cic_simulador WHERE si_codigo is not null";
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				Simulador simulador = new Simulador();
				simulador.setNombre(rs.getString("si_nombre"));
				simulador.setUbicacion(rs.getString("si_ubicacion"));
				simulador.setCodigo(rs.getString("si_codigo"));
				simuladorList.add(simulador);
			}
			response.setSimulador((Simulador[]) simuladorList.toArray(new Simulador[simuladorList.size()]));

			if (null != response.getSimulador() && response.getSimulador().length > 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("010");
				messageResponse.setMessage("No se encuetran los simuladores");
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

	public SimuladorResponse updateSimulador(SimuladorRequest request) {
		SimuladorResponse response = new SimuladorResponse();
		Message messageResponse = new Message();
		
		logger.info("Iniciando metodo updateSimulador");
		try {
			this.setConnection();
			String query = "UPDATE cicte.cic_simulador \n" +
					       "SET si_nombre = ?, si_ubicacion = ? \n"+
					       "WHERE si_codigo = ?";
			PreparedStatement value = conn.prepareStatement(query);
			value.setString(1, request.getNombre());
			value.setString(2, request.getUbicacion());
			value.setString(3, request.getCodigo());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("011");
			messageResponse.setMessage("Error en actualizar el simulador");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	public SimuladorResponse getSimuladorByName(SimuladorRequest request) {
		SimuladorResponse response = new SimuladorResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getSimuladorByName");
		try {
			this.setConnection();
			List<Simulador> simuladorList = new ArrayList<Simulador>();
			String query = "SELECT si_nombre, si_ubicacion, si_codigo FROM cicte.cic_simulador \n"+
						   "WHERE si_codigo like '%"+request.getNombre()+"%'";
			// create the java statement
			Statement st = conn.createStatement(); 
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				Simulador simulador = new Simulador();
				simulador.setNombre(rs.getString("si_nombre"));
				simulador.setUbicacion(rs.getString("si_ubicacion"));
				simulador.setCodigo(rs.getString("si_codigo"));
				simuladorList.add(simulador);
			}
			response.setSimulador((Simulador[]) simuladorList.toArray(new Simulador[simuladorList.size()]));

			if (null != response.getSimulador() && response.getSimulador().length > 0)
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

	public SimuladorResponse getSimuladorByCode(SimuladorRequest request) {
		SimuladorResponse response = new SimuladorResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getSimuladorByCode");
		try {
			this.setConnection();
			List<Simulador> simuladorList = new ArrayList<Simulador>();
			String query = "SELECT si_nombre, si_ubicacion, si_codigo FROM cicte.cic_simulador \n"+
					   "WHERE si_codigo like '%"+request.getCodigo()+"%'";
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				Simulador simulador = new Simulador();
				simulador.setNombre(rs.getString("si_nombre"));
				simulador.setUbicacion(rs.getString("si_ubicacion"));
				simulador.setCodigo(rs.getString("si_codigo"));
				simuladorList.add(simulador);
			}
			response.setSimulador((Simulador[]) simuladorList.toArray(new Simulador[simuladorList.size()]));

			if (null != response.getSimulador() && response.getSimulador().length > 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("015");
				messageResponse.setMessage("No se encuetran los simuladores");
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

	public SimuladorResponse deleteSimulador(SimuladorRequest request) {
		SimuladorResponse response = new SimuladorResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo deleteSimulador");
		try {
			this.setConnection();
			String query = "DELETE FROM cicte.cic_simulador \n" +					     
					       "WHERE si_codigo = ?";
			PreparedStatement value = conn.prepareStatement(query);
			value.setString(1, request.getCodigo());
			value.execute();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("013");
			messageResponse.setMessage("Error en eliminar el simulador");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

}
