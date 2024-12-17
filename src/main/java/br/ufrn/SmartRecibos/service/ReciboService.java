package br.ufrn.SmartRecibos.service;

import br.ufrn.SmartRecibos.dto.ReciboRequest;
import br.ufrn.SmartRecibos.model.Cliente;
import br.ufrn.SmartRecibos.model.Funcionario;
import br.ufrn.SmartRecibos.model.Recibo;
import br.ufrn.SmartRecibos.repository.ClienteRepository;
import br.ufrn.SmartRecibos.repository.FuncionarioRepository;
import br.ufrn.SmartRecibos.repository.ReciboRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReciboService {
    private final ReciboRepository reciboRepository;
    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ReciboService(ReciboRepository reciboRepository,
            ClienteRepository clienteRepository,
            FuncionarioRepository funcionarioRepository) {
        this.reciboRepository = reciboRepository;
        this.clienteRepository = clienteRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Recibo> findAll() {
        return reciboRepository.findAll();
    }

    public Recibo findById(Long id) {
        return reciboRepository.findById(id).orElse(null);
    }

    public Recibo save(ReciboRequest request) {

        Recibo recibo = new Recibo();
        recibo.setValor(request.valor());
        recibo.setDescricao(request.descricao());

        recibo.setDataCriacao(request.dataCriacao());
        recibo.setStatus(request.status());

        Cliente cliente = clienteRepository.findById(request.cliente_id())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        recibo.setCliente(cliente);

        Funcionario funcionario = funcionarioRepository.findById(request.funcionario_id())
                .orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));
        recibo.setFuncionario(funcionario);

        return reciboRepository.save(recibo);
    }

    public void delete(Long id) {
        reciboRepository.deleteById(id);
    }
}
