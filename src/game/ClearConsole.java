package game;
/**
 * This class is used to clear the console regardless of the operating system
 */
public class ClearConsole {
    public static void clear(){
        try{
            String os = System.getProperty("os.name").toLowerCase();
            if(os.contains("win")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else{
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        }catch(Exception exception){
            System.out.println("[Error]: Impossible to clear the console");
        }
    }
}
