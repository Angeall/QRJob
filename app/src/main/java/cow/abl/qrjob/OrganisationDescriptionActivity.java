package cow.abl.qrjob;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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

/**
 * Created by helldog136 on 6/03/16.
 */
public class OrganisationDescriptionActivity extends AppCompatActivity{
        FloatingActionButton shareButton;
        FloatingActionButton saveButton;
        FloatingActionMenu actionMenu;
        TextView organisationNameTextView;
        TextView descriptionTextView;

        private String companyId_;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_organisation_description);
            Toolbar toolbar = (Toolbar) findViewById(R.id.job_description_toolbar);
            setSupportActionBar(toolbar);
            actionMenu = (FloatingActionMenu) findViewById(R.id.job_description_floating_menu);
            shareButton = (FloatingActionButton) actionMenu.findViewById(R.id.job_share_button);
            saveButton = (FloatingActionButton) actionMenu.findViewById(R.id.job_save_button);
            organisationNameTextView = (TextView) findViewById(R.id.organisation_name);
            descriptionTextView = (TextView) findViewById(R.id.organisation_description);

            companyId_ = getIntent().getStringExtra("companyId");

            companyId_ = "6";

            populateViews(companyId_);
            populateButtons();


        }

        private void populateButtons() {
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String shareBody = organisationNameTextView.getText()+" "+descriptionTextView.getText();
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "\n\n");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent,  "Partager..."));
                }
            });
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(saveButton, "Vous êtes désormais abonné aux offres d'emploi de cette entreprise.", Snackbar.LENGTH_SHORT).show();
                }
            });
        }

        private void populateViews(String companyId) {
            final RestData rest = new RestData();
            rest.getCompany(companyId, new ApiCallback() {
                @Override
                public void onSuccess(JSONObject msg) {
                    try {
                        msg = msg.getJSONObject("content");
                        organisationNameTextView.setText(msg.getString("name"));
                        descriptionTextView.setText(msg.getString("description"));
                    } catch (JSONException e) {
                        Log.e("OrgaDescriptionLoading", e.getMessage());
                    }
                }

                @Override
                public void onFailure(String errorMsg) {
                    Snackbar.make(descriptionTextView, "error importing information", Snackbar.LENGTH_SHORT).show();
                }
            });

        }

        private class DescriptionAdapter extends ArrayAdapter<JobDescription> {

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

