package de.monir.example.emails.controller;

import de.monir.example.emails.model.Email;
import de.monir.example.emails.service.EmailServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static de.monir.example.emails.EmailTestUtil.asJsonString;
import static de.monir.example.emails.EmailTestUtil.getDraftEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmailController.class)
public class EmailControllerTest {

    @MockBean
    private EmailServiceImpl emailService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createEmail_success() throws Exception {
        // When
        long emailId = 1L;
        Email email = getDraftEmail();
        Mockito.when(emailService.create(email)).thenReturn(email);
        RequestBuilder request = MockMvcRequestBuilders
                .post("/emails/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(email));
        // Call
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();
        // Verify
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
    }

    @Test
    public void createEmail_failed() throws Exception {
        // When
        long emailId = 1L;
        Email email = getDraftEmail();
        Mockito.when(emailService.create(email)).thenReturn(email);
        RequestBuilder request = MockMvcRequestBuilders
                .post("/emails/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("wrong json");
        // Call
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn();
        // Verify
        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }
}
