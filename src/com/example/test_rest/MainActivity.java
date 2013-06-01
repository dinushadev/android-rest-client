package com.example.test_rest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView helloText;
	String url="http://openexchangerates.org/currencies.json";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		helloText=(TextView) findViewById(R.id.textView1);
		
		new Task(this).execute(url);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	private  class Task extends AsyncTask<String, Void, String> {

		MainActivity activity;
		ProgressDialog pd;

		public Task(MainActivity activity) {
			this.activity = activity;

		}

		@Override
		protected String doInBackground(String... url) {

			HttpClient client = new DefaultHttpClient();

			HttpGet get = new HttpGet(url[0]);

			ResponseHandler<String> responseHandler = new BasicResponseHandler();

			String response = null;

			try {
				response = client.execute(get, responseHandler);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return response;
		}

		@Override
		protected void onPostExecute(String result) {
			
			pd.dismiss();
			helloText.setText(result);
		}

		@Override
		protected void onPreExecute() {

			pd = ProgressDialog.show(this.activity, "Currencies",
					"Please wait, getting currencies..");

		}

	}


}
