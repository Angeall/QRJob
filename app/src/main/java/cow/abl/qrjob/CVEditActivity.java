package cow.abl.qrjob;

import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cow.abl.qrjob.Fragment.ExperiencesFragment;
import cow.abl.qrjob.Fragment.FormationFragment;
import cow.abl.qrjob.Fragment.PersonalInformationFragment;
import cow.abl.qrjob.net.ApiCallback;
import cow.abl.qrjob.net.RestData;

public class CVEditActivity extends AppCompatActivity implements
        PersonalInformationFragment.OnFragmentInteractionListener,
        ExperiencesFragment.OnFragmentInteractionListener,
        FormationFragment.OnFragmentInteractionListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private String userID;
    private JSONObject cVJSON;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private PersonalInformationFragment personalInformationFragment = new PersonalInformationFragment();
    private FormationFragment formationFragment = new FormationFragment();
    private ExperiencesFragment experiencesFragment = new ExperiencesFragment();
    private JSONArray experiencesJSON;
    private JSONArray formationJSON;
    private String CVID;
    private boolean experiencesPushed = false;
    private boolean formationsPushed = false;
    private boolean broken = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvedit);
        userID = getIntent().getStringExtra("userId");

        //  DEFAULT
        userID = "18";
        //  /DEFAULT

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.cv_edit_tabs);
        tabLayout.setupWithViewPager(mViewPager);
        final RestData restData = new RestData();
        restData.getCV(userID, new ApiCallback() {
            @Override
            public void onSuccess(JSONObject msg) {
                try {
                    cVJSON = msg.getJSONObject("content");
                    CVID = cVJSON.getString("id");
                    restData.getExperiences(CVID, new ApiCallback() {
                        @Override
                        public void onSuccess(JSONObject msg) {
                            try {
                                experiencesJSON = msg.getJSONArray("content");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d("QRJob", "GET : " + msg.toString());
                        }

                        @Override
                        public void onFailure(String errorMsg) {
                            Log.d("QRJob", "FAILED" + errorMsg);
                        }
                    });
                    restData.getFormations(CVID, new ApiCallback() {
                        @Override
                        public void onSuccess(JSONObject msg) {
                            try {
                                formationJSON = msg.getJSONArray("content");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d("QRJob", "GET : " + msg.toString());
                        }

                        @Override
                        public void onFailure(String errorMsg) {

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("QRJob", "GET : " + cVJSON);
            }

            @Override
            public void onFailure(String errorMsg) {
                cVJSON = null;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cvedit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cv_edit_ok_button) {
            Log.d("QRJob", "To send");
            personalInformationFragment.pushCV();
        }
        else if(id == android.R.id.home) {
            Log.d("QRJob", "Back Pressed");
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public JSONObject getCVJSON(){
        return cVJSON;
    }

    public JSONArray getExperiencesJSON() {
        return experiencesJSON;
    }

    public JSONArray getFormationJSON() {
        return formationJSON;
    }

    public String getUserID(){
        return userID;
    }

    public void notifyCVPushed(String res) {
        CVID = res;
        Log.d("QRJob", "Result CVs : " + res);
        experiencesFragment.pushExperiences();
    }

    public void notifyExperiencesPushed(String res) {
        Log.d("QRJob", "Result Experiences : " + res);
        if(!(res == null) && res.equals("-1")) broken = true;
        if (!experiencesPushed){
            experiencesPushed = true;
            formationFragment.pushFormations();
        }
    }

    public void notifyFormationsPushed(String res) {
        Log.d("QRJob", "Result Formations : " + res);
        if(!(res == null) && res.equals("-1")) {
            broken = true;
            Snackbar.make(mViewPager, "Problème de connexion internet", Snackbar.LENGTH_SHORT);
        }
        else if (!formationsPushed){
            formationsPushed = true;
            if(!broken) runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("QRJob", "onBackPressed called");
                    CVEditActivity.this.onBackPressed();
                }
            });
            else Snackbar.make(mViewPager, "Problème de connexion internet", Snackbar.LENGTH_SHORT);
        }
    }

    public String getCVID() {
        return CVID;
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch(position){
                case 0:
                    return personalInformationFragment;
                case 1:
                    return experiencesFragment;
                case 2:
                    return formationFragment;
            }
            return new PersonalInformationFragment();
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.personnal_info);
                case 1:
                    return getString(R.string.experiences);
                case 2:
                    return getString(R.string.formations);
            }
            return null;
        }
    }
}
