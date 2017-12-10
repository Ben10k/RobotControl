package Views;

import javax.swing.*;

public class ControlRobotController implements IController {
    private JPanel contentPanel;

    @Override
    public JPanel getContentPanel() {
        return contentPanel;
    }

    @Override
    public String getTitle() {
        return "Kontroliuoti robotą";
    }
}
