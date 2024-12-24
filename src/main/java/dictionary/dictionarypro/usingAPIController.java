package dictionary.dictionarypro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is the controller class for the paragraph translator.
 */

public class usingAPIController implements Initializable {

    /**
     * This is a private Button cancelButton property represents the cancel Button in the paragraph
     * translator UI.
     */

    @FXML
    private Button cancelButton;

    /**
     * This is a private Button cancelButton property represents the translate Button in the
     * paragraph translator UI.
     */

    @FXML
    private Button translateButton;

    /**
     * This is the private TextArea translationExplanation represents the explanation text area in
     * the paragraph translator UI.
     */

    @FXML
    private TextArea translationExplanation;

    /**
     * This is the private TextArea translationTarget text area represents the target text area in
     * the paragraph translator UI.
     */

    @FXML
    private TextArea translationTarget;

    /**
     * This is the method to initialize the paragraph translator UI.
     * @param location is the URL
     * @param resources is the resource bundle
     */

    public void initialize(URL location, ResourceBundle resources) {
        translateButton.setDisable(true);
        translationTarget.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.trim().isEmpty()) {
                if (translateButton.isDisable()) {
                    translateButton.setDisable(false);
                }
            } else {
                translateButton.setDisable(true);
            }
        });
    }

    /**
     * This is the translate method handle the translate button action in the paragraph translator
     * UI.
     * @param event is the event to handle
     * @throws IOException is the exception being thrown when there is an IOException
     */

    public void translate(ActionEvent event) throws IOException {
        String target = translationTarget.getText();
        String explanation = "";
        if (target.contains("\n")) {
            String[] parts = target.split("\n");
            for (int i = 0; i < parts.length - 1; i++) {
                if (parts[i].contains("<")) {
                    String[] parts1 = parts[i].split("<");
                    for (int j = 0; j < parts1.length - 1; j++) {
                        explanation += TranslatorUsingAPI.translate(parts1[j]) + '<';
                    }
                    explanation += TranslatorUsingAPI.translate(parts1[parts1.length - 1]);
                } else {
                    explanation += TranslatorUsingAPI.translate(parts[i]);
                }
                explanation += '\n';
            }
            if (parts[parts.length - 1].contains("<")) {
                String[] parts1 = parts[parts.length - 1].split("<");
                for (int j = 0; j < parts1.length - 1; j++) {
                    explanation += TranslatorUsingAPI.translate(parts1[j]) + '<';
                }
                explanation += TranslatorUsingAPI.translate(parts1[parts1.length - 1]);
            } else {
                explanation += TranslatorUsingAPI.translate(parts[parts.length - 1]);
            }
        } else {
            if (target.contains("<")) {
                String[] parts = target.split("<");
                for (int i = 0; i < parts.length - 1; i++) {
                    explanation += TranslatorUsingAPI.translate(parts[i]) + '<';
                }
                explanation += TranslatorUsingAPI.translate(parts[parts.length - 1]);
            } else {
                explanation += TranslatorUsingAPI.translate(target);
            }
        }
        if (explanation.contains("&amp; ")) explanation = explanation.replace("&amp; ", "&");
        if (explanation.contains(" &amp;"))explanation = explanation.replace(" &amp;", "&");
        if (explanation.contains("&#39;")) explanation = explanation.replace("&#39;", "\'");
        if (explanation.contains("&quot;")) explanation = explanation.replace("&quot;", "\"");
        if (explanation.contains("&gt;")) explanation = explanation.replace("&gt;", " >");
        //if (!explanation.equals("NS")) {
        translationExplanation.setText(explanation);
//        } else {
//            translationExplanation.setText(target);
//        }
    }

    /**
     * This is the method to handle event of the cancel Button.
     * @param event is the event to handle
     */

    public void cancel(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
