package easygo;

import javax.swing.*;
import java.util.Objects;

public class CalculationView {
    private JLabel Riepilogo;
    private JLabel Periodo;
    private JLabel Periodoselezionatoinizio;
    private JLabel Periodoselezionatoiniziomese;
    private JLabel Periodoselezionatofine;
    private JLabel Periodoselezionatofinemese;
    private JLabel DurataNoleggioLabel;
    private JLabel DurataNoleggio;
    private JLabel ClusterSelezionatoLabel;
    private JLabel ClusterSelezionato;
    private JLabel TotaleLabel;
    private JLabel Totale;
    private JLabel SimboloE;
    private JButton Tornaallaselezione;
    private JLabel esc;
    private JButton backButton;
    private JButton createContractButton;

    public CalculationView(JFrame frame, Client client) {
        // Create UI elements
        Riepilogo = new JLabel("Total info");
        Periodo = new JLabel("Periodo del noleggio : ");
        Periodoselezionatoinizio = new JLabel();
        Periodoselezionatoiniziomese = new JLabel("Maggio 2020");
        Periodoselezionatofine = new JLabel();
        Periodoselezionatofinemese = new JLabel("Maggio 2020");
        DurataNoleggioLabel = new JLabel("Durata noleggio : ");
        DurataNoleggio = new JLabel();
        ClusterSelezionatoLabel = new JLabel("Selected car : ");
        ClusterSelezionato = new JLabel();
        TotaleLabel = new JLabel("Totale : ");
        Totale = new JLabel();

        if (Objects.isNull(client.getRole())) {
            JOptionPane.showMessageDialog(frame,
                    "You will not be able to complete your booking. Please register your profile.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            Tornaallaselezione = new JButton("Back");
            Tornaallaselezione.setBounds(825, 500, 150, 70);
            frame.add(Tornaallaselezione);
        } else if (client.getRole() == Roles.CLIENT) {
            backButton = new JButton("Back");
            backButton.setBounds(600, 500, 200, 70);
            frame.add(backButton);
            createContractButton = new JButton("Create contract");
            createContractButton.setBounds(825, 500, 150, 70);
            frame.add(createContractButton);
        }

        esc = new JLabel();

        //Add elements
        Riepilogo.setBounds(50, 25, 100, 70);
        frame.add(Riepilogo);
        Periodo.setBounds(50, 75, 150, 70);
        frame.add(Periodo);
        Periodoselezionatoinizio.setBounds(200, 75, 50, 70);
        frame.add(Periodoselezionatoinizio);
        Periodoselezionatoiniziomese.setBounds(250, 75, 100, 70);
        frame.add(Periodoselezionatoiniziomese);
        Periodoselezionatofine.setBounds(350, 75, 50, 70);
        frame.add(Periodoselezionatofine);
        Periodoselezionatofinemese.setBounds(400, 75, 100, 70);
        frame.add(Periodoselezionatofinemese);
        DurataNoleggioLabel.setBounds(500, 75, 100, 70);
        frame.add(DurataNoleggioLabel);
        DurataNoleggio.setBounds(600, 75, 100, 70);
        frame.add(DurataNoleggio);
        ClusterSelezionatoLabel.setBounds(50, 125, 200, 70);
        frame.add(ClusterSelezionatoLabel);
        ClusterSelezionato.setBounds(250, 125, 100, 70);
        frame.add(ClusterSelezionato);
        TotaleLabel.setBounds(350, 125, 100, 70);
        frame.add(TotaleLabel);
        Totale.setBounds(450, 125, 100, 70);
        frame.add(Totale);

        esc.setBounds(0, 0, 1, 1);
        frame.add(esc);

        frame.repaint();
    }

    public JButton getTornaallaselezione() {
        return Tornaallaselezione;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getCreateContractButton() {
        return createContractButton;
    }

    public void setPeriodoselezionatoinizio(Integer periodoselezionatoinizio) {
        Periodoselezionatoinizio.setText(periodoselezionatoinizio.toString());
    }

    public void setPeriodoselezionatofine(Integer periodoselezionatofine) {
        Periodoselezionatofine.setText(periodoselezionatofine.toString());
    }

    public void setDurataNoleggio(Integer durataNoleggio) {
        DurataNoleggio.setText(durataNoleggio.toString());
    }

    public void setClusterSelezionato(String clusterSelezionato) {
        ClusterSelezionato.setText(clusterSelezionato);
    }

    public void setTotale(Double totale) {
        Totale.setText(totale.toString());
    }
}
