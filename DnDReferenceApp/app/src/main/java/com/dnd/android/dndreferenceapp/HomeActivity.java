package com.dnd.android.dndreferenceapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HomeActivity extends AppCompatActivity {

    Character player;
    File characterData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(fileExists(this,"CharacterData.txt"))
        {
            try {
                FileInputStream fis = this.openFileInput("CharacterData.txt");
                ObjectInputStream is = new ObjectInputStream(fis);
                player = (Character) is.readObject();
                is.close();
                fis.close();
            }catch(FileNotFoundException e) {
                e.printStackTrace();
            }catch(IOException e) {
                e.printStackTrace();
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
            System.out.println("Character loaded in.");
            player.print();
            FragmentTransaction t = getFragmentManager().beginTransaction();
            t.add(R.id.lContent, new FragmentProfile(), "1");
            t.commit();
        }
        else {
            player = new Character();
            System.out.println("New character created");
            FragmentTransaction t = getFragmentManager().beginTransaction();
            t.add(R.id.lContent, new FragmentNewCharacter(), "0");
            t.commit();
        }

        BottomNavigationView navMenu = (BottomNavigationView) findViewById(R.id.navigation);
        navMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fg = getFragmentManager();
                FragmentTransaction transaction = fg.beginTransaction();
                switch(item.getItemId()){
                    case R.id.nProfile:
                        if(fg.findFragmentByTag("1") == null)
                        {
                            transaction.replace(R.id.lContent, new FragmentProfile(), "1");
                            transaction.commit();
                        }
                        break;
                    case R.id.nAttacks:
                        if(fg.findFragmentByTag("2") == null)
                        {
                            transaction.replace(R.id.lContent, new FragmentAttacks(), "2");
                            transaction.commit();
                        }
                        break;
                    case R.id.nAttributes:
                        if(fg.findFragmentByTag("3") == null)
                        {
                            transaction.replace(R.id.lContent, new FragmentAttributes(), "3");
                            transaction.commit();
                        }
                        break;
                    case R.id.nNotes:
                        if(fg.findFragmentByTag("4") == null)
                        {
                            transaction.replace(R.id.lContent, new FragmentNotes(), "4");
                            transaction.commit();
                        }
                        break;
                }
                return true;
            }
        });
    }


    protected void onPause()
    {
        super.onPause();
        try {
            FileOutputStream fos = this.openFileOutput("CharacterData.txt", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(player);
            os.close();
            fos.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("Character saved");
    }

    /**
     * This method checks to see if the character file exists before opening the file
     * @param context The activity context
     * @param filename Name of the file to be read in
     * @return boolean that reflects the status of the file in internal memory
     */
    public boolean fileExists(Context context, String filename) {
        File file = context.getFileStreamPath(filename);
        if(file == null || !file.exists()) {
            return false;
        }
        return true;
    }

}
