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
 * {@link FormationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FormationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormationFragment extends Fragment {
    private AppCompatEditText formation1title;
    private AppCompatEditText formation2title;
    private AppCompatEditText formation3title;
    private AppCompatEditText formation4title;
    private AppCompatEditText formation5title;
    private AppCompatEditText formation6title;
    private AppCompatEditText formation7title;
    private AppCompatEditText formation8title;
    private AppCompatEditText formation9title;
    private AppCompatEditText formation10title;
    private AppCompatEditText formation1desc;
    private AppCompatEditText formation2desc;
    private AppCompatEditText formation3desc;
    private AppCompatEditText formation4desc;
    private AppCompatEditText formation5desc;
    private AppCompatEditText formation6desc;
    private AppCompatEditText formation7desc;
    private AppCompatEditText formation8desc;
    private AppCompatEditText formation9desc;
    private AppCompatEditText formation10desc;
    private AppCompatEditText f_startdate1;
    private AppCompatEditText f_startdate2;
    private AppCompatEditText f_startdate3;
    private AppCompatEditText f_startdate4;
    private AppCompatEditText f_startdate5;
    private AppCompatEditText f_startdate6;
    private AppCompatEditText f_startdate7;
    private AppCompatEditText f_startdate8;
    private AppCompatEditText f_startdate9;
    private AppCompatEditText f_startdate10;
    private AppCompatEditText f_enddate1;
    private AppCompatEditText f_enddate2;
    private AppCompatEditText f_enddate3;
    private AppCompatEditText f_enddate4;
    private AppCompatEditText f_enddate5;
    private AppCompatEditText f_enddate6;
    private AppCompatEditText f_enddate7;
    private AppCompatEditText f_enddate8;
    private AppCompatEditText f_enddate9;
    private AppCompatEditText f_enddate10;
    private ArrayList<AppCompatEditText> formationTitles = new ArrayList<>();
    private ArrayList<AppCompatEditText> formationDescs = new ArrayList<>();
    private ArrayList<AppCompatEditText> startDates = new ArrayList<>();
    private ArrayList<AppCompatEditText> endDates = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FormationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FormationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FormationFragment newInstance(String param1, String param2) {
        FormationFragment fragment = new FormationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_formation, container, false);
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
        formation1title = (AppCompatEditText) getView().findViewById(R.id.formation1title);
        formation2title = (AppCompatEditText) getView().findViewById(R.id.formation2title);
        formation3title = (AppCompatEditText) getView().findViewById(R.id.formation3title);
        formation4title = (AppCompatEditText) getView().findViewById(R.id.formation4title);
        formation5title = (AppCompatEditText) getView().findViewById(R.id.formation5title);
        formation6title = (AppCompatEditText) getView().findViewById(R.id.formation6title);
        formation7title = (AppCompatEditText) getView().findViewById(R.id.formation7title);
        formation8title = (AppCompatEditText) getView().findViewById(R.id.formation8title);
        formation9title = (AppCompatEditText) getView().findViewById(R.id.formation9title);
        formation10title = (AppCompatEditText) getView().findViewById(R.id.formation10title);
        formationTitles.add(formation1title);
        formationTitles.add(formation2title);
        formationTitles.add(formation3title);
        formationTitles.add(formation4title);
        formationTitles.add(formation5title);
        formationTitles.add(formation6title);
        formationTitles.add(formation7title);
        formationTitles.add(formation8title);
        formationTitles.add(formation9title);
        formationTitles.add(formation10title);
        formation1desc = (AppCompatEditText) getView().findViewById(R.id.formation1desc);
        formation2desc = (AppCompatEditText) getView().findViewById(R.id.formation2desc);
        formation3desc = (AppCompatEditText) getView().findViewById(R.id.formation3desc);
        formation4desc = (AppCompatEditText) getView().findViewById(R.id.formation4desc);
        formation5desc = (AppCompatEditText) getView().findViewById(R.id.formation5desc);
        formation6desc = (AppCompatEditText) getView().findViewById(R.id.formation6desc);
        formation7desc = (AppCompatEditText) getView().findViewById(R.id.formation7desc);
        formation8desc = (AppCompatEditText) getView().findViewById(R.id.formation8desc);
        formation9desc = (AppCompatEditText) getView().findViewById(R.id.formation9desc);
        formation10desc = (AppCompatEditText) getView().findViewById(R.id.formation10desc);
        formationDescs.add(formation1desc);
        formationDescs.add(formation2desc);
        formationDescs.add(formation3desc);
        formationDescs.add(formation4desc);
        formationDescs.add(formation5desc);
        formationDescs.add(formation6desc);
        formationDescs.add(formation7desc);
        formationDescs.add(formation8desc);
        formationDescs.add(formation9desc);
        formationDescs.add(formation10desc);
        f_startdate1 = (AppCompatEditText) getView().findViewById(R.id.f_startdate1);
        f_startdate2 = (AppCompatEditText) getView().findViewById(R.id.f_startdate2);
        f_startdate3 = (AppCompatEditText) getView().findViewById(R.id.f_startdate3);
        f_startdate4 = (AppCompatEditText) getView().findViewById(R.id.f_startdate4);
        f_startdate5 = (AppCompatEditText) getView().findViewById(R.id.f_startdate5);
        f_startdate6 = (AppCompatEditText) getView().findViewById(R.id.f_startdate6);
        f_startdate7 = (AppCompatEditText) getView().findViewById(R.id.f_startdate7);
        f_startdate8 = (AppCompatEditText) getView().findViewById(R.id.f_startdate8);
        f_startdate9 = (AppCompatEditText) getView().findViewById(R.id.f_startdate9);
        f_startdate10 = (AppCompatEditText) getView().findViewById(R.id.f_startdate10);
        f_enddate1 = (AppCompatEditText) getView().findViewById(R.id.f_enddate1);
        f_enddate2 = (AppCompatEditText) getView().findViewById(R.id.f_enddate2);
        f_enddate3 = (AppCompatEditText) getView().findViewById(R.id.f_enddate3);
        f_enddate4 = (AppCompatEditText) getView().findViewById(R.id.f_enddate4);
        f_enddate5 = (AppCompatEditText) getView().findViewById(R.id.f_enddate5);
        f_enddate6 = (AppCompatEditText) getView().findViewById(R.id.f_enddate6);
        f_enddate7 = (AppCompatEditText) getView().findViewById(R.id.f_enddate7);
        f_enddate8 = (AppCompatEditText) getView().findViewById(R.id.f_enddate8);
        f_enddate9 = (AppCompatEditText) getView().findViewById(R.id.f_enddate9);
        f_enddate10 = (AppCompatEditText) getView().findViewById(R.id.f_enddate10);
        startDates.add(f_startdate1);
        startDates.add(f_startdate2);
        startDates.add(f_startdate3);
        startDates.add(f_startdate4);
        startDates.add(f_startdate5);
        startDates.add(f_startdate6);
        startDates.add(f_startdate7);
        startDates.add(f_startdate8);
        startDates.add(f_startdate9);
        startDates.add(f_startdate10);
        endDates.add(f_enddate1);
        endDates.add(f_enddate2);
        endDates.add(f_enddate3);
        endDates.add(f_enddate4);
        endDates.add(f_enddate5);
        endDates.add(f_enddate6);
        endDates.add(f_enddate7);
        endDates.add(f_enddate8);
        endDates.add(f_enddate9);
        endDates.add(f_enddate10);
        JSONArray formations = ((CVEditActivity) getActivity()).getFormationJSON();
        JSONObject formation;
        int i = 0;
        if(formations != null) {
            try {
                for (i = 0; i < 10; i++) {
                    formation = formations.getJSONObject(i);
                    formationTitles.get(i).setText(formation.getString("title"));
                    formationDescs.get(i).setText(formation.getString("description"));
                    startDates.get(i).setText(formation.getString("begin"));
                    endDates.get(i).setText(formation.getString("end"));
                }
            } catch (JSONException exc) {
                Log.d("QRJob", "Le remplissage des formation s'est arretée à : " + i);
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void pushFormations() {
        final String[] res = new String[1];
        RestData restData = new RestData();
        if(((CVEditActivity) getActivity()).getCVID() == null) return;
        int i;
        for(i = 0; i<10; i++) {
            if (formationTitles.get(i).getText().toString().equals("")) {
                break;
            }
            final int finalI = i;
            restData.postFormation(((CVEditActivity) getActivity()).getCVID(),
                    formationTitles.get(i).getText(), formationDescs.get(i).getText(),
                    startDates.get(i).getText(), endDates.get(i).getText(),
                    new ApiCallback() {
                        @Override
                        public void onSuccess(JSONObject msg) {
                            try {
                                Log.d("QRJob", "JSON : " + msg);
                                res[0] = msg.getString("id");
                                if (finalI == 10 || formationTitles.get(finalI + 1).getText().toString().equals(""))
                                    ((CVEditActivity) FormationFragment.this.getActivity()).notifyFormationsPushed(res[0]);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                onFailure("JSONException");
                            }
                        }

                        @Override
                        public void onFailure(String errorMsg) {
                            res[0] = "-1";
                            Log.d("QRJob", "FAILLLLLEEEEDDDD : " + errorMsg);
                            ((CVEditActivity) FormationFragment.this.getActivity()).notifyFormationsPushed(res[0]);
                        }
                    });
        }
        if(i==0) ((CVEditActivity) FormationFragment.this.getActivity()).notifyFormationsPushed(res[0]);

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
