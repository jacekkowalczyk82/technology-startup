#!/usr/local/bin/python3

# encoding: utf-8


import sys


def main2():
    name = input("What is your name? ")
    print("\n\n\n\t\tHello " + name)
    if name == "Jacek":
        print("\n\n\n\t\tYou are the best! 🏍️")
    elif name == "Kuba":
        print("\n\n\n\t\tYou are the second best! ")
        print("\t\tYou love minecraft! ⛏️ ⚔️ 🟩 🧟 💎")
    else:
        print("\n\n\n\t\tYou are not the best!")

    print("\n\n\n")
    exit(0) 
    

if __name__ == "__main__":

    sys.exit(main2())
