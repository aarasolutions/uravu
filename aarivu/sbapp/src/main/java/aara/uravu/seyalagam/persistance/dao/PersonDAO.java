package aara.uravu.seyalagam.persistance.dao;


import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class PersonDAO extends BaseDAO{

    @Id
    @GeneratedValue
    private Long nodeId;

    private String name;
    private String gender;
    private boolean isAlive;
    private boolean isRoot;
    private Date birthDate;
    private CasteDAO jaathi;

    public PersonDAO() {
       super(DBEnums.NodeType.PERSON);
    }

    ;


    /**
     * Neo4j doesn't REALLY have bi-directional relationships. It just means when querying
     * to ignore the direction of the relationship.
     * https://dzone.com/articles/modelling-data-neo4j
     */
    @Relationship(type = "FAMILY", direction = Relationship.UNDIRECTED)
    public Set<PersonDAO> family;

    public void worksWith(PersonDAO person) {
        if (family == null) {
            family = new HashSet<>();
        }
        family.add(person);
    }

    @Relationship(type="CHILD",direction = Relationship.UNDIRECTED)
    public MarriageDAO bornFor;


    @Override
    public String toString() {
        return "PersonDAO{" +
                "nodeId=" + nodeId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", isAlive=" + isAlive +
                ", isRoot=" + isRoot +
                ", family=" + family +
                "} " + super.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    public MarriageDAO getBornFor() {
        return bornFor;
    }

    public void setBornFor(MarriageDAO bornFor) {
        this.bornFor = bornFor;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Set<PersonDAO> getFamily() {
        return family;
    }

    public void setFamily(Set<PersonDAO> family) {
        this.family = family;
    }
}