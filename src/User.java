/*
 * This class
 * Author: Hugo Perdomo
 */

import java.math.BigInteger;
import java.util.Random;

public class User {

    private BigInteger chosenNum;
    private BigInteger message;
    private BigInteger p;
    private BigInteger g;

    public User(BigInteger p, BigInteger g){
        this.p = p;
        this.g = g;
        chosenNum = generateNum(p);
    }

    private BigInteger generateNum(BigInteger bound){
        Random rand = new Random(System.currentTimeMillis());
        int bits = bound.bitLength();
        int accuracy = 100;
        BigInteger res = new BigInteger(bits, accuracy, rand).mod(bound);
        if (res.intValue() == 0){
            return new BigInteger("1"); // Return a one in case 0 is reached
        }else {
            return res;
        }
    }

    public BigInteger getChosenNum(){
        return chosenNum;
    }

    public void generateMessage(){
        message = g.pow(chosenNum.intValue()).mod(p);
    }

    public BigInteger getMessage(){
        return message;
    }

    /* Computes (A)^b mod p | (B)^a mod p */
    public BigInteger decryptKey(BigInteger K){
        return K.pow(chosenNum.intValue()).mod(p);
    }
}
