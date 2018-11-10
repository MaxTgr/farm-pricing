package database;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CSVLoader {

    private static final String FAZENDAS_CSV = "fazendas.csv";
    private static final String RESOURCES_PATH = "./src/main/resources/";

    //TODO:fix file loading
    public static String getFazendas() throws IOException, URISyntaxException {


        Path fazendasPath = Paths.get(System.getProperty("user.dir") + RESOURCES_PATH + FAZENDAS_CSV);

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

            System.out.println("Record No - " + csvRecord.getRecordNumber());
            System.out.println("---------------");
            System.out.println("Id fazenda : " + idFazenda);
            System.out.println("Nome da fazenda : " + nomeDaFazenda);
            System.out.println("Nome do agricultor : " + nomeDoAgricultor);
            System.out.println("GeoJSON : " + geoJSON);
            System.out.println("---------------\n\n");
        }

        return "";
    }

}
