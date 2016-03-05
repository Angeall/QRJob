package cow.abl.qrjob.net;

/**
 * Created by lucas on 5/03/16.
 */
public class LoginRestRequest {

    private ApiCallback callback_ = null;

    public LoginRestRequest(final String username, final String password) {

    }

    public void setCallback(final ApiCallback callback) {
        callback_ = callback;
    }
}
