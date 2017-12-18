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

    public void goBack(){
        System.out.println("back");
        a = new MainMenuController();
        GUIController.getInstance().setContent(a.getContentPanel(), a.getTitle());
        Controller c = GUIController.getInstance().getLeapMotion();
        c.removeListener(motionListener);
        c.addListener(GUIController.getInstance().getMouseListener());
    }


    ControlRobotController(){

        backPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                goBack();
            }
        });
        Thread t1 = new Thread(() -> {
            init();
        });
        t1.start();
    }

    public void init() {
        Controller c = GUIController.getInstance().getLeapMotion();
        c.removeListener(GUIController.getInstance().getMouseListener());
        c.setPolicy(Controller.PolicyFlag.POLICY_BACKGROUND_FRAMES);
        motionListener = new MotionListener(label,this);
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
