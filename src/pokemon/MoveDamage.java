package pokemon;

public class MoveDamage extends Move {
    private double power;

    public MoveDamage(String name, double precision ,double power){
        super(name, precision);
        setPower(power);
    }

    // getter methods
    public double getPower(){
        return power;
    }

    // setter methods
    public void setPower(double power){
        if(power > 0){
            this.power = power;
        }
    }
}
