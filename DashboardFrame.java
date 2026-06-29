package cvdashboard;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import java.awt.BorderLayout;

public class DashboardFrame extends JFrame {
    private final JDesktopPane desktopPane;

    public DashboardFrame() {
        setTitle("Dashboard CV");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        desktopPane = new JDesktopPane();
        add(desktopPane, BorderLayout.CENTER);

        setJMenuBar(buildMenuBar());
        add(buildToolBar(), BorderLayout.NORTH);
    }

    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu cvMenu = new JMenu("CV");

        JMenuItem openCv = new JMenuItem("Open CV Window");
        openCv.addActionListener(e -> openCvWindow());

        JMenuItem about = new JMenuItem("About");
        about.addActionListener(e -> JOptionPane.showMessageDialog(
                this,
                "Clean standalone CV dashboard extracted from the original project.",
                "About",
                JOptionPane.INFORMATION_MESSAGE
        ));

        cvMenu.add(openCv);
        cvMenu.add(about);
        menuBar.add(cvMenu);

        return menuBar;
    }

    private JToolBar buildToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JButton openButton = new JButton("Open CV");
        openButton.addActionListener(e -> openCvWindow());

        toolBar.add(openButton);
        return toolBar;
    }

    private void openCvWindow() {
        CvInternalFrame cvFrame = new CvInternalFrame();
        desktopPane.add(cvFrame);
        cvFrame.setVisible(true);

        try {
            cvFrame.setSelected(true);
        } catch (Exception ignored) {
        }
    }
}
