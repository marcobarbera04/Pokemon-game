package game;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

import pokemon.Move;
import pokemon.MoveDamage;
import pokemon.Pokemon;

public class Game {
    public static Pokemon pokemons[];
    public Pokemon playerPokemon;
    public Pokemon enemyPokemon;

    public int playerPokemonIndex;
    public int enemyPokemonIndex;

    private Random random = new Random();
    
    public void initPokemons(){
        pokemons = new Pokemon[3];
        
        ArrayList<Move> pokemonMoves0 = new ArrayList<>();
        pokemonMoves0.add(new MoveDamage("Flamethrower", 75, 110));
        pokemons[0] = new Pokemon("Charizard", 100, 25, 25, 25, pokemonMoves0);

        ArrayList<Move> pokemonMoves1 = new ArrayList<>();
        pokemonMoves1.add(new MoveDamage("Idropulsar", 75, 110));
        pokemons[1] = new Pokemon("Blastoise", 100, 25, 25, 25, pokemonMoves1);

        ArrayList<Move> pokemonMoves2 = new ArrayList<>();
        pokemonMoves2.add(new MoveDamage("Leaf blade", 75, 110));
        pokemons[2] = new Pokemon("Venusaur", 100, 25, 25, 25, pokemonMoves2);
    }

    public void match(){
        System.out.println("------------------------------------");
        System.out.println(enemyPokemon + ": " + enemyPokemon.getHp() + " hp\n");
        System.out.println(playerPokemon + ": " + playerPokemon.getHp() + " hp");
        playerPokemon.showMoves();
        System.out.println("------------------------------------");
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
        scanner.close();

        // choosing randomly enemy pokemon (different than the player)
        enemyPokemonIndex = -1;
        do{
            enemyPokemonIndex = random.nextInt(pokemons.length);
        }while(enemyPokemonIndex == playerPokemonIndex);
        System.out.println("Your enemy choose " + pokemons[enemyPokemonIndex].getName());

        playerPokemon = pokemons[playerPokemonIndex];
        enemyPokemon = pokemons[enemyPokemonIndex];

        match();
        // ask to play again 
        // exit
    }
}
