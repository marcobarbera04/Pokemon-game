package pokemon;

public class MoveStatus extends Move {
    final Statistic statistic;    // The statistic that will be affected by the move
    final double percentage;      // The percentage that will increase/decrease the statistic
    final boolean self;           // Flag that tell if the pokemon target is the one that execute the move    

    public MoveStatus(String name, double accuracy, Statistic statistic, double percentage, boolean self){
        super(name, accuracy);

        if(statistic == null){
            throw new RuntimeException("[ERROR]: MoveStatus statistic is invalid (move name: " + name + ")");
        }
        if(percentage <= 0 || percentage > 100){
            throw new RuntimeException("[ERROR]: MoveStatus percentage is invalid (move name: " + name + ")");
        }
        this.statistic = statistic;
        this.percentage = percentage;
        this.self = self;
    }

    public Statistic getStatistic(){
        return statistic;
    }
    public double getPercentage(){
        return percentage;
    }
    public boolean isSelfAffected(){
        return self;
    }
}