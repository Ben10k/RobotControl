package Views;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuController implements IController {
    private JPanel robotsTodayPanel;
    private JPanel historyOfRoboticsPanel;
    private JPanel controlRobotPanel;
    private JPanel languagePanel;
    private JPanel contentPanel;
    private String title = "Main Menu";
    private IController a;


    public MainMenuController() {
        robotsTodayPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("robotsTodayPanel");
                a = new RobotsTodayController();
                GUIController.getInstance().setContent(a.getContentPanel(), a.getTitle());

            }
        });
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    @Override
    public String getTitle() {
        return title;
    }


}
