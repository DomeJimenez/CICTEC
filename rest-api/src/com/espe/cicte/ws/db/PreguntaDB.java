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

import com.espe.cicte.ws.dto.Message;
import com.espe.cicte.ws.dto.Pregunta;
import com.espe.cicte.ws.dto.PreguntaRequest;
import com.espe.cicte.ws.dto.PreguntaResponse;
import com.espe.cicte.ws.utils.Config;

public class PreguntaDB {

	private static Logger logger = org.apache.log4j.Logger.getLogger(PreguntaDB.class);

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
	
	public PreguntaResponse addPregunta(Pregunta request) {
		PreguntaResponse response = new PreguntaResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo addPregunta");
		try {
			this.setConnection();
			String query = "INSERT INTO cicte.cic_pregunta \n" +
					"(pr_criterio, pr_simulador, pr_estado)\n" +
					"VALUES(?, ?, 'V');";
			PreparedStatement value = conn.prepareStatement(query);
			value.setString(1, request.getCriterio());
			value.setInt(2, request.getFormulario().getId());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("012");
			messageResponse.setMessage("Error al insertar el Pregunta");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	public PreguntaResponse updatePregunta(Pregunta request) {
		PreguntaResponse response = new PreguntaResponse();
		Message messageResponse = new Message();

		logger.info("Iniciando metodo updatePregunta");
		try {
			this.setConnection();
			String query = "UPDATE cicte.cic_pregunta \n" +
					       "SET pr_criterio = ? \n"+
					       "WHERE idcic_pregunta = ?";
			PreparedStatement value = conn.prepareStatement(query);
			value.setString(1, request.getCriterio());
			value.setInt(2, request.getCodigo());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("011");
			messageResponse.setMessage("Error en actualizar el Pregunta");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	public PreguntaResponse getPregunta(PreguntaRequest request) {
		PreguntaResponse response = new PreguntaResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getPregunta");
		try {
			this.setConnection();
			List<Pregunta> ilusionList = new ArrayList<Pregunta>();
			String query = "SELECT idcic_pregunta, pr_criterio, pr_formulario, pr_estado, \r\n" + 
					" (select s.fo_codigo from cicte.cic_formulario s\r\n" + 
					" where i.pr_formulario = s.idcic_formulario ) as pr_fo_nombre \r\n" + 
					" FROM cicte.cic_pregunta i WHERE pr_estado = 'V' ";
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				Pregunta pregunta = new Pregunta();
				pregunta.setCodigo(rs.getInt("idcic_pregunta"));
				pregunta.setCriterio(rs.getString("pr_criterio"));
				pregunta.setFormularioNombre(rs.getString("pr_fo_nombre"));
				pregunta.setCodigoFormulario(rs.getInt("idcic_pregunta"));
				pregunta.setEstado(rs.getString("pr_estado"));
				ilusionList.add(pregunta);
			}
			response.setPregunta((Pregunta[]) ilusionList.toArray(new Pregunta[ilusionList.size()]));

			if (null != response.getPregunta() && response.getPregunta().length > 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("010");
				messageResponse.setMessage("No se encuetran los Preguntas");
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

	public PreguntaResponse getPreguntaById(PreguntaRequest request) {
		PreguntaResponse response = new PreguntaResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getPreguntaByName");
		try {
			this.setConnection();
			List<Pregunta> PreguntaList = new ArrayList<Pregunta>();
			String query = "SELECT idcic_pregunta, pr_criterio, pr_formulario, pr_estado, \r\n" + 
					   "(select s.fo_codigo from cicte.cic_formulario s \r\n" + 
			           "where i.pr_formulario = s.idcic_formulario ) as pr_fo_nombre \r\n" +
				       "FROM cicte.cic_pregunta i WHERE pr_estado = 'V' AND idcic_pregunta = "+ request.getCodigo() +" ;";
			// create the java statement
			Statement st = conn.createStatement(); 
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				Pregunta pregunta = new Pregunta();
				pregunta.setCodigo(rs.getInt("idcic_pregunta"));
				pregunta.setCriterio(rs.getString("pr_criterio"));
				pregunta.setFormularioNombre(rs.getString("pr_formulario"));
				pregunta.setEstado(rs.getString("pr_estado"));
				PreguntaList.add(pregunta);
			}
			response.setPregunta((Pregunta[]) PreguntaList.toArray(new Pregunta[PreguntaList.size()]));

			if (null != response.getPregunta() && response.getPregunta().length > 0)
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
	

	public PreguntaResponse deletePregunta(PreguntaRequest request) {
		PreguntaResponse response = new PreguntaResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo deletePregunta");
		try {
			this.setConnection();
			String query = "DELETE FROM cicte.cic_pregunta \n" +					     
					       "WHERE idcic_pregunta = ?";
			PreparedStatement value = conn.prepareStatement(query);
			value.setInt(1, request.getCodigo());
			value.execute();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("013");
			messageResponse.setMessage("Error en eliminar el Pregunta");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}
}
