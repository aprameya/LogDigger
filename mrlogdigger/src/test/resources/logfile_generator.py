#!/usr/bin/env python 
import sys, random 

def main(count): 
	for x in range(count): 
		first_number = random.randint(0, 255) 
		second_number = random.randint(0, 255) 
		third_number = random.randint(0, 255) 
		fourth_number = random.randint(0, 255) 
		print ("%d.%d.%d.%d" % (first_number, second_number, third_number, fourth_number))

if __name__ == "__main__": 
	main(int(sys.argv[1]))