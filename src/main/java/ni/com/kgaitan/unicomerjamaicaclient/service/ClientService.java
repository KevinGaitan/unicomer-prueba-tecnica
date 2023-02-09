package ni.com.kgaitan.unicomerjamaicaclient.service;

import ni.com.kgaitan.unicomerjamaicaclient.entity.Client;
import ni.com.kgaitan.unicomerjamaicaclient.resource.request.ClientRequest;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> getAllClients();

    Optional<Client> getClient(Long id);

    Client createClient(ClientRequest clientRequest);

    Client updateClient(Long id, ClientRequest clientRequest);
}
