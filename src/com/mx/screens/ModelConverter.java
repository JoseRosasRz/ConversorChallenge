package com.mx.screens;

import java.text.DecimalFormat;

/**
 * Esta clase a traves de los 3 metodos disponibles ejecuta la conversion de temperatura, masa o distancia segun sea el 
 * caso.
 * @author josei
 *
 */

public class ModelConverter {

	Double result; //Almacena el resultado de la conversion
	DecimalFormat decimalFormatTemp = new DecimalFormat("#.##");//Formateo para mostrar dos decimales despues del punto
	DecimalFormat decimalFormatGen = new DecimalFormat("#.#####");//Formateo para mostrar cuatro decimales despues del punto
	
	//Constructor por defecto
	public ModelConverter() {
		
	}
	
	
	/**
	 * Metodo para realizar la conversion entre 3 distintas unidades de medida de temperatura:
	 * Celsius, Farenheit y Kelvin.
	 * 
	 * @param jcombobox1  Recibe el contenido un primer JComboBox como String
	 * @param jcombobox2  Recibe el contenido un segundo JComboBox como String
	 * @param input Recibe el texto ingresado en un JTextField transformado a dato de tipo Double
	 * @return Retorna el resultado de la conversion transformado a String ya que se asignará a un segundo JTextField.
	 */
	public String toConvertTemp(String jcombobox1, String jcombobox2, Double input) {
		
		/*
		 * Validacion que en caso de que el item seleccionado de ambos JComboBox sea el mismo devuelva el valor de "input"
		 * como resultado.
		 * "result" es la variable de tipo Double que almacenará el resultado mismo de la conversion efectuada.
		 */
		if (jcombobox1 == jcombobox2) {
			
			result = input;
			return result.toString();
			
		} else {
			
			/*
			 * Primer switch el cual dicta desde que unidad se va a convertir.
			 * */
			switch (jcombobox1) {
			
				case " Celsius Degrees":
					/*
					 * Segundo switch que indica a que unidad se hará la conversión.
					 */
					switch (jcombobox2) {
						case " Farenheit Degrees":
							result = Double.parseDouble(decimalFormatTemp.format(input * 5/9 +32));
							break;
							
						case " Kelvin Degrees":
							result = Double.parseDouble(decimalFormatTemp.format(input + 273.15));
							break;	
					}
				break;
			
				case " Farenheit Degrees":
					switch (jcombobox2) {
						case " Celsius Degrees":
							result =  Double.parseDouble(decimalFormatTemp.format((input - 32) * 5/9));
							break;
							
						case " Kelvin Degrees":
							result = Double.parseDouble(decimalFormatTemp.format((input - 32) * 5/9 + 273.15));
							break;
					}
				break;
				
				case " Kelvin Degrees":
					switch (jcombobox2) {
						case " Celsius Degrees":
							result = Double.parseDouble(decimalFormatTemp.format(input - 273.15));
							break;
							
						case " Farenheit Degrees":
							result = Double.parseDouble(decimalFormatTemp.format((input - 273.15) * 9/5 + 32));
							break;
				}
				break;
					
			}
			return result.toString();
	}
	}
	
	
	/**
	 * Metodo para realizar la conversion entre distintas unidades de medida de Masa:
	 * Celsius, Farenheit y Kelvin.
	 * @param jcombobox1  Recibe el contenido un primer JComboBox como String
	 * @param jcombobox2  Recibe el contenido un segundo JComboBox como String
	 * @param input Recibe el texto ingresado en un JTextField transformado a dato de tipo Double
	 * @return Retorna el resultado de la conversion transformado a String ya que se asignará a un segundo JTextField.
	 */
	public String toConvertMass(String jcombobox1, String jcombobox2, Double input) {
		if (jcombobox1 == jcombobox2) {
			
			result = input;
			return result.toString();
			
		} else {
		
			switch (jcombobox1) {
			
				case " Gram (g)":	
					switch (jcombobox2) {
					
						case " Kilogram (Kg)":
							result = Double.parseDouble(decimalFormatGen.format(input / 1000));
							break;
							
						case " Ton (T)":
							result = Double.parseDouble(decimalFormatGen.format(input / 1e+6));
							break;
							
						case " Pound (lb)":
							result = Double.parseDouble(decimalFormatGen.format(input / 453.6));
							break;
							
						case " Ounce (oz)":
							result = Double.parseDouble(decimalFormatGen.format(input / 28.35));
							break;
							
					}
				break;
				
				case " Kilogram (Kg)":
					switch (jcombobox2) {
					
						case " Gram (g)":
							result = Double.parseDouble(decimalFormatGen.format(input * 1000));
							break;
							
						case " Ton (T)":
							result = Double.parseDouble(decimalFormatGen.format(input / 1000));
							break;
							
						case " Pound (lb)":
							result = Double.parseDouble(decimalFormatGen.format(input * 2.205));
							break;
							
						case " Ounce (oz)":
							result = Double.parseDouble(decimalFormatGen.format(input * 35.274));
							break;
							
					}
				break;
				
				case " Ton (T)":
					switch (jcombobox2) {
					
						case " Gram (g)":
							result = Double.parseDouble(decimalFormatGen.format(input * 1e+6));
							break;
							
						case " Kilogram (Kg)":
							result = Double.parseDouble(decimalFormatGen.format(input * 1000));
							break;
							
						case " Pound (lb)":
							result = Double.parseDouble(decimalFormatGen.format(input * 2205));
							break;
							
						case " Ounce (oz)":
							result = Double.parseDouble(decimalFormatGen.format(input * 35270));
							break;
					
					}
				break;
				
				case " Pound (lb)":
					switch (jcombobox2) {
					
						case " Gram (g)":
							result = Double.parseDouble(decimalFormatGen.format(input * 453.6));
							break;
							
						case " Kilogram (Kg)":
							result = Double.parseDouble(decimalFormatGen.format(input / 2.205));
							break;
							
						case " Ton (T)":
							result = Double.parseDouble(decimalFormatGen.format(input / 2205));
							break;
							
						case " Ounce (oz)":
							result = Double.parseDouble(decimalFormatGen.format(input * 16));
							break;
					
					}
				break;
				
				case " Ounce (oz)":
					switch (jcombobox2) {
					
						case " Gram (g)":
							result = Double.parseDouble(decimalFormatGen.format(input * 28.35));
							break;
							
						case " Kilogram (Kg)":
							result = Double.parseDouble(decimalFormatGen.format(input / 35.274));
							break;
							
						case " Ton (T)":
							result = Double.parseDouble(decimalFormatGen.format(input / 35270));
							break;
							
						case " Pound (lb)":
							result = Double.parseDouble(decimalFormatGen.format(input / 16));
							break;
					
					}
				break;
			}
		}
			return result.toString();
		}
	
	/**
	 * Metodo para realizar la conversion entre distintas unidades de medida de Distancia o Longitud:
	 * Celsius, Farenheit y Kelvin.
	 * @param jcombobox1  Recibe el contenido un primer JComboBox como String
	 * @param jcombobox2  Recibe el contenido un segundo JComboBox como String
	 * @param input Recibe el texto ingresado en un JTextField transformado a dato de tipo Double
	 * @return Retorna el resultado de la conversion transformado a String ya que se asignará a un segundo JTextField.
	 */
	public String toConvertDistance(String jcombobox1, String jcombobox2, Double input) {

		if (jcombobox1 == jcombobox2) {
			
			result = input;
			return result.toString();
			
		} else {
			
			switch (jcombobox1) {
			
				case " Centimeter (cm)":
					switch(jcombobox2) {
					
						case " Meter (m)":
							result = Double.parseDouble(decimalFormatGen.format(input / 100));
							break;
							
						case " Kilometer (Km)":
							result = Double.parseDouble(decimalFormatGen.format(input / 1e+5));
							break;
							
						case " Inch (in)":
							result = Double.parseDouble(decimalFormatGen.format(input / 2.54));
							break;
							
						case " Foot (ft)":
							result = Double.parseDouble(decimalFormatGen.format(input / 30.48));
							break;
							
						case " Mile (M)":
							result = Double.parseDouble(decimalFormatGen.format(input / 160900));
							break;
					}
				break;
				
				case " Meter (m)":
					switch(jcombobox2) {
					
						case " Centimeter (cm)":
							result = Double.parseDouble(decimalFormatGen.format(input * 100));
							break;
							
						case " Kilometer (Km)":
							result = Double.parseDouble(decimalFormatGen.format(input / 1000));
							break;
							
						case " Inch (in)":
							result = Double.parseDouble(decimalFormatGen.format(input * 39.37));
							break;
							
						case " Foot (ft)":
							result = Double.parseDouble(decimalFormatGen.format(input * 3.281));
							break;
							
						case " Mile (M)":
							result = Double.parseDouble(decimalFormatGen.format(input / 1609));
							break;
					}
				break;
				
				case " Kilometer (Km)":
					switch(jcombobox2) {
					
						case " Meter (m)":
							result = Double.parseDouble(decimalFormatGen.format(input * 1000));
							break;
							
						case " Centimeter (cm)":
							result = Double.parseDouble(decimalFormatGen.format(input * 1e+5));
							break;
							
						case " Inch (in)":
							result = Double.parseDouble(decimalFormatGen.format(input * 39370));
							break;
							
						case " Foot (ft)":
							result = Double.parseDouble(decimalFormatGen.format(input * 3281));
							break;
							
						case " Mile (M)":
							result = Double.parseDouble(decimalFormatGen.format(input / 1.609));
							break;
					}
				break;
				
				case " Inch (in)":
					switch(jcombobox2) {
					
						case " Meter (m)":
							result = Double.parseDouble(decimalFormatGen.format(input / 39.37));
							break;
							
						case " Kilometer (Km)":
							result = Double.parseDouble(decimalFormatGen.format(input / 39370));
							break;
							
						case " Centimeter (cm)":
							result = Double.parseDouble(decimalFormatGen.format(input * 2.54));
							break;
							
						case " Foot (ft)":
							result = Double.parseDouble(decimalFormatGen.format(input / 12));
							break;
							
						case " Mile (M)":
							result = Double.parseDouble(decimalFormatGen.format(input / 63360));
							break;
					}
				break;
				
				case " Foot (ft)":
					switch(jcombobox2) {
					
						case " Meter (m)":
							result = Double.parseDouble(decimalFormatGen.format(input / 3.281));
							break;
							
						case " Kilometer (Km)":
							result = Double.parseDouble(decimalFormatGen.format(input / 3281));
							break;
							
						case " Inch (in)":
							result = Double.parseDouble(decimalFormatGen.format(input * 12));
							break;
							
						case " Centimeter (cm)":
							result = Double.parseDouble(decimalFormatGen.format(input * 30.48));
							break;
							
						case " Mile (M)":
							result = Double.parseDouble(decimalFormatGen.format(input / 5280));
							break;
					}
				break;
				
				case " Mile (M)":
					switch(jcombobox2) {
					
						case " Meter (m)":
							result = Double.parseDouble(decimalFormatGen.format(input * 1609));
							break;
							
						case " Kilometer (Km)":
							result = Double.parseDouble(decimalFormatGen.format(input * 1.609));
							break;
							
						case " Inch (in)":
							result = Double.parseDouble(decimalFormatGen.format(input * 63360));
							break;
							
						case " Foot (ft)":
							result = Double.parseDouble(decimalFormatGen.format(input * 5280));
							break;
							
						case " Centimeter (cm)":
							result = Double.parseDouble(decimalFormatGen.format(input * 160900));
							break;
					}
				break;
			}
			
		}
		return result.toString();
	}
	
}

