# Diffie Hellman

######  This mini project was an in class assignment with the goal to implement Diffie Hellman's key exchange protocol

To run:

```
cd out/production/Diffie-Hellman-hw2/
java Program 5
```
## The User Class

This class is used to generate users based on a large prime number **p** and a random number **g** member of the set {2,3,...,p-1}.
This class initializes a variable *chosenNum* which is a random integer member of the set  <img src="https://latex.codecogs.com/gif.latex?Z_p" />.

Other methods are specified and you can read the source code to find out what they do. 
## The Program Class

This class is used to wrap around the user class and grab input from the user to perform the operations necessary to implement the protocol.

