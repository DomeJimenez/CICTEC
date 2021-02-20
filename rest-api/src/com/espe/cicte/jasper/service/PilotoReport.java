package com.espe.cicte.jasper.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.espe.cicte.jasper.service.databean.PilotosDATABEAN;
import com.espe.cicte.ws.db.PilotoDB;
import com.espe.cicte.ws.dto.Piloto;
import com.espe.cicte.ws.dto.PilotoResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PilotoReport {

	private static PilotoDB pilotoDB = new PilotoDB();
	private static Logger logger = org.apache.log4j.Logger.getLogger(PilotoReport.class);
	
	public void reportePilotos() throws JRException, IOException {
		// TODO Auto-generated method stub
		 // Compile jrxml file.
		   logger.info("Ingesando al reporte Pilotos");
	       JasperReport jasperReport = JasperCompileManager
	               .compileReport("C:/CICTE/JASPER/pilotos.jrxml");
	 
	       // Parameters for report
	       Map<String, Object> parameters = new HashMap<String, Object>();
	 
	       // DataSource
	       // This is simple example, no database.
	       // then using empty datasource.
	       //JRDataSource dataSource = new JREmptyDataSource();
	      
	       PilotoResponse response = new PilotoResponse();

		   response = pilotoDB.getPilotos();

		   List<Piloto> pilotoList = new ArrayList<Piloto>(Arrays.asList(response.getPiloto()));
		   
		   List<PilotosDATABEAN> lists = new ArrayList<>();
		   PilotosDATABEAN piloto = new PilotosDATABEAN();
		   piloto.setPilotos(pilotoList);
	       lists.add(piloto);
		   
		   if (response.getPiloto() != null) {
	       JRDataSource dataSource = new JRBeanCollectionDataSource(lists,true);
	       
	       JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	 
	    
	       // Make sure the output directory exists.
	       File outDir = new File("C:/CICTE/PDF");
	       outDir.mkdirs();
	 
	       // Export to PDF.
	       JasperExportManager.exportReportToPdfFile(jasperPrint,
	               "C:/CICTE/PDF/pilotos.pdf");
		   }
	        
	       System.out.println("Done Pilotos!");
	}
}
