public class Caesar extends MonoAlphaSubstitution{
    private int shift;
    
    /**
     * default constructor, string mapping is already defined in superclass
     */
    public Caesar(){

    }
    /**
     * Constructor that takes shift and adjusts character mapping appropriately.
     * @param s shift
     */
    public Caesar(int s){
        int currAscii;
        shift = s;
        for (int i = 0; i < 26; i++){
            if (i + shift < 26 && i + shift >= 0){
                currAscii = (i + shift + 97);
                this.getMapStr().set(i, (char) currAscii); // handles lower case
                this.getMapStr().set(i + 59, Character.toUpperCase((char) currAscii)); // handles capital letter section of mapping
            }
            else if (i + shift < 0){ // handles underflows 
                currAscii = ((i + shift) + 123);
                this.getMapStr().set(i, (char) currAscii);
                this.getMapStr().set(i + 59, Character.toUpperCase((char) currAscii)); 
            }
            else { // handles overflows
                currAscii = (i + shift + 71);
                this.getMapStr().set(i, (char) currAscii);
                this.getMapStr().set(i + 59, Character.toUpperCase((char) currAscii)); 
            }
        }
    }
    /**
     * Handles user input and validation.
     * @param args cmd line arguments.
     */
    public static void main(String[] args){
        if (args.length == 3) {
            Caesar n = new Caesar(Integer.parseInt(args[1]));
            if (args[0].equals("encrypt")){
                System.out.println(n.encrypt(args[2]));
            }
            else if (args[0].equals("decrypt")){
                System.out.println(n.decrypt(args[2]));
            }
            else {
                System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!\nUsage: java Caesar encrypt n \"cipher text\"");
            }
        } 
        else if (args.length < 3){
            System.out.println("Too few parameters!\nUsage: java Caesar encrypt n \"cipher text\"");
        }
        else {
            System.out.println("Too many parameters!\nUsage: java Caesar encrypt n \"cipher text\"");
        }
    }
}
