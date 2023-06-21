package com.moviereservation.utils.reservation;

public abstract class AgeCalc {
    public abstract void setValues(int[] values);

    public int[] getUpdatedValues(){
        int[] updatedValues = new int[4];
        setValues(updatedValues);
        return updatedValues;
    }
}