package Tasks;

import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class WikitTask extends Task<String> {

    private TextField textSearch;

    public WikitTask (TextField textSearch) {
        this.textSearch = textSearch;
    }

    @Override
    protected String call() throws Exception {
        String output= "";

        /*
        Setup the wikit command and run it
         */
        String command = "wikit " + textSearch.getText();
        ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
        Process process = pb.start();

        BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        /*
        Check if the wikit command was valid or not
         */
        int exitStatus = process.waitFor();
        if (exitStatus == 0) {
            output = stdout.readLine().trim();
            if (output.equals(textSearch.getText() + " not found :^(")) {
                return "ERROR";
            }
        } else {
            String line;
            while ((line = stderr.readLine()) != null) {
                System.err.println(line);
            }
        }
        return output;
    }
}
