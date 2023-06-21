package com.moviereservation.utils.reservation;

public class AdultCalc extends AgeCalc{
    @Override
    public void setValues(int[] values) {
        values[0] = 1;
        values[3] = 10000;
    }
}
