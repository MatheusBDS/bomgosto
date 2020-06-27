package com.bomgosto.services;

import com.bomgosto.domain.*;
import com.bomgosto.domain.enums.EstadoPagamento;
import com.bomgosto.domain.enums.Perfil;
import com.bomgosto.domain.enums.TipoCliente;
import com.bomgosto.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
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
    
	public void instantiateTestDatabase() throws ParseException{
		// PRODUTOS
        Produto p1 = Produto.builder().nome("Pizza de Calabresa").tamanho("Grande").unidadeMedida("Gramas")
                .precoUnitario(BigDecimal.valueOf(33.90)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
        Produto p2 = Produto.builder().nome("Pizza de Frango").tamanho("Grande").unidadeMedida("Gramas")
                .precoUnitario(BigDecimal.valueOf(33.90)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
        Produto p3 = Produto.builder().nome("Suco de Laranja").tamanho("500").unidadeMedida("Mililitros")
                .precoUnitario(BigDecimal.valueOf(3)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
        Produto p4 = Produto.builder().nome("Suco de Maracujá").tamanho("500").unidadeMedida("Mililitros")
                .precoUnitario(BigDecimal.valueOf(3)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
        Produto p5 = Produto.builder().nome("Isca de Peixe").tamanho("N/A").unidadeMedida("Gramas")
                .precoUnitario(BigDecimal.valueOf(39.90)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
        Produto p6 = Produto.builder().nome("Picanha na Chapa").tamanho("N/A").unidadeMedida("Gramas")
                .precoUnitario(BigDecimal.valueOf(99.90)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
        Produto p7 = Produto.builder().nome("Camarão Cremoso").tamanho("N/A").unidadeMedida("Gramas")
                .precoUnitario(BigDecimal.valueOf(99.90)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
        Produto p8 = Produto.builder().nome("Peito de Frango").tamanho("N/A").unidadeMedida("Gramas")
                .precoUnitario(BigDecimal.valueOf(23.90)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
        Produto p9 = Produto.builder().nome("Lasanha de Carne").tamanho("N/A").unidadeMedida("Gramas")
                .precoUnitario(BigDecimal.valueOf(29.90)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
        Produto p10 = Produto.builder().nome("X-Bom Gosto").tamanho("N/A").unidadeMedida("Gramas")
                .precoUnitario(BigDecimal.valueOf(18.90)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
        Produto p11 = Produto.builder().nome("Foundue de Morango").tamanho("N/A").unidadeMedida("Gramas")
                .precoUnitario(BigDecimal.valueOf(12.90)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
        Produto p12 = Produto.builder().nome("Champagne Chandon").tamanho("N/A").unidadeMedida("Mililitros")
                .precoUnitario(BigDecimal.valueOf(129.90)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
        Produto p13 = Produto.builder().nome("Chopp Brahma").tamanho("N/A").unidadeMedida("Mililitros")
                .precoUnitario(BigDecimal.valueOf(5.90)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
        Produto p14 = Produto.builder().nome("Carne Seca").tamanho("N/A").unidadeMedida("Gramas")
                .precoUnitario(BigDecimal.valueOf(19.90)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();

//        Produto p15 = Produto.builder().nome("Pizza 15").tamanho("Grande").unidadeMedida("Gramas")
//                .precoUnitario(BigDecimal.valueOf(30)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
//        Produto p16 = Produto.builder().nome("Pizza 16").tamanho("Grande").unidadeMedida("Gramas")
//                .precoUnitario(BigDecimal.valueOf(30)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
//        Produto p17 = Produto.builder().nome("Pizza 17").tamanho("Grande").unidadeMedida("Gramas")
//                .precoUnitario(BigDecimal.valueOf(30)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
//        Produto p18 = Produto.builder().nome("Pizza 18").tamanho("Grande").unidadeMedida("Gramas")
//                .precoUnitario(BigDecimal.valueOf(30)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
//        Produto p19 = Produto.builder().nome("Pizza 19").tamanho("Grande").unidadeMedida("Gramas")
//                .precoUnitario(BigDecimal.valueOf(30)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
//        Produto p20 = Produto.builder().nome("Pizza 20").tamanho("Grande").unidadeMedida("Gramas")
//                .precoUnitario(BigDecimal.valueOf(30)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
//        Produto p21 = Produto.builder().nome("Pizza 21").tamanho("Grande").unidadeMedida("Gramas")
//                .precoUnitario(BigDecimal.valueOf(30)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
//        Produto p22 = Produto.builder().nome("Pizza 22").tamanho("Grande").unidadeMedida("Gramas")
//                .precoUnitario(BigDecimal.valueOf(30)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
//        Produto p23 = Produto.builder().nome("Pizza 23").tamanho("Grande").unidadeMedida("Gramas")
//                .precoUnitario(BigDecimal.valueOf(30)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
//        Produto p24 = Produto.builder().nome("Pizza 24").tamanho("Grande").unidadeMedida("Gramas")
//                .precoUnitario(BigDecimal.valueOf(30)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
//        Produto p25 = Produto.builder().nome("Pizza 25").tamanho("Grande").unidadeMedida("Gramas")
//                .precoUnitario(BigDecimal.valueOf(30)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
//        Produto p26 = Produto.builder().nome("Pizza 26").tamanho("Grande").unidadeMedida("Gramas")
//                .precoUnitario(BigDecimal.valueOf(30)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
//        Produto p27 = Produto.builder().nome("Pizza 27").tamanho("Grande").unidadeMedida("Gramas")
//                .precoUnitario(BigDecimal.valueOf(30)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
//        Produto p28 = Produto.builder().nome("Pizza 28").tamanho("Grande").unidadeMedida("Gramas")
//                .precoUnitario(BigDecimal.valueOf(30)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
//        Produto p29 = Produto.builder().nome("Pizza 29").tamanho("Grande").unidadeMedida("Gramas")
//                .precoUnitario(BigDecimal.valueOf(30)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
//        Produto p30 = Produto.builder().nome("Pizza 30").tamanho("Grande").unidadeMedida("Gramas")
//                .precoUnitario(BigDecimal.valueOf(30)).categorias(new ArrayList<>()).itens(new HashSet<>()).build();

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
        cat2.getProdutos().addAll(Collections.singletonList(p12));
        cat3.getProdutos().addAll(Collections.singletonList(p5));
        cat4.getProdutos().addAll(Collections.singletonList(p6));
        cat5.getProdutos().addAll(Collections.singletonList(p7));
        cat6.getProdutos().addAll(Collections.singletonList(p8));
        cat7.getProdutos().addAll(Collections.singletonList(p9));
        cat8.getProdutos().addAll(Collections.singletonList(p10));
        cat9.getProdutos().addAll(Collections.singletonList(p14));
        cat10.getProdutos().addAll(Collections.singletonList(p11));
        cat11.getProdutos().addAll(Arrays.asList(p3, p4));
        cat12.getProdutos().addAll(Collections.singletonList(p13));

//        cat1.getProdutos().addAll(Arrays.asList(p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30));

        p1.getCategorias().addAll(Collections.singletonList(cat1));
        p2.getCategorias().addAll(Collections.singletonList(cat1));
        p3.getCategorias().addAll(Collections.singletonList(cat11));
        p4.getCategorias().addAll(Collections.singletonList(cat11));
        p5.getCategorias().addAll(Collections.singletonList(cat3));
        p6.getCategorias().addAll(Collections.singletonList(cat4));
        p7.getCategorias().addAll(Collections.singletonList(cat5));
        p8.getCategorias().addAll(Collections.singletonList(cat6));
        p9.getCategorias().addAll(Collections.singletonList(cat7));
        p10.getCategorias().addAll(Collections.singletonList(cat8));
        p11.getCategorias().addAll(Collections.singletonList(cat10));
        p12.getCategorias().addAll(Collections.singletonList(cat2));
        p13.getCategorias().addAll(Collections.singletonList(cat12));
        p14.getCategorias().addAll(Collections.singletonList(cat9));

//        p15.getCategorias().add(cat1);
//        p16.getCategorias().add(cat1);
//        p17.getCategorias().add(cat1);
//        p18.getCategorias().add(cat1);
//        p19.getCategorias().add(cat1);
//        p20.getCategorias().add(cat1);
//        p21.getCategorias().add(cat1);
//        p22.getCategorias().add(cat1);
//        p23.getCategorias().add(cat1);
//        p24.getCategorias().add(cat1);
//        p25.getCategorias().add(cat1);
//        p26.getCategorias().add(cat1);
//        p27.getCategorias().add(cat1);
//        p28.getCategorias().add(cat1);
//        p29.getCategorias().add(cat1);
//        p30.getCategorias().add(cat1);


        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14));

//        produtoRepository.saveAll(Arrays.asList(p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30));

        // ESTADOS
        Estado est1 = Estado.builder().nome("Distrito Federal").cidades(new ArrayList<>()).build();

        Cidade c1 = Cidade.builder().nome("Ceilândia").estado(est1).build();

        est1.getCidades().addAll(Collections.singletonList(c1));

        estadoRepository.saveAll(Collections.singletonList(est1));
        cidadeRepository.saveAll(Collections.singletonList(c1));

        Cliente cli1 = new Cliente(null, "Matheus Silva", "matheusb565@gmail.com", "85633321120",
                TipoCliente.PESSOA_FISICA, pe.encode("123"));
        cli1.getTelefones().addAll(Arrays.asList("992706077", "991289851"));
        
        Cliente cli2 = new Cliente(null, "Lisangela", "lisangela@gmail.com", "38566358155",
                TipoCliente.PESSOA_FISICA, pe.encode("123"));
        cli2.getTelefones().addAll(Arrays.asList("999991563", "985236547"));
        cli2.addPerfil(Perfil.ADMIN);

        Endereco e1 = Endereco.builder().logradouro("QNO 13 CONJUNTO K").numero("09").complemento("SETOR O")
                .bairro("CEILÂNDIA").cep("72255311").cidade(c1).cliente(cli1).build();
        Endereco e2 = Endereco.builder().logradouro("QNO 03 CONJUNTO F").numero("33").complemento("SETOR O")
                .bairro("CEILÂNDIA").cep("72255306").cidade(c1).cliente(cli1).build();
        Endereco e3 = Endereco.builder().logradouro("QNO 11 CONJUNTO L").numero("30").complemento("SETOR O")
                .bairro("CEILÂNDIA").cep("72255310").cidade(c1).cliente(cli2).build();

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
        cli2.getEnderecos().addAll(Arrays.asList(e3));

        clienteRepository.saveAll(Arrays.asList(cli1, cli2));
        enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
        
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
