package cvdashboard;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class CvInternalFrame extends JInternalFrame {
    private static final String[] LANGUAGES = {
            "Francais", "Anglais", "Arabe", "Espagnol", "Allemand",
            "Korean", "Japonais", "Esperanto", "Thailandais", "Mandarin", "Portugais"
    };

    private final JTextField tfNom;
    private final JTextField tfPrenom;
    private final JList<String> languageList;
    private final JRadioButton rdMale;
    private final JRadioButton rdFemale;

    public CvInternalFrame() {
        super("CV", true, true, true, true);

        setSize(720, 460);
        setLocation(30, 30);
        setLayout(new BorderLayout(12, 12));

        JLabel title = new JLabel("Curriculum Vitae", JLabel.CENTER);
        title.setOpaque(true);
        title.setBackground(new Color(39, 93, 173));
        title.setForeground(Color.WHITE);
        title.setFont(new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 28));
        title.setPreferredSize(new Dimension(720, 58));
        add(title, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        tfNom = new JTextField(24);
        tfPrenom = new JTextField(24);

        content.add(buildFieldRow("Nom:", tfNom));
        content.add(Box.createVerticalStrut(12));
        content.add(buildFieldRow("Prenom:", tfPrenom));
        content.add(Box.createVerticalStrut(12));

        JLabel languageLabel = new JLabel("Choisir les langues que vous parlez:");
        languageLabel.setAlignmentX(LEFT_ALIGNMENT);
        content.add(languageLabel);
        content.add(Box.createVerticalStrut(6));

        languageList = new JList<>(LANGUAGES);
        languageList.setVisibleRowCount(6);
        languageList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(languageList);
        scrollPane.setPreferredSize(new Dimension(260, 120));
        scrollPane.setAlignmentX(LEFT_ALIGNMENT);
        content.add(scrollPane);
        content.add(Box.createVerticalStrut(12));

        rdMale = new JRadioButton("Male");
        rdFemale = new JRadioButton("Female");
        ButtonGroup sexGroup = new ButtonGroup();
        sexGroup.add(rdMale);
        sexGroup.add(rdFemale);

        JPanel sexPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        sexPanel.setAlignmentX(LEFT_ALIGNMENT);
        sexPanel.add(new JLabel("Sex:"));
        sexPanel.add(rdMale);
        sexPanel.add(rdFemale);
        content.add(sexPanel);

        add(content, BorderLayout.CENTER);
        add(buildButtons(), BorderLayout.SOUTH);
    }

    private JPanel buildFieldRow(String label, JTextField field) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        row.setAlignmentX(LEFT_ALIGNMENT);
        row.add(new JLabel(label));
        row.add(field);
        return row;
    }

    private JPanel buildButtons() {
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 12));

        JButton previewButton = new JButton("Valider");
        previewButton.addActionListener(e -> previewCv());

        JButton exportButton = new JButton("Exporter");
        exportButton.addActionListener(e -> exportCv());

        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> dispose());

        buttons.add(previewButton);
        buttons.add(exportButton);
        buttons.add(closeButton);
        return buttons;
    }

    private void previewCv() {
        String content = buildCvContent();
        JOptionPane.showMessageDialog(
                this,
                content,
                "Apercu du CV",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void exportCv() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Exporter le CV");
        chooser.setSelectedFile(new java.io.File("CV.txt"));

        int result = chooser.showSaveDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        Path outputPath = chooser.getSelectedFile().toPath();
        try {
            Files.writeString(outputPath, buildCvContent(), StandardCharsets.UTF_8);
            JOptionPane.showMessageDialog(
                    this,
                    "CV exporte vers:\n" + outputPath,
                    "Export reussi",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erreur lors de l'export:\n" + e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private String buildCvContent() {
        String nom = tfNom.getText().trim();
        String prenom = tfPrenom.getText().trim();
        String sex = rdMale.isSelected() ? "Male" : rdFemale.isSelected() ? "Female" : "Non renseigne";
        java.util.List<String> selectedLanguages = languageList.getSelectedValuesList();
        String languages = selectedLanguages.isEmpty() ? "Aucune" : String.join(", ", selectedLanguages);

        return "CV\n\n"
                + "Nom: " + (nom.isEmpty() ? "-" : nom) + "\n"
                + "Prenom: " + (prenom.isEmpty() ? "-" : prenom) + "\n"
                + "Sex: " + sex + "\n"
                + "Langues: " + languages + "\n";
    }
}
