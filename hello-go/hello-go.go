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
    	    fmt.Println("\n\n\n\t\tHello Kubusiu mój kochany !!!")
    	} else {
    	    fmt.Println("\n\n\n\t\tHello "+ name)
    	}
    } else {
    	fmt.Println("\n\n\nHello Stranger")
    }
    fmt.Println("\n\n\n")

}
