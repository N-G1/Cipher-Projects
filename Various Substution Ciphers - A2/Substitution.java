public abstract class Substitution implements Cipher{
    /**
     * Handled by string encrypt for individual character encryption.
     * @param c character
     * @return encrypted character
     */
    public abstract char encrypt(char c);
    /**
     * Handled by string decrypt for individual character decryption.
     * @param c character
     * @return decrypted character.
     */
    public abstract char decrypt(char c);

    /**
     * Makes use of the char encrypt function to encrypt a whole string
     * 1 char at time, return and rebuild.
     * @param plainText inputted plain text
     * @return Encrypted string
     **/
    public String encrypt(String plainText){
        char currChar;
        String newStr = "";

        for (int i= 0; i< plainText.length(); i++){
            currChar = encrypt(plainText.charAt(i));
            newStr += currChar;
        } 
        return newStr;
    }
    /**
     * Makes use of the char decrypt function to decrypt a whole string 
     * 1 char at a time, return and rebuild.
     * @param cryptoText inputted crypto text
     * @return Decrypted string
     **/
    public String decrypt(String cryptoText){
        char currChar;
        String newStr = "";

        for (int i = 0; i < cryptoText.length(); i ++){
            currChar = decrypt(cryptoText.charAt(i));
            newStr += currChar;
        }
        return newStr;
    }


}
