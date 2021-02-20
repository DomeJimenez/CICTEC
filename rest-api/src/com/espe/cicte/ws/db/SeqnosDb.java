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

import com.espe.cicte.ws.dto.BaseResponse;
import com.espe.cicte.ws.dto.Evaluacion;
import com.espe.cicte.ws.dto.EvaluacionResponse;
import com.espe.cicte.ws.dto.Instructor;
import com.espe.cicte.ws.dto.Message;
import com.espe.cicte.ws.dto.Piloto;
import com.espe.cicte.ws.dto.Plan;
import com.espe.cicte.ws.utils.Config;

public class SeqnosDb {

	private static Logger logger = org.apache.log4j.Logger.getLogger(SeqnosDb.class);

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
	
	public BaseResponse getIdSeqnos() {
		BaseResponse response = new BaseResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getEvaluacion");
		try {
			this.setConnection();
			String query = "SELECT se_id " + 
					" FROM cicte.cic_seqnos WHERE se_tabla = 'cic_evaluacion';";
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			int seqnos = 0;
			
			while (rs.next()) {
				seqnos = rs.getInt("se_id");
			}
			response.setCodigo(seqnos + 1);

			if (seqnos != 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("010");
				messageResponse.setMessage("Error al consultar el secuencial");
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
	
	public BaseResponse updateSeqnos(int seqnos) {
		BaseResponse response = new BaseResponse();
		Message messageResponse = new Message();
		
		
		logger.info("Iniciando metodo updateSeqnos");
		try {
			if(seqnos != 0) {
			this.setConnection();
			String query = "UPDATE cicte.cic_seqnos \n" +
					       "SET se_id = "+ seqnos +" \n"+
					       "WHERE se_tabla = 'cic_evaluacion'";
			PreparedStatement value = conn.prepareStatement(query);
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
			}else { 
				messageResponse.setCode("212");
				messageResponse.setMessage("Error en actualizar el secuencial");
				response.setMessage(messageResponse);
				response.setSuccess(false);
			}
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("211");
			messageResponse.setMessage("Error en actualizar el secuencial");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}
}
