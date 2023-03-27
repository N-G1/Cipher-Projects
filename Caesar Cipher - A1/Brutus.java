public class Brutus {
    public static final double[] english = {
        0.0855, 0.0160, 0.0316, 0.0387, 0.1210, 0.0218, 0.0209, 0.0496, 0.0733,
        0.0022, 0.0081, 0.0421, 0.0253, 0.0717, 0.0747, 0.0207, 0.0010, 0.0633,
        0.0673, 0.0894, 0.0268, 0.0106, 0.0183, 0.0019, 0.0172, 0.0011
    };
    /**
     * main func that takes cmd line args and decodes the string.
     * @param args args
     */
    public static void main(String args[]){
        if (args.length == 1) {
            double smallestScore = 100;
            int savedShft = 0;
            for (int i = 0; i < 26; i++){
                String shftStr = Caesar.rotate(i, args[0]);
                double[] strFrq = frequency(shftStr);
                double freq = chiSquared(strFrq, english);
                if (freq < smallestScore){
                    smallestScore = freq;
                    savedShft = i;
                }
            }
            System.out.println(Caesar.rotate(savedShft, args[0]));
        } 
        else if (args.length < 1){
            System.out.println("Too few parameters!\nUsage: java Brutus \"cipher text\"");
        }
        else {
            System.out.println("Too many parameters!\nUsage: java Brutus \"cipher text\"");
        }
    }
    /**
     * Takes a string and returns the count of each individual letter in the string as an int arr.
     * @param str input string
     * @return count int arr
     */
    public static int[] count(String str){
        int letterCounts[] = new int[26];
        String alph = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < letterCounts.length; i++){
            int currLett = alph.charAt(i);
            for (int j = 0; j < str.length(); j++){
                if (currLett == Character.toLowerCase(str.charAt(j))){
                    letterCounts[i]++;      
                }
            }
        }
        return letterCounts;
    }
    /**
     * calculates the frequency of all characters in a string and returns them as a doub arr.
     * @param str input str
     * @return frequency double arr
     */
    public static double[] frequency(String str){
        double letterFreq[] = new double[26];
        int[] count = count(str);
        str = str.replaceAll("[^a-zA-Z0-9]", ""); 
        for (int i = 0; i < letterFreq.length; i++){
            if (count[i] > 0){
                letterFreq[i] = (double) count[i] / str.length();  
            }
            
        }
        return letterFreq;
    }
    /**
     * Returns the chiSquared score of a given frequency and the standard english frequency.
     * @param givenFreq given observed frequency.
     * @param englishFreq standard english frequency.
     * @return chiSquared score.
     */
    public static double chiSquared(double[] givenFreq, double[] englishFreq){
        double chiSquared = 0;
        for (int i = 0; i < 26; i++){
            double numerator = givenFreq[i] - englishFreq[i];
            chiSquared += (numerator * numerator) / englishFreq[i];
        }
        return chiSquared; 
    }
}
