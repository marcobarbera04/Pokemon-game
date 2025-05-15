package shared.pokemon;

public class MoveDamage extends Move {
    final private double power;

    public MoveDamage(int id, String name, double accuracy ,double power){
        super(id, name, accuracy);

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
