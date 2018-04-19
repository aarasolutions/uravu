package aara.uravu.seyalagam.service.pojo;

import java.util.Date;

public class Parents {
    private Person appa;
    private Person amma;
    private Date parentsMarriageDate;

    public Parents(Person appa, Person amma, Date parentsMarriageDate) {
        this.appa = appa;
        this.amma = amma;
        this.parentsMarriageDate = parentsMarriageDate;
    }

    public Person getAmma() {
        return amma;
    }

    public void setAmma(Person amma) {
        this.amma = amma;
    }

    public Date getParentsMarriageDate() {
        return parentsMarriageDate;
    }

    public void setParentsMarriageDate(Date parentsMarriageDate) {
        this.parentsMarriageDate = parentsMarriageDate;
    }

    public Person getAppa() {

        return appa;
    }

    public void setAppa(Person appa) {
        this.appa = appa;
    }
}
