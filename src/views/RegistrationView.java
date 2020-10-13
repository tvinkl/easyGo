package views;

import javax.swing.*;

public class RegistrationView {

    private JLabel InformazioniPersonaliLabel;
    private JLabel NomeLabel;
    private JTextField NomeTextfield;
    private JLabel PasswordUtenteLabel;
    private JPasswordField PasswordUtenteTextField;
    private JLabel CognomeLabel;
    private JTextField CognomeTextfield;
    private JLabel EmailLabel;
    private JTextField EmailTextfield;
    private JLabel PrefissoLabel;
    private JComboBox<String> prefisso;
    private String[] prefissi;
    private JLabel TelefonoLabel;
    private JTextField TelefonoTextfield;
    private JLabel DatadinascitaLabel;
    private JLabel GiornodnLabel;
    private JComboBox<Integer> gdn;
    private JLabel MesednLabel;
    private JComboBox<String> mdn;
    private JLabel AnnodnLabel;
    private JComboBox<Integer> adn;
    private JLabel InfoPatenteLabel;
    private JLabel NPatenteLabel;
    private JTextField NPatenteTextfield;
    private JLabel PaeseEmissionePLabel;
    private JComboBox<String> paese;
    private String[] paesi;
    private JLabel GiornodeLabel;
    private JComboBox<Integer> gde;
    private JLabel MesedeLabel;
    private JComboBox<String> mde;
    private JLabel AnnodeLabel;
    private JComboBox<Integer> ade;
    private JLabel GiornodsLabel;
    private JComboBox<Integer> gds;
    private JLabel MesedsLabel;
    private JComboBox<String> mds;
    private JLabel AnnodsLabel;
    private JComboBox<Integer> ads;
    private JLabel FatturazioneLabel;
    private JLabel IndirizzoLabel;
    private JTextField IndirizzoTextfield;
    private JLabel cityLabel;
    private JTextField cityTextField;
    private JLabel PaeseResidenzaPLabel;
    private JComboBox<String> paeseres;
    private JLabel CPLabel;
    private JTextField CPTextfield;

    public RegistrationView(JFrame frame) {

        InformazioniPersonaliLabel = new JLabel("Informazioni Personali");
        NomeLabel = new JLabel("Nome :");
        NomeTextfield = new JTextField();
        PasswordUtenteLabel = new JLabel("Password :");
        PasswordUtenteTextField = new JPasswordField();
        CognomeLabel = new JLabel("Cognome :");
        CognomeTextfield = new JTextField();
        EmailLabel = new JLabel("Email :");
        EmailTextfield = new JTextField();
        PrefissoLabel = new JLabel("Prefisso : ");
        prefissi = new String[]{"+39", "+33", "+1"};
        prefisso = new JComboBox<String>(prefissi);
        TelefonoLabel = new JLabel("Telefono :");
        TelefonoTextfield = new JTextField();
        DatadinascitaLabel = new JLabel("Data di nascita");
        GiornodnLabel = new JLabel("Giorno : ");
        gdn = new JComboBox<>(StaticData.DAYS);
        MesednLabel = new JLabel("Mese : ");
        mdn = new JComboBox<>(StaticData.MONTHS);
        AnnodnLabel = new JLabel("Anno : ");
        adn = new JComboBox<>(StaticData.YEARS);

        InfoPatenteLabel = new JLabel("Info Patente  ");
        NPatenteLabel = new JLabel("Numero Patente : ");
        NPatenteTextfield = new JTextField();
        PaeseEmissionePLabel = new JLabel("Paese Emissione Patente : ");
        paesi = new String[]{"Italia", "Francia", "America"};
        paese = new JComboBox<String>(paesi);
        GiornodeLabel = new JLabel("Giorno emissione : ");
        gde = new JComboBox<Integer>(StaticData.DAYS);
        MesedeLabel = new JLabel("Mese emissione : ");
        mde = new JComboBox<String>(StaticData.MONTHS);
        AnnodeLabel = new JLabel("Anno emissione: ");
        ade = new JComboBox<Integer>(StaticData.YEARS);
        GiornodsLabel = new JLabel("Giorno scadenza : ");
        gds = new JComboBox<Integer>(StaticData.DAYS);
        MesedsLabel = new JLabel("Mese scadenza : ");
        mds = new JComboBox<String>(StaticData.MONTHS);
        AnnodsLabel = new JLabel("Anno scadenza: ");
        ads = new JComboBox<Integer>(StaticData.ANNIS);
        FatturazioneLabel = new JLabel("Dati fatturazione");
        IndirizzoLabel = new JLabel("Indirizzo :");
        IndirizzoTextfield = new JTextField();
        cityLabel = new JLabel("Citta':");
        cityTextField = new JTextField();
        PaeseResidenzaPLabel = new JLabel("Paese :");
        paeseres = new JComboBox<String>(paesi);
        CPLabel = new JLabel("Codice Postale :");
        CPTextfield = new JTextField();

        InformazioniPersonaliLabel.setBounds(50, 25, 200, 70);
        frame.add(InformazioniPersonaliLabel);
        NomeLabel.setBounds(50, 75, 70, 70);
        frame.add(NomeLabel);
        NomeTextfield.setBounds(150, 100, 200, 20);
        frame.add(NomeTextfield);
        PasswordUtenteLabel.setBounds(400, 75, 100, 70);
        frame.add(PasswordUtenteLabel);
        PasswordUtenteTextField.setBounds(500, 100, 200, 20);
        frame.add(PasswordUtenteTextField);
        CognomeLabel.setBounds(50, 125, 70, 70);
        frame.add(CognomeLabel);
        CognomeTextfield.setBounds(150, 150, 200, 20);
        frame.add(CognomeTextfield);
        EmailLabel.setBounds(50, 175, 70, 70);
        frame.add(EmailLabel);
        EmailTextfield.setBounds(150, 200, 200, 20);
        frame.add(EmailTextfield);
        PrefissoLabel.setBounds(50, 225, 70, 70);
        frame.add(PrefissoLabel);
        prefisso.setBounds(150, 250, 70, 20);
        frame.add(prefisso);
        TelefonoLabel.setBounds(250, 225, 70, 70);
        frame.add(TelefonoLabel);
        TelefonoTextfield.setBounds(350, 250, 200, 20);
        frame.add(TelefonoTextfield);
        DatadinascitaLabel.setBounds(50, 275, 200, 70);
        frame.add(DatadinascitaLabel);
        GiornodnLabel.setBounds(200, 275, 70, 70);
        frame.add(GiornodnLabel);
        gdn.setBounds(300, 300, 70, 20);
        frame.add(gdn);
        MesednLabel.setBounds(400, 275, 70, 70);
        frame.add(MesednLabel);
        mdn.setBounds(500, 300, 100, 20);
        frame.add(mdn);
        AnnodnLabel.setBounds(650, 275, 70, 70);
        frame.add(AnnodnLabel);
        adn.setBounds(750, 300, 70, 20);
        frame.add(adn);
        InfoPatenteLabel.setBounds(50, 325, 100, 70);
        frame.add(InfoPatenteLabel);
        NPatenteLabel.setBounds(175, 325, 150, 70);
        frame.add(NPatenteLabel);
        NPatenteTextfield.setBounds(325, 350, 150, 20);
        frame.add(NPatenteTextfield);
        PaeseEmissionePLabel.setBounds(500, 325, 200, 70);
        frame.add(PaeseEmissionePLabel);
        paese.setBounds(700, 350, 100, 20);
        frame.add(paese);
        GiornodeLabel.setBounds(50, 375, 150, 70);
        frame.add(GiornodeLabel);
        gde.setBounds(200, 400, 70, 20);
        frame.add(gde);
        MesedeLabel.setBounds(300, 375, 150, 70);
        frame.add(MesedeLabel);
        mde.setBounds(450, 400, 100, 20);
        frame.add(mde);
        AnnodeLabel.setBounds(600, 375, 150, 70);
        frame.add(AnnodeLabel);
        ade.setBounds(750, 400, 70, 20);
        frame.add(ade);
        GiornodsLabel.setBounds(50, 425, 150, 70);
        frame.add(GiornodsLabel);
        gds.setBounds(200, 450, 70, 20);
        frame.add(gds);
        MesedsLabel.setBounds(300, 425, 150, 70);
        frame.add(MesedsLabel);
        mds.setBounds(450, 450, 100, 20);
        frame.add(mds);
        AnnodsLabel.setBounds(600, 425, 150, 70);
        frame.add(AnnodsLabel);
        ads.setBounds(750, 450, 70, 20);
        frame.add(ads);
        FatturazioneLabel.setBounds(50, 475, 150, 70);
        frame.add(FatturazioneLabel);
        IndirizzoLabel.setBounds(50, 525, 70, 70);
        frame.add(IndirizzoLabel);
        IndirizzoTextfield.setBounds(150, 550, 200, 20);
        frame.add(IndirizzoTextfield);
        cityLabel.setBounds(375, 525, 70, 70);
        frame.add(cityLabel);
        cityTextField.setBounds(450, 550, 200, 20);
        frame.add(cityTextField);
        PaeseResidenzaPLabel.setBounds(50, 575, 70, 70);
        frame.add(PaeseResidenzaPLabel);
        paeseres.setBounds(150, 600, 100, 20);
        frame.add(paeseres);
        CPLabel.setBounds(275, 575, 150, 70);
        frame.add(CPLabel);
        CPTextfield.setBounds(400, 600, 200, 20);
        frame.add(CPTextfield);

        frame.repaint();
    }

    public JLabel getInformazioniPersonaliLabel() {
        return InformazioniPersonaliLabel;
    }

    public void setInformazioniPersonaliLabel(JLabel informazioniPersonaliLabel) {
        InformazioniPersonaliLabel = informazioniPersonaliLabel;
    }

    public JLabel getNomeLabel() {
        return NomeLabel;
    }

    public void setNomeLabel(JLabel nomeLabel) {
        NomeLabel = nomeLabel;
    }

    public JTextField getNomeTextfield() {
        return NomeTextfield;
    }

    public void setNomeTextfield(JTextField nomeTextfield) {
        NomeTextfield = nomeTextfield;
    }

    public JLabel getPasswordUtenteLabel() {
        return PasswordUtenteLabel;
    }

    public void setPasswordUtenteLabel(JLabel passwordUtenteLabel) {
        PasswordUtenteLabel = passwordUtenteLabel;
    }

    public JPasswordField getPasswordUtenteTextField() {
        return PasswordUtenteTextField;
    }

    public void setPasswordUtenteTextField(String pswd) {
        PasswordUtenteTextField.setText(pswd);
    }

    public JLabel getCognomeLabel() {
        return CognomeLabel;
    }

    public void setCognomeLabel(JLabel cognomeLabel) {
        CognomeLabel = cognomeLabel;
    }

    public JTextField getCognomeTextfield() {
        return CognomeTextfield;
    }

    public void setCognomeTextfield(JTextField cognomeTextfield) {
        CognomeTextfield = cognomeTextfield;
    }

    public JLabel getEmailLabel() {
        return EmailLabel;
    }

    public void setEmailLabel(JLabel emailLabel) {
        EmailLabel = emailLabel;
    }

    public JTextField getEmailTextfield() {
        return EmailTextfield;
    }

    public void setEmailTextfield(JTextField emailTextfield) {
        EmailTextfield = emailTextfield;
    }

    public JLabel getPrefissoLabel() {
        return PrefissoLabel;
    }

    public void setPrefissoLabel(JLabel prefissoLabel) {
        PrefissoLabel = prefissoLabel;
    }

    public String getPrefisso() {
        return prefisso.getSelectedItem().toString();
    }

    public void setPrefisso(JComboBox<String> prefisso) {
        this.prefisso = prefisso;
    }

    public String[] getPrefissi() {
        return prefissi;
    }

    public void setPrefissi(String[] prefissi) {
        this.prefissi = prefissi;
    }

    public JLabel getTelefonoLabel() {
        return TelefonoLabel;
    }

    public void setTelefonoLabel(JLabel telefonoLabel) {
        TelefonoLabel = telefonoLabel;
    }

    public JTextField getTelefonoTextfield() {
        return TelefonoTextfield;
    }

    public void setTelefonoTextfield(JTextField telefonoTextfield) {
        TelefonoTextfield = telefonoTextfield;
    }

    public JLabel getDatadinascitaLabel() {
        return DatadinascitaLabel;
    }

    public void setDatadinascitaLabel(JLabel datadinascitaLabel) {
        DatadinascitaLabel = datadinascitaLabel;
    }

    public JLabel getGiornodnLabel() {
        return GiornodnLabel;
    }

    public void setGiornodnLabel(JLabel giornodnLabel) {
        GiornodnLabel = giornodnLabel;
    }

    public Integer getGdn() {
        return Integer.parseInt(gdn.getSelectedItem().toString());
    }

    public void setGdn(JComboBox<Integer> gdn) {
        this.gdn = gdn;
    }

    public JLabel getMesednLabel() {
        return MesednLabel;
    }

    public void setMesednLabel(JLabel mesednLabel) {
        MesednLabel = mesednLabel;
    }

    public String getMdn() {
        return mdn.getSelectedItem().toString();
    }

    public void setMdn(JComboBox<String> mdn) {
        this.mdn = mdn;
    }

    public JLabel getAnnodnLabel() {
        return AnnodnLabel;
    }

    public void setAnnodnLabel(JLabel annodnLabel) {
        AnnodnLabel = annodnLabel;
    }

    public Integer getAdn() {
        return Integer.valueOf(adn.getSelectedItem().toString());
    }

    public void setAdn(JComboBox<Integer> adn) {
        this.adn = adn;
    }

    public JLabel getInfoPatenteLabel() {
        return InfoPatenteLabel;
    }

    public void setInfoPatenteLabel(JLabel infoPatenteLabel) {
        InfoPatenteLabel = infoPatenteLabel;
    }

    public JLabel getNPatenteLabel() {
        return NPatenteLabel;
    }

    public void setNPatenteLabel(JLabel NPatenteLabel) {
        this.NPatenteLabel = NPatenteLabel;
    }

    public JTextField getNPatenteTextfield() {
        return NPatenteTextfield;
    }

    public void setNPatenteTextfield(JTextField NPatenteTextfield) {
        this.NPatenteTextfield = NPatenteTextfield;
    }

    public JLabel getPaeseEmissionePLabel() {
        return PaeseEmissionePLabel;
    }

    public void setPaeseEmissionePLabel(JLabel paeseEmissionePLabel) {
        PaeseEmissionePLabel = paeseEmissionePLabel;
    }

    public String getPaese() {
        return paese.getSelectedItem().toString();
    }

    public void setPaese(JComboBox<String> paese) {
        this.paese = paese;
    }

    public String[] getPaesi() {
        return paesi;
    }

    public void setPaesi(String[] paesi) {
        this.paesi = paesi;
    }

    public JLabel getGiornodeLabel() {
        return GiornodeLabel;
    }

    public void setGiornodeLabel(JLabel giornodeLabel) {
        GiornodeLabel = giornodeLabel;
    }

    public Integer getGde() {
        return Integer.valueOf(gde.getSelectedItem().toString());
    }

    public void setGde(JComboBox<Integer> gde) {
        this.gde = gde;
    }

    public JLabel getMesedeLabel() {
        return MesedeLabel;
    }

    public void setMesedeLabel(JLabel mesedeLabel) {
        MesedeLabel = mesedeLabel;
    }

    public String getMde() {
        return mde.getSelectedItem().toString();
    }

    public void setMde(JComboBox<String> mde) {
        this.mde = mde;
    }

    public JLabel getAnnodeLabel() {
        return AnnodeLabel;
    }

    public void setAnnodeLabel(JLabel annodeLabel) {
        AnnodeLabel = annodeLabel;
    }

    public Integer getAde() {
        return Integer.valueOf(ade.getSelectedItem().toString());
    }

    public void setAde(JComboBox<Integer> ade) {
        this.ade = ade;
    }

    public JLabel getGiornodsLabel() {
        return GiornodsLabel;
    }

    public void setGiornodsLabel(JLabel giornodsLabel) {
        GiornodsLabel = giornodsLabel;
    }

    public Integer getGds() {
        return Integer.valueOf(gds.getSelectedItem().toString());
    }

    public void setGds(JComboBox<Integer> gds) {
        this.gds = gds;
    }

    public JLabel getMesedsLabel() {
        return MesedsLabel;
    }

    public void setMesedsLabel(JLabel mesedsLabel) {
        MesedsLabel = mesedsLabel;
    }

    public String getMds() {
        return mds.getSelectedItem().toString();
    }

    public void setMds(JComboBox<String> mds) {
        this.mds = mds;
    }

    public JLabel getAnnodsLabel() {
        return AnnodsLabel;
    }

    public void setAnnodsLabel(JLabel annodsLabel) {
        AnnodsLabel = annodsLabel;
    }

    public Integer getAds() {
        return Integer.valueOf(ads.getSelectedItem().toString());
    }

    public void setAds(JComboBox<Integer> ads) {
        this.ads = ads;
    }

    public JLabel getFatturazioneLabel() {
        return FatturazioneLabel;
    }

    public void setFatturazioneLabel(JLabel fatturazioneLabel) {
        FatturazioneLabel = fatturazioneLabel;
    }

    public JLabel getIndirizzoLabel() {
        return IndirizzoLabel;
    }

    public void setIndirizzoLabel(JLabel indirizzoLabel) {
        IndirizzoLabel = indirizzoLabel;
    }

    public JTextField getIndirizzoTextfield() {
        return IndirizzoTextfield;
    }

    public void setIndirizzoTextfield(JTextField indirizzoTextfield) {
        IndirizzoTextfield = indirizzoTextfield;
    }

    public JLabel getCityLabel() {
        return cityLabel;
    }

    public void setCityLabel(JLabel cityLabel) {
        this.cityLabel = cityLabel;
    }

    public JTextField getCityTextField() {
        return cityTextField;
    }

    public void setCityTextField(JTextField cityTextField) {
        this.cityTextField = cityTextField;
    }

    public JLabel getPaeseResidenzaPLabel() {
        return PaeseResidenzaPLabel;
    }

    public void setPaeseResidenzaPLabel(JLabel paeseResidenzaPLabel) {
        PaeseResidenzaPLabel = paeseResidenzaPLabel;
    }

    public String getPaeseres() {
        return paeseres.getSelectedItem().toString();
    }

    public void setPaeseres(JComboBox<String> paeseres) {
        this.paeseres = paeseres;
    }

    public JLabel getCPLabel() {
        return CPLabel;
    }

    public void setCPLabel(JLabel CPLabel) {
        this.CPLabel = CPLabel;
    }

    public JTextField getCPTextfield() {
        return CPTextfield;
    }

    public void setCPTextfield(JTextField CPTextfield) {
        this.CPTextfield = CPTextfield;
    }
}
