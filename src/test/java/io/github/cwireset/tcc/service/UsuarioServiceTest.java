//package io.github.cwireset.tcc.service;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import io.github.cwireset.tcc.exceptions.NenhumaInstanciaEncontradaException;
//import io.github.cwireset.tcc.request.AtualizarUsuarioRequest;
//import org.junit.jupiter.api.BeforeEach;
//import org.mockito.*;
//
//import static org.mockito.Mockito.*;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import io.github.cwireset.tcc.domain.Endereco;
//import io.github.cwireset.tcc.domain.Usuario;
//import io.github.cwireset.tcc.exceptions.InstanciaJaCadastrada;
//import io.github.cwireset.tcc.repository.UsuarioAvatarRepository;
//import io.github.cwireset.tcc.repository.UsuarioRepository;
//import org.springframework.data.domain.*;
//
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@ExtendWith(MockitoExtension.class)
//public class UsuarioServiceTest {
//
//    @Mock
//    private UsuarioAvatarRepository usuarioAvatar;
//
//    @Mock
//    private AtualizarUsuarioRequest atualizarUsuarioRequest;
//
//    @Mock
//    private UsuarioRepository usuarioRepository;
//
//    @Captor
//    private ArgumentCaptor<Usuario> usuarioArgumentCaptor;
//
//    @InjectMocks
//    private UsuarioService usuarioService;
//
//    private Usuario usuarioModel01;
//    private List<Usuario> listaUsuarios;
//
//    @BeforeEach
//    public void iniciarUsuarioExpected(){
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
//        Usuario usuarioModel03 = Usuario.builder()
//                .nome("Felipe Coelho")
//                .email("Coelho.felipe.rj@gmail.com")
//                .senha("1234567889")
//                .cpf("70287364714")
//                .dataNascimento(LocalDate.of(1993, 9, 29))
//                .endereco(Endereco.builder()
//                        .cep("25960-602")
//                        .logradouro("Av. Rotariana")
//                        .numero("891")
//                        .bairro("Soberbo")
//                        .cidade("Teresopolis")
//                        .estado("RJ")
//                        .build())
//                .build();
//        Usuario usuarioModel02 = Usuario.builder()
//                .nome("Felipe Coelho")
//                .email("Coelho.felipe.rj@gmail.com")
//                .senha("1234567889")
//                .cpf("70287364714")
//                .dataNascimento(LocalDate.of(1993, 9, 29))
//                .endereco(Endereco.builder()
//                        .cep("25960-602")
//                        .logradouro("Av. Rotariana")
//                        .numero("891")
//                        .bairro("Soberbo")
//                        .cidade("Teresopolis")
//                        .estado("RJ")
//                        .build())
//                .build();
//
//        listaUsuarios = new ArrayList<>();
//        listaUsuarios.add(usuarioModel01);
//        listaUsuarios.add(usuarioModel03);
//        listaUsuarios.add(usuarioModel02);
//    }
//
//    //    <-- Testes metodo cadastrar -->
//
//    @Test
//    @DisplayName("Não permitir que sejam criado usuário com e-mails iguais")
//    public void testCadastrarUsuarioMesmoEmail() throws Exception{
//        //Arrange
//        final String mensagemExpected = String.format("Já existe um recurso do tipo Usuario com E-Mail com o valor '%s'.", usuarioModel01.getEmail());
//
//        //Action
//        when(usuarioRepository.existsByEmail(usuarioModel01.getEmail())).thenReturn(true);
//
//        //Assert
//        InstanciaJaCadastrada exception = assertThrows(InstanciaJaCadastrada.class,
//                () -> usuarioService.cadastrarUsuario(usuarioModel01));
//        assertEquals(mensagemExpected,exception.getMessage());
//
//    }
//
//    @Test
//    @DisplayName("Não permiti que seja criado usuário com CPFs iguais")
//    public void testCadastrarUsuarioMesmoCPF(){
//        //Arrange
//        final String mensagemExpected = String.format("Já existe um recurso do tipo Usuario com CPF com o valor '%s'.", usuarioModel01.getCpf());
//
//        //Action
//        when(usuarioRepository.existsByCpf(usuarioModel01.getCpf())).thenReturn(true);
//        //Assert
//        InstanciaJaCadastrada exception = assertThrows(InstanciaJaCadastrada.class,
//                () -> usuarioService.cadastrarUsuario(usuarioModel01));
//        assertEquals(mensagemExpected,exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Deve ser salvado o Usuário no repositório.")
//    public void testCadastrarUsuario() throws Exception {
//        //Action
//        when(usuarioRepository.existsByEmail(usuarioModel01.getEmail())).thenReturn(false);
//        when(usuarioRepository.existsByCpf(usuarioModel01.getCpf())).thenReturn(false);
//        when(usuarioRepository.save(usuarioArgumentCaptor.capture())).thenReturn(usuarioModel01);
//
//        usuarioService.cadastrarUsuario(usuarioModel01);
//        //Assert
//        verify(usuarioRepository,times(1)).save(usuarioModel01);
//        assertEquals(usuarioModel01,usuarioArgumentCaptor.getValue());
//    }
//
//    //    <-- Testes metodo Listar Usuário -->
//
//    @Test
//    @DisplayName("Deve retornar todos os Usuários")
//    public void testListarUsuarios(){
//        //Arrange
//        Pageable pageRequest = PageRequest.of(1,2,Sort.by(Sort.Direction.ASC,"nome"));
//        //Action
//        when(usuarioService.listarUsuarios(pageRequest)).thenReturn(new PageImpl<Usuario>(listaUsuarios,pageRequest,listaUsuarios.size()));
//        Page<Usuario> usuariosPage = usuarioService.listarUsuarios(pageRequest);
//        // Assert
//        assertFalse(usuariosPage.isEmpty());
//        assertEquals(1, usuariosPage.getPageable().getPageNumber());
//        assertEquals(Sort.by(Sort.Direction.ASC,"nome"),usuariosPage.getSort());
//
//    }
//
//    //    <-- Testes metodo Buscar Usuário ID -->
//
//    @Test
//    @DisplayName("Deve retornar um Usuário pelo ID")
//    public void retornarUmUsuarioPeloID() throws Exception {
//        //Action
//        when(usuarioRepository.findById(usuarioModel01.getId())).thenReturn(Optional.of(usuarioModel01));
//        Usuario usuarioExpect = usuarioService.buscarIdUsuario(usuarioModel01.getId());
//        //Assert
//        verify(usuarioRepository,times(1)).findById(usuarioModel01.getId());
//        assertEquals(usuarioExpect, usuarioModel01);
//    }
//
//    @Test
//    @DisplayName("Deve retornar erro ao não encontrar Usuário pelo ID")
//    public void retornarErroUsuarioPeloID() throws Exception {
//        //Arrange
//        usuarioModel01.setId(1L);
//        final String mensagemExpected = String.format("Nenhum(a) Usuario com Id com o valor '%s' foi encontrado.", usuarioModel01.getId());
//
//        //Assert
//        NenhumaInstanciaEncontradaException exception = assertThrows(NenhumaInstanciaEncontradaException.class,
//                () -> usuarioService.buscarIdUsuario(usuarioModel01.getId()));
//        assertEquals(mensagemExpected,exception.getMessage());
//    }
//
//    //    <-- Testes metodo Buscar Usuário CPF -->
//
//    @Test
//    @DisplayName("Deve retornar um Usuário pelo CPF")
//    public void retornarUmUsuarioPeloCPF() throws Exception {
//        //Action
//        when(usuarioRepository.findByCpf(usuarioModel01.getCpf())).thenReturn(Optional.of(usuarioModel01));
//        Usuario usuarioExpect = usuarioService.buscarCpfUsuario(usuarioModel01.getCpf());
//        //Assert
//        assertEquals(usuarioExpect, usuarioModel01);
//        verify(usuarioRepository,times(1)).findByCpf(usuarioModel01.getCpf());
//    }
//
//    @Test
//    @DisplayName("Deve retornar erro ao não encontrar Usuário pelo CPF")
//    public void retornarErroUsuarioPeloCPF() throws Exception {
//        //Arrange
//        final String mensagemExpected = String.format("Nenhum(a) Usuario com CPF com o valor '%s' foi encontrado.", usuarioModel01.getCpf());
//
//        //Assert
//        NenhumaInstanciaEncontradaException exception = assertThrows(NenhumaInstanciaEncontradaException.class,
//                () -> usuarioService.buscarCpfUsuario(usuarioModel01.getCpf()));
//        assertEquals(mensagemExpected,exception.getMessage());
//    }
//
//    //    <-- Testes metodo Atualizar Usuários -->
//
//    private AtualizarUsuarioRequest iniciarUsuarioRequest(){
//        Long id = 1L;
//        usuarioModel01.setId(id);
//        return AtualizarUsuarioRequest.builder()
//                .dataNascimento(usuarioModel01.getDataNascimento())
//                .email(usuarioModel01.getEmail())
//                .nome(usuarioModel01.getNome())
//                .endereco(usuarioModel01.getEndereco())
//                .senha(usuarioModel01.getSenha())
//                .build();
//    }
//
//    @Test
//    @DisplayName("Não permitir alteração no CPF do Usuário")
//    public void testNaoAlteracaoCPFUsuario() throws Exception{
//        //Arrange
//        Long id = 1L;
//        AtualizarUsuarioRequest usuarioRequest = iniciarUsuarioRequest();
//        String cpfExpected = "70287364714";
//        //Action
//        when(usuarioRepository.existsByEmailAndIdNot(usuarioRequest.getEmail(),id)).thenReturn(false);
//        when(usuarioRepository.findById(usuarioModel01.getId())).thenReturn(Optional.of(usuarioModel01));
//        when(usuarioRepository.save(usuarioArgumentCaptor.capture())).thenReturn(usuarioModel01);
//
//        usuarioService.atualizarUsuario(id, usuarioRequest);
//        //Assert
//        assertEquals(usuarioArgumentCaptor.getValue().getCpf(),cpfExpected);
//    }
//
//    @Test
//    @DisplayName("Deve retornar erro ao não encontrar Usuário pelo ID na Atualização do Usuário")
//    public void retornarErroUsuarioPeloIDAtualizar(){
//        //Arrange
//        Long id = 1L;
//        AtualizarUsuarioRequest usuarioRequest = iniciarUsuarioRequest();
//        String mensagemExpected = String.format("Nenhum(a) Usuario com Id com o valor '%s' foi encontrado.", usuarioModel01.getId());
//
//        //Action and Assert
//        NenhumaInstanciaEncontradaException exception = assertThrows(NenhumaInstanciaEncontradaException.class,
//                () -> usuarioService.atualizarUsuario(usuarioModel01.getId(),usuarioRequest));
//        assertEquals(mensagemExpected,exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Deve retornar erro ao encontrar Usuário com E-mails iguais")
//    public void retornarErroUsuarioPelosEmailsIguais(){
//        //Arrange
//        Long id = 1L;
//        AtualizarUsuarioRequest usuarioRequest = iniciarUsuarioRequest();
//        String mensagemExpected = String.format("Já existe um recurso do tipo Usuario com E-Mail com o valor '%s'.", usuarioModel01.getEmail());
//
//        //Action
//        when(usuarioRepository.findById(usuarioModel01.getId())).thenReturn(Optional.of(usuarioModel01));
//        when(usuarioRepository.existsByEmailAndIdNot(usuarioModel01.getEmail(),id)).thenReturn(true);
//
//        //Assert
//        InstanciaJaCadastrada exception = assertThrows(InstanciaJaCadastrada.class,
//                () -> usuarioService.atualizarUsuario(id,usuarioRequest));
//        assertEquals(mensagemExpected,exception.getMessage());
//    }
//}
