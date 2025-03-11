package pokemon;

public class Pokemon{
    private String name;
    private double maxHp;
    private double hp;
    private double attack;
    private double defense;
    private double speed;
    private Move moves[];

    public Pokemon(String name){
        this.name = name;
    }

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

    public void setNomePokemon(String name) {
        this.name = name;
    }
    public void setMaxHp(double maxHp) {
        this.maxHp = maxHp;
    }
    public void setHp(double hp) {
        this.hp = hp;
    }
    public void setAttack(double attack) {
        this.attack = attack;
    }
    public void setDefense(double defense) {
        this.defense = defense;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
}