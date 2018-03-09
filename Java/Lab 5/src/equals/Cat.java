package equals;

public class Cat extends Pet {

    private boolean longhair;

    public Cat(String name, boolean longhair) {
        super(name);

        this.longhair = longhair;
    }

    public Cat(Cat other) {
        super(other);
        this.longhair = other.longhair;
    }

    public boolean isLonghair() {
        return longhair;
    }

    public void setLonghair(boolean longhair) {
        this.longhair = longhair;
    }

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other.getClass() == getClass()) {
            Cat c = (Cat) other;
            return (super.equals(c) && (c.longhair == longhair));
        } else {
            return false;
        }
    }

}
