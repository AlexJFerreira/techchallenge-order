#!/bin/bash


# cria a filas
awslocal sqs create-queue --queue-name orders-queue
ttp://localhost:4566 sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:notification-topic --protocol sqs --notification-endpoint arn:aws:sqs:us-east-1:000000000000:orders-queue