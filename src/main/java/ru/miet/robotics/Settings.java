package ru.miet.robotics;


import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Settings {

    private int commandForButtonOne;
    private int commandForButtonTwo;
    private int commandForButtonThree;
    private int commandForButtonFour;
    private int commandForButtonFive;
    private int commandForButtonSix;
    private String comPort;
    private int baudRate;
    private int dataBits;
    private int stopBits;
    private int parity;


    public int getCommandForButtonOne() {
        return commandForButtonOne;
    }

    public int getCommandForButtonTwo() {
        return commandForButtonTwo;
    }

    public int getCommandForButtonThree() {
        return commandForButtonThree;
    }

    public int getCommandForButtonFour() {
        return commandForButtonFour;
    }

    public int getCommandForButtonFive() {
        return commandForButtonFive;
    }

    public int getCommandForButtonSix() {
        return commandForButtonSix;
    }

    public String getComPort() {
        return comPort;
    }

    public int getBaudRate() {
        return baudRate;
    }

    public int getDataBits() {
        return dataBits;
    }

    public int getStopBits() {
        return stopBits;
    }

    public int getParity() {
        return parity;
    }

    public Settings() {
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/application.properties");
            property.load(fis);
            this.commandForButtonOne=Integer.parseInt(property.getProperty("commandForButtonOne"));
            this.commandForButtonTwo=Integer.parseInt(property.getProperty("commandForButtonTwo"));
            this.commandForButtonThree=Integer.parseInt(property.getProperty("commandForButtonThree"));
            this.commandForButtonFour=Integer.parseInt(property.getProperty("commandForButtonFour"));
            this.commandForButtonFive=Integer.parseInt(property.getProperty("commandForButtonFive"));
            this.commandForButtonSix=Integer.parseInt(property.getProperty("commandForButtonSix"));
            this.comPort=property.getProperty("comPort");
            this.baudRate=Integer.parseInt(property.getProperty("baudRate"));
            this.dataBits=Integer.parseInt(property.getProperty("dataBits"));
            this.stopBits=Integer.parseInt(property.getProperty("stopBits"));
            this.parity=Integer.parseInt(property.getProperty("parity"));

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }
}
