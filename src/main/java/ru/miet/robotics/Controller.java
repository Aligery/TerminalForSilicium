package ru.miet.robotics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
    private TextArea sendedInfo;
    @FXML
    public TextArea recievedInfo;

    private static Settings settings = new Settings();

    private List<String> listForTextArea=  new ArrayList<String>();

    private List<String> recievedText = new ArrayList<>();

    private static SerialPort serialPort = new SerialPort(settings.getComPort());

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    Date date = new Date();

    @FXML
    private void actionOnButtonOne(ActionEvent actionEvent) {
        sendData(settings.getCommandForButtonOne());
        setTextArea(listForTextArea);
    }
    @FXML
    private void actiononButtonTwo(ActionEvent actionEvent){
        sendData(settings.getCommandForButtonTwo());
        setTextArea(listForTextArea);
    }
    @FXML
    private void actionOnButtonThree(ActionEvent actionEvent) {
        sendData(settings.getCommandForButtonThree());
        setTextArea(listForTextArea);
    }
    @FXML
    private void actionOnButtonFour(ActionEvent actionEvent) {
        sendData(settings.getCommandForButtonFour());
        setTextArea(listForTextArea);
    }
    @FXML
    private void actionOnButtonFive(ActionEvent actionEvent) {
        sendData(settings.getCommandForButtonFive());
        setTextArea(listForTextArea);
    }
    @FXML
    private void actionOnButtonSix(ActionEvent actionEvent) {
        sendData(settings.getCommandForButtonSix());
        setTextArea(listForTextArea);
    }
    @FXML
    private void actionOnHandButton(ActionEvent actionEvent) {
        sendData(textField.getText());
        setTextArea(listForTextArea);
    }

    private void setTextArea(List<String> strings) {
        for (String element:strings) {
            sendedInfo.setText(element);
        }
    }

    private void sendData(String data) {
        try {
            serialPort.openPort();
            serialPort.setParams(Integer.parseInt(settings.getBaudRate()),
                    Integer.parseInt(settings.getDataBits()),
                    Integer.parseInt(settings.getStopBits()),
                    Integer.parseInt(settings.getStopBits()),
                    false, //RTS
                    false); //DTR
            serialPort.setEventsMask(SerialPort.MASK_RXCHAR);
            serialPort.addEventListener(new EventListener ());
            serialPort.writeString(data);
            listForTextArea.add("Команда " + data + " отправлена." + " Время" + dateFormat.format(date));
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
        finally {
            try {
                serialPort.closePort(); //boiler plate code, к сожалению, implements autocloseble не реализован у класса
            } catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
    }

    private void getData(){
        try(FileReader fr = new FileReader("InfoFromController.txt")) {
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) {
                recievedText.add(scan.nextLine());
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void setTextRecievied(List<String> strings) {
        for (String element:strings) {
            recievedInfo.setText(element);
        }
    }

    private static class EventListener implements SerialPortEventListener {

        private static List<String> listForTextArea=  new ArrayList<String>();

        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0){
                try {
                    //Получаем ответ от устройства, обрабатываем данные и т.д.
                    String data = serialPort.readString(event.getEventValue());
                    try (FileWriter fileWriter = new FileWriter("InfoFromController.txt", true)){
                        fileWriter.write(data);
                        fileWriter.append('\n');
                        fileWriter.flush();
                    } catch (IOException IO) {
                        System.out.println(IO);
                    }
                }
                catch (SerialPortException ex) {
                    System.out.println(ex);
                }

            }
        }
    }
}
