package cow.abl.qrjob;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import org.json.JSONException;
import org.json.JSONObject;

import cow.abl.qrjob.net.ApiCallback;
import cow.abl.qrjob.net.RestData;

public class JobDescriptionActivity extends AppCompatActivity {
    FloatingActionButton shareButton;
    FloatingActionButton cvButton;
    FloatingActionButton saveButton;
    FloatingActionMenu actionMenu;
    TextView organisationNameTextView;
    TextView dateTextView;
    TextView descriptionTextView;

    private String companyId_;
    private String jobOfferId_;
    TextView typeTextView;
    private String userID_;
    ActionBar supportActionBar;
    Toolbar bar;
    CollapsingToolbarLayout lay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_description);
        bar = (Toolbar) findViewById(R.id.job_description_toolbar);
        setSupportActionBar(bar);
        actionMenu = (FloatingActionMenu) findViewById(R.id.job_description_floating_menu);
        shareButton = (FloatingActionButton) actionMenu.findViewById(R.id.job_share_button);
        cvButton = (FloatingActionButton) actionMenu.findViewById(R.id.job_cv_button);
        saveButton = (FloatingActionButton) actionMenu.findViewById(R.id.job_save_button);
        organisationNameTextView = (TextView) findViewById(R.id.job_organisation_name);
        typeTextView = (TextView) findViewById(R.id.job_type);
        dateTextView = (TextView) findViewById(R.id.job_date);
        descriptionTextView = (TextView) findViewById(R.id.job_description);
        supportActionBar = getSupportActionBar();
        lay = (CollapsingToolbarLayout) findViewById(R.id.job_description_toolbar_layout);
        supportActionBar.setDisplayHomeAsUpEnabled(true); //TODO : listener du bouton en haut a gauche

        jobOfferId_ = getIntent().getStringExtra("jobOfferId");
        userID_     = getIntent().getStringExtra("user_id");
        userID_     = "6";

        jobOfferId_ = "6";

        Log.i("after", String.valueOf(supportActionBar.getTitle()));
        populateButtons();
        populateViews(jobOfferId_);
    }

    private void populateButtons() {
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareBody = organisationNameTextView.getText()+" propose un emploi de "+
                        supportActionBar.getTitle()+" à partir du "+dateTextView.getText()+
                        "\nDescription : "+descriptionTextView.getText();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "\n\n");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent,  "Partager..."));
            }
        });
        cvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestData rest = new RestData();
                rest.applyToJob(userID_,jobOfferId_, new ApiCallback() {
                    @Override
                    public void onSuccess(JSONObject msg) {
                        Snackbar.make(saveButton, "CV envoyé!", Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(String errorMsg) {
                        Snackbar.make(saveButton, "Erreur lors de l'envoi du CV!", Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar.make(saveButton, "Offre d'emploi sauvegardée pour plus tard...", Snackbar.LENGTH_SHORT).show();

                Snackbar.make(saveButton, supportActionBar.getTitle(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void setToolbarTitle(String title){
        supportActionBar.setTitle(title);
        bar.setTitle(title);
        lay.setTitle(title);
    }

    private void populateViews(String jobOfferId) {
        final RestData rest = new RestData();
        rest.getJobOffer(jobOfferId, new ApiCallback() {
            @Override
            public void onSuccess(JSONObject msg) {
                try {
                    msg = msg.getJSONObject("content");
                    final JSONObject finalMsg = msg;
                    JobDescriptionActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JobDescriptionActivity.this.setToolbarTitle(finalMsg.getString("title"));
                                Log.i("getTitle", String.valueOf(supportActionBar.getTitle()));
                                dateTextView.setText(finalMsg.getString("date"));
                                descriptionTextView.setText(finalMsg.getString("description"));
                            } catch (JSONException e) {
                                Log.e("JobDescriptionActivity", e.getMessage());
                            }
                        }
                    });

                    companyId_ = msg.getString("company_id");
                    if(companyId_ != null){
                        rest.getCompany(companyId_, new ApiCallback() {
                            @Override
                            public void onSuccess(JSONObject msg) {
                                try {
                                    msg = msg.getJSONObject("content");
                                    final JSONObject finalMsg = msg;
                                    JobDescriptionActivity.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                organisationNameTextView.setText(finalMsg.getString("name"));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                } catch (JSONException e) {
                                    Log.e("JobDescriptionLoading", e.getMessage());
                                }
                            }

                            @Override
                            public void onFailure(String errorMsg) {
                                Snackbar.make(organisationNameTextView, "error importing information", Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    Log.e("JobDescriptionLoading", e.getMessage());
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                Snackbar.make(descriptionTextView, "error importing information", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    private class DescriptionAdapter extends ArrayAdapter<JobDescription>{

        public DescriptionAdapter(Context context, int resource, JobDescription[] objects) {
            super(context, resource, objects);
        }
    }

    private class JobDescription {
        String title;
        String description;

        public JobDescription(String title, String description){
            this.title = title;
            this.description = description;
        }
    }
}
