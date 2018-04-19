package aara.uravu.seyalagam.persistance.dao;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

public class LocationDAO extends BaseDAO{

    public LocationDAO() {
        super(DBEnums.NodeType.LOCATION);
    }

    enum LocLevel{
        CONTINENT(7),COUNTRY(6),STATE(5),CITY(4),LOCALITY(3),AREA(2),DOOR(1);

        int level;

        LocLevel(int level) {
            this.level = level;
        }
    }

    @Id
    @GeneratedValue
    private Long nodeId;
    private LocLevel level;
    private String value;
    private String description;

    @Relationship(type = "LOC_PART_OF", direction = Relationship.UNDIRECTED)
    private LocationDAO parent;

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public LocLevel getLevel() {
        return level;
    }

    public void setLevel(LocLevel level) {
        this.level = level;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocationDAO getParent() {
        return parent;
    }

    public void setParent(LocationDAO parent) {
        this.parent = parent;
    }
}
