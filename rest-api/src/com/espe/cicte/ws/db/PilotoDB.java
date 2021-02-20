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
import com.espe.cicte.ws.dto.Piloto;
import com.espe.cicte.ws.dto.PilotoRequest;
import com.espe.cicte.ws.dto.PilotoResponse;
import com.espe.cicte.ws.utils.Config;

public class PilotoDB {
	private static Logger logger = org.apache.log4j.Logger.getLogger(PilotoDB.class);

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
	
	
	public PilotoResponse addPiloto(Piloto request) {
		PilotoResponse response = new PilotoResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo add Piloto");
		try {
			this.setConnection();
			String query = "INSERT INTO cicte.cic_piloto \n" +
					"(pi_nombre, pi_apellidos, pi_fecha_nacimiento, pi_grado, pi_fecha_reg, pi_fecha_mod, pi_nacionalidad, \n"+
					" pi_cedula, pi_id_mil, pi_foto, pi_estado, pi_fuerza, pi_unidad, pi_email, pi_telefono)\n" +
					"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement value = conn.prepareStatement(query);
			value.setString(1, request.getNombres());
			value.setString(2, request.getApellidos());
			value.setString(3, request.getFecha_nac());
			value.setInt(4, request.getGrado());
			value.setString(5, request.getFecha_reg());
			value.setString(6, request.getFecha_mod());
			value.setString(7, request.getNacionalidad());
			value.setString(8, request.getId());
			value.setString(9, request.getId_mil());
			value.setBytes(10, request.getFoto());
			value.setString(11, request.getEstado());
			value.setString(12, request.getFuerza());
			value.setString(13, request.getUnidad());
			value.setString(14, request.getEmail());
			value.setString(15, request.getTelefono());
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
	
	public PilotoResponse getPilotos() {
		PilotoResponse response = new PilotoResponse();

		Message messageResponse = new Message();
		logger.info("Iniciando metodo get Piloto");

		try {
			this.setConnection();
			
			List<Piloto> pilotoList = new ArrayList<Piloto>();	
			// STATES: W=WAITING; R=RUNNING; D=DONE
			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of using "*"
			String query = "SELECT *, \r\n" + 
					"(select c.detalle from cicte.cic_tabla t, cic_catalogo c \r\n" + 
					"where t.ta_codigo = c.tabla and t.ta_nombre = 'cic_piloto' and c.valor = p.pi_grado) as pi_rango \r\n" + 
					"FROM cicte.cic_piloto p";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
				while (rs.next()) {		
					Piloto piloto = new Piloto();
					piloto.setCodigo(rs.getInt("idcic_piloto"));
					piloto.setNombres(rs.getString("pi_nombre"));
					piloto.setApellidos(rs.getString("pi_apellidos"));
					piloto.setEmail(rs.getString("pi_email"));
					piloto.setTelefono(rs.getString("pi_telefono"));
					piloto.setFecha_nac(rs.getString("pi_fecha_nacimiento"));
					piloto.setNacionalidad(rs.getString("pi_nacionalidad"));
					piloto.setId(rs.getString("pi_cedula"));							
					piloto.setId_mil(rs.getString("pi_id_mil"));
					piloto.setGrado(rs.getInt("pi_grado"));
					piloto.setFoto(rs.getBytes("pi_foto"));
					piloto.setFuerza(rs.getString("pi_fuerza"));
					piloto.setUnidad(rs.getString("pi_unidad"));
					piloto.setEstado(rs.getString("pi_estado"));
					piloto.setFecha_reg(rs.getString("pi_fecha_reg"));
					piloto.setRango(rs.getString("pi_rango"));
					piloto.setHv(rs.getInt("pi_horas_vuelo"));
					pilotoList.add(piloto);
				}
				
				response.setPiloto((Piloto[]) pilotoList.toArray(new Piloto[pilotoList.size()]));
				
			if(null != response.getPiloto() && response.getPiloto().length > 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("004");
				messageResponse.setMessage("No se encuetran los pilotos");
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
	public PilotoResponse getFotoPiloto(PilotoRequest request) {
		PilotoResponse response = new PilotoResponse();

		Message messageResponse = new Message();
		logger.info("Iniciando metodo get getFotoPiloto");

		try {
			this.setConnection();
			
			List<Piloto> pilotoList = new ArrayList<Piloto>();	
			// STATES: W=WAITING; R=RUNNING; D=DONE
			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of using "*"
			String query = "SELECT * FROM cicte.cic_piloto \n"+
						   "WHERE pi_id_mil ="+ request.getId_mil();

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
				while (rs.next()) {		
					Piloto piloto = new Piloto();
					piloto.setFoto(rs.getBytes("pi_foto"));
					pilotoList.add(piloto);
				}
				
				response.setPiloto((Piloto[]) pilotoList.toArray(new Piloto[pilotoList.size()]));
				
			if(null != response.getPiloto() && response.getPiloto().length > 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("004");
				messageResponse.setMessage("No se encuetran los pilotos");
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
	
	public PilotoResponse getPilotoById(PilotoRequest request) {
		PilotoResponse response = new PilotoResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getPilotoById");
		try {
			this.setConnection();
			
			List<Piloto> pilotoList = new ArrayList<Piloto>();	
			// STATES: W=WAITING; R=RUNNING; D=DONE
			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of using "*"
			String query = "SELECT *, \r\n" + 
					       "(select c.detalle from cicte.cic_tabla t, cic_catalogo c \r\n" + 
					       "where t.ta_codigo = c.tabla and t.ta_nombre = 'cic_piloto' and c.valor = p.pi_grado) as pi_rango \r\n" + 
					       "FROM cicte.cic_piloto p \n"+
						   "WHERE pi_id_mil LIKE '%"+ request.getId_mil()+"%'";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
				while (rs.next()) {		
					Piloto piloto = new Piloto();
					piloto.setCodigo(rs.getInt("idcic_piloto"));
					piloto.setNombres(rs.getString("pi_nombre"));
					piloto.setApellidos(rs.getString("pi_apellidos"));
					piloto.setEmail(rs.getString("pi_email"));
					piloto.setTelefono(rs.getString("pi_telefono"));
					piloto.setFecha_nac(rs.getString("pi_fecha_nacimiento"));
					piloto.setNacionalidad(rs.getString("pi_nacionalidad"));
					piloto.setId(rs.getString("pi_cedula"));							
					piloto.setId_mil(rs.getString("pi_id_mil"));
					piloto.setGrado(rs.getInt("pi_grado"));
					piloto.setFoto(rs.getBytes("pi_foto"));
					piloto.setFuerza(rs.getString("pi_fuerza"));
					piloto.setUnidad(rs.getString("pi_unidad"));
					piloto.setEstado(rs.getString("pi_estado"));
					piloto.setFecha_reg(rs.getString("pi_fecha_reg"));
					piloto.setRango(rs.getString("pi_rango"));
					pilotoList.add(piloto);
				}
				
				response.setPiloto((Piloto[]) pilotoList.toArray(new Piloto[pilotoList.size()]));
				
			if(null != response.getPiloto() && response.getPiloto().length > 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("004");
				messageResponse.setMessage("No se encuetran los pilotos");
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
	

	public PilotoResponse updateFotoPiloto(PilotoRequest request) {
		PilotoResponse response = new PilotoResponse();
		Message messageResponse = new Message();
		try {
			this.setConnection();
			String query = "UPDATE cicte.cic_piloto \n" +
					       "SET pi_foto=? \n"+
					       "WHERE pi_id_mil=?";
			PreparedStatement value = conn.prepareStatement(query);
			value.setBlob(1, request.getFoto());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("015");
			messageResponse.setMessage("Error en actualizar la foto");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;	
	}
	

	public PilotoResponse updatePiloto(Piloto request) {
		PilotoResponse response = new PilotoResponse();
		Message messageResponse = new Message();
		try {
			this.setConnection();
			String query = "UPDATE cicte.cic_piloto \n" +
					       "SET pi_nombre=?, pi_apellidos=?, pi_fecha_nacimiento=?, pi_grado=?, pi_fecha_mod=?, pi_nacionalidad=?, \n"+
					       "pi_cedula=?, pi_estado=?, pi_fuerza=?, pi_unidad=?, pi_email=? \n"+
					       "WHERE pi_id_mil=?";
			PreparedStatement value = conn.prepareStatement(query);
			value.setString(1, request.getNombres());
			value.setString(2, request.getApellidos());
			value.setString(3, request.getFecha_nac());
			value.setInt(4, request.getGrado());
			value.setString(5, request.getFecha_mod());
			value.setString(6, request.getNacionalidad());
			value.setString(7, request.getId());
			value.setString(8, request.getEstado());
			value.setString(9, request.getFuerza());
			value.setString(10, request.getUnidad());
			value.setString(11, request.getEmail());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("011");
			messageResponse.setMessage("Error en actualizar piloto");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;	
	}
	
	public PilotoResponse deletePiloto(Piloto request) {
		PilotoResponse response = new PilotoResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo deleteSimulador");
		try {
			this.setConnection();
			String query = "DELETE FROM cicte.cic_piloto \n" +					     
					       "WHERE pi_id_mil = ?";
			PreparedStatement value = conn.prepareStatement(query);
			value.setString(1, request.getId_mil());
			value.execute();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("007");
			messageResponse.setMessage("Error en eliminar el Piloto seleccionado");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}
}
