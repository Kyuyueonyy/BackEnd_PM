package org.scoula.demo.ex05.doman;

public class Member {
    private String name;
    private String userId;

    public Member() {

    }

    public Member(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
