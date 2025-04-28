package pokemon;

public abstract class Move{
    final private String name;
    final private double precision;

    public Move(String name, double precision){
        if(name == null){
            throw new RuntimeException("[ERROR]: Move name cannot be null");
        }
        if(precision <= 0){
            throw new RuntimeException("[ERROR]: Move precision must be positive (move name: " + name + ")");
        }

        this.name = name;
        this.precision = precision;
    }

    @Override
    public String toString(){
        return this.getName();
    }

    // getter methods
    public String getName(){
        return name;
    }
    public double getPrecision(){
        return precision;
    }
}
