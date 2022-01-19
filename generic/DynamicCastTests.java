package module_8.generic;
class Example{
    private String data;
    public Example(String data){
        this.data = data;
    }
    public Example(){
        this.data = "member of Example";
    }
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
class ExampleInheritant extends  Example{
    public ExampleInheritant(String data){
        super("This is ExampleInheritant data = " + data);
    }
    public ExampleInheritant(){
        super("Member of ExampleInheritant");
    }
}
public class DynamicCastTests {
    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Example();
        Object object3 = new ExampleInheritant();
        object1 = (ExampleInheritant) object1;
        object2 = (Example) object2;
        object3 = (ExampleInheritant) object3;
        System.out.println("((ExampleInheritant) object1).getData() = " + ((ExampleInheritant) object1).getData());
        System.out.println("((Example) object2).getData() = " + ((Example) object2).getData());
        System.out.println("((ExampleInheritant) object3).getData() = " + ((ExampleInheritant) object3).getData());


    }
}
