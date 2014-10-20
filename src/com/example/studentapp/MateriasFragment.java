package com.example.studentapp;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MateriasFragment extends Fragment {
	private TextView textView2;
	private TextView textView3;
	private TextView textView4;
	private EditText editText19;
	private EditText editText20;
	private EditText editText21;
	private EditText editText22;
	private EditText editText23;
	private EditText editText24;
	private EditText editText25;
	private EditText editText26	;
	private EditText editText27;
	private EditText editText28;
	private EditText editText29;
	private EditText editText30;
	private EditText editText31;
	private EditText editText32;
	private EditText editText33;
	private EditText editText34;
	private EditText editText35;
	private EditText editText36;
	private Spinner spinner_materias_en_materias;
	

	SQLiteDatabase db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_materias, container,
				false);

		textView2 = (TextView)view.findViewById(R.id.textView2);
		textView3 = (TextView)view.findViewById(R.id.textView3);
		textView4 = (TextView)view.findViewById(R.id.textView4);
		editText19 = (EditText)view.findViewById(R.id.editText19);
		editText20 = (EditText)view.findViewById(R.id.editText20);
		editText21 = (EditText)view.findViewById(R.id.editText21);
		editText22 = (EditText)view.findViewById(R.id.editText22);
		editText23 = (EditText)view.findViewById(R.id.editText23);
		editText24 = (EditText)view.findViewById(R.id.editText24);
		editText25 = (EditText)view.findViewById(R.id.editText25);
		editText26 = (EditText)view.findViewById(R.id.editText26);
		editText27 = (EditText)view.findViewById(R.id.editText27);
		editText28 = (EditText)view.findViewById(R.id.editText28);
		editText29 = (EditText)view.findViewById(R.id.editText29);
		editText30 = (EditText)view.findViewById(R.id.editText30);
		editText31 = (EditText)view.findViewById(R.id.editText31);
		editText32 = (EditText)view.findViewById(R.id.editText32);
		editText33 = (EditText)view.findViewById(R.id.editText33);
		editText34 = (EditText)view.findViewById(R.id.editText34);
		editText35 = (EditText)view.findViewById(R.id.editText35);
		editText36 = (EditText)view.findViewById(R.id.editText36);
		spinner_materias_en_materias = (Spinner)view.findViewById(R.id.spinner_materias_en_materias);
		editText19.setEnabled(false);editText21.setEnabled(false);
		editText22.setEnabled(false);editText24.setEnabled(false);
		editText25.setEnabled(false);editText27.setEnabled(false);
		editText28.setEnabled(false);editText30.setEnabled(false);
		editText31.setEnabled(false);editText33.setEnabled(false);
		editText34.setEnabled(false);editText36.setEnabled(false);
		
		
		ArrayList<String> spinnerMateriasMaterias = new ArrayList<String>();

		db = getActivity().openOrCreateDatabase("StudentDB", 0, null);
		
		Cursor c = db.rawQuery("select materia from materias", null);
		
		if (c.moveToFirst()) {
			do {
				spinnerMateriasMaterias.add(c.getString(0));
			} while (c.moveToNext());
		}	
				
		c.close();
		db.close();
		ArrayAdapter<String> Adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, spinnerMateriasMaterias);
		spinner_materias_en_materias.setAdapter(Adapter);
		addListenerOnSpinnerItemSelection();
		return view;
	}

	private void addListenerOnSpinnerItemSelection() {
		// TODO Auto-generated method stub
		spinner_materias_en_materias
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						
												
						int i = 1;
						db = getActivity().openOrCreateDatabase("StudentDB", 0,
								null);

						Cursor c = db
								.rawQuery(
										"SELECT examenes.id, examenes.examen, examenes.nota, examenes.porcentaje "
												+ "FROM examenes,materias "
												+ "WHERE examenes.idMateria = materias.id "
												+ "AND materias.materia ='"
												+ spinner_materias_en_materias
														.getSelectedItem()
														.toString() + "'", null);

						if (c.moveToFirst()) {
							do {
								switch (i) {
									case 1:
										editText19.setText(c.getString(1));
										if(Double.parseDouble(c.getString(2))!=0){
											editText19.setTextColor(Color.WHITE);
											editText20.setTextColor(Color.WHITE);
											editText21.setTextColor(Color.WHITE);
											editText19.setEnabled(false);
											editText20.setEnabled(false);
											editText21.setEnabled(false);
										}
										editText20.setText(c.getString(2));
										editText21.setText(c.getString(3));
										i++;
									break;
									
									case 2:
										editText22.setText(c.getString(1));
										if(Double.parseDouble(c.getString(2))!=0){
											editText22.setTextColor(Color.WHITE);
											editText23.setTextColor(Color.WHITE);
											editText24.setTextColor(Color.WHITE);
											editText22.setEnabled(false);
											editText23.setEnabled(false);
											editText24.setEnabled(false);
										}
										editText23.setText(c.getString(2));
										editText24.setText(c.getString(3));
										i++;
									break;
									
									case 3:
										editText25.setText(c.getString(1));
										if(Double.parseDouble(c.getString(2))!=0){
											editText25.setTextColor(Color.WHITE);
											editText26.setTextColor(Color.WHITE);
											editText27.setTextColor(Color.WHITE);
											editText25.setEnabled(false);
											editText26.setEnabled(false);
											editText27.setEnabled(false);
										}
										editText26.setText(c.getString(2));
										editText27.setText(c.getString(3));
										i++;
									break;
									
									case 4:
										editText28.setText(c.getString(1));
										if(Double.parseDouble(c.getString(2))!=0){
											editText28.setTextColor(Color.WHITE);
											editText29.setTextColor(Color.WHITE);
											editText30.setTextColor(Color.WHITE);
											editText28.setEnabled(false);
											editText29.setEnabled(false);
											editText30.setEnabled(false);
										}
										editText29.setText(c.getString(2));
										editText30.setText(c.getString(3));
										i++;
									break;
									
									case 5:
										editText31.setText(c.getString(1));
										if(Double.parseDouble(c.getString(2))!=0){
											editText31.setTextColor(Color.WHITE);
											editText32.setTextColor(Color.WHITE);
											editText33.setTextColor(Color.WHITE);
											editText31.setEnabled(false);
											editText32.setEnabled(false);
											editText33.setEnabled(false);
										}
										editText32.setText(c.getString(2));
										editText33.setText(c.getString(3));
										i++;
									break;
									
									case 6:
										editText34.setText(c.getString(1));
										if(Double.parseDouble(c.getString(2))!=0){
											editText34.setTextColor(Color.WHITE);
											editText35.setTextColor(Color.WHITE);
											editText36.setTextColor(Color.WHITE);
											editText34.setEnabled(false);
											editText35.setEnabled(false);
											editText36.setEnabled(false);
										}
										editText35.setText(c.getString(2));
										editText36.setText(c.getString(3));
										i++;
									break;
									
									default:
										
									break;
								}
							} while (c.moveToNext());
							
							switch(c.getCount()){
								case 3:
									editText28.setVisibility(View.INVISIBLE);
									editText29.setVisibility(View.INVISIBLE);
									editText30.setVisibility(View.INVISIBLE);
									editText31.setVisibility(View.INVISIBLE);
									editText32.setVisibility(View.INVISIBLE);
									editText33.setVisibility(View.INVISIBLE);
									editText34.setVisibility(View.INVISIBLE);
									editText35.setVisibility(View.INVISIBLE);
									editText36.setVisibility(View.INVISIBLE);
									break;
								case 4:
									editText28.setVisibility(View.VISIBLE);
									editText29.setVisibility(View.VISIBLE);
									editText30.setVisibility(View.VISIBLE);
									editText31.setVisibility(View.INVISIBLE);
									editText32.setVisibility(View.INVISIBLE);
									editText33.setVisibility(View.INVISIBLE);
									editText34.setVisibility(View.INVISIBLE);
									editText35.setVisibility(View.INVISIBLE);
									editText36.setVisibility(View.INVISIBLE);
									break;
								case 5:
									editText28.setVisibility(View.VISIBLE);
									editText29.setVisibility(View.VISIBLE);
									editText30.setVisibility(View.VISIBLE);
									editText31.setVisibility(View.VISIBLE);
									editText32.setVisibility(View.VISIBLE);
									editText33.setVisibility(View.VISIBLE);
									editText34.setVisibility(View.INVISIBLE);
									editText35.setVisibility(View.INVISIBLE);
									editText36.setVisibility(View.INVISIBLE);
									break;
								case 6:
									editText28.setVisibility(View.VISIBLE);
									editText29.setVisibility(View.VISIBLE);
									editText30.setVisibility(View.VISIBLE);
									editText31.setVisibility(View.VISIBLE);
									editText32.setVisibility(View.VISIBLE);
									editText33.setVisibility(View.VISIBLE);
									editText34.setVisibility(View.VISIBLE);
									editText35.setVisibility(View.VISIBLE);
									editText36.setVisibility(View.VISIBLE);
									break;
							}
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub
						editText28.setVisibility(View.INVISIBLE);
						editText29.setVisibility(View.INVISIBLE);
						editText30.setVisibility(View.INVISIBLE);
						editText31.setVisibility(View.INVISIBLE);
						editText32.setVisibility(View.INVISIBLE);
						editText33.setVisibility(View.INVISIBLE);
						editText34.setVisibility(View.INVISIBLE);
						editText35.setVisibility(View.INVISIBLE);
						editText36.setVisibility(View.INVISIBLE);
					}
				});
	}
}
