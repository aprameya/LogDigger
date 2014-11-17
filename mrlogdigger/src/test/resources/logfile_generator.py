#!/usr/bin/env python 
import sys, random, datetime, time 

#The original script is modified to produce a more realistic access log file 
#consisting of timestamps of user visits along with the user's IP address.

def main(count): 
	for x in range(count): 
		first_number = random.randint(0, 255) 
		second_number = random.randint(0, 255) 
		third_number = random.randint(0, 255) 
		fourth_number = random.randint(0, 255) 
		user_log_count = random.randint(0, 5) 
		for x in range(user_log_count): 
			year = random.randint(2013, 2014) 
			month = random.randint(1, 12) 
			day = random.randint(1, 30) 
			hour = random.randint(1, 23) 
			minute = random.randint(1, 59) 
			second = random.randint(1, 59) 
			#line format: [2014-11-23 23:21:52] 200.158.1.71
			print ("[%04d-%02d-%02d %02d:%02d:%02d] %d.%d.%d.%d" % (year, month, day, hour, minute, second, first_number, second_number, third_number, fourth_number))

if __name__ == "__main__": 
	main(int(sys.argv[1]))