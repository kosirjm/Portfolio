package equals;

public class Dog extends Pet {

    private boolean isToy;

    public Dog(String name, boolean toy) {
        super(name);
        isToy = toy;
    }

    public Dog(Dog other) {
        super(other);
        this.isToy = other.isToy;
    }

    public boolean isToy() {
        return isToy;
    }

    public void setToy(boolean isToy) {
        this.isToy = isToy;
    }

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other.getClass() == getClass()) {
            Dog d = (Dog) other;
            return (super.equals(d) && (d.isToy == isToy));
        } else {
            return false;
        }
    }

}
