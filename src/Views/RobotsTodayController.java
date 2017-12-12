package Views;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;

public class RobotsTodayController implements IController {
    private JPanel backPanel;
    private JPanel mainPanel;
    private JPanel goToLeftPanel;
    private JPanel goToRightPanel;
    private JPanel contentPanel;
    private JLabel imageLabel;
    private JTextArea textArea1;
    private IController a;

    private int id = 0;
    private final String textFile[] = {"Resources/Robots-Today/01.txt",
            "Resources/Robots-Today/02.txt",
            "Resources/Robots-Today/03.txt",
            "Resources/Robots-Today/04.txt"};
    private final String imageFile[] = {"Resources/Robots-Today/01.JPG",
            "Resources/Robots-Today/02.JPG",
            "Resources/Robots-Today/03.JPG",
            "Resources/Robots-Today/04.JPG"};

    public RobotsTodayController() {
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
        return "Robots Today";
    }
}
