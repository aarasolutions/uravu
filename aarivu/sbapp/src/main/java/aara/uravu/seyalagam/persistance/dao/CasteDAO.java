package aara.uravu.seyalagam.persistance.dao;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class CasteDAO extends BaseDAO{

    enum CasteLevel {
        MATHAM(5),SAMAYAM(4),JAATHI(3),JAATHI_ULPRIVU(2),NAADU(1);

        int level;

        CasteLevel(int level) {
            this.level = level;
        }
    }
    @Id
    @GeneratedValue
    private Long nodeId;
    private CasteLevel level;
    private String value;
    private String description;

    @Relationship(type = "CASTE_PART_OF", direction = Relationship.UNDIRECTED)
    private LocationDAO parent;

    public CasteDAO() {
        super(DBEnums.NodeType.CASTE);
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public CasteLevel getLevel() {
        return level;
    }

    public void setLevel(CasteLevel level) {
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
