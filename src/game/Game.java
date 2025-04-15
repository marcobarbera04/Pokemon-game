package game;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;

import pokemon.*;

public class Game {
    public static Pokemon pokemons[];
    private Pokemon playerPokemon;
    private Pokemon enemyPokemon;
    private int playerPokemonIndex;
    private int enemyPokemonIndex;
    private Random random = new Random();
    private Scanner scanner;
    private boolean hasPlayerWon;
    
    public void initPokemons(){
        pokemons = new Pokemon[3];
        
        ArrayList<Move> pokemonMoves0 = new ArrayList<>();
        pokemonMoves0.add(new MoveDamage("Flamethrower", 75, 110));
        pokemonMoves0.add(new MoveDamage("Tackle", 100, 50));
        pokemonMoves0.add(new MoveDamage("foo", 100, 0));
        pokemonMoves0.add(new MoveDamage("foo", 100, 0));
        pokemons[0] = new Pokemon("Charizard", 350, 25, 25, 25, pokemonMoves0);

        ArrayList<Move> pokemonMoves1 = new ArrayList<>();
        pokemonMoves1.add(new MoveDamage("Idropulsar", 75, 110));
        pokemons[1] = new Pokemon("Blastoise", 350, 25, 25, 25, pokemonMoves1);

        ArrayList<Move> pokemonMoves2 = new ArrayList<>();
        pokemonMoves2.add(new MoveDamage("Leaf blade", 75, 110));
        pokemons[2] = new Pokemon("Venusaur", 350, 25, 25, 25, pokemonMoves2);
    }

    public void run(){
        this.initPokemons();
        scanner = new Scanner(System.in);

        playerChoosePokemon();  // let the player choose his pokemon
        enemyChoosePokemon();   // choosing randomly enemy pokemon (different than the player)

        match();                // match loop
        if(hasPlayerWon){
            System.out.println("You have won");
        }else{
            System.out.println("You have lost");
        }
        simulateWait();
        ClearConsole.clear();
        scanner.close();
    }

    public void match(){
        while (playerPokemon.getHp() > 0 && enemyPokemon.getHp() > 0) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(enemyPokemon + ": " + enemyPokemon.getHp() + " hp\n\n\n\n\n");
            System.out.println("\t\t\t\t\t\t\t\t" + playerPokemon + ": " + playerPokemon.getHp() + " hp");
            playerPokemon.showMoves();
            System.out.println("----------------------------------------------------------------------------------");

            Move playerMove = playerChooseMove();   
            Move enemyMove = enemyChooseMove();

            Pokemon fastest = Pokemon.getFastestPokemon(playerPokemon, enemyPokemon);
            if(fastest == playerPokemon){
                executeMove(playerPokemon, enemyPokemon, playerMove);
                if(!Pokemon.isAlive(enemyPokemon)){
                    setHasPlayerWon(true);
                    continue;
                }
                executeMove(enemyPokemon, playerPokemon, enemyMove);
                if(!Pokemon.isAlive(playerPokemon)){
                    setHasPlayerWon(false);
                    continue;
                }
            }
            ClearConsole.clear();
        }
    }

    public void playerChoosePokemon(){
        System.out.print("Choose a pokemon between: ");
        for(int i = 0; i < pokemons.length; i++){
            System.out.print(pokemons[i].getName() + ", ");
        }
        System.out.println("write the name of the pokemon to choose it.");
        // choosing player pokemon
        boolean isValid = false;
        playerPokemonIndex = -1;
        while(!isValid){
            String choose = scanner.nextLine();
            for(int i = 0; i < pokemons.length; i++){
                String name = pokemons[i].getName();
                if(name.equalsIgnoreCase(choose)){
                    System.out.println("You choose: " + name);
                    playerPokemonIndex = i;
                    isValid = true;
                    break;
                }
            }
            if(!isValid){
                System.out.println("You wrote an invalid name");
            }
        }
        playerPokemon = pokemons[playerPokemonIndex];
    }

    public void enemyChoosePokemon(){
        enemyPokemonIndex = -1;
        do{
            enemyPokemonIndex = random.nextInt(pokemons.length);
        }while(enemyPokemonIndex == playerPokemonIndex);
        System.out.println("Your enemy choose " + pokemons[enemyPokemonIndex].getName());
        enemyPokemon = pokemons[enemyPokemonIndex];
    }

    public Move playerChooseMove(){
        int move = -1;
        do {
            System.out.println("Choose a move [0, " + (playerPokemon.getMoves().size()-1) + "]");
            try{
                move = scanner.nextInt();
            }catch(InputMismatchException exception){
                System.out.println("[ERROR]: insert a valid integer");
                scanner.nextLine();  // consume the invalid character to avoid infinite loop
            }
        }while(move < 0 || move > playerPokemon.getMoves().size()-1);
        //System.out.println("You choose the move: " + playerPokemon.getMoves().get(move));
        return playerPokemon.getMoves().get(move);
    }

    public Move enemyChooseMove(){
        int move = random.nextInt(0, enemyPokemon.getMoves().size());
        //System.out.println("Your enemy choose the move: " + enemyPokemon.getMoves().get(move));
        return enemyPokemon.getMoves().get(move);
    }

    public void executeMove(Pokemon attacking, Pokemon defending, Move move){
        if(move instanceof MoveDamage){
            MoveDamage moveDamage = (MoveDamage)move;
            attack(attacking, defending, moveDamage);
        }
        else if(move instanceof MoveStatus){
            MoveStatus moveStatus = (MoveStatus)move;
            attackStatus(attacking, defending, moveStatus);
        }
    }

    public void attack(Pokemon attacking, Pokemon defending, MoveDamage move){
        if(Pokemon.doesHit(move)){
            double damage = Pokemon.calculateDamage(attacking, defending, move);
            defending.setHp(defending.getHp() - damage);
            System.out.println(attacking.getName() + " successfully uses " + move.getName());
            simulateWait();
        }
        else{
            System.out.println(attacking.getName() + " failed to use " + move.getName());
            simulateWait();
        }
    }

    public void attackStatus(Pokemon attacking, Pokemon defending, MoveStatus move){}

    public void simulateWait(){
        try{
            System.out.print(".");
            Thread.sleep(1000); 
            System.out.print(".");
            Thread.sleep(1000);
            System.out.print(".");
            Thread.sleep(1000);
            System.out.println(); 
        }catch(InterruptedException exception){
            System.out.println("[ERROR]: sleep interrupted");
        }
    }

    public boolean getHasPlayerWon(){
        return hasPlayerWon;
    }
    public void setHasPlayerWon(boolean hasPlayerWon){
        this.hasPlayerWon = hasPlayerWon;
    }
}
