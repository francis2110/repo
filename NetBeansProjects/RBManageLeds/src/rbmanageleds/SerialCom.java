/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbmanageleds;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

/**
 *
 * @author fran
 */
public class SerialCom implements SerialPortEventListener {

    SerialPort serialPort;
    Enumeration portEnum;
    CommPortIdentifier portId;
    BufferedReader input;
    OutputStream output;
    //variable that says that the communication has started for first time or not.
    boolean initCom = false;
    /**
     * Milliseconds to block while waiting for port open
     */
    private static final int TIME_OUT = 2000;
    /**
     * Default bits per second for COM port.
     */
    private static final int DATA_RATE = 9600;

    public Enumeration getPortEnum() {
        return portEnum;
    }

    public void setPortEnum(Enumeration portEnum) {
        this.portEnum = portEnum;
    }

    public void initialize() {
        System.out.println("ports identified.");
        portId = null;
        System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");
        //System.setProperty("gnu.io.rxtx.SerialPorts","/dev/ttyUSB0");
        portEnum = CommPortIdentifier.getPortIdentifiers();

    }

    public CommPortIdentifier getPortId() {
        return portId;
    }

    public void setPortId(CommPortIdentifier portId) {
        this.portId = portId;
    }

    public OutputStream getOutput() {
        return output;
    }

    public void setOutput(OutputStream output) {
        this.output = output;
    }

    public void findPort(String portId) {
        while (getPortEnum().hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) getPortEnum().nextElement();
            //  if (currPortId.getName().equals(portId)) {
            setPortId(currPortId);
            System.out.println("Port selected is " + currPortId.getName());
            // }
        }

    }

    public boolean initPortCommunication() {
        try {
            //open serial port and use class name for appName.
            serialPort = (SerialPort) getPortId().open(this.getClass().getName(), TIME_OUT);
            //set port parameters:
            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            //open the streams 
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();
            //add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            return true;

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return false;
        }

    }

    /**
     * This should be called when you stop using the port. This will prevent
     * port locking on platforms like Linux.
     */
    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    /**
     * Handle and event on serial port. Send data to arduino.
     *
     */
    @Override
    public synchronized void serialEvent(SerialPortEvent spe) {
        if (spe.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                //only send data to arduino
                System.out.println(output);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

}
