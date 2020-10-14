package easygo;

import javax.swing.*;
import java.util.Objects;

public class CalculationView {
//    private JLabel totalHoursRentLabel;
//    private JLabel Periodo;
//    private JLabel selecterPeriodLabel;
//    private JLabel selectedPeriodHoursLabel;
//    private JLabel selectedPeriodDaysLabel;
//    private JLabel Periodoselezionatofinemese;
    private JLabel totalHoursLabel;
   // private JLabel DurataNoleggio;
    private JLabel selectedCarLabel;

    //private JLabel ClusterSelezionato;
    private JLabel totalCostLabel;
    private JLabel totalHoursLabelInput;
    private JLabel totalCostLabelInput;
    private JButton toMenuButton;
    private JLabel selectedCarLabelInput;
    private JButton backButton;
    private JButton createContractButton;

    public CalculationView(JFrame frame, Client client) {
        // Create UI elements

        toMenuButton = new JButton();

        totalHoursLabel = new JLabel("Durata totale del noleggio (ore): ");
        totalHoursLabelInput = new JLabel();

        selectedCarLabel = new JLabel("Auto selezionata: ");
        selectedCarLabelInput = new JLabel();

        totalCostLabel = new JLabel("Costo totale : ");
        totalCostLabelInput = new JLabel();


        createContractButton = new JButton("Create contract");
        createContractButton.setBounds(825, 500, 150, 70);

        if (Objects.isNull(client.getRole())) {
            JOptionPane.showMessageDialog(frame,
                    "You will not be able to complete your booking. Please register your profile.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else if (client.getRole() == Roles.CLIENT) {
            frame.add(createContractButton);
        }

        backButton = new JButton("Back");
        backButton.setBounds(600, 500, 200, 70);
        frame.add(backButton);

        //Add elements
        totalHoursLabel.setBounds(50, 50, 200, 70);
        frame.add(totalHoursLabel);
        totalHoursLabelInput.setBounds(250, 50, 50, 70);
        frame.add(totalHoursLabelInput);

        selectedCarLabel.setBounds(50, 100, 200, 70);
        frame.add(selectedCarLabel);
        selectedCarLabelInput.setBounds(250, 100, 50, 70);
        frame.add(selectedCarLabelInput);

        totalCostLabel.setBounds(50, 150, 200, 70);
        frame.add(totalCostLabel);
        totalCostLabelInput.setBounds(250, 150, 50, 70);
        frame.add(totalCostLabelInput);

//        totalHoursLabel.setBounds(500, 75, 100, 70);
//        frame.add(totalHoursLabel);
//        DurataNoleggio.setBounds(600, 75, 100, 70);
//        frame.add(DurataNoleggio);
//        selectedCarLabel.setBounds(50, 125, 200, 70);
//        frame.add(selectedCarLabel);
//        ClusterSelezionato.setBounds(250, 125, 100, 70);
//        frame.add(ClusterSelezionato);
//        totalCostLabel.setBounds(350, 125, 100, 70);
//        frame.add(totalCostLabel);
//        Totale.setBounds(450, 125, 100, 70);
//        frame.add(Totale);
//
//        esc.setBounds(0, 0, 1, 1);
//        frame.add(esc);

        frame.repaint();
    }


    public void setTotalHoursLabelInput(int totalHours) {
        this.totalHoursLabelInput.setText(String.valueOf(totalHours));
    }

    public void setTotalCostLabelInput(float totalCost) {
        this.totalCostLabelInput.setText(String.valueOf(totalCost));
    }

    public void setSelectedCarLabelInput(int selectedCar) {
        this.selectedCarLabelInput.setText(String.valueOf(selectedCar));
    }

    public JButton getToMenuButton() {
        return toMenuButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getCreateContractButton() {
        return createContractButton;
    }

//    public void setSelecterPeriodLabel(Integer selecterPeriodLabel) {
//        this.selecterPeriodLabel.setText(selecterPeriodLabel.toString());
//    }
//
//    public void setSelectedPeriodDaysLabel(Integer selectedPeriodDaysLabel) {
//        this.selectedPeriodDaysLabel.setText(selectedPeriodDaysLabel.toString());
//    }

//    public void setDurataNoleggio(Integer durataNoleggio) {
//        DurataNoleggio.setText(durataNoleggio.toString());
//    }
//
//    public void setClusterSelezionato(String clusterSelezionato) {
//        ClusterSelezionato.setText(clusterSelezionato);
//    }

}
