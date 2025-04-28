package pokemon;

public class MoveDamage extends Move {
    final private double power;

    public MoveDamage(String name, double precision ,double power){
        super(name, precision);

        if(power <= 0){
            throw new RuntimeException("[ERROR]: MoveDamage power must be positive (move name: " + name + ")");
        }
        this.power = power;
    }

    // getter methods
    public double getPower(){
        return power;
    }
}
