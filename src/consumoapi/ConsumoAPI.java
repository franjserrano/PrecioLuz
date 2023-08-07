package consumoapi;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.net.HttpURLConnection;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class ConsumoAPI {

	public static void main(String[] args) {
		// Solicitar petición
		// https://api.preciodelaluz.org/v1/prices/all?zone=PCB
		
				
		try {
			URL url = new URL("https://api.preciodelaluz.org/v1/prices/all?zone=PCB");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			conn.connect();
			
			Map<String,DatosHorarios> preciosHora = new TreeMap<String, DatosHorarios>();
			
			// Comprobar si ha sido correcta
			int responseCode = conn.getResponseCode();
			if(responseCode != 200) {
				throw new RuntimeException("Error :"+responseCode);
			}else {
				//Leer datos
				StringBuilder informationString = new StringBuilder();
				Scanner scanner = new Scanner(url.openStream());
				
				while(scanner.hasNext()) {
					informationString.append(scanner.nextLine());
				}
						
				scanner.close();
				
				JSONParser jparser = new JSONParser();
				JSONObject obj = (JSONObject) jparser.parse(informationString.toString());
				
				for(String hora:Constantes.HORAS) {
					JSONObject obt = (JSONObject) obj.get(hora);
					preciosHora.put(hora, new DatosHorarios(obt.get("date").toString(), 
															obt.get("hour").toString(), 
															obt.get("is-cheap").toString(), 
															obt.get("is-under-avg").toString(), 
															obt.get("market").toString(), 
															obt.get("price").toString()));
				}
				
				
				/*************************/
				//Pintar información
				
				//System.out.println(informationString);
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
