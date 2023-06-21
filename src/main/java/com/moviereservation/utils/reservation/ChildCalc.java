package com.moviereservation.utils.reservation;

public class ChildCalc  extends AgeCalc{
    @Override
    public void setValues(int[] values) {
        values[0] = 1;
        values[3] = 6000;
    }
}
