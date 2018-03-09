
import java.util.Random;

public class Die {

    private String[] side;
    private boolean isUsed;
    private boolean usedInWord;

    /**
     * @param side
     */
    public Die(String[] side, boolean isUsed) {
        super();
        this.isUsed = isUsed;
        this.side = new String[side.length];
        for (int i = 0; i < side.length; i++) {
            this.side[i] = new String(side[i]);
        }
    }

    /**
     * @return the isUsed
     */
    public boolean isUsed() {
        return isUsed;
    }

    /**
     * @param isUsed the isUsed to set
     */
    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Die(Die die) {
        side = die.side;
        isUsed = die.isUsed;
    }

    /**
     * @return the side
     */
    public String[] getSide() {
        return side;
    }

    /**
     * @param side the side to set
     */
    public void setSide(String[] side) {
        this.side = side;
    }

    public String rollDie() {
        Random rand = new Random();
        int randSide = rand.nextInt(6);

        return side[randSide];
    }

}
