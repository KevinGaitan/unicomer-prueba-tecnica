package ni.com.kgaitan.unicomerjamaicaclient.resource;

import lombok.RequiredArgsConstructor;
import ni.com.kgaitan.unicomerjamaicaclient.entity.Client;
import ni.com.kgaitan.unicomerjamaicaclient.exception.ClientNotFoundException;
import ni.com.kgaitan.unicomerjamaicaclient.resource.request.ClientRequest;
import ni.com.kgaitan.unicomerjamaicaclient.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") Long id) {
        Optional<Client> client = clientService.getClient(id);

        if (client.isEmpty()) {
            throw new ClientNotFoundException(String.format("User %d is not found", id));
        }

        return ResponseEntity.ok(client.get());
    }

    @PostMapping
    public ResponseEntity<Client> saveClient(@RequestBody @Valid ClientRequest clientRequest) {

        Client client = clientService.createClient(clientRequest);

        if (client == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(client.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody @Valid ClientRequest clientRequest) {
        return ResponseEntity.ok(clientService.updateClient(id, clientRequest));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
