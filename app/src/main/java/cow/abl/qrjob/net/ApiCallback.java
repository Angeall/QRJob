package cow.abl.qrjob.net;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by lucas on 5/03/16.
 */
public interface ApiCallback {
    void onSuccess(JSONObject msg);
    void onFailure(String errorMsg);
}
