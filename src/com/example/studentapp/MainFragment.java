package com.example.studentapp;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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
import android.app.ListFragment;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainFragment extends Fragment {

	private EditText campo_promedio_acumulado;
	private EditText campo_creditos_cursados;
	private EditText campo_semestre;
	SQLiteDatabase db;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		campo_promedio_acumulado = (EditText) getActivity().findViewById(
				R.id.campo_promedio_acumulado);
		campo_creditos_cursados = (EditText) getActivity().findViewById(
				R.id.campo_creditos_cursados);
		campo_semestre = (EditText) getActivity().findViewById(
				R.id.campo_semestre);
		View view = inflater.inflate(R.layout.fragment_main, container, false);

		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		Button boton_ingresar = (Button) getActivity().findViewById(
				R.id.boton_ingresar);
		if (existeInfo()) {
			boton_ingresar.setText("Modificar");
		}
	}

	public boolean existeInfo() {
		campo_promedio_acumulado = (EditText) getActivity().findViewById(
				R.id.campo_promedio_acumulado);
		campo_creditos_cursados = (EditText) getActivity().findViewById(
				R.id.campo_creditos_cursados);
		campo_semestre = (EditText) getActivity().findViewById(
				R.id.campo_semestre);
		boolean existe = false;

		db = getActivity().openOrCreateDatabase("StudentDB",
				Context.MODE_PRIVATE, null);
		Cursor c = db.rawQuery("SELECT * From informacion", null);

		if (c.moveToLast()) {
			existe = true;
			campo_promedio_acumulado.setText(c.getString(1));
			campo_creditos_cursados.setText(c.getString(2));
			campo_semestre.setText(c.getString(3));

		} else {
			existe = false;
		}

		c.close();
		db.close();

		return existe;
	}
}
