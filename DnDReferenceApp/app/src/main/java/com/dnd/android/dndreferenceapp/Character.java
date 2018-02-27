package com.dnd.android.dndreferenceapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class stores character data in the internal storage. This may be removed when Firebase is implemented
 */

public class Character implements Serializable {
    private String characterName;
    private String playerName;
    private String race;
    private String background;
    private int experience;
    private int cumLevel;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private int proficiency;
    private int armorClass;
    private int initiative;
    private int speed;
    private boolean inspiration;
    private ArrayList<Classes> classes;

    /**
     * Constructor to initialize all values.
     */
    public Character()
    {
        characterName = "";
        playerName = "";
        race = "";
        background = "";
        experience = 0;
        cumLevel = 0;
        strength = 1;
        dexterity = 1;
        constitution = 1;
        intelligence = 1;
        wisdom = 1;
        charisma = 1;
        proficiency = 0;
        armorClass = 0;
        initiative = 0;
        speed = 0;
        inspiration = false;
        classes = new ArrayList<Classes>();
        Classes a = new Classes("Warlock");
        Classes b = new Classes("Warrior");
        Classes c = new Classes("Ranger");
        classes.add(a);
        classes.add(b);
        classes.add(c);
    }

    public void addClass(Classes newClass)
    {
        classes.add(newClass);
    }
    public void print(){
        System.out.println("Character Name: " + characterName);
        System.out.println("Player Name: " + playerName);
        System.out.println("Race: " + race);
        System.out.println("Background: " + background);
        System.out.println("Experience: " + experience);
        System.out.println("Cumulative Level: " + cumLevel);
        System.out.println("Strength: " + strength);
        System.out.println("Dexterity: " + dexterity);
        System.out.println("Constitution: " + constitution);
        System.out.println("Intelligence: " + intelligence);
        System.out.println("Wisdom: " + wisdom);
        System.out.println("Charisma: " + charisma);
        System.out.println("Proficiency: " + proficiency);
        System.out.println("Armor Class: " + armorClass);
        System.out.println("Initiative: " + initiative);
        System.out.println("Speed: " + speed);
        System.out.println("Inspiration: " + inspiration);
    }

    //Setters
    public void setCharacterName(String input){
        characterName = input;
    }
    public void setPlayerName(String input){
        playerName = input;
    }
    public void setRace(String input){
        race = input;
    }
    public void setBackground(String input){
        background = input;
    }
    public void setExperience(int input){
        experience = input;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }
    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }
    public void setInspiration(boolean inspiration) {
        this.inspiration = inspiration;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }
    public void setCumLevel(int cumLevel) {
        this.cumLevel = cumLevel;
    }
    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }
    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    //Getters
    public String getCharacterName(){
        return characterName;
    }
    public String getPlayerName(){
        return playerName;
    }
    public String getRace(){
        return race;
    }
    public String getBackground(){
        return background;
    }
    public int getExperience(){
        return experience;
    }
    public int getCumLevel(){
        return cumLevel;
    }
    public int getStrength() {
        return strength;
    }
    public int getDexterity() {
        return dexterity;
    }
    public int getConstitution() {
        return constitution;
    }
    public int getIntelligence() {
        return intelligence;
    }
    public int getWisdom() {
        return wisdom;
    }
    public int getCharisma() {
        return charisma;
    }
    public int getProficiency() {
        return proficiency;
    }
    public int getArmorClass() {
        return armorClass;
    }
    public int getInitiative() {
        return initiative;
    }
    public int getSpeed() {
        return speed;
    }
    public boolean isInspiration() {
        return inspiration;
    }
    public ArrayList<Classes> getClasses()
    {
        return classes;
    }
}
