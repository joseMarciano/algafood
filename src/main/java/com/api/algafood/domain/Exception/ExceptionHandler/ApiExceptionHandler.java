package com.api.algafood.domain.Exception.ExceptionHandler;

import com.api.algafood.domain.Exception.EntidadeEmUsoException;
import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.Exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/* Essa anotação permite que todas as excessões dos controladores serão tratadas aqui
 * É muito melhor usar assim do que criar um exception handler pra cada controller
 * ----Qualquer exception gerenciada pelo spring
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL =
            "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o " +
                    "problema persistir, entre em contato com o administrador do sistema";


    /*Quando a excessão for tratada, esse método é chamado e eu
     *retorno o que eu quiser (Estou capturando também as exceptions
     *causadas por EntidadeNaoEncontradaException... passando o throwable)
     */
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e,
                                                                  WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        var problem = problemBuilder(status, ProblemType.RECURSO_NAO_ENCONTRADO, e.getMessage());
        problem.setUserMessage("A entidade não foi encontrada");
        problem.setTimeStamp(LocalDateTime.now());

        return handleExceptionInternal(
                e,
                problem,
                new HttpHeaders(),
                status,
                request);// -- Usando o handleExceptionInternal nos métodos que eu criei para tratar as exceptions
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException e, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        var problem = problemBuilder(status, ProblemType.ERRO_NEGOCIO, e.getMessage());
        problem.setUserMessage("Erro de negócio");
        problem.setTimeStamp(LocalDateTime.now());

        return handleExceptionInternal(
                e,
                problem,
                new HttpHeaders(),
                status,
                request);
    }

    /* @ExceptionHandler(HttpMediaTypeNotSupportedException.class) -- Essa Exception é exclusiva do SpringBoot
     * Código comentado pois estamos extendendo uma classe que já faz o tratamento de exceptions exclusivas
     * do spring(

        public ResponseEntity<?> handleTratarXMLnoCorpoDaRequisicao(HttpMediaTypeNotSupportedException e){
            var problem = new Problema(LocalDateTime.now(),"A aplicação não aceita XML");
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(problem);
        }*/
    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        var problem = problemBuilder(status, ProblemType.ENTIDADE_EM_USO, e.getMessage());
        problem.setUserMessage(e.getMessage());
        problem.setTimeStamp(LocalDateTime.now());

        return handleExceptionInternal(e,
                problem,
                new HttpHeaders(),
                status,
                request);
    }

    /* Sobrescrevendo o método da classe ResponseEntityExceptionHandler para que eu possa
     * modificar e colocar algum corpo que eu queira (notar que todos os metodos de ResponseEntityExceptionHandler
     * chamam esse método para formatar o corpo/statusHttp/etc....)
     *
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        /* -- Se o corpo for null eu pego e instancio um problema passando a mensagem original do status
         * -- Se o corpo nã é nulo e é uma String, quer dizer que eu passei alguma mensagem no corpo
         * -- então eu pego instacio um Problema com base na mensagem recebida
         */
        if (body == null) {
            var problem = new Problem();
            problem.setTittle(status.getReasonPhrase());
            problem.setStatus(status.value());
            problem.setUserMessage(MSG_ERRO_GENERICA_USUARIO_FINAL);
            problem.setTimeStamp(LocalDateTime.now());
            body = problem;
        } else if (body instanceof String) {
            var problem = new Problem();
            problem.setTittle((String) body);
            problem.setStatus(status.value());
            problem.setUserMessage(MSG_ERRO_GENERICA_USUARIO_FINAL);
            problem.setTimeStamp(LocalDateTime.now());
            body = problem;
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (e instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) e,
                    new HttpHeaders(),
                    status,
                    request);
        }
        return super.handleTypeMismatch(e, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
                                                                             HttpHeaders httpHeaders,
                                                                             HttpStatus status,
                                                                             WebRequest request) {
        var problemType = ProblemType.INVALID_PARAMETER;
        var detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido." +
                "Corrija e informe um valor compatível com o tipo '%s'", e.getName(), e.getValue(), e.getRequiredType().getSimpleName());
        var problem = problemBuilder(status, problemType, detail);
        problem.setUserMessage(MSG_ERRO_GENERICA_USUARIO_FINAL);
        problem.setTimeStamp(LocalDateTime.now());
        return handleExceptionInternal(e,
                problem,
                httpHeaders,
                status,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        var detail = String.format("O recurso '%s', que você tentou acessar, é inexistente", e.getRequestURL());


        var problem = problemBuilder(status, problemType, detail);
        problem.setUserMessage("Você tentou acessar um recurso inexistente.Por favor acesse um recurso correto");
        problem.setTimeStamp(LocalDateTime.now());

        return handleExceptionInternal(e,
                problem,
                headers,
                status,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(e);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) rootCause,
                    headers,
                    status,
                    request);
        } else if (rootCause instanceof IgnoredPropertyException) {
            return handleIgnoredProperty((IgnoredPropertyException) rootCause,
                    headers,
                    status,
                    request);
        } else if (rootCause instanceof UnrecognizedPropertyException) {
            return handleUnrecognizedProperty((UnrecognizedPropertyException) rootCause,
                    headers,
                    status,
                    request);
        }


        return super.handleExceptionInternal(e,
                problemBuilder(status, ProblemType.UNKNOWN_BODY, "O corpo da requisição está inválido"),
                new HttpHeaders(),
                status,
                request);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUncaugth(Exception e, WebRequest request) {

        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var problemType = ProblemType.SYSTEM_ERROR;
        var detail = MSG_ERRO_GENERICA_USUARIO_FINAL;


        // Importante colocar o printStackTrace (pelo menos por enquanto, que não estamos
        // fazendo logging) para mostrar a stacktrace no console
        // Se não fizer isso, você não vai ver a stacktrace de exceptions que seriam importantes
        // para você durante, especialmente na fase de desenvolvimento
        e.printStackTrace();

        return handleExceptionInternal(e,
                problemBuilder(status, problemType, detail),
                new HttpHeaders(),
                status,
                request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        final String MSG_CAMPOS_INVALIDOS = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        BindingResult bindingResult = e.getBindingResult();

        /*Para cada bindingResult eu insttancio um
        novo Problem.Field e adiciono em problemFields
         */
        List<Problem.Field> problemFields = bindingResult.getFieldErrors()
                .stream()
                .map(fieldError -> new Problem.Field(fieldError.getField(),fieldError.getDefaultMessage())).collect(Collectors.toList());

        var problem = problemBuilder(status, ProblemType.INVALID_DATAS, MSG_CAMPOS_INVALIDOS);
        problem.setTimeStamp(LocalDateTime.now());
        problem.setUserMessage(MSG_CAMPOS_INVALIDOS);
        problem.setFields(problemFields);

        return handleExceptionInternal(e,
                problem,
                new HttpHeaders(),
                status,
                request);
    }

    private ResponseEntity<Object> handleIgnoredProperty(IgnoredPropertyException e,
                                                         HttpHeaders headers,
                                                         HttpStatus status,
                                                         WebRequest request) {
        String path = joinPath(e.getPath());
        ProblemType problemType = ProblemType.INVALID_PROPERTY;
        String detail = String.format("A propriedade '%s' está sendo ignorada pela aplicação", path);
        var problem = problemBuilder(status, problemType, detail);
        problem.setUserMessage(MSG_ERRO_GENERICA_USUARIO_FINAL);

        return handleExceptionInternal(e,
                problem,
                new HttpHeaders(),
                status,
                request);

    }

    private ResponseEntity<Object> handleUnrecognizedProperty(UnrecognizedPropertyException e,
                                                              HttpHeaders headers,
                                                              HttpStatus status,
                                                              WebRequest request) {
        String path = joinPath(e.getPath());
        var problemType = ProblemType.UNKNOWN_PROPERTY;
        var detail = String.format("A propriedade '%s' não é reconhecida pela aplicação", path);

        return handleExceptionInternal(e,
                problemBuilder(status, problemType, detail),
                new HttpHeaders(),
                status,
                request);
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException e,
                                                       HttpHeaders headers,
                                                       HttpStatus status,
                                                       WebRequest request) {

        String path = joinPath(e.getPath());

        ProblemType problemType = ProblemType.UNKNOWN_BODY;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s' que é de um tipo inválido." +
                "Informar um valor compatível do tipo '%s'", path, e.getValue(), e.getTargetType().getSimpleName());

        var problem = problemBuilder(status, problemType, detail);
        problem.setUserMessage(MSG_ERRO_GENERICA_USUARIO_FINAL);
        problem.setTimeStamp(LocalDateTime.now());

        return handleExceptionInternal(e,
                problem,
                new HttpHeaders(),
                status,
                request);
    }

    public static Problem problemBuilder(HttpStatus status, ProblemType problemType, String detail) {
        return new Problem(
                status.value(),
                problemType.getUrl(),
                problemType.getTitle(),
                detail);

    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(reference -> reference.getFieldName())
                .collect(Collectors.joining("."));
    }
}
