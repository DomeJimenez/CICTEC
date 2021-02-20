package com.espe.cicte.ws.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.espe.cicte.ws.dto.FingerRequest;
import com.espe.cicte.ws.dto.FingerResponse;
import com.espe.cicte.ws.dto.Message;
import com.espe.cicte.ws.utils.Config;

public class BiometricDB {

	private static Logger logger = org.apache.log4j.Logger.getLogger(BiometricDB.class);

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

	public FingerResponse getFingerById(String id_mil) {
		FingerResponse response = new FingerResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getFingerById");

		try {
			this.setConnection();
			// STATES: W=WAITING; R=RUNNING; D=DONE
			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of using "*"
			String query = "SELECT bi_mano, bi_dedo, bi_id_mil, bi_minuta1, bi_minuta2 FROM cic_biometric WHERE bi_id_mil = '"+ id_mil + "'";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			
				byte[] bi_minuta1 = null;
				byte[] bi_minuta2 = null;
			// iterate through the java resultset
				while (rs.next()) {					
					String bi_mano = rs.getString("bi_mano");
					String bi_dedo = rs.getString("bi_dedo");
					String bi_id_mil = rs.getString("bi_id_mil");
					bi_minuta1 = rs.getBytes("bi_minuta1");
					bi_minuta2 = rs.getBytes("bi_minuta2");
					response = new FingerResponse(bi_mano, bi_dedo, bi_id_mil, bi_minuta1, bi_minuta2);
				}
				
				
			if(null != response.getId_mil())
				response.setSuccess(true);
			else {
				messageResponse.setCode("003");
				messageResponse.setMessage("El registro no existe");
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

	public boolean addFingerById(FingerRequest request) {
		boolean result = false;

		logger.info("Iniciando metodo addFingerById");

		try {
			this.setConnection();
			// STATES: W=WAITING; R=RUNNING; D=DONE
			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of using "*"
			String query = "INSERT INTO cic_biometric\n" + "(bi_mano, bi_dedo, bi_id_mil, bi_minuta1, bi_minuta2)\n"
					+ "VALUES(?, ?, ?, ?, ?);";
			// create the java statement

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, request.getMano());
			preparedStmt.setString(2, request.getDedo());
			preparedStmt.setString(3, request.getId());
			preparedStmt.setBytes(4, request.getMin1());
			preparedStmt.setBytes(5, request.getMin2());

			int rowCount = preparedStmt.executeUpdate();
			if (rowCount == 1)
				result = true;
			else
				result = false;
			conn.close();
			return result;
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			return false;
		}

	}
	
	public boolean updateFingerById(FingerRequest request) {
		boolean result = false;

		logger.info("Iniciando metodo updateFingerById");
		Message messageResponse = new Message();

		try {
			this.setConnection();
			// STATES: W=WAITING; R=RUNNING; D=DONE
			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of using "*"
			String query = "UPDATE cicte.cic_biometric \n" + 
					"SET bi_mano = ?, bi_dedo = ?,  bi_minuta1 = ?, bi_minuta2 = ? \n" + 
					"WHERE bi_id_mil = ?";
			// create the java statement

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, request.getMano());
			preparedStmt.setString(2, request.getDedo());
			preparedStmt.setBytes(3, request.getMin1());
			preparedStmt.setBytes(4, request.getMin2());
			preparedStmt.setString(5, request.getId());
			preparedStmt.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("011");
			messageResponse.setMessage("Error en actualizar el Instructor");
			return false;
		}

	}
	
	public FingerResponse getFingerPosition(FingerRequest request) {
		FingerResponse response = new FingerResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getFingerPosition");

		try {
			this.setConnection();
			String query = "SELECT bi_mano, bi_dedo, bi_id_mil, bi_minuta1, bi_minuta2 FROM cic_biometric WHERE bi_id_mil = '"+ request.getId() + "'";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			
				byte[] bi_minuta1 = null;
				byte[] bi_minuta2 = null;
			// iterate through the java resultset
				while (rs.next()) {					
					String bi_mano = rs.getString("bi_mano");
					String bi_dedo = rs.getString("bi_dedo");
					String bi_id_mil = rs.getString("bi_id_mil");
					bi_minuta1 = rs.getBytes("bi_minuta1");
					bi_minuta2 = rs.getBytes("bi_minuta2");
					response = new FingerResponse(bi_mano, bi_dedo, bi_id_mil, bi_minuta1, bi_minuta2);
				}
				
				
			if(null != response.getId_mil())
				response.setSuccess(true);
			else {
				messageResponse.setCode("034");
				messageResponse.setMessage("El piloto no tiene registro biometrico");
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
