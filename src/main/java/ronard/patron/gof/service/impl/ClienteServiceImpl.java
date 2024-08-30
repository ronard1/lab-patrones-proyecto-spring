package ronard.patron.gof.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ronard.patron.gof.model.Cliente;
import ronard.patron.gof.model.ClienteRepository;
import ronard.patron.gof.model.Direccion;
import ronard.patron.gof.model.DireccionRepository;
import ronard.patron.gof.service.ClienteService;
import ronard.patron.gof.service.ViaCepService;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    // Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private DireccionRepository direccionRepository;
    @Autowired
    private ViaCepService viaCepService;

    // Strategy: Implementar os métodos definidos na interface.
    // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Cliente> buscarTodos() {
        // Buscar todos os Clientes.
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        // Buscar Cliente por ID.
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void insertar(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void actualizar(Long id, Cliente cliente) {
        // Buscar Cliente por ID, caso exista:
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void borrar(Long id) {
        // Deletar Cliente por ID.
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        // Verificar se o Endereco do Cliente já existe (pelo CEP).
        String cep = cliente.getDireccion().getCep();
        Direccion direccion = direccionRepository.findById(cep).orElseGet(() -> {
            // Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Direccion novoEndereco = viaCepService.consultarCep(cep);
            direccionRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setDireccion(direccion);
        // Inserir Cliente, vinculando o Endereco (novo ou existente).
        clienteRepository.save(cliente);
    }
}
