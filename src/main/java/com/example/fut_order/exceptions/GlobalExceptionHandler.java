package com.example.fut_order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice = @ControllerAdvice + @ResponseBody.
// Registra essa classe como um "interceptador global" de exceções:
// qualquer @RestController da aplicação que deixar uma exceção escapar
// passa primeiro por aqui antes de virar resposta HTTP.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler diz QUAL exceção esse método trata.
    // Sempre que CamisaNaoEncontradaException for lançada em qualquer
    // service/controller (ex: camisaService.buscarPorId lança essa exception
    // dentro de um orElseThrow), o Spring intercepta e chama este método
    // em vez de deixar a exceção subir e virar 500.
    @ExceptionHandler(CamisaNaoEncontradaException.class)
    public ProblemDetail handleCamisaNaoEncontrada(CamisaNaoEncontradaException ex){

        // ProblemDetail = implementação do padrão RFC 7807 (Problem Details
        // for HTTP APIs), formato padronizado de erro que o Spring adotou
        // nativamente a partir do Spring 6 / Boot 3.
        // forStatusAndDetail já monta o corpo JSON com "status" e "detail".
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage()); // ex.getMessage() = a mensagem que você passou ao lançar a exception (ex: "Camisa não encontrada com id: 5")

        // setTitle adiciona um resumo curto do tipo de erro,
        // separado da mensagem detalhada (ex.getMessage()).
        problemDetail.setTitle("Camisa não encontrada");

        // O retorno vira automaticamente o corpo da resposta HTTP
        // com status 404, graças ao @ResponseBody embutido no
        // @RestControllerAdvice — não precisa de ResponseEntity aqui.
        return problemDetail;
    }

    // MethodArgumentNotValidException é lançada automaticamente pelo Spring
    // (não por você) sempre que um @Valid falha — ou seja, quando um
    // CamisaCreateDTO ou CamisaUpdateDTO chega com dados que violam
    // @NotBlank, @Digits, @PositiveOrZero etc.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidacao(MethodArgumentNotValidException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, "Dados inválidos"); // 400, não 404 — erro do CLIENTE ao enviar o request, não "recurso inexistente"
        problemDetail.setTitle("Erro de Validação");
        return problemDetail;
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ProblemDetail handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Usuário não encontrado");
        return problemDetail;
    }

    @ExceptionHandler(UsuarioJaCadastradoException.class)
    public ProblemDetail handleUsuarioJaCadastrado(UsuarioJaCadastradoException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT, ex.getMessage());
        problemDetail.setTitle("Usuário já cadastrado");
        return problemDetail;
    }
}