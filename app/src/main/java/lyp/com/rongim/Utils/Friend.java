package lyp.com.rongim.Utils;

/**
 * Created by liyp on 18-12-14.
 */

public class Friend {

    private String userId;
    private String userName;
    private String portraitUri;

    public Friend(String userId, String userName, String portraitUrl) {
        this.userId = userId;
        this.userName = userName;
        this.portraitUri = portraitUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPortraitUri() {
        return portraitUri;
    }

    public void setPortraitUri(String portraitUrl) {
        this.portraitUri = portraitUrl;
    }
}
