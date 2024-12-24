package dictionary.dictionarypro;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.*;

/**
 * This is a public Controller class is the controller of the dictionary UI.
 */

public class Controller implements Initializable {

    /**
     * This is a private TextField searchBox property represents the searchBox.
     */

    @FXML
    private TextField searchBox;

    /**
     * This is a private ListView<String> searchResult property represents the result of a search.
     */

    @FXML
    private ListView<String> searchResult;

    /**
     * This is a private Label wordTargetLabel property represents the word Target Label.
     */

    @FXML
    private Label wordTargetLabel;

    /**
     * This is a private Label wordTypeLabel property represents the word Type Label.
     */

    @FXML
    private Label wordTypeLabel;

    /**
     * This is a private Label wordPronunciationLabel represents the word Pronunciation Label.
     */

    @FXML
    private Label wordPronunciationLabel;

    /**
     * This is a private Label wordExplainLabel represents the word Explain Label.
     */

    @FXML
    private Label wordExplainLabel;

    /**
     * This is a private DictionaryManagement dic property represents the dictionary to manage
     * and operate with.
     */

    private DictionaryManagement dic = new DictionaryManagement();

    /**
     * This is a private Word selectedWord property represents the selected word in UI.
     */

    private Word selectedWord;

    /**
     * This is a private MediaView mediaView property to play the intro video.
     */

    @FXML
    private MediaView mediaView;

    /**
     * This is a private ObservableList<String> observableList property to store all the wordTarget
     * of the dictionary to display it in searchResult.
     */

    private ObservableList<String> observableList = FXCollections.observableArrayList();

    /**
     * This is a private ChoiceBox<String> themeChoiceBox to choose the theme in UI.
     */

    @FXML
    private ChoiceBox<String> themeChoiceBox;

    /**
     * This is a private saveButton Button to save all the change into file in UI.
     */

    @FXML
    private Button saveButton;

    /**
     * This is a private editButton Button to edit a word in UI.
     */

    @FXML
    private Button editButton;

    /**
     * This is a private removeButton Button to remove a word in UI.
     */

    @FXML
    private Button removeButton;

    /**
     * This is a private speakButton Button to use pronounce a word by sound in UI.
     */

    @FXML
    private Button speakButton;

    /**
     * This is a public void initialize method to start the program with video and initialize the
     * searchResult and the choiceBox.
     * @param location is the URL location
     * @param resources is the resources
     */

    public void initialize(URL location, ResourceBundle resources) {
        Media media = new Media(this.getClass().getResource("Dic.mp4").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaView.setVisible(false);
        });
        mediaPlayer.play();
        for (int i = 0; i < Dictionary.numberOfWords(); i++) {
            observableList.add(Dictionary.at(i).getWordTarget());
        }
        searchResult.setItems(observableList);
        themeChoiceBox.getItems().add("Blue");
        themeChoiceBox.getItems().add("Beach Towels");
        themeChoiceBox.getItems().add("Light Pink");
        themeChoiceBox.getItems().add("Moonlight Bytes");
        themeChoiceBox.getItems().add("Space Gray");
        themeChoiceBox.getSelectionModel().selectFirst();
        themeChoiceBox.setOnAction(event -> {
            switch (themeChoiceBox.getSelectionModel().getSelectedIndex()) {
                case 0:
                    themeChoiceBox.getScene().getStylesheets().clear();
                    themeChoiceBox.getScene().getStylesheets().add(this.getClass().getResource(
                            "style.css").toExternalForm());
                    break;
                case 1:
                    themeChoiceBox.getScene().getStylesheets().clear();
                    themeChoiceBox.getScene().getStylesheets().add(this.getClass().getResource(
                            "style2.css").toExternalForm());
                    break;
                case 2:
                    themeChoiceBox.getScene().getStylesheets().clear();
                    themeChoiceBox.getScene().getStylesheets().add(this.getClass().getResource(
                            "style3.css").toExternalForm());
                    break;
                case 3:
                    themeChoiceBox.getScene().getStylesheets().clear();
                    themeChoiceBox.getScene().getStylesheets().add(this.getClass().getResource(
                            "style4.css").toExternalForm());
                    break;
                case 4:
                    themeChoiceBox.getScene().getStylesheets().clear();
                    themeChoiceBox.getScene().getStylesheets().add(this.getClass().getResource(
                            "style5.css").toExternalForm());
            }
        });
        saveButton.setDisable(true);
        editButton.setDisable(true);
        removeButton.setDisable(true);
        speakButton.setDisable(true);
        Platform.runLater(() -> {
            mediaView.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
                    exit.setTitle("Exit Confirm");
                    exit.setHeaderText("Are you sure want to exit the dictionary program?");
                    Stage exitStage = (Stage) exit.getDialogPane().getScene().getWindow();
                    exitStage.getIcons().add(new Image(Controller.class.getResourceAsStream(
                            "exit.png")));
                    exit.getDialogPane().getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> confirm = exit.showAndWait();
                    if (confirm.get() == ButtonType.YES) {
                        if (!saveButton.isDisable()) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Save to file");
                            alert.setHeaderText("Do you want to save all the changes into file?");
                            alert.getDialogPane().getButtonTypes().setAll(ButtonType.YES
                                    , ButtonType.NO);
                            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image(Controller.class.getResourceAsStream(
                                    "save.png")));
                            Optional<ButtonType> choose = alert.showAndWait();
                            if (choose.get() == ButtonType.YES) {
                                dic.dictionaryExportToFile();
                            }
                        }
                    } else {
                        windowEvent.consume();
                    }
                }
            });
        });
    }

    /**
     * This is a public void searchWord method to search for a word in UI.
     * @param e is the key pressed
     */

    public void searchWord(KeyEvent e) {
        ObservableList<String> result = FXCollections.observableArrayList();
        String target = searchBox.getText().toLowerCase();
        if (e.getCode().isLetterKey()) {
            target += e.getText();
        }
        if (!target.equals("")) {
            int r = Dictionary.numberOfWords() - 1;
            int l = 0;
            while (r >= l) {
                int mid = l + (r - l) / 2;
                String s = Dictionary.at(mid).getWordTarget();
                if (s.length() >= target.length()) {
                    if (s.startsWith(target)) {
                        if (mid == 0 || !Dictionary.at(mid - 1).getWordTarget()
                                .startsWith(target)) {
                            result.add(s);
                            for (int i = mid + 1; i < Dictionary.numberOfWords() && Dictionary
                                    .at(i).getWordTarget().indexOf(target) == 0; i++) {
                                result.add(Dictionary.at(i).getWordTarget());
                            }
                            break;
                        } else {
                            r = mid - 1;
                        }
                    } else if (s.substring(0, target.length()).compareTo(target) > 0) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                } else {
                    if (s.compareTo(target.substring(0, s.length())) > 0) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                }
            }
            searchResult.setItems(result);
        } else {
            searchResult.setItems(observableList);
        }
//        } else {
//            foundWords.clear();
//            result = FXCollections.observableArrayList(foundWords);
//            searchResult.setItems(result);
//        }
    }

    /**
     * This is a public void viewDetail method to view detail of a word.
     * @param e is the mouse event on a word
     */

    public void viewDetail(MouseEvent e) {
        String selectedString = searchResult.getSelectionModel().getSelectedItem();
        if (selectedString != null) {
            selectedWord = Dictionary.at(Dictionary.contains(selectedString));
            wordTargetLabel.setText(' ' + selectedWord.getWordTarget());
            wordTypeLabel.setText(' ' + selectedWord.getWordType());
            wordPronunciationLabel.setText(' ' + selectedWord.getPronunciation());
            wordExplainLabel.setText(' ' + selectedWord.getWordExplain());
            if (editButton.isDisable()) {
                editButton.setDisable(false);
            }
            if (removeButton.isDisable()) {
                removeButton.setDisable(false);
            }
            if (speakButton.isDisable()) {
                speakButton.setDisable(false);
            }
        }
    }

    /**
     * This is a public void speak method to pronounce a word by sound.
     */

    public void speak() {
        Sound.speak(wordTargetLabel.getText());
    }

    /**
     * This is a public void ignore method to clear the word being displayed in UI.
     */

    public void ignore() {
        if(selectedWord != null) {
            wordTargetLabel.setText(null);
            wordTypeLabel.setText(null);
            wordPronunciationLabel.setText(null);
            wordExplainLabel.setText(null);
            selectedWord = null;
            searchResult.getSelectionModel().clearSelection();
            editButton.setDisable(true);
            removeButton.setDisable(true);
            speakButton.setDisable(true);
        }
    }

    /**
     * This is a method to add the wordTarget into the observableList.
     * @param s is the wordTarget
     */

    public void observableListAdd(String s) {
        boolean b = false;
        int n = observableList.size();
        observableList.add("");
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (observableList.get(mid).compareTo(s) > 0) {
                if (mid == 0 || observableList.get(mid - 1).compareTo(s) <= 0) {
                    for (int j = n; j >= mid + 1; j--) {
                        observableList.set(j, observableList.get(j - 1));
                    }
                    observableList.set(mid, s);
                    b = true;
                    break;
                } else {
                    r = mid - 1;
                }
            } else {
                l = mid + 1;
            }
        }
        if (!b) observableList.set(n, s);
    }

    /**
     * This is the method to add the wordTarget into the searchResult.
     * @param s is the wordTarget
     */

    public void searchResultAdd(String s) {
        int n = searchResult.getItems().size();
        searchResult.getItems().add("");
        boolean b = false;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (searchResult.getItems().get(mid).compareTo(s) > 0) {
                if (mid == 0 || searchResult.getItems().get(mid - 1).compareTo(s) <= 0) {
                    for (int j = n; j >= mid + 1; j--) {
                        searchResult.getItems().set(j, observableList.get(j - 1));
                    }
                    searchResult.getItems().set(mid, s);
                    b = true;
                    break;
                } else {
                    r = mid - 1;
                }
            } else {
                l = mid + 1;
            }
        }
        if (!b) searchResult.getItems().set(n, s);
    }

    /**
     * This is a public void add method to add a word from UI.
     */

    public void add() {
        Dialog<Word> addNew = new Dialog<>();
        addNew.setTitle("Word Inserter");
        addNew.setHeaderText("Please enter the word you want to insert:");
        ButtonType confirm = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        addNew.getDialogPane().getButtonTypes().addAll(confirm, ButtonType.CANCEL);
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(20, 150, 10, 10));
        TextField newWordTarget = new TextField();
        newWordTarget.setPromptText("Word target");
        TextField newWordType = new TextField();
        newWordType.setPromptText("Word type");
        TextField newPronunciation = new TextField();
        newPronunciation.setPromptText("Pronunciation");
        TextField newWordExplain = new TextField();
        newWordExplain.setPromptText("Word Explain");
        pane.add(new Label("Target:"), 0, 0);
        pane.add(new Label("Type:"), 0, 1);
        pane.add(new Label("Pronunciation:"), 0, 2);
        pane.add(new Label("Explain:"), 0, 3);
        pane.add(newWordTarget, 1, 0);
        pane.add(newWordType, 1, 1);
        pane.add(newPronunciation, 1, 2);
        pane.add(newWordExplain, 1, 3);
        Node addButton = addNew.getDialogPane().lookupButton(confirm);
        addButton.setDisable(true);
        newWordTarget.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newWordType.getText().isEmpty() && !newPronunciation.getText().isEmpty() &&
                    !newWordExplain.getText().isEmpty()) {
                if (!newValue.isEmpty()) {
                    if (addButton.isDisable()) {
                        addButton.setDisable(false);
                    }
                }
                else {
                    addButton.setDisable(true);
                }
            }
        });
        newWordType.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newWordTarget.getText().isEmpty() && !newPronunciation.getText().isEmpty() &&
                    !newWordExplain.getText().isEmpty()) {
                if (!newValue.isEmpty()) {
                    if (addButton.isDisable()) {
                        addButton.setDisable(false);
                    }
                }
                else {
                    addButton.setDisable(true);
                }
            }
        });
        newPronunciation.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newWordType.getText().isEmpty() && !newWordTarget.getText().isEmpty() &&
                    !newWordExplain.getText().isEmpty()) {
                if (!newValue.isEmpty()) {
                    if (addButton.isDisable()) {
                        addButton.setDisable(false);
                    }
                }
                else {
                    addButton.setDisable(true);
                }
            }
        });
        newWordExplain.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newWordType.getText().isEmpty() && !newPronunciation.getText().isEmpty() &&
                    !newWordTarget.getText().isEmpty()) {
                if (!newValue.isEmpty()) {
                    if (addButton.isDisable()) {
                        addButton.setDisable(false);
                    }
                }
                else {
                    addButton.setDisable(true);
                }
            }
        });
        addNew.getDialogPane().setContent(pane);
        addNew.setResultConverter(dialogButton -> {
            if (dialogButton == confirm) {
                String s = newWordTarget.getText();
                if (Dictionary.contains(s) == -1) {
                    dic.addWord(s, newWordType.getText(), newPronunciation.getText()
                            , newWordExplain.getText());
                    if (observableList != searchResult.getItems()) {
                        observableListAdd(s);
                    }
                    if (s.startsWith(searchBox.getText())) {
                        searchResultAdd(s);
                    };
                    if (saveButton.isDisable()) {
                        saveButton.setDisable(false);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Adding Error");
                    alert.setHeaderText("The word you want to add already exists!");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(Controller.class.getResourceAsStream(
                            "add.png")));
                    alert.show();
                }
                return new Word(s, newWordType.getText()
                        , newPronunciation.getText(), newWordExplain.getText());
            }
            return null;
        });
        Stage stage = (Stage) addNew.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Controller.class.getResourceAsStream("add.png")));
        addNew.show();
    }

    /**
     * This is a public void change method to change a word in UI.
     */

    public void change() {
        if (selectedWord != null) {
            Dialog<Word> changeAWord = new Dialog<>();
            changeAWord.setTitle("Word Editor");
            changeAWord.setHeaderText("Please edit the word by modifying it in the text box:");
            ButtonType confirm = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            changeAWord.getDialogPane().getButtonTypes().addAll(confirm, ButtonType.CANCEL);
            GridPane pane = new GridPane();
            pane.setHgap(10);
            pane.setVgap(10);
            pane.setPadding(new Insets(20, 150, 10, 10));
            TextField newWordTarget = new TextField();
            newWordTarget.setPromptText("Word target");
            newWordTarget.setText(selectedWord.getWordTarget());
            TextField newWordType = new TextField();
            newWordType.setPromptText("Word type");
            newWordType.setText(selectedWord.getWordType());
            TextField newPronunciation = new TextField();
            newPronunciation.setPromptText("Pronunciation");
            newPronunciation.setText(selectedWord.getPronunciation());
            TextField newWordExplain = new TextField();
            newWordExplain.setPromptText("Word Explain");
            newWordExplain.setText(selectedWord.getWordExplain());
            pane.add(new Label("Target:"), 0, 0);
            pane.add(new Label("Type:"), 0, 1);
            pane.add(new Label("Pronunciation:"), 0, 2);
            pane.add(new Label("Explain:"), 0, 3);
            pane.add(newWordTarget, 1, 0);
            pane.add(newWordType, 1, 1);
            pane.add(newPronunciation, 1, 2);
            pane.add(newWordExplain, 1, 3);
            Node changeButton = changeAWord.getDialogPane().lookupButton(confirm);
            changeButton.setDisable(false);
            newWordTarget.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.trim().isEmpty()) {
                    changeButton.setDisable(true);
                } else if (changeButton.isDisable()) {
                    changeButton.setDisable(false);
                }
            });
            newWordType.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.trim().isEmpty()) {
                    changeButton.setDisable(true);
                } else if (changeButton.isDisable()) {
                    changeButton.setDisable(false);
                }
            });
            newPronunciation.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.trim().isEmpty()) {
                    changeButton.setDisable(true);
                } else if (changeButton.isDisable()) {
                    changeButton.setDisable(false);
                }
            });
            newWordExplain.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.trim().isEmpty()) {
                    changeButton.setDisable(true);
                } else if (changeButton.isDisable()) {
                    changeButton.setDisable(false);
                }
            });
            changeAWord.getDialogPane().setContent(pane);
            changeAWord.setResultConverter(dialogButton -> {
                if (dialogButton == confirm) {
                    String s = selectedWord.getWordTarget();
                    String s1 = newWordTarget.getText();
                    dic.editWord(s, s1, newWordType.getText(), newPronunciation.getText()
                            , newWordExplain.getText());
                    if (observableList != searchResult.getItems()) {
                        observableList.remove(s);
                        observableListAdd(s1);
                    }
                    searchResult.getItems().remove(s);
                    if (s1.startsWith(searchBox.getText())) {
                        searchResultAdd(s1);
                        searchResult.getSelectionModel().select(s1);
                    }
//                    ignore();
                    wordTargetLabel.setText(' ' + s1);
                    wordTypeLabel.setText("  " + newWordType.getText());
                    wordPronunciationLabel.setText("  " + newPronunciation.getText());
                    wordExplainLabel.setText("  " + newWordExplain.getText());
                    selectedWord = Dictionary.at(Dictionary.contains(s1));
                    if (saveButton.isDisable()) {
                        saveButton.setDisable(false);
                    }
                    return new Word(newWordTarget.getText(), newWordType.getText()
                            , newPronunciation.getText(), newWordExplain.getText());
                }
                return null;
            });
            Stage stage = (Stage) changeAWord.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Controller.class.getResourceAsStream("change.png")));
            changeAWord.show();
        }
    }

    /**
     * This is a method to remove a word from the UI.
     */

    public void remove() {
        if (selectedWord != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Word Remover");
            alert.setHeaderText("Are you sure to remove the word \""
                    + selectedWord.getWordTarget() + "\" from the dictionary?");
            ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Controller.class.getResourceAsStream("remove.png")));
            Optional<ButtonType> choose = alert.showAndWait();
            if (choose.get() == buttonTypeYes) {
                String s = selectedWord.getWordTarget();
                dic.removeWord(s);
                if (observableList != searchResult.getItems()) {
                    observableList.remove(s);
                }
                searchResult.getItems().remove(s);
                if (saveButton.isDisable()) {
                    saveButton.setDisable(false);
                }
                ignore();
            }
        }
    }

    /**
     * This is a public void usingAPI method to translate a word or a sentence from English
     * to Vietnamese using Google Translate API.
     */

    public void usingAPI() throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("TranslatorUsingAPI2.fxml"));
        Scene scene = new Scene(root);
        switch (themeChoiceBox.getSelectionModel().getSelectedIndex()) {
            case 0:
                scene.getStylesheets().add(this.getClass().getResource("translateStyle.css")
                        .toExternalForm());
                break;
            case 1:
                scene.getStylesheets().add(this.getClass().getResource("translateStyle2.css")
                        .toExternalForm());
                break;
            case 2:
                scene.getStylesheets().add(this.getClass().getResource("translateStyle3.css")
                        .toExternalForm());
                break;
            case 3:
                scene.getStylesheets().add(this.getClass().getResource("translateStyle4.css")
                        .toExternalForm());
                break;
            case 4:
                scene.getStylesheets().add(this.getClass().getResource("translateStyle5.css")
                        .toExternalForm());
        }
        Stage stage = new Stage();
        stage.setTitle("Translator");
        stage.setScene(scene);
        stage.getIcons().add(new Image(Controller.class.getResourceAsStream(
                "1419095_google_translate_translator_icon.png")));
        stage.show();
    }

    /**
     * This is a public void save method to save all current changes being made in the UI to the
     * dictionary file.
     */

    public void save() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save to file");
        alert.setHeaderText("Do you want to save all the changes into file?");
        alert.getDialogPane().getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Controller.class.getResourceAsStream("save.png")));
        Optional<ButtonType> choose = alert.showAndWait();
        if (choose.get() == ButtonType.YES) {
            dic.dictionaryExportToFile();
            Alert done = new Alert(Alert.AlertType.INFORMATION);
            done.setTitle("Save successfully");
            done.setHeaderText("Ok! All the changes have been saved into file!");
            done.show();
            saveButton.setDisable(true);
        }
    }
}
