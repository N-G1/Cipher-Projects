import java.util.*;

public class Vigenere extends Substitution{
    private ArrayList<Caesar> shfts = new ArrayList<Caesar>();
    private int currPos = 0;
    private String keyword;

    /**
     * Default constructor, sets the only letter shift to 0 as it will be looped over by entire string.
     **/
    public Vigenere(){
        shfts.add(new Caesar(0));
    }
    /**
     * Constructor that takes keyword to be used and translated into shifts.
     * @param s Keyword to be used
     **/
    public Vigenere(String s){
        int currShft;
        keyword = s;
        for(int i = 0; i < s.length(); i++){        
            currShft = ((int) Character.toLowerCase(s.charAt(i)) - 'a');
            //takes the current shift and saves it to a seperate caesar object in an arrList where all the individual shifts are held.
            shfts.add(new Caesar(currShft)); 
        }
    }
    /**
     * encrypts each character relative to keyword.
     * @param c character to be encrypted
     * @return encrypted character.
     **/
    public char encrypt(char c){
        int currAscii;

        if (Character.isLetter(c) && Character.isUpperCase(c)){
            currAscii = Character.toLowerCase(c) - 38;
        }
        else if (Character.isLetter(c)){
            currAscii = Character.toLowerCase(c) - 97;
        }
        else{
            return c;
        }

        //handles if default constructor is used or keyword is 1 char
        if (this.shfts.size() == 1){
            currPos = 0;
        }
        return this.shfts.get(currPos).getMapStr().get(currAscii);
       
    }
    /**
     * decrypts each character relative to keyword.
     * @param c character to be decrypted
     * @return decrypted character.
     **/
    public char decrypt(char c){
        int currAscii = 0;

        for (int i = 0; i < this.shfts.get(currPos).getMapStr().size(); i++){
            if (c == (char) this.shfts.get(currPos).getMapStr().get(i)){
                currAscii = i;
                break;
            }
        }
        if (Character.isLetter(c) && Character.isUpperCase(c)){ 
            currAscii += 6; 
        }
        else if (Character.isLetter(c)){
            currAscii += 97;       
        }
        else{
            return c;
        }

        return (char) currAscii;
    }
    /**
     * Passes 1 char at a time to char encrypt and rebuilds string.
     * @param s plain text string
     * @return encrypted string
     **/
    public String encrypt(String s){
        String str = "";

        for (int i = 0; i < s.toString().length(); i++){
            this.currPos = i % keyword.length();
            str += this.encrypt(s.toString().charAt(i));
        }

        return str;
    }
    /**
     * Passes 1 char at a time to char decrypt and rebuilds string.
     * @param s crypto text string
     * @return decrypted string
     **/
    public String decrypt(String s){
        String str = "";

        for (int i = 0; i < s.toString().length(); i++){
            this.currPos = i % keyword.length();
            str += this.decrypt(s.toString().charAt(i));
        }

        return str;
    }
    /**
     * Main function takes and validates user input.
     * @param args cmd line args
     */
    public static void main(String args[]){      
        if (args.length == 3) {
            Vigenere n = new Vigenere(args[1]);   
            if (args[0].equals("encrypt")){
                n.currPos = 0;
                System.out.println(n.encrypt(args[2]));
            }
            else if (args[0].equals("decrypt")){
                n.currPos = 0; 
                
                System.out.println(n.decrypt(args[2]));
            }
            else {
                System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!\nUsage: java Vigenere encrypt key \"cipher text\"");
            }
        } 
        else if (args.length < 3){
            System.out.println("Too few parameters!\nUsage: java Vigenere encrypt key \"cipher text\"");
        }
        else {
            System.out.println("Too many parameters!\nUsage: java Vigenere encrypt key \"cipher text\"");
        }
    }
}
