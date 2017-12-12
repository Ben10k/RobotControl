package Views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class RobotsHistoryController implements IController {
    private JPanel backPanel;
    private JPanel goToLeftPanel;
    private JPanel goToRightPanel;
    private JPanel contentPanel;
    private JPanel mainPanel;
    private JTextArea textArea1;
    private JLabel imageLabel;
    private IController a;
    private int id = 0;
    private final String textFile[] = {"Resources/History-of-Robotics/01.txt",
            "Resources/History-of-Robotics/02.txt",
            "Resources/History-of-Robotics/03.txt",
            "Resources/History-of-Robotics/04.txt"};
    private final String imageFile[] = {"Resources/History-of-Robotics/01.JPG",
            "Resources/History-of-Robotics/02.JPG",
            "Resources/History-of-Robotics/03.JPG",
            "Resources/History-of-Robotics/04.JPG"};

    public RobotsHistoryController() {
        backPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("back");
                a = new MainMenuController();
                GUIController.getInstance().setContent(a.getContentPanel(), a.getTitle());
            }
        });
        goToRightPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("goToRightPanel");
                if(id == 3) id = 0;
                else id++;
                loadResources(id);

            }
        });
        goToLeftPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("goToLeftPanel");
                if(id == 0) id = 3;
                else id--;
                loadResources(id);

            }
        });


        loadResources(id);


    }

    private void loadResources(int id) {
        try {
            imageLabel.setIcon(new ImageIcon(imageFile[id]));
            BufferedReader br = new BufferedReader(new FileReader(textFile[id]));
            String line = br.readLine();
            textArea1.setText("");
            while(line != null)
            {
                textArea1.append(line+"\n");
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public JPanel getContentPanel() {
        return contentPanel;
    }

    @Override
    public String getTitle() {
        return "History Of Robotics";
    }
}
