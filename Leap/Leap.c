//
//  Leap.c
//  Leap year identifier.
//
//  Created by David Banwell-Clode on 16/10/2015.
//  Copyright (c) 2015 David Banwell-Clode. All rights reserved.
//

#include <stdio.h>
int isLeapYear(int year);
 
int main(int argc, char **argv)
{
    int num;
    int bool;
    int input;
    int quit;
    
    printf("Please enter a year or -1 to quit: ");
    while(quit != 1) {
        input = scanf("%d",&num);
        if(input > 0) {
            if(num == -1) {
                return 0;
            }
            
            if(isLeapYear(num)) {
                printf("%d is a leap year!\n", num);
            }
            else {
                printf("%d is not a leap year.\n", num);
            }
            printf("Please enter a year or -1 to quit: ");
        } 
        else {
            printf("Please enter a year or -1 to quit: \n");
        }
    }
}

/*
 * Leap year test.
 */
int isLeapYear(int year) 
{
    if(year % 4 == 0) {
        if(year % 100 == 0) {
            if(year % 400 == 0) {
                return 1;
            }
            else {
                return 0;
            }
        }
        else {
            return 1;
        }
    }
    else {
        return 0;
    }
}
