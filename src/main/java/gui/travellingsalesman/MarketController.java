package gui.travellingsalesman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class MarketController{
    //attributes
    private ArrayList<Treasures> treasures = new ArrayList<Treasures>();
    private Stage stage2;
    private Player player;
    @FXML
    private Label swordPower, hammerPower, bowPower, swordCost, hammerCost, bowCost, potionCost, treasureCost, marketMessage;
    private double sPower, hPower, bPower, sCost, hCost, bCost, pCost, tCost;
    private int turns;
    private boolean numbersGenerated;

    //method to set market info
    protected void display(){
        //displaying
        String sPowerString = String.format("%.1f", sPower);
        String bPowerString = String.format("%.1f", bPower);
        String hPowerString = String.format("%.1f", hPower);

        swordPower.setText("Power: "+ sPowerString);
        hammerPower.setText("Power: "+ hPowerString);
        bowPower.setText("Power: "+ bPowerString);

        swordCost.setText("Cost:    "+sCost);
        hammerCost.setText("Cost:    "+hCost);
        bowCost.setText("Cost:    "+bCost);
        potionCost.setText("Cost:    "+pCost);
        treasureCost.setText("Cost:    "+ tCost);

    }

    public double[] shopGenerator(Random random) {
        sCost = random.nextInt(301-100)+100;
        hCost = random.nextInt(301-100)+100;
        bCost = random.nextInt(301-100)+100;
        pCost = random.nextInt(301-100)+100;
        tCost = random.nextInt(301-100)+100;
        sPower = sCost * 0.1;
        hPower = hCost * 0.1;
        bPower = bCost * 0.1;
        return new double[] {sCost, hCost, bCost, pCost, tCost, sPower, hPower, bPower};
    }

    public void setShopValues(double[] values){
        sCost = values[0];
        hCost = values[1];
        bCost = values[2];
        pCost = values[3];
        tCost = values[4];
        sPower = sCost * 0.1;
        hPower = hCost * 0.1;
        bPower = bCost * 0.1;
    }

    //method to purchase treasure location
    @FXML
    protected void purchaseTreasure(MouseEvent event){
        Random r = new Random();
        int treasureNum = r.nextInt(treasures.size());
        if(tCost <= player.getWealth()){
            player.setWealth(-tCost);
            player.getPlayerPath().add(treasures.get(treasureNum).getLocation());
            player.getPlayerPathMap().add(treasures.get(treasureNum).getLocation());
            marketMessage.setText("Purchased "+treasures.get(treasureNum).getName()+" location successfully");
        }else {
            marketMessage.setText("Insufficient funds");
        }
    }

    //method to use recovery potion
    @FXML
    protected void purchasePotion(MouseEvent event){
        if((player.getPower()- player.getItemPower())!=10){
            if(pCost <= player.getWealth()){
                player.setWealth(-pCost);
                player.setPower(-player.getPower()+10+ player.getItemPower());
                marketMessage.setText("Purchased recovery potion successfully");
            }else {
                marketMessage.setText("Insufficient funds");
            }
        }else {
            marketMessage.setText("You are already full base power!");
        }
    }

    //method to purchase the weapons
    @FXML
    protected void purchaseSword(MouseEvent event){
        if(sPower > player.getItemPower()&&player.getWeapon()!= Player.Weapons.Sword){
            statsUpdateWeapon(sPower,sCost);
            player.setWeapon(Player.Weapons.Sword);
        }else {
            marketMessage.setText("Careful! You already own this weapon\n or a weapon that is better.");
        }
    }

    @FXML
    protected void purchaseBow(MouseEvent event){
        if(bPower > player.getItemPower()&&player.getWeapon()!= Player.Weapons.Bow){
            statsUpdateWeapon(bPower,bCost);
            player.setWeapon(Player.Weapons.Bow);
        }else {
            marketMessage.setText("Careful! You already own this weapon\n or a weapon that is better.");
        }
    }

    @FXML
    protected void purchaseHammer(MouseEvent event){
        if(hPower > player.getItemPower()&&player.getWeapon()!= Player.Weapons.Hammer){
            statsUpdateWeapon(hPower,hCost);
            player.setWeapon(Player.Weapons.Hammer);
        }else {
            marketMessage.setText("Careful! You already own this weapon\n or a weapon that is better.");
        }
    }

    //method to update the players stats
    protected void statsUpdateWeapon(double itemPower, double cost){
        if(cost <= player.getWealth()){
            player.setWealth(-cost);
            player.setPower(-player.getItemPower()+itemPower);
            player.setItemPower(itemPower);
            marketMessage.setText("Purchased weapon successfully");
        }else {
            marketMessage.setText("Insufficient funds...");
        }
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

    public ArrayList<Treasures> getTreasures() {
        return treasures;
    }

    public void setTreasures(ArrayList<Treasures> treasures) {
        this.treasures = treasures;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public boolean isNumbersGenerated() {
        return numbersGenerated;
    }

    public void setNumbersGenerated(boolean numbersGenerated) {
        this.numbersGenerated = numbersGenerated;
    }
}
