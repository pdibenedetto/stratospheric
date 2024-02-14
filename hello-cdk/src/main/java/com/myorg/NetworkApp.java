package com.myorg;

import dev.stratospheric.cdk.DockerRepository;
import dev.stratospheric.cdk.Network;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

public class NetworkApp {

  public static void main(String[] args) {
    App app = new App();
    String accountId = (String) app.getNode().tryGetContext("accountId");
    requireNonEmpty(accountId, "context variable 'accountId' must not be null");

    String region = (String) app.getNode().tryGetContext("region");
    requireNonEmpty(region, "context variable 'region' must not be null");

    String applicationName = (String) app.getNode().tryGetContext("applicationName");
    requireNonEmpty(region, "context variable 'applicationName' must not be null");

    String environmentName = (String) app.getNode().tryGetContext("environmentName");
    requireNonEmpty(region, "context variable 'environmentName' must not be null");

    Environment env = makeEnv(accountId, region);

    Stack networkStack = new Stack(
      app,
      "NetworkStack",
      StackProps.builder()
        .stackName(environmentName + "-Network")
        .env(env)
        .build());

    Network network = new Network(
      networkStack,
      "Network",
      env,
      environmentName,
      new Network.NetworkInputParameters());

    app.synth();
  }

  static Environment makeEnv(String accountId, String region) {
    return Environment.builder()
      .account(accountId)
      .region(region)
      .build();
  }

  public static void requireNonEmpty(String string, String message) {
    if (string == null || string.isEmpty()) {
      throw new IllegalArgumentException(message);
    }
  }

}
