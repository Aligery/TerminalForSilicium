package ru.miet.robotics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jssc.SerialPort;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private Button buttonOne;
    @FXML
    private Button buttonTwo;
    @FXML
    private Button buttonThree;
    @FXML
    private Button buttonFour;
    @FXML
    private Button buttonFive;
    @FXML
    private Button buttonSix;
    @FXML
    private Button buttonHand;
    @FXML
    private TextField textField;
    @FXML
    private TextArea textArea;

    private static Settings settings = new Settings();

    private static ConnectionToSerialPort connectionToSerialPort = new ConnectionToSerialPort();

    private List<String> listForTextArea=  new ArrayList<String>();

    private static SerialPort serialPort = new SerialPort(settings.getComPort());

    @FXML
    private void actionOnButtonOne(ActionEvent actionEvent) {
        listForTextArea.add(connectionToSerialPort.getConnection(serialPort, settings, settings.getCommandForButtonOne()));
        setTextArea(listForTextArea);
    }
    @FXML
    private void actiononButtonTwo(ActionEvent actionEvent){
        listForTextArea.add(connectionToSerialPort.getConnection(serialPort, settings, settings.getCommandForButtonTwo()));
        setTextArea(listForTextArea);
    }
    @FXML
    private void actionOnButtonThree(ActionEvent actionEvent) {
        listForTextArea.add(connectionToSerialPort.getConnection(serialPort, settings, settings.getCommandForButtonThree()));
        setTextArea(listForTextArea);
    }
    @FXML
    private void actionOnButtonFour(ActionEvent actionEvent) {
        listForTextArea.add(connectionToSerialPort.getConnection(serialPort, settings, settings.getCommandForButtonFour()));
        setTextArea(listForTextArea);
    }
    @FXML
    private void actionOnButtonFive(ActionEvent actionEvent) {
        listForTextArea.add(connectionToSerialPort.getConnection(serialPort, settings, settings.getCommandForButtonFive()));
        setTextArea(listForTextArea);
    }
    @FXML
    private void actionOnButtonSix(ActionEvent actionEvent) {
        listForTextArea.add(connectionToSerialPort.getConnection(serialPort, settings, settings.getCommandForButtonSix()));
        setTextArea(listForTextArea);
    }
    @FXML
    private void actionOnHandButton(ActionEvent actionEvent) {
        listForTextArea.add(connectionToSerialPort.getConnection(serialPort, settings, Integer.parseInt(textField.getText())));
        setTextArea(listForTextArea);
    }

    private void setTextArea(List<String> strings) {
        for (String element:strings) {
            textArea.setText(element);
        }
    }
}
