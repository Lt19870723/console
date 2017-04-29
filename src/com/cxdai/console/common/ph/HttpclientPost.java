/* ======================================================================
 * Example WSDbfetch (REST) client using HttpClient and HTTP POST
 * 
 * See:
 * http://www.ebi.ac.uk/Tools/webservices/services/dbfetch_rest
 * http://www.ebi.ac.uk/Tools/dbfetch/dbfetch
 * http://www.ebi.ac.uk/Tools/webservices/tutorials/06_programming/java
 * ====================================================================== */
package com.cxdai.console.common.ph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpclientPost {
	/**
	 * Get a web page using HTTP POST.
	 * 
	 * @param urlStr The URL of the page to be retrieved.
	 * @param postData Array of name value pairs describing the data for the
	 *            POST
	 * @return A string containing the page.
	 */
	public static String getHttpUrlPost(String urlStr, NameValuePair[] postData) {
		// Data obtained from service, to be returned
		String retVal = null;
		// Create a client
		HttpClient client = new HttpClient();
		// Create a HTTP POST request
		PostMethod method = new PostMethod(urlStr);
		// Add the POST data to the request
		method.setRequestBody(postData);
		try {
			// Execute the request using the client
			int statusCode = client.executeMethod(method);
			// Handle redirect response (cannot use setFollowRedirects(true)
			// with POST).
			// See http://hc.apache.org/httpclient-legacy/redirects.html
			if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY || statusCode == HttpStatus.SC_TEMPORARY_REDIRECT) {
				// Get the location to go to.
				Header locationHeader = method.getResponseHeader("location");
				if (locationHeader != null) {
					String redirectLocation = locationHeader.getValue();
					// Try request against new location
					method = new PostMethod(redirectLocation);
					method.setRequestBody(postData);
					statusCode = client.executeMethod(method);
				}
			}
			// Check the response status code
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}
			// Get the page data, allowing for character encoding
			BufferedReader bis = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), method.getResponseCharSet()));
			int bufLen = 8 * 1024;
			char[] charBuf = new char[bufLen];
			StringBuffer strBuf = new StringBuffer();
			int count;
			while ((count = bis.read(charBuf)) != -1) {
				strBuf.append(charBuf, 0, count);
			}
			bis.close();
			retVal = strBuf.toString();
		} catch (HttpException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		} finally {
			// Clean-up the connection
			method.releaseConnection();
		}
		// Return the response data
		return retVal;
	}

	/**
	 * <p>
	 * Description:将对象转换为参数集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年12月26日
	 * @param obj
	 * @return
	 * @throws Exception List<NameValuePair>
	 */
	public static List<NameValuePair> getParamsList(Object obj) throws Exception {
		Object o;
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		if (obj == null) {
			return list;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		String name;
		String value;
		for (Field field : fields) {
			name = field.getName();
			o = obj.getClass().getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1)).invoke(obj, null);
			if (o == null)
				continue;
			value = String.valueOf(o);

			list.add(new NameValuePair(name, value));
		}
		return list;
	}

	/**
	 * Execution entry point
	 * 
	 * @param args Command-line arguments
	 * @return Exit status
	 */
	public static void main(String[] args) {
		// Parameters for the dbfetch call
		String dbName = "uniprotkb"; // Database name (e.g. UniProtKB)
		String id = "WAP_RAT"; // Entry identifier, name or accession
		String format = "uniprot"; // Data format

		// Parameter style URL for dbfetch
		String dbfetchUrl = "http://www.ebi.ac.uk/Tools/dbfetch/dbfetch";
		// Construct the POST data for the parameters
		NameValuePair[] postData = { new NameValuePair("db", dbName), // Database
				new NameValuePair("id", id), // Entry identifier(s)
				new NameValuePair("format", format), // Data format
				new NameValuePair("style", "raw") // Result style
		};

		// Get the page and print it.
		System.out.print(getHttpUrlPost("http://127.0.0.1:8080/cxdai_portal/api/tzj/login.html", postData));
	}
	

}