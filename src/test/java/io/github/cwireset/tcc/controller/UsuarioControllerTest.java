//package io.github.cwireset.tcc.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.github.cwireset.tcc.domain.Endereco;
//import io.github.cwireset.tcc.domain.Usuario;
//import io.github.cwireset.tcc.request.AtualizarUsuarioRequest;
//import io.github.cwireset.tcc.service.UsuarioService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(UsuarioController.class)
//class UsuarioControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private UsuarioService usuarioService;
//
//    @Captor
//    private ArgumentCaptor<Usuario> captor;
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
//                .dataNascimento(LocalDate.of(1993, 9, 29))
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
//    @Disabled("Testes Desabilitados, estudando a lógica dos processos MVC")
//    @Test
//    public void criaUsuario_201() throws Exception {
//
//        when(usuarioService.cadastrarUsuario(captor.capture())).thenReturn(usuarioModel01);
//
//        mockMvc.perform(post("/usuarios")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(usuarioModel01)))
//                .andExpect(status().isCreated());
//
//        assertEquals(usuarioModel01,captor.getValue());
//    }
//
//}