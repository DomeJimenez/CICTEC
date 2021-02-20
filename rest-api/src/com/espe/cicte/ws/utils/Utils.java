package com.espe.cicte.ws.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.SecureRandom;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Utils {
	
	private static final char[] CHARSET_AZ_09 = "0123456789".toCharArray();

	
	private static Utils instance;

	public static Utils getInstance() {
		if (instance == null)
			instance = new Utils();
		return instance;
	}

	public static void setInstance(Utils instance) {
		Utils.instance = instance;
	}

	public Utils() {
		// Singleton Class for Util instance
	}
	
	public String getBlobAsString(Blob blob)
	{
	    StringBuffer result = new StringBuffer();
	    
	    if ( blob != null ) 
	    {
	        int read = 0;
	        Reader reader = null;
	        char[] buffer = new char[1024];
	                                
	        try
	        {
	            reader = new InputStreamReader(blob.getBinaryStream(), "UTF-8");

	            while((read = reader.read(buffer)) != -1) 
	            {
	                result.append(buffer, 0, read);
	            }
	        }
	        catch(SQLException ex)
	        {
	            throw new RuntimeException("Unable to read blob data.", ex);
	        }
	        catch(IOException ex)
	        {
	            throw new RuntimeException("Unable to read blob data.", ex);
	        }
	        finally
	        {
	            try { if(reader != null) reader.close(); } catch(Exception ex) {};
	        }
	    }
	    
	    return result.toString();
	}
	
	public String getDateTime(){
		
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentTime = sdf.format(dt);
		
		return currentTime;
	}
	
    public String[] muestraContenido(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            System.out.println(cadena);
        }
        b.close();
        
        return null;
    }
    
    public String randomString(int length) {
		char[] characterSet = CHARSET_AZ_09;
		Random random = new SecureRandom();
		char[] result = new char[length];
		for (int i = 0; i < result.length; i++) {
			int randomCharIndex = random.nextInt(characterSet.length);
				result[i] = characterSet[randomCharIndex];
		}
			return new String(result);
	}
}
