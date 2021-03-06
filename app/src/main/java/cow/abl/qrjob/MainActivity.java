package cow.abl.qrjob;

import android.content.Intent;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import org.json.JSONException;
import org.json.JSONObject;

import cow.abl.qrjob.Fragment.OrganisationFragment;
import cow.abl.qrjob.Fragment.QRScanFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        QRCodeReaderView.OnQRCodeReadListener,
        QRScanFragment.OnFragmentInteractionListener,
        OrganisationFragment.OnFragmentInteractionListener {

    private String lastQrCodeRead_ = null;
    private String userId_ = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Default drawer code
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Default fragment : QR Scanner
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,
                new QRScanFragment(), "").commit();

        Intent myIntent = getIntent();
        userId_ = myIntent.getStringExtra("userId");
    }

    @Override
    public void onPause() {
        super.onPause();

        lastQrCodeRead_ = null;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Default fragment : QR Scanner
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,
                    new QRScanFragment(), "").commit();
        } else if (id == R.id.nav_slideshow) {
            Intent myIntent2 = new Intent(this, CVEditActivity.class);
            myIntent2.putExtra("userId", userId_);
            startActivity(myIntent2);
        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

        // Parse JSON from QR code
        try {
            if (lastQrCodeRead_!=null && lastQrCodeRead_.equals(text)) {
                return;
            } else {
                lastQrCodeRead_ = text;
            }
            JSONObject json = new JSONObject(text);

            String companyId = json.getString("id");
            String companyName = json.getString("name");
            String companyDescription = json.getString("description");

//            Intent myIntent = new Intent(this, JobDescriptionActivity.class);
//            myIntent.putExtra("companyId", companyId);
//            myIntent.putExtra("companyName", companyName);
//            myIntent.putExtra("companyDescription", companyDescription);
//            startActivity(myIntent);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, OrganisationFragment.newInstance(userId_, companyId, companyName, companyDescription)).commit();

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("JSON parsing", "Failed to parse the JSON from the QR code.");
        }
    }

    @Override
    public void cameraNotFound() {
        ((AppCompatTextView)this.findViewById(R.id.qrscantextview))
                .setText(R.string.camera_not_found);
    }

    @Override
    public void QRCodeNotFoundOnCamImage() {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
