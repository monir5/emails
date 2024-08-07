package de.monir.example.emails;

import de.monir.example.emails.mapper.EmailMapper;
import de.monir.example.emails.model.Email;
import de.monir.example.emails.repository.EmailRepository;
import de.monir.example.emails.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static de.monir.example.emails.EmailTestUtil.asJsonString;
import static de.monir.example.emails.EmailTestUtil.getDraftEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class EmailsApplicationTests {

	@Autowired
	private EmailService emailService;
	@Autowired
	private EmailRepository emailRepository;
	@Autowired
	private EmailMapper emailMapper;
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private Environment environment;

	private MockMvc mockMvc;

	@BeforeEach
    public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testCreateEmail() throws Exception {
		String emailBody = "Test email body";
		String emailFrom = Math.random()+"test@monir.de";
		Email email = getDraftEmail();
		email.setBody(emailBody);
		email.setFrom(emailFrom);
		mockMvc.perform(post("/emails/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(email))
				).andExpect(status().isCreated());

		List<Email> savedEmails = emailRepository.findAllByFromContainingIgnoreCase(emailFrom);
		assertEquals(1, savedEmails.size());
		assertEquals(emailBody, savedEmails.get(0).getBody());
		assertEquals(emailFrom, savedEmails.get(0).getFrom());
	}

	@Test
	void contextLoads() {
	}

}
