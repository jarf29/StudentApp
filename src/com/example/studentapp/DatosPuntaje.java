package com.example.studentapp;

public class DatosPuntaje {
	
	private String nombreMateria;
	private double puntaje;
	private double peso;
	private double creditos;
	private boolean mod;
		
	public DatosPuntaje(String nombreMateria, double puntaje, double peso, boolean mod, double creditos) {
		super();
		this.nombreMateria = nombreMateria;
		this.puntaje = puntaje;
		this.peso = peso;
		this.mod = mod;
		this.creditos = creditos;
		
	}
	
	public double getTotal(){		
		return (puntaje*peso)*creditos;
		
	}

	public double getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(double puntaje) {
		if (mod==true)
		this.puntaje = puntaje;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		if (mod==true)
		this.peso = peso;
	}

	public double getCreditos() {
		return creditos;
	}

	public void setCreditos(double creditos) {
		this.creditos = creditos;
	}

	public boolean isMod() {
		return mod;
	}

	public void setMod(boolean mod) {
		this.mod = mod;
	}

	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setIdMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}
	
	
	
	
	
	
	
}
