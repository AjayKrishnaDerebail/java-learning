package com.learnjava.completablefuture;

import static com.learnjava.util.CommonUtil.stopWatchReset;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.learnjava.service.HelloWorldService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
public class CompletableFutureHelloWorldExceptionTest {

  @Mock
  private HelloWorldService helloWorldService;

  @InjectMocks
  CompletableFutureHelloWorldException completableFutureHelloWorldException;

  @BeforeEach
  void setUp(){
    stopWatchReset();
  }

  @Test
  void completableFutureThenCombine3CfsHandleExceptionUsing_CF_Handle_Hello() {

    when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception to test CF handle"));
    when(helloWorldService.world()).thenCallRealMethod();
    when(helloWorldService.hi()).thenCallRealMethod();

    val result = completableFutureHelloWorldException.completableFutureThenCombine3CfsUsingHandle();

    assertEquals(" WORLD! HI COMPLETABLEFUTURE", result);

  }

  @Test
  void completableFutureThenCombine3CfsHandleExceptionUsing_CF_Handle_Hello_And_World() {

    when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception to test CF handle"));
    when(helloWorldService.world()).thenThrow(new RuntimeException("Exception to test CF handle"));
    when(helloWorldService.hi()).thenCallRealMethod();

    val result = completableFutureHelloWorldException.completableFutureThenCombine3CfsUsingHandle();

    assertEquals(" HI COMPLETABLEFUTURE", result);

  }

  @Test
  void completableFutureThenCombine3CfsHandleExceptionUsing_CF_Exceptionally_Hello() {

    when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception to test CF handle"));
    when(helloWorldService.world()).thenCallRealMethod();
    when(helloWorldService.hi()).thenCallRealMethod();

    val result = completableFutureHelloWorldException.completableFutureThenCombine3CfsUsingExceptionally();

    assertEquals(" WORLD! HI COMPLETABLEFUTURE", result);

  }

  @Test
  void completableFutureThenCombine3CfsHandleExceptionUsing_CF_Exceptionally_Hello_And_World() {

    when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception to test CF handle"));
    when(helloWorldService.world()).thenThrow(new RuntimeException("Exception to test CF handle"));
    when(helloWorldService.hi()).thenCallRealMethod();

    val result = completableFutureHelloWorldException.completableFutureThenCombine3CfsUsingExceptionally();

    assertEquals(" HI COMPLETABLEFUTURE", result);

  }
}