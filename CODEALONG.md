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

### 1.03 Deploy a Docker Image to Amazon ECR

### 1.04 Getting Started with CloudFormation

### 1.05 Deploying a Network Stack with CloudFormation

### 1.06 Deploying a Service Stack with CloudFormation

### 1.07 Getting Started with CDK

