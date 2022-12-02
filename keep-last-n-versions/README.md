
# Keep last n versions 

The application keep-last-n-versions - keeps n last versions of files matching given file pattern in the given directory. 
Versioned archive files should have the filenames containing timestamp in format yyyy-mm-dd__HH-MM-SS. 

```

go build keep-n-versions.go 

# to compile for Windows x64 


./keep-last-n-versions  ~/temp/test-versioned-files/ "notes_version_.*.tar.gz" 5 
./keep-last-n-versions  ~/temp/test-versioned-files/ "notes_version_.*.tar.gz" 5  ~/temp/test-dir-archived/
./keep-last-n-versions  ~/temp/test-versioned-files/ "keep-last-n-versions_version_[-_0-9]*.tar.gz" 5  ~/temp/test-dir-archived/



```

# Go resources 

## https://go.dev/

https://go.dev/


## https://www.openmymind.net/The-Little-Go-Book/

https://github.com/karlseguin/the-little-go-book
https://www.openmymind.net/assets/go/go.pdf

## https://gobyexample.com

https://gobyexample.com/command-line-arguments
https://gobyexample.com/slices
https://gobyexample.com/regular-expressions

## https://yourbasic.org/golang/

https://yourbasic.org/golang/append-explained/

## https://opensource.com/

https://opensource.com/article/21/1/go-cross-compiling
