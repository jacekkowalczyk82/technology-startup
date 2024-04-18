package main

import (
	"fmt"
	"os"
	"reflect"
	"strconv"
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
			fmt.Println("\n\n\n\t\t Hello " + name)
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

	var s string = "9223372036854775807"
	i, err := strconv.ParseInt(s, 10, 64)
	if err != nil {
		panic(err)
	}
	fmt.Printf("Hello, %v with type %s!\n", i, reflect.TypeOf(i))

}
