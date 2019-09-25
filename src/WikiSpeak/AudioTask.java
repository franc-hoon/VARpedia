package WikiSpeak;

import javafx.concurrent.Task;


public class AudioTask extends Task<String> {

    private String text;
    private String synthesiser;
    private String fileName;

    public AudioTask (String text, String synthesiser, String fileName) {
        this.text = text;
        this.synthesiser = synthesiser;
        this.fileName = fileName;
    }

    @Override
    protected String call() throws Exception {
        String command = "";
        if (synthesiser.equals("Festival")) {
            command = "echo \"" + text + "\" | text2wave -o " + fileName + "_" + synthesiser + ".wav";
            System.out.println(command);
        } else if (synthesiser.equals("eSpeak")) {
            command = "espeak \"" + text + "\" -w " + fileName + "_" + synthesiser + ".wav";
        }

        ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
        try {
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "yes";
    }
}
