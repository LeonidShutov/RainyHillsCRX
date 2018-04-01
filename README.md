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
input array: {3,2,4,1,2}, it describes this kind of surface (asterisks describes "walls"):

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
# Assumptions

It is not stated explicitly in task, but we assume that, if we have "open boundary" in array, it could not be filled with water.
Example:
in this case
```
* ~ *
* * *
* * * *
3 2 3 1
```
result will be 1 unit of water. 

# Details of implementation

Interaction type application uses is not stated explicitly. I assume, that appropriate way to do it, to create REST service, user can interact with. 
It is convenient and popular way for application to interact with external world. 

# Deploy
To deploy this application, one should first run: mvn clean install
After that war file will be created in /target catalog
Next, you should go to GlassFish admin panel, then go to application section and click Deploy button. In deploy menu you need to choose created war file in Location section and set Context Root (for example - RainyHills)

# Using
To use this application, one should send POST request to ```http://{domain:port}/{ContextRoot}/rest/calculateVolume``` endpoint with JSON array "numbers" as input
examples of correct input:
```
     {"numbers":[9999999,0,2]}
     {"numbers":[1,0]}
```     
examples of incorrect input:
```
     {"numbers":["asd",0,2]} - not integer value in 0 position
     {"numbers":[9999999999999,0,2]} - not integer value in 0 position
     {"numbers":[5]} - array should contain at least 2 values
     {"numbers":""} - this isn't an array
     {"NOT_NUMBERS":[9999999,0,2]}  - array "numbers" isn't provided
```