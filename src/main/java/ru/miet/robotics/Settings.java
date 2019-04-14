package ru.miet.robotics;


import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Settings {

    private String commandForButtonOne;
    private String commandForButtonTwo;
    private String commandForButtonThree;
    private String commandForButtonFour;
    private String commandForButtonFive;
    private String commandForButtonSix;
    private String comPort;
    private String baudRate;
    private String dataBits;
    private String stopBits;
    private String parity;

    public String getCommandForButtonOne() {
        return commandForButtonOne;
    }

    public String getCommandForButtonTwo() {
        return commandForButtonTwo;
    }

    public String getCommandForButtonThree() {
        return commandForButtonThree;
    }

    public String getCommandForButtonFour() {
        return commandForButtonFour;
    }

    public String getCommandForButtonFive() {
        return commandForButtonFive;
    }

    public String getCommandForButtonSix() {
        return commandForButtonSix;
    }

    public String getComPort() {
        return comPort;
    }

    public String getBaudRate() {
        return baudRate;
    }

    public String getDataBits() {
        return dataBits;
    }

    public String getStopBits() {
        return stopBits;
    }

    public String getParity() {
        return parity;
    }

    public Settings() {
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/application.properties");
            property.load(fis);
            this.commandForButtonOne=property.getProperty("commandForButtonOne");
            this.commandForButtonTwo=property.getProperty("commandForButtonTwo");
            this.commandForButtonThree=property.getProperty("commandForButtonThree");
            this.commandForButtonFour=property.getProperty("commandForButtonFour");
            this.commandForButtonFive=property.getProperty("commandForButtonFive");
            this.commandForButtonSix=property.getProperty("commandForButtonSix");
            this.comPort=property.getProperty("comPort");
            this.baudRate=property.getProperty("baudRate");
            this.dataBits=property.getProperty("dataBits");
            this.stopBits=property.getProperty("stopBits");
            this.parity=property.getProperty("parity");

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }
}
