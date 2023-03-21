#!/usr/bin/env python

import sys
from random import choice
from argparse import ArgumentParser, FileType
from configparser import ConfigParser
from confluent_kafka import Producer

if __name__ == '__main__':
    # Parse the command line.
    parser = ArgumentParser()
    parser.add_argument('config_file', type=FileType('r'))
    args = parser.parse_args()

    # Parse the configuration.
    # See https://github.com/edenhill/librdkafka/blob/master/CONFIGURATION.md
    config_parser = ConfigParser()
    config_parser.read_file(args.config_file)
    config = dict(config_parser['default'])

    # Create Producer instance
    producer = Producer(config)

    # Optional per-message delivery callback (triggered by poll() or flush())
    # when a message has been successfully delivered or permanently
    # failed delivery (after retries).
    def delivery_callback(err, msg):
        if err:
            print('ERROR: Message failed delivery: {}'.format(err))
        else:
            print("Produced event to topic {topic}: key = {key:12} value = {value:12}".format(
                topic=msg.topic(), key=msg.key().decode('utf-8'), value=msg.value().decode('utf-8')))

    # Produce data by selecting random values from these lists.
    topic = "py-playtube"
    py_playtube_play_key = "ADD_AND_PLAY"
    py_playtube_play_value = "https://www.youtube.com/watch?v=IdIb5RPabjw"
    # # keys 
    # user_ids = ['eabara', 'jsmith', 'sgarcia', 'jbernard', 'htanaka', 'awalther']

    # # values
    # products = ['book', 'alarm clock', 't-shirts', 'gift card', 'batteries']

    # count = 0
    # for _ in range(10):

    #     user_id = choice(user_ids)
    #     product = choice(products)
    #     producer.produce(topic, product, user_id, callback=delivery_callback)
    #     count += 1

    # # Block until the messages are sent.

    producer.produce(topic, py_playtube_play_value, py_playtube_play_key, callback=delivery_callback)
    producer.poll(10000)
    producer.flush()

