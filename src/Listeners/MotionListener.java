package Listeners;

import com.leapmotion.leap.*;

import javax.swing.*;

public class MotionListener extends Listener {
    JLabel label;
    Frame lastFrame;
    Frame firstFrame;


    public MotionListener(JLabel label) {
        this.label = label;
    }

    @Override
    public void onInit(Controller controller) {

    }

    @Override
    public void onExit(Controller controller) {

    }

    @Override
    public void onConnect(Controller controller) {
        System.out.println("MotionListener");
    }

    @Override
    public void onDisconnect(Controller controller) {

    }
    @Override
    public void onFrame(Controller controller) {
        if (lastFrame == null) lastFrame = controller.frame();
        if (firstFrame == null) firstFrame = controller.frame();

        Frame frame = controller.frame();

        Vector a = frame.rotationAxis(lastFrame);
        float b = frame.rotationAngle(lastFrame);

        float moveForwardBackward = frame.translation(firstFrame).getZ();
        float moveUpDown = frame.translation(firstFrame).getY();





        this.label.setText(String.valueOf(moveForwardBackward));






        lastFrame = frame;
    }
}
