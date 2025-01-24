#!/usr/bin/bash
operating_systems=(linux windows)
version=0.1

for os in ${operating_systems[@]}
do
    env GOOS=${os} GOARCH=amd64 go build -o bin/go-base64-${version}-${os}-amd64.bin go-base64.go
    
    if [ "linux" == "${os}" ]; then 
        cp bin/go-base64-${version}-${os}-amd64.bin ~/bin/go-base64
    fi
    if [ "windows" == "${os}" ]; then 
        mv bin/go-base64-${version}-${os}-amd64.bin bin/go-base64-${version}-${os}-amd64.exe
        cp bin/go-base64-${version}-${os}-amd64.exe bin/go-base64.exe

        go-base64 encode bin/go-base64-${version}-${os}-amd64.exe bin/go-base64-${version}-${os}-amd64.exe_go-base64.txt
        go-base64 decode bin/go-base64-${version}-${os}-amd64.exe_go-base64.txt bin/go-base64-${version}-${os}-amd64.exe_go-base64.txt_decoded.bin
        md5sum bin/go-base64-${version}-${os}-amd64.exe
        md5sum bin/go-base64-${version}-${os}-amd64.exe_go-base64.txt_decoded.bin

    fi 

done

ls -alh bin/ 

