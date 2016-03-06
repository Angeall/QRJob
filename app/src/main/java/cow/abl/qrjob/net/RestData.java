package cow.abl.qrjob.net;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * REST request to the API.
 */
public class RestData {
    private OkHttpClient client_ = new OkHttpClient();
    private static final String apiUrl = "http://restful-api.eu-gb.mybluemix.net";
    private static final String loginUrl = "/login";
    private static final String createUserUrl = "/users/create";
    private static final String getJobOfferUrl = "/offers/";
    private static final String getUserUrl = "/users/";
    private static final String getCompanyUrl = "/companies/";
    private static final String getCompanyOffersUrl = "/companies/%s/offers";
    private static final String getApplicationUrl = "/applications/";
    private static final String getCVUrl = "/cvs/";

    /*
    POST QRjob requests.
     */

    public void login(final String username, final String password, final ApiCallback callback) {
        Map<String, String> loginParams = new HashMap<>(2);
        loginParams.put("mail", username);
        loginParams.put("password", password);

        post(apiUrl + loginUrl, loginParams, callback);
    }

    public void createUser(final String username, final String password, final String name, final ApiCallback callback) {
        Map<String, String> params = new HashMap<>(2);
        params.put("mail", username);
        params.put("password", password);
        params.put("name", name);

        post(apiUrl + createUserUrl, params, callback);
    }

    /*
    GET QRjob requests.
     */

    public void getJobOffer(final String id, final ApiCallback callback) {
        get(apiUrl + getJobOfferUrl + id, callback);
    }

    public void getUser(final String id, final ApiCallback callback) {
        get(apiUrl + getUserUrl + id, callback);
    }

    public void getCompany(final String id, final ApiCallback callback) {
        get(apiUrl + getCompanyUrl + id, callback);
    }

    public void getCompanyOffers(final String companyId, final ApiCallback callback) {
        get(apiUrl + String.format(getCompanyOffersUrl, companyId), callback);
    }

    public void getApplication(final String id, final ApiCallback callback) {
        get(apiUrl + getApplicationUrl + id, callback);
    }

    public void getCV(final String id, final ApiCallback callback) {
        get(apiUrl + getCVUrl + id, callback);
    }

    /*
    REST requests.
     */

    private void get(final String url, final ApiCallback callback) {

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

                    // Parse JSON
                    resultJson = new JSONObject(responseStr);

                } catch (IOException e) {
                    e.printStackTrace();
                    callback.onFailure("Network error :(");
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onFailure("Error: bad server response");
                } finally {
                    callback.onSuccess(resultJson);
                }
            }
        }).start();
    }

    private void post(final String url, final Map<String, String> params, final ApiCallback callback) {

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
                    response = client_.newCall(request).execute();
                    String responseStr = response.body().string();

                    // Parse JSON response
                    jsonObject = new JSONObject(responseStr);

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
