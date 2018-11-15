/*
 * This program is an implementation of Diffie-Hellman's key exchange protocol
 * Usage: java Program <int bitSize>
 *     The number fed into the program will be used as the number of bits used
 *     to produce a prime number p
 *     The primer number p will then be the upper bound (inclusive) of a random
 *     number g
 *     Two User objects (Alice, Bob) are created with these two variables
 *
 * Author: Hugo Perdomo
 */

import java.math.BigInteger;
import java.util.Random;

public class Program {
    private static Random rand = new Random(System.currentTimeMillis()); // Random generator with seed

    public static void main(String[] args) {

        int bitSize = 0; // Initialize this because the code after try/catch clause ran into some issues

        /* Catch user input errors!! */
        try {
            bitSize = Integer.parseInt(args[0]); // Save input from cli program call
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException exc){
            System.out.println("You must provide an integer as a parameter!");
            System.exit(0);
        }

        BigInteger p = getPrime(bitSize);
        BigInteger g = new BigInteger(bitSize, rand);
        g = clean(g, p); // In order to make it inclusive we need to add 1 to p

        // Bob and Alice agree on a prime number p and a random g member of the set {2,3,...,p-1}
        User Alice = new User(p, g);
        User Bob = new User(p, g);

        System.out.println("p: " + p);
        System.out.println("g " + g);
        System.out.println("Alice selected: " + Alice.getChosenNum());
        System.out.println("Bob selected: " + Bob.getChosenNum());
        Alice.generateMessage(); // Generate the message to send to Bob: g^a mod p
        System.out.println("Alice sent to Bob: " + Alice.getMessage());
        Bob.generateMessage(); // Generate the message to send to Alice: g^b mod p
        System.out.println("Bob sent to Alice: " + Bob.getMessage());
        System.out.println("Bob got from Alice key: " + Bob.decryptKey(Alice.getMessage()));
        System.out.println("Alice got from Bob key: " + Alice.decryptKey(Bob.getMessage()));

    }

    /*
     * Returns a random prime BigInteger of n-bits in size
     */
    private static BigInteger getPrime(int n){
        return new BigInteger(n, 100, rand);
    }

    /*
     * Returns a BigInteger that is a member of the set {2, 3, ..., bound-1}
     */
    private static BigInteger clean(BigInteger num, BigInteger bound){
        BigInteger res = num.mod(bound);
        if(res.intValue() <= 1){
            return new BigInteger("2");
        }else {
            return res;
        }
    }

}

