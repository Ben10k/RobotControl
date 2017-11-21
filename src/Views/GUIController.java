package Views;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUIController {
    private static GUIController ourInstance = new GUIController();

    public static GUIController getInstance() {
        return ourInstance;
    }

    public void setContent(JPanel panel, String title){
        frame.setContentPane(panel);
        frame.setTitle(title);
        frame.pack();
        frame.setVisible(true);
    }
    private GUIController() {
        init();
    }
    private JFrame frame;

    private void init(){


        MainMenuController mmc = new MainMenuController();

        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1024, 768));
        frame.setResizable(false);


        frame.setContentPane(mmc.getContentPanel());

        frame.setTitle(mmc.getTitle());

        frame.pack();
        frame.setVisible(true);


    }
}