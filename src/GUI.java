import Listeners.GestureListener;
import Listeners.MotionListener;
import Listeners.MouseListener;
import Views.GUIController;
import com.leapmotion.leap.Controller;

import javax.swing.*;
import java.io.IOException;

public class GUI extends JFrame {
    private static final long serialVersionUID = 6411499808530678723L;

    public JLabel label1;
    public JLabel label2;

    public Controller c = new Controller();
    public GestureListener gestureListener;
    public MotionListener motionListener;
    public MouseListener mouseListener;
    //public MouseListener a
    public JPanel panel;

    public GUI() {
        c.setPolicy(Controller.PolicyFlag.POLICY_BACKGROUND_FRAMES);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(300, 200);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));


        this.label1 = new JLabel("Waiting for Gestures");
        panel.add(label1);

        gestureListener = new GestureListener(this.label1);
        c.addListener(gestureListener);


        this.label2 = new JLabel("Waiting for Motions");
        panel.add(label2);
        motionListener = new MotionListener(this.label2);
        c.addListener(motionListener);

        mouseListener = new MouseListener();
        //c.addListener(mouseListener);

        this.add(panel);
        setVisible(true);
    }


    public static void main(String[] args) {
        // Setting listeners up and debug information
        GUI gui = new GUI();

        // Main application
        GUIController control = GUIController.getInstance();


        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}