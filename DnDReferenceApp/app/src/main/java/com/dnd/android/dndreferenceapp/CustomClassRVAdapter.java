package com.dnd.android.dndreferenceapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chris on 2/26/2018.
 */

public class CustomClassRVAdapter extends RecyclerView.Adapter<CustomClassRVAdapter.ClassViewHolder> {

    private ArrayList<Classes> listClasses;

    public static class ClassViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView className, classLevel;

        ClassViewHolder(View v) {
            super(v);
            cv = v.findViewById(R.id.cvRV);
            className = v.findViewById(R.id.tClassName);
            classLevel = v.findViewById(R.id.tClassLevel);
        }

    }

    CustomClassRVAdapter(ArrayList<Classes> t)
    {
        listClasses = t;
    }

    @Override
    public int getItemCount(){
        return listClasses.size();
    }

    @Override
    public ClassViewHolder onCreateViewHolder(ViewGroup vg, int i) {
        View v = LayoutInflater.from(vg.getContext()).inflate(R.layout.custom_class_rv, vg, false);
        ClassViewHolder cvh = new ClassViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(ClassViewHolder cvh, int i) {
        cvh.className.setText(listClasses.get(i).getClassName());
        cvh.classLevel.setText("Hi");
        cvh.classLevel.setText(Integer.toString(listClasses.get(i).getClassLevel()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView rv){
        super.onAttachedToRecyclerView(rv);
    }
}
