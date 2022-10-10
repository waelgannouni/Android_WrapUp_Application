package com.example.mini_project.Activities.Fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mini_project.Activities.LoadingScreen;
import com.example.mini_project.Activities.MiniApps.Factoriel;
import com.example.mini_project.Activities.MiniApps.IMC;
import com.example.mini_project.Activities.MiniApps.Maps;
import com.example.mini_project.Activities.MiniApps.TextToSpeach;
import com.example.mini_project.LOG_SIGN.Login;
import com.example.mini_project.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MiniAppsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MiniAppsFragment extends Fragment {

    public Button Factoriel;
    public Button IMC;
    public Button Maps;
    public Button TextToSpeach;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MiniAppsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MiniAppsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MiniAppsFragment newInstance(String param1, String param2) {
        MiniAppsFragment fragment = new MiniAppsFragment();
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
        return inflater.inflate(R.layout.fragment_mini_apps, container, false);

    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initEvent();
    }
    public void initView(View view) {

        Factoriel = (Button) view.findViewById(R.id.Factoriel);
        IMC = (Button) view.findViewById(R.id.IMC);
        Maps = (Button) view.findViewById(R.id.Maps);
        TextToSpeach = (Button) view.findViewById(R.id.TextToSpeach);
    }
    public void initEvent (){
        Factoriel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transition = new Intent(getActivity(),
                        Factoriel.class);
                MiniAppsFragment.this.startActivity(transition);
            }
        });
        IMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transition = new Intent(getActivity(),
                      IMC.class);
                MiniAppsFragment.this.startActivity(transition);
            }
        });
        TextToSpeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transition = new Intent(getActivity(),
                        TextToSpeach.class);
                MiniAppsFragment.this.startActivity(transition);
            }
        });
        Maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transition = new Intent(getActivity(),
                        Maps.class);
                MiniAppsFragment.this.startActivity(transition);
            }
        });
    }
}