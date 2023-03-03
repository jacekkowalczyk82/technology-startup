#!/usr/bin/bash
operating_systems=(linux windows)

for os in ${operating_systems[@]}
do

    env GOOS=${os} GOARCH=amd64 go build -o go-ascii_${os}-amd64.bin go-ascii.go

    if [ "windows" == "${os}" ]; then 
        mv o-ascii_${os}-amd64.bin o-ascii_${os}-amd64.exe
    fi 
done

ls -alh 

