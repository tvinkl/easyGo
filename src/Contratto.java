public class Contratto {

    private int IDContratto;
    private int UserID;
    private int IDPreventivo;
    private String Targa;
    private float Mora;


    public void setIDContratto(int ID) {

        this.IDContratto = ID;
    }

    public int getIDContratto() {

        return IDContratto;
    }

    public void setUserID(int ID) {

        this.UserID = ID;
    }

    public int getUserID() {

        return UserID;
    }

    public void setIDPreventivo(int ID) {

        this.IDPreventivo = ID;
    }

    public int getIDPreventivo() {

        return IDPreventivo;
    }

    public void setTarga(String Targa) {

        this.Targa = Targa;
    }

    public String getTarga() {

        return Targa;
    }

    public void setMora(float Mora) {

        this.Mora = Mora;
    }

    public float getMora() {

        return Mora;
    }


}
