package aara.uravu.seyalagam.service.pojo;

public class AppNode {

    private String appId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "AppNode{" +
                "appId=" + appId +
                '}';
    }
}
