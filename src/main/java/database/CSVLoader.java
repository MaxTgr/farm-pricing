package database;

import model.Fazenda;
import model.Servico;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    private static final String FAZENDAS_CSV = "fazendas.csv";
    private static final String SERVICOS_CSV = "servicos.csv";
    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "./src/main/resources/"; // project path + resources path

    public static List<Fazenda> getFazendas() throws IOException {
        Path fazendasPath = Paths.get( RESOURCES_PATH + FAZENDAS_CSV);
        List<Fazenda> returningValues = new ArrayList<>();

        CSVParser csvParser = reader(fazendasPath);

        // populate the list with the data, using the model class
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

    public static List<Servico> getServicos() throws IOException {
        Path servicosPath = Paths.get( RESOURCES_PATH + SERVICOS_CSV);
        List<Servico> returningValues = new ArrayList<>();

        CSVParser csvParser = reader(servicosPath);

        // populate the list with the data, using the model class
        for (CSVRecord csvRecord : csvParser) {
            // Accessing values by Header names
            String idServico = csvRecord.get("Identificador do serviço");
            String nomeDoServico = csvRecord.get("Nome do serviço");
            String geoJSON = csvRecord.get("GeoJSON");

            returningValues.add(new Servico(idServico, nomeDoServico, geoJSON));
        }

        return returningValues;
    }

    private static CSVParser reader(Path path) throws IOException{

        Reader reader = Files.newBufferedReader(path);
        return new CSVParser(reader, CSVFormat.DEFAULT
                .withDelimiter(';') // since the delimiter is not a comma it needs to be changed here
                .withQuote(null)
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim());

    }

}
