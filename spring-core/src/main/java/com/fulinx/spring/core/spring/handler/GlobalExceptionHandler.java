// 包声明和导入
package com.fulinx.spring.core.spring.handler;

import com.fulinx.spring.core.generic.ResultVo;
import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.core.spring.exception.ServiceUnavailableException;
import com.fulinx.spring.core.spring.exception.UnauthenticatedException;
import com.fulinx.spring.core.spring.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.stream.Collectors;

// 表示该类用于为控制器提供集中式异常处理
@RestControllerAdvice
// 表示这个类也是一个 RestController
@RestController
// Lombok 注解，用于生成一个日志记录器字段
@Slf4j
public class GlobalExceptionHandler {

    // 用于处理请求有效负载中的验证错误的异常处理器
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultVo<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        // 提取字段错误并进行格式化
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(p -> String.format("%s%s", p.getField(), p.getDefaultMessage()))
                .collect(Collectors.toList());

        // 使用验证错误构建 ResultVo 并返回带有 BAD_REQUEST 状态的 ResponseEntity
        ResultVo<Object> resultVo = ResultVo.build(errors);
        return new ResponseEntity<>(resultVo, HttpStatus.BAD_REQUEST);
    }

    // 处理访问被拒绝错误的异常处理器（403 禁止访问）
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResultVo<Object>> handleAccessDeniedException(AccessDeniedException e){
        // 记录异常并返回带有 UNAUTHORIZED 状态的 ResponseEntity
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(ResultVo.build(e.getMessage(), -1), HttpStatus.FORBIDDEN);
    }

    // 处理未经身份验证错误的异常处理器（401 未经授权）
    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseEntity<ResultVo<Object>> handleUnauthenticatedException(UnauthenticatedException e){
        // 记录异常并返回带有 UNAUTHORIZED 状态的 ResponseEntity
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(ResultVo.build(e.getMessage(), e.getErrorCode()), HttpStatus.UNAUTHORIZED);
    }

    // 处理未经授权错误的异常处理器（403 禁止访问）
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ResultVo<Object>> handleUnauthorizedException(UnauthorizedException e){
        // 记录异常并返回带有 FORBIDDEN 状态的 ResponseEntity
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(ResultVo.build(e.getMessage(), e.getErrorCode()), HttpStatus.FORBIDDEN);
    }

    // 处理业务异常的异常处理器（422 不可处理的实体）
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResultVo<Object>> handleBusinessException(BusinessException e){
        // 记录异常并返回带有 UNPROCESSABLE_ENTITY 状态的 ResponseEntity
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(ResultVo.build(e.getMessage(), e.getErrorCode()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // 处理服务不可用错误的异常处理器（503 服务不可用）
    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<ResultVo<Object>> handleServiceUnavailableException(ServiceUnavailableException e){
        // 记录异常并返回带有 SERVICE_UNAVAILABLE 状态的 ResponseEntity
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(ResultVo.build(e.getMessage(), e.getErrorCode()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    // 处理其他异常的异常处理器（500 内部服务器错误）
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultVo<Object>> buildOthersException(Exception e){
        // 记录异常并返回带有 INTERNAL_SERVER_ERROR 状态的 ResponseEntity
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(ResultVo.build(e.getMessage(), -9), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 处理找不到处理程序异常的异常处理器（404 未找到）
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ResultVo<Object>> handleNoHandlerFoundException(NoHandlerFoundException e){
        // 记录异常并构建一个带有 NOT_FOUND 状态的 ResultVo 返回带有 NOT_FOUND 状态的 ResponseEntity
        log.error(e.getMessage(), e);
        ResultVo<Object> resultVo = ResultVo.build(e.getMessage(), -9);
        return new ResponseEntity<>(resultVo, HttpStatus.NOT_FOUND);
    }

    // 处理不支持的 HTTP 请求方法的异常处理器（405 方法不允许）
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResultVo<Object>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        // 记录异常并构建一个带有 METHOD_NOT_ALLOWED 状态的 ResultVo 返回带有 METHOD_NOT_ALLOWED 状态的 ResponseEntity
        log.warn(e.getMessage(), e);
        ResultVo<Object> resultVo = ResultVo.build(e.getMessage(), -9);
        return new ResponseEntity<>(resultVo, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
