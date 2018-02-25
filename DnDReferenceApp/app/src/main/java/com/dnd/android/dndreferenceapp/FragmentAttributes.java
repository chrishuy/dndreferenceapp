package com.dnd.android.dndreferenceapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.text.InputFilter;
import android.text.Spanned;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FragmentAttributes extends Fragment {

    public FragmentAttributes() {
        // Required empty public constructor
    }

    private TextView strength, constitution, charisma, intelligence, dexterity, wisdom, speed, proficiency;
    private EditText eStrength, eConstitution, eCharisma, eIntelligence, eDexterity, eWisdom;
    private CheckBox inspiration;
    Context baseActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_attributes, container, false);

        //Initialize context for base activity to get information from player object

        //Initializing TextViews for character modifiers
        strength = v.findViewById(R.id.tStrModifier);
        constitution = v.findViewById(R.id.tConModifier);
        charisma = v.findViewById(R.id.tChaModifier);
        intelligence = v.findViewById(R.id.tIntModifier);
        dexterity = v.findViewById(R.id.tDexModifier);
        wisdom = v.findViewById(R.id.tWisModifier);
        speed = v.findViewById(R.id.tSpeed);
        proficiency = v.findViewById(R.id.tProficiency);

        //Initialize EditTexts for character attributes
        eStrength = v.findViewById(R.id.etStrength);
        eConstitution = v.findViewById(R.id.etConstitution);
        eCharisma = v.findViewById(R.id.etCharisma);
        eIntelligence = v.findViewById(R.id.etIntelligence);
        eDexterity = v.findViewById(R.id.etDexterity);
        eWisdom = v.findViewById(R.id.etWisdom);

        //Initializing Checkbox for inspiration
        inspiration = v.findViewById(R.id.cbInspiration);

        //Sets min-max values for character attributes to prevent impossible values
        eStrength.setFilters(new InputFilter[]{new MinMaxFilter(1,30)});
        eConstitution.setFilters(new InputFilter[]{new MinMaxFilter(1,30)});
        eCharisma.setFilters(new InputFilter[]{new MinMaxFilter(1,30)});
        eIntelligence.setFilters(new InputFilter[]{new MinMaxFilter(1,30)});
        eDexterity.setFilters(new InputFilter[]{new MinMaxFilter(1,30)});
        eWisdom.setFilters(new InputFilter[]{new MinMaxFilter(1,30)});

        //Player reference to save data locally
        final Character refPlayer = ((HomeActivity)getActivity()).player;

        //Listens to text changes and automatically adjusts modifiers
        eStrength.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int rawInput;
                if(editable == null || editable.toString().equals(""))
                {
                    rawInput = 0;
                }
                else{
                    rawInput = Integer.parseInt(editable.toString());
                }
                modifierListener(strength, rawInput);
                refPlayer.setStrength(rawInput);
            }
        });
        eConstitution.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int rawInput;
                if(editable == null || editable.toString().equals(""))
                {
                    rawInput = 0;
                }
                else{
                    rawInput = Integer.parseInt(editable.toString());
                }
                modifierListener(constitution, rawInput);
                refPlayer.setConstitution(rawInput);
            }
        });
        eCharisma.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int rawInput;
                if(editable == null || editable.toString().equals(""))
                {
                    rawInput = 0;
                }
                else{
                    rawInput = Integer.parseInt(editable.toString());
                }
                modifierListener(charisma, rawInput);
                refPlayer.setCharisma(rawInput);
            }
        });
        eIntelligence.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int rawInput;
                if(editable == null || editable.toString().equals(""))
                {
                    rawInput = 0;
                }
                else{
                    rawInput = Integer.parseInt(editable.toString());
                }
                modifierListener(intelligence, rawInput);
                refPlayer.setIntelligence(rawInput);
            }
        });
        eDexterity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int rawInput;
                if(editable == null || editable.toString().equals(""))
                {
                    rawInput = 0;
                }
                else{
                    rawInput = Integer.parseInt(editable.toString());
                }
                modifierListener(dexterity, rawInput);
                modifierListener(speed, rawInput);
                refPlayer.setDexterity(rawInput);
                refPlayer.setSpeed(rawInput);
            }
        });
        eWisdom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int rawInput;
                if(editable == null || editable.toString().equals(""))
                {
                    rawInput = 0;
                }
                else{
                    rawInput = Integer.parseInt(editable.toString());
                }
                modifierListener(wisdom, rawInput);
                refPlayer.setWisdom(rawInput);
            }
        });

        //Puts initial values into EditText fields
        eStrength.setText(Integer.toString(refPlayer.getStrength()));
        eConstitution.setText(Integer.toString(refPlayer.getConstitution()));
        eCharisma.setText(Integer.toString(refPlayer.getCharisma()));
        eIntelligence.setText(Integer.toString(refPlayer.getIntelligence()));
        eDexterity.setText(Integer.toString(refPlayer.getDexterity()));
        eWisdom.setText(Integer.toString(refPlayer.getWisdom()));
        speed.setText(Integer.toString(refPlayer.getSpeed()));
        proficiency.setText(Integer.toString(refPlayer.getProficiency()));


        //Checks the checkbox automatically from the player object if it is the first time navigating to this tab on launch
        inspiration.setChecked(refPlayer.isInspiration());

        //Listens to checkbox status and changes inspiration boolean as necessary
        inspiration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    refPlayer.setInspiration(true);
                }
                else
                    refPlayer.setInspiration(false);
            }
        });



        //Button used strictly for testing purposes
        Button t = (Button) v.findViewById(R.id.button);
        t.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                refPlayer.print();
            }
        });

        return v;
    }


    /**
     * Method to change the displayed modifier dynamically
     * @param tv TextView to display modifier value
     * @param input input given by user
     */
    private void modifierListener(TextView tv, int input)
    {
        if(input <= 1)
        {
            tv.setText("-5");
        }
        else if(input == 2 || input == 3)
        {
            tv.setText("-4");
        }
        else if(input == 4 || input == 5)
        {
            tv.setText("-3");
        }
        else if(input == 6 || input == 7)
        {
            tv.setText("-2");
        }
        else if(input == 8 || input == 9)
        {
            tv.setText("-1");
        }
        else if(input == 10 || input == 11)
        {
            tv.setText("+0");
        }
        else if(input == 12 || input == 13)
        {
            tv.setText("+1");
        }
        else if(input == 14 || input == 15)
        {
            tv.setText("+2");
        }
        else if(input == 16 || input == 17)
        {
            tv.setText("+3");
        }
        else if(input == 18 || input == 19)
        {
            tv.setText("+4");
        }
        else if(input == 20 || input == 21)
        {
            tv.setText("+5");
        }

        else if(input == 22 || input == 23)
        {
            tv.setText("+6");
        }

        else if(input == 24 || input == 25)
        {
            tv.setText("+7");
        }

        else if(input == 26 || input == 27)
        {
            tv.setText("+8");
        }

        else if(input == 28 || input == 29)
        {
            tv.setText("+9");
        }

        else
        {
            tv.setText("+10");
        }

    }
}
