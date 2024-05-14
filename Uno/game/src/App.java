import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {

        ArrayList<Object> deck = new ArrayList<Object>();

        String[] colors = {"Red", "Yellow", "Green", "Blue", "Black"};

        
       for (int i = 0; i<colors.length; i++){
            for (int j = 0; j<9; j++){
                if (!(i == 4)){
                    deck.add(new cardNumbers(colors[i], j));
                }
                else{
                   
                }

              
                
            }
       }

     


       System.out.println(((cardNumbers) deck.get(0)).getColor());
       System.out.println(((cardNumbers) deck.get(1)).getNumber());
        
            











    }

    public static void createDeck(){
        



    }
}
