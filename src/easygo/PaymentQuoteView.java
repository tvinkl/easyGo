package easygo;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentQuoteView {

    private JButton backButton;
    private JButton calculatePaymentButton;
    private JButton paymentViewBackButton;

    private JLabel carPickTimeLabel;
    private JLabel carPickDateLabel;
    private JLabel carReturnDateLabel;
    private JLabel carReturnTimeLabel;
    private JLabel dateOfBirthLabel;
    private JLabel dayOfBirth;
    private JLabel selectBrandsLabel;
    private JLabel selectModelsLabel;

    private JComboBox<Integer> carPickTimeComboBox;
    private JComboBox<Integer> carPickDayComboBox; // дата взятия
    private JComboBox<Integer> carPickMonthComboBox;
    private JComboBox<Integer> carPickYearComboBox;
    private JComboBox<Integer> carReturnTimeComboBox;
    private JComboBox<Integer> carReturnDayComboBox; // дата взятия
    private JComboBox<Integer> carReturnMonthComboBox;
    private JComboBox<Integer> carReturnYearComboBox;
    private JComboBox<String> brandsComboBox;
    private JComboBox<String> modelsComboBox;


    public PaymentQuoteView(List<Car> cars, JFrame frame){

        // Create UI elements

        carPickTimeLabel = new JLabel("Ora ritiro auto: ");
        carPickDateLabel = new JLabel("Data ritiro auto (gg/mm/aaaa): ");

        carPickTimeComboBox = new JComboBox<>(StaticData.HOURS_INT);
        carPickDayComboBox = new JComboBox<>(StaticData.DAYS);
        carPickMonthComboBox = new JComboBox<>(StaticData.MONTHS_INT);
        carPickYearComboBox = new JComboBox<>(StaticData.YEARS_FUTURE);

        carReturnTimeLabel = new JLabel("Ora riconsegna auto: ");
        carReturnDateLabel = new JLabel("Data riconsegna auto (gg/mm/aaaa): ");

        carReturnTimeComboBox = new JComboBox<>(StaticData.HOURS_INT);
        carReturnDayComboBox = new JComboBox<>(StaticData.DAYS);
        carReturnMonthComboBox = new JComboBox<>(StaticData.MONTHS_INT);
        carReturnYearComboBox = new JComboBox<>(StaticData.YEARS_FUTURE);
//        dateOfBirthLabel = new JLabel("Client birthday : ");
//        dayOfBirth = new JLabel("Day : ");

//        // start useless
//
//        gnc = new JComboBox<>(StaticData.DAYS);
//        MNClienteLabel = new JLabel("Month : ");
//        mnc = new JComboBox<>(StaticData.MONTHS);
//        ANClienteLabel = new JLabel("Year : ");
//        anc = new JComboBox<>(StaticData.YEARS_PAST);
//        DEPatenteLabel = new JLabel("License issue date : ");
//        GEPatenteLabel = new JLabel("Day : ");
//        gep = new JComboBox<>(StaticData.DAYS);
//        MEPatenteLabel = new JLabel("Month : ");
//        mep = new JComboBox<String>(StaticData.MONTHS);
//        AEPatenteLabel = new JLabel("Year : ");
//        aep = new JComboBox<>(StaticData.YEARS_PAST);

        selectBrandsLabel = new JLabel("Select brand: ");
        selectModelsLabel = new JLabel("Select model: ");
        // end useless

        java.util.List<String> brands = cars.stream().map(Car::getBrand).distinct().collect(Collectors.toList());
        brandsComboBox = new JComboBox<String>(brands.toArray(String[]::new));

        java.util.List<String> models = cars.stream().map(Car::getModel).distinct().collect(Collectors.toList());
        modelsComboBox = new JComboBox<String>(models.toArray(String[]::new));

        brandsComboBox.addActionListener(e -> {
            java.util.List<String> filteredModels = cars.stream()
                    .filter(it ->it.getBrand().equals(brandsComboBox.getSelectedItem().toString()))
                    .map(Car::getModel).distinct()
                    .collect(Collectors.toList());
            modelsComboBox.setModel(new DefaultComboBoxModel<>(filteredModels.toArray(String[]::new)));
            frame.repaint();
        });

        calculatePaymentButton = new JButton("Calculate");
        backButton = new JButton("Back");
        // Add UI element to frame
        frame.setLayout(null);

        carPickTimeLabel.setBounds(50, 20,200,70);
        frame.add(carPickTimeLabel);
        carPickTimeComboBox.setBounds(300,45,100,20);
        frame.add(carPickTimeComboBox);

        carPickDateLabel.setBounds(50, 50, 200, 70);
        frame.add(carPickDateLabel);
        carPickDayComboBox.setBounds(300, 75, 100, 20);
        frame.add(carPickDayComboBox);
        carPickMonthComboBox.setBounds(450,75,100,20);
        frame.add(carPickMonthComboBox);
        carPickYearComboBox.setBounds(600,75,100,20);
        frame.add(carPickYearComboBox);

        carReturnTimeLabel.setBounds(50,100,200,70);
        frame.add(carReturnTimeLabel);
        carReturnTimeComboBox.setBounds(300, 125, 100, 20);
        frame.add(carReturnTimeComboBox);

        carReturnDateLabel.setBounds(50, 125, 200, 70);
        frame.add(carReturnDateLabel);

        carReturnDayComboBox.setBounds(300, 150, 100, 20);
        frame.add(carReturnDayComboBox);
        carReturnMonthComboBox.setBounds(450, 150, 100, 20);
        frame.add(carReturnMonthComboBox);
        carReturnYearComboBox.setBounds(600, 150, 100, 20);
        frame.add(carReturnYearComboBox);


//        dateOfBirthLabel.setBounds(50, 250, 200, 70);
//        frame.add(dateOfBirthLabel);
//        dayOfBirth.setBounds(200, 250, 100, 70);
//        frame.add(dayOfBirth);
//        gnc.setBounds(300, 275, 100, 20);
//        frame.add(gnc);
//        MNClienteLabel.setBounds(450, 250, 100, 70);
//        frame.add(MNClienteLabel);
//        mnc.setBounds(550, 275, 100, 20);
//        frame.add(mnc);
//        ANClienteLabel.setBounds(675, 250, 100, 70);
//        frame.add(ANClienteLabel);
//        anc.setBounds(750, 275, 100, 20);
//        frame.add(anc);
//
//        DEPatenteLabel.setBounds(50, 300, 200, 70);
//        frame.add(DEPatenteLabel);
//        GEPatenteLabel.setBounds(200, 300, 100, 70);
//        frame.add(GEPatenteLabel);
//        gep.setBounds(300, 325, 100, 20);
//        frame.add(gep);
//        MEPatenteLabel.setBounds(450, 300, 100, 70);
//        frame.add(MEPatenteLabel);
//        mep.setBounds(550, 325, 100, 20);
//        frame.add(mep);
//        AEPatenteLabel.setBounds(675, 300, 100, 70);
//        frame.add(AEPatenteLabel);
//        aep.setBounds(750, 325, 100, 20);
//        frame.add(aep);

        selectBrandsLabel.setBounds(50, 350, 300, 70);
        frame.add(selectBrandsLabel);
        selectModelsLabel.setBounds(50, 375, 300, 70);
        frame.add(selectModelsLabel);

        brandsComboBox.setBounds(300, 375, 100, 20);
        frame.add(brandsComboBox);
        modelsComboBox.setBounds(300, 400, 100, 20);
        frame.add(modelsComboBox);

        calculatePaymentButton.setBounds(900, 575, 100, 50);
        frame.add(calculatePaymentButton);
        backButton.setBounds(1050, 575, 100, 50);
        frame.add(backButton);
    }

    public int getCarPickTimeComboBox() {
        return Integer.parseInt(carPickTimeComboBox.getSelectedItem().toString());
    }

    public int getCarPickDayComboBox() {
        return Integer.parseInt(carPickDayComboBox.getSelectedItem().toString());
    }


    public int getCarPickMonthComboBox() {
        return Integer.parseInt(carPickMonthComboBox.getSelectedItem().toString());
    }

    public int getCarPickYearComboBox() {
        return Integer.parseInt(carPickYearComboBox.getSelectedItem().toString());
    }

    public int getCarReturnTimeComboBox() {
        return Integer.parseInt(carReturnTimeComboBox.getSelectedItem().toString());
    }

    public int getCarReturnDayComboBox() {
        return Integer.parseInt(carReturnDayComboBox.getSelectedItem().toString());
    }

    public int getCarReturnMonthComboBox() {
        return Integer.parseInt(carReturnMonthComboBox.getSelectedItem().toString());
    }

    public int getCarReturnYearComboBox() {
        return Integer.parseInt(carReturnYearComboBox.getSelectedItem().toString());
    }

    public String getBrand() {
        return brandsComboBox.getSelectedItem().toString();
    }
    public String getModel() {
        return modelsComboBox.getSelectedItem().toString();
    }

    public JButton getCalculatePaymentButton() {
        return calculatePaymentButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

}
