package database;

import model.Fazenda;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    private static final String FAZENDAS_CSV = "fazendas.csv";
    private static final String RESOURCES_PATH = "./src/main/resources/";

    public static List<Fazenda> getFazendas() throws IOException {


        Path fazendasPath = Paths.get(System.getProperty("user.dir") + RESOURCES_PATH + FAZENDAS_CSV);
        List<Fazenda> returningValues = new ArrayList<>();

        Reader reader = Files.newBufferedReader(fazendasPath);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withDelimiter(';')
                .withQuote(null)
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim());

        for (CSVRecord csvRecord : csvParser) {
            // Accessing values by Header names
            String idFazenda = csvRecord.get("Id fazenda");
            String nomeDaFazenda = csvRecord.get("Nome da fazenda");
            String nomeDoAgricultor = csvRecord.get("Nome do agricultor");
            String geoJSON = csvRecord.get("GeoJSON");

            returningValues.add(new Fazenda(idFazenda, nomeDaFazenda, nomeDoAgricultor, geoJSON));
        }

        return returningValues;
    }

}
