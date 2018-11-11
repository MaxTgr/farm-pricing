package api;

import static org.hamcrest.Matchers.equalTo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestTest {
    @Autowired
    private MockMvc mvc;

    private Boolean JsonTest(String test){
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void getFazendas() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/fazendas").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(not(isEmptyString())));
    }

    @Test
    public void getValidJsonInFazendas() throws Exception {
        MvcResult res = mvc.perform(MockMvcRequestBuilders.get("/fazendas").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        JsonTest(res.getResponse().getContentAsString());
    }

    @Test
    public void getServicos() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/servicos").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(not(isEmptyString())));
    }

    @Test
    public void getValidJsonInServicos() throws Exception {
        MvcResult res = mvc.perform(MockMvcRequestBuilders.get("/servicos").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        JsonTest(res.getResponse().getContentAsString());
    }

    @Test
    public void getIndex() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }

}
