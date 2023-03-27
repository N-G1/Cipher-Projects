public class Caesar{
    /**
     * main.
     * @param args
     */
    public static void main(String[] args){
        if (args.length == 2) {
            System.out.println(rotate(Integer.parseInt(args[0]), args[1]));
        } 
        else if (args.length < 2){
            System.out.println("Too few parameters!\nUsage: java Caesar n \"cipher text\"");
        }
        else {
            System.out.println("Too many parameters!\nUsage: java Caesar n \"cipher text\"");
        }
    }
    /**
     * Takes a character and a shift and returns the new character.
     * @param shft shift
     * @param rte char
     * @return returns encrypted character 
     */
    public static char rotate(int shft, char rte){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int currPos = alph.indexOf(Character.toLowerCase(rte));
        int newPos = (currPos + shft) % 26;
        char newChar = 'a';
        if (newPos < 0){
            newChar = alph.charAt(alph.length() - (int) Math.sqrt(newPos*newPos));
        }
        else {
            newChar = alph.charAt(newPos);
        }

        if (Character.isUpperCase(rte) && Character.isLetter(rte)) {
            return Character.toUpperCase(newChar);
        }
        else if (Character.isLowerCase(rte) && Character.isLetter(rte)){
            return newChar;
        }
        else{
            return rte;
        }
    }
    /**
     * Takes a string, a shift and returns the new string.
     * @param shft shift
     * @param rteStr str
     * @return returns encrypted string
     */
    public static String rotate(int shft, String rteStr){
        String ecrpStr = "";
        for (int i = 0; i < rteStr.length(); i++){
            ecrpStr += rotate(shft, rteStr.charAt(i));
        }
        return ecrpStr;
    }
}
