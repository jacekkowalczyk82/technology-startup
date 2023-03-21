#!/usr/bin/bash
operating_systems=(linux windows)

for os in ${operating_systems[@]}
do

    env GOOS=${os} GOARCH=amd64 go build -o playtube-add-and-play-${os}-amd64.bin playtube-add-and-play.go

    if [ "windows" == "${os}" ]; then 
        mv playtube-add-and-play-${os}-amd64.bin playtube-add-and-play-${os}-amd64.exe
    fi 
done

ls -alh 

