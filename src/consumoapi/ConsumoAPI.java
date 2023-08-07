package consumoapi;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.net.HttpURLConnection;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/**
 * Clase de llamada, utilizaremos una API pública para recuperar los precios de la luz
 */
public class ConsumoAPI {

	/**
	 * Clase main. Desde ella realizaremos tres acciones:
	 * - Llamar a la API pública
	 * - Leer los datos
	 * - Pintar la información formateada
	 * @param args Parámetros por defecto de la función main. Sin uso en esta aplicación. 
	 */
	public static void main(String[] args) {
		// Solicitar petición
		// https://api.preciodelaluz.org/v1/prices/all?zone=PCB
		
		Map<String,DatosHorarios> preciosHora = new TreeMap<String, DatosHorarios>();
				
		try {
			//Creamos la URL y la conexión al API
			URL url = new URL(Constantes.TOTAL_PRECIOS);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			conn.connect();
			
			/*Confirmamos que se haya conectado correctamente.
			 * Esto es así si la respuesta recibida por el API es 200.
			 * En caso de no recibir un 200, puede haber pasado algo, por lo que lanzamos una execpión*/
			int responseCode = conn.getResponseCode();
			if(responseCode != 200) {
				throw new RuntimeException("Error :"+responseCode);
			}else {
				//Leemos los datos recibidos por el API
				StringBuilder informationString = new StringBuilder();
				Scanner scanner = new Scanner(url.openStream());
				
				while(scanner.hasNext()) {
					informationString.append(scanner.nextLine());
				}
						
				scanner.close();
				
				/* Parseamos a JSON para poder tratarlos con más facilidad, para ello necesitamos
				 * importar previamente la librería json-simple*/
				JSONParser jparser = new JSONParser();
				JSONObject obj = (JSONObject) jparser.parse(informationString.toString());
				
				/* Sabemos que el JSON que devuelve tiene siempre los mismos datos separados por hora,
				 * recorremos cada hora para recoger la información*/
				for(String hora:Constantes.HORAS) {
					JSONObject obt = (JSONObject) obj.get(hora);
					preciosHora.put(hora, new DatosHorarios(obt.get(Constantes.FECHA).toString(), 
															obt.get(Constantes.HORA).toString(), 
															obt.get(Constantes.ES_BARATO).toString(), 
															obt.get(Constantes.BAJO_MEDIA).toString(), 
															obt.get(Constantes.MERCADO).toString(), 
															obt.get(Constantes.PRECIO).toString()));
				}
				
				/*************************/
				//Pintar información
				
				//Pintamos los datos guardados en el Map (TreeMap, ordenado naturalmente por su nombre de key)
				Iterator it = preciosHora.keySet().iterator();
				
				while(it.hasNext()) {
					String key = (String) it.next();
					System.out.println(preciosHora.get(key));
				}
				
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
