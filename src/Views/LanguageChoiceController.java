package Views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class LanguageChoiceController implements IController {
    private JPanel lithuanianPanel;
    private JPanel englishPanel;
    private JPanel russianPanel;
    private JPanel germanPanel;
    private JPanel contentPanel;
    private JPanel backPanel;
    private IController a;


    public LanguageChoiceController(){

//        BufferedImage myPicture = ImageIO.read(new File("path-to-file"));
//        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//        add(picLabel);
//







        backPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("back");
                a = new MainMenuController();
                GUIController.getInstance().setContent(a.getContentPanel(), a.getTitle());
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                backPanel.setBackground(GUIController.getInstance().getOnColor());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                backPanel.setBackground(GUIController.getInstance().getOffColor());

            }
        });
    }



    @Override
    public JPanel getContentPanel() {
        return contentPanel;
    }

    @Override
    public String getTitle() {
        return "Language selection";
    }
}
