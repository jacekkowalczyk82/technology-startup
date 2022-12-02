#!/usr/bin/bash
operating_systems=(linux windows)

for os in ${operating_systems[@]}
do
    env GOOS=${os} GOARCH=amd64 go build -o keep-n-versions_${os}-amd64.bin keep-n-versions.go
done

ls -alh 

