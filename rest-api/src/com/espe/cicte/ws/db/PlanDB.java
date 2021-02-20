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

import com.espe.cicte.ws.dto.Plan;
import com.espe.cicte.ws.dto.PlanRequest;
import com.espe.cicte.ws.dto.PlanResponse;
import com.espe.cicte.ws.dto.Message;
import com.espe.cicte.ws.utils.Config;

public class PlanDB {

	private static Logger logger = org.apache.log4j.Logger.getLogger(PlanDB.class);

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
	
	public PlanResponse addPlan(PlanRequest request) {
		PlanResponse response = new PlanResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo addPlan");
		try {
			this.setConnection();
			String query = "INSERT INTO cicte.cic_plan \n" +
					"(pl_nombre, pl_lugar, pl_ilusion, pl_grafico_xy, pl_grafico_xz)\n" +
					"VALUES(?, ?, ?, ?, ?);";
			PreparedStatement value = conn.prepareStatement(query);
			value.setString(1, request.getNombre());
			value.setString(2, request.getLugar());
			value.setInt(3, request.getIlusion().getCodigo());
			value.setBlob(4, request.getGraficoXY());
			value.setBlob(5, request.getGraficoXZ());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("012");
			messageResponse.setMessage("Error al insertar el Plan");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	public PlanResponse getPlan() {
		PlanResponse response = new PlanResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getPlan");
		try {
			this.setConnection();
			List<Plan> planList = new ArrayList<Plan>();
			String query = "SELECT idcic_plan, pl_nombre, pl_lugar,\n" + 
					"							(select il.il_nombre from cicte.cic_ilusion il \n" + 
					"						    where pl.pl_ilusion = il.idcic_ilusion ) as pl_il_nombre, si.si_codigo\n" + 
					"						    FROM cicte.cic_plan pl, cicte.cic_simulador si, cicte.cic_ilusion il \n" + 
					"						    WHERE si.si_codigo = il.il_simulador\n" + 
					"                            AND il.idcic_ilusion = pl.pl_ilusion;";
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				Plan plan = new Plan();
				plan.setCodigo(rs.getInt("idcic_plan"));
				plan.setNombre(rs.getString("pl_nombre"));
				plan.setLugar(rs.getString("pl_lugar"));
				plan.setIlusion(rs.getString("pl_il_nombre"));
				plan.setSimulador(rs.getString("si_codigo"));
				planList.add(plan);
			}
			response.setPlan((Plan[]) planList.toArray(new Plan[planList.size()]));

			if (null != response.getPlan() && response.getPlan().length > 0)
				response.setSuccess(true);
			else {
				messageResponse.setCode("010");
				messageResponse.setMessage("No se encuetran los Planes");
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

	public PlanResponse updatePlan(PlanRequest request) {
		PlanResponse response = new PlanResponse();
		Message messageResponse = new Message();
		
		logger.info("Iniciando metodo updatePlan");
		try {
			this.setConnection();
			String query = "UPDATE cicte.cic_plan \n" +
					       "SET pl_nombre = ?, pl_ubicacion = ? \n"+
					       "WHERE idcic_plan = ?";
			PreparedStatement value = conn.prepareStatement(query);
			value.setString(1, request.getNombre());
			value.setString(2, request.getLugar());
			value.setInt(3, request.getCodigo());
			value.executeUpdate();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("011");
			messageResponse.setMessage("Error en actualizar el Plan");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	public PlanResponse getPlanByName(PlanRequest request) {
		PlanResponse response = new PlanResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getPlanByName");
		try {
			this.setConnection();
			List<Plan> PlanList = new ArrayList<Plan>();
			String query = "SELECT idcic_plan, pl_nombre, pl_lugar, \r\n" + 
					"		(select il.il_nombre from cicte.cic_ilusion il \r\n" + 
					"		 where pl.pl_ilusion = il.idcic_ilusion ) as pl_il_nombre \r\n" + 
					"	 FROM cicte.cic_plan pl \r\n"+
				    "	 WHERE pl.pl_nombre like '%"+request.getNombre()+"%'";
			// create the java statement
			Statement st = conn.createStatement(); 
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				Plan Plan = new Plan();
				Plan.setCodigo(rs.getInt("idcic_plan"));
				Plan.setNombre(rs.getString("pl_nombre"));
				Plan.setLugar(rs.getString("pl_lugar"));
				Plan.setIlusion(rs.getString("pl_il_nombre"));
				PlanList.add(Plan);
			}
			response.setPlan((Plan[]) PlanList.toArray(new Plan[PlanList.size()]));

			if (null != response.getPlan() && response.getPlan().length > 0)
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

	public PlanResponse getPlanById(PlanRequest request) {
		PlanResponse response = new PlanResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo getPlanById");
		try {
			this.setConnection();
			List<Plan> PlanList = new ArrayList<Plan>();
			String query = "SELECT idcic_plan, pl_nombre, pl_lugar, il_simulador,\n" + 
					"(select il.il_nombre from cicte.cic_ilusion il \n" + 
					" where pl.pl_ilusion = il.idcic_ilusion ) as pl_il_nombre \n" + 
					"FROM cicte.cic_plan pl, cicte.cic_ilusion il \n" + 
					"WHERE pl.pl_ilusion = il.idcic_ilusion\n" + 
					"AND pl.pl_ilusion = '"+request.getIlusion().getCodigo()+"'";
			// create the java statement
			Statement st = conn.createStatement(); 
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				Plan Plan = new Plan();
				Plan.setCodigo(rs.getInt("idcic_plan"));
				Plan.setNombre(rs.getString("pl_nombre"));
				Plan.setLugar(rs.getString("pl_lugar"));
				Plan.setIlusion(rs.getString("pl_il_nombre"));
				Plan.setSimulador(rs.getString("il_simulador"));
				PlanList.add(Plan);
			}
			response.setPlan((Plan[]) PlanList.toArray(new Plan[PlanList.size()]));

			if (null != response.getPlan() && response.getPlan().length > 0)
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
	

	public PlanResponse deletePlan(PlanRequest request) {
		PlanResponse response = new PlanResponse();
		Message messageResponse = new Message();
		logger.info("Iniciando metodo deletePlan");
		try {
			this.setConnection();
			String query = "DELETE FROM cicte.cic_plan \n" +					     
					       "WHERE idcic_plan = ?";
			PreparedStatement value = conn.prepareStatement(query);
			value.setInt(1, request.getCodigo());
			value.execute();
			conn.close();
			response.setSuccess(true);
		} catch (SQLException ex) {
			logger.error("Error SQL:\n" + ex);
			messageResponse.setCode("013");
			messageResponse.setMessage("Error en eliminar el Plan");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}
}
