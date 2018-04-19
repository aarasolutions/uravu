package aara.uravu.seyalagam.service.pojo;


import aara.uravu.seyalagam.persistance.validation.AppValidator;

import java.util.Date;

public class Marriage extends AppNode implements AppValidator {

    private Date marriageDate;
    private Person manamagan;
    private Person manamagal;

    public Person getManamagan() {
        return manamagan;
    }

    public void setManamagan(Person manamagan) {
        this.manamagan = manamagan;
    }

    public Person getManamagal() {
        return manamagal;
    }

    public void setManamagal(Person manamagal) {
        this.manamagal = manamagal;
    }

    @Override
    public String toString() {
        return "Marriage{" +
                "manamagan=" + manamagan +
                ", manamagal=" + manamagal +
                '}';
    }

    @Override
    public boolean validate() {
        return manamagal.validate() && manamagan.validate();
    }
}
