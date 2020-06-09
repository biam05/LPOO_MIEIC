import java.util.Objects;

import static java.lang.String.valueOf;

public abstract class Person extends User implements Comparable<Person>{
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return name + valueOf(age);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int compareTo(Person person) {
        return 0;
    }

}
