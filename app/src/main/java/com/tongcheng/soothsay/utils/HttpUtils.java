/**
 * HttpURLConnection 连接 
 */
package com.tongcheng.soothsay.utils;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class HttpUtils {
	
	//是否需要cookie
	private boolean isCheckCookie = true;
	
	//来源
	private String Referer = "";
	
	//超时设置,毫秒
	private int timeout = 20 * 1000;
	
	//UA默认可能为Java/1.7.0_25
	private String UA = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)";	
	
	//设置Content-Type :  json提交有问题的话，尝试"application/json"
	private String content_type = "application/x-www-form-urlencoded";
	
	private Map<String, String> cookies = new HashMap<String, String>();
	//https,只需要操作一次证书认证
	private boolean isCheckHttps = true;

	/**
	 * 下载相关设置
	 */
	// 是否按原网站结构下载
	private boolean iskeepdir = false;
	//下载文件名
	private String filename;
	//自定义后缀
	private String suffix;
	// 最小下载限制0M
	private int minSize = 0;
	// 最大下载文件50M
	private int maxSize = 1024 * 1024 * 50;
	//是否打印下载进度
	private boolean downlog = true;
	
	/**
	 * 获取网页内容
	 * 
	 * @param url
	 *            远程地址
	 * @param encode
	 *            编码
	 */
	public String get(String url, String encode){
		return conn(url,"",encode);
	}

	public String get(String url){
		return get(url, "utf-8");
	}

	/**
	 * POST
	 * @param action
	 * @param params 参数map
	 * @param encode 编码
	 * @return 响应文本
	 */
	public String post(String action, List<String[]> params, String encode) {
		String r = null;
		
		// 构建请求参数
		StringBuffer sb = new StringBuffer();
		String pstr = null;
		if (params.size() > 0) {
			for (String[] e : params) {
				sb.append(e[0]);
				sb.append("=");
				sb.append(e[1]);
				sb.append("&");
			}
			pstr = sb.substring(0, sb.length() - 1);
		}
		r = conn(action,pstr,encode);
        return r;
	}
	public String post(String action, List<String[]> params) {
		return post(action, params,"utf-8");
	}
	
	
	/**
	 * POST
	 * @param action
	 * @param params 参数map
	 * @param encode 编码
	 * @return 响应文本
	 */
	public String post(String action, Map<String, String> params, String encode) {
		String r = null;
		
		// 构建请求参数
		StringBuffer sb = new StringBuffer();
		String pstr = null;
		if (params != null) {
			for (Entry<String, String> e : params.entrySet()) {
				sb.append(e.getKey());
				sb.append("=");
				sb.append(e.getValue());
				sb.append("&");
			}
			pstr = sb.substring(0, sb.length() - 1);
		}
		r = conn(action,pstr,encode);
        return r;
	}
	/**
	 * POST
	 * @param action
	 * @param params 参数map
	 * @return 响应文本
	 */
	public String post(String action, Map<String, String> params) {
		return post(action, params,"utf-8");
	}


	/**
	 * post > json,xml等数据流
	 * @param url 远程地址
	 * @paramjson
	 * @return
	 */
	public String post(String url,String data) {
		return post(url,data,"utf-8");
	}
	public String post(String url,String data,String encode){
		return conn(url,data,encode);
	}
	
	private String conn(String url,String data,String encode){
		String  r = null;
		//https处理
		if(url.startsWith("https") && isCheckHttps) {
			trustEveryone();
			isCheckHttps = false;
		}
		HttpURLConnection conn = null;
		try {
			//1.创建连接
			URL u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
			
			
			//设置来源
			if(Referer.length() == 0) Referer = url;
			conn.setRequestProperty("referer", Referer);
			Referer = url;
			
			// 设置连接超时时间
			conn.setConnectTimeout(timeout);
			
			// User-Agent
			conn.setRequestProperty("User-Agent",UA);
			
	        //post.
	        if(data != null && data.length() > 0) {
	        	// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在   
	        	 // http正文内，因此需要设为true, 默认情况下是false;   
	        	conn.setDoOutput(true);
	        	conn.setRequestMethod("POST");
		        conn.setRequestProperty("Content-Type",content_type);
		        // 不使用缓存
		         conn.setUseCaches(false);
	        }
	        
	         //跳转
	         conn.setInstanceFollowRedirects(true);
	         
	         //cookie
	         if(isCheckCookie){
	        	 String cookie = getCookie();
	        	 if(cookie != null && cookie.length() > 0)
	        		 conn.setRequestProperty("Cookie", cookie);
	         }
	         
	         conn.connect();
	         
	       //2.post.
	         if(data != null && data.length() > 0) {
	            OutputStreamWriter osw = new OutputStreamWriter(
	        			conn.getOutputStream(), encode);
	        			osw.write(data.toString());
	        			osw.flush();
	        			osw.close();
	        		//或者字节提交
	        			//DataOutputStream out = new DataOutputStream(conn.getOutputStream());
	        			// out.write(data.getBytes());
	        			//out.flush();
	        			//out.close();
	         }			
	        			
	        //3.获取返回内容
			StringBuffer buffer = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(
			conn.getInputStream(), encode));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\r\n");
			}
			br.close();
			if(buffer !=null)
			r = buffer.toString().trim();	
			
			//cookie处理  
			setCookie(conn);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(conn != null) conn.disconnect();
		}
		return r;
	}
	
	//cookie
	private void setCookie(HttpURLConnection conn){
		if(!isCheckCookie) return;
		try{
			Map<String,List<String>> map=conn.getHeaderFields();
//			System.out.println(map);
			List<String> cookie = map.get("Set-Cookie");
			if(cookie != null)
			for(String c:cookie){
				String[] cs = c.split(";");
				for(String kv:cs){
					String[] m = kv.split("=");
					if(m.length == 2){
						cookies.put(m[0].trim(), m[1].trim());
//						System.out.println("key="+m[0].trim() + ",value=" + m[1].trim());
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private String getCookie(){
		String r = "";
//		System.out.println(cookies);
		if(!isCheckCookie || cookies.size() == 0) return r;
		try{
			for(String key:cookies.keySet()){
				r += key + "=" + cookies.get(key) + "; ";
			}
			r = r.substring(0,r.length()-1);
		}catch(Exception e){
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 文件下载
	 * 
	 * @param downurl
	 *            下载的文件
	 * @param baseurl
	 *            保存的路径
	 * @return 返回路径和文件名
	 */
	public String download(String downurl, String baseurl){
		
		// 全路径和文件名，包含baseurl
		String savefilename = resetFilename(downurl,baseurl);
		if(savefilename == null){
			System.out.println("之前可能已下载过，忽略:" + downurl);
			return null;
		}
		try {
			URL url = new URL(downurl);
			//下载操作
			HttpURLConnection uc = (HttpURLConnection)url.openConnection();

			// 请求头设置，cookie保持，唯一跟JS这个类有关的代码
			uc.setRequestProperty("User-Agent", UA);
			if(Referer.length() == 0) Referer = downurl;
			uc.setRequestProperty("referer", Referer);
			uc.setConnectTimeout(timeout);
			uc.setReadTimeout(timeout);
			//uc.setRequestProperty("Accept-Encoding", "identity");
			//cookie
	         if(isCheckCookie){
	        	 String cookie = getCookie();
	        	 if(cookie != null && cookie.length() > 0)
	        		 uc.setRequestProperty("Cookie", cookie);
	         }
			
//			System.out.println("cookie2="+uc.getRequestProperty("Cookie"));
			int filesize = uc.getContentLength();
			System.out.println("filesize="+filesize+",downurl="+downurl);
			if (filesize > 0 && (filesize > maxSize || filesize <= minSize)) {
				System.out.println("文件大小限制 ...MaxSize = " + maxSize + ",MinSize="+minSize+",FileSize="+filesize);
				return null;
			}
			int fsize = 0;// 已下载多少
			int lasta = 0;
			InputStream ui = uc.getInputStream();
			if(filesize == -1) 
				filesize = ui.available();
//			uc.getHeaderFields();
			byte[] b = new byte[1024];
			int len;
			//文件名设置
//			System.out.println(uc.getHeaderField("Content-Disposition"));
			FileOutputStream fos = new FileOutputStream(savefilename);
//			System.out.println("download,filename=" + savefilename + ",size="
//					+ filesize);
			while ((len = ui.read(b)) != -1) {
				fos.write(b, 0, len);
				// 打印下载进度
				if (downlog) {
					fsize = fsize + len;
					if(filesize != -1){
						int lastb = (int) ((fsize * 1.0) / filesize * 100);
						if (lastb > lasta) {
							System.out.println(lastb + "%,filename=" + savefilename
									+ ",size=" + filesize);
							lasta = lastb;
						}
					}else{
						if (fsize > maxSize){
							//没办法获取到文件大小时，临时处理
							fos.close();
							ui.close();
							return null;
						}
					}
					
				}

			}
			fos.close();
			ui.close();
			System.out.println("------ok,download,filename=" + savefilename + ",size="
					+ filesize);
		} catch (IOException e) {
			e.printStackTrace();
			return  null;
		}
		return savefilename.replace(baseurl, "");
	}
	/**
	 * JAVA7下载
	 * @param downurl
	 * @param baseurl
	 * @return
	 */
//public String downloadForFiles(String downurl, String baseurl){
//		
//		// 全路径和文件名，包含baseurl
//		String savefilename = resetFilename(downurl,baseurl);
//		if(savefilename == null){
//			System.out.println("之前可能已下载过，忽略:" + downurl);
//			return null;
//		}
//		try {
//			Files.copy(new URL(downurl).openStream(), Paths.get(savefilename));
//			System.out.println("------ok,download,filename=" + savefilename);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return  null;
//		}
//		return savefilename.replace(baseurl, "");
//	}

	private String resetFilename(String downurl, String baseurl) {
		String savefilename = null;// 全路径和文件名，包含baseurl
		String dirname = null; // 路径和文件名
		
		if(this.iskeepdir){
			URL url = null;
			try {
				url = new URL(downurl);
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return null;
			}
			
			//按网站结构生成文件名
			String basedir;
			String host = url.getHost();
			String path = url.getPath();
			int port = url.getPort();
			if (port == -1) {
				basedir = host;
			} else {
				basedir = host + "_" + port;
			}
			dirname = basedir + path;
			String bau = "";
			//路径是否包括/标志，没有则加上
			if (!baseurl.endsWith("/") && !baseurl.endsWith("\\")) {
				bau = "/";
			}
			savefilename = baseurl + bau + dirname;
			
			File f = new File(savefilename);
			if(f.exists()){
				savefilename = null;
			}else{
				// 路径没有则自动创建
				if (!f.getParentFile().exists()) {
					boolean p = f.getParentFile().mkdirs();
					if (!p) {
						return null;
					}
				}
			}
		}else{
			//自动生成文件名
			// 路径，如2013/0711/
			String basedir = new SimpleDateFormat("yyyy/MMdd/")
					.format(new Date());
			
			// 文件名，如165339528592
			String basename = "";
			if (filename != null && filename.length() > 0){
				basename = filename;
			}else{
				basename = new SimpleDateFormat("HHmmss")
				.format(new Date()) + new Random().nextInt(999999);
			}
				
			// 后缀，如.zip
			if (suffix == null) {
				suffix = getSuffix(downurl);
			}
			dirname = basedir + basename + suffix;
				String bau = "";
				//路径是否包括/标志，没有则加上
				if (!baseurl.endsWith("/") && !baseurl.endsWith("\\")) {
					bau = "/";
				}
				savefilename = baseurl + bau + dirname;
				File f = new File(savefilename);
				StringBuffer sbuf=null;
				//文件是否以存在
				int n=2;
				if(f.exists()){
					//文件名已经存在，重新命名
					sbuf=new StringBuffer(savefilename);
					int idx=sbuf.lastIndexOf(".");
					sbuf.insert(idx, "_"+n++);
					f=new File(sbuf.toString());
				}
				if(sbuf!=null){
					savefilename=sbuf.toString();
				}
				// 路径没有则自动创建
				if (!f.getParentFile().exists()) {
					boolean p = f.getParentFile().mkdirs();
					if (!p) {
						return null;
					}
				}
		}
		
		return savefilename;
	}
	
	/**
	 * 获取后缀名
	 * @param url
	 * @return
	 */
	public static String getSuffix(String url){
		String suffix = "";
		try {
			URL u = new URL(url);
			String path = u.getPath();
			int i = path.lastIndexOf(".");
			if(i > -1){
				suffix = path.substring(i);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return suffix;
	}
	
	/**
	 * 获取域名
	 * @param url
	 * @return
	 */
	public static String getDomain(String url){
		try {
			URL u = new URL(url);
			String host = u.getHost();
			int i = host.indexOf(".");
			if(i > -1){
				int i2 = host.lastIndexOf(".");
				if(i != i2){
					host = host.substring(i + 1);
				}
			}
			return host;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return "";
		
	}
	//HTTPS处理
	 public static void trustEveryone() {
	        try {
	            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
	                public boolean verify(String hostname, SSLSession session) {
	                    return true;
	                }  
	            });
	
	            SSLContext context = SSLContext.getInstance("TLS");
	            context.init(null, new X509TrustManager[] { new X509TrustManager() {
	                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	                }
	  
	                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	                }
	  
	                public X509Certificate[] getAcceptedIssuers() {
	                    return new X509Certificate[0];
	                }
	            } }, new SecureRandom());
	            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
	        } catch (Exception e) {
	            // e.printStackTrace();  
	        }
	    } 
	 
	 public boolean isCheckHttps() {
			return isCheckHttps;
		}

	public void setCheckHttps(boolean isCheckHttps) {
		this.isCheckHttps = isCheckHttps;
	}
	 public boolean isCheckCookie() {
			return isCheckCookie;
		}

		public void setCheckCookie(boolean isCheckCookie) {
			this.isCheckCookie = isCheckCookie;
		}

		public Map<String, String> getCookies() {
			return cookies;
		}

		public void setCookies(Map<String, String> cookies) {
			this.cookies = cookies;
		}

		public String getContent_type() {
			return content_type;
		}

		public void setContent_type(String content_type) {
			this.content_type = content_type;
		}

		public String getReferer() {
			return Referer;
		}

		public void setReferer(String referer) {
			Referer = referer;
		}

		public int getTimeout() {
			return timeout;
		}

		public void setTimeout(int timeout) {
			this.timeout = timeout;
		}

		public String getUA() {
			return UA;
		}

		public void setUA(String uA) {
			UA = uA;
		}

		public boolean isIskeepdir() {
			return iskeepdir;
		}

		public void setIskeepdir(boolean iskeepdir) {
			this.iskeepdir = iskeepdir;
		}

		public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}

		public String getSuffix() {
			return suffix;
		}

		public void setSuffix(String suffix) {
			this.suffix = suffix;
		}

		public int getMinSize() {
			return minSize;
		}

		public void setMinSize(int minSize) {
			this.minSize = minSize;
		}

		public int getMaxSize() {
			return maxSize;
		}

		public void setMaxSize(int maxSize) {
			this.maxSize = maxSize;
		}

		public boolean isDownlog() {
			return downlog;
		}

		public void setDownlog(boolean downlog) {
			this.downlog = downlog;
		}
		
	
	/**
	 * 提交上传表单
	 * @param url
	 * @paramtextMap 文本类字段,key为字段名,value为值
	 * @paramfileMap file字段,key为字段名,value为文件路径
	 * @paramencode 编码
	 * @return 结果字符串
	 */
	public  String postMultipart(String url, List<String[]> textList, List<String[]> fileList) {
		return postMultipart(url,textList,fileList,"utf-8");
	}	
	public  String postMultipart(String url, List<String[]> textList) {
		return postMultipart(url,textList,null,"utf-8");
	}
	public  String postMultipart(String url, List<String[]> textList, List<String[]> fileList,String encode) {
		String  r = null;
		//https处理
		if(url.startsWith("https") && isCheckHttps) {
			trustEveryone();
			isCheckHttps = false;
		}
		HttpURLConnection conn = null;
		try {
			//1.创建连接
			URL u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
			
			
			//设置来源
			if(Referer.length() == 0) Referer = url;
			conn.setRequestProperty("referer", Referer);
			Referer = url;
			
			// 设置连接超时时间
			conn.setConnectTimeout(timeout);
			
			// User-Agent
			conn.setRequestProperty("User-Agent",UA);
			
	        //post.
        	// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在   
        	 // http正文内，因此需要设为true, 默认情况下是false;   
        	conn.setDoOutput(true);
        	conn.setRequestMethod("POST");
        	conn.setUseCaches(false);
        	
        	String boundary = "------" + System.currentTimeMillis();
	        conn.setRequestProperty("Content-Type","multipart/form-data;boundary=" + boundary);
	         
	         //cookie
	         if(isCheckCookie){
	        	 String cookie = getCookie();
	        	 if(cookie != null && cookie.length() > 0)
	        		 conn.setRequestProperty("Cookie", cookie);
	         }
	         
	         DataOutputStream out = new DataOutputStream(conn.getOutputStream());




	       //2.普通字段
	         if(textList != null && textList.size() > 0){
	        	 StringBuffer s = new StringBuffer();
	        	 for (String[] param:textList) {
	        		    String name = param[0];
						String value = param[1];
						if (value == null || value.length() == 0) {
							continue;
						}
						s.append("\r\n").append("--").append(boundary).append("\r\n");
						s.append("Content-Disposition: form-data; name=\"" + name + "\"\r\n\r\n");
						s.append(value);
		         }
	        	 out.write(s.toString().getBytes());
	         }
	         
	         
	       //3.文件
	     if(fileList != null && fileList.size() > 0)
         for (String[] file:fileList) {
         	StringBuilder s = new StringBuilder();  
         	s.append("\r\n").append("--").append(boundary).append("\r\n");  
         	String field = file[0];
            String filepath = file[1];
            if(filepath == null || filepath.length() == 0) continue; 
             s.append("Content-Disposition:  form-data;  name=\"" + field+ "\";  filename=\"" + filepath + "\"\r\n");
             s.append("Content-Type:application/octet-stream\r\n\r\n");  
             out.write(s.toString().getBytes());
             
             // 读取文件
             FileInputStream fis = new FileInputStream(filepath);
             byte[] buffer = new byte[8192]; // 8k
             int count = 0;
             while ((count = fis.read(buffer)) != -1){
             	out.write(buffer, 0, count);
             }
             fis.close();
         }      
	         
             //结束
             byte[] endData = ("\r\n--" + boundary + "--\r\n").getBytes();  
             out.write(endData);  
             out.flush();  
             out.close();
	        			
	        //3.获取返回内容
			StringBuffer buffer = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(
			conn.getInputStream(), encode));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\r\n");
			}
			br.close();
			if(buffer !=null)
			r = buffer.toString().trim();	
    			
    		//cookie处理  
    		setCookie(conn);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(conn != null) conn.disconnect();
		}
		return r;
	}
	 
	/*public static void main(String[] args) throws IOException {
		HttpUploadUtils js = new HttpUploadUtils();
		
		//1.get
//		String url = "http://www.baidu.com/";
//		String str = js.get(url);
//		System.out.println(str);
		
		//2.post 参数
		*//**
		 back.jsp
		response.setDateHeader("Expires", 0);
		request.setCharacterEncoding("utf-8");
		String age = request.getParameter("age");
		System.out.println("age="+age+"==");
		out.println("abc");
		out.println("2222");
		 *//*
//		String url = "http://www.test.com:8080/a/posts";
//		List<String[]> textList = new ArrayList<String[]>();
//		String[] param = {"names","jack"};
//		String[] param2 = {"names","jack2"};
//		textList.add(param);
//		textList.add(param2);
//		String r = js.post(url,textList);
//		System.out.println("r="+r+"---");
		
		//3.post原数据，如json,xml
		*//**
		 back.jsp
		response.setDateHeader("Expires", 0);
		//获取信息
		int l = request.getContentLength();
		InputStream is = request.getInputStream();
		byte[] b = new byte[l];
		is.read(b);
		String s = new String(b, "utf-8");
		System.out.println("ssss====="+s);
		out.println("abc");
		out.println("2222");
		 *//*
//		String url = "http://test.test.com:8080/back.jsp";
//		String json = "{\"age\":\"你1\"}";
//		String r = js.post(url,json);
//		System.out.println("r="+r+"---");
		
		//4.下载
//		String url = "http://test.test.com:8080/test/test.jpg";
//		String baseUrl = "f:/down/";
//		js.download(url, baseUrl);
		
		//5.HTTPS
//		String url = "https://sls.cdb.com.cn/";
//		String doc = js.get(url);
//		System.out.println(doc);
		
		//6.提交表单+上传文件
		//action
//		String url = "http://www.test.com:8080/app/user/topic_post";
		String url = "http://www.test.com:8080/test/post.jsp?name=abc";
		//普通字段
		List<String[]> textList = new ArrayList<String[]>();
		String[] param = {"named","0b42bb6c2ffe4509a42c7926740c234a"};
		textList.add(param);
		List<String[]> fileList = new ArrayList<String[]>();
        String result = js.postMultipart(url, textList, fileList);
//        String result = js.post(url, textList);
        System.out.println(result);
	}*/
}
