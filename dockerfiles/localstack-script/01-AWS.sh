#!/bin/bash

echo "########### Configuring AWS ###########"
aws configure set aws_access_key_id localstack_access_key_id
aws configure set aws_secret_access_key localstack_secret_access_key
aws configure set region us-east-1

echo "LocalStack está pronto. Criando tópico SNS..."
LOCALSTACK_ENDPOINT="http://localhost:4566"

# Criar infra

echo "Infra Ready"