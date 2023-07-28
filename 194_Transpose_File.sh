#!/bin/bash

awk '
{
    for (i = 1; i <= NF; i++) {
        out[i] = (NR == 1) ? $i : out[i] " " $i
    }
}
END {
    for (i = 1; i <= NF; i++) {
        print out[i]
    }
}
' file.txt