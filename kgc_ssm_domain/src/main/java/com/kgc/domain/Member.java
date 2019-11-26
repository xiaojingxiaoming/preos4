package com.kgc.domain;

public class Member {
    private String id;
    private String name;
    private String nickname;
    private String phonenum;
    private String email;

    public Member() {
    }

    public Member(String id, String name, String nickname, String phonenum, String email) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.phonenum = phonenum;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
