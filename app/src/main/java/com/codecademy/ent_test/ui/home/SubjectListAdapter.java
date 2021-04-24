package com.codecademy.ent_test.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codecademy.ent_test.R;

import java.util.List;

public class SubjectListAdapter extends RecyclerView.Adapter<SubjectListAdapter. MyTViewHolder>{
    private Context context;
    private List<Subject_models> subject_modelsList;

    public class MyTViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon_Subject;
        public TextView subject_Name;

        public MyTViewHolder(View view){
            super(view);
            icon_Subject = view.findViewById(R.id.icon_Subject);
            subject_Name = view.findViewById(R.id.subject_Name);
        }
    }


    public SubjectListAdapter(Context context, List<Subject_models> subject_modelsList){
        this.context = context;
        this.subject_modelsList = subject_modelsList;
    }




    @Override
    public MyTViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_subjects, parent, false);
        return new MyTViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyTViewHolder holder, int position) {
        Subject_models item = subject_modelsList.get(position);
        Glide.with(context.getApplicationContext())
                .load(item.getIcon())
                .plache

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
