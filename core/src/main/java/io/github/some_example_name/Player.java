package io.github.some_example_name;

public class Player {
    
    private int playerID;
    private int resources;
    private int health;

    public Player(int playerID){
        this.playerID = playerID;
        this.resources = 100; // placeholder
        this.health = 1000; // placeholder (health of the base)
    }

    // handles base damage
    public void takeBaseDamage(int damage){
        this.health = Math.max(this.health - damage, 0);
    }

    // checks if the player's base is destroyed
    public boolean isDefeated(){
        return this.health <= 0;
    }

    // checks if the player has enough sources
    public boolean spendResources(int cost){
        if(this.resources >= cost){
            this.resources -= cost;
            return true;
        }
        return false;
    }

    public void addIncome(int amount){
        this.resources += amount;
    }

    // getters
    public int getPlayerID(){
        return this.playerID;
    }

    public int getHealth(){
        return this.health;
    }

    public int getResources(){
        return this.resources;
    }
}
