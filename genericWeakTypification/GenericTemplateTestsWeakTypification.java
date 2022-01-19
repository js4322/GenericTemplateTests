package module_8.genericWeakTypification;
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
        return "This instance has type " + this.getClass() + "<" /*+ member.getClass() + ">"*/;
    }
    /*public MetaClass(){
        member = new T();
    }*/
}
class MetaclassChild <T extends GrandFather & IsRed & IsGreen & IsBlue> extends MetaClass{
    /*public MetaclassChild(){
        member = new T();
    }*/
}
public class GenericTemplateTestsWeakTypification {
    public static void main(String[] args) {
        //MetaClass<Father> Sergiy = new Metaclass<Father>(); //is not within its bound; should implement 'module_8.genericWeakTypification.IsRed...
        //MetaClass<? extends GrandFather> woman = new MetaClass<ChildMykola>(); // Mykola does not implement colors
        MetaClass<? extends GrandFather> woman2 = new MetaClass<ChildNastya>(); // correct form
        //MetaClass<?  super Mother> mother = new MetaClass<ChildNastya>(); // bottom border of inheritance requirement is not met
        MetaClass<? super Mother> mother2 = new MetaClass<Mother>(); //correct form
        MetaClass<? super Mother> woman3 = new MetaClass<>();//Якого типу нащадок GrandFather ініціалізується? Чому тут нема помилки? //Бо він не ініціалізується. Якщо спробувати ініціалізувати всередині класу - то під час ініціалізації в програмі не визначений конкретний тип для якого має бути проведено ініціалізацію

        System.out.println("woman.toString() = " + woman2.toString());
        System.out.println("mother.toString() = " + mother2.toString());
        System.out.println("Why member.getClass() in string<20> does not work?"); //Чому при спробі запуску member.getClass() помилка? На етапі виконання тип має бути відомим і має викликатись  GrandFather.member.getClass() який наслідується всім нащадкам //На етапі виконання у цьому місці ще невідомо яким має бути тип Т
        System.out.println("Рядок 69. Чому це працює?");
        System.out.println("Чому не створюються інстанси GrandFather, чому не викликаються конструктори його і його нащадків?"); //Якщо їх явно не ініціалізувати то і не повинні. Якщо ініціалізувати - то ззовні, бо всередині нам поки-що невідомий їх тип
        String url = "https://miro.com/app/board/uXjVOU5a0SA=/";
        System.out.println("Висновок у тому що на момент створення інстанса MetaClass, MetaclassChild програмі невідомо який конкретно тип має бути підставлений в Т. Джава - строго типізована мова. Те що я намагався тут зробити - динамічна типізація (визначення типу в процесі виконання програми)");
        //--------------------------METACLASSCHILD TESTS
        class MetaChildTests{
            //MetaClass<? extends GrandFather> woman = new MetaclassChild<ChildMykola>(); // Mykola does not implement colors
            MetaClass<? extends GrandFather> woman2 = new MetaclassChild<ChildNastya>(); // correct form
            MetaClass<?  super Mother> mother = new MetaclassChild<ChildNastya>(); // bottom border of inheritance requirement is not met Чому це працює? //Схоже ідея навіть не чекає від програміста використання динамічної типізації
            MetaClass<? super Mother> mother2 = new MetaclassChild<Mother>(); //correct form
            MetaClass<? super Mother> woman3 = new MetaclassChild<>();//Якого типу нащадок GrandFather ініціалізується? Чому тут нема помилки?//Схоже ідея навіть не чекає від програміста використання динамічної типізації
        }
    }
}
