package shared.pokemon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Pokemon implements Serializable{
    private final int id;
    private final String name;

    private final double hp;
    private final double attack;
    private final double defense;
    private final double speed;

    private double actualHp;
    private double actualAttack;
    private double actualDefense;
    private double actualSpeed;

    public static final int MAX_MOVES = 4;
    private ArrayList<Move> moves;

    public Pokemon(int id, String name, double hp, double attack, double defense, double speed, ArrayList<Move> moves){
        if(name == null){
            throw new RuntimeException("Name is null");
        }
        if(hp <= 0 | attack <= 0 | defense <= 0 | speed <= 0){
            throw new RuntimeException("Hp or Attack or Defense or Speed are not valid (less or equal than 0)");
        }
        if(moves == null){
            throw new RuntimeException("Moves are null");
        }
        if(moves.size() > MAX_MOVES){
            throw new RuntimeException("There can be a maximum of " + MAX_MOVES + " moves");
        }
        this.id = id;
        this.name = name;

        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;

        this.actualHp = hp;
        this.actualAttack = attack;
        this.actualDefense = defense;
        this.actualSpeed = speed;

        this.moves = new ArrayList<>();
        setMoves(moves);
    }

    // getter methods
    public int getId(){
        return id;
    }
    public String getName() {
        return name;
    }
    public double getHp() {
        return hp;
    }
    public double getActualHp() {
        return actualHp;
    }
    public double getAttack() {
        return attack;
    }
    public double getActualAttack(){
        return actualAttack;
    }
    public double getDefense() {
        return defense;
    }
    public double getActualDefense(){
        return actualDefense;
    }
    public double getSpeed() {
        return speed;
    }
    public double getActualSpeed(){
        return actualSpeed;
    }
    public ArrayList<Move> getMoves(){
        return this.moves;
    }

    // setter methods
    public void setActualHp(double ActualHp) {
        this.actualHp = ActualHp;
    }
    public void setActualAttack(double actualAttack) {
        if(attack > 0){
            this.actualAttack = actualAttack;
        }
    }
    public void setActualDefense(double actualDefense) {
        if(defense > 0){
            this.actualDefense = actualDefense;
        }
    }
    public void setActualSpeed(double actualSpeed) {
        if(speed > 0){
            this.actualSpeed = actualSpeed;
        }
    }
    public void setMove(Move move){
        if(move != null){
            moves.add(move);
        }
    }
    public void setMoves(ArrayList<Move> moves){
        if(moves.isEmpty()){
            return;
        }

        for(int i = 0; i < moves.size(); i++){  
            setMove(moves.get(i));   
        }
    }

    @Override
    public String toString(){
        return this.getName();
    }

    /**
     * This method print every moves that a pokemon has
     */
    public void showMoves(){
        ArrayList<Move> moves = getMoves();
        for(int i = 0; i < moves.size(); i++){
            Move move = moves.get(i);
            System.out.print(i + ") " + move.getName() + "\t\t");
        }
        System.out.println();
    }

    /**
     * This static method calculate how much damage a move should inflict.
     * The calculation is based on the attacking pokemon's attack,
     * move's power and defending pokemon's defense
     * @param   attaccking  the attacking pokemon    
     * @param   defending   the defending pokemon
     * @param   move        the used move
     * @return              the value of damage inflicted by the move
     */
    public static double calculateDamage(Pokemon attaccking, Pokemon defending, MoveDamage move){
        double attack = attaccking.getActualAttack();
        double defense = defending.getActualDefense();
        double power = move.getPower();
        double damage = ((attack * power) / defense) * 0.5;
        return damage;
    }

    /**
     * This static method calculate the statistic affection of a MoveStatus and apply it
     * to the target statistic if it would stay above 0, if the move do not affect the statistic
     * the method will return false, otherwise the method will return true
     * @param   pokemon the attacking pokemon
     * @param   move    the defending pokemon
     * @return          a flag that determines whether the move affects the statistics
     */
    public static boolean affectStatistic(Pokemon pokemon, MoveStatus move){
        boolean didAffect = false;
        double percentage = move.getPercentage();
        double affectionValue = 0;

        switch(move.getStatistic()){
            case ATTACK:
                double actualAttack = pokemon.getActualAttack();
                affectionValue = actualAttack * (percentage / 100);
                double newAttack = actualAttack + affectionValue;
                if(newAttack > 0){
                    didAffect = true;
                    pokemon.setActualAttack(newAttack);
                }
                break;
            case DEFENSE:
                double actualDefense = pokemon.getActualDefense();
                affectionValue = actualDefense * (percentage / 100);
                double newDefense = actualDefense + affectionValue;
                if(newDefense > 0){
                    didAffect = true;
                    pokemon.setActualDefense(newDefense);
                }
                break;
            case SPEED:
                double actualSpeed = pokemon.getActualSpeed();
                affectionValue = actualSpeed * (percentage / 100);
                double newSpeed = actualSpeed + affectionValue;
                if(newSpeed > 0){
                    didAffect = true;
                    pokemon.setActualSpeed(newSpeed);
                }
                break;
            default:
                System.out.println("Invalid statistic type!");
                return false;
        }

        return didAffect;
    }

    /**
     * This static method check if a move does hit based on its accuracy
     * @param   move    the executed move  
     * @return          a boolean value, true (it does hit), false (it does not hit)
     */
    public static boolean doesHit(Move move){
        double random = new Random().nextDouble(0, 101);
        double accuracy = move.getAccuracy();
        return random < accuracy;
    }

    /**
     * This static method check if a pokemon is alive using its health,
     * If the pokemon's health is less or equal 0 it return false,
     * if the pokemon's health is greater than 0 it return true. 
     * @param   pokemon the pokemon that is going to be checked
     * @return          a boolean value, true (pokemon is alive), false (pokemon is not alive)
     */
    public static boolean isAlive(Pokemon pokemon){
        if(pokemon.getActualHp() <= 0){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * This static method compare two pokemons and return the faster one
     * @param   p1  first pokemon to be compared
     * @param   p2  second pokemon to be compared
     * @return      the faster pokemon
     */
    public static Pokemon getFastestPokemon(Pokemon p1, Pokemon p2){
        double p1Speed = p1.getActualSpeed();
        double p2Speed = p2.getActualSpeed();
        if(p2Speed > p1Speed){
            return p2;
        }
        else{
            return p1;
        }
    }

}