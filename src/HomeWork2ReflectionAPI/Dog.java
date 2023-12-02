package HomeWork2ReflectionAPI;

public class Dog extends Animal{

    private boolean securityGuard; //способность охранять

    public Dog(String name, int age, boolean securityGuard) {
        super(name, age);
        this.securityGuard = securityGuard;
    }


    public void hunter(){
        System.out.println("Собака-охотник");
    }



    @Override
    void makeSound() {
        System.out.println("Это собака и она умеет делать Гааав-г...)))");
    }

    @Override
    public String toString() {
        return "Dog";
    }
}
