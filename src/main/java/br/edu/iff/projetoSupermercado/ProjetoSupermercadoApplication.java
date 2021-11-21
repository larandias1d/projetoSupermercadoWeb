package br.edu.iff.projetoSupermercado;

import br.edu.iff.projetoSupermercado.model.Cliente;
import br.edu.iff.projetoSupermercado.model.Endereco;
import br.edu.iff.projetoSupermercado.model.Fornecedor;
import br.edu.iff.projetoSupermercado.model.Funcionario;
import br.edu.iff.projetoSupermercado.model.Item_venda;
import br.edu.iff.projetoSupermercado.model.Produto;
import br.edu.iff.projetoSupermercado.model.Telefone;
import br.edu.iff.projetoSupermercado.model.Venda;
import br.edu.iff.projetoSupermercado.repository.ClienteRepository;
import br.edu.iff.projetoSupermercado.repository.FornecedorRepository;
import br.edu.iff.projetoSupermercado.repository.FuncionarioRepository;
import br.edu.iff.projetoSupermercado.repository.ProdutoRepository;
import br.edu.iff.projetoSupermercado.repository.VendaRepository;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetoSupermercadoApplication implements CommandLineRunner{
    
    @Autowired
    private ClienteRepository clienteRepo;
    
    @Autowired
    private FuncionarioRepository funcionarioRepo;
    
    
    @Autowired
    private FornecedorRepository fornecedorRepo;
         
    @Autowired
    private VendaRepository vendaRepo;
    
    @Autowired
    private ProdutoRepository produtoRepo;
       
   
	public static void main(String[] args) {
		SpringApplication.run(ProjetoSupermercadoApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        
        //CLIENTE
        
        Cliente c1 = new Cliente();
        c1.setNome("Lara");
        c1.setCpf("305.075.990-98");
        c1.setEmail("larandias@hotmail.com");
        
        Telefone t = new Telefone();
        t.setTel("(22)99999-9999");
        
        Endereco e = new Endereco();
        e.setRua("Rua Durval de Souza");
        e.setNumero(210);
        e.setBairro("Alphaville");
        e.setCidade("Campos");
        e.setCep("28024-330");
        
        c1.setTelefone(t);
        c1.setEndereco(e);
        clienteRepo.save(c1);
        
        Cliente c2 = new Cliente();
        c2.setNome("João");
        c2.setCpf("876.836.750-32");
        c2.setEmail("joao123@gmail.com");
        
        Telefone t2 = new Telefone();
        t2.setTel("(22)72722-1111");
        
        Endereco e2 = new Endereco();
        e2.setRua("Rua Durval de Souza");
        e2.setNumero(210);
        e2.setBairro("Alphaville");
        e2.setCidade("Campos");
        e2.setCep("28024-330");
        
        c2.setTelefone(t2);
        c2.setEndereco(e2);
        clienteRepo.save(c2);
        
        //FUNCIONARIO
        
        Funcionario f = new Funcionario();
        f.setNome("Maria");
        f.setCpf("684.712.130-01");
        f.setEmail("maria123@hotmail.com");
        f.setCargo("Gerente");
        f.setSalario(2400);
        f.setSenha("Maria-123");
        
        Telefone t3 = new Telefone();
        t3.setTel("(22)77777-7777");
        
        Endereco e3 = new Endereco();
        e3.setRua("Rua Das Flores");
        e3.setNumero(210);
        e3.setBairro("Parque das Flores");
        e3.setCidade("Campos");
        e3.setCep("28024-332");
        
        f.setTelefone(t3);
        f.setEndereco(e3);
        funcionarioRepo.save(f);
        
        
        Funcionario f2 = new Funcionario();
        f2.setNome("José");
        f2.setCpf("684.295.810-57");
        f2.setEmail("jose123@hotmail.com");
        f2.setCargo("Caixa");
        f2.setSalario(1800);
        f2.setSenha("Jose-123");
        
        Telefone t4 = new Telefone();
        t4.setTel("(22)10101-1111");
        
        Endereco e4 = new Endereco();
        e4.setRua("Rua Das Palmeniras");
        e4.setNumero(20);
        e4.setBairro("Parque das Palmeiras");
        e4.setCidade("Campos");
        e4.setCep("28024-333");
        
        f2.setTelefone(t4);
        f2.setEndereco(e4);       
        funcionarioRepo.save(f2);
     
        
        //FORNECEDOR
        
        Fornecedor fc = new Fornecedor();
        fc.setRazao_social("Supermecado");
        fc.setCnpj("94.962.714/0001-24");
        fc.setEmail("supermercado@gmail.com");
   
        
        Telefone t5 = new Telefone();
        t5.setTel("(22)22222-2222");
        
        Endereco e5 = new Endereco();
        e5.setRua("Rua do Leão");
        e5.setNumero(45);
        e5.setBairro("Parque Rosário");
        e5.setCidade("Campos");
        e5.setCep("28024-222");
        
        fc.setTelefone(t5);
        fc.setEndereco(e5);
        //fornecedorRepo.save(fc);
        
        
        Fornecedor fc2 = new Fornecedor();
        fc2.setRazao_social("Atacado Assaí");
        fc2.setCnpj("88.204.281/0001-45");
        fc2.setEmail("atacadoassai@gmail.com");
   
        
        Telefone t6 = new Telefone();
        t6.setTel("(22)34234-0000");
        
        Endereco e6 = new Endereco();
        e6.setRua("Athur Bernardes");
        e6.setNumero(45);
        e6.setBairro("Turf");
        e6.setCidade("Campos");
        e6.setCep("28024-333");
        
        fc2.setTelefone(t6);
        fc.setEndereco(e6);
        //fornecedorRepo.save(fc2);
        
        
        
        //PRODUTO
        
        Produto p = new Produto();
        p.setDescricao("Nescau");
        p.setValor(8.00);
        p.setQtde_estoque(20);
        p.setFornecedor((List.of(fc, fc2));

        produtoRepo.save(p);
       

        //produto 2
        Produto p2 = new Produto();
        p2.setDescricao("Leite Condensado");
        p2.setValor(4.50);
        p2.setQtde_estoque(30); 
        p2.setFornecedor((List.of(fc, fc2));
        produtoRepo.save(p2);

        
        
        
        //VENDA
        
        Venda v = new Venda();
        Calendar datahora = Calendar.getInstance();
        datahora.set(2021, 10, 12);
        v.setDatahora(datahora);
        v.setTotal(350);
        v.setCliente(c1);
        v.setFuncionario(f); 
        
        
        //ITEM  VENDA
        Item_venda i = new Item_venda();
        i.setQtde(2);
        i.setSubtotal(16);
        i.setProduto(p);

        Item_venda i2 = new Item_venda();
        i2.setQtde(6);
        i2.setSubtotal(27);
        i2.setProduto(p2);
        
        v.setItem_vendas(List.of(i, i2));
        vendaRepo.save(v);
        
    }

}
