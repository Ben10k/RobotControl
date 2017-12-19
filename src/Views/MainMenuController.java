package Views;

import javax.swing.*;
import java.awt.*;
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
                a = new RobotsInformationController("Resources/Robots-Today/");

                GUIController.getInstance().setContent(a.getContentPanel(), a.getTitle());

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                robotsTodayPanel.setBackground(GUIController.getInstance().getOnColor());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                robotsTodayPanel.setBackground(GUIController.getInstance().getOffColor());

            }
        });

        historyOfRoboticsPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("historyOfRoboticsPanel");
                a = new RobotsInformationController("Resources/History-of-Robotics/");
                GUIController.getInstance().setContent(a.getContentPanel(), a.getTitle());

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                historyOfRoboticsPanel.setBackground(GUIController.getInstance().getOnColor());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                historyOfRoboticsPanel.setBackground(GUIController.getInstance().getOffColor());

            }
        });

        controlRobotPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("controlRobotPanel");
                a = new ControlRobotController();

                GUIController.getInstance().setContent(a.getContentPanel(), a.getTitle());
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                controlRobotPanel.setBackground(GUIController.getInstance().getOnColor());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                controlRobotPanel.setBackground(GUIController.getInstance().getOffColor());

            }
        });

        languagePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("languagePanel");
                a = new LanguageChoiceController();
                GUIController.getInstance().setContent(a.getContentPanel(), a.getTitle());

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                languagePanel.setBackground(GUIController.getInstance().getOnColor());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                languagePanel.setBackground(GUIController.getInstance().getOffColor());

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
