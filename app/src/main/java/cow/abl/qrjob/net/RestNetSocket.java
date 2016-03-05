package cow.abl.qrjob.net;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * REST request to the API.
 */
public class RestNetSocket {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client_ = new OkHttpClient();


    public void login(final String username, final String password, final ApiCallback callback) {
    }

    public void get(final String url, final ApiCallback callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = null;
                JSONObject resultJson = null;
                try {
                    response = client_.newCall(request).execute();
                    String responseStr = response.body().string();
                    Log.d("DEBUG", "### " + responseStr);

                    // Parse JSON
                    resultJson = new JSONObject(responseStr);

                } catch (IOException e) {
                    e.printStackTrace();
                    callback.onFailure("Network error :(");
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onFailure("Error: bad server response");
                }

                callback.onSuccess(resultJson);
            }
        }).start();
    }

    public void post(final String url, final Map<String, String> params, final ApiCallback callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                FormBody.Builder formBodyBuilder = new FormBody.Builder();
                for (Map.Entry<String, String> param : params.entrySet()) {
                    formBodyBuilder.add(param.getKey(), param.getValue());
                }

                RequestBody body = formBodyBuilder.build();

                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                Response response = null;

                JSONObject jsonObject = null;
                try {
                    Log.d("DEBUG", "### 0");
                    response = client_.newCall(request).execute();
                    Log.d("DEBUG", "### 1");
                    String responseStr = response.body().string();
                    Log.d("DEBUG", "### 2");

                    // Parse JSON response
                    jsonObject = new JSONObject(responseStr);
                    Log.d("DEBUG", "### 3");

                } catch (IOException e) {
                    e.printStackTrace();
                    callback.onFailure("Network error :(");
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onFailure("Error: bad server response");
                } finally {
                    callback.onSuccess(jsonObject);
                }
            }
        }).start();
    }
}
