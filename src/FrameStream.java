import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FrameStream extends JFrame
{
    JPanel mainFramePanel; // Main panel for the overall layout
    JPanel searchPanel; // Panel for searching a string
    JPanel buttonPanel; // Panel for the buttons
    JPanel textAreaPanel; // Panel for the text areas

    JTextArea fileDisplayArea; // Text area for displaying the file content
    JTextArea resultTextArea; // Text area for the results from the search string entered

    JFileChooser fileChooser; // File chooser for selecting files
    File selectedFile; // The file selected by the user

    JButton chooseFileButton; // Button to choose a file
    JButton searchButton; // Button to search for a string
    JButton exitButton; // Button to exit the program

    JTextField searchTextField; // Text field for entering the search string

    public FrameStream()
    {
        // Set the title and default close operation
        setTitle("Stream File Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the main panel and set its layout
        mainFramePanel = new JPanel();
        mainFramePanel.setLayout(new BoxLayout(mainFramePanel, BoxLayout.Y_AXIS));

        // Initialize the file chooser and set its properties
        fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));

        // Initialize the search panel and add components to it
        searchPanel = new JPanel();
        searchTextField = new JTextField(20);
        searchButton = new JButton("Search");
        //searchButton.addActionListener(e -> searchString());

        searchPanel.add(new JLabel("Enter string to search:"));
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);

        // Initialize the file display area and result text area
        fileDisplayArea = new JTextArea(20, 30);
        resultTextArea = new JTextArea(20, 30);

        // Add the main panel to the frame
        add(mainFramePanel);
        // Initialize the text area panel and button panel
        buttonPanel();
        mainFramePanel.add(searchPanel);
        textAreaPanel();

        // Pack and set the frame visible
        pack();
        setVisible(true);
    }

    public void textAreaPanel()
    {
        // Initialize the text area panel and set its layout
        textAreaPanel = new JPanel();
        textAreaPanel.setLayout(new FlowLayout());

        // Set the text areas to be non-editable
        fileDisplayArea.setEditable(false);
        resultTextArea.setEditable(false);
        JPanel fileDisplayPanel = new JPanel();
        fileDisplayPanel.setLayout(new BorderLayout());
        JLabel fileDisplayLabel = new JLabel("Original File Content:");
        fileDisplayPanel.add(fileDisplayLabel, BorderLayout.NORTH);
        fileDisplayPanel.add(new JScrollPane(fileDisplayArea), BorderLayout.CENTER);
        // Add the file display area and result text area to the panel
        textAreaPanel.add(new JScrollPane(fileDisplayArea));
        textAreaPanel.add(new JScrollPane(resultTextArea));

        // Add the text area panel to the main panel
        mainFramePanel.add(textAreaPanel);
    }

    public void buttonPanel()
    {
        // Initialize the button panel and set its layout
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));


        // Initialize the buttons and add action listeners
        chooseFileButton = new JButton("Choose File");
        chooseFileButton.addActionListener(e -> chooseFile());


        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Add buttons to the button panel
        buttonPanel.add(chooseFileButton);
        buttonPanel.add(Box.createHorizontalStrut(10)); // Add spacing between buttons
        buttonPanel.add(exitButton);

        // Add the button panel to the main panel
        mainFramePanel.add(buttonPanel);
    }

    public void chooseFile()
    {
        // Show the file chooser dialog and get the selected file
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = fileChooser.getSelectedFile();
            // Load the file content into the text area
            loadFileContent(selectedFile, fileDisplayArea);
        }
    }

    private void loadFileContent(File selectedFile, JTextArea fileDisplayArea)
    {
        // Clear the text area before loading new content
        fileDisplayArea.setText("");
        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                fileDisplayArea.append(line + "\n");
            }
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(this, "Error loading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
