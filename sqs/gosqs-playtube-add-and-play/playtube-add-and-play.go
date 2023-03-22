// Based on the examples from
// https://github.com/awsdocs/aws-doc-sdk-examples/blob/main/gov2/sqs/SendMessage/SendMessagev2.go

package main

import (
	"context"
	"flag"
	"fmt"

	"github.com/aws/aws-sdk-go-v2/aws"
	"github.com/aws/aws-sdk-go-v2/config"
	"github.com/aws/aws-sdk-go-v2/service/sqs"
	"github.com/aws/aws-sdk-go-v2/service/sqs/types"
)

func main() {
	queue := flag.String("q", "", "The name of the SQS queue")
	url := flag.String("u", "", "The youtube audio url")
	title := flag.String("t", "", "The youtube audio title")

	flag.Parse()

	if *queue == "" {
		fmt.Println("You must provide the name of a queue (-q QUEUE)")
		return
	}

	if *url == "" {
		fmt.Println("You must provide the youtube music url (-u URL)")
		return
	}

	if *title == "" {
		fmt.Println("You must provide the youtube music title (-t TITLE)")
		return
	}

	cfg, err := config.LoadDefaultConfig(context.TODO())
	if err != nil {
		panic("ERROR: Configuration error, " + err.Error())
	}

	client := sqs.NewFromConfig(cfg)

	// Get URL of queue
	gQInput := &sqs.GetQueueUrlInput{
		QueueName: queue,
	}

	result, err := client.GetQueueUrl(context.TODO(), gQInput)
	if err != nil {
		fmt.Println("ERROR: Failed to get the queue URL:")
		fmt.Println(err)
		return
	}

	queueURL := result.QueueUrl

	sMInput := &sqs.SendMessageInput{
		DelaySeconds: 0,
		MessageAttributes: map[string]types.MessageAttributeValue{
			"type": {
				DataType:    aws.String("String"),
				StringValue: aws.String("ADD_AND_PLAY"),
			},
		},

		MessageBody: aws.String("{\"action\": \"ADD_AND_PLAY\", \"url\": \"" + *url +
			"\", \"title\": \"" + *title + "\"}"),
		QueueUrl: queueURL,
	}

	fmt.Println("Sending message: " + *sMInput.MessageBody)

	resp, err := client.SendMessage(context.TODO(), sMInput)
	if err != nil {
		fmt.Println("Got an error sending the message:")
		fmt.Println(err)
		return
	}

	fmt.Println("Sent message with ID: " + *resp.MessageId)
}
