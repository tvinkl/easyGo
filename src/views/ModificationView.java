package views;

import javax.swing.*;

public class ModificationView {
    private JLabel InformazioniPersonaliLabel;
    private JLabel NomeLabel;
    private JLabel PasswordUtenteLabel;
    private JPasswordField PasswordUtenteTextField;
    private JLabel CognomeLabel;
    private JLabel EmailLabel;
    private JLabel PrefissoLabel;
    private String[] prefissi;
    private JLabel TelefonoLabel;
    private JLabel DatadinascitaLabel;
    private JLabel GiornodnLabel;
    private JLabel MesednLabel;
    private JLabel AnnodnLabel;
    private JLabel InfoPatenteLabel;
    private JLabel NPatenteLabel;
    private JLabel PaeseEmissionePLabel;
    private String[] paesi;
    private JLabel GiornodeLabel;
    private JLabel MesedeLabel;
    private JLabel AnnodeLabel;
    private JLabel GiornodsLabel;
    private JLabel MesedsLabel;
    private JLabel AnnodsLabel;
    private JLabel FatturazioneLabel;
    private JLabel IndirizzoLabel;
    private JLabel cityLabel;
    private JLabel PaeseResidenzaPLabel;
    private JLabel CPLabel;
    private JLabel NomeClienteLabel;
    private JLabel CognomeClienteLabel;
    private JTextField EmailClienteTextField;
    private JComboBox<String> prefissocliente;
    private JComboBox<String> paeseCliente;
    private JComboBox<String> paeseresCliente;
    private JTextField TelefonoClienteTextField;
    private JTextField NPatenteClienteTextField;
    private JTextField IndirizzoClienteTextField;
    private JTextField cityClientTextField;
    private JTextField CPClienteTextField;
    private JLabel gdnClienteLabel;
    private JLabel mdnClienteLabel;
    private JLabel adnClienteLabel;
    private JLabel gdeClienteLabel;
    private JLabel mdeClienteLabel;
    private JLabel adeClienteLabel;
    private JComboBox<Integer> gdsCliente;
    private JComboBox<String> mdsCliente;
    private JComboBox<Integer> adsCliente;
    private JButton SalvaModifiche;
    private JButton Indietro;

    public ModificationView(JFrame frame) {
        InformazioniPersonaliLabel = new JLabel("Informazioni Personali");
        NomeLabel = new JLabel("Nome :");
        NomeClienteLabel = new JLabel();
        PasswordUtenteLabel = new JLabel("Password :");
        PasswordUtenteTextField = new JPasswordField();
        CognomeLabel = new JLabel("Cognome :");
        CognomeClienteLabel = new JLabel();
        EmailLabel = new JLabel("Email :");
        EmailClienteTextField = new JTextField();
        PrefissoLabel = new JLabel("Prefisso : ");
        prefissi = new String[]{"+39", "+33", "+1"};
        prefissocliente = new JComboBox<String>(prefissi);
        TelefonoLabel = new JLabel("Telefono :");
        TelefonoClienteTextField = new JTextField();
        DatadinascitaLabel = new JLabel("Data di nascita");
        GiornodnLabel = new JLabel("Giorno : ");
        gdnClienteLabel = new JLabel();
        MesednLabel = new JLabel("Mese : ");
        mdnClienteLabel = new JLabel();
        AnnodnLabel = new JLabel("Anno : ");
        adnClienteLabel = new JLabel();

        InfoPatenteLabel = new JLabel("Info Patente  ");
        NPatenteLabel = new JLabel("Numero Patente : ");
        NPatenteClienteTextField = new JTextField();

        PaeseEmissionePLabel = new JLabel("Paese Emissione Patente : ");
        paesi = new String[]{"Italia", "Francia", "America"};
        paeseCliente = new JComboBox<>(paesi);
        GiornodeLabel = new JLabel("Giorno emissione : ");
        gdeClienteLabel = new JLabel();
        MesedeLabel = new JLabel("Mese emissione : ");
        mdeClienteLabel = new JLabel();

        AnnodeLabel = new JLabel("Anno emissione: ");
        adeClienteLabel = new JLabel();
        GiornodsLabel = new JLabel("Giorno scadenza : ");
        gdsCliente = new JComboBox<Integer>(StaticData.DAYS);
        MesedsLabel = new JLabel("Mese scadenza : ");
        mdsCliente = new JComboBox<String>(StaticData.MONTHS);
        AnnodsLabel = new JLabel("Anno scadenza: ");
        adsCliente = new JComboBox<Integer>(StaticData.ANNIS);
        FatturazioneLabel = new JLabel("Dati fatturazione");
        IndirizzoLabel = new JLabel("Indirizzo :");
        IndirizzoClienteTextField = new JTextField();
        cityLabel = new JLabel("Citt� :");
        cityClientTextField = new JTextField();
        PaeseResidenzaPLabel = new JLabel("Paese :");
        paeseresCliente = new JComboBox<String>(paesi);
        CPLabel = new JLabel("Codice Postale :");
        CPClienteTextField = new JTextField();

        SalvaModifiche = new JButton("Salva Modifiche");
        Indietro = new JButton("Indietro");

        // Add UI element to frame
        frame.setLayout(null);
        InformazioniPersonaliLabel.setBounds(50, 25, 200, 70);
        frame.add(InformazioniPersonaliLabel);

        NomeLabel.setBounds(50, 75, 70, 70);
        frame.add(NomeLabel);
        NomeClienteLabel.setBounds(150, 100, 200, 20);
        frame.add(NomeClienteLabel);
        PasswordUtenteLabel.setBounds(400, 75, 100, 70);
        frame.add(PasswordUtenteLabel);
        PasswordUtenteTextField.setBounds(500, 100, 200, 20);
        frame.add(PasswordUtenteTextField);
        CognomeLabel.setBounds(50, 125, 70, 70);
        frame.add(CognomeLabel);
        CognomeClienteLabel.setBounds(150, 150, 200, 20);
        frame.add(CognomeClienteLabel);
        EmailLabel.setBounds(50, 175, 70, 70);
        frame.add(EmailLabel);
        EmailClienteTextField.setBounds(150, 200, 200, 20);
        frame.add(EmailClienteTextField);
        PrefissoLabel.setBounds(50, 225, 70, 70);
        frame.add(PrefissoLabel);
        prefissocliente.setBounds(150, 250, 70, 20);
        frame.add(prefissocliente);
        TelefonoLabel.setBounds(250, 225, 70, 70);
        frame.add(TelefonoLabel);
        TelefonoClienteTextField.setBounds(350, 250, 200, 20);
        frame.add(TelefonoClienteTextField);
        DatadinascitaLabel.setBounds(50, 275, 200, 70);
        frame.add(DatadinascitaLabel);
        GiornodnLabel.setBounds(200, 275, 70, 70);
        frame.add(GiornodnLabel);
        gdnClienteLabel.setBounds(300, 300, 70, 20);
        frame.add(gdnClienteLabel);
        MesednLabel.setBounds(400, 275, 70, 70);
        frame.add(MesednLabel);
        mdnClienteLabel.setBounds(500, 300, 100, 20);
        frame.add(mdnClienteLabel);
        AnnodnLabel.setBounds(650, 275, 70, 70);
        frame.add(AnnodnLabel);
        adnClienteLabel.setBounds(750, 300, 70, 20);
        frame.add(adnClienteLabel);
        InfoPatenteLabel.setBounds(50, 325, 100, 70);
        frame.add(InfoPatenteLabel);
        NPatenteLabel.setBounds(175, 325, 150, 70);
        frame.add(NPatenteLabel);
        NPatenteClienteTextField.setBounds(325, 350, 150, 20);
        frame.add(NPatenteClienteTextField);
        PaeseEmissionePLabel.setBounds(500, 325, 200, 70);
        frame.add(PaeseEmissionePLabel);
        paeseCliente.setBounds(700, 350, 100, 20);
        frame.add(paeseCliente);
        GiornodeLabel.setBounds(50, 375, 150, 70);
        frame.add(GiornodeLabel);
        gdeClienteLabel.setBounds(200, 400, 70, 20);
        frame.add(gdeClienteLabel);
        MesedeLabel.setBounds(300, 375, 150, 70);
        frame.add(MesedeLabel);
        mdeClienteLabel.setBounds(450, 400, 100, 20);
        frame.add(mdeClienteLabel);
        AnnodeLabel.setBounds(600, 375, 150, 70);
        frame.add(AnnodeLabel);
        adeClienteLabel.setBounds(750, 400, 70, 20);
        frame.add(adeClienteLabel);
        GiornodsLabel.setBounds(50, 425, 150, 70);
        frame.add(GiornodsLabel);
        gdsCliente.setBounds(200, 450, 70, 20);
        frame.add(gdsCliente);
        MesedsLabel.setBounds(300, 425, 150, 70);
        frame.add(MesedsLabel);
        mdsCliente.setBounds(450, 450, 100, 20);
        frame.add(mdsCliente);
        AnnodsLabel.setBounds(600, 425, 150, 70);
        frame.add(AnnodsLabel);
        adsCliente.setBounds(750, 450, 70, 20);
        frame.add(adsCliente);
        FatturazioneLabel.setBounds(50, 475, 150, 70);
        frame.add(FatturazioneLabel);
        IndirizzoLabel.setBounds(50, 525, 70, 70);
        frame.add(IndirizzoLabel);
        IndirizzoClienteTextField.setBounds(150, 550, 200, 20);
        frame.add(IndirizzoClienteTextField);
        cityLabel.setBounds(375, 525, 70, 70);
        frame.add(cityLabel);
        cityClientTextField.setBounds(450, 550, 200, 20);
        frame.add(cityClientTextField);
        PaeseResidenzaPLabel.setBounds(50, 575, 70, 70);
        frame.add(PaeseResidenzaPLabel);
        paeseresCliente.setBounds(150, 600, 100, 20);
        frame.add(paeseresCliente);
        CPLabel.setBounds(275, 575, 150, 70);
        frame.add(CPLabel);
        CPClienteTextField.setBounds(400, 600, 200, 20);
        frame.add(CPClienteTextField);

        SalvaModifiche.setBounds(850, 575, 150, 50);
        frame.add(SalvaModifiche);
        Indietro.setBounds(1050, 575, 100, 50);
        frame.add(Indietro);
        frame.repaint();
    }

    public JTextField getCPClienteTextField() {
        return IndirizzoClienteTextField;
    }

    public JTextField getPasswordUtenteTextField() {
        return PasswordUtenteTextField;
    }

    public JTextField getTelefonoClienteTextField() {
        return TelefonoClienteTextField;
    }

    public void setNomeClienteLabel(String N) {

        this.NomeClienteLabel.setText(N);

    }

    public void setPasswordUtenteTextField(String N) {

        this.PasswordUtenteTextField.setText(N);

    }

    public void setCognomeClienteLabel(String N) {

        this.CognomeClienteLabel.setText(N);

    }

    public void setEmailClienteTextField(String N) {

        this.EmailClienteTextField.setText(N);

    }

    public void setPrefissoCliente(String N) {

        this.prefissocliente.setSelectedItem(N);

    }

    public void setTelefonoClienteTextField(Long N) {

        this.TelefonoClienteTextField.setText(Long.toString(N));
    }

    public void setNPatenteClienteTextField(String N) {

        this.NPatenteClienteTextField.setText(N);
    }

    public void setIndirizzoClienteTextField(String N) {

        this.IndirizzoClienteTextField.setText(N);
    }

    public void setgdnClienteLabel(int N) {

        this.gdnClienteLabel.setText(Integer.toString(N));
    }

    public void setmdnClienteLabel(String N) {

        this.mdnClienteLabel.setText(N);
    }

    public void setadnClienteLabel(int N) {

        this.adnClienteLabel.setText(Integer.toString(N));
    }

    public void setgdeClienteLabel(int N) {

        this.gdeClienteLabel.setText(Integer.toString(N));
    }

    public void setmdeClienteLabel(String N) {

        this.mdeClienteLabel.setText(N);
    }

    public void setadeClienteLabel(int N) {

        this.adeClienteLabel.setText(Integer.toString(N));
    }

    public void setCityClientTextField(String N) {

        this.cityClientTextField.setText(N);
    }

    public void setCPClienteTextField(int N) {

        this.CPClienteTextField.setText(Integer.toString(N));
    }

    public void setPaesePatenteCliente(String N) {

        this.paeseCliente.setSelectedItem(N);

    }

    public void setgdsCliente(int N) {

        this.gdsCliente.setSelectedItem(N);

    }

    public void setmdsCliente(String N) {

        this.mdsCliente.setSelectedItem(N);

    }

    public void setadsCliente(int N) {

        this.adsCliente.setSelectedItem(N);

    }

    public void setPaeseResCliente(String N) {

        this.paeseCliente.setSelectedItem(N);

    }

    public JButton getSalvaModifiche() {
        return SalvaModifiche;
    }


    public JTextField getEmailClienteTextField() {
        return EmailClienteTextField;
    }

    public JTextField getNPatenteClienteTextField() {
        return NPatenteClienteTextField;
    }

    public String getprefissocliente() {

        String text = prefissocliente.getSelectedItem().toString();

        return text;
    }

    public String getpaeseCliente() {

        String text = paeseCliente.getSelectedItem().toString();
        return text;
    }

    public int getgdsCliente() {

        String text = gdsCliente.getSelectedItem().toString();
        int valuegds = Integer.parseInt(text);
        return valuegds;
    }

    public String getmdsCliente() {

        String text = mdsCliente.getSelectedItem().toString();
        return text;
    }

    public int getadsCliente() {

        String text = adsCliente.getSelectedItem().toString();
        int valueads = Integer.parseInt(text);
        return valueads;
    }

    public JTextField getIndirizzoClienteTextField() {
        return IndirizzoClienteTextField;
    }

    public JTextField getCityClientTextField() {
        return cityClientTextField;
    }

    public String getpaeseresCliente() {
        String text = paeseresCliente.getSelectedItem().toString();
        return text;
    }
}
