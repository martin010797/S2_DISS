package Gui;

import Simulation.BeautySalonSimulator;
import Simulation.Events.Event;
import Simulation.Simulator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeautySalonGui implements ISimDelegate{
    private JFrame frame;

    private JPanel mainPanel;
    private JPanel chartPanel;
    private JButton startSimulationButton;
    private JLabel simulationTimeLabel;
    private JButton stopSimulationButton;
    private JButton pauseSimulationButton;
    private JRadioButton slowSimulationRadioButton;
    private JRadioButton fastSimulationRadioButton;
    private JLabel NumberOfReplicationsLabel;
    private JTextField numberOfReplicationsTextField;
    private JLabel lengthOfSimulationLabel;
    private JLabel lengthOfSleepLabel;
    private JTextField lengthOfSimulationTextField;
    private JTextField lengthOfSleepTextField;
    private JLabel deltaTLabel;
    private JTextField deltaTTextField;
    private JButton changeTheSpeedButton;
    private JTextField numberOfHairdressersTextField;
    private JTextField numberOfMakeupArtistsTextField;
    private JTextField numberOfReceptionistsTextField;
    private JLabel numberOfHairdressersLabel;
    private JLabel numberOfMakeupArtistsLabel;
    private JLabel numberOfReceptionistsLabel;
    private JTextPane statisticsTextPane;
    private JTextPane statesOfSystemTextPane;
    private JLabel isPausedLabel;
    private JLabel calendarLabel;
    private JLabel lastProcesseedEventTextLabel;
    private JLabel lastProcessedEventLabel;
    private JTextPane calendarTextPane;

    private DefaultXYDataset datasetLineChart;
    private XYSeries lineChartXYSeries;
    private JFreeChart lineChart;
    private BeautySalonSimulator simulator;
    private String lastStatesValues;
    private String lastCalendar;

    public BeautySalonGui() {
        frame = new JFrame("Beauty salon");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        simulator = new BeautySalonSimulator(1,100000000);
        simulator.setDeltaT(400);
        simulator.setSleepLength(400);
        simulator.registerDelegate(this);

        createDatasets();

        frame.pack();
        frame.setLocationRelativeTo(null);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        ButtonGroup speedOfTheSimulationButtonGroup = new ButtonGroup();
        speedOfTheSimulationButtonGroup.add(fastSimulationRadioButton);
        speedOfTheSimulationButtonGroup.add(slowSimulationRadioButton);

        slowSimulationRadioButton.setSelected(true);
        lengthOfSimulationLabel.setVisible(false);
        lengthOfSimulationTextField.setVisible(false);
        NumberOfReplicationsLabel.setVisible(false);
        numberOfReplicationsTextField.setVisible(false);

        setDeafultText();
        startSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulator.setDeltaT(Integer.parseInt(deltaTTextField.getText()));
                simulator.setSleepLength(Integer.parseInt(lengthOfSleepTextField.getText()));
                fastSimulationRadioButton.setEnabled(false);
                slowSimulationRadioButton.setEnabled(false);
                isPausedLabel.setVisible(false);
                simulator.setNumberOfHairstylists(Integer.parseInt(numberOfHairdressersTextField.getText()));
                simulator.setNumberOfMakeupArtists(Integer.parseInt(numberOfMakeupArtistsTextField.getText()));
                simulator.setNumberOfReceptionists(Integer.parseInt(numberOfReceptionistsTextField.getText()));
                simulator.simulate();
            }
        });
        stopSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulator.stopSimulation();
                fastSimulationRadioButton.setEnabled(true);
                slowSimulationRadioButton.setEnabled(true);
                isPausedLabel.setVisible(false);
            }
        });
        fastSimulationRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lengthOfSimulationLabel.setVisible(true);
                lengthOfSimulationTextField.setVisible(true);
                NumberOfReplicationsLabel.setVisible(true);
                numberOfReplicationsTextField.setVisible(true);
                lengthOfSleepLabel.setVisible(false);
                lengthOfSleepTextField.setVisible(false);
                deltaTLabel.setVisible(false);
                deltaTTextField.setVisible(false);
                changeTheSpeedButton.setVisible(false);
            }
        });
        slowSimulationRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lengthOfSimulationLabel.setVisible(false);
                lengthOfSimulationTextField.setVisible(false);
                NumberOfReplicationsLabel.setVisible(false);
                numberOfReplicationsTextField.setVisible(false);
                lengthOfSleepLabel.setVisible(true);
                lengthOfSleepTextField.setVisible(true);
                deltaTLabel.setVisible(true);
                deltaTTextField.setVisible(true);
                changeTheSpeedButton.setVisible(true);
            }
        });
        pauseSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(simulator.setPaused(true)){
                    isPausedLabel.setVisible(true);
                }
            }
        });
        changeTheSpeedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulator.setDeltaT(Integer.parseInt(deltaTTextField.getText()));
                simulator.setSleepLength(Integer.parseInt(lengthOfSleepTextField.getText()));
            }
        });
    }

    @Override
    public void refresh(Simulator simulator) {
        BeautySalonSimulator sim = (BeautySalonSimulator) simulator;
        simulationTimeLabel.setText(sim.getCurrentTime());

        String statesOfSystem = sim.getStatesOfSimulation();
        //aby vykreslovalo len ked nastala zmena nejakej hondoty
        if (!statesOfSystem.equals(lastStatesValues)) {
            statesOfSystemTextPane.setText(statesOfSystem);
            lastStatesValues = statesOfSystemTextPane.getText();
        }
        String calendar = sim.getCalendar();
        if (!calendar.equals(lastCalendar)){
            calendarTextPane.setText(calendar);
            lastCalendar = calendarTextPane.getText();
        }

        //v spracovanych nezobrazuje systemove udalosti
        if (sim.getLastProcessedEvent() != null){
            Event e = sim.getLastProcessedEvent();
            lastProcessedEventLabel.setText(sim.converTime(e.getTime()) + "  " + e.getNameOfTheEvent());
        }
    }

    public void createDatasets(){
        datasetLineChart = new DefaultXYDataset();
        lineChartXYSeries = new XYSeries("waitingQueue");
        lineChart = ChartFactory.createXYLineChart(
                "Priemerne pocty cakajucich v rade na recepcii",
                "Cas",
                "Dlzka radu",
                datasetLineChart,
                PlotOrientation.VERTICAL,
                false,
                true,
                false);
        //aby spravne skalovalo a nezahrnovalo stale 0
        NumberAxis yAxis = (NumberAxis) lineChart.getXYPlot().getRangeAxis();
        yAxis.setAutoRangeIncludesZero(false);

        ChartPanel panel = new ChartPanel(lineChart);
        chartPanel.removeAll();
        chartPanel.add(panel, BorderLayout.CENTER);
    }

    private void setDeafultText(){
        lastProcessedEventLabel.setText("-");
        calendarTextPane.setText("");
        isPausedLabel.setVisible(false);
        simulationTimeLabel.setText(simulator.getCurrentTime());
        numberOfMakeupArtistsTextField.setText("1");
        numberOfReceptionistsTextField.setText("1");
        numberOfHairdressersTextField.setText("1");
        lengthOfSleepTextField.setText("400");
        deltaTTextField.setText("400");
        numberOfReplicationsTextField.setText("100000");
        lengthOfSimulationTextField.setText("1");
        String text = "Pocet ludi v radoch: -\n  Rad pred recepciou: -\n  Rad pred upravou ucesu: -" +
                "\n  Rad pred licenim: -\n  Rad pred platenim: - \nPocet prichodov zakaznikov: -" +
                "\nPocet obsluzenych zakaznikov: -\nStavy personalu: - \nStavy zakaznikov v systeme: -";
        statesOfSystemTextPane.setText(text);
        statisticsTextPane.setText("Statistiky: -");
        lastStatesValues = statesOfSystemTextPane.getText();
        lastCalendar = calendarTextPane.getText();
    }
}
