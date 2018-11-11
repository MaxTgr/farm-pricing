package api;

import database.CSVLoader;
import model.Fazenda;
import model.Servico;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Formatter {

    public static JSONArray getFazendasJson(){

        List<Fazenda> fazendas = new ArrayList<>();

        // getting the array of objects from the api
        try {
            fazendas = CSVLoader.getFazendas();
        } catch (IOException e) {
            System.out.println(e);
        }

        JSONArray json = new JSONArray();

        try {
            // creating json objects and pushing them to the json array
            for (Fazenda fazenda : fazendas) {
                // the pushing was made like this for easier debugging, as doing so doesn't hurt performance
                JSONObject obj = new JSONObject();
                obj.put("agricultor", fazenda.getAgricultor());
                obj.put("fazenda", fazenda.getFazenda());
                obj.put("geoJSON", new JSONObject(fazenda.getGeoJSON()));
                obj.put("id", fazenda.getId());

                json.put(obj);
            }
        } catch (JSONException e){
            System.out.println(e);
        }

        return json;
    }

    public static JSONArray getServicosJson(){

        List<Servico> servicos = new ArrayList<>();

        try {
            servicos = CSVLoader.getServicos();
        } catch (IOException e) {
            System.out.println(e); 
        }

        JSONArray json = new JSONArray();

        try {
            for (Servico servico : servicos) {
                JSONObject obj = new JSONObject();
                obj.put("serviço", servico.getNome());
                obj.put("geoJSON", new JSONObject(servico.getGeoJSON()));
                obj.put("id", servico.getId());

                json.put(obj);
            }
        } catch (JSONException e){
            System.out.println(e); 
        }

        return json;

    }

}
