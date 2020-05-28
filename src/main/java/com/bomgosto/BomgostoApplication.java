package com.bomgosto;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bomgosto.domain.Categoria;
import com.bomgosto.domain.Cidade;
import com.bomgosto.domain.Cliente;
import com.bomgosto.domain.Endereco;
import com.bomgosto.domain.Estado;
import com.bomgosto.domain.ItemPedido;
import com.bomgosto.domain.Pagamento;
import com.bomgosto.domain.PagamentoComCartao;
import com.bomgosto.domain.PagamentoComDinheiro;
import com.bomgosto.domain.Pedido;
import com.bomgosto.domain.Produto;
import com.bomgosto.domain.enums.EstadoPagamento;
import com.bomgosto.domain.enums.TipoCliente;
import com.bomgosto.repositories.CategoriaRepository;
import com.bomgosto.repositories.CidadeRepository;
import com.bomgosto.repositories.ClienteRepository;
import com.bomgosto.repositories.EnderecoRepository;
import com.bomgosto.repositories.EstadoRepository;
import com.bomgosto.repositories.ItemPedidoRepository;
import com.bomgosto.repositories.PagamentoRepository;
import com.bomgosto.repositories.PedidoRepository;
import com.bomgosto.repositories.ProdutoRepository;

@SpringBootApplication
public class BomgostoApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private PagamentoRepository pagamentoRepository;
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public static void main(String[] args) {
        SpringApplication.run(BomgostoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // PRODUTOS
        Produto p1 = Produto.builder().nome("Pizza de Calabresa").tamanho("Grande").unidadeMedida("Gramas")
                .precoUnitario(new BigDecimal(30)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
        Produto p2 = Produto.builder().nome("Pizza de Frango").tamanho("Grande").unidadeMedida("Gramas")
                .precoUnitario(new BigDecimal(30)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
        Produto p3 = Produto.builder().nome("Suco de Laranja").tamanho("500").unidadeMedida("Milimetros")
                .precoUnitario(new BigDecimal(3)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();

        // CATEGORIAS
        Categoria cat1 = Categoria.builder().nome("Pizzas").produtos(new ArrayList<>()).build();
        Categoria cat2 = Categoria.builder().nome("Bebidas").produtos(new ArrayList<>()).build();
        Categoria cat3 = Categoria.builder().nome("Entradas").produtos(new ArrayList<>()).build();
        Categoria cat4 = Categoria.builder().nome("À La Carte").produtos(new ArrayList<>()).build();
        Categoria cat5 = Categoria.builder().nome("Peixes e Frutos do Mar").produtos(new ArrayList<>()).build();
        Categoria cat6 = Categoria.builder().nome("Pratos Individuais").produtos(new ArrayList<>()).build();
        Categoria cat7 = Categoria.builder().nome("Massas").produtos(new ArrayList<>()).build();
        Categoria cat8 = Categoria.builder().nome("Lanches").produtos(new ArrayList<>()).build();
        Categoria cat9 = Categoria.builder().nome("Crepes").produtos(new ArrayList<>()).build();
        Categoria cat10 = Categoria.builder().nome("Sobremesas").produtos(new ArrayList<>()).build();
        Categoria cat11 = Categoria.builder().nome("Sucos e Cremes").produtos(new ArrayList<>()).build();
        Categoria cat12 = Categoria.builder().nome("Happy Hour").produtos(new ArrayList<>()).build();
        

        cat1.getProdutos().addAll(Arrays.asList(p1, p2));
        cat2.getProdutos().addAll(Collections.singletonList(p3));

        p1.getCategorias().addAll(Collections.singletonList(cat1));
        p2.getCategorias().addAll(Collections.singletonList(cat1));
        p3.getCategorias().addAll(Collections.singletonList(cat2));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        // ESTADOS
        Estado est1 = Estado.builder().nome("Distrito Federal").cidades(new ArrayList<>()).build();

        Cidade c1 = Cidade.builder().nome("Ceilândia").estado(est1).build();

        est1.getCidades().addAll(Collections.singletonList(c1));

        estadoRepository.saveAll(Collections.singletonList(est1));
        cidadeRepository.saveAll(Collections.singletonList(c1));

        Cliente cli1 = new Cliente(null, "Matheus Silva", "matheusb565@gmail.com", "06680395151",
                TipoCliente.PESSOA_FISICA);
        cli1.getTelefones().addAll(Arrays.asList("992706077", "991289851"));

        Endereco e1 = Endereco.builder().logradouro("QNO 13 CONJUNTO K").numero("09").complemento("SETOR O")
                .bairro("CEILÂNDIA").cep("72255311").cidade(c1).cliente(cli1).build();
        Endereco e2 = Endereco.builder().logradouro("QNO 03 CONJUNTO F").numero("33").complemento("SETOR O")
                .bairro("CEILÂNDIA").cep("72255306").cidade(c1).cliente(cli1).build();

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteRepository.saveAll(Collections.singletonList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1, e2));
        
        //PEDIDOS
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        Pedido ped1 = Pedido.builder().id(null).instante(sdf.parse("13/05/2020 13:01"))
        		.cliente(cli1).enderecoDeEntrega(e1).build();
        Pedido ped2 = Pedido.builder().id(null).instante(sdf.parse("10/10/2019 13:01"))
        		.cliente(cli1).enderecoDeEntrega(e2).build();
        
        Pagamento pagto1 = PagamentoComCartao.builder().id(null).estado(EstadoPagamento.QUITADO).pedido(ped1)
        		.numeroParcelas(6).build();
        ped1.setPagamento(pagto1);
        
        Pagamento pagto2 = PagamentoComDinheiro.builder().id(null).estado(EstadoPagamento.PENDENTE).pedido(ped2)
        		.dataPagamento(null).build();
        ped2.setPagamento(pagto2);
        
        cli1.setPedidos(Arrays.asList(ped1, ped2));
        
        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
        
        ItemPedido ip1 = ItemPedido.builder().pedido(ped1).produto(p1).desconto(BigDecimal.valueOf(0))
        		.quantidade(1).preco(BigDecimal.valueOf(2000)).build();
        ItemPedido ip2 = ItemPedido.builder().pedido(ped1).produto(p3).desconto(BigDecimal.valueOf(0))
        		.quantidade(2).preco(BigDecimal.valueOf(80)).build();
        ItemPedido ip3 = ItemPedido.builder().pedido(ped2).produto(p2).desconto(BigDecimal.valueOf(100))
        		.quantidade(1).preco(BigDecimal.valueOf(800)).build();
        
        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));
        
        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));
        
        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
        
    }

}
