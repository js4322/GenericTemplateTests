package module_8.generic;
interface IsRed{}
interface IsGreen{}
interface IsBlue{}
abstract class GrandFather{
    @Override
    public String toString(){
        return "This instance has type " + this.getClass();
    }
    public GrandFather(){
        System.out.println("GrandFather constructor");
    }
}
class Father extends GrandFather{
    public Father(){
        System.out.println("Father constructor");
    }
}
class Mother extends GrandFather implements IsRed, IsGreen, IsBlue{
    public Mother(){
        System.out.println("Mother constructor");
    }
}
class ChildMykola extends  Father{
    public ChildMykola(){
        System.out.println("ChildMykola constructor");
    }
}
class ChildNastya extends  Mother{
    public ChildNastya(){
        System.out.println("ChildNastya constructor");
    }
}

class MetaClass <T extends GrandFather & IsRed & IsGreen & IsBlue>{//extend classes and interfaces //probably should  be only Mother and ChildNastya //operator ? wildcard is not usable in describing generics/templates (only in usage)
    T member;
    @Override
    public String toString(){
        return "This instance has type " + this.getClass() + "<" + member.getClass() + ">";
    }
    public MetaClass(T copyOfInstance){
        member = copyOfInstance;
    }
}
class MetaclassChild <T extends GrandFather & IsRed & IsGreen & IsBlue> extends MetaClass{
    public MetaclassChild(T copyOfInstance){
        super(copyOfInstance);
    }
}

public class GenericTemplateTests {
    public static void main(String[] args) {
        //MetaClass<Father> Sergiy = new MetaClass<Father>(new Father()); //is not within its bound; should implement 'module_8.genericWeakTypification.IsRed...
       //MetaClass<? extends GrandFather> woman = new MetaClass<ChildMykola>(new ChildMykola()); // Mykola does not implement colors
        MetaClass<? extends GrandFather> woman2 = new MetaClass<ChildNastya>(new ChildNastya()); // correct form
        //MetaClass<?  super Mother> mother = new MetaClass<ChildNastya>(new ChildNastya()); // bottom border of inheritance requirement is not met
        MetaClass<? super Mother> mother2 = new MetaClass<Mother>(new Mother()); //correct form
        MetaClass<? super Mother> woman3 = new MetaClass<>(new Mother());//нащадок GrandFather ініціалізується того типу, який ми йому передамо Чому тут не було помилки? Бо інстанс Mother не ініціалізувався (це було неможливо через використання слабкої типізації)

        System.out.println("woman.toString() = " + woman2.toString());
        System.out.println("mother.toString() = " + mother2.toString());

        //System.out.println("Why member.getClass() in string<20> does not work?"); //Чому при спробі запуску member.getClass() помилка? На етапі виконання тип має бути відомим і має викликпатись  GrandFather.member.getClass() який наслідується всім нащадкам
        //Якщо не старатись використати динамічну типізацію, яку джава не підтримує, все працює
        System.out.println("Рядок 74. Чому це працює?");
        String url = "https://miro.com/app/board/uXjVOU5a0SA=/";
       //--------------------------METACLASSCHILD TESTS
        MetaClass<?  super Mother> mother3 = new MetaclassChild<ChildNastya>(new ChildNastya()); // bottom border of inheritance requirement is not met Чому це працює? -- Актуальне питання
        System.out.println("mother3.toString() = " + mother3.toString());
        MetaClass<? extends ChildNastya> wildChild = new MetaclassChild<Mother>(new Mother()); // bottom border of inheritance requirement is not met Чому це працює? -- Актуальне питання
        System.out.println("wildChild.toString() = " + wildChild.toString());
        /*
        class MetaChildTests{
            //MetaClass<? extends GrandFather> woman = new MetaclassChild<ChildMykola>(new ChildMykola()); // Mykola does not implement colors
            MetaClass<? extends GrandFather> woman2 = new MetaclassChild<ChildNastya>(new ChildNastya()); // correct form
            MetaClass<?  super Mother> mother = new MetaclassChild<ChildNastya>(new ChildNastya()); // bottom border of inheritance requirement is not met Чому це працює? -- Актуальне питання
            MetaClass<? super Mother> mother2 = new MetaclassChild<Mother>(new Mother()); //correct form
            MetaClass<? super Mother> woman3 = new MetaclassChild<>(new Mother());//Якого типу нащадок GrandFather ініціалізується? Той який туди передамо
        }

         */
    }
}
