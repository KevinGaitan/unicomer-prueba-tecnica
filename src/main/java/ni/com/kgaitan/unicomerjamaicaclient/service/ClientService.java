package ni.com.kgaitan.unicomerjamaicaclient.service;

import ni.com.kgaitan.unicomerjamaicaclient.entity.Client;
import ni.com.kgaitan.unicomerjamaicaclient.resource.request.ClientRequest;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> getAllClients();

    Optional<Client> getClient(Long id);

    Client saveClient(ClientRequest clientRequest);

    Client saveClient(Long id, ClientRequest clientRequest);
}
