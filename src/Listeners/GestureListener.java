package Listeners;

import com.leapmotion.leap.*;

import javax.swing.*;

public class GestureListener extends Listener {
    JLabel label;

    public GestureListener(JLabel label) {
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
        System.out.println("GestureListener");
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
    }

    @Override
    public void onDisconnect(Controller controller) {

    }

    @Override
    public void onFrame(Controller controller) {
        Frame frame = controller.frame();

        GestureList glist = frame.gestures();

        for (int i = 0; i < glist.count(); i++) {
            Gesture g = glist.get(i);

            switch (g.type()) {
                case TYPE_CIRCLE:
                    CircleGesture circle = new CircleGesture(g);
                    this.label.setText("TYPE_CIRCLE");
                    break;
                case TYPE_KEY_TAP:
                    KeyTapGesture key = new KeyTapGesture(g);
                    this.label.setText("TYPE_KEY_TAP");
                    break;
                case TYPE_SCREEN_TAP:
                    ScreenTapGesture screen = new ScreenTapGesture(g);
                    this.label.setText("TYPE_SCREEN_TAP");
                    break;
                case TYPE_SWIPE:
                    SwipeGesture swipe = new SwipeGesture(g);
                    this.label.setText("TYPE_SWIPE");
                    break;
                default:
                    System.out.println("nothing!");
                    break;
            }
        }
    }
}