import javax.swing.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FilterStream
{
    File seltectFile;
    String filter;
    JTextField searchTextField;

    public FilterStream(File seltectFile, FrameStream frame)
    {
        this.seltectFile = new File(seltectFile.getAbsolutePath());
        this.searchTextField = frame.getSearchTextField();
        this.filter = searchTextField.getText();
    }

    public void filteringFile(FrameStream frame) {
        try (Stream<String> lines = Files.lines(Paths.get(seltectFile.getAbsolutePath()))) {
            lines.filter(line -> line.contains(filter))
                 .forEach(line -> frame.resultTextArea.append(line + "\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
