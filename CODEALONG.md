# AWS Commands

## Chapter 1

### 1.01 Deployment Options

```shell
aws cloudformation deploy \
  --template-file vpc-stack.yml \
  --stack-name vpc-stack \
  --profile stratospheric
```
```shell
aws cloudformation delete-stack \
  --stack-name vpc-stack \
  --profile stratospheric
```
```shell
aws cloudformation describe-stacks \
  --region us-east-1 \
  --profile stratospheric
```

### 1.02 Getting Ready to Use AWS

- `aws --version`
- `aws configure`
- `aws configure --profile stratospheric`
- `export AWS_PROFILE=stratospheric`
- `aws ec2 describe-vpcs --profile stratospheric`


### 1.03 Deploy a Docker Image to Amazon ECR

```shell
./gradlew clean build
```

```shell
docker build -t todo-app .
```

```shell
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin <ecr-url>
```

```shell
docker tag todo-app <aws-ecr-url>/todo-app:v1
```

```shell
docker tag todo-app <aws-ecr-url>/todo-app:latest
```
```shell
docker push <aws-ecr-url>/todo-app:v1
```

```shell
docker push <aws-ecr-url>/todo-app:latest
```

### 1.04 Getting Started with CloudFormation

```shell
aws cloudformation deploy \
--stack-name=todo-docker-repository \
--template-file registry.yml \
--parameter-overrides RegistryName=todo-app \
--profile stratospheric
```

### 1.05 Deploying a Network Stack with CloudFormation

```shell
aws cloudformation deploy \
--stack-name=dev-network \
--template-file network.yml \
--capabilities CAPABILITY_IAM \
--profile stratospheric
```
### 1.06 Deploying a Service Stack with CloudFormation

```shell
aws cloudformation deploy \
--stack-name dev-service \
--template-file service.yml \
--profile stratospheric \
--parameter-overrides \
    NetworkStackName=dev-network \
    ServiceName=hello-world-app \
    ImageUrl=<ecr-url>/todo-app:v1 \
    ContainerPort=8080
```
### 1.07 Getting Started with CDK

