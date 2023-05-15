//==========================
//DO NOT REMOVE GIVEN CODE!
//==========================
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.*;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.util.Random;
import javax.swing.JOptionPane;
//==========================
//DO NOT REMOVE GIVEN CODE!
//==========================
public class rpsGUI extends Application
{
    private Button btnRock, btnPaper, btnScissors, btnPlayAgain;
    private Scene scenePlay, sceneResults;
    private Label lblWelcome, lblRules, lblStart;
    private TextArea displayArea;
    
    private int playerWins = 0;
    private int computerWins = 0;
    private int draws = 0;

    private String playerChoice = "";
    @Override
    public void start(Stage stage) throws Exception
    {
        //set text and font for labels
        lblWelcome = new Label("Welcome to Rock Paper Scissors");
        lblWelcome.setFont(Font.font("",FontWeight.BOLD, FontPosture.ITALIC, 20));
        
        lblRules = new Label("Remember that Rock beats Scissors, Paper beats Rock, and Scissors beat Paper!");
        
        lblStart = new Label("Please make your selection below:");
        lblStart.setFont(Font.font("", FontWeight.BOLD, FontPosture.ITALIC, 14));
        
        //create and add images
        Image imgRock = new Image("rock.png");
        Image imgPaper = new Image("paper.png");
        Image imgScissor = new Image ("scissors.png");

        //create buttons and set image
        btnRock = new Button("  Rock  ");
        btnRock.setGraphic(new ImageView(imgRock));

        btnPaper = new Button("  Paper  ");
        btnPaper.setGraphic(new ImageView(imgPaper));

        btnScissors = new Button("  Scissors  ");
        btnScissors.setGraphic(new ImageView(imgScissor));
        
        // set TextArea and styling
        displayArea = new TextArea();
        displayArea.setId("displayArea");

        //set VBox for labels
        VBox vbox = new VBox();
        vbox.getChildren().addAll(lblWelcome, lblRules, lblStart);
        vbox.setAlignment(Pos.CENTER);

        //set HBox for buttons
        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(btnRock, btnPaper, btnScissors);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.setPadding(new Insets(5,5,5,5));
        hbox1.setSpacing(15);

        //set VBox for main layout
        VBox vbMain = new VBox(5);
        vbMain.getChildren().addAll(vbox, hbox1, displayArea);
        vbMain.setAlignment(Pos.CENTER);
        vbMain.setPadding(new Insets(15,15,15,15));

        //set borderpane for main layout
        BorderPane paneBorder = new BorderPane();
        paneBorder.setCenter(vbMain);

        //set both scenes (play and result)
        scenePlay = new Scene(paneBorder, 600, 220);
        scenePlay.getStylesheets().add("styles.css");
        //====================================================================================================
        //Activate button event handlers below
        //====================================================================================================
        btnRock.setOnAction(e -> handleRockBtn());
        btnPaper.setOnAction(e -> handlePaperBtn());
        btnScissors.setOnAction(e -> handleScissorsBtn());
        
        
        
        
        //====================================================================================================
        //Activate button event handlers above
        //====================================================================================================
        
        //set stage title and show the window
        stage.setTitle("Rock - Paper - Scissors");
        stage.setScene(scenePlay);
        stage.show();
    }
    //==========================
    //DO NOT REMOVE GIVEN CODE!
    //==========================
    //===========================================================================================================
    //Add Event handlers for buttons below
    //===========================================================================================================
    
    private String handleRockBtn() {
       playerChoice = "Rock";
       checkWinner(playerChoice);
       return playerChoice;
    }

    private String handlePaperBtn() {
        playerChoice = "Paper";
        checkWinner(playerChoice);
        return playerChoice;
    }
    
    private String handleScissorsBtn() {
        playerChoice = "Scissors";
        checkWinner(playerChoice);
        return playerChoice;
    }
    
    
    //===========================================================================================================
    //Add Event handlers for buttons above
    //===========================================================================================================
    //===========================================================================================================
    //Add determineCompSelection() method below
    //===========================================================================================================
    private String determineCompSelection() {
        Random rand = new Random();
        int randNum = rand.nextInt(3) + 1;

        switch (randNum) {
            case 1:
                return "Rock";
            case 2:
                return "Paper";
            case 3:
                return "Scissors";
            default:
                return "Invalid selection";
        }
    }
    
    //===========================================================================================================
    //Add determineCompSelection() method above
    //===========================================================================================================
    //===========================================================================================================
    //Add checkWinner() method below
    //===========================================================================================================
    
    private void checkWinner(String playerChoice) {
        String computerChoice = determineCompSelection();
        String result = "";
    
        if (playerChoice.equals(computerChoice)) {
            result = "The game was a tie! Please try again!";
            draws++;
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            result = "Congratulations. you win!";
            
            playerWins++;
        } else {
            result = "The computer wins. Better luck next time!";
            computerWins++;
        }
    
        displayResults(computerChoice, result);
   }
    
    
    //===========================================================================================================
    //Add checkWinner() method above
    //===========================================================================================================
    //===========================================================================================================
    //Add displayResults() method below
    //===========================================================================================================
    

    private void displayResults(String computerChoice, String result) {
    computerChoice = determineCompSelection();
    String displayMessage= String.format("You picked %s  and computer picked %s",playerChoice,computerChoice);
    
    String display = String.format("Player %d - %d Computer \t Draws - %d",playerWins,computerWins,draws);
    displayArea.setText(display);

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Game Result");
    alert.setHeaderText(displayMessage);
    alert.setContentText(result);
    alert.showAndWait();
  }
    
    //===========================================================================================================
    //Add displayResults() method below
    //===========================================================================================================
}
