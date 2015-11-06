package br.com.fuzzy.robot;

import simbad.gui.Simbad;

public class Simulator {

    public static void main(String[] args) {
        // request antialising
        System.setProperty("j3d.implicitAntialiasing", "true");
        // create Simbad instance with given environment
        new Simbad(new InitialEnvironment(), false);
    }

} 