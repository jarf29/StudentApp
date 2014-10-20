package com.example.studentapp;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
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

public class PromedioFragment extends Fragment {
	
	private EditText editText37;
	private EditText editText38;
	private EditText editText39;
	private EditText editText40;
	private EditText editText41;
	private EditText editText42;
	private EditText editText43;
	private EditText editText44;
	private EditText editText45;
	private EditText editText46;
	private EditText editText47;
	private EditText editText48;
	private EditText editText49;
	private EditText editText50;
	private EditText editText51;
	private EditText editText52;
	private EditText editText53;
	private EditText editText54;
	private EditText campo_promedio_deseado_en_promedio;
	private EditText campo_promedio_acumulado_en_promedio;
	private EditText campo_creditos_cursados_en_promedio;
	private Spinner spinner_materias_en_promedio;

	SQLiteDatabase db;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_promedio,container, false);
		
		editText37 = (EditText)view.findViewById(R.id.editText37);
		editText38 = (EditText)view.findViewById(R.id.editText38);
		editText39 = (EditText)view.findViewById(R.id.editText39);
		editText40 = (EditText)view.findViewById(R.id.editText40);
		editText41 = (EditText)view.findViewById(R.id.editText41);
		editText42 = (EditText)view.findViewById(R.id.editText42);
		editText43 = (EditText)view.findViewById(R.id.editText43);
		editText44 = (EditText)view.findViewById(R.id.editText44);
		editText45 = (EditText)view.findViewById(R.id.editText45);
		editText46 = (EditText)view.findViewById(R.id.editText46);
		editText47 = (EditText)view.findViewById(R.id.editText47);
		editText48 = (EditText)view.findViewById(R.id.editText48);
		editText49 = (EditText)view.findViewById(R.id.editText49);
		editText50 = (EditText)view.findViewById(R.id.editText50);
		editText51 = (EditText)view.findViewById(R.id.editText51);
		editText52 = (EditText)view.findViewById(R.id.editText52);
		editText53 = (EditText)view.findViewById(R.id.editText53);
		editText54 = (EditText)view.findViewById(R.id.editText54);
		campo_promedio_deseado_en_promedio = (EditText)view.findViewById(R.id.campo_promedio_deseado_en_promedio);
		campo_creditos_cursados_en_promedio = (EditText)view.findViewById(R.id.campo_creditos_cursados_en_promedio);
		campo_promedio_acumulado_en_promedio = (EditText)view.findViewById(R.id.campo_promedio_acumulado_en_promedio);
		spinner_materias_en_promedio = (Spinner)view.findViewById(R.id.spinner_materias_en_promedio);
		
		editText37.setEnabled(false);editText39.setEnabled(false);
		editText40.setEnabled(false);editText42.setEnabled(false);
		editText43.setEnabled(false);editText45.setEnabled(false);
		editText46.setEnabled(false);editText48.setEnabled(false);
		editText49.setEnabled(false);editText51.setEnabled(false);
		editText51.setEnabled(false);editText54.setEnabled(false);
		
		
		
		db = getActivity().openOrCreateDatabase("StudentDB", 0, null);
		
		Cursor cur = db.rawQuery("select * from materias", null);
		
		ArrayList<String> materias = new ArrayList<String>();
		if (cur.moveToFirst()){
			do{
				materias.add(cur.getString(1));
			}while (cur.moveToNext());
		}
		
		
		ArrayAdapter<String> Adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,
				materias);
		
		spinner_materias_en_promedio.setAdapter(Adapter);
		
		cur.close();
		db.close();
		
		db = getActivity().openOrCreateDatabase("StudentDB", 0, null);
		
		Cursor c = db.rawQuery("select * from informacion", null);
		
		if(c.moveToLast()){
			do{
				campo_promedio_acumulado_en_promedio.setText(c.getString(1));
				campo_creditos_cursados_en_promedio.setText(c.getString(2));
			}while(c.moveToNext());
		}
		c.close();
		db.close();
		
		addListenerOnSpinnerItemSelection();		
		return view;
	}

	private void addListenerOnSpinnerItemSelection() {

		spinner_materias_en_promedio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
				
			
				int i = 1;
				db = getActivity().openOrCreateDatabase("StudentDB", 0,	null);

				//NOTA: CAMBIÉ examenes.id por materias.id
				Cursor c = db
						.rawQuery(
								"SELECT materias.materia, examenes.examen, examenes.nota, examenes.porcentaje "
										+ "FROM examenes,materias "
										+ "WHERE examenes.idMateria = materias.id "
										+ "AND materias.materia ='"
										+ spinner_materias_en_promedio
												.getSelectedItem()
												.toString() + "'", null);

				if (c.moveToFirst()) {
					do {
						switch (i) {
							case 1:
								editText37.setText(c.getString(1));
								editText38.setText(c.getString(2));																
								editText39.setText(c.getString(3));
								if(Double.parseDouble(c.getString(2))!=0){
									editText37.setTextColor(Color.WHITE);
									editText38.setTextColor(Color.WHITE);
									editText39.setTextColor(Color.WHITE);
									editText37.setEnabled(false);
									editText38.setEnabled(false);
									editText39.setEnabled(false);
								}
								i++;
							break;
							
							case 2:
								editText40.setText(c.getString(1));
								editText41.setText(c.getString(2));
								editText42.setText(c.getString(3));
								if(Double.parseDouble(c.getString(2))!=0){
									editText40.setTextColor(Color.WHITE);
									editText41.setTextColor(Color.WHITE);
									editText42.setTextColor(Color.WHITE);
									editText40.setEnabled(false);
									editText41.setEnabled(false);
									editText42.setEnabled(false);
								}
								i++;
							break;
							
							case 3:
								editText43.setText(c.getString(1));
								editText44.setText(c.getString(2));
								editText45.setText(c.getString(3));
								if(Double.parseDouble(c.getString(2))!=0){
									editText43.setTextColor(Color.WHITE);
									editText44.setTextColor(Color.WHITE);
									editText45.setTextColor(Color.WHITE);
									editText43.setEnabled(false);
									editText44.setEnabled(false);
									editText45.setEnabled(false);
								}
								i++;
							break;
							
							case 4:
								editText46.setText(c.getString(1));
								editText47.setText(c.getString(2));
								editText48.setText(c.getString(3));
								if(Double.parseDouble(c.getString(2))!=0){
									editText46.setTextColor(Color.WHITE);
									editText47.setTextColor(Color.WHITE);
									editText48.setTextColor(Color.WHITE);
									editText46.setEnabled(false);
									editText47.setEnabled(false);
									editText48.setEnabled(false);
								}
								i++;
							break;
							
							case 5:
								editText49.setText(c.getString(1));
								editText50.setText(c.getString(2));
								editText51.setText(c.getString(3));
								if(Double.parseDouble(c.getString(2))!=0){
									editText49.setTextColor(Color.WHITE);
									editText50.setTextColor(Color.WHITE);
									editText51.setTextColor(Color.WHITE);
									editText49.setEnabled(false);
									editText50.setEnabled(false);
									editText51.setEnabled(false);
								}
								i++;
							break;
							
							case 6:
								editText52.setText(c.getString(1));
								editText53.setText(c.getString(2));
								editText54.setText(c.getString(3));
								if(Double.parseDouble(c.getString(2))!=0){
									editText52.setTextColor(Color.WHITE);
									editText53.setTextColor(Color.WHITE);
									editText54.setTextColor(Color.WHITE);
									editText52.setEnabled(false);
									editText53.setEnabled(false);
									editText54.setEnabled(false);
								}
								i++;
							break;
							
							default:
								
							break;
						}
						
					} while (c.moveToNext());
					
					switch(c.getCount()){
						case 3:
							editText46.setVisibility(View.INVISIBLE);
							editText47.setVisibility(View.INVISIBLE);
							editText48.setVisibility(View.INVISIBLE);
							editText49.setVisibility(View.INVISIBLE);
							editText50.setVisibility(View.INVISIBLE);
							editText51.setVisibility(View.INVISIBLE);
							editText52.setVisibility(View.INVISIBLE);
							editText53.setVisibility(View.INVISIBLE);
							editText54.setVisibility(View.INVISIBLE);
							break;
						case 4:
							editText46.setVisibility(View.VISIBLE);
							editText47.setVisibility(View.VISIBLE);
							editText48.setVisibility(View.VISIBLE);
							editText49.setVisibility(View.INVISIBLE);
							editText50.setVisibility(View.INVISIBLE);
							editText51.setVisibility(View.INVISIBLE);
							editText52.setVisibility(View.INVISIBLE);
							editText53.setVisibility(View.INVISIBLE);
							editText54.setVisibility(View.INVISIBLE);
							break;
						case 5:
							editText46.setVisibility(View.VISIBLE);
							editText47.setVisibility(View.VISIBLE);
							editText48.setVisibility(View.VISIBLE);
							editText49.setVisibility(View.VISIBLE);
							editText50.setVisibility(View.VISIBLE);
							editText51.setVisibility(View.VISIBLE);
							editText52.setVisibility(View.INVISIBLE);
							editText53.setVisibility(View.INVISIBLE);
							editText54.setVisibility(View.INVISIBLE);
							break;
						case 6:
							editText46.setVisibility(View.VISIBLE);
							editText47.setVisibility(View.VISIBLE);
							editText48.setVisibility(View.VISIBLE);
							editText49.setVisibility(View.VISIBLE);
							editText50.setVisibility(View.VISIBLE);
							editText51.setVisibility(View.VISIBLE);
							editText52.setVisibility(View.VISIBLE);
							editText53.setVisibility(View.VISIBLE);
							editText54.setVisibility(View.VISIBLE);
							break;
					}
				}	
				
				if (!MainActivity.getPuntajes().isEmpty())
					mostrarPromAcum();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				editText46.setVisibility(View.INVISIBLE);
				editText47.setVisibility(View.INVISIBLE);
				editText48.setVisibility(View.INVISIBLE);
				editText49.setVisibility(View.INVISIBLE);
				editText50.setVisibility(View.INVISIBLE);
				editText51.setVisibility(View.INVISIBLE);
				editText52.setVisibility(View.INVISIBLE);
				editText53.setVisibility(View.INVISIBLE);
				editText54.setVisibility(View.INVISIBLE);
			}
		});
		
	}
	
	
	public void mostrarPromAcum(){
		editText37 = (EditText) getActivity().findViewById(R.id.editText37);
		editText38 = (EditText) getActivity().findViewById(R.id.editText38);
		editText39 = (EditText) getActivity().findViewById(R.id.editText39);
		editText40 = (EditText) getActivity().findViewById(R.id.editText40);
		editText41 = (EditText) getActivity().findViewById(R.id.editText41);
		editText42 = (EditText) getActivity().findViewById(R.id.editText42);
		editText43 = (EditText) getActivity().findViewById(R.id.editText43);
		editText44 = (EditText) getActivity().findViewById(R.id.editText44);
		editText45 = (EditText) getActivity().findViewById(R.id.editText45);
		editText46 = (EditText) getActivity().findViewById(R.id.editText46);
		editText47 = (EditText) getActivity().findViewById(R.id.editText47);
		editText48 = (EditText) getActivity().findViewById(R.id.editText48);
		editText49 = (EditText) getActivity().findViewById(R.id.editText49);
		editText50 = (EditText) getActivity().findViewById(R.id.editText50);
		editText51 = (EditText) getActivity().findViewById(R.id.editText51);
		editText52 = (EditText) getActivity().findViewById(R.id.editText52);
		editText53 = (EditText) getActivity().findViewById(R.id.editText53);
		editText54 = (EditText) getActivity().findViewById(R.id.editText54);
		campo_promedio_deseado_en_promedio = (EditText) getActivity().findViewById(R.id.campo_promedio_deseado_en_promedio);
		campo_promedio_acumulado_en_promedio = (EditText) getActivity().findViewById(R.id.campo_promedio_acumulado_en_promedio);
		campo_creditos_cursados_en_promedio = (EditText) getActivity().findViewById(R.id.campo_creditos_cursados_en_promedio);
		spinner_materias_en_promedio = (Spinner) getActivity().findViewById(R.id.spinner_materias_en_promedio);
		
		
		db = getActivity().openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
		
		Cursor c = db.rawQuery("SELECT examenes.id, examenes.examen, "
				+ "examenes.nota, examenes.porcentaje "
				+ "FROM examenes,materias "
				+ "WHERE examenes.idMateria = materias.id "
				+ "AND materias.materia ='"
				+ spinner_materias_en_promedio.getSelectedItem().toString()
				+ "'", null);
		
		DecimalFormat REAL_FORMATTER = new DecimalFormat("0.###");
		
		
		ArrayList<Double> valores=new ArrayList<Double>();
		
		for (int i=0;i<MainActivity.getPuntajes().size();i++){
			if (MainActivity.getPuntajes().get(i).getNombreMateria().equals(
					spinner_materias_en_promedio.getSelectedItem().toString())){
				valores.add(MainActivity.getPuntajes().get(i).getPuntaje());
			}
				
		}
		
		if (c.moveToFirst()) {
			do {
				switch (c.getCount()) {
				case 3:
					if (editText38.getText().toString().equals("0")) {
						editText38.setText(REAL_FORMATTER.format(valores.get(0)));
					}
					if (editText41.getText().toString().equals("0")) {
						editText41.setText(REAL_FORMATTER.format(valores.get(1)));
					}
					if (editText44.getText().toString().equals("0")) {
						editText44.setText(REAL_FORMATTER.format(valores.get(2)));
					}
					break;

				case 4:

					if (editText38.getText().toString().equals("0")) {
						editText38.setText(REAL_FORMATTER.format(valores.get(0)));
					}
					if (editText41.getText().toString().equals("0")) {
						editText41.setText(REAL_FORMATTER.format(valores.get(1)));
					}
					if (editText44.getText().toString().equals("0")) {
						editText44.setText(REAL_FORMATTER.format(valores.get(2)));
					}
					if (editText47.getText().toString().equals("0")) {
						editText47.setText(REAL_FORMATTER.format(valores.get(3)));
					}

					break;

				case 5:
					if (editText38.getText().toString().equals("0")) {
						editText38.setText(REAL_FORMATTER.format(valores.get(0)));
					}
					if (editText41.getText().toString().equals("0")) {
						editText41.setText(REAL_FORMATTER.format(valores.get(1)));
					}
					if (editText44.getText().toString().equals("0")) {
						editText44.setText(REAL_FORMATTER.format(valores.get(2)));
					}
					if (editText47.getText().toString().equals("0")) {
						editText47.setText(REAL_FORMATTER.format(valores.get(3)));
					}
					if (editText50.getText().toString().equals("0")) {
						editText50.setText(REAL_FORMATTER.format(valores.get(4)));
					}
					break;

				case 6:
					if (editText38.getText().toString().equals("0")) {
						editText38.setText(REAL_FORMATTER.format(valores.get(0)));
					}
					if (editText41.getText().toString().equals("0")) {
						editText41.setText(REAL_FORMATTER.format(valores.get(1)));
					}
					if (editText44.getText().toString().equals("0")) {
						editText44.setText(REAL_FORMATTER.format(valores.get(2)));
					}
					if (editText47.getText().toString().equals("0")) {
						editText47.setText(REAL_FORMATTER.format(valores.get(3)));
					}
					if (editText50.getText().toString().equals("0")) {
						editText50.setText(REAL_FORMATTER.format(valores.get(4)));
					}
					if (editText53.getText().toString().equals("0")) {
						editText53.setText(REAL_FORMATTER.format(valores.get(5)));
					}
					break;

				}
			} while (c.moveToNext());
		}
		c.close();
	}
	
	
	
	
	
}
