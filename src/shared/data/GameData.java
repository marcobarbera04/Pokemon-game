package shared.data;

import java.io.Serializable;
import java.util.ArrayList;

import shared.pokemon.Pokemon;
import shared.pokemon.Move;

public final class GameData implements Serializable{
    private ArrayList<Pokemon> pokemons = new ArrayList<>();
    private ArrayList<Move> moves = new ArrayList<>();

    public GameData(){
        pokemons = new ArrayList<>();
        moves = new ArrayList<>();
    }

    public void addPokemon(Pokemon pokemon){
        if(pokemon != null){
            pokemons.add(pokemon);
        }
    }

    public void addPokemons(ArrayList<Pokemon> pokemons){
        if(pokemons != null){
            this.pokemons.addAll(pokemons);
        }
    }

    public void addMove(Move move){
        if(move != null){
            moves.add(move);
        }
    }

    public void addMoves(ArrayList<Move> moves){
        if(moves != null){
            this.moves.addAll(moves);
        }
    }

    public int pokemonsSize(){
        return pokemons.size();
    }

    public int movesSize(){
        return moves.size();
    }

    // For debugging purpose (used on server)
    public void printPokemons(){
        if(pokemons == null){
            throw new RuntimeException("Pokemons list is null");
        }
        System.out.println(pokemons);
    }

    public void printMoves(){
        if(moves == null){
            throw new RuntimeException("Moves list is null");
        }
        System.out.println(moves);
    }
}
