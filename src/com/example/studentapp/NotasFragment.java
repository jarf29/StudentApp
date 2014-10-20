package com.example.studentapp;



import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class NotasFragment  extends Fragment  {
	
	private TextView textView5;
	private EditText editText1;
	private EditText editText2;
	private EditText editText3;
	private EditText editText4;
	private EditText editText5;
	private EditText editText6;
	private EditText editText7;
	private EditText editText8;
	private EditText editText9;
	private EditText editText10;
	private EditText editText11;
	private EditText editText12;
	private EditText editText13;
	private EditText editText14;
	private EditText editText15;
	private EditText editText16;
	private EditText editText17;
	private EditText editText18;
	private Spinner spinner_creditos_en_notas;
	private Spinner spinner_examenes_en_notas;
	
	public String[] numeroCreditos={"1","2","3","4"};
	public String[] numeroExamenes={"3","4","5","6"};
	
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_notas,container, false);
		
		textView5 = (TextView)view.findViewById(R.id.textView5);
		editText1 = (EditText)view.findViewById(R.id.editText1);
		editText2 = (EditText)view.findViewById(R.id.editText2);
		editText3 = (EditText)view.findViewById(R.id.editText3);
		editText4 = (EditText)view.findViewById(R.id.editText4);
		editText5 = (EditText)view.findViewById(R.id.editText5);
		editText6 = (EditText)view.findViewById(R.id.editText6);
		editText7 = (EditText)view.findViewById(R.id.editText7);
		editText8 = (EditText)view.findViewById(R.id.editText8);
		editText9 = (EditText)view.findViewById(R.id.editText9);
		editText10 = (EditText)view.findViewById(R.id.editText10);
		editText11 = (EditText)view.findViewById(R.id.editText11);
		editText12 = (EditText)view.findViewById(R.id.editText12);
		editText13 = (EditText)view.findViewById(R.id.editText13);
		editText14 = (EditText)view.findViewById(R.id.editText14);
		editText15 = (EditText)view.findViewById(R.id.editText15);
		editText16 = (EditText)view.findViewById(R.id.editText16);
		editText17 = (EditText)view.findViewById(R.id.editText17);
		editText18 = (EditText)view.findViewById(R.id.editText18);
		
		spinner_creditos_en_notas = (Spinner)view.findViewById(R.id.spinner_creditos_en_notas);
		spinner_examenes_en_notas = (Spinner)view.findViewById(R.id.spinner_examenes_en_notas);
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_list_item_1,numeroExamenes);
        spinner_examenes_en_notas.setAdapter(dataAdapter);
        
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_list_item_1,numeroCreditos);
        spinner_creditos_en_notas.setAdapter(dataAdapter1);
        
        addListenerOnSpinnerItemSelection();
        
        return view;
	}

	private void addListenerOnSpinnerItemSelection() {
		// TODO Auto-generated method stub
		spinner_examenes_en_notas.setOnItemSelectedListener(new OnItemSelectedListener() 
		{   @Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) 
		    {
				int num = Integer.parseInt(parent.getItemAtPosition(position).toString());
				
				textView5.setEnabled(false);
				editText2.setEnabled(false);
				editText5.setEnabled(false);
				editText8.setEnabled(false);
				editText11.setEnabled(false);
				editText14.setEnabled(false);
				editText17.setEnabled(false);
				
				switch(num){
					case 3:
						
						editText10.setVisibility(View.INVISIBLE);
						editText11.setVisibility(View.INVISIBLE);
						editText12.setVisibility(View.INVISIBLE);
						editText13.setVisibility(View.INVISIBLE);
						editText14.setVisibility(View.INVISIBLE);
						editText15.setVisibility(View.INVISIBLE);
						editText16.setVisibility(View.INVISIBLE);
						editText17.setVisibility(View.INVISIBLE);
						editText18.setVisibility(View.INVISIBLE);
					break;
					case 4:
						editText10.setVisibility(View.VISIBLE);
						editText11.setVisibility(View.VISIBLE);
						editText12.setVisibility(View.VISIBLE);
						editText13.setVisibility(View.INVISIBLE);
						editText14.setVisibility(View.INVISIBLE);
						editText15.setVisibility(View.INVISIBLE);
						editText16.setVisibility(View.INVISIBLE);
						editText17.setVisibility(View.INVISIBLE);
						editText18.setVisibility(View.INVISIBLE);
					break;
					case 5:
						editText10.setVisibility(View.VISIBLE);
						editText11.setVisibility(View.VISIBLE);
						editText12.setVisibility(View.VISIBLE);
						editText13.setVisibility(View.VISIBLE);
						editText14.setVisibility(View.VISIBLE);
						editText15.setVisibility(View.VISIBLE);
						editText16.setVisibility(View.INVISIBLE);
						editText17.setVisibility(View.INVISIBLE);
						editText18.setVisibility(View.INVISIBLE);
					break;
					case 6:
						editText10.setVisibility(View.VISIBLE);
						editText11.setVisibility(View.VISIBLE);
						editText12.setVisibility(View.VISIBLE);
						editText13.setVisibility(View.VISIBLE);
						editText14.setVisibility(View.VISIBLE);
						editText15.setVisibility(View.VISIBLE);
						editText16.setVisibility(View.VISIBLE);
						editText17.setVisibility(View.VISIBLE);
						editText18.setVisibility(View.VISIBLE);
					break;
				}
    		}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				 
			}
		});
		

	}
	
	
}
