package ni.com.kgaitan.unicomerjamaicaclient.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import ni.com.kgaitan.unicomerjamaicaclient.entity.Client;
import ni.com.kgaitan.unicomerjamaicaclient.entity.Gender;
import ni.com.kgaitan.unicomerjamaicaclient.exception.ClientNotFoundException;
import ni.com.kgaitan.unicomerjamaicaclient.resource.request.ClientRequest;
import ni.com.kgaitan.unicomerjamaicaclient.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    private final Client johnDoe = new Client(1L, "John", "Doe", Date.valueOf(LocalDate.of(1997, 5, 24)),
            Gender.MALE, "1 999 999 9999", null, "A place", "Developer", BigDecimal.valueOf(100));

    @Test
    void getClientsItShouldBeOk() throws Exception {
        given(clientService.getAllClients()).willReturn(new ArrayList<>());

        mockMvc.perform(get("/api/v1/clients"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
    }

    @Test
    void getClientShouldBeNotFound() throws Exception {
        Long id = -1L;

        given(clientService.getClient(id)).willReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/clients/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void getClientShouldBeOk() throws Exception {
        Long id = 1L;

        given(clientService.getClient(id)).willReturn(Optional.of(johnDoe));

        mockMvc.perform(get("/api/v1/clients/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void saveClientShouldBeBadRequest() throws Exception {

        String request = "i'm a bad request";

        mockMvc.perform(post("/api/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(request)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void saveClientShouldBeCreated() throws Exception {


        ClientRequest clientRequest = new ClientRequest();

        clientRequest.setFirstName(johnDoe.getFirstName());
        clientRequest.setLastName(johnDoe.getLastName());
        clientRequest.setBirthDate(johnDoe.getBirthDate());
        clientRequest.setGender(johnDoe.getGender());
        clientRequest.setCellPhone(johnDoe.getCellPhone());
        clientRequest.setHomePhone(johnDoe.getHomePhone());
        clientRequest.setAddressHome(johnDoe.getAddressHome());
        clientRequest.setProfession(johnDoe.getProfession());
        clientRequest.setIncomes(johnDoe.getIncomes());

        given(clientService.createClient(clientRequest)).willReturn(johnDoe);


        mockMvc.perform(post("/api/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientRequest))
                )
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void updateClientShouldBeNotFound() throws Exception {


        Long id = -1L;

        ClientRequest clientRequest = new ClientRequest();

        clientRequest.setFirstName(johnDoe.getFirstName());
        clientRequest.setLastName(johnDoe.getLastName());
        clientRequest.setBirthDate(johnDoe.getBirthDate());
        clientRequest.setGender(johnDoe.getGender());
        clientRequest.setCellPhone(johnDoe.getCellPhone());
        clientRequest.setHomePhone(johnDoe.getHomePhone());
        clientRequest.setAddressHome(johnDoe.getAddressHome());
        clientRequest.setProfession(johnDoe.getProfession());
        clientRequest.setIncomes(johnDoe.getIncomes());

        given(clientService.updateClient(id, clientRequest)).willThrow(new ClientNotFoundException("Client is not found"));

        mockMvc.perform(put("/api/v1/clients/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientRequest))
                )
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void updateClientShouldBeOk() throws Exception {
        Long id = 1L;

        ClientRequest clientRequest = new ClientRequest();

        clientRequest.setFirstName(johnDoe.getFirstName());
        clientRequest.setLastName(johnDoe.getLastName());
        clientRequest.setBirthDate(johnDoe.getBirthDate());
        clientRequest.setGender(johnDoe.getGender());
        clientRequest.setCellPhone(johnDoe.getCellPhone());
        clientRequest.setHomePhone(johnDoe.getHomePhone());
        clientRequest.setAddressHome(johnDoe.getAddressHome());
        clientRequest.setProfession(johnDoe.getProfession());
        clientRequest.setIncomes(johnDoe.getIncomes());

        given(clientService.updateClient(id, clientRequest)).willReturn(johnDoe);

        mockMvc.perform(put("/api/v1/clients/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientRequest))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}