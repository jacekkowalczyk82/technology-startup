package main

import "fmt"

func main() {
	a := make([]int, 3)
	for i := 0; i < len(a); i++ {
		fmt.Println(a[i])
	}

	fmt.Println("Default array after init")
	fmt.Println(a)

	for i := 0; i < len(a); i++ {
		a[i] = i
	}

	fmt.Println("array after modyfing values")
	fmt.Println(a)

	a = append(a, 10)
	fmt.Println("slice after append")
	fmt.Println(a)
}
