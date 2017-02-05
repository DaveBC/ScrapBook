#
#  multi.sh
#
#  Created by David Banwell-Clode on 4/02/2017.
#  Copyright (c) 2017 David Banwell-Clode. All rights reserved.
#

#!/bin/bash

x=0
y=0
result=0

Question_Generator() {
    x=$(( ( RANDOM % 20 )  + 1 ))
    y=$(( ( RANDOM % 20 )  + 1 ))
    let "result = $x * $y"
}

Question_Generator

while true
do
    printf "%d x %d = " $x $y
    read answer
    if [ $answer -eq $result ]; then
        Question_Generator
    else
        continue
    fi
done