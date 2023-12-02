package HomeWork2ReflectionAPI;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Program {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        List<Animal> animals = new ArrayList<>();

        animals.add(new Cat("Masy", 5, true));
        animals.add(new Dog("Cheyza", 15, true));
        animals.add(new Dog("Max", 10, true));

        Methods methods = new Methods();
        animals.stream().forEach(animal -> {
            methods.showDeclaredFields(animal); // метод вывод полей
            try {
                methods.makeSounds(animal);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            System.out.println("----------------");
        });






       }
}
