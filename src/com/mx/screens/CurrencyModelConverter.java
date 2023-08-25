package com.mx.screens;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Clase que a traves del unico metodo realiza la conversion entre 10 diferentes divisas:
 * USD, MXN, CAD, BRL, PEN, COP, EUR, JPY, CNY, RUB.
 * Para la conversion se consume una API de uso gratuitp que otorga el precio de las divisas a tiempo real con actualizaciones
 * constantes. Link de API: https://www.exchangerate-api.com/
 * @author josei
 *
 */
public class CurrencyModelConverter{
	
	//Variable creadas para reducir la escritura del nombre de cada divisa contenida dentro de los JComboBox 
	final String usd = " American Dollar (USD)";
	final String mxn = " Mexican Peso (MXN)";
	final String cad = "Canadian Dollar (CAD)";
	final String brl = "Brazilian Real (BRL)";
	final String pen = "Peruvian Sol (PEN)";
	final String cop = "Colombian Peso (COP)";
	final String eur = "Euro (EUR)";
	final String jpy = "Japanese Yen (JPY)";
	final String cny = "Chinese Yuan (CNY)";
	final String rub = "Russian Ruble (RUB)";
	
	Double result, requestResult;//resultado de la conversion//resultado de la consulta al endpoint de la API
	DecimalFormat decimalFormatCurrency = new DecimalFormat("#.##");//formato para delimitar a 2 decimales despues del cero.
	
	HttpURLConnection request;
	URL url;
	JsonParser jp;
	JsonElement rootElement;
	JsonObject jsonobj, jsonSubObj;
	
	
	//Constructor vacio por defecto
	public CurrencyModelConverter() {
		
	}
	
	/**
	 * Metodo para realizar la conversion entre 10 distintas divisas de LATAM e Interoceanicas.
	 * 
	 * @param jcombobox1  Recibe el contenido un primer JComboBox como String, la divisa desde la que se hará la conversion
	 * @param jcombobox2  Recibe el contenido un segundo JComboBox como String, la divisa a la que se hará la conversion
	 * @param input Recibe el texto ingresado en un JTextField transformado a dato de tipo Double, es la cantidad
	 * @return Retorna el resultado de la conversion transformado a String ya que se asignará a un segundo JTextField.
	 */
	public String toConvertCurrency (String jcombobox1, String jcombobox2, Double input) {
		/*
		 * Validacion que en caso de que el item seleccionado de ambos JComboBox sea el mismo devuelva el valor de "input"
		 * como resultado.
		 * "result" es la variable de tipo Double que almacenará el resultado mismo de la conversion efectuada.
		 */
		if (jcombobox1 == jcombobox2) {
			
			return input.toString();
			
		}	else {
			
			try {
			/*
			 * Primer switch, este determina desde que divisa se hará la conversion
			 */
			switch (jcombobox1) {
				case usd:
					
					//Haciendo la solicitud al endpoint
					url = new URL("https://v6.exchangerate-api.com/v6/53b19674234c4c3f3d821dea/latest/USD");
					request = (HttpURLConnection) url.openConnection();
					request.connect();
					
					//Convertir a Json
					jp = new JsonParser();
					rootElement = jp.parse(new InputStreamReader((InputStream) request.getContent()));
					jsonobj = rootElement.getAsJsonObject();
					jsonSubObj = jsonobj.get("conversion_rates").getAsJsonObject();
					
					/*
					 * Los siguientes switchs contenidos dentro de cada case especifico por divisa, determinan
					 * hacia que divisa se hará la conversion.
					 */
					switch (jcombobox2) {
						
						case mxn:
							requestResult = Double.parseDouble(jsonSubObj.get("MXN").getAsString());
							break;
						
						case cad:
							requestResult = Double.parseDouble(jsonSubObj.get("CAD").getAsString());
							break;
						
						case brl:
							requestResult = Double.parseDouble(jsonSubObj.get("BRL").getAsString());
							break;
							
						case pen:
							requestResult = Double.parseDouble(jsonSubObj.get("PEN").getAsString());
							break;
							
						case cop:
							requestResult = Double.parseDouble(jsonSubObj.get("COP").getAsString());
							break;
							
						case eur:
							requestResult = Double.parseDouble(jsonSubObj.get("EUR").getAsString());
							break;
							
						case jpy:
							requestResult = Double.parseDouble(jsonSubObj.get("JPY").getAsString());
							break;
							
						case cny:
							requestResult = Double.parseDouble(jsonSubObj.get("CNY").getAsString());
							break;
							
						case rub:
							requestResult = Double.parseDouble(jsonSubObj.get("RUB").getAsString());
							break;
					}
				break;
				
				case mxn:
					
					//Haciendo la solicitud
					url = new URL("https://v6.exchangerate-api.com/v6/53b19674234c4c3f3d821dea/latest/MXN");
					request = (HttpURLConnection) url.openConnection();
					request.connect();
					
					//Convertir a Json
					jp = new JsonParser();
					rootElement = jp.parse(new InputStreamReader((InputStream) request.getContent()));
					jsonobj = rootElement.getAsJsonObject();
					jsonSubObj = jsonobj.get("conversion_rates").getAsJsonObject();
					
					switch (jcombobox2) {
						
						case usd:
							requestResult = Double.parseDouble(jsonSubObj.get("USD").getAsString());
							break;
						
						case cad:
							requestResult = Double.parseDouble(jsonSubObj.get("CAD").getAsString());
							break;
						
						case brl:
							requestResult = Double.parseDouble(jsonSubObj.get("BRL").getAsString());
							break;
							
						case pen:
							requestResult = Double.parseDouble(jsonSubObj.get("PEN").getAsString());
							break;
							
						case cop:
							requestResult = Double.parseDouble(jsonSubObj.get("COP").getAsString());
							break;
							
						case eur:
							requestResult = Double.parseDouble(jsonSubObj.get("EUR").getAsString());
							break;
							
						case jpy:
							requestResult = Double.parseDouble(jsonSubObj.get("JPY").getAsString());
							break;
							
						case cny:
							requestResult = Double.parseDouble(jsonSubObj.get("CNY").getAsString());
							break;
							
						case rub:
							requestResult = Double.parseDouble(jsonSubObj.get("RUB").getAsString());
							break;
					}
				break;
				
				case cad:
					
					//Haciendo la solicitud
					url = new URL("https://v6.exchangerate-api.com/v6/53b19674234c4c3f3d821dea/latest/CAD");
					request = (HttpURLConnection) url.openConnection();
					request.connect();
					
					//Convertir a Json
					jp = new JsonParser();
					rootElement = jp.parse(new InputStreamReader((InputStream) request.getContent()));
					jsonobj = rootElement.getAsJsonObject();
					jsonSubObj = jsonobj.get("conversion_rates").getAsJsonObject();
					
					switch (jcombobox2) {
						
						case usd:
							requestResult = Double.parseDouble(jsonSubObj.get("USD").getAsString());
							break;
						
						case mxn:
							requestResult = Double.parseDouble(jsonSubObj.get("MXN").getAsString());
							break;
						
						case brl:
							requestResult = Double.parseDouble(jsonSubObj.get("BRL").getAsString());
							break;
							
						case pen:
							requestResult = Double.parseDouble(jsonSubObj.get("PEN").getAsString());
							break;
							
						case cop:
							requestResult = Double.parseDouble(jsonSubObj.get("COP").getAsString());
							break;
							
						case eur:
							requestResult = Double.parseDouble(jsonSubObj.get("EUR").getAsString());
							break;
							
						case jpy:
							requestResult = Double.parseDouble(jsonSubObj.get("JPY").getAsString());
							break;
							
						case cny:
							requestResult = Double.parseDouble(jsonSubObj.get("CNY").getAsString());
							break;
							
						case rub:
							requestResult = Double.parseDouble(jsonSubObj.get("RUB").getAsString());
							break;
					}
				break;
				
				case brl:
					
					//Haciendo la solicitud
					url = new URL("https://v6.exchangerate-api.com/v6/53b19674234c4c3f3d821dea/latest/BRL");
					request = (HttpURLConnection) url.openConnection();
					request.connect();
					
					//Convertir a Json
					jp = new JsonParser();
					rootElement = jp.parse(new InputStreamReader((InputStream) request.getContent()));
					jsonobj = rootElement.getAsJsonObject();
					jsonSubObj = jsonobj.get("conversion_rates").getAsJsonObject();
					
					switch (jcombobox2) {
						
						case usd:
							requestResult = Double.parseDouble(jsonSubObj.get("USD").getAsString());
							break;
						
						case cad:
							requestResult = Double.parseDouble(jsonSubObj.get("CAD").getAsString());
							break;
						
						case mxn:
							requestResult = Double.parseDouble(jsonSubObj.get("MXN").getAsString());
							break;
							
						case pen:
							requestResult = Double.parseDouble(jsonSubObj.get("PEN").getAsString());
							break;
							
						case cop:
							requestResult = Double.parseDouble(jsonSubObj.get("COP").getAsString());
							break;
							
						case eur:
							requestResult = Double.parseDouble(jsonSubObj.get("EUR").getAsString());
							break;
							
						case jpy:
							requestResult = Double.parseDouble(jsonSubObj.get("JPY").getAsString());
							break;
							
						case cny:
							requestResult = Double.parseDouble(jsonSubObj.get("CNY").getAsString());
							break;
							
						case rub:
							requestResult = Double.parseDouble(jsonSubObj.get("RUB").getAsString());
							break;
					}
				break;
				
				case pen:
					
					//Haciendo la solicitud
					url = new URL("https://v6.exchangerate-api.com/v6/53b19674234c4c3f3d821dea/latest/PEN");
					request = (HttpURLConnection) url.openConnection();
					request.connect();
					
					//Convertir a Json
					jp = new JsonParser();
					rootElement = jp.parse(new InputStreamReader((InputStream) request.getContent()));
					jsonobj = rootElement.getAsJsonObject();
					jsonSubObj = jsonobj.get("conversion_rates").getAsJsonObject();
					
					switch (jcombobox2) {
						
						case usd:
							requestResult = Double.parseDouble(jsonSubObj.get("USD").getAsString());
							break;
						
						case cad:
							requestResult = Double.parseDouble(jsonSubObj.get("CAD").getAsString());
							break;
						
						case brl:
							requestResult = Double.parseDouble(jsonSubObj.get("BRL").getAsString());
							break;
							
						case mxn:
							requestResult = Double.parseDouble(jsonSubObj.get("MXN").getAsString());
							break;
							
						case cop:
							requestResult = Double.parseDouble(jsonSubObj.get("COP").getAsString());
							break;
							
						case eur:
							requestResult = Double.parseDouble(jsonSubObj.get("EUR").getAsString());
							break;
							
						case jpy:
							requestResult = Double.parseDouble(jsonSubObj.get("JPY").getAsString());
							break;
							
						case cny:
							requestResult = Double.parseDouble(jsonSubObj.get("CNY").getAsString());
							break;
							
						case rub:
							requestResult = Double.parseDouble(jsonSubObj.get("RUB").getAsString());
							break;
					}
				break;
				
				case cop:
					
					//Haciendo la solicitud
					url = new URL("https://v6.exchangerate-api.com/v6/53b19674234c4c3f3d821dea/latest/COP");
					request = (HttpURLConnection) url.openConnection();
					request.connect();
					
					//Convertir a Json
					jp = new JsonParser();
					rootElement = jp.parse(new InputStreamReader((InputStream) request.getContent()));
					jsonobj = rootElement.getAsJsonObject();
					jsonSubObj = jsonobj.get("conversion_rates").getAsJsonObject();
					
					switch (jcombobox2) {
						
						case usd:
							requestResult = Double.parseDouble(jsonSubObj.get("USD").getAsString());
							break;
						
						case cad:
							requestResult = Double.parseDouble(jsonSubObj.get("CAD").getAsString());
							break;
						
						case brl:
							requestResult = Double.parseDouble(jsonSubObj.get("BRL").getAsString());
							break;
							
						case pen:
							requestResult = Double.parseDouble(jsonSubObj.get("PEN").getAsString());
							break;
							
						case mxn:
							requestResult = Double.parseDouble(jsonSubObj.get("MXN").getAsString());
							break;
							
						case eur:
							requestResult = Double.parseDouble(jsonSubObj.get("EUR").getAsString());
							break;
							
						case jpy:
							requestResult = Double.parseDouble(jsonSubObj.get("JPY").getAsString());
							break;
							
						case cny:
							requestResult = Double.parseDouble(jsonSubObj.get("CNY").getAsString());
							break;
							
						case rub:
							requestResult = Double.parseDouble(jsonSubObj.get("RUB").getAsString());
							break;
					}
				break;
				
				case eur:
					
					//Haciendo la solicitud
					url = new URL("https://v6.exchangerate-api.com/v6/53b19674234c4c3f3d821dea/latest/EUR");
					request = (HttpURLConnection) url.openConnection();
					request.connect();
					
					//Convertir a Json
					jp = new JsonParser();
					rootElement = jp.parse(new InputStreamReader((InputStream) request.getContent()));
					jsonobj = rootElement.getAsJsonObject();
					jsonSubObj = jsonobj.get("conversion_rates").getAsJsonObject();
					
					switch (jcombobox2) {
						
						case usd:
							requestResult = Double.parseDouble(jsonSubObj.get("USD").getAsString());
							break;
						
						case cad:
							requestResult = Double.parseDouble(jsonSubObj.get("CAD").getAsString());
							break;
						
						case brl:
							requestResult = Double.parseDouble(jsonSubObj.get("BRL").getAsString());
							break;
							
						case pen:
							requestResult = Double.parseDouble(jsonSubObj.get("PEN").getAsString());
							break;
							
						case cop:
							requestResult = Double.parseDouble(jsonSubObj.get("COP").getAsString());
							break;
							
						case mxn:
							requestResult = Double.parseDouble(jsonSubObj.get("MXN").getAsString());
							break;
							
						case jpy:
							requestResult = Double.parseDouble(jsonSubObj.get("JPY").getAsString());
							break;
							
						case cny:
							requestResult = Double.parseDouble(jsonSubObj.get("CNY").getAsString());
							break;
							
						case rub:
							requestResult = Double.parseDouble(jsonSubObj.get("RUB").getAsString());
							break;
					}
				break;
				
				case jpy:
					
					//Haciendo la solicitud
					url = new URL("https://v6.exchangerate-api.com/v6/53b19674234c4c3f3d821dea/latest/JPY");
					request = (HttpURLConnection) url.openConnection();
					request.connect();
					
					//Convertir a Json
					jp = new JsonParser();
					rootElement = jp.parse(new InputStreamReader((InputStream) request.getContent()));
					jsonobj = rootElement.getAsJsonObject();
					jsonSubObj = jsonobj.get("conversion_rates").getAsJsonObject();
					
					switch (jcombobox2) {
						
						case usd:
							requestResult = Double.parseDouble(jsonSubObj.get("USD").getAsString());
							break;
						
						case cad:
							requestResult = Double.parseDouble(jsonSubObj.get("CAD").getAsString());
							break;
						
						case brl:
							requestResult = Double.parseDouble(jsonSubObj.get("BRL").getAsString());
							break;
							
						case pen:
							requestResult = Double.parseDouble(jsonSubObj.get("PEN").getAsString());
							break;
							
						case cop:
							requestResult = Double.parseDouble(jsonSubObj.get("COP").getAsString());
							break;
							
						case eur:
							requestResult = Double.parseDouble(jsonSubObj.get("EUR").getAsString());
							break;
							
						case mxn:
							requestResult = Double.parseDouble(jsonSubObj.get("MXN").getAsString());
							break;
							
						case cny:
							requestResult = Double.parseDouble(jsonSubObj.get("CNY").getAsString());
							break;
							
						case rub:
							requestResult = Double.parseDouble(jsonSubObj.get("RUB").getAsString());
							break;
					}
				break;
				
				case cny:
					
					//Haciendo la solicitud
					url = new URL("https://v6.exchangerate-api.com/v6/53b19674234c4c3f3d821dea/latest/CNY");
					request = (HttpURLConnection) url.openConnection();
					request.connect();
					
					//Convertir a Json
					jp = new JsonParser();
					rootElement = jp.parse(new InputStreamReader((InputStream) request.getContent()));
					jsonobj = rootElement.getAsJsonObject();
					jsonSubObj = jsonobj.get("conversion_rates").getAsJsonObject();
					
					switch (jcombobox2) {
						
						case usd:
							requestResult = Double.parseDouble(jsonSubObj.get("USD").getAsString());
							break;
						
						case cad:
							requestResult = Double.parseDouble(jsonSubObj.get("CAD").getAsString());
							break;
						
						case brl:
							requestResult = Double.parseDouble(jsonSubObj.get("BRL").getAsString());
							break;
							
						case pen:
							requestResult = Double.parseDouble(jsonSubObj.get("PEN").getAsString());
							break;
							
						case cop:
							requestResult = Double.parseDouble(jsonSubObj.get("COP").getAsString());
							break;
							
						case eur:
							requestResult = Double.parseDouble(jsonSubObj.get("EUR").getAsString());
							break;
							
						case jpy:
							requestResult = Double.parseDouble(jsonSubObj.get("JPY").getAsString());
							break;
							
						case mxn:
							requestResult = Double.parseDouble(jsonSubObj.get("MXN").getAsString());
							break;
							
						case rub:
							requestResult = Double.parseDouble(jsonSubObj.get("RUB").getAsString());
							break;
					}
				break;
				
				case rub:
					
					//Haciendo la solicitud
					url = new URL("https://v6.exchangerate-api.com/v6/53b19674234c4c3f3d821dea/latest/RUB");
					request = (HttpURLConnection) url.openConnection();
					request.connect();
					
					//Convertir a Json
					jp = new JsonParser();
					rootElement = jp.parse(new InputStreamReader((InputStream) request.getContent()));
					jsonobj = rootElement.getAsJsonObject();
					jsonSubObj = jsonobj.get("conversion_rates").getAsJsonObject();
					
					switch (jcombobox2) {
						
						case usd:
							requestResult = Double.parseDouble(jsonSubObj.get("USD").getAsString());
							break;
						
						case cad:
							requestResult = Double.parseDouble(jsonSubObj.get("CAD").getAsString());
							break;
						
						case brl:
							requestResult = Double.parseDouble(jsonSubObj.get("BRL").getAsString());
							break;
							
						case pen:
							requestResult = Double.parseDouble(jsonSubObj.get("PEN").getAsString());
							break;
							
						case cop:
							requestResult = Double.parseDouble(jsonSubObj.get("COP").getAsString());
							break;
							
						case eur:
							requestResult = Double.parseDouble(jsonSubObj.get("EUR").getAsString());
							break;
							
						case jpy:
							requestResult = Double.parseDouble(jsonSubObj.get("JPY").getAsString());
							break;
							
						case cny:
							requestResult = Double.parseDouble(jsonSubObj.get("CNY").getAsString());
							break;
							
						case mxn:
							requestResult = Double.parseDouble(jsonSubObj.get("MXN").getAsString());
							break;
					}
				break;
					
			}
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			result = Double.parseDouble(decimalFormatCurrency.format(input * requestResult));
			return result.toString();
		}
	}
	
}
