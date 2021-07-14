package ru.job4j.generics;

public class User extends Base {
    private String name;

    public User(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
