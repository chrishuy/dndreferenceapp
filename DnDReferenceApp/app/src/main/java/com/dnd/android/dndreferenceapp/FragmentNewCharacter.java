package com.dnd.android.dndreferenceapp;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNewCharacter extends Fragment {


    public FragmentNewCharacter() {
        // Required empty public constructor
    }

    private EditText pName, cName, race, background;
    private Button save;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_character, container, false);

        //Initialize widgets
        pName = (EditText) v.findViewById(R.id.etPlayerName);
        cName = (EditText) v.findViewById(R.id.etCharacterName);
        race = (EditText) v.findViewById(R.id.etRace);
        background = (EditText) v.findViewById(R.id.etBackground);
        save = (Button) v.findViewById(R.id.bSave);

        //Object reference to save data
        final Character refPlayer = ((HomeActivity)getActivity()).player;

        //Sets edit texts to saved values
        pName.setText(refPlayer.getPlayerName());
        cName.setText(refPlayer.getCharacterName());
        race.setText(refPlayer.getRace());
        background.setText(refPlayer.getBackground());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refPlayer.setPlayerName(pName.getText().toString());
                refPlayer.setCharacterName(cName.getText().toString());
                refPlayer.setRace(race.getText().toString());
                refPlayer.setBackground(background.getText().toString());
                FragmentTransaction t = getFragmentManager().beginTransaction();
                t.replace(R.id.lContent, new FragmentProfile(), "1");
                t.commit();
            }
        });

        return v;
    }

}
