package com.dnd.android.dndreferenceapp;


import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfile extends Fragment {
    public FragmentProfile() {
        // Required empty public constructor
    }

    private TextView name, cumLevel, race, background, nextLevel;
    private EditText experience;
    private Character refPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_profile, container, false);

        //Initialize text view
        name = (TextView) v.findViewById(R.id.tProName);
        cumLevel = (TextView) v.findViewById(R.id.tProCumLevel);
        race = (TextView) v.findViewById(R.id.tProRace);
        background = (TextView) v.findViewById(R.id.tProBackground);
        nextLevel = (TextView) v.findViewById(R.id.tProNextLevel);

        //Initialize edit texts
        experience = (EditText) v.findViewById(R.id.etProExperience);

        //Reference to player object
        refPlayer = ((HomeActivity)getActivity()).player;

        //String to be displayed in cumLevel
        String lvlString = "LVL: " + Integer.toString(refPlayer.getCumLevel());

        //Setting text from player object
        name.setText(refPlayer.getCharacterName());
        cumLevel.setText(lvlString);
        race.setText(refPlayer.getRace());
        background.setText(refPlayer.getBackground());
        experience.setText(Integer.toString(refPlayer.getExperience()));
        levelListener(refPlayer.getExperience());

        //Setting text filter
        experience.setFilters(new InputFilter[]{new MinMaxFilter(0, 355000)});

        //Adding text listener
        experience.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                int xp;
                String lvlString = "LVL: " + Integer.toString(refPlayer.getCumLevel());
                if(editable.toString().equals(""))
                    xp = 0;
                else
                    xp = Integer.parseInt(editable.toString());
                levelListener(xp);
                proficiencyListener(xp);
                refPlayer.setExperience(xp);
                cumLevel.setText(lvlString);

            }
        });

        return v;
    }

    private void levelListener(int exp)
    {
        int remainingXP;
        if(exp < 300) {
            refPlayer.setCumLevel(1);
            remainingXP = 300 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
        else if(exp < 900) {
            refPlayer.setCumLevel(2);
            remainingXP = 900 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
        else if(exp < 2700){
            refPlayer.setCumLevel(3);
            remainingXP = 2700 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
        else if(exp < 6500){
            refPlayer.setCumLevel(4);
            remainingXP = 6500 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
        else if(exp < 14000){
            refPlayer.setCumLevel(5);
            remainingXP = 14000 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
        else if(exp < 23000){
            refPlayer.setCumLevel(6);
            remainingXP = 23000 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
        else if(exp < 34000){
            refPlayer.setCumLevel(7);
            remainingXP = 34000 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
        else if(exp < 48000){
            refPlayer.setCumLevel(8);
            remainingXP = 48000 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
        else if(exp < 64000){
            refPlayer.setCumLevel(9);
            remainingXP = 64000 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
        else if(exp < 85000){
            refPlayer.setCumLevel(10);
            remainingXP = 85000 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
        else if(exp < 100000){
            refPlayer.setCumLevel(11);
            remainingXP = 100000 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
        else if(exp < 120000){
            refPlayer.setCumLevel(12);
            remainingXP = 120000 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
        else if(exp < 140000){
            refPlayer.setCumLevel(13);
            remainingXP = 140000 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
        else if(exp < 165000){
            refPlayer.setCumLevel(14);
            remainingXP = 165000 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
        else if(exp < 195000){
            refPlayer.setCumLevel(15);
            remainingXP = 195000 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
        else if(exp < 225000){
            refPlayer.setCumLevel(16);
            remainingXP = 225000 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
        else if(exp < 265000){
            refPlayer.setCumLevel(17);
            remainingXP = 265000 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
        else if(exp < 305000){
            refPlayer.setCumLevel(18);
            remainingXP = 305000 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
        else if(exp < 355000){
            refPlayer.setCumLevel(19);
            remainingXP = 355000 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
        else if(exp == 355000){
            refPlayer.setCumLevel(20);
            remainingXP = 355000 - exp;
            nextLevel.setText(Integer.toString(remainingXP));
        }
    }

    private void proficiencyListener(int exp)
    {
        if(exp < 6500)
            refPlayer.setProficiency(2);
        else if(exp < 48000)
            refPlayer.setProficiency(3);
        else if(exp < 120000)
            refPlayer.setProficiency(4);
        else if(exp < 225000)
            refPlayer.setProficiency(5);
        else if(exp <= 355000)
            refPlayer.setProficiency(6);
    }
}
