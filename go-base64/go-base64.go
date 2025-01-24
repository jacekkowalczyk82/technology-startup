package main

import (
    "encoding/base64"
    "fmt"
    "io/ioutil"
    "os"
)

// EncodeFile encodes the content of the input file and writes it to the output file
func EncodeFile(inputFile, outputFile string) error {
    data, err := ioutil.ReadFile(inputFile)
    if err != nil {
        return fmt.Errorf("failed to read file: %w", err)
    }

    encodedData := base64.StdEncoding.EncodeToString(data)

    err = ioutil.WriteFile(outputFile, []byte(encodedData), 0644)
    if err != nil {
        return fmt.Errorf("failed to write file: %w", err)
    }

    return nil
}

// DecodeFile decodes the base64 content of the input file and writes it to the output file
func DecodeFile(inputFile, outputFile string) error {
    data, err := ioutil.ReadFile(inputFile)
    if err != nil {
        return fmt.Errorf("failed to read file: %w", err)
    }

    decodedData, err := base64.StdEncoding.DecodeString(string(data))
    if err != nil {
        return fmt.Errorf("failed to decode base64 data: %w", err)
    }

    err = ioutil.WriteFile(outputFile, decodedData, 0644)
    if err != nil {
        return fmt.Errorf("failed to write file: %w", err)
    }

    return nil
}

func ShowUsage() {
    fmt.Println("Usage:")
    fmt.Println("  go-base64 encode <input file> <output file>")
    fmt.Println("  go-base64 decode <input file> <output file>")
}

func main() {
    if len(os.Args) < 4 {
        ShowUsage()
        return
    }

    command := os.Args[1]
    inputFile := os.Args[2]
    outputFile := os.Args[3]

    var err error
    switch command {
    case "encode":
        err = EncodeFile(inputFile, outputFile)
    case "decode":
        err = DecodeFile(inputFile, outputFile)
    default:
        ShowUsage()
        return
    }

    if err != nil {
        fmt.Printf("Error: %v\n", err)
    } else {
        fmt.Printf("Success: %s completed\n", command)
    }
}