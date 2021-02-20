package com.espe.cicte.jasper.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.espe.cicte.jasper.service.databean.TestDATABEAN;
import com.espe.cicte.jasper.service.dto.ScoreDTO;
import com.espe.cicte.ws.db.CatalogoDB;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PdfFromXmlFile {
	private static Logger logger = org.apache.log4j.Logger.getLogger(PdfFromXmlFile.class);
	public static void main(String[] args) throws JRException, IOException {
		// TODO Auto-generated method stub
		 // Compile jrxml file.
	       JasperReport jasperReport = JasperCompileManager
	               .compileReport("C:/CICTE/JASPER/test.jrxml");
	 
	       // Parameters for report
	       Map<String, Object> parameters = new HashMap<String, Object>();
	 
	       // DataSource
	       // This is simple example, no database.
	       // then using empty datasource.
	       //JRDataSource dataSource = new JREmptyDataSource();
	       ScoreDTO s = new ScoreDTO();
	       s.setScore(99);
	       ScoreDTO s1 = new ScoreDTO();
	       s1.setScore(156);
	       ScoreDTO s2 = new ScoreDTO();
	       s2.setScore(99999);
	       
	       List<ScoreDTO> sl1 = new ArrayList<>();
	       sl1.add(s);
	       sl1.add(s1);
	       List<ScoreDTO> sl2 = new ArrayList<>();
	       sl2.add(s2);
	       List<TestDATABEAN> lists = new ArrayList<>();
	       TestDATABEAN t = new TestDATABEAN();
	       t.setNombre("CARLOS");
	       t.setScores(sl1);
	       TestDATABEAN t1 = new TestDATABEAN();
	       t1.setNombre("SERGIO");
	       t1.setScores(sl2);
	       lists.add(t);
	       lists.add(t1);
	       JRDataSource dataSource = new JRBeanCollectionDataSource(lists,true);
	       
	       JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	 
	    
	       // Make sure the output directory exists.
	       File outDir = new File("C:/CICTE/PDF");
	       outDir.mkdirs();
	 
	       // Export to PDF.
	       JasperExportManager.exportReportToPdfFile(jasperPrint,
	               "C:/CICTE/PDF/test.pdf");
	        
	       System.out.println("Done!");
	}

}
