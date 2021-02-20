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

import com.espe.cicte.ws.dto.Instructor;
import com.espe.cicte.ws.dto.InstructorRequest;
import com.espe.cicte.ws.dto.InstructorResponse;
import com.espe.cicte.ws.dto.Message;
import com.espe.cicte.ws.utils.Config;
import com.espe.cicte.ws.utils.Utils;

public class InstructorDB {
	
	private static Logger logger = org.apache.log4j.Logger.getLogger(InstructorDB.class);

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
	
	public InstructorResponse addInstructor(InstructorRequest request) {
		InstructorResponse response = new InstructorResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo addInstructor");
		try {
			this.setConnection();
			String query = "INSERT INTO cicte.cic_instructor \n" +
					"(in_nombre, in_apellido, in_id_mil, in_cedula, in_grado, in_email)\n" +
					"VALUES(?, ?, ?, ?, ?, ?);";
			PreparedStatement value = conn.prepareStatement(query);
			value.setString(1, request.getNombre());
			value.setString(2, request.getApellido());
			value.setString(3, request.getId_mil());
			value.setString(4, request.getCedula());
			value.setInt(5, request.getGrado());
			value.setString(6, request.getEmail());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("012");
			messageResponse.setMessage("Error al insertar el Instructor");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	public InstructorResponse getInstructor() {
		InstructorResponse response = new InstructorResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getInstructor");
		try {
			this.setConnection();
			List<Instructor> InstructorList = new ArrayList<Instructor>();
			String query = "SELECT * ,\n" + 
					"(select c.detalle from cicte.cic_tabla t, cic_catalogo c\n" + 
					" where t.ta_codigo = c.tabla and t.ta_nombre = 'cic_piloto' and c.valor = i.in_grado) as in_rango \n" + 
					"FROM cicte.cic_instructor i;";
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				Instructor Instructor = new Instructor();
				Instructor.setCodigo(rs.getInt("idcic_instructor"));
				Instructor.setNombre(rs.getString("in_nombre"));
				Instructor.setApellido(rs.getString("in_apellido"));
				Instructor.setId_mil(rs.getString("in_id_mil"));
				Instructor.setCedula(rs.getString("in_cedula"));
				Instructor.setRango(rs.getString("in_rango"));
				Instructor.setEmail(rs.getString("in_email"));
				Instructor.setGrado(rs.getInt("in_grado"));
				InstructorList.add(Instructor);
			}
			response.setInstructor((Instructor[]) InstructorList.toArray(new Instructor[InstructorList.size()]));

			if (null != response.getInstructor() && response.getInstructor().length > 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("010");
				messageResponse.setMessage("No se encuetran los Instructores");
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

	public InstructorResponse updateInstructor(InstructorRequest request) {
		InstructorResponse response = new InstructorResponse();
		Message messageResponse = new Message();

		
		logger.info("Iniciando metodo updateInstructor");
		try {
			this.setConnection();
			String query = "UPDATE cicte.cic_instructor \n" +
					       "SET in_nombre = ?, in_apellido = ?, in_cedula = ?, in_grado = ?, in_create = ?, in_email = ? \n"+
					       "WHERE idcic_instructor = ?";
			PreparedStatement value = conn.prepareStatement(query);
			value.setString(1, request.getNombre());
			value.setString(2, request.getApellido());
			value.setString(3, request.getCedula());
			value.setInt(4, request.getGrado());
			value.setString(5, Utils.getInstance().getDateTime());
			value.setInt(6, request.getCodigo());
			value.setString(7, request.getEmail());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("011");
			messageResponse.setMessage("Error en actualizar el Instructor");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	public InstructorResponse getInstructorById(InstructorRequest request) {
		InstructorResponse response = new InstructorResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getInstructorByName");
		try {
			this.setConnection();
			List<Instructor> InstructorList = new ArrayList<Instructor>();
			String query = "SELECT * ,\n" + 
					"(select c.detalle from cicte.cic_tabla t, cic_catalogo c\n" + 
					" where t.ta_codigo = c.tabla and t.ta_nombre = 'cic_piloto' and c.valor = i.in_grado) as in_rango \n" + 
					"FROM cicte.cic_instructor i \n" + 
				    "WHERE in_id_mil like '%"+request.getId_mil()+"%'";
			// create the java statement
			Statement st = conn.createStatement(); 
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				Instructor Instructor = new Instructor();
				Instructor.setCodigo(rs.getInt("idcic_instructor"));
				Instructor.setNombre(rs.getString("in_nombre"));
				Instructor.setApellido(rs.getString("in_apellido"));
				Instructor.setId_mil(rs.getString("in_id_mil"));
				Instructor.setCedula(rs.getString("in_cedula"));
				Instructor.setRango(rs.getString("in_rango"));
				Instructor.setGrado(rs.getInt("in_grado"));
				Instructor.setEmail(rs.getString("in_email"));
				InstructorList.add(Instructor);
			}
			response.setInstructor((Instructor[]) InstructorList.toArray(new Instructor[InstructorList.size()]));

			if (null != response.getInstructor() && response.getInstructor().length > 0)
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

	

	public InstructorResponse deleteInstructor(InstructorRequest request) {
		InstructorResponse response = new InstructorResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo delete Instructor");
		try {
			this.setConnection();
			String query = "DELETE FROM cicte.cic_instructor \n" +					     
					       "WHERE idcic_instructor = ?";
			PreparedStatement value = conn.prepareStatement(query);
			value.setInt(1, request.getCodigo());
			value.execute();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("013");
			messageResponse.setMessage("Error en eliminar el Instructor");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

}
