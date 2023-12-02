package HomeWork2ReflectionAPI;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Methods {

    public void showDeclaredFields(Animal animal) {

        Class<?> clazz = animal.getClass().getSuperclass();
        Class<?> clazz2 = animal.getClass();
        System.out.printf("%s %s -> %s\n", animal, clazz, clazz2);

        Field[] fields = clazz.getDeclaredFields();
        Field[] fields2 = clazz2.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            System.out.printf("поле %s\n", field.getName());
        }
        for (Field field : fields2) {
            field.setAccessible(true);
            System.out.printf("уникальное поле %s\n", field.getName());
        }

        Constructor[] constructors = clazz2.getConstructors();

        for (Constructor constructor : constructors) {
            Class[] paramTypes2 = constructor.getParameterTypes();
            System.out.print("конструктор объекта принимает ->  ");
            for (Class paramType : paramTypes2) {
                System.out.print(paramType.getName() + " ");
            }
        }
        System.out.println("\nМетоды класса: " + Arrays.toString(clazz2.getDeclaredMethods()));
    }

    public void makeSounds(Animal animal) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> animalClass = animal.getClass();

        Method methodAnimal = animalClass.getDeclaredMethod("makeSound");
        methodAnimal.invoke(animal);

    }

}
