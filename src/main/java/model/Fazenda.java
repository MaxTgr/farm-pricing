package model;

import org.json.JSONException;
import org.json.JSONObject;

public class Fazenda{

    private String mId;
    private String mFazenda;
    private String mAgricultor;
    private String mGeoJSON;

    public Fazenda(String id,String fazenda,String agricultor,String geoGson){
        mId = id;
        mFazenda = fazenda;
        mAgricultor = agricultor;
        mGeoJSON = geoGson;
    }

    public String getId() {
        return mId;
    }

    public String getFazenda() {
        return mFazenda;
    }

    public String getAgricultor() {
        return mAgricultor;
    }

    public String getGeoJSON() {
        return mGeoJSON;
    }
}
