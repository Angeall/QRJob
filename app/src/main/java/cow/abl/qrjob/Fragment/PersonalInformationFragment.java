package cow.abl.qrjob.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import cow.abl.qrjob.CVEditActivity;
import cow.abl.qrjob.R;
import cow.abl.qrjob.net.ApiCallback;
import cow.abl.qrjob.net.RestData;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PersonalInformationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PersonalInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalInformationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private AppCompatAutoCompleteTextView countriesTextView;
    private AppCompatAutoCompleteTextView driverLicenseTextView;
    private AppCompatAutoCompleteTextView maritalStatusesTextView;
    private TextView nameTextView;
    private TextView surnameTextView;
    private TextView ageTextView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PersonalInformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalInformationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalInformationFragment newInstance(String param1, String param2) {
        PersonalInformationFragment fragment = new PersonalInformationFragment();
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
        return inflater.inflate(R.layout.fragment_personal_information, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        nameTextView = (TextView) getView().findViewById(R.id.cv_edit_name_text);
        surnameTextView = (TextView) getView().findViewById(R.id.cv_edit_surname_text);
        ageTextView = (TextView) getView().findViewById(R.id.cv_edit_age_text);
        countriesTextView = (AppCompatAutoCompleteTextView) getView()
                .findViewById(R.id.cv_edit_nationality_text);
        driverLicenseTextView = (AppCompatAutoCompleteTextView) getView()
                .findViewById(R.id.cv_edit_driver_license_text);
        maritalStatusesTextView = (AppCompatAutoCompleteTextView) getView()
                .findViewById(R.id.cv_edit_marital_status_text);
        String[] countries = getResources().getStringArray(R.array.countries);
        countriesTextView.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, countries));
        String[] maritalStatuses = getResources().getStringArray(R.array.marital_status);
        maritalStatusesTextView.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, maritalStatuses));
        String[] driversLicense = getResources().getStringArray(R.array.drivers_license);
        driverLicenseTextView.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, driversLicense));
        driverLicenseTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((AppCompatAutoCompleteTextView) v).showDropDown();
                } else ((AppCompatAutoCompleteTextView) v).dismissDropDown();
            }
        });
        countriesTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((AppCompatAutoCompleteTextView) v).showDropDown();
                } else ((AppCompatAutoCompleteTextView) v).dismissDropDown();
            }
        });
        maritalStatusesTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    ((AppCompatAutoCompleteTextView) v).showDropDown();
                }
                else ((AppCompatAutoCompleteTextView) v).dismissDropDown();
            }
        });
        JSONObject cv = ((CVEditActivity) getActivity()).getCVJSON();
        if(cv != null){
            try {
                nameTextView.setText(cv.getString("lastname"));
                surnameTextView.setText(cv.getString("firstname"));
                ageTextView.setText(cv.getString("age"));
                countriesTextView.setText(cv.getString("nationality"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void pushCV() {
        final String[] res = new String[1];
            new RestData().postCV(nameTextView.getText(), surnameTextView.getText(),
                    ((CVEditActivity) getActivity()).getUserID(), ageTextView.getText(),
                    countriesTextView.getText(), new ApiCallback() {
                        @Override
                        public void onSuccess(JSONObject msg) {
                            try {
                                Log.d("QRJob", "JSON : " + msg);
                                res[0] = msg.getString("id");
                                ((CVEditActivity) PersonalInformationFragment.this.getActivity()).notifyCVPushed(res[0]);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                onFailure("JSONException");
                            }
                        }

                        @Override
                        public void onFailure(String errorMsg) {
                            res[0] = "-1";
                            Log.d("QRJob", "FAILLLLLEEEEDDDD : " + errorMsg);
                            ((CVEditActivity) PersonalInformationFragment.this.getActivity()).notifyCVPushed(res[0]);
                        }
                    });
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
