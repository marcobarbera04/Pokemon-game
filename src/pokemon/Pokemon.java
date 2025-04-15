package pokemon;

import java.util.ArrayList;
import java.util.Random;

public class Pokemon{
    private String name;
    private double maxHp;
    private double hp;
    private double attack;
    private double defense;
    private double speed;
    public static final int MAX_MOVES = 2;
    private ArrayList<Move> moves;

    public Pokemon(String name, double maxHp, double attack, double defense, double speed, ArrayList<Move> moves){
        setName(name);
        setMaxHp(maxHp);
        this.hp = this.maxHp;
        setAttack(attack);
        setDefense(defense);
        setSpeed(speed);
        this.moves = new ArrayList<>();
        setMoves(moves);
    }

    @Override
    public String toString(){
        return this.getName();
    }

    public void showMoves(){
        ArrayList<Move> moves = getMoves();
        for(int i = 0; i < moves.size(); i++){
            Move move = moves.get(i);
            System.out.print(i + ") " + move.getName() + "\t\t");
        }
        System.out.println();
    }

    // getter methods
    public String getName() {
        return name;
    }
    public double getMaxHp() {
        return maxHp;
    }
    public double getHp() {
        return hp;
    }
    public double getAttack() {
        return attack;
    }
    public double getDefense() {
        return defense;
    }
    public double getSpeed() {
        return speed;
    }
    public ArrayList<Move> getMoves(){
        return this.moves;
    }

    // setter methods
    public void setName(String name) {
        if(name != null){
            this.name = name;
        }
    }
    public void setMaxHp(double maxHp) {
        if(maxHp > 0){
            this.maxHp = maxHp;
        }
    }
    public void setHp(double hp) {
        this.hp = hp;
    }
    public void setAttack(double attack) {
        if(attack > 0){
            this.attack = attack;
        }
    }
    public void setDefense(double defense) {
        if(defense > 0){
            this.defense = defense;
        }
    }
    public void setSpeed(double speed) {
        if(speed > 0){
            this.speed = speed;
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

    public static double calculateDamage(Pokemon attaccking, Pokemon defending, MoveDamage move){
        double attack = attaccking.getAttack();
        double defense = defending.getDefense();
        double power = move.getPower();
        double damage = ((attack * power) / defense) * 0.5;
        return damage;
    }

    public static boolean doesHit(Move move){
        double random = new Random().nextDouble(0, 101);
        double precision = move.getPrecision();
        return random < precision;
    }

    public static boolean isAlive(Pokemon pokemon){
        if(pokemon.getHp() <= 0){
            return false;
        }
        else{
            return true;
        }
    }

    public static Pokemon getFastestPokemon(Pokemon p1, Pokemon p2){
        double p1Speed = p1.getSpeed();
        double p2Speed = p2.getSpeed();
        if(p2Speed > p1Speed){
            return p2;
        }
        else{
            return p1;
        }
    }

}