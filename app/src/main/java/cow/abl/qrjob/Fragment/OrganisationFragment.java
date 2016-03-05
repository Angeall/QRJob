package cow.abl.qrjob.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cow.abl.qrjob.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrganisationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrganisationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrganisationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "companyId";
    private static final String ARG_PARAM2 = "companyName";
    private static final String ARG_PARAM3 = "companyDescription";

    private String companyId_;
    private String companyName_;
    private String companyDescription_;

    private OnFragmentInteractionListener mListener;

    public OrganisationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment OrganisationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrganisationFragment newInstance(String companyId, String companyName, String companyDescription) {
        OrganisationFragment fragment = new OrganisationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, companyId);
        args.putString(ARG_PARAM2, companyName);
        args.putString(ARG_PARAM3, companyDescription);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            companyId_ = getArguments().getString(ARG_PARAM1);
            companyName_ = getArguments().getString(ARG_PARAM2);
            companyDescription_ = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        TextView tvOrganisationName = (TextView)this.getActivity().findViewById(R.id.organisation_name);
        TextView tvOrganisationDescription = (TextView)this.getActivity().findViewById(R.id.organisation_description);

        if (companyName_ != null && companyDescription_ != null) {
            tvOrganisationName.setText(companyName_);
            tvOrganisationDescription.setText(companyDescription_);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_organisation, container, false);
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
