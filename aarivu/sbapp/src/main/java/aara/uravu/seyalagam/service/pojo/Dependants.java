package aara.uravu.seyalagam.service.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dependants {

    private Person spouse;
    private Date marriageDate;
    private List<Person> children = new ArrayList<>();

    public Dependants(Person spouse, Date marriageDate) {
        this.spouse = spouse;
        this.marriageDate = marriageDate;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public Date getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(Date marriageDate) {
        this.marriageDate = marriageDate;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void addChildren(List<Person> children) {
        getChildren().addAll(children);
    }

    public void addChild(Person child) {
        getChildren().add(child);
    }
}
