package ru.job4j.generics;

public class Role extends Base{
    private String role;

    public Role(String id, String role) {
        super(id);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
