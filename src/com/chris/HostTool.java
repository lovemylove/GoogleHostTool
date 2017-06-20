package com.chris;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HostTool {

	public static String getNewHostText(String url) {
		StringBuilder result = new StringBuilder();
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line+"\n");
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return result.toString();
	}
	public static void writeHost(String hostStr,String savePath) throws IOException
	{
		File file=new File(savePath);
		BufferedWriter output=new BufferedWriter(new FileWriter(file));
		output.write(hostStr);
	}
	public static String getMySettingHost(String mySettingHostPath) throws IOException {
		
		BufferedReader reader=new BufferedReader(new InputStreamReader(URLClassLoader.getSystemResourceAsStream((mySettingHostPath))));
		
		StringBuilder result=new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			result.append(line+"\n");
		}
		return result.toString();
	}
	public static void main(String[] args) {
		try {
			String savePath="/Users/chris-cai/Documents/hosts";
			String hostUrl="https://raw.githubusercontent.com/racaljk/hosts/master/hosts";
			String mySettingHostPath="mySettingHost";
			String mySettingHost=getMySettingHost(mySettingHostPath);
			writeHost(mySettingHost+"\n"+getNewHostText(hostUrl),savePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
