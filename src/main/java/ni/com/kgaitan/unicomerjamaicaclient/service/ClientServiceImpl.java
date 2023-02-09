package ni.com.kgaitan.unicomerjamaicaclient.service;

import lombok.RequiredArgsConstructor;
import ni.com.kgaitan.unicomerjamaicaclient.entity.Client;
import ni.com.kgaitan.unicomerjamaicaclient.exception.ClientNotFoundException;
import ni.com.kgaitan.unicomerjamaicaclient.repository.ClientRepository;
import ni.com.kgaitan.unicomerjamaicaclient.resource.request.ClientRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<Client> getAllClients() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Optional<Client> getClient(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client createClient(ClientRequest clientRequest) {
        return saveClientRequest(clientRequest, new Client());
    }

    @Override
    public Client updateClient(Long id, ClientRequest clientRequest) {

        Optional<Client> clientOpt = clientRepository.findById(id);

        if (clientOpt.isEmpty()) {
            throw new ClientNotFoundException(String.format("Client %d is not found", id));
        }

        Client client = clientOpt.get();

        return saveClientRequest(clientRequest, client);
    }

    private Client saveClientRequest(ClientRequest clientRequest, Client client) {

        client.setFirstName(clientRequest.getFirstName());
        client.setLastName(clientRequest.getLastName());
        client.setBirthDate(clientRequest.getBirthDate());
        client.setGender(clientRequest.getGender());
        client.setCellPhone(clientRequest.getCellPhone());
        client.setHomePhone(clientRequest.getHomePhone());
        client.setAddressHome(clientRequest.getAddressHome());
        client.setProfession(clientRequest.getProfession());
        client.setIncomes(clientRequest.getIncomes());

        return clientRepository.save(client);
    }

}
