package main

import "fmt"

func main() {

	var tablicaa [5]int

	for i := 0; i < len(tablicaa); i++ {
		tablicaa[i] = i
	}

	fmt.Printf("Type of tablicaa %T \n", tablicaa)
	fmt.Println(tablicaa)

	// slices

	a := make([]int, 3)
	for i := 0; i < len(a); i++ {
		fmt.Println(a[i])
	}

	fmt.Println("Default slice after init")
	fmt.Println(a)

	for i := 0; i < len(a); i++ {
		a[i] = i
	}

	fmt.Println("slice modyfing values")
	fmt.Println(a)

	a = append(a, 10)
	fmt.Println("slice append")
	fmt.Println(a)

	// slices podzbiory

	slice_b := make([]int, 5)
	for i := 0; i < len(slice_b); i++ {
		slice_b[i] = i
	}

	fmt.Println("slice_b", slice_b)
	fmt.Printf("Type of slice_b %T \n", slice_b)
	fmt.Println("slice_b[1:3]", slice_b[1:3])
	fmt.Println("slice_b[:3]", slice_b[:3])
	fmt.Println("slice_b[3:]", slice_b[3:])

}
