//
//  selfDescriptiveNumbers.c
//  Daily Programmer Intermediate Challenge #250
//  https://www.reddit.com/r/dailyprogrammer/comments/41tdzy/20160120_challenge_250_intermediate/
// 
//  Created by David Banwell-Clode on 21/01/2016.
//  Copyright (c) 2016 David Banwell-Clode. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <math.h>

int range = 1;

/*
 *  Returns the description of a given number.
 */
int description(int num) {
    int i, j, count, numlen;
    int *str = calloc(range, sizeof(int));
    int *number = calloc(range, sizeof(int));
    i = range-1;
    
    // Split num into digits.
    while (num > 0) {
        int digit = num % 10;
        str[i] = digit;
        num /= 10;
        i--;
    }
    
    // Find where the number actually starts.
    i = 0;
    while(i < range) {
        if(str[i] != 0) {
            break;
        }
        i++;
    }
    numlen = i;
    
    // Shift number to left to remove 0's.
    for(i = 0; i+numlen < range; i++) {
        number[i] = str[numlen+i];
    }
    
    // Space for digits 0-9.
    int res[10] = {0};
    numlen = range-numlen;

    // Count number of occurances of a digit within the number.
    for(i = 0; i < 10; i++) {
        count = 0;
        for(j = 0; j < numlen; j++) {
            if(number[j] == i) {
                count++;
            } 
        }
        res[i] = count;
    }
    
    int desc = 0;
    j = 0;
    // Convert result stored in res array into a number.
    while(numlen > 0) {
        numlen--;       
        desc += pow(10,numlen)*res[j];
        j++;
    }

    free(number);
    free(str);
    return desc;
}

/*
 * Takes a number as an argument to find self-descriptive numbers with that many digits.
 */
int main(int argc, char **argv)
{
    int flag = 0;   // Indicate whether a self-descriptive number has been found.
    range = atoi(argv[1]);
    
    // Not valid input or no self-descriptive numbers exist for that range.
    if(range < 4 || range == 6) {
        printf("No self-descriptive number found.\n");
        return EXIT_SUCCESS;
    }
    else if(range == 4 || range == 5) {
        // Upper and lower limit to search.
        int ulim, llim;
        llim = pow(10,range-1);
        ulim = pow(10,range-1)*(range-1) + pow(10,range-2); // No need to exhaust full range. i.e 3100 instead of 9999.
        
        int i;
        // Search through numbers.
        for(i = llim; i < ulim; i++) {
            if(i == description(i)) {   // Found one.
                printf("%d\n",i);
                flag = 1;
            }
        }    
    }
    else {
        // Equation for finding self-descriptive numbers with digits > 7. Source: Wikipedia.
        long selfdesc = (range-4)*pow(range,range-1) + 2*pow(range,range-2) + pow(range,range-3) + pow(range,3);
        long remainder = 1;
        int index = 0;
        int *digits = calloc(range, sizeof(int));
        
        // Change from base 10 to base 'range'.
        while(selfdesc != 0) {
            remainder = selfdesc % range;
            selfdesc = selfdesc / range;
            digits[index] = remainder;
            index++;
        }
        
        // Convert array into number.
        while(index > 0) {
            index--;
            selfdesc += pow(10, index)*digits[index];
        }
        free(digits);
        printf("%ld\n",selfdesc);
        flag = 1;
    }
    
    
    if(flag == 0) printf("No self-descriptive number found.\n");
    
    return EXIT_SUCCESS;
}