package equals;

public class Pet {

    protected String name;

    public Pet(String name) {
        this.name = name;
    }

    public Pet(Pet other) {
        this.name = new String(other.name);
    }

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other.getClass() == getClass()) {
            Pet p = (Pet) other;
            return (p.name.equals(name));
        }
        return false;
    }

}
