package main

import(
	"os"
	"github.com/common-nighthawk/go-figure")

func main() {
	if len(os.Args) > 1 {
		message := os.Args[1]
		myFigure := figure.NewFigure(message, "", true)
		myFigure.Print()	
	} else {

  	myFigure := figure.NewFigure("Hello World", "", true)
  	myFigure.Print()
	}
}

