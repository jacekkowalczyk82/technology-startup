#!/usr/bin/env python


import boto3


# Get the service resource
sqs = boto3.resource('sqs')

queue_name = "py-player-queue"

# Get the queue. This returns an SQS.Queue instance
queue = sqs.get_queue_by_name(QueueName=queue_name)

# You can now access identifiers and attributes
print(queue.url)
print("DelaySeconds", queue.attributes.get("DelaySeconds"))
print("queue attributes", str(queue.attributes))

# Process messages by printing out body and optional author name
for message in queue.receive_messages():
    
    # Print out the body 
    print(f"Received message on {queue_name}: {message.body}")

    # Let the queue know that the message is processed
    message.delete()
