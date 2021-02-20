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

import com.espe.cicte.ws.dto.Resultados;
import com.espe.cicte.ws.dto.ResultadoRequest;
import com.espe.cicte.ws.dto.ResultadoResponse;
import com.espe.cicte.ws.dto.Message;
import com.espe.cicte.ws.dto.Pregunta;
import com.espe.cicte.ws.dto.PreguntaResponse;
import com.espe.cicte.ws.dto.Respuesta;
import com.espe.cicte.ws.dto.RespuestaResponse;
import com.espe.cicte.ws.utils.Config;

public class ResultadoDB {
	
	private static Logger logger = org.apache.log4j.Logger.getLogger(ResultadoDB.class);
	
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
	public ResultadoResponse addResultado(ResultadoRequest request) {
		ResultadoResponse response = new ResultadoResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo addResultado");
		SeqnosDb seqnosDb = new SeqnosDb();
		int seqnos = seqnosDb.getIdSeqnos().getCodigo() - 1;
		try {
			this.setConnection();
			String query = "INSERT INTO cicte.cic_cabecera_respuestas \n" +
					"(cr_evaluacion, cr_formulario, cr_pregunta, cr_respuesta) \n" +
					"VALUES(?, ?, ?, ?);";
			PreparedStatement value = conn.prepareStatement(query);
			value.setInt(1, seqnos);
			value.setInt(2, request.getFormulario());
			value.setInt(3, request.getPregunta());
			value.setInt(4, request.getRespuesta());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("012");
			messageResponse.setMessage("Error al insertar el Resultado");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	public ResultadoResponse getResultado(ResultadoRequest request) {
		ResultadoResponse response = new ResultadoResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getResultado");
		try {
			this.setConnection();
			List<Resultados> ResultadoList = new ArrayList<Resultados>();
			String query = "select ev.idcic_evaluacion, fo.idcic_formulario, pr.idcic_pregunta, re.idcic_respuesta, \r\n" + 
					"	   pi.pi_nombre, pi.pi_apellidos, pi.pi_id_mil, ev.ev_observacion,\r\n" + 
					"	   ev.ev_fecha, fo.fo_codigo, fo.fo_detalle,\r\n" + 
					"       pr.pr_criterio, re.re_tipo, re_detalle,\r\n" + 
					"       (select c.detalle from cicte.cic_tabla t, cic_catalogo c \r\n" + 
					"        where t.ta_codigo = c.tabla and t.ta_nombre = 'cic_pregunta' and c.valor = re.re_tipo) as re_tipo_respuesta\r\n" + 
					"from cicte.cic_cabecera_respuestas cr, cicte.cic_evaluacion ev, cicte.cic_formulario fo,\r\n" + 
					"			  cicte.cic_pregunta pr, cicte.cic_respuesta re, cicte.cic_piloto pi\r\n" + 
					"where cr.cr_evaluacion = ev.idcic_evaluacion\r\n" + 
					"and cr.cr_formulario = fo.idcic_formulario\r\n" + 
					"and cr.cr_pregunta = pr.idcic_pregunta\r\n" + 
					"and cr.cr_respuesta = re.idcic_respuesta\r\n" + 
					"and ev.ev_piloto = pi.pi_id_mil \r\n" +
					"and ev.ev_piloto = '" + request.getPiloto() + "'";
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				Resultados Resultado = new Resultados();
				//Resultado.setId(rs.getInt("idcic_cabecera_respuestas"));
				//Resultado.setNombre(rs.getString("fo_nombre"));
				//Resultado.setFecha_crea(rs.getString("fo_fecha_crea"));
				//Resultado.setCodigo(rs.getString("fo_codigo"));
				//Resultado.setDetalle(rs.getString("fo_detalle"));
				//Resultado.setEstado(rs.getString("fo_estado"));
				ResultadoList.add(Resultado);
			}
			//response.setResultados((Resultados[]) ResultadoList.toArray(new Resultados[ResultadoList.size()]));

			if (null != response.getResultados() )
				response.setSuccess(true);
			else {
				messageResponse.setCode("010");
				messageResponse.setMessage("No se encuetran los Resultadoes");
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
	
	public ResultadoResponse getResultadoMatriz(ResultadoRequest request) {
		ResultadoResponse response = new ResultadoResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getResultadoMatriz");
		try {
			this.setConnection();
			List<Resultados> ResultadoList = new ArrayList<Resultados>();
			String query = "SELECT fo.idcic_cabecera_respuestas, fo.fo_codigo, fo.fo_detalle \r\n" + 
					"FROM cicte.cic_cabecera_respuestas fo\r\n" + 
					//"WHERE fo.fo_codigo =  '"+ request.getCodigo() +"' \r\n" + 
					"AND fo.fo_estado = 'V';" ;
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
					
			while (rs.next()) {
				Resultados Resultado = new Resultados();
				//Resultado.setId(rs.getInt("idcic_cabecera_respuestas"));
				//Resultado.setCodigo(rs.getString("fo_codigo"));
				//Resultado.setDetalle(rs.getString("fo_detalle"));
				//Resultado.setPreguntaList(this.getPreguntas(request.getCodigo(),rs.getInt("idcic_cabecera_respuestas")).getPregunta());	
				ResultadoList.add(Resultado);
			}
								
			//response.setResultado((Resultados[]) ResultadoList.toArray(new Resultados[ResultadoList.size()]));

			
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
					"FROM cicte.cic_cabecera_respuestas fo, cicte.cic_pregunta pr\r\n" + 
					"WHERE fo.idcic_cabecera_respuestas = pr.pr_formulario\r\n" + 
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
				messageResponse.setMessage("No se encuetran las Preguntas del Resultado");
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
					"FROM cicte.cic_cabecera_respuestas fo, cicte.cic_pregunta pr, cicte.cic_respuesta re\r\n" + 
					"WHERE fo.idcic_cabecera_respuestas = pr.pr_formulario\r\n" + 
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
				messageResponse.setMessage("No se encuetran los Resultadoes");
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
    
	public ResultadoResponse updateResultado(ResultadoRequest request) {
		ResultadoResponse response = new ResultadoResponse();
		Message messageResponse = new Message();
		
		logger.info("Iniciando metodo updateResultado");
		try {
			this.setConnection();
			String query = "UPDATE cicte.cic_cabecera_respuestas \n" +
					       "SET fo_nombre = ?, fo_detalle = ?, fo_fecha_mod = default \n"+
					       "WHERE idcic_cabecera_respuestas = ?";
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
			messageResponse.setMessage("Error en actualizar el Resultado");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	public ResultadoResponse deleteResultado(ResultadoRequest request) {
		ResultadoResponse response = new ResultadoResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo deleteResultado");
		try {
			this.setConnection();
			String query = "UPDATE cicte.cic_cabecera_respuestas \n" +
					       "SET fo_estado = ?, fo_fecha_mod = default \n"+
					       "WHERE idcic_cabecera_respuestas = ?";
			PreparedStatement value = conn.prepareStatement(query);
			//value.setString(1, request.getEstado());
			//value.setInt(2, request.getId());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("013");
			messageResponse.setMessage("Error en desabilitar el Resultado");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}
}
