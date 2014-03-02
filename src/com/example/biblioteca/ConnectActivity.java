package com.example.biblioteca;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.xmlpull.v1.XmlPullParserException;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class ConnectActivity extends Activity {

	private static final String URL = "https://www.dropbox.com/login";

	private String emailToPost;
	private String passwordToPost;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_connect);
		// Show the Up button in the action bar.
		// setupActionBar();

		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		// Get the email and password from the intent
		Intent intent = getIntent();
		emailToPost = intent.getStringExtra(MainActivity.EXTRA_EMAIL);
		passwordToPost = intent.getStringExtra(MainActivity.EXTRA_PASSWORD);

		new DownloadWebpageTask().execute(URL);

		return;
	}

	/* Class to execute the download of the web site in the background */
	private class DownloadWebpageTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... urls) {
			try {
				return loadWebpageFromNetwork(urls[0]);
			} catch (IOException e) {
				return getResources().getString(R.string.connection_error);
			} catch (XmlPullParserException e) {
				return getResources().getString(R.string.xml_error);
			}
		}

		@Override
		protected void onPostExecute(String result) {
			setContentView(R.layout.activity_connect);
			// Displays the HTML string in the UI via a WebView
			WebView myWebView = (WebView) findViewById(R.id.webview);
			myWebView.loadData(result, "text/html", null);
		}
	}

	/* Method where we call the method to load the web site and where we extract the source code of it  */
	/* INPUT: URL of the site we want to load (String) */
	/* OUTPUT: Source code of the site (String) */
	private String loadWebpageFromNetwork(String urlString) throws XmlPullParserException, IOException {
		int len = 100000;
		InputStream stream = null;

		try {
			stream = downloadUrl(urlString);

			Reader reader = null;
			reader = new InputStreamReader(stream, "UTF-8");
			char[] buffer = new char[len];
			reader.read(buffer);
			String contentAsString = new String(buffer);

			return contentAsString;

			// Makes sure that the InputStream is closed after the app is
			// finished using it.
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}

	/* Method to download the web site given in the urlString argument */
	/* INPUT: URL of the site we want to load (String) */
	/* OUTPUT: Stream of the site (InputStream) */
	private InputStream downloadUrl(String urlString) throws IOException {
		/* GET main Dropbox site */
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(10000 /* milliseconds */);
		conn.setConnectTimeout(15000 /* milliseconds */);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		// Starts the query
		conn.connect();

		/* Code to extract cookie to include in the post */
		//Map<String, List<String>> headerFields = conn.getHeaderFields();
		//List<String> cookiesHeader = headerFields.get("Set-Cookie");
		//String cookie = cookiesHeader.get(2);
		// Pattern p = Pattern.compile("t=(.*?);");
		// Matcher m = p.matcher(cookie);
		// String cookieT = m.group(); // Access a submatch group; String can't
		// do this.

		/* POST user credentials in Dropbox site */
		URL url2 = new URL(urlString);
		HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
		conn2.setReadTimeout(10000 /* milliseconds */);
		conn2.setConnectTimeout(15000 /* milliseconds */);
		conn2.setRequestMethod("POST");
		conn2.setDoInput(true);
		conn2.setDoOutput(true);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("t", ""));
		params.add(new BasicNameValuePair("display", "desktop"));
		params.add(new BasicNameValuePair("login_email",emailToPost));
		params.add(new BasicNameValuePair("login_password", passwordToPost));
		params.add(new BasicNameValuePair("login_submit", "1"));
		params.add(new BasicNameValuePair("login_submit_dummy", "Sign in"));

		OutputStream os = conn2.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
		writer.write(getQuery(params));
		writer.flush();
		writer.close();
		os.close();

		conn2.connect();

		InputStream stream2 = conn.getInputStream();

		return stream2;
	}

	private String getQuery(List<NameValuePair> params)
			throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		boolean first = true;

		for (NameValuePair pair : params) {
			if (first)
				first = false;
			else
				result.append("&");

			result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
		}

		return result.toString();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.connect, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
