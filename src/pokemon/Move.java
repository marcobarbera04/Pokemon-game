package pokemon;

public abstract class Move{
    private String name;
    private double precision;

    public Move(){}

    public Move(String name, double precision){
        setName(name);
        setPrecision(precision);
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

    // setter methods
    public void setPrecision(double precision){
        if(precision > 0){
            this.precision = precision;
        }
    }
    public void setName(String name){
        if(name != null){
            this.name = name;
        }
    }
}
