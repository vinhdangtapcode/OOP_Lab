package hust.soict.dsai.lab01;
import javax.swing.JOptionPane;

public class test {
    public static void main(String[] args) {
        // Define custom button labels
        Object[] options = {"Yes", "No"};

        // Show option dialog with only "Yes" and "No"
        int option = JOptionPane.showOptionDialog(
            null,
            "Do you want to change to the first class ticket?", // Message
            "Dialog With 2 Options", // Dialog title
            JOptionPane.YES_NO_OPTION, // Limit to Yes/No buttons only
            JOptionPane.QUESTION_MESSAGE, // Type of message (Question icon)
            null, // No custom icon
            options, // Custom button labels
            options[0] // Default button (Yes)
        );

        // Handle the response
        if (option == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "You selected: Yes");
        } else if (option == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "You selected: No");
        }
    }
}
