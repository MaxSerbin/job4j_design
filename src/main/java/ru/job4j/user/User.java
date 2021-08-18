package ru.job4j.user;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;


    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", children=" + children +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        User user1 = new User("Alex", 3, new GregorianCalendar(1975, Calendar.APRIL, 5));
        User user2 = new User("Alex", 3, new GregorianCalendar(1975, Calendar.APRIL, 5));
        Map<User,Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        for (User i : map.keySet()) {
            System.out.println(i);
            System.out.println(Objects.hashCode(i));
        }
    }
}
