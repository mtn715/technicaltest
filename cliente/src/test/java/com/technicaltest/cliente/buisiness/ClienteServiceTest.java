package com.technicaltest.cliente.buisiness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.technicaltest.cliente.business.impl.ClienteServiceImpl;
import com.technicaltest.cliente.client.exception.RegistroDuplicadoException;
import com.technicaltest.cliente.model.entity.Cliente;
import com.technicaltest.cliente.model.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ClienteServiceTest {
    @InjectMocks
    private ClienteServiceImpl clienteService;
    @Mock
    private ClienteRepository clienteRepository;

    @Test
    void insertarCliente_CuandoNoExiste_test() {

        Cliente cliente = new Cliente();
        cliente.setIdentificacion(123456789L);

        when(clienteRepository.getClienteByIdentificacion(cliente.getIdentificacion())).thenReturn(null);


        clienteService.insertCliente(cliente);

        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void insertarCliente_CuandoClienteExiste_test() {

        Cliente cliente = new Cliente();
        cliente.setIdentificacion(123456789L);

        when(clienteRepository.getClienteByIdentificacion(cliente.getIdentificacion())).thenReturn(new Cliente());

        assertThrows(RegistroDuplicadoException.class, () -> clienteService.insertCliente(cliente));
    }
}
