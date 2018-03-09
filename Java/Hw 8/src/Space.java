
public class Space {

    private String letter;
    private boolean used;

    /**
     * @param letter
     * @param used
     */
    public Space(String letter, boolean used) {
        super();
        this.letter = letter;
        this.used = used;
    }

    public Space(Space spaceC) {
        letter = spaceC.letter;
        used = spaceC.used;
    }

    /**
     * @return the letter
     */
    public String getLetter() {
        return letter;
    }

    /**
     * @param letter the letter to set
     */
    public void setLetter(String letter) {
        this.letter = letter;
    }

    /**
     * @return the used
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * @param used the used to set
     */
    public void setUsed(boolean used) {
        this.used = used;
    }

}
