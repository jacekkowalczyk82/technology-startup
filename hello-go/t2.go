package main

import "fmt"

func main() {
	var liczba int
	liczba = 64
	lz := 64.0
	// zmienne typu całkowitego (int) nie obsługują poprawnie znacznika formatowania notacji naukowej %e.
	fmt.Printf("%v %b %T %e\n", liczba, liczba, liczba, liczba)
	fmt.Printf("%v %b %T %e\n", lz, lz, lz, lz)

	liczba = 2
	lz2 := 2.0
	// zmienne typu całkowitego (int) nie obsługują poprawnie znacznika formatowania notacji naukowej %e.
	fmt.Printf("%v %b %T %e\n", liczba, liczba, liczba, liczba)
	fmt.Printf("%v %b %T %e\n", lz2, lz2, lz2, lz2)

}
