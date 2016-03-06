package cow.abl.qrjob.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cow.abl.qrjob.CVEditActivity;
import cow.abl.qrjob.R;
import cow.abl.qrjob.net.ApiCallback;
import cow.abl.qrjob.net.RestData;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExperiencesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExperiencesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExperiencesFragment extends Fragment {
    private AppCompatEditText experience1title;
    private AppCompatEditText experience2title;
    private AppCompatEditText experience3title;
    private AppCompatEditText experience4title;
    private AppCompatEditText experience5title;
    private AppCompatEditText experience6title;
    private AppCompatEditText experience7title;
    private AppCompatEditText experience8title;
    private AppCompatEditText experience9title;
    private AppCompatEditText experience10title;
    private AppCompatEditText experience1desc;
    private AppCompatEditText experience2desc;
    private AppCompatEditText experience3desc;
    private AppCompatEditText experience4desc;
    private AppCompatEditText experience5desc;
    private AppCompatEditText experience6desc;
    private AppCompatEditText experience7desc;
    private AppCompatEditText experience8desc;
    private AppCompatEditText experience9desc;
    private AppCompatEditText experience10desc;
    private AppCompatEditText startdate1;
    private AppCompatEditText startdate2;
    private AppCompatEditText startdate3;
    private AppCompatEditText startdate4;
    private AppCompatEditText startdate5;
    private AppCompatEditText startdate6;
    private AppCompatEditText startdate7;
    private AppCompatEditText startdate8;
    private AppCompatEditText startdate9;
    private AppCompatEditText startdate10;
    private AppCompatEditText enddate1;
    private AppCompatEditText enddate2;
    private AppCompatEditText enddate3;
    private AppCompatEditText enddate4;
    private AppCompatEditText enddate5;
    private AppCompatEditText enddate6;
    private AppCompatEditText enddate7;
    private AppCompatEditText enddate8;
    private AppCompatEditText enddate9;
    private AppCompatEditText enddate10;
    private ArrayList<AppCompatEditText> experienceTitles = new ArrayList<>();
    private ArrayList<AppCompatEditText> experienceDescs = new ArrayList<>();
    private ArrayList<AppCompatEditText> startDates = new ArrayList<>();
    private ArrayList<AppCompatEditText> endDates = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private OnFragmentInteractionListener mListener;
    private int i;

    public ExperiencesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExperiencesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExperiencesFragment newInstance(String param1, String param2) {
        ExperiencesFragment fragment = new ExperiencesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_experiences, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        experience1title = (AppCompatEditText) getView().findViewById(R.id.experience1title);
        experience2title = (AppCompatEditText) getView().findViewById(R.id.experience2title);
        experience3title = (AppCompatEditText) getView().findViewById(R.id.experience3title);
        experience4title = (AppCompatEditText) getView().findViewById(R.id.experience4title);
        experience5title = (AppCompatEditText) getView().findViewById(R.id.experience5title);
        experience6title = (AppCompatEditText) getView().findViewById(R.id.experience6title);
        experience7title = (AppCompatEditText) getView().findViewById(R.id.experience7title);
        experience8title = (AppCompatEditText) getView().findViewById(R.id.experience8title);
        experience9title = (AppCompatEditText) getView().findViewById(R.id.experience9title);
        experience10title = (AppCompatEditText) getView().findViewById(R.id.experience10title);
        experienceTitles.add(experience1title);
        experienceTitles.add(experience2title);
        experienceTitles.add(experience3title);
        experienceTitles.add(experience4title);
        experienceTitles.add(experience5title);
        experienceTitles.add(experience6title);
        experienceTitles.add(experience7title);
        experienceTitles.add(experience8title);
        experienceTitles.add(experience9title);
        experienceTitles.add(experience10title);
        experience1desc = (AppCompatEditText) getView().findViewById(R.id.experience1desc);
        experience2desc = (AppCompatEditText) getView().findViewById(R.id.experience2desc);
        experience3desc = (AppCompatEditText) getView().findViewById(R.id.experience3desc);
        experience4desc = (AppCompatEditText) getView().findViewById(R.id.experience4desc);
        experience5desc = (AppCompatEditText) getView().findViewById(R.id.experience5desc);
        experience6desc = (AppCompatEditText) getView().findViewById(R.id.experience6desc);
        experience7desc = (AppCompatEditText) getView().findViewById(R.id.experience7desc);
        experience8desc = (AppCompatEditText) getView().findViewById(R.id.experience8desc);
        experience9desc = (AppCompatEditText) getView().findViewById(R.id.experience9desc);
        experience10desc = (AppCompatEditText) getView().findViewById(R.id.experience10desc);
        experienceDescs.add(experience1desc);
        experienceDescs.add(experience2desc);
        experienceDescs.add(experience3desc);
        experienceDescs.add(experience4desc);
        experienceDescs.add(experience5desc);
        experienceDescs.add(experience6desc);
        experienceDescs.add(experience7desc);
        experienceDescs.add(experience8desc);
        experienceDescs.add(experience9desc);
        experienceDescs.add(experience10desc);
        startdate1 = (AppCompatEditText) getView().findViewById(R.id.startdate1);
        startdate2 = (AppCompatEditText) getView().findViewById(R.id.startdate2);
        startdate3 = (AppCompatEditText) getView().findViewById(R.id.startdate3);
        startdate4 = (AppCompatEditText) getView().findViewById(R.id.startdate4);
        startdate5 = (AppCompatEditText) getView().findViewById(R.id.startdate5);
        startdate6 = (AppCompatEditText) getView().findViewById(R.id.startdate6);
        startdate7 = (AppCompatEditText) getView().findViewById(R.id.startdate7);
        startdate8 = (AppCompatEditText) getView().findViewById(R.id.startdate8);
        startdate9 = (AppCompatEditText) getView().findViewById(R.id.startdate9);
        startdate10 = (AppCompatEditText) getView().findViewById(R.id.startdate10);
        enddate1 = (AppCompatEditText) getView().findViewById(R.id.enddate1);
        enddate2 = (AppCompatEditText) getView().findViewById(R.id.enddate2);
        enddate3 = (AppCompatEditText) getView().findViewById(R.id.enddate3);
        enddate4 = (AppCompatEditText) getView().findViewById(R.id.enddate4);
        enddate5 = (AppCompatEditText) getView().findViewById(R.id.enddate5);
        enddate6 = (AppCompatEditText) getView().findViewById(R.id.enddate6);
        enddate7 = (AppCompatEditText) getView().findViewById(R.id.enddate7);
        enddate8 = (AppCompatEditText) getView().findViewById(R.id.enddate8);
        enddate9 = (AppCompatEditText) getView().findViewById(R.id.enddate9);
        enddate10 = (AppCompatEditText) getView().findViewById(R.id.enddate10);
        startDates.add(startdate1);
        startDates.add(startdate2);
        startDates.add(startdate3);
        startDates.add(startdate4);
        startDates.add(startdate5);
        startDates.add(startdate6);
        startDates.add(startdate7);
        startDates.add(startdate8);
        startDates.add(startdate9);
        startDates.add(startdate10);
        endDates.add(enddate1);
        endDates.add(enddate2);
        endDates.add(enddate3);
        endDates.add(enddate4);
        endDates.add(enddate5);
        endDates.add(enddate6);
        endDates.add(enddate7);
        endDates.add(enddate8);
        endDates.add(enddate9);
        endDates.add(enddate10);
        JSONArray experiences = ((CVEditActivity) getActivity()).getExperiencesJSON();
        JSONObject experience;
        int i = 0;
        if(experiences != null) {
            try {
                for (i = 0; i < 10; i++) {
                    experience = experiences.getJSONObject(i);
                    experienceTitles.get(i).setText(experience.getString("title"));
                    experienceDescs.get(i).setText(experience.getString("description"));
                    startDates.get(i).setText(experience.getString("begin"));
                    endDates.get(i).setText(experience.getString("end"));
                }
            } catch (JSONException exc) {
                Log.d("QRJob", "ERROR : " + exc.toString());
                Log.d("QRJob", "Le remplissage des formation s'est arretée à : " + i);
            }
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void pushExperiences() {
        final String[] res = new String[1];
        RestData restData = new RestData();
        if(((CVEditActivity) getActivity()).getCVID() == null) return;
        int i;
        for(i = 0; i<10; i++) {
            if(experienceTitles.get(i).getText().toString().equals("")){

                break;
            }
            final int finalI = i;
            restData.postExperience(((CVEditActivity) getActivity()).getCVID(),
                    experienceTitles.get(i).getText(), experienceDescs.get(i).getText(),
                    startDates.get(i).getText(), endDates.get(i).getText(),
                    new ApiCallback() {
                        @Override
                        public void onSuccess(JSONObject msg) {
                            try {
                                Log.d("QRJob", "JSON : " + msg);
                                res[0] = msg.getString("id");
                                if(finalI == 10 || experienceTitles.get(finalI+1).getText().toString().equals(""))
                                    ((CVEditActivity) ExperiencesFragment.this.getActivity()).notifyExperiencesPushed(res[0]);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                onFailure("JSONException");
                            }
                        }

                        @Override
                        public void onFailure(String errorMsg) {
                            res[0] = "-1";
                            Log.d("QRJob", "FAILLLLLEEEEDDDD : " + errorMsg);
                            ((CVEditActivity) ExperiencesFragment.this.getActivity()).notifyExperiencesPushed(res[0]);
                        }
                    });
        }
        if(i == 0) ((CVEditActivity) ExperiencesFragment.this.getActivity()).notifyExperiencesPushed(res[0]);

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
