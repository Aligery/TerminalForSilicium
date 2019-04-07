package ru.miet.robotics;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class PortReader implements SerialPortEventListener {

    private static SerialPort serialPort;
    private String answerData;
    PortReader(SerialPort serialPort) {
        this.serialPort=serialPort;
    }

    public void serialEvent(SerialPortEvent event) {
        if(event.isRXCHAR() && event.getEventValue() > 0){
            try {
                //Получаем ответ от устройства, обрабатываем данные и т.д.
                this.answerData = serialPort.readString(event.getEventValue());
                //И снова отправляем запрос
//                    serialPort.writeString("Get data");
            }
            catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
    }

    public String getAnswerData() {
        return answerData;
    }
}