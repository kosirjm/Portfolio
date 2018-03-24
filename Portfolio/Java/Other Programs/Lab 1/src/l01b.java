/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;
import java.util.ArrayList;
/**
 *
 * @author kosirjm
 */


public class l01b {

    public void evenRemover(ArrayList<Integer> value)
{
    for(int i = 0; i < value.size(); i++)
    {
        if(value.get(i) % 2 == 0)
        {
            value.remove(i);
        }
    }
}

     public void removeEvenValues2(ArrayList<Integer> value)
{
    for(int i = value.size(); i < 0; i--)
    {
        if(value.get(i) % 2 == 0)
        {
            value.remove(i);
        }
}

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    l01b lab = new l01b();
    Random rand = new Random();
    int howMany = 200;
    ArrayList<Integer> arrayList = new ArrayList<Integer>(10);

    for(int i = 0; i < howMany; i++)
    {
        arrayList.add(rand.nextInt(99));
        System.out.print(arrayList.get(i) + ", ");
        System.out.println(arrayList.size());
    }
    lab.removeEvenValues2(arrayList);
    }

}
