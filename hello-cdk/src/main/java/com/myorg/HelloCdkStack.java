package com.myorg;

import dev.stratospheric.cdk.DockerRepository;
import software.amazon.awscdk.Environment;
import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

public class HelloCdkStack extends Stack {
  public HelloCdkStack(final Construct scope, final String id) {
    this(scope, id, null);
  }

  public HelloCdkStack(final Construct scope, final String id, final StackProps props) {
    super(scope, id, props);

    Environment environment = props.getEnv();

    DockerRepository repository = new DockerRepository(
      this,
      "repository",
      environment,
      new DockerRepository.DockerRepositoryInputParameters(
        "hello-world-repo", environment.getAccount(), 10));
  }

}