package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Collections;

/**
 * A custom control using FXML that represents a dialog box consisting of an
 * ImageView and a Label. The ImageView represents the speaker's face and the
 * Label contains text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a dialog box with a Label on the left and ImageView on the right.
     *
     * @param text String of text to be added.
     * @param img Image to be displayed.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and the Label
     * is on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> childNodes =
                FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(childNodes);
        this.getChildren().setAll(childNodes);
    }

    /**
     * Returns a new duke.DialogBox with the given Label on the left and the
     * given ImageView on the right.
     *
     * @param text String containing text.
     * @param image Image containing display picture.
     */
    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    /**
     * Returns a new duke.DialogBox with the given Label on the right and the
     * given ImageView on the left.
     *
     * @param text String containing text.
     * @param image Image containing display picture.
     */
    public static DialogBox getDukeDialog(String text, Image image) {
        var dialogBox = new DialogBox(text, image);
        dialogBox.flip();
        return dialogBox;
    }
}
