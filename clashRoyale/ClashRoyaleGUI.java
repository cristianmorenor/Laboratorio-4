package presentation;

import domain.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * @version ECI 2025
 */
public class ClashRoyaleGUI extends JFrame {

    private static final Dimension PREFERRED_DIMENSION = new Dimension(700, 700);

    private ClashRoyale clash;

    /* List */
    private JButton buttonList;
    private JButton buttonRestartList;
    private JTextArea textDetails;

    /* Add */
    private JTextField name;
    private JTextField type;
    private JTextField elixir;
    private JTextField hitPoints;
    private JTextArea cards;
    private JButton buttonAdd;
    private JButton buttonRestartAdd;

    /* Search */
    private JTextField textSearch;
    private JTextArea textResults;

    private ClashRoyaleGUI() {
        clash = new ClashRoyale();
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        setTitle("Plan 15");
        name = new JTextField(50);
        type = new JTextField(50);
        elixir = new JTextField(50);
        hitPoints = new JTextField(50);
        cards = new JTextArea(10, 50);
        cards.setLineWrap(true);
        cards.setWrapStyleWord(true);

        JTabbedPane etiquetas = new JTabbedPane();
        etiquetas.add("Listar", prepareAreaList());
        etiquetas.add("Adicionar", prepareAreaAdd());
        etiquetas.add("Buscar", prepareSearchArea());
        getContentPane().add(etiquetas);
        setSize(PREFERRED_DIMENSION);

    }

    /**
     * Prepare list area
     * 
     * @return
     */
    private JPanel prepareAreaList() {

        textDetails = new JTextArea(10, 50);
        textDetails.setEditable(false);
        textDetails.setLineWrap(true);
        textDetails.setWrapStyleWord(true);
        JScrollPane scrollArea = new JScrollPane(textDetails,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel botones = new JPanel();
        buttonList = new JButton("Listar");
        buttonRestartList = new JButton("Limpiar");
        botones.add(buttonList);
        botones.add(buttonRestartList);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollArea, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Prepare adiction area
     * 
     * @return adiction area
     */
    private JPanel prepareAreaAdd() {

        JPanel fields = new JPanel(new GridLayout(9, 1));
        fields.add(new JLabel("Nombre"));
        fields.add(name);
        fields.add(new JLabel("Tipo"));
        fields.add(type);
        fields.add(new JLabel("Elixir (solo para cartas)"));
        fields.add(elixir);
        fields.add(new JLabel("Resistencia (solo para cartas)"));
        fields.add(hitPoints);
        fields.add(new JLabel("Cartas (solo para mazos)"));

        JPanel textDetailsPanel = new JPanel();
        textDetailsPanel.setLayout(new BorderLayout());
        textDetailsPanel.add(fields, BorderLayout.NORTH);
        textDetailsPanel.add(cards, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        buttonAdd = new JButton("Adicionar");
        buttonRestartAdd = new JButton("Limpiar");

        botones.add(buttonAdd);
        botones.add(buttonRestartAdd);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(textDetailsPanel, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);
        return panel;
    }

    /**
     * 
     * @return
     */
    private JPanel prepareSearchArea() {

        Box busquedaEtiquetaArea = Box.createHorizontalBox();
        busquedaEtiquetaArea.add(new JLabel("Buscar", JLabel.LEFT));
        busquedaEtiquetaArea.add(Box.createGlue());
        textSearch = new JTextField(50);
        Box busquedaArea = Box.createHorizontalBox();
        busquedaArea.add(busquedaEtiquetaArea);
        busquedaArea.add(textSearch);

        textResults = new JTextArea(10, 50);
        textResults.setEditable(false);
        textResults.setLineWrap(true);
        textResults.setWrapStyleWord(true);
        JScrollPane scrollArea = new JScrollPane(textResults,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel buttonListea = new JPanel();
        buttonListea.setLayout(new BorderLayout());
        buttonListea.add(busquedaArea, BorderLayout.NORTH);
        buttonListea.add(scrollArea, BorderLayout.CENTER);

        return buttonListea;
    }

    private void prepareActions() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                setVisible(false);
                System.exit(0);
            }
        });

        /* List */
        buttonList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                actionList();
            }
        });

        buttonRestartList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textDetails.setText("");
            }
        });

        /* Add */
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                actionAdd();
            }
        });

        buttonRestartAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                name.setText("");
                name.setText("");
                elixir.setText("");
                hitPoints.setText("");
                cards.setText("");
            }
        });

        /* Search */
        textSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent ev) {
                actionSearch();
            }

            public void insertUpdate(DocumentEvent ev) {
                actionSearch();
            }

            public void removeUpdate(DocumentEvent ev) {
                actionSearch();
            }
        });
    }

    private void actionList() {
        textDetails.setText(clash.toString());
    }

    private void actionAdd() {
        if (cards.getText().trim().equals("")) {
            clash.addCard(name.getText(), type.getText(), elixir.getText(), hitPoints.getText());
        } else {
            clash.addDeck(name.getText(), type.getText(), cards.getText());
        }
    }

    private void actionSearch() {
        String patronBusqueda = textSearch.getText();
        String answer = "";
        if (patronBusqueda.length() > 0) {
            answer = clash.search(patronBusqueda);
        }
        textResults.setText(answer);
    }

    public static void main(String args[]) {
        ClashRoyaleGUI gui = new ClashRoyaleGUI();
        gui.setVisible(true);
    }
}
