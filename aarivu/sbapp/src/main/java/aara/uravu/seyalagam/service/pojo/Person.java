package aara.uravu.seyalagam.service.pojo;


import aara.uravu.seyalagam.persistance.validation.AppValidator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person extends AppNode implements AppValidator {

    private String name;
    private Gender gender;
    private boolean isAlive;
    private boolean isRoot;
    private Date birthDate;
    private Parents parents;
    private List<Dependants> dependants = new ArrayList<>();
    private List<Person> siblings = new ArrayList<>();


    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }

    public Parents getParents() {
        return parents;
    }

    public void setParents(Parents parents) {
        this.parents = parents;
    }

    public List<Dependants> getDependants() {
        return dependants;
    }

    public void setDependants(List<Dependants> dependants) {
        this.dependants = dependants;
    }

    public void addDependants(Dependants dependants){
        getDependants().add(dependants);
    }

    public void setSiblings(List<Person> siblings) {
        this.siblings = siblings;
    }

    public List<Person> getSiblings() {
        return siblings;
    }

    public void addSibling(Person sibling){
        getSiblings().add(sibling);
    }



    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", isAlive=" + isAlive +
                ", isRoot=" + isRoot +
                ", birthDate=" + birthDate +
                ", parents=" + parents +
                ", dependants=" + dependants +
                ", siblings=" + siblings +
                "} " + super.toString();
    }

    @Override
    public boolean validate() {
        return (getName() != null || getAppId() != null);
    }
}
