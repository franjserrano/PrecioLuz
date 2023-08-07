package consumoapi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class DatosHorarios {
	String fecha;
	String hora;
	String isCheap;
	String isUnderAvg;
	String mercado;
	String precio;
	BigDecimal precioNum;
	String unidades = "€/MWh";
	
	public DatosHorarios(String fecha, String hora, String isCheap, String isUnderAvg, String mercado, String precio) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.isCheap = isCheap;
		this.isUnderAvg = isUnderAvg;
		this.mercado = mercado;
		this.precio = precio;
		
		transformaPrecio();
		
	}

	public void transformaPrecio() {
		this.precioNum = new BigDecimal(precio).divide(new BigDecimal(1000),3,RoundingMode.HALF_EVEN);
		
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getIsCheap() {
		return isCheap;
	}

	public void setIsCheap(String isCheap) {
		this.isCheap = isCheap;
	}

	public String getIsUnderAvg() {
		return isUnderAvg;
	}

	public void setIsUnderAvg(String isUnderAvg) {
		this.isUnderAvg = isUnderAvg;
	}

	public String getMercado() {
		return mercado;
	}

	public void setMercado(String mercado) {
		this.mercado = mercado;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getUnidades() {
		return unidades;
	}

	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}
	
	public String toString() {
		String mensaje = "A dia "+fecha+" el precio en el rango de horas "+hora+" es de "+precioNum+"€/kWh";
		
		if(Boolean.parseBoolean(isUnderAvg) && Boolean.parseBoolean(isCheap)) {
			mensaje += ", en los precios más bajos del día";
		}
		else if(Boolean.parseBoolean(isUnderAvg) && !Boolean.parseBoolean(isCheap)) {
			mensaje += ", bajo la media";
		}
		if(!Boolean.parseBoolean(isUnderAvg) && Boolean.parseBoolean(isCheap)) {
			mensaje += ", precio bajo";
		}
		return mensaje;
	}
	
}
