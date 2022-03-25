package com.math.api.math;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MathApiTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void maxValueTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/max?numbers=15,20,35,40,50"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("50.0"))
                .andReturn();
    }

    @Test
    public void maxValueErrorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/max?numbers="))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Invalid list\"}"))
                .andReturn();
    }

    @Test
    public void minValueTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/min?numbers=15,20,35,40,50"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("15.0"))
                .andReturn();
    }

    @Test
    public void mingValueErrorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/min?numbers="))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Invalid list\"}"))
                .andReturn();
    }

    @Test
    public void avgValueTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/avg?numbers=15,20,35,40,50"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("32.0"))
                .andReturn();
    }


    @Test
    public void avgValueErrorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/avg?numbers="))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Invalid list\"}"))
                .andReturn();
    }

    @Test
    public void medianValueTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/median?numbers=15,20,35,40,50"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("35.0"))
                .andReturn();
    }

    @Test
    public void medianValueErrorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/median?numbers="))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Invalid list\"}"))
                .andReturn();
    }

    @Test
    public void percentileValueTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/percentile?numbers=15,20,35,40,50"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[15.0,20.0,20.0,35.0,35.0]"))
                .andReturn();
    }

    @Test
    public void percentileValueErrorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/percentile?numbers="))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Invalid list\"}"))
                .andReturn();
    }
}
