package com.coolweather.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2016/8/31.
 */
public class CoolWeatherDB
{
    /**
     * 数据库名
     */
    public static final String DB_NAME = "cool_weather";

    /**
     * 数据库版本号
     */
    public static final int VERSION = 1;

    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;

    private CoolWeatherDB(Context context)
    {
        CoolWeatherOpenHelper coolWeatherOpenHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = coolWeatherOpenHelper.getWritableDatabase();
    }

    public synchronized static CoolWeatherDB getInstance(Context context)
    {
        if (coolWeatherDB == null)
            coolWeatherDB = new CoolWeatherDB(context);
        return coolWeatherDB;
    }

    public void saveProvince(Province province)
    {
        ContentValues values = new ContentValues();
        values.put("province_code", province.getProvince_code());
        values.put("province_name", province.getProvince_name());
        db.insert("Province", null, values);
    }

    public List<Province> loadProvince()
    {
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.rawQuery("select * from Province", null);

        while (cursor.moveToNext())
        {
            Province province = new Province();
            province.setId(cursor.getInt(cursor.getColumnIndex("id")));
            province.setProvince_code(cursor.getString(cursor.getColumnIndex("Province_code")));
            province.setProvince_name(cursor.getString(cursor.getColumnIndex("Province_name")));
            list.add(province);
        }

        return list;
    }

    public void saveCounty(County county)
    {
        ContentValues values = new ContentValues();
        values.put("City_id", county.getCity_id());
        values.put("County_code", county.getCounty_code());
        values.put("County_name", county.getCounty_name());
        db.insert("County", null, values);
    }

    public List<County> loadCounty()
    {
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.rawQuery("select * from County", null);

        while (cursor.moveToNext())
        {
            County County = new County();
            County.setId(cursor.getInt(cursor.getColumnIndex("id")));
            County.setCounty_code(cursor.getString(cursor.getColumnIndex("County_code")));
            County.setCounty_name(cursor.getString(cursor.getColumnIndex("County_name")));
            list.add(County);
        }

        return list;
    }

    public void saveCity(City city)
    {
        ContentValues values = new ContentValues();
        values.put("City_code", city.getCity_code());
        values.put("City_name", city.getCity_name());
        values.put("Province_id", city.getProvince_id());
        db.insert("City", null, values);
    }

    public List<City> loadCity()
    {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.rawQuery("select * from City", null);

        while (cursor.moveToNext())
        {
            City City = new City();
            City.setId(cursor.getInt(cursor.getColumnIndex("id")));
            City.setCity_code(cursor.getString(cursor.getColumnIndex("City_code")));
            City.setCity_name(cursor.getString(cursor.getColumnIndex("City_name")));
            City.setProvince_id(cursor.getInt(cursor.getColumnIndex("Province_id")));
            list.add(City);
        }

        return list;
    }
}
