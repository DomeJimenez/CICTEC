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
import com.espe.cicte.ws.dto.InstructorResponse;
import com.espe.cicte.ws.dto.Login;
import com.espe.cicte.ws.dto.LoginRequest;
import com.espe.cicte.ws.dto.LoginResponse;
import com.espe.cicte.ws.dto.Message;
import com.espe.cicte.ws.utils.Config;
//import com.espe.cicte.ws.utils.GibberishAESCrypto;
import com.espe.cicte.ws.utils.Utils;

public class LoginDB {

	private static Logger logger = org.apache.log4j.Logger.getLogger(LoginDB.class);

	private Connection conn;
	//private GibberishAESCrypto crypto = new GibberishAESCrypto();

	//private static final char[] PUBLIC_KEY = "secret".toCharArray();

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
	
	public LoginResponse addUser(LoginRequest request) {
		LoginResponse response = new LoginResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo addInstructor");
		try {
			if(getInstructor(request.getInstructor())) {
			this.setConnection();
			String query = "INSERT INTO cicte.usuario \n" + 
					"(username, email, password, instructor, intento, terminal) \n" +
					"VALUES(?, ?, ?, ?, ?, ?);";
			PreparedStatement value = conn.prepareStatement(query);
			value.setString(1, request.getUsername());
			value.setString(2, request.getEmail());
			value.setString(3, request.getPassword());
			value.setString(4, request.getInstructor());
			value.setInt(5, 0);
			value.setString(6, "127.0.0.1");
			value.executeUpdate();
			conn.close();
			if(addUserRol(request)) {
				response.setSuccess(true);
			}else {
				messageResponse.setCode("510");
				messageResponse.setMessage("Erro al insertar el rol de usuario");
				response.setMessage(messageResponse);
				response.setSuccess(false);
			}
			} else {
				messageResponse.setCode("510");
				messageResponse.setMessage("No se encuetra precalificado en el sistema por favor consulte al administrador");
				response.setMessage(messageResponse);
				response.setSuccess(false);
			}
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("012");
			messageResponse.setMessage("Error al insertar el Instructor");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}
	
	private boolean addUserRol(LoginRequest request) {
		LoginResponse response = new LoginResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo addInstructor");
		try {
			if(getInstructor(request.getInstructor())) {
			this.setConnection();
			String query = "INSERT INTO cicte.usuario_grupo (usuario_id, grupo_id) \n" + 
					       "VALUES (?, '2');";
			PreparedStatement value = conn.prepareStatement(query);
			value.setInt(1, getUserId(request.getUsername()));
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
			} else {
				messageResponse.setCode("510");
				messageResponse.setMessage("No se encuetra el rol del ususrio");
				response.setMessage(messageResponse);
				response.setSuccess(false);
			}
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("012");
			messageResponse.setMessage("Error al insertar el rol del ususario");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response.getSuccess();
	}
	
	private int getUserId(String User) {
		LoginResponse response = new LoginResponse();
		logger.info("Iniciando metodo loginID");
			try {
				this.setConnection();
				Login login = new Login();
				String query = "SELECT * FROM cicte.usuario\n" + 
						       "WHERE username = '"+ User +"'; ";
				// create the java statement
				Statement st = conn.createStatement();
				// execute the query, and get a java resultset
				ResultSet rs = st.executeQuery(query);
				// iterate through the java resultset
				while (rs.next()) {
					login.setCodigo(rs.getInt("id_usuario"));
				}
				response.setLogin(login);

				st.close();
				conn.close();
			} catch (SQLException ex) {
				logger.error("Error SQL:\n" + ex);
			}

		return response.getLogin().getCodigo();
	}
	
	public boolean getInstructor(String instructor) {
		boolean flag = false;
		InstructorResponse response = new InstructorResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getInstructor");
		try {
			this.setConnection();
			List<Instructor> InstructorList = new ArrayList<Instructor>();
			String query = "SELECT * \n" +  
					"FROM cicte.cic_instructor WHERE in_id_mil = '"+ instructor +"';";
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
				Instructor.setGrado(rs.getInt("in_grado"));
				InstructorList.add(Instructor);
			}
			response.setInstructor((Instructor[]) InstructorList.toArray(new Instructor[InstructorList.size()]));

			if (null != response.getInstructor() && response.getInstructor().length > 0) {
				response.setSuccess(true);
				flag = true;
			}
			else {
				messageResponse.setCode("010");
				messageResponse.setMessage("No se encuetran los Instructores");
				response.setMessage(messageResponse);
				response.setSuccess(false);
				flag = false;
			}
			st.close();
			conn.close();
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
		}
		return flag;
	}

	public LoginResponse login(LoginRequest loginRequest) {
		Message messageResponse = new Message();
		LoginResponse response = new LoginResponse();
//		String clearPassword;
//		try {
//			clearPassword = crypto.decrypt(loginRequest.getPassword(),PUBLIC_KEY);
//			logger.info("PVI clearPassword trae esto: "+clearPassword);
//
//		} catch (Exception e) {
//			logger.error(e, e);
//			loginResponse.setSuccess(false);
//			messageResponse.setCode("500");
//			messageResponse.setMessage("Problemas con la autenticaciÃ³n");
//			loginResponse.setMessage(messageResponse);
//			loginResponse.setSuccess(false);
//			return loginResponse;
//		}
		if (validateUser(loginRequest.getUsername())) {
			logger.info("Iniciando metodo login");
			try {
				this.setConnection();
				Login login = new Login();
				String query = "SELECT id_usuario, username, instructor, intento, nombre, create_time\n"
						+ "FROM cicte.usuario us, cicte.usuario_grupo ug, cicte.grupo gr\n"
						+ "WHERE usuario_id = id_usuario and\n" + "grupo_id = id_grupo and\n" + "username = '"
						+ loginRequest.getUsername() + "' and password = '" + loginRequest.getPassword() + "';";
				// create the java statement
				Statement st = conn.createStatement();
				// execute the query, and get a java resultset
				ResultSet rs = st.executeQuery(query);
				// iterate through the java resultset
				while (rs.next()) {
					login.setCodigo(rs.getInt("id_usuario"));
					login.setUsuario(rs.getString("username"));
					login.setInstructor(rs.getString("instructor"));
					login.setIntento(rs.getInt("intento"));
					login.setRol(rs.getString("nombre"));
					login.setUltimoIngreso(rs.getString("create_time"));
				}
				response.setLogin(login);

				if (null != response.getLogin().getUsuario()) {
					updateIntentos(0,login.getCodigo());
					updateLastAccess(login.getCodigo());
					response.setSuccess(true);
					response.setAuth(true);
					response.setAccess(true);
				} else {
					updateIntentos(login.getIntento() + 1,login.getCodigo());
					messageResponse.setCode("500");
					messageResponse.setMessage("Problemas con la autenticación");
					response.setMessage(messageResponse);
					response.setSuccess(false);
					response.setAuth(false);
					response.setAccess(false);
				}
				st.close();
				conn.close();
			} catch (SQLException ex) {
				logger.error("Error SQL:\n" + ex);
			}
		} else {
			messageResponse.setCode("501");
			messageResponse.setMessage("No existe el usuario");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}

		return response;
	}
	

	public boolean validateUser(String username) {
		logger.info("Iniciando metodo validateLogin");
		boolean valid = false;
		try {
			this.setConnection();
			Login login = new Login();
			String query = "SELECT id_usuario, username, instructor, intento, nombre, create_time\n" + 
					"FROM cicte.usuario us, cicte.usuario_grupo ug, cicte.grupo gr\n" + 
					"WHERE usuario_id = id_usuario and\n" + 
					"grupo_id = id_grupo and\n" + 
					"username = '"+ username +"'";
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				login.setCodigo(rs.getInt("id_usuario"));
				login.setUsuario(rs.getString("username"));
				login.setInstructor(rs.getString("instructor"));
				login.setIntento(rs.getInt("intento"));
				login.setRol(rs.getString("nombre"));
				login.setUltimoIngreso(rs.getString("create_time"));
			}

			if (null != login.getUsuario()) {
				valid = true;
			}
			else {
				logger.info("No existe el usuario");
				valid = false;
			}
			st.close();
			conn.close();
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			return false;
		}
		return valid;
	}
	
	
	public boolean updateIntentos(int intento, int codigo) {
		boolean flag = false;
		
		logger.info("Iniciando metodo updateIntentos");
		try {
			this.setConnection();
			String query = "UPDATE cicte.usuario \n" + 
						   "SET intento =  ? \n" + 
					       "WHERE id_usuario = ?;";
			PreparedStatement value = conn.prepareStatement(query);
			value.setInt(1, intento);
			value.setInt(2, codigo);
			value.executeUpdate();
			conn.close();
			flag = true;
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			flag = false;
		}
		
		return flag;
	}
	
	public boolean updateLastAccess(int codigo) {
		boolean flag = false;
		
		logger.info("Iniciando metodo updateLastAccess");
		try {
			this.setConnection();
			String query = "UPDATE cicte.usuario \n" + 
						   "SET create_time = ? \n" + 
						   "WHERE id_usuario = ?;";
			PreparedStatement value = conn.prepareStatement(query);
			value.setString(1, Utils.getInstance().getDateTime());
			value.setInt(2, codigo);
			value.executeUpdate();
			conn.close();
			flag = true;
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			flag = false;
		}
		
		return flag;
	}

	
}
