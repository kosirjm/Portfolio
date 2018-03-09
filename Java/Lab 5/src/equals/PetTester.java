package equals;

public class PetTester {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Pet p1 = new Pet("Fluffy");
        Pet pet;
        Cat c1 = new Cat("Sam", false);
        Cat c2 = new Cat("Sam", false);
        Cat c3 = new Cat("Sam", true);
        Dog d1 = new Dog("Fluffy", false);
        pet = new Cat(c3);

        System.out.println("p1 equal to c1? " + p1.equals(c1));
        System.out.println("p1 equal to c2? " + p1.equals(c2));
        System.out.println("p1 equal to c3? " + p1.equals(c3));
        System.out.println("p1 equal to d1? " + p1.equals(d1));
        System.out.println("p1 equal to pet? " + p1.equals(pet));
        System.out.println();

        System.out.println("c1 equal to p1? " + c1.equals(p1));
        System.out.println("c1 equal to c2? " + c1.equals(c2));
        System.out.println("c1 equal to c3? " + c1.equals(c3));
        System.out.println("c1 equal to d1? " + c1.equals(d1));
        System.out.println("c1 equal to pet? " + c1.equals(pet));
        System.out.println();

        System.out.println("c2 equal to p1? " + c2.equals(p1));
        System.out.println("c2 equal to c1? " + c2.equals(c1));
        System.out.println("c2 equal to c3? " + c2.equals(c3));
        System.out.println("c2 equal to d1? " + c2.equals(d1));
        System.out.println("c2 equal to pet? " + c2.equals(pet));
        System.out.println();

        System.out.println("c3 equal to p1? " + c3.equals(p1));
        System.out.println("c3 equal to c1? " + c3.equals(c1));
        System.out.println("c3 equal to c2? " + c3.equals(c2));
        System.out.println("c3 equal to d1? " + c3.equals(d1));
        System.out.println("c3 equal to pet? " + c3.equals(pet));
        System.out.println();

        System.out.println("d1 equal to p1? " + d1.equals(p1));
        System.out.println("d1 equal to c1? " + d1.equals(c1));
        System.out.println("d1 equal to c2? " + d1.equals(c2));
        System.out.println("d1 equal to c3? " + d1.equals(c3));
        System.out.println("d1 equal to pet? " + d1.equals(pet));
        System.out.println();

        System.out.println("pet equal to p1? " + pet.equals(p1));
        System.out.println("pet equal to c1? " + pet.equals(c1));
        System.out.println("pet equal to c2? " + pet.equals(c2));
        System.out.println("pet equal to c3? " + pet.equals(c3));
        System.out.println("pet equal to d1? " + pet.equals(d1));
        System.out.println();

        Pet[] nullPet = new Pet[1];
        System.out.println("pet equal to nullPet[0]? " + pet.equals(nullPet[0]));
        System.out.println("c1 equal to nullPet[0]? " + c1.equals(nullPet[0]));
        System.out.println("c2 equal to nullPet[0]? " + c2.equals(nullPet[0]));
        System.out.println("c3 equal to nullPet[0]? " + c3.equals(nullPet[0]));
        System.out.println("d1 equal to nullPet[0]? " + d1.equals(nullPet[0]));
        System.out.println("p1 equal to nullPet[0]? " + p1.equals(nullPet[0]));

    }

}
