package shared.pokemon;

import java.io.Serializable;

public abstract class Move implements Serializable{
    final private int id;
    final private String name;
    final private double accuracy;

    public Move(int id, String name, double accuracy){
        if(name == null){
            throw new RuntimeException("[ERROR]: Move name cannot be null");
        }
        if(accuracy <= 0){
            throw new RuntimeException("[ERROR]: Move precision must be positive (move name: " + name + ")");
        }

        this.id = id;
        this.name = name;
        this.accuracy = accuracy;
    }

    @Override
    public String toString(){
        return this.getName();
    }

    // getter methods
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public double getAccuracy(){
        return accuracy;
    }
}
