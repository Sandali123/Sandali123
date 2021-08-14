package com.example.cow.models;

public class Cow {

    private String tagNumber,name,sex,breed,dob,age,joinedWhen,howObtain,danTagNum,sireNameTag;


    public Cow() {
        this.tagNumber = tagNumber;
        this.name = name;
        this.sex = sex;
        this.breed = breed;
        this.dob = dob;
        this.age = age;
        this.joinedWhen = joinedWhen;
        this.howObtain = howObtain;
        this.danTagNum = danTagNum;
        this.sireNameTag = sireNameTag;
    }



    public String getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(String tagNumber) {
        this.tagNumber = tagNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getJoinedWhen() {
        return joinedWhen;
    }

    public void setJoinedWhen(String joinedWhen) {
        this.joinedWhen = joinedWhen;
    }

    public String getHowObtain() {
        return howObtain;
    }

    public void setHowObtain(String howObtain) {
        this.howObtain = howObtain;
    }

    public String getDanTagNum() {
        return danTagNum;
    }

    public void setDanTagNum(String danTagNum) {
        this.danTagNum = danTagNum;
    }

    public String getSireNameTag() {
        return sireNameTag;
    }

    public void setSireNameTag(String sireNameTag) {
        this.sireNameTag = sireNameTag;
    }
}
