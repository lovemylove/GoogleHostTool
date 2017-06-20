package util.properties;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;



/**
 * <pre>
 * 
 *  N/A Automation
 *  File: PropertyHelper.java
 * 
 *  N/A, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Revision History
 *  Date,		Who,		What;
 *  2014-9-2		Chris		Initial.
 * 
 * </pre>
 */

public class PropertyUtil
{
	// 属性文件的路径
	private static final String profilepath = "config.properties";
	
	private static Properties props = new Properties();

	static
	{
		File file = new File(profilepath);
		if (true)
		{
			try
			{
				file.createNewFile();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try
		{
			props.load(new FileInputStream(profilepath));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	/**
	 * TODO.
	 *
	 * @param key
	 * @return 
	 */
	public static String getKeyValue(String key)
	{
		return props.getProperty(key);
	}


	/**
	 * TODO.
	 *
	 * @param filePath
	 * @param key
	 * @return 
	 */
	public String readValue(String filePath, String key)
	{
		Properties props = new Properties();
		try
		{
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			props.load(in);
			String value = props.getProperty(key);
			return value;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * TODO.
	 *
	 * @param keyname
	 * @param keyvalue 
	 */
	public void writeProperties(String keyname, String keyvalue)
	{

		try
		{
			OutputStream fos = new FileOutputStream(profilepath);
			props.setProperty(keyname, keyvalue);
			props.store(fos, "Update '" + keyname + "' value");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	/**
	 * TODO.
	 *
	 * @param keyname
	 * @param keyvalue 
	 */
	public void updateProperties(String keyname, String keyvalue)
	{
		try
		{
			props.load(new FileInputStream(profilepath));
			OutputStream fos = new FileOutputStream(profilepath);
			props.setProperty(keyname, keyvalue);
			props.store(fos, "Update '" + keyname + "' value");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
/*
 * $Log: $
 */