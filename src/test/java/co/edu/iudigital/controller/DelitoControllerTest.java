package co.edu.iudigital.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import co.edu.iudigital.service.IDelitoService;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class DelitoControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private IDelitoService delitoService;
    
    @BeforeEach
    public void setup() throws Exception{
    	//delitoService.findAll();
    }
    
    @Test
    public void indexTest() throws Exception{
    	
        mvc.perform(
                get(new URI("/delitos"))
                        )
                .andExpect(status().isOk());
        
        verify(delitoService, times(1)).findAll();
    }
}
