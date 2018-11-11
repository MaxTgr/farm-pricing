package model;

public class Servico {

//    Identificador do serviço;Nome do serviço;GeoJSON
    private String mId;
    private String mNome;
    private String mGeoJSON;

    public Servico(String id, String nome, String geoJSON){
        mId = id;
        mNome = nome;
        mGeoJSON = geoJSON;
    }

    public String getId() {
        return mId;
    }

    public String getNome() {
        return mNome;
    }

    public String getGeoJSON() {
        return mGeoJSON;
    }
}
