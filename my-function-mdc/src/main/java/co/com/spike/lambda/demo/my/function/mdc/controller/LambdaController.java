package co.com.spike.lambda.demo.my.function.mdc.controller;

import co.com.spike.lambda.demo.my.function.mdc.model.request.Request;
import co.com.spike.lambda.demo.my.function.mdc.model.response.Response;
import co.com.spike.lambda.demo.my.function.mdc.service.ILambdaService;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.function.aws.MicronautRequestHandler;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Introspected
@Slf4j
public class LambdaController extends MicronautRequestHandler<Request, Response> {
  @Inject ILambdaService lambdaService;

  @Override
  public Response execute(final Request input) {
    log.info("Start consumer LambdaController");
    final Response client = lambdaService.get(input.getMessage());
    log.info("End consumer LambdaController");
    return client;
  }
}
