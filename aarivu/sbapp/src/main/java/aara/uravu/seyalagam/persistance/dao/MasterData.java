package aara.uravu.seyalagam.persistance.dao;

import java.util.List;

public class MasterData {
    private List<LocationDAO> locList;
    private List<CasteDAO> casteList;

    public List<LocationDAO> getLocList() {
        return locList;
    }

    public void setLocList(List<LocationDAO> locList) {
        this.locList = locList;
    }

    public List<CasteDAO> getCasteList() {
        return casteList;
    }

    public void setCasteList(List<CasteDAO> casteList) {
        this.casteList = casteList;
    }
}


