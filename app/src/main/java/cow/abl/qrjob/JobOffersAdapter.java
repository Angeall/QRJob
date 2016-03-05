package cow.abl.qrjob;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cow.abl.qrjob.Fragment.OrganisationFragment;

/**
 * Created by lucas on 5/03/16.
 */
public class JobOffersAdapter extends RecyclerView.Adapter<JobOffersAdapter.MyViewHolder> {

    private ArrayList<JobOffer> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvJobName;
        TextView tvJobDescription;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvJobName = (TextView) itemView.findViewById(R.id.job_name);
            this.tvJobDescription = (TextView) itemView.findViewById(R.id.job_description);
        }
    }

    public JobOffersAdapter(ArrayList<JobOffer> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_card, parent, false);

        view.setOnClickListener(OrganisationFragment.cardOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView tvJobName = holder.tvJobName;
        TextView tvJobDescription = holder.tvJobDescription;

        tvJobName.setText(dataSet.get(listPosition).getType());
        tvJobDescription.setText(dataSet.get(listPosition).getDescription());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}

