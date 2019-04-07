package ru.miet.robotics;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.util.Date;

public class ConnectionToSerialPort {


    private static SerialPort serialPort;
    private String answerData;

    public String getConnection(SerialPort serialPort, Settings settings, int command) {

        this.serialPort=serialPort;
        Date date = new Date();
        try {
            serialPort.openPort();
            serialPort.setParams(settings.getBaudRate(),
                                 settings.getDataBits(),
                                 settings.getStopBits(),
                                 settings.getStopBits());
            //Включаем аппаратное управление потоком
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            //Устанавливаем ивент лисенер и маску
            PortReader portReader = new PortReader(serialPort);
            serialPort.addEventListener(portReader, SerialPort.MASK_RXCHAR);
            //Отправляем запрос устройству
            serialPort.writeString("Get data");
            return command + "sended" + date.toString() + "\n" +
                    "answer" + portReader.getAnswerData() + "\n";
        } catch (SerialPortException ex) {
            return ex.toString();
        }
    }

}
