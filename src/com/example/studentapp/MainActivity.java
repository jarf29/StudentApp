package com.example.studentapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends Activity {

	private static final String TAG_FRAGMENT = "TAG_FRAGMENT";
	protected String TAG = MainActivity.class.getSimpleName();

	private static ArrayList<DatosPuntaje> puntajes = new ArrayList<DatosPuntaje>();

	public static ArrayList<DatosPuntaje> getPuntajes() {
		return puntajes;
	}

	public void setPuntajes(ArrayList<DatosPuntaje> puntajes) {
		MainActivity.puntajes = puntajes;
	}

	// JSON
	// protected static Context mContext;
	protected JSONObject mData;

	// fragment_main
	private EditText campo_promedio_acumulado;
	private EditText campo_creditos_cursados;
	private EditText campo_semestre;
	private Button boton_ingresar;

	// fragment notas
	private EditText campo_materia_en_notas;
	private Spinner spinner_creditos_en_notas;
	private Spinner spinner_examenes_en_notas;
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

	// fragment prematerias
	private EditText campo_cred;
	private Spinner spinner_materias_en_pre_materias;

	// fragment materias
	private Spinner spinner_materias_en_materias;
	private EditText editText19;
	private EditText editText20;
	private EditText editText21;
	private EditText editText22;
	private EditText editText23;
	private EditText editText24;
	private EditText editText25;
	private EditText editText26;
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
	private EditText notaFinal;
	private EditText creditos_materia;

	// fragment promedio
	private EditText campo_promedio_deseado_en_promedio;
	private EditText campo_promedio_acumulado_en_promedio;
	private EditText campo_creditos_cursados_en_promedio;
	private Spinner spinner_materias_en_promedio;
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
	private EditText editText55;
	private EditText editText56;

	boolean BD_guardar = false;
	SQLiteDatabase db;

	public View.OnClickListener OnClickListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		campo_promedio_acumulado = (EditText) findViewById(R.id.campo_promedio_acumulado);
		campo_creditos_cursados = (EditText) findViewById(R.id.campo_creditos_cursados);
		campo_semestre = (EditText) findViewById(R.id.campo_semestre);
		boton_ingresar = (Button) findViewById(R.id.boton_ingresar);

		db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS materias(id INTEGER PRIMARY KEY AUTOINCREMENT,materia TEXT NOT NULL,creditos INTEGER NOT NULL);");
		db.execSQL("CREATE TABLE IF NOT EXISTS examenes(id INTEGER PRIMARY KEY AUTOINCREMENT,examen TEXT NOT NULL,nota REAL,porcentaje INTEGER NOT NULL,idMateria INTEGER NOT NULL,FOREIGN KEY(idMateria) REFERENCES materias(id));");

		db.execSQL("CREATE TABLE IF NOT EXISTS materiasServidor(id INTEGER PRIMARY KEY AUTOINCREMENT,materia TEXT NOT NULL,creditos INTEGER NOT NULL);");
		db.execSQL("CREATE TABLE IF NOT EXISTS examenesServidor(id INTEGER PRIMARY KEY AUTOINCREMENT,examen TEXT NOT NULL,nota REAL,porcentaje INTEGER NOT NULL,idMateria INTEGER NOT NULL,FOREIGN KEY(idMateria) REFERENCES materias(id));");

		db.execSQL("CREATE TABLE IF NOT EXISTS informacion(id INTEGER PRIMARY KEY AUTOINCREMENT,promedio REAL NOT NULL,creditos INTEGER NOT NULL, semestre INTEGER NOT NULL);");

		FragmentManager fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragment_content);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// servidor

		// BUSCAR MATERIAS DE LA BASE DE DATOS ASOCIADO AL SERVIDOR
		Cursor c = db.rawQuery("SELECT * FROM materiasServidor; ", null);

		if (!c.moveToFirst()) {
			obtenerDatosJSON();
		}

		if (fragment == null) {

			FragmentTransaction ft = fm.beginTransaction();

			ft.add(R.id.fragment_content, new MainFragment());

			ft.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// FRAGMENT MAIN

	public void verNotas(View view) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();

		if (existenMaterias()) {
			ft.replace(R.id.fragment_content, new MateriasFragment())
					.addToBackStack(TAG_FRAGMENT);
		} else {
			ft.replace(R.id.fragment_content, new PreMateriasFragment())
					.addToBackStack(TAG_FRAGMENT);
		}

		ft.commit();
	}

	public void verPromedio(View view) {
		campo_promedio_acumulado = (EditText) findViewById(R.id.campo_promedio_acumulado);
		campo_creditos_cursados = (EditText) findViewById(R.id.campo_creditos_cursados);
		campo_semestre = (EditText) findViewById(R.id.campo_semestre);
		boton_ingresar = (Button) findViewById(R.id.boton_ingresar);

		if ((campo_promedio_acumulado.getText().toString().length() == 0)
				|| (campo_creditos_cursados.getText().toString().length() == 0)
				|| (campo_semestre.getText().toString().length() == 0)) {

			showMessage("Error", "Llene todos los campos");
		} else {
			FragmentManager fm = getFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(R.id.fragment_content, new PromedioFragment())
					.addToBackStack(TAG_FRAGMENT);
			ft.commit();

		}
	}

	public void ingresar(View view) {
		campo_promedio_acumulado = (EditText) findViewById(R.id.campo_promedio_acumulado);
		campo_creditos_cursados = (EditText) findViewById(R.id.campo_creditos_cursados);
		campo_semestre = (EditText) findViewById(R.id.campo_semestre);
		boton_ingresar = (Button) findViewById(R.id.boton_ingresar);

		if ((campo_promedio_acumulado.getText().toString().length() == 0)
				|| (campo_creditos_cursados.getText().toString().length() == 0)
				|| (campo_semestre.getText().toString().length() == 0)) {

			showMessage("Error", "Llene todos los campos");
		} else {

			if (Double.parseDouble((campo_promedio_acumulado.getText()
					.toString())) > 5.0) {
				showMessage("Error", "El promedio acumulado excede el limite");

			} else {
				if (Integer.parseInt(campo_creditos_cursados.getText()
						.toString()) >= 200) {
					showMessage("Error",
							"Los creditos cursados exceden el limite");
				} else {
					if (Integer.parseInt(campo_semestre.getText().toString()) > 10) {
						showMessage("Error", "El semestre excede el limite");
					} else {
						db = openOrCreateDatabase("StudentDB", 0, null);
						db.execSQL("INSERT INTO informacion (promedio,creditos,semestre) VALUES("
								+ campo_promedio_acumulado.getText()
								+ ","
								+ campo_creditos_cursados.getText()
								+ ","
								+ campo_semestre.getText() + ");");

						showMessage("Alerta", "Informacion ingresada");

						db.close();
						boton_ingresar.setText("Modificar");
					}
				}
			}
		}

	}

	// FRAGMENT NOTAS

	public void guardarMaterias(View view) {
		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		editText3 = (EditText) findViewById(R.id.editText3);
		editText4 = (EditText) findViewById(R.id.editText4);
		editText5 = (EditText) findViewById(R.id.editText5);
		editText6 = (EditText) findViewById(R.id.editText6);
		editText7 = (EditText) findViewById(R.id.editText7);
		editText8 = (EditText) findViewById(R.id.editText8);
		editText9 = (EditText) findViewById(R.id.editText9);
		editText10 = (EditText) findViewById(R.id.editText10);
		editText11 = (EditText) findViewById(R.id.editText11);
		editText12 = (EditText) findViewById(R.id.editText12);
		editText13 = (EditText) findViewById(R.id.editText13);
		editText14 = (EditText) findViewById(R.id.editText14);
		editText15 = (EditText) findViewById(R.id.editText15);
		editText16 = (EditText) findViewById(R.id.editText16);
		editText17 = (EditText) findViewById(R.id.editText17);
		editText18 = (EditText) findViewById(R.id.editText18);
		campo_materia_en_notas = (EditText) findViewById(R.id.campo_materia_en_notas);
		spinner_creditos_en_notas = (Spinner) findViewById(R.id.spinner_creditos_en_notas);
		spinner_examenes_en_notas = (Spinner) findViewById(R.id.spinner_examenes_en_notas);
		boolean error = false;

		switch (Integer.parseInt(spinner_examenes_en_notas.getSelectedItem()
				.toString())) {
		case 3:

			if (editText1.getText().toString().trim().length() == 0
					|| editText3.getText().toString().trim().length() == 0
					|| editText4.getText().toString().trim().length() == 0
					|| editText6.getText().toString().trim().length() == 0
					|| editText7.getText().toString().trim().length() == 0
					|| editText9.getText().toString().trim().length() == 0)
				error = true;
			else {
				error = false;
			}
			break;

		case 4:
			if (editText1.getText().toString().trim().length() == 0
					|| editText3.getText().toString().trim().length() == 0
					|| editText4.getText().toString().trim().length() == 0
					|| editText6.getText().toString().trim().length() == 0
					|| editText7.getText().toString().trim().length() == 0
					|| editText9.getText().toString().trim().length() == 0
					|| editText10.getText().toString().trim().length() == 0
					|| editText12.getText().toString().trim().length() == 0)
				error = true;
			else {
				error = false;
			}
			break;
		case 5:
			if (editText1.getText().toString().trim().length() == 0
					|| editText3.getText().toString().trim().length() == 0
					|| editText4.getText().toString().trim().length() == 0
					|| editText6.getText().toString().trim().length() == 0
					|| editText7.getText().toString().trim().length() == 0
					|| editText9.getText().toString().trim().length() == 0
					|| editText10.getText().toString().trim().length() == 0
					|| editText12.getText().toString().trim().length() == 0
					|| editText13.getText().toString().trim().length() == 0
					|| editText15.getText().toString().trim().length() == 0)
				error = true;
			else {
				error = false;
			}
			break;
		case 6:
			if (editText1.getText().toString().trim().length() == 0
					|| editText3.getText().toString().trim().length() == 0
					|| editText4.getText().toString().trim().length() == 0
					|| editText6.getText().toString().trim().length() == 0
					|| editText7.getText().toString().trim().length() == 0
					|| editText9.getText().toString().trim().length() == 0
					|| editText10.getText().toString().trim().length() == 0
					|| editText12.getText().toString().trim().length() == 0
					|| editText13.getText().toString().trim().length() == 0
					|| editText15.getText().toString().trim().length() == 0
					|| editText16.getText().toString().trim().length() == 0
					|| editText18.getText().toString().trim().length() == 0)
				error = true;
			else {
				error = false;
			}
			break;
		default:
			showMessage("Error", "Seleccione una cantidad de examenes");

			break;
		}

		if (error == true
				|| campo_materia_en_notas.getText().toString().trim().length() == 0) {
			showMessage("Error", "Lleno todos los campos");
		} else {
			db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);

			db.execSQL("INSERT INTO materias (materia,creditos) VALUES('"
					+ campo_materia_en_notas.getText() + "',"
					+ spinner_creditos_en_notas.getSelectedItem().toString()
					+ ");");

			Cursor c = db.rawQuery("SELECT id FROM materias WHERE materia ='"
					+ campo_materia_en_notas.getText() + "'", null);

			if (c.moveToLast()) {

				switch (Integer.parseInt(spinner_examenes_en_notas
						.getSelectedItem().toString())) {
				case 3:
					db.execSQL("INSERT INTO examenes (examen,nota,porcentaje,idMateria) "
							+ "VALUES('"
							+ editText1.getText()
							+ "',0,"
							+ editText3.getText() + "," + c.getString(0) + ");");
					db.execSQL("INSERT INTO examenes (examen,nota,porcentaje,idMateria) "
							+ "VALUES('"
							+ editText4.getText()
							+ "',0,"
							+ editText6.getText() + "," + c.getString(0) + ");");
					db.execSQL("INSERT INTO examenes (examen,nota,porcentaje,idMateria) "
							+ "VALUES('"
							+ editText7.getText()
							+ "',0,"
							+ editText9.getText() + "," + c.getString(0) + ");");

					showMessage("Alerta", "Materia Guardada");
					clearText();
					break;

				case 4:
					db.execSQL("INSERT INTO examenes (examen,nota,porcentaje,idMateria) "
							+ "VALUES('"
							+ editText1.getText()
							+ "',0,"
							+ editText3.getText() + "," + c.getString(0) + ");");
					db.execSQL("INSERT INTO examenes (examen,nota,porcentaje,idMateria) "
							+ "VALUES('"
							+ editText4.getText()
							+ "',0,"
							+ editText6.getText() + "," + c.getString(0) + ");");
					db.execSQL("INSERT INTO examenes (examen,nota,porcentaje,idMateria) "
							+ "VALUES('"
							+ editText7.getText()
							+ "',0,"
							+ editText9.getText() + "," + c.getString(0) + ");");
					db.execSQL("INSERT INTO examenes (examen,nota,porcentaje,idMateria) "
							+ "VALUES('"
							+ editText10.getText()
							+ "',0,"
							+ editText12.getText()
							+ ","
							+ c.getString(0)
							+ ");");

					showMessage("Alerta", "Materia Guardada");
					clearText();
					break;
				case 5:
					db.execSQL("INSERT INTO examenes (examen,nota,porcentaje,idMateria) "
							+ "VALUES('"
							+ editText1.getText()
							+ "',0,"
							+ editText3.getText() + "," + c.getString(0) + ");");
					db.execSQL("INSERT INTO examenes (examen,nota,porcentaje,idMateria) "
							+ "VALUES('"
							+ editText4.getText()
							+ "',0,"
							+ editText6.getText() + "," + c.getString(0) + ");");
					db.execSQL("INSERT INTO examenes (examen,nota,porcentaje,idMateria) "
							+ "VALUES('"
							+ editText7.getText()
							+ "',0,"
							+ editText9.getText() + "," + c.getString(0) + ");");
					db.execSQL("INSERT INTO examenes (examen,nota,porcentaje,idMateria) "
							+ "VALUES('"
							+ editText10.getText()
							+ "',0,"
							+ editText12.getText()
							+ ","
							+ c.getString(0)
							+ ");");
					db.execSQL("INSERT INTO examenes (examen,nota,porcentaje,idMateria) "
							+ "VALUES('"
							+ editText13.getText()
							+ "',0,"
							+ editText15.getText()
							+ ","
							+ c.getString(0)
							+ ");");

					showMessage("Alerta", "Materia Guardada");
					clearText();
					break;
				case 6:
					db.execSQL("INSERT INTO examenes (examen,nota,porcentaje,idMateria) "
							+ "VALUES('"
							+ editText1.getText()
							+ "',0,"
							+ editText3.getText() + "," + c.getString(0) + ");");
					db.execSQL("INSERT INTO examenes (examen,nota,porcentaje,idMateria) "
							+ "VALUES('"
							+ editText4.getText()
							+ "',0,"
							+ editText6.getText() + "," + c.getString(0) + ");");
					db.execSQL("INSERT INTO examenes (examen,nota,porcentaje,idMateria) "
							+ "VALUES('"
							+ editText7.getText()
							+ "',0,"
							+ editText9.getText() + "," + c.getString(0) + ");");
					db.execSQL("INSERT INTO examenes (examen,nota,porcentaje,idMateria) "
							+ "VALUES('"
							+ editText10.getText()
							+ "',0,"
							+ editText12.getText()
							+ ","
							+ c.getString(0)
							+ ");");
					db.execSQL("INSERT INTO examenes (examen,nota,porcentaje,idMateria) "
							+ "VALUES('"
							+ editText13.getText()
							+ "',0,"
							+ editText15.getText()
							+ ","
							+ c.getString(0)
							+ ");");
					db.execSQL("INSERT INTO examenes (examen,nota,porcentaje,idMateria) "
							+ "VALUES('"
							+ editText16.getText()
							+ "',0,"
							+ editText18.getText()
							+ ","
							+ c.getString(0)
							+ ");");

					showMessage("Alerta", "Materia Guardada");
					clearText();
					break;
				default:
					showMessage("Error", "Seleccione una cantidad de examenes");
					break;

				}
				c.close();
				db.close();

			}
			while (c.moveToNext())
				;
		}

	}

	public void verMaterias(View view) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.fragment_content, new MateriasFragment())
				.addToBackStack(TAG_FRAGMENT);
		ft.commit();
	}

	// FRAGMENT PREMATERIAS
	public void guardarPreMaterias(View view) {

		campo_cred = (EditText) findViewById(R.id.campo_cred);
		spinner_materias_en_pre_materias = (Spinner) findViewById(R.id.spinner_materias_en_pre_materias);

		String nombreMateria = spinner_materias_en_pre_materias
				.getSelectedItem().toString();

		db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);

		// INSERTAR MATERIA SELECCIONADA
		db.execSQL("INSERT INTO materias (materia,creditos) VALUES('"
				+ nombreMateria + "'," + campo_cred.getText().toString() + ");");

		// OBTENER Y GUARDAR ID DE LA MATERIA PARA POSTERIOR USO
		String idMateriaCreada = null;

		Cursor idMateriaGetter = db.rawQuery("SELECT id FROM materias "
				+ "WHERE materias.materia ='" + nombreMateria + "'", null);

		if (idMateriaGetter.moveToFirst()) {
			idMateriaCreada = idMateriaGetter.getString(0);
		}

		// TODO
		// INSERTAR NOTAS DE LA MATERIA SELECCIONADA

		// A) BUSCAR NOTAS DE LA BASE DE DATOS ASOCIADO AL SERVIDOR
		Cursor c = db
				.rawQuery(
						"SELECT examenesServidor.examen, examenesServidor.porcentaje "
								+ "FROM examenesServidor,materiasServidor "
								+ "WHERE examenesServidor.idMateria = materiasServidor.id "
								+ "AND materiasServidor.materia ='"
								+ spinner_materias_en_pre_materias
										.getSelectedItem().toString() + "'",
						null);

		// A1) SE ASUME QUE LOS NOMBRES DE LAS MATERIAS NO SE REPITEN
		if (c.moveToFirst()) {

			do {
				db.execSQL("INSERT INTO examenes (examen,nota,porcentaje,idMateria) "
						+ "VALUES('"
						+ c.getString(0)
						+ "',0,'"
						+ c.getString(1) + "'," + idMateriaCreada + ");");

			} while (c.moveToNext());

		}

		// MENSAJE DE EXITO
		Toast.makeText(
				this,
				"LA MATERIA DE "
						+ spinner_materias_en_pre_materias.getSelectedItem()
								.toString() + " HA SIDO INGRESADA.",
				Toast.LENGTH_LONG).show();

		db.close();

	}

	// FRAGMENT MATERIAS
	public void guardarNotas(View view) {
		editText19 = (EditText) findViewById(R.id.editText19);
		editText20 = (EditText) findViewById(R.id.editText20);
		editText21 = (EditText) findViewById(R.id.editText21);
		editText22 = (EditText) findViewById(R.id.editText22);
		editText23 = (EditText) findViewById(R.id.editText23);
		editText24 = (EditText) findViewById(R.id.editText24);
		editText25 = (EditText) findViewById(R.id.editText25);
		editText26 = (EditText) findViewById(R.id.editText26);
		editText27 = (EditText) findViewById(R.id.editText27);
		editText28 = (EditText) findViewById(R.id.editText28);
		editText29 = (EditText) findViewById(R.id.editText29);
		editText30 = (EditText) findViewById(R.id.editText30);
		editText31 = (EditText) findViewById(R.id.editText31);
		editText32 = (EditText) findViewById(R.id.editText32);
		editText33 = (EditText) findViewById(R.id.editText33);
		editText34 = (EditText) findViewById(R.id.editText34);
		editText35 = (EditText) findViewById(R.id.editText35);
		spinner_materias_en_materias = (Spinner) findViewById(R.id.spinner_materias_en_materias);

		String idMateria = null;
		db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);

		if (editText19.getText().toString().length() == 0) {
			showMessage("Error", "No hay informacion ingresada");
		} else {
			
			Cursor idMateriaGetter = db.rawQuery("SELECT id FROM materias "
					+ "WHERE materias.materia ='"
					+ spinner_materias_en_materias.getSelectedItem().toString()
					+ "'", null);
			if (idMateriaGetter.moveToFirst()) {
				idMateria = idMateriaGetter.getString(0);
			}

			Cursor c = db
					.rawQuery(
							"SELECT examenes.id, examenes.examen, examenes.nota, examenes.porcentaje, examenes.idMateria "
									+ "FROM examenes,materias "
									+ "WHERE examenes.idMateria = materias.id "
									+ "AND materias.materia ='"
									+ spinner_materias_en_materias
											.getSelectedItem().toString() + "'",
							null);
			
			int i = 1;
			
				if (c.moveToFirst()){
					
				
				 
				do {					
					
					switch (i) {

					case 1:
						if (Double.parseDouble(editText20.getText().toString()) > 5) {
							showMessage("Error",
									"Las notas ingresadas exceden el limite");
						} else {
														
							db.execSQL("UPDATE examenes SET nota = "
									+ Double.parseDouble(editText20.getText()
											.toString())
									+ " WHERE examenes.id = "+ c.getString(0)
									+ " ;");
							i++;

						}

						break;

					case 2:
						if (Double.parseDouble(editText23.getText().toString()) > 5) {
							showMessage("Error",
									"Las notas ingresadas exceden el limite");
						} else {

							db.execSQL("UPDATE examenes SET nota = "
									+ Double.parseDouble(editText23.getText()
											.toString())
									+ " WHERE examenes.id = "+ c.getString(0)
									+ " ;");
							i++;
						}

						break;

					case 3:
						if (Double.parseDouble(editText26.getText().toString()) > 5) {
							showMessage("Error",
									"Las notas ingresadas exceden el limite");
						} else {

							db.execSQL("UPDATE examenes SET nota = "
									+ Double.parseDouble(editText26.getText()
											.toString())
									+ " WHERE examenes.id = "+ c.getString(0)
									+ " ;");
							i++;

						}

						break;

					case 4:
						if (Double.parseDouble(editText29.getText().toString()) > 5) {
							showMessage("Error",
									"Las notas ingresadas exceden el limite");
						} else {

							db.execSQL("UPDATE examenes SET nota = "
									+ Double.parseDouble(editText29.getText()
											.toString())
									+ " WHERE examenes.id = "+ c.getString(0)
									+ " ;");
							i++;

						}

						break;

					case 5:
						if (Double.parseDouble(editText32.getText().toString()) > 5) {
							showMessage("Error",
									"Las notas ingresadas exceden el limite");
						} else {
							
							db.execSQL("UPDATE examenes SET nota = "
									+ Double.parseDouble(editText32.getText()
											.toString())
									+ " WHERE examenes.id = "+ c.getString(0)
									+ " ;");
							i++;

							
						}

						break;

					case 6:
						if (Double.parseDouble(editText35.getText().toString()) > 5) {
							showMessage("Error",
									"Las notas ingresadas exceden el limite");
						} else {
							
							db.execSQL("UPDATE examenes SET nota = "
									+ Double.parseDouble(editText35.getText()
											.toString())
									+ " WHERE examenes.id = "+ c.getString(0)
									+ " ;");
							i++;
							
						}

						break;

					default:

						break;

					}
					
				} while (c.moveToNext() && i<=6);
				
			}
			showMessage("Alerta", "La materia ha sido actualizada");
			c.close();
		}
			
		db.close();
	
	}

	public void calcularNotas(View view) {

		editText19 = (EditText) findViewById(R.id.editText19);
		editText20 = (EditText) findViewById(R.id.editText20);
		editText23 = (EditText) findViewById(R.id.editText23);
		editText26 = (EditText) findViewById(R.id.editText26);
		editText29 = (EditText) findViewById(R.id.editText29);
		editText32 = (EditText) findViewById(R.id.editText32);
		editText35 = (EditText) findViewById(R.id.editText35);
		spinner_materias_en_materias = (Spinner) findViewById(R.id.spinner_materias_en_materias);
		notaFinal = (EditText) findViewById(R.id.notaFinal);

		db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);

		if (editText19.getText().toString().length() == 0) {
			showMessage("Error", "No hay informacion ingresada");
		} else {

			Cursor c = db.rawQuery("SELECT examenes.id, examenes.examen, "
					+ "examenes.nota, examenes.porcentaje "
					+ "FROM examenes,materias "
					+ "WHERE examenes.idMateria = materias.id "
					+ "AND materias.materia ='"
					+ spinner_materias_en_materias.getSelectedItem().toString()
					+ "'", null);

			int porcentaje = 0;
			int posicion = 0;
			double notaF = 0;
			double promedio = 0;
			double notaPromDes = 0;

			if (notaFinal.getText().toString().length() == 0) {
				showMessage("Error", "Ingrese su nota");
			} else {
				if (Double.parseDouble(notaFinal.getText().toString()) > 5.0) {
					showMessage("Error", "La nota excede el limite");
				} else {

					if (c.moveToFirst()) {
						do {
							promedio = promedio
									+ (Double.parseDouble(c.getString(2)
											.toString()) * (Double
											.parseDouble(c.getString(3)) / 100));

						} while (c.moveToNext());
					}

					notaPromDes = Double.parseDouble(notaFinal.getText()
							.toString()) - promedio;

					if (c.moveToFirst()) {
						do {

							if (c.getString(2).toString().equals("0")) {
								porcentaje = porcentaje
										+ Integer.parseInt(c.getString(3));
							}
						} while (c.moveToNext());
					}

					notaF = (notaPromDes / porcentaje) * 100;

					DecimalFormat REAL_FORMATTER = new DecimalFormat("0.###");

					if (notaF > 5) {
						showMessage("Error",
								"La nota deseada no se puede alcanzar.");
					} else {

						if (c.moveToFirst()) {
							do {
								if (Double.parseDouble(c.getString(2)) == 0) {
									switch (c.getPosition()) {
									case 0:
										editText20.setText(REAL_FORMATTER
												.format(notaF));
										break;
									case 1:
										editText23.setText(REAL_FORMATTER
												.format(notaF));
										break;
									case 2:
										editText26.setText(REAL_FORMATTER
												.format(notaF));
										break;
									case 3:
										editText29.setText(REAL_FORMATTER
												.format(notaF));
										break;
									case 4:
										editText32.setText(REAL_FORMATTER
												.format(notaF));
										break;
									case 5:
										editText35.setText(REAL_FORMATTER
												.format(notaF));
										break;
									}
								}
							} while (c.moveToNext());
						}
						c.close();
					}

				}
			}
		}
	}

	public void calcularNotaFinal(View view) {

		editText19 = (EditText) findViewById(R.id.editText19);
		editText20 = (EditText) findViewById(R.id.editText20);
		editText21 = (EditText) findViewById(R.id.editText21);
		editText22 = (EditText) findViewById(R.id.editText22);
		editText23 = (EditText) findViewById(R.id.editText23);
		editText24 = (EditText) findViewById(R.id.editText24);
		editText25 = (EditText) findViewById(R.id.editText25);
		editText26 = (EditText) findViewById(R.id.editText26);
		editText27 = (EditText) findViewById(R.id.editText27);
		editText28 = (EditText) findViewById(R.id.editText28);
		editText29 = (EditText) findViewById(R.id.editText29);
		editText30 = (EditText) findViewById(R.id.editText30);
		editText31 = (EditText) findViewById(R.id.editText31);
		editText32 = (EditText) findViewById(R.id.editText32);
		editText33 = (EditText) findViewById(R.id.editText33);
		editText34 = (EditText) findViewById(R.id.editText34);
		editText35 = (EditText) findViewById(R.id.editText35);
		editText36 = (EditText) findViewById(R.id.editText36);
		spinner_materias_en_materias = (Spinner) findViewById(R.id.spinner_materias_en_materias);
		notaFinal = (EditText) findViewById(R.id.notaFinal);

		double promedio = 0;

		db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);

		if (editText19.getText().toString().length() == 0) {
			showMessage("Error", "No hay informacion ingresada");
		} else {

			Cursor c = db.rawQuery("SELECT examenes.id, examenes.examen, "
					+ "examenes.nota, examenes.porcentaje "
					+ "FROM examenes,materias "
					+ "WHERE examenes.idMateria = materias.id "
					+ "AND materias.materia ='"
					+ spinner_materias_en_materias.getSelectedItem().toString()
					+ "'", null);

			switch (c.getCount()) {
			case 3:
				promedio = (Double.parseDouble(editText20.getText().toString()) * ((Double
						.parseDouble(editText21.getText().toString())) / 100))
						+ (Double.parseDouble(editText23.getText().toString())
								* ((Double.parseDouble(editText24.getText()
										.toString()))) / 100)
						+ (Double.parseDouble(editText26.getText().toString())
								* ((Double.parseDouble(editText27.getText()
										.toString()))) / 100);
				break;
			case 4:
				promedio = (Double.parseDouble(editText20.getText().toString()) * ((Double
						.parseDouble(editText21.getText().toString())) / 100))
						+ (Double.parseDouble(editText23.getText().toString())
								* ((Double.parseDouble(editText24.getText()
										.toString()))) / 100)
						+ (Double.parseDouble(editText26.getText().toString())
								* ((Double.parseDouble(editText27.getText()
										.toString()))) / 100)
						+ (Double.parseDouble(editText29.getText().toString())
								* ((Double.parseDouble(editText30.getText()
										.toString()))) / 100);
				break;
			case 5:
				promedio = (Double.parseDouble(editText20.getText().toString()) * ((Double
						.parseDouble(editText21.getText().toString())) / 100))
						+ (Double.parseDouble(editText23.getText().toString())
								* ((Double.parseDouble(editText24.getText()
										.toString()))) / 100)
						+ (Double.parseDouble(editText26.getText().toString())
								* ((Double.parseDouble(editText27.getText()
										.toString()))) / 100)
						+ (Double.parseDouble(editText29.getText().toString())
								* ((Double.parseDouble(editText30.getText()
										.toString()))) / 100)
						+ (Double.parseDouble(editText32.getText().toString())
								* ((Double.parseDouble(editText33.getText()
										.toString()))) / 100);
				break;
			case 6:
				promedio = (Double.parseDouble(editText20.getText().toString()) * ((Double
						.parseDouble(editText21.getText().toString())) / 100))
						+ (Double.parseDouble(editText23.getText().toString())
								* ((Double.parseDouble(editText24.getText()
										.toString()))) / 100)
						+ (Double.parseDouble(editText26.getText().toString())
								* ((Double.parseDouble(editText27.getText()
										.toString()))) / 100)
						+ (Double.parseDouble(editText29.getText().toString())
								* ((Double.parseDouble(editText30.getText()
										.toString()))) / 100)
						+ (Double.parseDouble(editText32.getText().toString())
								* ((Double.parseDouble(editText33.getText()
										.toString()))) / 100)
						+ (Double.parseDouble(editText35.getText().toString())
								* ((Double.parseDouble(editText36.getText()
										.toString()))) / 100);
				break;

			}

			DecimalFormat REAL_FORMATTER = new DecimalFormat("0.###");

			notaFinal.setText(REAL_FORMATTER.format(promedio));
		}
	}

	// FRAGMENT PROMEDIO

	public void calcularPromAcum(View view) {
		editText37 = (EditText) findViewById(R.id.editText37);
		editText38 = (EditText) findViewById(R.id.editText38);
		editText39 = (EditText) findViewById(R.id.editText39);
		editText40 = (EditText) findViewById(R.id.editText40);
		editText41 = (EditText) findViewById(R.id.editText41);
		editText42 = (EditText) findViewById(R.id.editText42);
		editText43 = (EditText) findViewById(R.id.editText43);
		editText44 = (EditText) findViewById(R.id.editText44);
		editText45 = (EditText) findViewById(R.id.editText45);
		editText46 = (EditText) findViewById(R.id.editText46);
		editText47 = (EditText) findViewById(R.id.editText47);
		editText48 = (EditText) findViewById(R.id.editText48);
		editText49 = (EditText) findViewById(R.id.editText49);
		editText50 = (EditText) findViewById(R.id.editText50);
		editText51 = (EditText) findViewById(R.id.editText51);
		editText52 = (EditText) findViewById(R.id.editText52);
		editText53 = (EditText) findViewById(R.id.editText53);
		editText54 = (EditText) findViewById(R.id.editText54);
		campo_promedio_deseado_en_promedio = (EditText) findViewById(R.id.campo_promedio_deseado_en_promedio);
		campo_promedio_acumulado_en_promedio = (EditText) findViewById(R.id.campo_promedio_acumulado_en_promedio);
		campo_creditos_cursados_en_promedio = (EditText) findViewById(R.id.campo_creditos_cursados_en_promedio);
		spinner_materias_en_promedio = (Spinner) findViewById(R.id.spinner_materias_en_promedio);

		db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);

		if (editText37.getText().toString().length() == 0) {
			showMessage("Error", "No hay informacion ingresada");
		} else {
			Cursor c = db.rawQuery("SELECT * FROM materias ", null);
			Cursor cur = db.rawQuery("SELECT SUM(creditos*5) FROM materias ",
					null);
			Cursor d = db
					.rawQuery(
							"SELECT materias.materia, examenes.nota, examenes.porcentaje, materias.creditos "
									+ "FROM examenes, materias "
									+ "WHERE examenes.idMateria = materias.id ;",
							null);

			double promReq = 0;
			double creditos = 0;
			double puntaje_max = 0;
			double puntSem = 0;
			double sumaPuntajes = 0;

			puntajes = new ArrayList<DatosPuntaje>();

			if (c.moveToFirst()) {
				do {
					creditos = creditos + Double.parseDouble((c.getString(2)));
				} while (c.moveToNext());
			}

			if (cur.moveToFirst()) {
				puntaje_max = puntaje_max
						+ Double.parseDouble(cur.getString(0).toString());
			}

			if (d.moveToFirst()) {
				do {
					if (d.getString(1).toString().equals("0")) {

						puntajes.add(new DatosPuntaje(d.getString(0), Double
								.parseDouble(d.getString(1)), Double
								.parseDouble(d.getString(2)) / 100, true,
								Double.parseDouble(d.getString(3))));
					} else {
						puntajes.add(new DatosPuntaje(d.getString(0), Double
								.parseDouble(d.getString(1)), Double
								.parseDouble(d.getString(2)) / 100, false,
								Double.parseDouble(d.getString(3))));
					}

				} while (d.moveToNext());

			}

			promReq = ((Double.parseDouble(campo_promedio_deseado_en_promedio
					.getText().toString()) * (Double
					.parseDouble(campo_creditos_cursados_en_promedio.getText()
							.toString()) + creditos)) - (Double
					.parseDouble(campo_promedio_acumulado_en_promedio.getText()
							.toString()) * Double
					.parseDouble(campo_creditos_cursados_en_promedio.getText()
							.toString())))
					/ creditos;

			puntSem = (promReq * puntaje_max) / 5;

			sumaPuntajes = 0;
			for (int i = 0; i < puntajes.size(); i++) {
				sumaPuntajes = sumaPuntajes + puntajes.get(i).getTotal();

			}

			if (sumaPuntajes >= puntSem) {

				showMessage("Alerta",
						"No hay necesidad de continuar, ya se ha alcanzado la nota");

			} else {

				sumaPuntajes = 0;
				for (int i = 0; i < puntajes.size(); i++) {
					if (puntajes.get(i).isMod() == true) {
						puntajes.get(i).setPuntaje(5.0);

					}
					sumaPuntajes = sumaPuntajes + puntajes.get(i).getTotal();

				}

				if (sumaPuntajes < puntSem) {
					showMessage("Alerta",
							"Es imposible alcanzar el promedio deseado");

				} else {

					while (sumaPuntajes >= puntSem) {

						sumaPuntajes = 0;
						for (int i = 0; i < puntajes.size(); i++) {
							if (puntajes.get(i).isMod() == true) {
								puntajes.get(i).setPuntaje(
										puntajes.get(i).getPuntaje() - 0.1);
							}
							sumaPuntajes = sumaPuntajes
									+ puntajes.get(i).getTotal();

						}

					}

					while (sumaPuntajes < puntSem) {

						Random r = new Random();
						int num = r.nextInt(puntajes.size());
						do {
							num = r.nextInt(puntajes.size());
						} while (puntajes.get(num).isMod() == false);
						puntajes.get(num).setPuntaje(
								puntajes.get(num).getPuntaje() + 0.1);

						sumaPuntajes = 0;
						for (int i = 0; i < puntajes.size(); i++) {
							sumaPuntajes = sumaPuntajes
									+ puntajes.get(i).getTotal();
						}

					}

					mostrarPromAcum();

				}

			}
		}

	}

	public void mostrarPromAcum() {

		editText37 = (EditText) findViewById(R.id.editText37);
		editText38 = (EditText) findViewById(R.id.editText38);
		editText39 = (EditText) findViewById(R.id.editText39);
		editText40 = (EditText) findViewById(R.id.editText40);
		editText41 = (EditText) findViewById(R.id.editText41);
		editText42 = (EditText) findViewById(R.id.editText42);
		editText43 = (EditText) findViewById(R.id.editText43);
		editText44 = (EditText) findViewById(R.id.editText44);
		editText45 = (EditText) findViewById(R.id.editText45);
		editText46 = (EditText) findViewById(R.id.editText46);
		editText47 = (EditText) findViewById(R.id.editText47);
		editText48 = (EditText) findViewById(R.id.editText48);
		editText49 = (EditText) findViewById(R.id.editText49);
		editText50 = (EditText) findViewById(R.id.editText50);
		editText51 = (EditText) findViewById(R.id.editText51);
		editText52 = (EditText) findViewById(R.id.editText52);
		editText53 = (EditText) findViewById(R.id.editText53);
		editText54 = (EditText) findViewById(R.id.editText54);
		campo_promedio_deseado_en_promedio = (EditText) findViewById(R.id.campo_promedio_deseado_en_promedio);
		campo_promedio_acumulado_en_promedio = (EditText) findViewById(R.id.campo_promedio_acumulado_en_promedio);
		campo_creditos_cursados_en_promedio = (EditText) findViewById(R.id.campo_creditos_cursados_en_promedio);
		spinner_materias_en_promedio = (Spinner) findViewById(R.id.spinner_materias_en_promedio);

		db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);

		Cursor c = db.rawQuery("SELECT examenes.id, examenes.examen, "
				+ "examenes.nota, examenes.porcentaje "
				+ "FROM examenes,materias "
				+ "WHERE examenes.idMateria = materias.id "
				+ "AND materias.materia ='"
				+ spinner_materias_en_promedio.getSelectedItem().toString()
				+ "'", null);

		DecimalFormat REAL_FORMATTER = new DecimalFormat("0.###");

		ArrayList<Double> valores = new ArrayList<Double>();

		for (int i = 0; i < puntajes.size(); i++) {
			if (puntajes
					.get(i)
					.getNombreMateria()
					.equals(spinner_materias_en_promedio.getSelectedItem()
							.toString())) {
				valores.add(puntajes.get(i).getPuntaje());
			}

		}

		if (c.moveToFirst()) {
			do {
				switch (c.getCount()) {
				case 3:
					if (editText38.getText().toString().equals("0")) {
						editText38
								.setText(REAL_FORMATTER.format(valores.get(0)));
					}
					if (editText41.getText().toString().equals("0")) {
						editText41
								.setText(REAL_FORMATTER.format(valores.get(1)));
					}
					if (editText44.getText().toString().equals("0")) {
						editText44
								.setText(REAL_FORMATTER.format(valores.get(2)));
					}
					break;

				case 4:

					if (editText38.getText().toString().equals("0")) {
						editText38
								.setText(REAL_FORMATTER.format(valores.get(0)));
					}
					if (editText41.getText().toString().equals("0")) {
						editText41
								.setText(REAL_FORMATTER.format(valores.get(1)));
					}
					if (editText44.getText().toString().equals("0")) {
						editText44
								.setText(REAL_FORMATTER.format(valores.get(2)));
					}
					if (editText47.getText().toString().equals("0")) {
						editText47
								.setText(REAL_FORMATTER.format(valores.get(3)));
					}

					break;

				case 5:
					if (editText38.getText().toString().equals("0")) {
						editText38
								.setText(REAL_FORMATTER.format(valores.get(0)));
					}
					if (editText41.getText().toString().equals("0")) {
						editText41
								.setText(REAL_FORMATTER.format(valores.get(1)));
					}
					if (editText44.getText().toString().equals("0")) {
						editText44
								.setText(REAL_FORMATTER.format(valores.get(2)));
					}
					if (editText47.getText().toString().equals("0")) {
						editText47
								.setText(REAL_FORMATTER.format(valores.get(3)));
					}
					if (editText50.getText().toString().equals("0")) {
						editText50
								.setText(REAL_FORMATTER.format(valores.get(4)));
					}
					break;

				case 6:
					if (editText38.getText().toString().equals("0")) {
						editText38
								.setText(REAL_FORMATTER.format(valores.get(0)));
					}
					if (editText41.getText().toString().equals("0")) {
						editText41
								.setText(REAL_FORMATTER.format(valores.get(1)));
					}
					if (editText44.getText().toString().equals("0")) {
						editText44
								.setText(REAL_FORMATTER.format(valores.get(2)));
					}
					if (editText47.getText().toString().equals("0")) {
						editText47
								.setText(REAL_FORMATTER.format(valores.get(3)));
					}
					if (editText50.getText().toString().equals("0")) {
						editText50
								.setText(REAL_FORMATTER.format(valores.get(4)));
					}
					if (editText53.getText().toString().equals("0")) {
						editText53
								.setText(REAL_FORMATTER.format(valores.get(5)));
					}
					break;

				}
			} while (c.moveToNext());
		}
		c.close();
	}

	// OTROS

	public boolean existenMaterias() {

		boolean existe = false;

		db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
		Cursor c = db.rawQuery("SELECT * From materias", null);

		if (c.moveToFirst()) {
			existe = true;
		} else {
			existe = false;
		}

		c.close();
		db.close();

		return existe;
	}

	public void clearText() {
		campo_materia_en_notas = (EditText) findViewById(R.id.campo_materia_en_notas);
		editText1 = (EditText) findViewById(R.id.editText1);
		editText3 = (EditText) findViewById(R.id.editText3);
		editText4 = (EditText) findViewById(R.id.editText4);
		editText6 = (EditText) findViewById(R.id.editText6);
		editText7 = (EditText) findViewById(R.id.editText7);
		editText9 = (EditText) findViewById(R.id.editText9);
		editText10 = (EditText) findViewById(R.id.editText10);
		editText12 = (EditText) findViewById(R.id.editText12);
		editText13 = (EditText) findViewById(R.id.editText13);
		editText15 = (EditText) findViewById(R.id.editText15);
		editText16 = (EditText) findViewById(R.id.editText16);
		editText18 = (EditText) findViewById(R.id.editText18);

		campo_materia_en_notas.setText("");
		editText1.setText("");
		editText3.setText("");
		editText4.setText("");
		editText6.setText("");
		editText7.setText("");
		editText9.setText("");
		editText10.setText("");
		editText12.setText("");
		editText13.setText("");
		editText15.setText("");
		editText16.setText("");
		editText18.setText("");

	}

	public void showMessage(String title, String message) {
		Builder builder = new Builder(this);
		builder.setCancelable(true);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.show();
	}

	// JSON

	public void obtenerDatosJSON() {

		if (isNetworkAvailable()) {
			GetDataTask getDataTask = new GetDataTask();

			getDataTask.execute();
		}

	}

	private boolean isNetworkAvailable() {
		ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		boolean isNetworkAvaible = false;
		if (networkInfo != null && networkInfo.isConnected()) {
			isNetworkAvaible = true;
			Toast.makeText(this, "La red está disponible ", Toast.LENGTH_LONG)
					.show();
		} else {
			Toast.makeText(this, "La red no está disponible ",
					Toast.LENGTH_LONG).show();
		}
		return isNetworkAvaible;
	}

	public class GetDataTask extends AsyncTask<Object, Void, JSONObject> {

		@Override
		protected JSONObject doInBackground(Object... params) {
			int responseCode = -1;
			JSONObject jsonResponse = null;

			try {
				URL blogFeedUsr = new URL(
						"http://augustodesarrollador.com/promedio_app/read.php");

				HttpURLConnection connection = (HttpURLConnection) blogFeedUsr
						.openConnection();
				connection.connect();

				responseCode = connection.getResponseCode();

				if (responseCode == HttpURLConnection.HTTP_OK) {

					try {
						jsonResponse = new JSONObject(
								readUrl("http://augustodesarrollador.com/promedio_app/read.php"));
						Log.v(TAG, jsonResponse.toString());

					} catch (JSONException e) {
						e.printStackTrace();
					}

					// InputStream inputStram = connection.getInputStream();
					// Reader reader = new InputStreamReader(inputStram);
					// char[] charArray = new
					// char[connection.getContentLength()];
					// reader.read(charArray);
					// String responseData = new String(charArray);
					// Log.v(TAG, responseData);
					// jsonResponse = new JSONObject(responseData);
					// Log.e(TAG, "Me metí");
				} else {
					Log.i(TAG,
							"Response code unsuccesful "
									+ String.valueOf(responseCode));
				}

			} catch (MalformedURLException e) {
				Log.e(TAG, "Exception1", e);
			} catch (IOException e) {
				Log.e(TAG, "Exception2", e);
			} catch (Exception e) {
				Log.e(TAG, "Exception3", e);
			}
			return jsonResponse;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			mData = result;
			handleBlogResponse();
		}

	}

	private static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	public void handleBlogResponse() {
		if (mData == null) {

			// updateDisplayForError();
			Toast.makeText(this, "No existe información", Toast.LENGTH_LONG)
					.show();
		} else {
			// Introducir información JSON en la BD
			try {

				JSONArray jsonPosts = mData.getJSONArray("materias");

				db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE,
						null);

				// Recorrido de cada nota en la materia

				Cursor e = null;

				for (int i = 0; i < jsonPosts.length(); i++) {

					JSONObject post = jsonPosts.getJSONObject(i);

					JSONArray jsonPostsComp = post.getJSONArray("componetes");
					String nombreMateria = post.getString("nombre_materia");

					boolean existe = false;

					// Verificar si existe la materia en la BD de acuerdo
					// con JSONObject(i)

					Cursor c = null;
					try {
						c = db.rawQuery(
								"SELECT materia FROM materiasServidor ", null);

						if (c.moveToFirst()) {

							do {
								if (nombreMateria.compareTo(c.getString(0)) == 0) {
									// La materia existe -> Sólo agregar notas
									existe = true;
								} else {
									// La materia no existe -> Agregar materia a
									// la
									// BD

								}
							} while (c.moveToNext() && existe == false);
						} else {

							// Asumo que no hay nada -> Agregar materia a la BD

						}

						if (existe == false) {

							db.execSQL("INSERT INTO materiasServidor (materia,creditos) VALUES('"
									+ nombreMateria + "'," + 0 + ");");

						}

					} finally {
						if (c != null)
							c.close();
					}

					for (int j = 0; j < jsonPostsComp.length(); j++) {

						JSONObject postComp = jsonPostsComp.getJSONObject(j);
						String nombreExamen = postComp.getString("desc");
						String porcentaje = postComp.getString("peso");

						// Hacer otro query para obtener el id de la materia a
						// partir del nombre.
						Cursor d = null;

						try {
							d = db.rawQuery(
									"SELECT id FROM materiasServidor "
											+ "WHERE materia ='"
											+ nombreMateria + "';", null);

							if (d.moveToFirst()) {

								// Verificar si existe la nota del servidor en
								// la
								// materia en la BD de acuerdo con JSONObject(i)

								existe = false;
								e = db.rawQuery(
										"SELECT examen FROM examenesServidor "
												+ "WHERE idMateria = "
												+ d.getString(0) + "; ", null);

								if (e.moveToFirst()) {

									do {

										if (nombreExamen.compareTo(e
												.getString(0)) == 0) {

											// La nota del servidor existe -> No
											// hacer
											// nada.
											existe = true;

										} else {

											// La nota del servidor no existe ->
											// Agregar
											// nota a la BD

										}

									} while (e.moveToNext() && existe == false);

								} else {

									// Asumo que no hay nada -> Agregar nota a
									// la BD

								}

								if (existe == false) {

									// Agregar la nota a la BD
									db.execSQL("INSERT INTO examenesServidor (examen,nota,porcentaje,idMateria) "
											+ "VALUES('"
											+ postComp.getString("desc")
											+ "',0,'"
											+ postComp.getString("peso")
											+ "',"
											+ d.getString(0) + ");");

								}
							}

						} finally {
							if (d != null || e != null) {
								e.close();
								d.close();
							}
						}
					}
				}

				db.close();

			} catch (JSONException e) {
				Log.e(TAG, "Exception caught!", e);
			}
		}
	}
}
