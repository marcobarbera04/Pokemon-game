package game;
import java.util.Scanner;
import java.util.Random;

import pokemon.Pokemon;

public class Game {
    public static Pokemon pokemons[];
    protected Pokemon playerPokemon;
    protected Pokemon enemyPokemon;

    private Random random = new Random();
    
    public void initPokemons(){
        pokemons = new Pokemon[3];
        pokemons[0] = new Pokemon("Charizard");
        pokemons[1] = new Pokemon("Blastoise");
        pokemons[2] = new Pokemon("Venusaur");
    }

    public void run(){
        this.initPokemons();

        System.out.print("Choose a pokemon between: ");
        for(int i = 0; i < pokemons.length; i++){
            System.out.print(pokemons[i].getName() + ", ");
        }
        System.out.println("write the name of the pokemon to choose it.");

        // choosing player pokemon
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        int playerPokemonIndex = -1;
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
        scanner.close();

        // choosing randomly enemy pokemon (different than the player)
        int enemyPokemonIndex = -1;
        do{
            enemyPokemonIndex = random.nextInt(pokemons.length);
        }while(enemyPokemonIndex == playerPokemonIndex);
        System.out.println("Your enemy choose " + pokemons[enemyPokemonIndex].getName());
        
        

        // match() loop
        // ask to play again 
        // exit
    }
}
