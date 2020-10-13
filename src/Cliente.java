
public class Cliente {

	private int userId;
	private String Nome;
	private String Cognome;
	private /*@ spec_public @*/ String Email;
	private String Prefisso;
	private long  Telefono;
	private int Dngiorno;
	private String Dnmese;
	private /*@ spec_public @*/ int Dnanno;
	private String Numpatente;
	private String PaesePatente;
	private int Giornoep;
	private String Meseep;
	private int Annoep;
	private int Giornosp;
	private String Mesesp;
	private int Annosp;
	private String Indirizzo;
	private String city;
	private String Paese;
	private int Codpostale;
	private String Pwd;
	
	//ogni cliente deve avere età >= 18 anni
	//controllo semplificato verifica solo anno poichè non è stata creata una variabile anni
	//@ requires 2020 - Dnanno >= 18
	public Cliente() {
		
	}
	
public void setUserId(int ID) {
		
		this.userId = ID;
	}
public int getID() {
	
	return userId;
}
	
	public void setNome(String Nome) {
		
		this.Nome = Nome;
	}
	
	public String getNome() {
		
		return Nome;
	}
	
	public void setCognome(String Cognome) {
		
		this.Cognome = Cognome;
	}

	public String getCognome() {
	
	return Cognome;
	}
	

public void setEmail(String Email) {
	
	this.Email = Email;
}

public String getEmail() {
	
	return Email;
}

public void setPrefisso(String Prefisso) {
	
	this.Prefisso = Prefisso;
}

public String getPrefisso() {
	
	return Prefisso;
}

public void setTelefono(Long Telefono) {
	
	this.Telefono = Telefono;
}

public Long getTelefono() {
	
	return Telefono;
}

public void setDngiorno(int Dngiorno) {
	
	this.Dngiorno = Dngiorno;
}

public int getDngiorno() {
	
	return Dngiorno;
}

public void setDnmese(String Dnmese) {
	
	this.Dnmese = Dnmese;
}

public String getDnmese() {
	
	return Dnmese;
}

public void setDnanno(int Dnanno) {
	
	this.Dnanno = Dnanno;
}

public int getDnanno() {
	
	return Dnanno;
}

public void setNumpatente(String Numpatente) {
	
	this.Numpatente = Numpatente;
}

public String getNumpatente() {
	
	return Numpatente;
}

public void setPaesePatente(String PaesePatente) {
	
	this.PaesePatente = PaesePatente;
}

public String getPaesePatente() {
	
	return PaesePatente;
}

public void setGiornoep(int Giornoep) {
	
	this.Giornoep = Giornoep;
}

public int getGiornoep() {
	
	return Giornoep;
}

public void setMeseep(String Meseep) {
	
	this.Meseep = Meseep;
}

public String getMeseep() {
	
	return Meseep;
}

public void setAnnoep(int Annoep) {
	
	this.Annoep = Annoep;
}

public int getAnnoep() {
	
	return Annoep;
}

public void setGiornosp(int Giornosp) {
	
	this.Giornosp = Giornosp;
}

public int getGiornosp() {
	
	return Giornosp;
}

public void setMesesp(String Mesesp) {
	
	this.Mesesp = Mesesp;
}

public String getMesesp() {
	
	return Mesesp;
}

public void setAnnosp(int Annosp) {
	
	this.Annosp = Annosp;
}

public int getAnnosp() {
	
	return Annosp;
}

public void setIndirizzo(String Indirizzo) {
	
	this.Indirizzo = Indirizzo;
}

public String getIndirizzo() {
	
	return Indirizzo;
}

public void setCity(String city) {
	
	this.city = city;
}

public String getCity() {
	
	return city;
}

public void setPaese(String Paese) {
	
	this.Paese = Paese;
}

public String getPaese() {
	
	return Paese;
}

public void setCodpostale(int Codpostale) {
	
	this.Codpostale = Codpostale;
}

public int getCodpostale() {
	
	return Codpostale;
}

public void setPwd(String Pwd) {
	
	this.Pwd = Pwd;
}

public String getPwd() {
	
	return Pwd;
}


	
}
