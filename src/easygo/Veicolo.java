package easygo;

public class Veicolo {
	
	private String Targa;
	private String Marca;
	private String Modello;
	private String Gruppo; //questo sarebbe il cluster di appartenenza
	private String Stato;
	private int Km;
	private String Danni;
	
	public void setTarga(String Targa) {
		
		this.Targa = Targa;
	}
	public String getTarga() {

	return Targa;
	}
	
	public void setMarca(String Marca) {
		
		this.Marca = Marca;
	}
	public String getMarca() {

	return Marca;
	}
	
public void setModello(String Modello) {
		
		this.Modello = Modello;
	}
	public String getModello() {

	return Modello;
	}
	
public void setGruppo(String Gruppo) {
		
		this.Gruppo = Gruppo;
	}
	public String getGruppo() {

	return Gruppo;
	}
	
	public void setStato(String Stato) {
		
		this.Stato = Stato;
	}
	public String getStato() {

	return Stato;
	}
	
	public void setKm(int Km) {
		
		this.Km = Km;
	}
	public int getKm() {

	return Km;
	}
	
	public void setDanni(String Danni) {
		
		this.Danni = Danni;
	}
	public String getDanni() {

	return Danni;
	}
	
	
	

}
