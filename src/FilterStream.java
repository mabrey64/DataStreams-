import javax.swing.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * FilterStream is a utility class that provides functionality to filter lines
 * from a selected file based on a search string. This is where the main logic of the project resides.
 */
public class FilterStream
{
    File seltectFile;
    String filter;
    JTextField searchTextField;

    // Constructor to initialize the FilterStream with a selected file and search text
    public FilterStream(File seltectFile, FrameStream frame, String searchText)
    {
        this.seltectFile = new File(seltectFile.getAbsolutePath());
        frame.setSearchTextField(searchText);
        this.searchTextField = frame.getSearchTextField();
        this.filter = searchTextField.getText();
    }

    public void filteringFile(FrameStream frame) {
        // Clear the result text area before displaying new results
        frame.resultTextArea.setText("");
        // Check if the selected file is valid, if not, return. If so, display the file content
        try (Stream<String> lines = Files.lines(Paths.get(seltectFile.getAbsolutePath()))) {
            lines.filter(line -> line.contains(filter))
                 .forEach(line -> frame.resultTextArea.append(line + "\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
