package com.example.mini_project.Activities.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.mini_project.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link System#newInstance} factory method to
 * create an instance of this fragment.
 */
public class System extends Fragment {

    TextView WIFI;
    TextView DATA;
    TextView BatteryState;
    TextView SIMSTATE;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public System() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment System.
     */
    // TODO: Rename and change types and number of parameters
    public static System newInstance(String param1, String param2) {
        System fragment = new System();
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
        return inflater.inflate(R.layout.fragment_system, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitView(view);
        InitEvent();
    }
    public void InitView(View view){
        WIFI=(TextView) view.findViewById(R.id.WIFI);
        DATA=(TextView) view.findViewById(R.id.DATA);
        BatteryState=(TextView) view.findViewById(R.id.BatteryState);
        SIMSTATE=(TextView) view.findViewById(R.id.SIMSTATE);

    }
    public void InitEvent(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (WifiConnected()) {
                    WIFI.setText("WIFI State : Connected");
                } else {
                    WIFI.setText("WIFI State : No internet connection");
                }
                if (DataConnected()) {
                    DATA.setText("4G State : Connected");
                } else {
                    DATA.setText("4G State : No internet connection");
                }
                BatteryState.setText("Battery State: "+String.valueOf(BatteryState())+"%");
                SIMSTATE.setText(SimCardState());
                InitEvent();
            }
        },1000);

    }
    public boolean WifiConnected() {
        boolean isWiFi = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) this.getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            isWiFi = nInfo.getType() == ConnectivityManager.TYPE_WIFI;
            return isWiFi;
        }catch (Exception e){
            Log.e("Connectivity Exception", e.getMessage());
        }
        return isWiFi;
    }
    public  boolean DataConnected(){
        boolean isData = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) this.getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            isData = nInfo.getType() == ConnectivityManager.TYPE_MOBILE_DUN;
            return isData;
        }catch (Exception e){
            Log.e("Connectivity Exception", e.getMessage());
        }
        return isData;
    }
    public float BatteryState(){
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = this.getActivity().getApplicationContext().registerReceiver(null, ifilter);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = level * 100 / (float)scale;
        return batteryPct;
    }
    public String SimCardState() {
        TelephonyManager telMgr = (TelephonyManager) this.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        int simState = telMgr.getSimState();
        String STATE="ERROR";
        switch (simState) {
            case TelephonyManager.SIM_STATE_ABSENT:
                STATE= "SIM STATE: ABSENT";
            break;
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                STATE= "SIM STATE: NETWORK LOCKED";
            break;
            case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                STATE= "SIM STATE: PIN REQUIRED";
            break;
            case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                STATE= "SIM STATE: PUK REQUIRED";
            break;
            case TelephonyManager.SIM_STATE_READY:
                STATE= "SIM STATE: READY";
            break;
            case TelephonyManager.SIM_STATE_UNKNOWN:
                STATE= "SIM STATE: UNKOWN";
            break;
        }
        return STATE;
    }
}