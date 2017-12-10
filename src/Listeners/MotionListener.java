package Listeners;

import Control.RobotInstructionInterface;
import com.leapmotion.leap.*;

import javax.swing.*;

public class MotionListener extends Listener {
    JLabel label;
    Frame firstFrame;
    RobotInstructionInterface hexapod;
    int timer = 0;
    int calibration = 0;
    boolean actuate = true;


    public MotionListener(JLabel label) {
        this.label = label;
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

        //if (firstFrame == null) firstFrame = controller.frame();

//        Frame currentFrame = controller.frame();


        if (controller.frame().hands().isEmpty())
            firstFrame = null;


        if (controller.frame(1).hands().isEmpty()
                && !controller.frame().hands().isEmpty()) {
//            firstFrame = controller.frame();
            System.out.println("Hand Captured");
            calibration = 1;
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
            FingerList fingers = firstHand.fingers();


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
                } else {
                    if (Math.abs(speed) > Math.abs(sideways)) {
                        hexapod.actuate(1, speed);
                        System.out.println("moveForwardBackward " + speed);
                    } else {
                        hexapod.actuate(2, sideways);
                        System.out.println("moveLeftRight       " + sideways);

                    }
                }

            }
            timer++;
            if (timer == 20)
                timer = 0;

        }
    }

}
