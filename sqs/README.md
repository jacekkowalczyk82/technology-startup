# Reading from SQS 

in python 

https://boto3.amazonaws.com/v1/documentation/api/latest/guide/sqs.html


## JOSN generator 

https://www.objgen.com/json/local/design


{
  "action": "ADD_AND_PLAY",
  "url": "https://www.youtube.com/watch?v=9Uj2VyG8T0I",
  "title": "Man in the rain"
}


{
  "action": "ADD_AND_PLAY",
  "url": "https://www.youtube.com/watch?v=YMsQ5izhIDo",
  "title": "Slodkiego milego zycia"
}


## config parser


from configparser import ConfigParser

## GO lang 

https://aws.github.io/aws-sdk-go-v2/docs/configuring-sdk/
https://github.com/awsdocs/aws-doc-sdk-examples/blob/main/gov2/sqs/SendMessage/SendMessagev2.go



go mod init gosqs
go get github.com/aws/aws-sdk-go-v2/aws
go get github.com/aws/aws-sdk-go-v2
go get github.com/aws/aws-sdk-go-v2/config
go get github.com/aws/aws-sdk-go-v2/service/sqs


-q py-player-queue -m "{\"action\": \"ADD_AND_PLAY\", \"url\": \"https://www.youtube.com/watch?v=9Uj2VyG8T0I\", \"title\": \"Man in the rain\"}"
-q py-player-queue -u "https://www.youtube.com/watch?v=YMsQ5izhIDo" -t "slodkiego milego zycia" 


./playtube-add-and-play-linux-amd64.bin -q py-player-queue -u "https://www.youtube.com/watch?v=YMsQ5izhIDo" -t "slodkiego milego zycia" 


go run playtube-add-and-play.go -q py-player-queue -u "https://www.youtube.com/watch?v=9Uj2VyG8T0I" -t "Man in the rain" 

go run playtube-add-and-play.go -q py-player-queue -u "https://www.youtube.com/watch?v=PEWP9nbqG9Q" -t "In the Air tonight" 


./playtube-add-and-play-linux-amd64.bin -q py-player-queue -u "https://www.youtube.com/watch?v=PEWP9nbqG9Q" -t "In the Air tonight" 

