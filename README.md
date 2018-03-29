# RainyHillsCRX
Java EE application that solves Rainy Hills problem.

This is GlassFish container. 

Application provides REST service which takes an array as an input, and calculates the volume of water
which remained after the rain, in units.

# Detailed task description

INPUT: array of integers
OUTPUT: integer number, further called "volume"

Input array describes profile of a surface.
Example:
input array: {3,2,4,1,2}, it describes this kind of surface (pipes describes "walls"):

```
    *
*   *
* * *   *
* * * * *
3 2 4 1 2
```
Now imagine that there was a heavy rain, and all possible "holes" are filled with water.
In this case, we have volume == 2 units of water (tilde describes water):
```
    *
* ~ *
* * * ~ *
* * * * *
3 2 4 1 2
```
Another example, here we have volume == 8 units of water:
```
*
* ~ ~ ~ ~ *
* ~ ~ ~ * *
* * * ~ * *
4 1 1 0 2 3
```
# Details of implementation and assumptions

It is not stated explicitly in task, but we assume that, if we have "open boundary" in array, it could not be filled with water.
Example:
in this case
```
*   *
* * *
* * * *
3 2 3 1
```
result will be 1 unit of water. 
