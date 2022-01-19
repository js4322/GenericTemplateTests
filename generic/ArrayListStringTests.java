package module_8.generic;

import java.util.ArrayList;
import java.util.List;

public class ArrayListStringTests {
    public static void main(String[] args) {
        List<String> planets = new ArrayList<>();// List planets кутові дужки можуть бути відсутні бо для кожного інстанса з яким буде працювати List його тип буде визначатись в момент передачі. І це ознака неправильного коду, бодана колекція групує певні об'єкти, логічно що вони мають буту об'єднаними одним батьківським класом чи інтерфейсом
        //Але якщо написати List<String> planets = new ArrayList<String>(); то цим явно вказується загальне обмеження
        //List<String> planets = new ArrayList<String>(); == List<String> planets = new ArrayList<>(); У ArrayList може бути тільки 1 тип, тому джава може підставляти його автоматично
        planets.add("Earth");
        planets.add("Mars");
        planets.add("Venus");
        planets.add(2, "not Mars");

        for (String planet : planets) {
            System.out.println(planet); //Earth, Mars, not Mars, Venus
        }
        //___________________________________
        System.out.println("\nuntyped planets\n");
        List planets2 = new ArrayList();
        planets2.add(1);
        planets2.add("Earth");
        planets2.add("Mars");
        planets2.add("Venus");
        planets2.add(2, "not Mars");

        try {//Помилка яку і відслідкувати нормально не можна (цикл не виводить planets2.get(0))
            for (String planet : planets) {
                System.out.println(planet); //Earth, Mars, not Mars, Venus
            }
        }
        catch(Throwable e){
            System.out.println("недопустиме приведення типів" + e.getStackTrace());

        }
        System.out.println("planets2.get(1) = " + planets2.get(0));
    }
}
