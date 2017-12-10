package Control;

import processing.core.PApplet;
import processing.serial.Serial;

import java.util.ArrayList;

public class RobotInstructionInterface extends PApplet {
    private final int debug = 1;
    ArrayList<String> serialList;
    Serial sPort;  // Create object from Serial class

    /**
     * Adds delay so that robot can catch up to commands
     *
     * @param ms time in miliseconds
     */
    private void delayMs(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets available port list.
     */
    public void getPortList() {
        serialList = new ArrayList<>();
        for (int i = 0; i < Serial.list().length; i++) {
            serialList.add(Serial.list()[i]);
            System.out.println("Port number " + i + ": " + Serial.list()[i]);
        }
    }

    /**
     * Connects to a specified serial port from a list of available ports.
     *
     * @param index to connect to a port form a list
     * @see RobotInstructionInterface#getPortList()
     */
    public void connectSerial(int index) {
        try {
            sPort = new Serial(this, Serial.list()[index], 38400);
        } catch (Exception e) {
            if (debug == 1) {
                System.out.println("Error Opening Serial Port");
            }
        }
        delayMs(200);//add delay for some systems
    }

    /**
     * Disconnects from a current port
     */
    public void disconnectSerial() {
        if (sPort != null) {
            sPort.stop();
            sPort = null;
        }
    }

    /**
     * Sends a stream of information to a robot to execute a specified command.
     * Please refer to comments on switch cases what each action does
     *
     * @param action preferred action
     * @param speed  actuation speed (does not affect action "0", speed limit -10 to +10)
     */
    public void actuate(int action, int speed) {
        int allButtons = 0;
        int right_V = 128;
        int right_H = 128;
        int left_V = 128;
        int left_H = 128;
        if (speed < -10) {
            System.out.println("Speed parameter is too low! Setting to lowest possible(-10)");
            speed = -10;
        }
        if (speed > 10) {
            System.out.println("Speed parameter is too high! Setting to highest possible(+10)");
            speed = 10;
        }
        switch (action) {
            //switch state
            case 0:
                allButtons = 16;
                System.out.println("Actuate[0]");
                //delayMs(100);//add delay for some systems

                break;
            //go forward (+)or backward (-)
            case 1:
                left_V = (int) Math.ceil((double) left_V + 12.8 * speed);
                if (speed == 10)
                    left_V--;
                System.out.println(left_V);
                break;
            //go left(-) or right (+)
            case 2:
                left_H = (int) Math.ceil((double) left_H + 12.8 * speed);
                if (speed == 10)
                    left_H--;
                System.out.println(left_H);
                break;
            //rotate left(-) or right (+)
            case 3:
                right_H = (int) Math.ceil((double) right_H + 12.8 * speed);
                if (speed == 10)
                    right_H--;
                System.out.println(right_H);
                break;
            default:
                System.out.println(action + " is not a sanctioned action. Plz fix");
        }
        if (sPort != null) {
            sPort.write(0xff);          //header
            sPort.write((byte) right_V); //right vertical joystick
            sPort.write((byte) right_H); //right horizontal joystick
            sPort.write((byte) left_V);  //left vertical joystick
            sPort.write((byte) left_H);  //left horizontal joystick
            sPort.write(allButtons);    //single byte holds all the button data
            sPort.write((byte) 0);       //0 char
            sPort.write((byte) (255 - (right_V + right_H + left_V + left_H + allButtons) % 256));  //checksum
            delayMs(100); //bigger delay by a robot can be interpreted as a lost connection and it will, for instance
            //drop on ground
        }
    }


}
