package uk.jamesgarden.randomanimepicker.welcome;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import uk.jamesgarden.randomanimepicker.AbstractControllerTest;
import uk.jamesgarden.randomanimepicker.listupdate.ListUpdateService;
import uk.jamesgarden.randomanimepicker.maluser.MalUserService;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = WelcomeController.class)
class WelcomeControllerTest extends AbstractControllerTest {


  @MockBean
  private WelcomeFormValidator welcomeFormValidator;

  @MockBean
  private MalUserService malUserService;

  @MockBean
  private ListUpdateService listUpdateService;

  @Test
  void renderWelcomePage() throws Exception {
    mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("welcome"));

    verifyNoInteractions(welcomeFormValidator);
    verifyNoInteractions(malUserService);
    verifyNoInteractions(listUpdateService);
  }
}
