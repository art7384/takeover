package com.sabotage.takeover.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.sabotage.takeover.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karmishin on 26.06.2015.
 */
public class GamesListFragment extends Fragment implements AdapterView.OnItemClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = init(inflater);


        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private View init(LayoutInflater inflater){
        View v = inflater.inflate(R.layout.fragment_games_list, null);

        return v;
    }



}
