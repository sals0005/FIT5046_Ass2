package com.example.assignment3;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.Date;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface StepsDAO {
    @Query("SELECT * FROM StepsSQL")
    List<StepsSQL> getAll();
    @Query("SELECT * FROM StepsSQL WHERE date_time = :date_time  LIMIT 1")
    StepsSQL findByDateAndTime(String date_time);
    @Query("SELECT * FROM StepsSQL WHERE id = :id LIMIT 1")
    StepsSQL findById(int id);
    @Insert
    void insertAll(StepsSQL... stepsSQLS);
    @Insert
    long insert(StepsSQL stepsSQL);
    @Delete
    void delete(StepsSQL stepsSQL);
    @Update(onConflict = REPLACE)
    public void updateUsers(StepsSQL... stepsSQLS);
    @Query("DELETE FROM StepsSQL")
    void deleteAll();
}
