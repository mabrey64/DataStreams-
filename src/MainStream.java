import javax.swing.*;

public class MainStream
{
    public static void main(String[] args)
    {
        // Create an instance of the StreamFrame class to display the GUI
        FrameStream frameStream = new FrameStream();

        // Set the default close operation for the frame
        frameStream.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the frame to be visible
        frameStream.setVisible(true);
    }
}

