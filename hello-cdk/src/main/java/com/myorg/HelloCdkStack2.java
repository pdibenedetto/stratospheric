package com.myorg;

import dev.stratospheric.cdk.DockerRepository;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.constructs.Construct;

public class HelloCdkStack2 extends Stack {

  public HelloCdkStack2(final Construct scope, final String id, final StackProps props, final String repoName) {
    super(scope, id, props);

    Environment environment = props.getEnv();

    DockerRepository repository = new DockerRepository(
      this,
      "repository",
      environment,
      new DockerRepository.DockerRepositoryInputParameters(
        repoName,
        environment.getAccount(),
        10));
  }

}
