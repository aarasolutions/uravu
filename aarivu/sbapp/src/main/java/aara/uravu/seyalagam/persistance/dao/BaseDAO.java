package aara.uravu.seyalagam.persistance.dao;

public class BaseDAO {

    private String appId;
    private DBEnums.NodeType nodeType ;

    public BaseDAO(DBEnums.NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = nodeType.getKey()+ appId;
    }

    public DBEnums.NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(DBEnums.NodeType nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public String toString() {
        return "BaseDAO{" +
                "appId=" + appId +
                ", nodeType=" + nodeType +
                '}';
    }
}
