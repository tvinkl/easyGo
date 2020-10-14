package easygo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Client {

    private long userId;
    private String nome;
    private String cognome;
    private String email;
    private String prefisso;
    private long telefono;
    private long dngiorno;
    private String dnmese;
    private long dnanno;
    private String numpatente;
    private String paesePatente;
    private long giornoep;
    private String meseep;
    private long annoep;
    private long giornosp;
    private String mesesp;
    private long annosp;
    private String indirizzo;
    private String city;
    private String paese;
    private long codpostale;
    private Roles role;
    private String password;

    //ogni cliente deve avere et� >= 18 anni
    //controllo semplificato verifica solo anno poich� non � stata creata una variabile anni
    //@ requires 2020 - Dnanno >= 18
    public Client() {

    }

    public Client(ResultSet rs) throws SQLException {
        this.userId = rs.getLong("userId");
        this.nome = rs.getString("nome");
        this.cognome = rs.getString("cognome");
        this.email = rs.getString("email");
        this.prefisso = rs.getString("prefisso");
        this.telefono = rs.getLong("telefono");
        this.dngiorno = rs.getLong("dngiorno");
        this.dnmese = rs.getString("dnmese");
        this.dnanno = rs.getLong("dnanno");
        this.numpatente = rs.getString("numpatente");
        this.paesePatente = rs.getString("paesePatente");
        this.giornoep = rs.getLong("giornoep");
        this.meseep = rs.getString("meseep");
        this.annoep = rs.getLong("annoep");
        this.giornosp = rs.getLong("giornosp");
        this.mesesp = rs.getString("mesesp");
        this.annosp = rs.getLong("annosp");
        this.indirizzo = rs.getString("indirizzo");
        this.city = rs.getString("city");
        this.paese = rs.getString("paese");
        this.codpostale = rs.getLong("codpostale");
        this.role = Roles.valueOf(rs.getString("role"));
        this.password = rs.getString("password");
    }

    public void setUserId(int ID) {

        this.userId = ID;
    }

    public long getID() {

        return userId;
    }

    public void setNome(String Nome) {

        this.nome = Nome;
    }

    public String getNome() {

        return nome;
    }

    public void setCognome(String Cognome) {

        this.cognome = Cognome;
    }

    public String getCognome() {

        return cognome;
    }


    public void setEmail(String Email) {

        this.email = Email;
    }

    public String getEmail() {

        return email;
    }

    public void setPrefisso(String Prefisso) {

        this.prefisso = Prefisso;
    }

    public String getPrefisso() {

        return prefisso;
    }

    public void setTelefono(Long telefono) {

        this.telefono = telefono;
    }

    public Long getTelefono() {

        return telefono;
    }

    public void setDngiorno(int Dngiorno) {

        this.dngiorno = Dngiorno;
    }

    public long getDngiorno() {

        return dngiorno;
    }

    public void setDnmese(String Dnmese) {

        this.dnmese = Dnmese;
    }

    public String getDnmese() {

        return dnmese;
    }

    public void setDnanno(int Dnanno) {

        this.dnanno = Dnanno;
    }

    public long getDnanno() {

        return dnanno;
    }

    public void setNumpatente(String Numpatente) {

        this.numpatente = Numpatente;
    }

    public String getNumpatente() {

        return numpatente;
    }

    public void setPaesePatente(String PaesePatente) {

        this.paesePatente = PaesePatente;
    }

    public String getPaesePatente() {

        return paesePatente;
    }

    public void setGiornoep(int Giornoep) {

        this.giornoep = Giornoep;
    }

    public long getGiornoep() {

        return giornoep;
    }

    public void setMeseep(String Meseep) {

        this.meseep = Meseep;
    }

    public String getMeseep() {

        return meseep;
    }

    public void setAnnoep(int Annoep) {

        this.annoep = Annoep;
    }

    public long getAnnoep() {

        return annoep;
    }

    public void setGiornosp(int Giornosp) {

        this.giornosp = Giornosp;
    }

    public long getGiornosp() {

        return giornosp;
    }

    public void setMesesp(String Mesesp) {

        this.mesesp = Mesesp;
    }

    public String getMesesp() {

        return mesesp;
    }

    public void setAnnosp(int Annosp) {

        this.annosp = Annosp;
    }

    public long getAnnosp() {

        return annosp;
    }

    public void setIndirizzo(String Indirizzo) {

        this.indirizzo = Indirizzo;
    }

    public String getIndirizzo() {

        return indirizzo;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getCity() {

        return city;
    }

    public void setPaese(String Paese) {

        this.paese = Paese;
    }

    public String getPaese() {

        return paese;
    }

    public void setCodpostale(int Codpostale) {

        this.codpostale = Codpostale;
    }

    public long getCodpostale() {

        return codpostale;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
