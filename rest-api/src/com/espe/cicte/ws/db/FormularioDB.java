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

import com.espe.cicte.ws.dto.Formulario;
import com.espe.cicte.ws.dto.FormularioRequest;
import com.espe.cicte.ws.dto.FormularioResponse;
import com.espe.cicte.ws.dto.Message;
import com.espe.cicte.ws.dto.Pregunta;
import com.espe.cicte.ws.dto.PreguntaResponse;
import com.espe.cicte.ws.dto.Respuesta;
import com.espe.cicte.ws.dto.RespuestaResponse;
import com.espe.cicte.ws.utils.Config;
import com.espe.cicte.ws.utils.Utils;
import com.mysql.jdbc.Util;

public class FormularioDB {

	private static Logger logger = org.apache.log4j.Logger.getLogger(FormularioDB.class);

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
	
	public FormularioResponse addFormulario(FormularioRequest request) {
		FormularioResponse response = new FormularioResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo addFormulario");
		try {
			this.setConnection();
			String query = "INSERT INTO cicte.cic_formulario \n" +
					"(fo_nombre, fo_fecha_crea, fo_fecha_mod, fo_codigo, fo_detalle, fo_estado) \n" +
					"VALUES(?, NOW(), NOW(), ?, ?, default);";
			PreparedStatement value = conn.prepareStatement(query);
			value.setString(1, request.getNombre());
			value.setString(2, request.getCodigo());
			value.setString(3, request.getDetalle());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("012");
			messageResponse.setMessage("Error al insertar el Formulario");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	public FormularioResponse getFormulario() {
		FormularioResponse response = new FormularioResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getFormulario");
		try {
			this.setConnection();
			List<Formulario> FormularioList = new ArrayList<Formulario>();
			String query = "SELECT idcic_formulario, fo_nombre, fo_fecha_crea, \r\n" + 
					"	 fo_codigo, fo_detalle, fo_estado \r\n" + 
					"	 FROM cicte.cic_formulario WHERE fo_estado = 'V' ";
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				Formulario Formulario = new Formulario();
				Formulario.setId(rs.getInt("idcic_formulario"));
				Formulario.setNombre(rs.getString("fo_nombre"));
				Formulario.setFecha_crea(rs.getString("fo_fecha_crea"));
				Formulario.setCodigo(rs.getString("fo_codigo"));
				Formulario.setDetalle(rs.getString("fo_detalle"));
				Formulario.setEstado(rs.getString("fo_estado"));
				FormularioList.add(Formulario);
			}
			response.setFormulario((Formulario[]) FormularioList.toArray(new Formulario[FormularioList.size()]));

			if (null != response.getFormulario() && response.getFormulario().length > 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("010");
				messageResponse.setMessage("No se encuetran los Formularioes");
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
	
	public FormularioResponse getFormularioMatriz(FormularioRequest request) {
		FormularioResponse response = new FormularioResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getFormularioMatriz");
		try {
			this.setConnection();
			List<Formulario> FormularioList = new ArrayList<Formulario>();
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
				Formulario Formulario = new Formulario();
				Formulario.setId(rs.getInt("idcic_formulario"));
				Formulario.setCodigo(rs.getString("fo_codigo"));
				Formulario.setDetalle(rs.getString("fo_detalle"));
				Formulario.setPreguntaList(this.getPreguntas(request.getCodigo(),rs.getInt("idcic_formulario")).getPregunta());	
				FormularioList.add(Formulario);
			}
								
			response.setFormulario((Formulario[]) FormularioList.toArray(new Formulario[FormularioList.size()]));

			if (null != response.getFormulario() && response.getFormulario().length > 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("020");
				messageResponse.setMessage("No se encontró el formulario");
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
				messageResponse.setMessage("No se encuetran las Preguntas del Formulario");
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
				messageResponse.setMessage("No se encuetran los Formularioes");
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
    
	public FormularioResponse updateFormulario(FormularioRequest request) {
		FormularioResponse response = new FormularioResponse();
		Message messageResponse = new Message();
		
		logger.info("Iniciando metodo updateFormulario");
		try {
			this.setConnection();
			String query = "UPDATE cicte.cic_formulario \n" +
					       "SET fo_nombre = ?, fo_detalle = ?, fo_fecha_mod = ? \n"+
					       "WHERE idcic_formulario = ?";
			PreparedStatement value = conn.prepareStatement(query);
			value.setString(1, request.getNombre());
			value.setString(2, request.getDetalle());
			value.setString(3, Utils.getInstance().getDateTime());
			value.setInt(4, request.getId());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("011");
			messageResponse.setMessage("Error en actualizar el Formulario");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	public FormularioResponse deleteFormulario(FormularioRequest request) {
		FormularioResponse response = new FormularioResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo deleteFormulario");
		try {
			this.setConnection();
			String query = "UPDATE cicte.cic_formulario \n" +
					       "SET fo_estado = ?, fo_fecha_mod = default \n"+
					       "WHERE idcic_formulario = ?";
			PreparedStatement value = conn.prepareStatement(query);
			value.setString(1, request.getEstado());
			value.setInt(2, request.getId());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("013");
			messageResponse.setMessage("Error en desabilitar el Formulario");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}
}
