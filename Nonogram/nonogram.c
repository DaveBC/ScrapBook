//
//  nonogram.c
//  Daily Programmer Easy Challenge #251
//  https://www.reddit.com/r/dailyprogrammer/comments/42lhem/20160125_challenge_251_easy_create_nonogram/
// 
//  Created by David Banwell-Clode on 26/01/2016.
//  Copyright (c) 2016 David Banwell-Clode. All rights reserved.
//

#include <stdlib.h>
#include <stdio.h>

struct linkedlist {
    int count;
    struct linkedlist *next;
};

int main(int argc, char** argv)
{
    char *input = calloc(1001, sizeof(char));
    int squareSize = 0, i = 0, j = 0;
    
    // Read input.
    printf("Enter an image in ASCII with ' ' being empty and '*' being full.\n");
    fgets(input,1000,stdin);
    
    while(i <= 1000) {
        if(input[i] == '\n') {
            squareSize = i;
            break;
        }
        i++;
    }
    
    int *row = calloc(squareSize, sizeof(int));
    int *col = calloc(squareSize, sizeof(int));
    
    int consec = 0;
    
    for(i = 0; i < squareSize*squareSize; i++) {
        if(input[i] == ' ') {
            row[j] = consec;
            j++;
            consec = 0;
        }
        if(input[i] == '*') {
            consec++;
            printf("consec: %d\n",consec);
        }
    }
    
    printf("\n");
    for(i=0; i<squareSize; i++) {
        printf("%d ", row[i]);
    }
    printf("\n");
    
    free(row);
    free(col);
    return 0;
}