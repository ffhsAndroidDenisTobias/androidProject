package com.example.denisbittante.androidprojektffhs.rest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.example.denisbittante.androidprojektffhs.R;

public class MainActivity extends Activity {



    public final static String apiKey =" diqkOP74wp5UdhBQNKwk2f0SpBY=";

    public final static String apiEndpoint = "http://api.swissunihockey.ch/rest/v1.0/";
    public final static String apiURLClubs= apiEndpoint+ "clubs/?";


    public final static String strikeIronUserName = "username@yourdomain.com";
	public final static String apiURL = "http://ws.strikeiron.com/StrikeIron/EMV6Hygiene/VerifyEmail?";
	public final static String EXTRA_MESSAGE = "com.example.webapitutorial.MESSAGE";
	
	private class emailVerificationResult {
		public String statusNbr;
		public String hygieneResult;	
	}
	
	private class CallAPI extends AsyncTask<String, String, String> {
    	
		
	    @Override
	    protected String doInBackground(String... params) {
	      String urlString=params[0]; // URL to call
	      String resultToDisplay = "";
	      InputStream in = null;
	      emailVerificationResult result = null;
	        
	      // HTTP Get
	      try {
	        URL url = new URL(urlString);
	    	HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
	    	in = new BufferedInputStream(urlConnection.getInputStream());
	      } catch (Exception e ) {
	        System.out.println(e.getMessage());
	    	return e.getMessage();
	      }
	    	   
	       // Parse XML
	       XmlPullParserFactory pullParserFactory;
	       try {
	    	    pullParserFactory = XmlPullParserFactory.newInstance();
	    	    XmlPullParser parser = pullParserFactory.newPullParser();
	    	    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false); 
	    	    parser.setInput(in, null);
	    	    result = parseXML(parser);
	       } catch (XmlPullParserException e) {
	    	   e.printStackTrace();
	       } catch (IOException e) {
	    	   e.printStackTrace();
	       }


	       return resultToDisplay;
   
	    }

	    protected void onPostExecute(String result) {
	        Intent intent = new Intent(getApplicationContext(), ResultActivity.class); 
	   		intent.putExtra(EXTRA_MESSAGE, result);
	    	startActivity(intent);
	    }
	    
	    private emailVerificationResult parseXML( XmlPullParser parser ) throws XmlPullParserException, IOException {
	      int eventType = parser.getEventType();
	      emailVerificationResult result = new emailVerificationResult(); 
	        
	      while( eventType!= XmlPullParser.END_DOCUMENT) {
	      String name = null;
	      switch(eventType)
	      {
	        case XmlPullParser.START_TAG:
	      	  name = parser.getName();
	      	  if( name.equals("Error")) {
	      	    System.out.println("Web API Error!");
	      	  }
	      	  else if ( name.equals("StatusNbr")) {
	      	    result.statusNbr = parser.nextText();
	      	  }
	      	  else if (name.equals("HygieneResult")) {
	      	    result.hygieneResult = parser.nextText();
	      	  }
	      	  break;
	      	case XmlPullParser.END_TAG:
	      	  break;
	      	} // end switch
	      	
	      	eventType = parser.next();	
	      } // end while
	        return result;		
	     }


	} // end CallAPI

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	// This is the method that is called when the submit button is clicked
    public void verifyEmail(View view) {

        if( email != null && !email.isEmpty()) {

            String.format()

 	      String urlString = apiURL + "LicenseInfo.RegisteredUser.UserID=" + strikeIronUserName + "&LicenseInfo.RegisteredUser.Password=" + strikeIronPassword + "&VerifyEmail.Email=" + email + "&VerifyEmail.Timeout=30";
 	      new CallAPI().execute(urlString);	
        }	
 

  
    }

	
}
