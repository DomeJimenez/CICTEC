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

import com.espe.cicte.ws.dto.Evaluacion;
import com.espe.cicte.ws.dto.EvaluacionRequest;
import com.espe.cicte.ws.dto.EvaluacionResponse;
import com.espe.cicte.ws.dto.Instructor;
import com.espe.cicte.ws.dto.Message;
import com.espe.cicte.ws.dto.Piloto;
import com.espe.cicte.ws.dto.Plan;
import com.espe.cicte.ws.dto.Pregunta;
import com.espe.cicte.ws.dto.PreguntaResponse;
import com.espe.cicte.ws.dto.Respuesta;
import com.espe.cicte.ws.dto.RespuestaResponse;
import com.espe.cicte.ws.utils.Config;

public class EvaluacionDB {

	private static Logger logger = org.apache.log4j.Logger.getLogger(EvaluacionDB.class);

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
	public EvaluacionResponse addEvaluacion(EvaluacionRequest request) {
		EvaluacionResponse response = new EvaluacionResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo addEvaluacion");
		SeqnosDb seqnosDB = new SeqnosDb();
		int seqnos = 0;
		try {
			this.setConnection();
			seqnos = seqnosDB.getIdSeqnos().getCodigo();
			String query = "INSERT INTO cicte.cic_evaluacion (idcic_evaluacion, ev_observacion, ev_piloto, \n" + 
					"		    ev_instructor, ev_plan, ev_funcion, ev_aeronave, ev_horas_vuelo, ev_grafico_xy) \n" + 
					"           VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement value = conn.prepareStatement(query);
			value.setInt(1, seqnos);
			value.setString(2, request.getObservacion());
			value.setString(3, request.getPiloto());
			value.setString(4, request.getInstructor());
			value.setInt(5, request.getPlan());
			value.setInt(6, request.getFuncion());
			value.setInt(7, request.getAeronave());
			value.setInt(8, request.getHoras_vuelo());
			value.setBytes(9, request.getGrafico());
			
			value.executeUpdate();
			conn.close();
			seqnosDB.updateSeqnos(seqnos);
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("012");
			messageResponse.setMessage("Error al insertar el Evaluacion");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	public EvaluacionResponse getEvaluacion() {
		EvaluacionResponse response = new EvaluacionResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getEvaluacion");
		try {
			this.setConnection();
			List<Evaluacion> EvaluacionList = new ArrayList<Evaluacion>();
			String query = "SELECT * " + 
					"	 FROM cicte.cic_evaluacion";
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				Evaluacion Evaluacion = new Evaluacion();
				Piloto Piloto = new Piloto();
				Instructor Instructor = new Instructor();
				Plan Plan = new Plan();
				
				Evaluacion.setCodigo(rs.getInt("idcic_evaluacion"));
				Evaluacion.setFecha(rs.getString("ev_fecha"));
				Evaluacion.setObservacion(rs.getString("ev_observacion"));
				Piloto.setId_mil(rs.getString("ev_piloto"));
				Plan.setCodigo(rs.getInt("ev_plan"));
				Evaluacion.setPiloto(Piloto);
				Instructor.setId_mil(rs.getString("ev_instructor"));
				Evaluacion.setInstructor(Instructor);
				Evaluacion.setPlan(Plan);
				Evaluacion.setFuncion(rs.getInt("ev_funcion"));
				Evaluacion.setAeronave(rs.getInt("ev_aeronave"));
				Evaluacion.setHorasVuelo(rs.getInt("ev_horas_vuelo"));
				Evaluacion.setXy(rs.getBlob("ev_grafico_xy"));
				EvaluacionList.add(Evaluacion);
			}
			response.setEvaluacion((Evaluacion[]) EvaluacionList.toArray(new Evaluacion[EvaluacionList.size()]));

			if (null != response.getEvaluacion() && response.getEvaluacion().length > 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("010");
				messageResponse.setMessage("No se encuetran los Evaluaciones");
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
	
	public EvaluacionResponse getEvaluacionMatriz(EvaluacionRequest request) {
		EvaluacionResponse response = new EvaluacionResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getEvaluacionMatriz");
		try {
			this.setConnection();
			List<Evaluacion> EvaluacionList = new ArrayList<Evaluacion>();
			String query = "SELECT fo.idcic_formulario, fo.fo_codigo, fo.fo_detalle \r\n" + 
					"FROM cicte.cic_formulario fo\r\n" + 
					"WHERE fo.fo_codigo =  '"+ request.getCodigo() +"' \r\n" + 
					"AND fo.fo_estado = 'V';" ;
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
					
			while (rs.next()) {
				Evaluacion Evaluacion = new Evaluacion();
				//Evaluacion.setId(rs.getInt("idcic_formulario"));
				//Evaluacion.setCodigo(rs.getString("fo_codigo"));
				//Evaluacion.setDetalle(rs.getString("fo_detalle"));
				//Evaluacion.setPreguntaList(this.getPreguntas(request.getCodigo(),rs.getInt("idcic_formulario")).getPregunta());	
				EvaluacionList.add(Evaluacion);
			}
								
			response.setEvaluacion((Evaluacion[]) EvaluacionList.toArray(new Evaluacion[EvaluacionList.size()]));

			if (null != response.getEvaluacion() && response.getEvaluacion().length > 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("020");
				messageResponse.setMessage("No se encontro el formulario");
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

    private PreguntaResponse getPreguntas(String codigo, int formularioId) {
    	PreguntaResponse response = new PreguntaResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getPreguntas");
		try {
			this.setConnection();
			List<Pregunta> preguntasList = new ArrayList<Pregunta>();
			String query = "SELECT pr.idcic_pregunta, pr.pr_criterio, pr.pr_formulario \r\n" + 
					"FROM cicte.cic_formulario fo, cicte.cic_pregunta pr\r\n" + 
					"WHERE fo.idcic_formulario = pr.pr_formulario\r\n" + 
					"AND fo.fo_estado = 'V' \r\n" +
					"AND fo.fo_codigo = '"+ codigo +"' \r\n" +
					"AND pr.pr_formulario = "+ formularioId +";";
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
					
			while (rs.next()) {
				Pregunta Preguntas = new Pregunta();
					
				Preguntas.setCodigo(rs.getInt("idcic_pregunta"));
				Preguntas.setCriterio(rs.getString("pr_criterio"));
				Preguntas.setRespuestaList(this.getRespuestas(codigo, rs.getInt("idcic_pregunta")).getRespuesta());
				
				preguntasList.add(Preguntas);
			}

			response.setPregunta((Pregunta[]) preguntasList.toArray(new Pregunta[preguntasList.size()]));

			if (null != response.getPregunta() && response.getPregunta().length > 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("021");
				messageResponse.setMessage("No se encuetran las Preguntas del Evaluacion");
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
    
    private RespuestaResponse getRespuestas(String codigo, int preguntaId) {
    	RespuestaResponse response = new RespuestaResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getRespuestas");
		try {
			this.setConnection();
			List<Respuesta> respuestasList = new ArrayList<Respuesta>();
			String query = "SELECT  re.idcic_respuesta, re.re_detalle, re.re_tipo, re.re_pregunta,\r\n" + 
					"       (select c.detalle from cicte.cic_tabla t, cic_catalogo c \r\n" + 
					"		where t.ta_codigo = c.tabla \r\n" + 
					"        and t.ta_nombre = 'cic_pregunta' and c.valor = re.re_tipo) as re_tipo_respuesta\r\n" + 
					"FROM cicte.cic_formulario fo, cicte.cic_pregunta pr, cicte.cic_respuesta re\r\n" + 
					"WHERE fo.idcic_formulario = pr.pr_formulario\r\n" + 
					"AND pr.idcic_pregunta = re.re_pregunta\r\n" + 
					"AND re.re_estado = 'V' \r\n" +
					"AND re.re_pregunta = "+ preguntaId +" \r\n" +
					"AND fo.fo_codigo = '"+ codigo +"';";
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
					
			while (rs.next()) {
				Respuesta Respuestas = new Respuesta();
			
				Respuestas.setCodigo(rs.getInt("idcic_respuesta"));
				Respuestas.setDetalle(rs.getString("re_detalle"));
				Respuestas.setTipo(rs.getInt("re_tipo"));
				Respuestas.setTipoRespuesta(rs.getString("re_tipo_respuesta"));
				respuestasList.add(Respuestas);
			}

			response.setRespuesta((Respuesta[]) respuestasList.toArray(new Respuesta[respuestasList.size()]));

			if (null != response.getRespuesta() && response.getRespuesta().length > 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("010");
				messageResponse.setMessage("No se encuetran los Evaluaciones");
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
    
	public EvaluacionResponse updateEvaluacion(EvaluacionRequest request) {
		EvaluacionResponse response = new EvaluacionResponse();
		Message messageResponse = new Message();
		
		logger.info("Iniciando metodo updateEvaluacion");
		try {
			this.setConnection();
			String query = "UPDATE cicte.cic_formulario \n" +
					       "SET fo_nombre = ?, fo_detalle = ?, fo_fecha_mod = default \n"+
					       "WHERE idcic_formulario = ?";
			PreparedStatement value = conn.prepareStatement(query);
			//value.setString(1, request.getNombre());
			//value.setString(2, request.getDetalle());
			//value.setInt(3, request.getId());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("011");
			messageResponse.setMessage("Error en actualizar el Evaluacion");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	public EvaluacionResponse deleteEvaluacion(EvaluacionRequest request) {
		EvaluacionResponse response = new EvaluacionResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo deleteEvaluacion");
		try {
			this.setConnection();
			String query = "UPDATE cicte.cic_formulario \n" +
					       "SET fo_estado = ?, fo_fecha_mod = default \n"+
					       "WHERE idcic_formulario = ?";
			PreparedStatement value = conn.prepareStatement(query);
			//value.setString(1, request.getEstado());
			//value.setInt(2, request.getId());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("013");
			messageResponse.setMessage("Error en desabilitar el Evaluacion");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}
}
