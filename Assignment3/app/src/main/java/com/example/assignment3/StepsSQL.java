package com.example.assignment3;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;

import java.util.Date;
@Entity
public class StepsSQL {

@PrimaryKey(autoGenerate = true)
public int id;
@ColumnInfo( name ="date_time")
public String dateTime;
@ColumnInfo( name ="totalstepstaken")
public int stepsTaken;
    public StepsSQL(String dateTime,int stepsTaken) {
        this.dateTime=dateTime;
        this.stepsTaken=stepsTaken;

    }
    public int getId(){
        return id;
    }
    public void setId(){
        this.id=id;
    }
    public String getDateTime(){
        return dateTime;
    }
    public void setDateTime(){
        this.dateTime=dateTime;
    }
    public int getStepsTaken() {
        return stepsTaken;
    }
    public void setStepsTaken() {
        this.stepsTaken = stepsTaken;
    }



}
