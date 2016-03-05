package cow.abl.qrjob;

import android.content.Context;
import android.os.Bundle;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class JobDescriptionActivity extends AppCompatActivity {
    FloatingActionButton shareButton;
    FloatingActionButton cvButton;
    FloatingActionButton saveButton;
    FloatingActionMenu actionMenu;
    ListViewCompat listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.job_description_toolbar);
        setSupportActionBar(toolbar);
        actionMenu = (FloatingActionMenu) findViewById(R.id.job_description_floating_menu);
        cvButton = (FloatingActionButton) actionMenu.findViewById(R.id.job_cv_button);
        shareButton = (FloatingActionButton) actionMenu.findViewById(R.id.job_share_button);
        saveButton = (FloatingActionButton) actionMenu.findViewById(R.id.job_save_button);
        listView = (ListViewCompat) findViewById(R.id.job_description_list_view);
        getSupportActionBar().setTitle("##JOB_TITLE");



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
