#!/bin/sh

WEBM_DIR=$1

mkdir -p ~/mp3-music/
THIS_CONVERSION_DIR=converted_`date '+%Y-%m-%d_%H.%M.%S'`
mkdir -p ~/mp3-music/$THIS_CONVERSION_DIR

find $WEBM_DIR -name "*.webm" -exec ffmpeg -i '{}' '{}.mp3' \;
find $WEBM_DIR -name "*.webm.mp3" -exec mv '{}' ~/mp3-music/$THIS_CONVERSION_DIR/ \;
