#!/usr/local/bin/python3

# encoding: utf-8


import sys


def main2():
    if len(sys.argv) > 1 :
        print("\n\n\n\t\tHello " + sys.argv[1])
    else:
        print ("\n\n\n\t\thello stranger")

    print("\n\n\n")
    exit(0) 
    

if __name__ == "__main__":

    sys.exit(main2())
