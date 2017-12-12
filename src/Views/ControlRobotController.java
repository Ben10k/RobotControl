package Views;

import Listeners.MotionListener;
import com.leapmotion.leap.Controller;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControlRobotController implements IController {
    private JPanel contentPanel;
    private JPanel backPanel;
    private JLabel label;
    private IController a;
    private MotionListener motionListener;


    ControlRobotController(){

        backPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("back");
                a = new MainMenuController();
                GUIController.getInstance().setContent(a.getContentPanel(), a.getTitle());
                motionListener.disconnect();

            }
        });
        Thread t1 = new Thread(() -> {
            init();
        });
        t1.start();
    }

    public void init() {
        Controller c = new Controller();
        c.setPolicy(Controller.PolicyFlag.POLICY_BACKGROUND_FRAMES);
        motionListener = new MotionListener(label);
        c.addListener(motionListener);
    }


    @Override
    public JPanel getContentPanel() {
        return contentPanel;
    }

    @Override
    public String getTitle() {
        return "Kontroliuoti robotą";
    }
}
