package Listeners;

import Control.RobotInstructionInterface;
import Views.ControlRobotController;
import Views.GUIController;
import Views.IController;
import Views.MainMenuController;
import com.leapmotion.leap.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MotionListener extends Listener {
    Frame firstFrame;
    RobotInstructionInterface hexapod;
    int timer = 0;
    int calibration = 0;
    JLabel label;
    boolean veryFirstFrame = true;
    int insertHandCounter = 0;
    int sleep = 10;
    ControlRobotController control;


    public MotionListener(JLabel label, ControlRobotController control) {
        this.label = label;
        this.control = control;
    }


    public void disconnect() {
        hexapod.disconnectSerial();
        System.out.println("Port disconnected");
    }

    @Override
    public void onInit(Controller controller) {
        hexapod = new RobotInstructionInterface();
        hexapod.getPortList();
        hexapod.connectSerial(0);
    }

    @Override
    public void onExit(Controller controller) {
        hexapod.disconnectSerial();
        System.out.println("Port disconnected");
    }

    @Override
    public void onConnect(Controller controller) {


        System.out.println("MotionListener");
    }

    @Override
    public void onDisconnect(Controller controller) {
        hexapod.disconnectSerial();
        System.out.println("Port disconnected");
    }

    @Override
    public void onFrame(Controller controller) {
        if (insertHandCounter > 300) {
            if (sleep == 0)
            {
                label.setText("This is where I should go to sleep");
                control.goBack();

            }
            else {
                label.setText("Entering sleep mode in " + sleep + " seconds");
                sleep--;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!controller.frame().hands().isEmpty()) {
                System.out.println("Hand Captured");
                label.setText("Loading");
                insertHandCounter = 0;
                sleep = 10;
                calibration = 1;
            }


        } else {

            if (controller.frame().hands().isEmpty()) {
                firstFrame = null;
                label.setText("Insert hand");
                insertHandCounter++;
            }


            if ((controller.frame(1).hands().isEmpty() && !controller.frame().hands().isEmpty())
                    || (!controller.frame().hands().isEmpty() && veryFirstFrame)) {
                System.out.println("Hand Captured");
                calibration = 1;
                label.setText("Loading");
                insertHandCounter = 0;
            }

            if (calibration == 50) {
                firstFrame = controller.frame();
                System.out.println("Hand Calibrated");
                hexapod.actuate(0, 5);
                calibration = 0;
            }
            if (calibration != 0) {
                calibration++;
                System.out.println("Hand calibration in " + (50 - calibration));
            }

            Frame currentFrame = controller.frame();

            if (firstFrame != null) {

                HandList hands = currentFrame.hands();
                Hand firstHand = hands.get(0);


                float moveForwardBackward = -currentFrame.translation(firstFrame).getZ();
                float moveLeftRight = currentFrame.translation(firstFrame).getX();
                float rotate = firstHand.rotationAngle(firstFrame, Vector.yAxis());


                if (timer == 0) {

                    int speed = Math.round(moveForwardBackward / 7);
                    int sideways = Math.round(moveLeftRight / 7);
                    int rotation = Math.round(rotate * 10);

                    if (Math.abs(speed) < 2)
                        speed = 0;
                    if (Math.abs(sideways) < 3)
                        sideways = 0;

                    if (Math.abs(rotation) > 4) {
                        hexapod.actuate(3, rotation);
                        System.out.println("Rotate              " + rotation);
                        if (rotation > 0)
                            label.setText("rotating Right");
                        if (rotation < 0)
                            label.setText("rotating Left");
                    } else {
                        if (Math.abs(speed) > Math.abs(sideways)) {
                            hexapod.actuate(1, speed);
                            System.out.println("moveForwardBackward " + speed);
                            if (speed > 0)
                                label.setText("moving Forward");
                            if (speed < 0)
                                label.setText("moving Backward");
                            if (speed == 0)
                                label.setText("waiting for actions");

                        } else {
                            hexapod.actuate(2, sideways);
                            System.out.println("moveLeftRight       " + sideways);
                            if (sideways > 0)
                                label.setText("moving Right");
                            if (sideways < 0)
                                label.setText("moving Left");
                            if (sideways == 0)
                                label.setText("waiting for actions");
                        }
                    }
                }
                timer++;
                if (timer == 20)
                    timer = 0;

            }
            veryFirstFrame = false;
        }
    }
}
