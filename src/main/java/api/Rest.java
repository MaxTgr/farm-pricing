package api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rest {

    @RequestMapping(value = "/fazendas", produces = "application/json")
    public String fazendas() {

        return Formatter.getFazendasJson().toString();
    }

    @RequestMapping(value = "/servicos", produces = "application/json")
    public String servicos() {

        return Formatter.getServicosJson().toString();
    }

}
