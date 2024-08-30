package ronard.patron.gof.service;

import ronard.patron.gof.model.Cliente;

public interface ClienteService {
    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    void insertar(Cliente cliente);

    void actualizar(Long id, Cliente cliente);

    void borrar(Long id);
}
