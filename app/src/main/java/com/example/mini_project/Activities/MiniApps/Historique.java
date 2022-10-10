package com.example.mini_project.Activities.MiniApps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mini_project.Activities.MiniApps.imc.IMCAdapter;
import com.example.mini_project.Activities.MiniApps.imc.imcDetails;
import com.example.mini_project.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Historique#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Historique extends Fragment {

    private RecyclerView historiqueRV;
    private IMCAdapter mAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Historique() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Historique.
     */
    // TODO: Rename and change types and number of parameters
    public static Historique newInstance(String param1, String param2) {
        Historique fragment = new Historique();
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
        return inflater.inflate(R.layout.fragment_historique, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
    }
    public void initView(View view) {
        historiqueRV = view.findViewById(R.id.historiqueRV);
        refreshData();
    }
    public void refreshData() {
        ArrayList<imcDetails> imcList = getIMCList(PreferenceManager.IMC_LIST);

        mAdapter = new IMCAdapter(imcList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        historiqueRV.setLayoutManager(mLayoutManager);
        historiqueRV.setItemAnimator(new DefaultItemAnimator());
        historiqueRV.setAdapter(mAdapter);
    }
    public ArrayList<imcDetails> getIMCList(String key) {

        Gson gson = new Gson();
        String json = PreferenceManager.getInstance(getContext()).getString(key, "");
        Type type = new TypeToken<ArrayList<imcDetails>>() {
        }.getType();
        ArrayList<imcDetails> arrayList = gson.fromJson(json, type);
        return arrayList == null ? new ArrayList<imcDetails>() : arrayList;
    }
}