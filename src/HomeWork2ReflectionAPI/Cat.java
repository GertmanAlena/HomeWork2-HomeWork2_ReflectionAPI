package HomeWork2ReflectionAPI;

public class Cat extends Animal{

    private boolean catchesMice; //способность ловить мышей

    public Cat(String name, int age, boolean catchesMice) {
        super(name, age);
        this.catchesMice = catchesMice;
    }

    public void lazy(){
        System.out.println("Кот-лентяй");
    }

    @Override
    public String toString() {
        return "Cat ";
    }

    @Override
    void makeSound() {
        System.out.println("Это котик и он умеет делать Мяяяууу....");
    }
}
