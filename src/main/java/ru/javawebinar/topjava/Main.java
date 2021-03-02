/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2021 VTB Group. All rights reserved.
 */

package ru.javawebinar.topjava;

import java.util.EnumSet;

/**
 * Main.
 *
 * @author ADivaev
 */

enum Bands {
    DDT, BI2, SPLIN, U2;
};

public class Main {
    public static void main(String[] args) {
        Bands[] bands = {Bands.BI2,Bands.SPLIN, Bands.DDT};
        EnumSet<Bands> set = EnumSet.of(Bands.DDT, bands);
        System.out.println(set);
    }
}