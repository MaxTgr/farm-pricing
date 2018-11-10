package api;

import database.CSVLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        //SpringApplication.run(App.class, args);
        try {
            CSVLoader.getFazendas();
        } catch (IOException e) {
            System.out.println(e);
        } catch (URISyntaxException e){
            System.out.println(e);
        }
    }

}
