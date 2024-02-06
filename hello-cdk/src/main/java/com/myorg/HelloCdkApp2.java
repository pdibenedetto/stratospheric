package com.myorg;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

public class HelloCdkApp2 {
  public static void main(final String[] args) {

    // 285338032503
    App app = new App();

    String accountId = (String) app.getNode().tryGetContext("accountId");
    requireNonEmpty(accountId, "context variable 'accountId' must not be null");

    String region = (String) app.getNode().tryGetContext("region");
    requireNonEmpty(region, "context variable 'region' must not be null");

    String repoName = (String) app.getNode().tryGetContext("repoName");

    Environment env = makeEnv(accountId, region);

    new HelloCdkStack2(app, "HelloCdkStack2",
      StackProps.builder()
        .env(env)
        .build(),
      repoName);

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

