package WikiSpeak;

import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;


public class WikitTask extends Task<String> {

    private TextArea result;
    private TextField textSearch;

    public WikitTask (TextField textSearch, TextArea result) {
        this.textSearch = textSearch;
        this.result = result;
    }

    @Override
    protected String call() throws Exception {
        String output= "";
        String command = "wikit " + textSearch.getText();
        ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
        Process process = pb.start();

        BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        int exitStatus = process.waitFor();
        if (exitStatus == 0) {
            output = stdout.readLine();
        } else {
            String line;
            while ((line = stderr.readLine()) != null) {
                System.err.println(line);
            }
        }
        return output;
    }
}
