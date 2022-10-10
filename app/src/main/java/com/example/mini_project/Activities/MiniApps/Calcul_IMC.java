package com.example.mini_project.Activities.MiniApps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mini_project.Activities.MiniApps.imc.imcDetails;
import com.example.mini_project.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Calcul_IMC#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calcul_IMC extends Fragment {


    private Button calculerBTN;
    private RadioGroup radioGroup;
    private EditText poidsET;
    private EditText tailleET;
    private TextView resultatTV;
    private ImageView smileIV;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Calcul_IMC() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Calcul_IMC.
     */
    // TODO: Rename and change types and number of parameters
    public static Calcul_IMC newInstance(String param1, String param2) {
        Calcul_IMC fragment = new Calcul_IMC();
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
        return inflater.inflate(R.layout.fragment_calcul__i_m_c, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initEvent();
    }
    public void initView(View view) {
        calculerBTN = view.findViewById(R.id.calculBTN);
        radioGroup = view.findViewById(R.id.radioGroup);
        poidsET = view.findViewById(R.id.poidsET);
        tailleET = view.findViewById(R.id.tailleET);
        resultatTV = view.findViewById(R.id.resultatTV);
        smileIV = view.findViewById(R.id.smileIV);
    }
    public void initEvent() {
        calculerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**   How get the selected radio option  femme/Homme    **/

              /*  int selectedId=radioGroup.getCheckedRadioButtonId();
                RadioButton radioSexButton=(RadioButton)findViewById(selectedId);
                if (radioSexButton.getText().equals("Homme")){
                    //case Homme
                    Toast.makeText(ImcActivity.this,"Homme",Toast.LENGTH_SHORT).show();

                }
                else {
                    // Case Femme
                    Toast.makeText(ImcActivity.this,"Femme",Toast.LENGTH_SHORT).show();

                }*/

                //poids en kg/taille² en (m)

                if (poidsET.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Donner le poids !!", Toast.LENGTH_SHORT).show();
                } else if (tailleET.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Donner la taille !!", Toast.LENGTH_SHORT).show();
                } else {
                    // imc = poids (kg) / taille^2 (m)
                    double poids = Float.parseFloat(poidsET.getText().toString());
                    double taille = Float.parseFloat(tailleET.getText().toString()) / 100;
                    double imcValue = poids / Math.pow(taille,2.0);

                    imcValue = Double.parseDouble(String.format("%.02f", imcValue).replace(",","."));

                    String imcStatus = updateUIView(imcValue);
                    resultatTV.setText(" Votre IMC est " + imcValue + "\n\n --> " + imcStatus);


                    ArrayList<imcDetails> imcList = getIMCList(PreferenceManager.IMC_LIST);

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss", Locale.FRANCE);
                    Date date = new Date();
                    String actualDate = formatter.format(date);


                    imcDetails imc = new imcDetails(actualDate.split("T")[0], actualDate.split("T")[1], String.valueOf(imcValue),imcStatus);
                    imcList.add(imc);
                    putIMCList(PreferenceManager.IMC_LIST, imcList);


                    updateUIView(imcValue);

                }
            }
        });
    }
    private String updateUIView(double imcValue) {

        smileIV.setVisibility(View.VISIBLE);
        if (imcValue < 18.5) {
            smileIV.setImageDrawable(getContext().getResources().getDrawable(R.drawable.worst_smile_value));
            return "Insuffisance pondérale (maigreur)";
        } else if (imcValue < 25) {
            smileIV.setImageDrawable(getContext().getResources().getDrawable(R.drawable.best_value_smile));
            return "Corpulence normale";
        } else if (imcValue < 30) {
            smileIV.setImageDrawable(getContext().getResources().getDrawable(R.drawable.emoji));
            return "Surpoids";
        } else if (imcValue < 35) {
            smileIV.setImageDrawable(getContext().getResources().getDrawable(R.drawable.middle_smile_value));
            return "Obésité modérée";
        } else if (imcValue < 40) {
            smileIV.setImageDrawable(getContext().getResources().getDrawable(R.drawable.worst_smile_value));
            return "Obésité sévère";
        } else {
            smileIV.setImageDrawable(getContext().getResources().getDrawable(R.drawable.worst_smile_value));
            return "Obésité morbide ou massive";
        }
    }

    public void putIMCList(String key, ArrayList<imcDetails> imcList) {

        Gson gson = new Gson();
        String json = gson.toJson(imcList);
        PreferenceManager.getInstance(getContext()).putString(key, json);
    }


    public ArrayList<imcDetails> getIMCList(String key) {

        Gson gson = new Gson();
        String json = PreferenceManager.getInstance(getContext()).getString(key, "");
        Type type = new TypeToken<ArrayList<imcDetails>>() {
        }.getType();
        ArrayList<imcDetails> arrayList = gson.fromJson(json, type);
        return arrayList == null ? new ArrayList<>() : arrayList;
    }
}