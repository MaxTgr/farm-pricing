package api;

import database.CSVLoader;
import model.Fazenda;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Rest {

    @RequestMapping(value = "/", produces = "application/json")
    public String index() {

        List<Fazenda> fazendas = new ArrayList<>();

        try {
            fazendas = CSVLoader.getFazendas();
        } catch (IOException e) {
            System.out.println(e); //TODO: better exception handler
        }

        JSONObject json = new JSONObject();

        try {
            for (Fazenda fazenda : fazendas) {
                JSONObject obj = new JSONObject();
                obj.put("agricultor", fazenda.getAgricultor());
                obj.put("fazenda", fazenda.getFazenda());
                obj.put("geoJSON", new JSONObject(fazenda.getGeoJSON()));
                json.put(fazenda.getId(), obj);
            }
        } catch (JSONException e) {
            System.out.println(e); //TODO: better exception handler
        }

        return json.toString();
    }

}
