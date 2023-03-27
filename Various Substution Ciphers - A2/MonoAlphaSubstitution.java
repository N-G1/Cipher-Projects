import java.util.*;
public class MonoAlphaSubstitution extends Substitution{

    private ArrayList<Character> mapStr = new ArrayList<Character>(
        Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
        'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
        's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ' , 
        '!', '"', '#', '$', '%', '&', '\'', '(', ')', 
        '*', '+', ',', '-', '.', '/', '0', '1', '2', 
        '3', '4', '5', '6', '7', '8', '9', ':', ';', 
        '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 
        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 
        'W', 'X', 'Y', 'Z'));
        
    /**
     * Default constructor does nothing as string mapping is already initialised.
     **/
    public MonoAlphaSubstitution(){

    }
    /**
     * Constructor that will usually be used, splits the inputted list of characters into 
     * the characters input and the characters the user wishes them to be mapped to, then performs 
     * these changes and saves the characters in a rebuilt successfully mapped arraylist.
     * @param s inputted characters and their corresponding mapped characters
     **/
    public MonoAlphaSubstitution(String s){
        char[] sSplit = s.toCharArray();
        ArrayList<Character> wantedEncr = new ArrayList<Character>();
        ArrayList<Character> charMapping = new ArrayList<Character>();

        for (int i = 0; i < sSplit.length; i++){
            if (i % 2 == 0){
                wantedEncr.add(sSplit[i]);
            }
            else {
                charMapping.add(sSplit[i]);
            }
        }

        int currAscii;
        for (int i = 0; i < wantedEncr.size(); i++){
            for (int j = 0; j < mapStr.size(); j++) {
                // performs changes on regular characters relative to their position in the alphabet (hence the -96)
                if (Character.toLowerCase(mapStr.get(j)) == Character.toLowerCase(wantedEncr.get(i)) && Character.isLetter(wantedEncr.get(i))){ 
                    //Handles if both chars are uppercase as it will then use the end of the arraylist instead of the beginning. 
                    if (Character.isUpperCase(charMapping.get(i)) && Character.isUpperCase(wantedEncr.get(i))){
                        currAscii = Character.toLowerCase(wantedEncr.get(i)) - 37;
                        mapStr.set(currAscii - 1, charMapping.get(i));
                        
                    }
                    //Handles if the user wishes to map a lowercase character to a lower or an uppercase.
                    else{
                        currAscii = Character.toLowerCase(wantedEncr.get(i)) - 96;
                        mapStr.set(currAscii - 1, charMapping.get(i)); 
                    }
                    break;
                }
                // performs changes on special characters and numbers relative to its position at the end of the alphabet (hence the -7 as ! ascii is 33)
                else if (Character.toLowerCase(mapStr.get(j)) == Character.toLowerCase(wantedEncr.get(i))){ 
                    currAscii = (int) Character.toLowerCase(wantedEncr.get(i) - 6);
                    mapStr.set(currAscii, Character.toLowerCase(charMapping.get(i)));
                    break;
                }
            }  
        }
    }
    /**
     * Takes a character and encrypts it relative to
     * the table set up in the constructor.
     * @param c character to be encrypted
     * @return new encrypted character
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
            currAscii = Character.toLowerCase(c) - 6;
        }

            return this.mapStr.get(currAscii);

    }
    /**
     * Takes a character and decrypts it relative to
     * its position in the table and its ascii code.
     * @param c character to be decrypted
     * @return new decrypted character
     **/
    public char decrypt(char c){
        int currAscii = 0;

        // finds position in table of letter
        for (int i = 0; i < mapStr.size(); i++){
            if (c == (char) this.mapStr.get(i)){
                currAscii = i;
                break;
            }
        }
        if (Character.isLetter(c) && Character.isLowerCase(c)){ 
            currAscii += 97; 
        }
        else{
            currAscii += 6; 
        }

        return (char) currAscii;

    }
    /**
     * getter
     * @return mapped characters
     */
    public ArrayList<Character> getMapStr(){
        return this.mapStr;
    }
    /**
     * setter
     * @param s input arraylist 
     */
    public void setMapStr(ArrayList<Character> s){
        this.mapStr = s;
    }
    /**
     * Used for user input validation and function.
     * @param args cmd line arguments.
     */
    public static void main (String args[]){
        String s = args[0];
        if (args.length > 3){
            System.out.println("Too many parameters!\nUsage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
        }
        else if (args.length < 3){
            System.out.println("Too few parameters!\nUsage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
        }
        else if (s.equals("encrypt") || s.equals("decrypt")){
            if (args[0].equals("encrypt")){
                MonoAlphaSubstitution n = new MonoAlphaSubstitution(args[1]);
                System.out.println(n.encrypt(args[2]));
            }
            else {
                MonoAlphaSubstitution n = new MonoAlphaSubstitution(args[1]);
                System.out.println(n.decrypt(args[2]));
            }
        }
        else {
            System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!\nUsage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
        }      
    }
}
