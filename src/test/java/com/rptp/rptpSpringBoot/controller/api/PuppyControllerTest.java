package com.rptp.rptpSpringBoot.controller.api;

import com.rptp.rptpSpringBoot.controller.api.common.documentation.DocumentationWithSecurity;
import com.rptp.rptpSpringBoot.core.puppy.dto.PuppyRequest;
import com.rptp.rptpSpringBoot.core.puppy.service.PuppyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PuppyController.class)
@ExtendWith(RestDocumentationExtension.class)
class PuppyControllerTest extends DocumentationWithSecurity {

    @MockBean
    private PuppyService puppyService;

    @Test
    @DisplayName("Puppy 전체 조회")
    public void getPuppies() throws Exception {
        mockMvc.perform(get("/api/puppy"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Puppy 조회")
    public void getPuppy() throws Exception {
        mockMvc.perform(get("/api/puppy/{id}",1L))
                .andExpect(status().isOk())
                .andDo(print());
    }

}