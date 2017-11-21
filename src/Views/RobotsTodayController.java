package Views;

import javax.swing.*;

public class RobotsTodayController implements IController{
    private JPanel backPanel;
    private JPanel moreInfoPanel;
    private JPanel mainPanel;
    private JPanel goToLeftPanel;
    private JPanel goToRightPanel;
    private JPanel contentPanel;


    public RobotsTodayController(){
        
    }

    @Override
    public JPanel getContentPanel() {
        return contentPanel;
    }

    @Override
    public String getTitle() {
        return "Robots Today";
    }
}
