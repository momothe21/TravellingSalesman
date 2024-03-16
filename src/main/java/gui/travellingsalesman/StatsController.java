package gui.travellingsalesman;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StatsController {
    //attributes etc
    Stage stage;
    private double p1Score,p2Score,p1Wealth, p2Wealth,p1Power,p2Power;
    private int questCounter;
    private int turn;
    Treasures quest;
    private long startTimeMillis;
    @FXML
    private AnchorPane statsWindow;
    @FXML
    private Label questCounterLabel, questCounterLabel1, p1ScoreLabel,p2ScoreLabel,p1WealthLabel,timerLabel, p2WealthLabel,p1PowerLabel,p2PowerLabel,questLabel, turnLabel;
    @FXML
    private ImageView p1WeaponImage,p2WeaponImage,questImage;

    //method to create a live time
    public void timer(){
        // Create a Timeline object that updates the label every second
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    // Calculate the elapsed time since the application started
                    long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
                    long elapsedSeconds = elapsedTimeMillis / 1000;

                    // Update the label with the elapsed time
                    int hours = (int)(elapsedSeconds / 3600);
                    int minutes = (int)((elapsedSeconds % 3600) / 60);
                    int seconds = (int)((elapsedSeconds % 3600) % 60);

                    String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                    timerLabel.setText("Round Time: " + timeString);

                })
        );

        // Set the animation to repeat indefinitely
        timeline.setCycleCount(Animation.INDEFINITE);

        // Start the timeline
        timeline.play();
    }

    //method to update labels with values
    @FXML
    protected void display(){
        //displaying the labels
        p1PowerLabel.setText(""+getP1Power());
        p2PowerLabel.setText(""+getP2Power());
        p1WealthLabel.setText(""+getP1Wealth());
        p2WealthLabel.setText(""+getP2Wealth());
        p1ScoreLabel.setText(""+getP1Score());
        p2ScoreLabel.setText(""+getP2Score());
        questLabel.setText(getQuest().getName().toString());
        questCounterLabel.setText("Items found: "+questCounter);
        questCounterLabel1.setText("Items not found: "+(8-questCounter));
        if(turn%2==0){
            turnLabel.setText("Player 2");
        }else {
            turnLabel.setText("Player 1");
        }
    }

    //method to switch windows
    @FXML
    protected void switchWindows(ActionEvent event){
        stage.getScene().getWindow().hide();
        GameController.showGame();
    }

    //setters and getters
    public double getP1Score() {
        return p1Score;
    }

    public void setP1Score(double p1Score) {
        this.p1Score = p1Score;
    }

    public double getP2Score() {
        return p2Score;
    }

    public void setP2Score(double p2Score) {
        this.p2Score = p2Score;
    }

    public double getP1Wealth() {
        return p1Wealth;
    }

    public void setP1Wealth(double p1Wealth) {
        this.p1Wealth = p1Wealth;
    }

    public double getP2Wealth() {
        return p2Wealth;
    }

    public void setP2Wealth(double p2Wealth) {
        this.p2Wealth = p2Wealth;
    }

    public double getP1Power() {
        return p1Power;
    }

    public void setP1Power(double p1Power) {
        this.p1Power = p1Power;
    }

    public double getP2Power() {
        return p2Power;
    }

    public void setP2Power(double p2Power) {
        this.p2Power = p2Power;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Treasures getQuest() {
        return quest;
    }

    public void setQuest(Treasures quest) {
        this.quest = quest;
    }

    public long getStartTimeMillis() {
        return startTimeMillis;
    }

    public void setStartTimeMillis(long startTimeMillis) {
        this.startTimeMillis = startTimeMillis;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public int getQuestCounter() {
        return questCounter;
    }

    public void setQuestCounter(int questCounter) {
        this.questCounter = questCounter;
    }
}
