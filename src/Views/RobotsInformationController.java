package Views;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class RobotsInformationController implements IController {
    private JPanel backPanel;
    private JPanel goToLeftPanel;
    private JPanel goToRightPanel;
    private JPanel contentPanel;
    private JPanel mainPanel;
    private JTextPane textArea1;
    private JLabel imageLabel;
    private IController a;
    private int id = 0;
    private String location;
    private final String textFile[] = {"01.txt",
            "02.txt",
            "03.txt",
            "04.txt"};
    private final String imageFile[] = {"01.JPG",
            "02.JPG",
            "03.JPG",
            "04.JPG"};

    public RobotsInformationController(String location) {
        this.location = location;
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
            imageLabel.setIcon(new ImageIcon(location+imageFile[id]));
            BufferedReader br = new BufferedReader(new FileReader(location+textFile[id]));
            String line = br.readLine();
            textArea1.setText("");
            String text = "";
            while(line != null)
            {
                text += line+"\n";
                line = br.readLine();
            }
            textArea1.setText(text);
            StyledDocument doc = textArea1.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

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
        if (location.equals("Resources/History-of-Robotics/"))
            return "History Of Robotics";
        else
            return "Robots today";
    }
}
