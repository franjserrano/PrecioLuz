package consumoapi;

/**
 * Constantes necesarias para el funcionamiento de la aplicación
 */
public class Constantes {
	static String[] HORAS = {"00-01","01-02","02-03","03-04","04-05","05-06","06-07","07-08","08-09","09-10","10-11","11-12","12-13","13-14","14-15","15-16","16-17","17-18","18-19","19-20","20-21","21-22","22-23","23-24"};
	
	static String TOTAL_PRECIOS = "https://api.preciodelaluz.org/v1/prices/all?zone=PCB";
	
	//Campos del JSON
	static String FECHA 	 = "date";
	static String HORA 		 = "hour";
	static String ES_BARATO  = "is-cheap";
	static String BAJO_MEDIA = "is-under-avg";
	static String MERCADO 	 = "market";
	static String PRECIO 	 = "price";
	
}
