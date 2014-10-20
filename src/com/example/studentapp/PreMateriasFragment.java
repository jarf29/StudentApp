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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class PreMateriasFragment extends Fragment {

	private EditText campo_cred;
	private Spinner spinner_materias_en_pre_materias;
	private Button boton_guardar_materias_en_pre_materias;
	private Button boton_ver_materias_en_pre_materias;
	
	SQLiteDatabase db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_pre_materias, container,
				false);

		campo_cred = (EditText)view.findViewById(R.id.editText19);
		spinner_materias_en_pre_materias = (Spinner)view.findViewById(R.id.spinner_materias_en_pre_materias);
		
		ArrayList<String> spinnerMateriasPreMaterias = new ArrayList<String>();

		db = getActivity().openOrCreateDatabase("StudentDB", 0, null);
		
		Cursor c = db.rawQuery("select materia from materiasServidor", null);
		
		if (c.moveToFirst()) {
			do {
				spinnerMateriasPreMaterias.add(c.getString(0));
			} while (c.moveToNext());
		}	
				
		c.close();
		db.close();
		ArrayAdapter<String> Adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, spinnerMateriasPreMaterias);
		spinner_materias_en_pre_materias.setAdapter(Adapter);
//		addListenerOnSpinnerItemSelection();
		return view;
	}
	
//	private void addListenerOnSpinnerItemSelection() {
//		// TODO Auto-generated method stub
//		spinner_materias_en_pre_materias
//				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//					public void onItemSelected(AdapterView<?> parent,
//							View view, int position, long id) {						
//						
//					}
//
//					@Override
//					public void onNothingSelected(AdapterView<?> parent) {
//						// TODO Auto-generated method stub
//
//					}
//				});
//		
//	}

	}
