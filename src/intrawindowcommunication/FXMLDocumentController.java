/*
 * The MIT License
 *
 * Copyright 2021 gfoster.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package intrawindowcommunication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author gfoster
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Label label;
    @FXML private TextField text;
    

    @FXML private void showDialog(ActionEvent event) {
        // Code that will display the dialog
        event.consume();
        
        // Loads the scene from the fxml file
        Scene newScene;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dialog.fxml"));
        try {
            newScene = new Scene(loader.load());
        } catch (IOException e) {
            System.out.println("Fail");
            System.out.println(e);
            return;
        }
        
        // Create the stage
        Stage inputStage = new Stage();
        // Sets the owner to being this window NOTE primaryStage is set up in IntraWindowCommunication
        inputStage.initOwner(IntraWindowCommunication.primaryStage);
        // Add the Scene to the stage
        inputStage.setScene(newScene);
        
        // Get the dialog controller so that a public method can be run to send date to the dialog
        DialogController dc = loader.<DialogController>getController();
        dc.showMessage(text.getText());
        
        // Show the dialog (and wait for the user to close it)
        inputStage.showAndWait();
        // use the dialog controller to cal a public method to get data from it
        label.setText(dc.getMessage());
    } // end of method showDialog
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Exampel of a on change listener.
        text.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue,
                                String newValue)
            {
                label.setText(text.getText());
            }
        });
    }    
    
}
