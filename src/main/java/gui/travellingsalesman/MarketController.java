package gui.travellingsalesman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Random;

public class MarketController{
    //attributes
    private Stage stage2;
    private Player player;
    @FXML
    private Label swordPower, hammerPower, bowPower, swordCost, hammerCost, bowCost, potionCost, treasureCost, marketMessage;
    double sPower, hPower, bPower, sCost, hCost, bCost, pCost, tCost;
    String sPowerString, hPowerString, bPowerString;

    //method to set market info
    protected void display(){
        Random random = new Random();

        //updating the shop prices
        sCost = random.nextInt(301-100)+100;
        hCost = random.nextInt(301-100)+100;
        bCost = random.nextInt(301-100)+100;
        pCost = random.nextInt(301-100)+100;
        tCost = random.nextInt(301-100)+100;
        sPower = sCost * 0.1;
        hPower = hCost * 0.1;
        bPower = bCost * 0.1;


        //displaying
        sPowerString = String.format("%.1f",sPower);
        bPowerString = String.format("%.1f",bPower);
        hPowerString = String.format("%.1f",hPower);

        swordPower.setText("Power: "+sPowerString);
        hammerPower.setText("Power: "+hPowerString);
        bowPower.setText("Power: "+bPowerString);

        swordCost.setText("Cost:    "+sCost);
        hammerCost.setText("Cost:    "+hCost);
        bowCost.setText("Cost:    "+bCost);
        potionCost.setText("Cost:    "+pCost);
        treasureCost.setText("Cost:    "+ tCost);

    }

    //method to switch windows
    @FXML
    protected void switchWindows(ActionEvent event){
        stage2.getScene().getWindow().hide();
        //super static method
        GameController.showGame();
    }

    //getters and setters
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Stage getStage2() {
        return stage2;
    }

    public void setStage2(Stage stage2) {
        this.stage2 = stage2;
    }
}
