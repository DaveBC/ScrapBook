//
//  stocks.c
//  Daily Programmer Intermediate Challenge #249
//  https://www.reddit.com/r/dailyprogrammer/comments/40h9pd/20160111_challenge_249_easy_playing_the_stock/
// 
//  Created by David Banwell-Clode on 24/01/2016.
//  Copyright (c) 2016 David Banwell-Clode. All rights reserved.
//

#include <stdlib.h>
#include <stdio.h>
#include <strings.h>

int main(int argc, char **argv)
{

    double high = 0.0;
    double low = 0.0;
    double profit = 0.0;
    int i, j;
    int index, numberIndex, lowIndex, highIndex = 0;
    
    char *number = calloc(6, sizeof(char));
    char *stock_lists = calloc(1000, sizeof(char));
    double *stock_list = calloc(300, sizeof(double));

    // Read input.
    printf("Enter a list of stock prices.\n");
    fgets(stock_lists,999,stdin);
    
    // Convert to doubles.
    for(i = 0; i < 1000; i++) {
        if(stock_lists[i] == '\0') {
            break;
        }
        if(stock_lists[i] == ' ') {
            stock_list[index] = atof(number);
            numberIndex = 0;
            bzero(number, 6);
            index++;
        }
        else {
            number[numberIndex] = stock_lists[i];
            numberIndex++;
        }
    }
    stock_list[index] = atof(number);
    numberIndex = 0;
    index++;
    
    // Find biggest margin.
    for(i = 0; i < index; i++) {
        for(j = i+2; j < index; j++) {
            if(stock_list[j]-stock_list[i] > profit) {
                profit = stock_list[j] - stock_list[i];
                lowIndex = i;
                highIndex = j;
            }
        }
    }

    printf("Low: %.2f (%d)\n", stock_list[lowIndex], lowIndex);
    printf("High: %.2f (%d)\n", stock_list[highIndex], highIndex);
    printf("Profit: %.2f\n", stock_list[highIndex] - stock_list[lowIndex]);

    free(stock_list);
    free(stock_lists);
    free(number);
    return 0;
}