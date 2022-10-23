package main

import (
    "fmt"
    "os"
)

/*
https://gobyexample.com/command-line-arguments
*/

func main() {

    //argsWithProg := os.Args
    //argsWithoutProg := os.Args[1:]

    //arg := os.Args[3]

    //fmt.Println(argsWithProg)
    //fmt.Println(argsWithoutProg)
    //fmt.Println(arg)
    
   
    if len(os.Args) > 1 {
    	name := os.Args[1]
    	if name == "Kuba" {
    	    fmt.Println("\n\n\n\t\t Hello Kubusiu mój kochany !!!")
    	} else {
    	    fmt.Println("\n\n\n\t\t Hello "+ name)
    	}
        fmt.Println("\n\n\n")

        otherArgs := os.Args[2:]
        fmt.Println("\n\n\n Your other aguments: ")
        fmt.Println(otherArgs)
    } else {
    	fmt.Println("\n\n\n Hello Stranger")
    }
    fmt.Println("\n\n\n")
    fmt.Println("\n\n\n Your all aguments: ")
    fmt.Println(os.Args)
    fmt.Println("\n\n\n")

}
