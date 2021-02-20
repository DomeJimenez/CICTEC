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
import com.espe.cicte.ws.dto.Respuesta;
import com.espe.cicte.ws.dto.RespuestaRequest;
import com.espe.cicte.ws.dto.RespuestaResponse;
import com.espe.cicte.ws.utils.Config;

public class RespuestaDB {

	private static Logger logger = org.apache.log4j.Logger.getLogger(RespuestaDB.class);

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
	
	public RespuestaResponse addRespuesta(Respuesta request) {
		RespuestaResponse response = new RespuestaResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo addRespuesta");
		try {
			this.setConnection();
			String query = "INSERT INTO cicte.cic_pregunta \n" +
					"(re_tipo, re_detalle, re_pregunta )\n" +
					"VALUES(?, ?, ?);";
			PreparedStatement value = conn.prepareStatement(query);			
			value.setInt(1, request.getTipo());
			value.setString(2, request.getDetalle());
			value.setInt(3, request.getPregunta().getCodigo());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("012");
			messageResponse.setMessage("Error al insertar el Respuesta");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	public RespuestaResponse updateRespuesta(Respuesta request) {
		RespuestaResponse response = new RespuestaResponse();
		Message messageResponse = new Message();

		logger.info("Iniciando metodo updateRespuesta");
		try {
			this.setConnection();
			String query = "UPDATE cicte.cic_pregunta \n" +
					       "SET re_tipo = ? , re_detalle = ?, re_pregunta = ? , re_estado = ? \n"+
					       "WHERE idcic_respuesta = ?";
			PreparedStatement value = conn.prepareStatement(query);
			value.setInt(1, request.getTipo());
			value.setString(2, request.getDetalle());
			value.setInt(3, request.getPregunta().getCodigo());
			value.setString(4, "V");
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("011");
			messageResponse.setMessage("Error en actualizar el Respuesta");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	public RespuestaResponse getRespuesta(RespuestaRequest request) {
		RespuestaResponse response = new RespuestaResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getRespuesta");
		try {
			this.setConnection();
			List<Respuesta> ilusionList = new ArrayList<Respuesta>();
			String query = "SELECT *,  \r\n" + 
					"(select c.detalle from cicte.cic_tabla t, cic_catalogo c\r\n" + 
					"where t.ta_codigo = c.tabla and t.ta_nombre = 'cic_pregunta' and c.valor = re.re_tipo) as re_tipo_respuesta \r\n" + 
					"FROM cicte.cic_respuesta re\r\n" + 
					"WHERE re_estado = 'V';";
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				Respuesta Respuesta = new Respuesta();
				Respuesta.setCodigo(rs.getInt("idcic_respuesta"));
				Respuesta.setTipo(rs.getInt("re_tipo"));
				Respuesta.setDetalle(rs.getString("re_detalle"));
				Respuesta.setTipoRespuesta(rs.getString("re_tipo_respuesta"));
				Respuesta.setPreguntaCodigo(rs.getInt("re_pregunta"));
				Respuesta.setEstado(rs.getString("re_estado"));
				ilusionList.add(Respuesta);
			}
			response.setRespuesta((Respuesta[]) ilusionList.toArray(new Respuesta[ilusionList.size()]));

			if (null != response.getRespuesta() && response.getRespuesta().length > 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("010");
				messageResponse.setMessage("No se encuetran los Respuestaes");
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

	public RespuestaResponse getRespuestaById(RespuestaRequest request) {
		RespuestaResponse response = new RespuestaResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getRespuestaByName");
		try {
			this.setConnection();
			List<Respuesta> RespuestaList = new ArrayList<Respuesta>();
			String query = "SELECT *,  \r\n" + 
					"(select c.detalle from cicte.cic_tabla t, cic_catalogo c\r\n" + 
					"where t.ta_codigo = c.tabla and t.ta_nombre = 'cic_pregunta' and c.valor = re.re_tipo) as re_tipo_respuesta \r\n" + 
					"FROM cicte.cic_respuesta re\r\n" + 
					"WHERE re_estado = 'V' \r\n" + 
					"AND re_pregunta = '"+ request.getPregunta().getCodigo() +"';";
			
			// create the java statement
			Statement st = conn.createStatement(); 
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				Respuesta Respuesta = new Respuesta();
				Respuesta.setCodigo(rs.getInt("idcic_respuesta"));
				Respuesta.setTipo(rs.getInt("re_tipo"));
				Respuesta.setDetalle(rs.getString("re_detalle"));
				Respuesta.setPreguntaCodigo(rs.getInt("re_pregunta"));
				Respuesta.setEstado(rs.getString("re_estado"));
				RespuestaList.add(Respuesta);
			}
			response.setRespuesta((Respuesta[]) RespuestaList.toArray(new Respuesta[RespuestaList.size()]));

			if (null != response.getRespuesta() && response.getRespuesta().length > 0)
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
	

	public RespuestaResponse deleteRespuesta(RespuestaRequest request) {
		RespuestaResponse response = new RespuestaResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo deleteRespuesta");
		try {
			this.setConnection();
			String query = "DELETE FROM cicte.cic_pregunta \n" +					     
					       "WHERE idcic_respuesta = ?";
			PreparedStatement value = conn.prepareStatement(query);
			value.setInt(1, request.getCodigo());
			value.execute();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("013");
			messageResponse.setMessage("Error en eliminar el Respuesta");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}
	
}
