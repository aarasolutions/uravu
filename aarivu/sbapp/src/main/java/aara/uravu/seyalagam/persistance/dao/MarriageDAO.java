package aara.uravu.seyalagam.persistance.dao;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.List;

@NodeEntity
public class MarriageDAO extends BaseDAO{

    @Id
    @GeneratedValue
    private Long nodeId;
    private Date eventDate;

    public MarriageDAO() {
        super(DBEnums.NodeType.EVENT_MARRIAGE);
    }

    @Relationship(type = "MAPPILAI", direction = Relationship.UNDIRECTED)
    private PersonDAO mappilai;

    @Relationship(type = "PONNU", direction = Relationship.UNDIRECTED)
    private PersonDAO ponnu;

    @Relationship(type = "CHILD", direction = Relationship.UNDIRECTED)
    private List<PersonDAO> children;


    public PersonDAO getMappilai() {
        return mappilai;
    }

    public void setMappilai(PersonDAO mappilai) {
        this.mappilai = mappilai;
    }

    public PersonDAO getPonnu() {
        return ponnu;
    }

    public void setPonnu(PersonDAO ponnu) {
        this.ponnu = ponnu;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public List<PersonDAO> getChildren() {
        return children;
    }

    public void setChildren(List<PersonDAO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "MarriageDAO{" +
                "nodeId=" + nodeId +
                ", mappilai=" + mappilai +
                ", ponnu=" + ponnu +
                "} " + super.toString();
    }
}
