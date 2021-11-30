//package io.github.cwireset.tcc.service;
//
//import io.github.cwireset.tcc.domain.*;
//import io.github.cwireset.tcc.exceptions.InstanciaJaCadastrada;
//import io.github.cwireset.tcc.exceptions.NenhumaInstanciaEncontradaException;
//import io.github.cwireset.tcc.exceptions.TipoException;
//import io.github.cwireset.tcc.repository.ImovelRepository;
//import io.github.cwireset.tcc.request.CadastrarImovelRequest;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ImovelServiceTest {
//
//    @Mock
//    ImovelRepository imovelRepository;
//
//    @Mock
//    UsuarioService usuarioService;
//
//    @Mock
//    AnuncioService anuncioService;
//
//    @Captor
//    ArgumentCaptor<Imovel> captor;
//
//    @InjectMocks
//    ImovelService imovelService;
//
//    private Usuario usuarioModel01;
//    private CadastrarImovelRequest imovelRequestModel;
//    private Imovel imovelModel;
//
//    @BeforeEach
//    public void buildImovel(){
//        List<CaracteristicaImovel> listCaracteristicasImovel = new ArrayList<>();
//        listCaracteristicasImovel.add(CaracteristicaImovel.builder()
//                .descricao("3 quartos")
//                .build());
//        listCaracteristicasImovel.add(CaracteristicaImovel.builder()
//                .descricao("2 banheiros")
//                .build());
//
//        usuarioModel01 = Usuario.builder()
//                .nome("Felipe Coelho")
//                .email("Coelho.felipe.rj@gmail.com")
//                .senha("1234567889")
//                .cpf("70287364714")
//                .dataNascimento(LocalDate.of(1993,9,29))
//                .endereco(Endereco.builder()
//                        .cep("25960-602")
//                        .logradouro("Av. Rotariana")
//                        .numero("891")
//                        .bairro("Soberbo")
//                        .cidade("Teresopolis")
//                        .estado("RJ")
//                        .complemento("Perto da Floricultura")
//                        .build())
//                .build();
//
//        imovelRequestModel = CadastrarImovelRequest.builder()
//                .endereco(Endereco.builder()
//                        .cep("21250-250")
//                        .logradouro("Rua Rego Monteiro")
//                        .numero("71")
//                        .bairro("Cordovil")
//                        .cidade("Rio de Janeiro")
//                        .estado("RJ")
//                        .build())
//                .tipoImovel(TipoImovel.CASA)
//                .caracteristicas(listCaracteristicasImovel)
//                .identificacao("Casa Pequena Longe da Praia")
//                .idProprietario(1L)
//                .build();
//
//        imovelModel = Imovel.builder()
//                .proprietario(usuarioModel01)
//                .tipoStatus(TipoStatus.AVAILABLE)
//                .tipoImovel(TipoImovel.CASA)
//                .identificacao("Casa Pequena Longe da Praia")
//                .caracteristicas(listCaracteristicasImovel)
//                .id(1L)
//                .endereco(Endereco.builder()
//                        .cep("21250-250")
//                        .logradouro("Rua Rego Monteiro")
//                        .numero("71")
//                        .bairro("Cordovil")
//                        .cidade("Rio de Janeiro")
//                        .estado("RJ")
//                        .build())
//                .build();
//
//    }
//
//    //   <-- Testes metodo Cadastrar Imóvel -->
//
//    @Test
//    @DisplayName("Deve ser salvado o Imovel no repositório.")
//    public void CriarUmImovelPassandoCorretamente() throws Exception {
//        // Action
//        when(usuarioService.buscarIdUsuario(any())).thenReturn(usuarioModel01);
//        when(imovelRepository.save(captor.capture())).thenReturn(imovelModel);
//        imovelService.cadastrarImovel(imovelRequestModel);
//
//        //Assert
//        verify(imovelRepository,times(1)).save(any());
//        assertEquals(imovelModel.getProprietario(),captor.getValue().getProprietario());
//    }
//
//    @Test
//    @DisplayName("Deve retornar erro ao não encontrar Usuário pelo ID")
//    public void CriarUmImovelRetornandoErroIDUsuario() throws Exception {
//        //Arrange
//        String mensagemExpected = String.format("Nenhum(a) Usuario com Id com o valor '%s' foi encontrado.", imovelRequestModel.getIdProprietario());
//
//        //Action
//        when(usuarioService.buscarIdUsuario(imovelRequestModel.getIdProprietario()))
//                .thenThrow(new NenhumaInstanciaEncontradaException(TipoException.USUARIO.getSingular(), TipoException.ID.getSingular(), Long.toString(imovelRequestModel.getIdProprietario())));
//
//        //Assertion
//        NenhumaInstanciaEncontradaException exception = assertThrows(NenhumaInstanciaEncontradaException.class,
//                () -> imovelService.cadastrarImovel(imovelRequestModel));
//        assertEquals(mensagemExpected,exception.getMessage());
//
//    }
//
//
//}