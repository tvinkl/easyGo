package easygo;

public class Preventivo {

    private int IDPreventivo;
    private int DataRitiro;
    private String OraRitiro;
    private int DataRiconsegna;
    private String OraRiconsegna;
    private int gncp;
    private String mncp;
    private int ancp;
    private int gepcp;
    private String mepcp;
    private int aepcp;
    private String car;
    private float price;
    private String seggiolino;
    private String catene;
    private String navigatore;
    private String hotspot;
    private float totale;

    //La data di riconsegna deve essere successiva alla data riconsegna
    //Le date sono dello stesso mese
    //@ ensures DataRiconsegna - DataRitiro >= 0
    public Preventivo() {

    }


    public void setIDPreventivo(int ID) {

        this.IDPreventivo = ID;
    }

    public int getIDPreventivo() {

        return IDPreventivo;
    }

    public void setDataRitiro(int DataRitiro) {

        this.DataRitiro = DataRitiro;
    }

    public int getDataRitiro() {

        return DataRitiro;
    }

    public void setOraRitiro(String OraRitiro) {

        this.OraRitiro = OraRitiro;
    }

    public String getOraRitiro() {

        return OraRitiro;
    }

    public void setDataRiconsegna(int DataRiconsegna) {

        this.DataRiconsegna = DataRiconsegna;
    }

    public int getDataRiconsegna() {

        return DataRiconsegna;
    }

    public void setOraRiconsegna(String OraRiconsegna) {

        this.OraRiconsegna = OraRiconsegna;
    }

    public String getOraRiconsegna() {

        return OraRiconsegna;
    }

    public void setgncp(int gncp) {

        this.gncp = gncp;
    }

    public int getgncp() {

        return gncp;
    }

    public void setmncp(String mncp) {

        this.mncp = mncp;
    }

    public String getmncp() {

        return mncp;
    }

    public void setancp(int ancp) {

        this.ancp = ancp;
    }

    public int getancp() {

        return ancp;
    }

    public void setgepcp(int gepcp) {

        this.gepcp = gepcp;
    }

    public int getgepcp() {

        return gepcp;
    }

    public void setmepcp(String mepcp) {

        this.mepcp = mepcp;
    }

    public String getmepcp() {

        return mepcp;
    }

    public void setaepcp(int aepcp) {

        this.aepcp = aepcp;
    }

    public int getaepcp() {

        return aepcp;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getCar() {
        return car;
    }

    public void setPrice(float price) {

        this.price = price;
    }

    public float getPrice() {
        return price;
    }


    public void setseggiolino(String seggiolino) {

        this.seggiolino = seggiolino;
    }

    public String getseggiolino() {

        return seggiolino;
    }

    public void setcatene(String catene) {

        this.catene = catene;
    }

    public String getcatene() {

        return catene;
    }

    public void setnavigatore(String navigatore) {

        this.navigatore = navigatore;
    }

    public String getnavigatore() {

        return navigatore;
    }

    public void sethotspot(String hotspot) {

        this.hotspot = hotspot;
    }

    public String gethotspot() {

        return hotspot;
    }

    public void settotale(float totale) {


        this.totale = totale;
    }

    public float gettotale() {

        return totale;
    }


}

