package com.netcracker.edu.java.tasks;

/**
 * Created by Sir Nightmare on 07/28/16.
 * Class for complex number
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;


public class ComplexNumberImpl implements ComplexNumber {

    private double realPart = 0;
    private double imanginaryPart = 0;

    public ComplexNumberImpl(double re, double im) {
        realPart = re;
        imanginaryPart = im;
    }

    public ComplexNumberImpl(String value) {
        if (value.endsWith("i")) {

            value = value.substring(0, value.length() - 1);

            if (value.endsWith("+")) {
                this.imanginaryPart = 1;
                value = value.substring(0, value.length() - 1);
                this.realPart = Double.parseDouble(value);
                return;
            } else if (value.endsWith("-")) {
                this.imanginaryPart = -1;
                value = value.substring(0, value.length() - 1);
                this.realPart = Double.parseDouble(value);
                return;
            } else {
                int tempSign1 = 1;
                if (value.startsWith("+")) {
                    value = value.substring(1, value.length());
                } else if (value.startsWith("-")) {
                    tempSign1 = -1;
                    value = value.substring(1, value.length());
                }
                String[] str = value.split("\\+");
                int tempSign2 = 1;

                if (str.length == 1) {
                    str = value.split("-");
                    tempSign2 = -1;
                }

                if (str.length == 1) {
                    this.realPart = 0;
                    this.imanginaryPart = tempSign1 * Double.parseDouble(str[0]);
                    return;
                }
                this.realPart = tempSign1 * Double.parseDouble(str[0]);
                this.imanginaryPart = tempSign2 * Double.parseDouble(str[1]);
            }
        } else {
            this.realPart = Double.parseDouble(value);
            this.imanginaryPart = 0;
            return;
        }
    }

    public ComplexNumberImpl() {
        this(0, 0);
    }

    /**
     * @return real part of this complex number
     */
    @Override
    public double getRe() {
        return realPart;
    }

    /**
     * @return imaginary part of this complex number
     */
    @Override
    public double getIm() {
        return imanginaryPart;
    }

    /**
     * @return true if this complex number has real part only (otherwise false)
     */
    @Override
    public boolean isReal() {
        return imanginaryPart == 0;
    }

    /**
     * Sets both real and imaginary part of this number.
     *
     * @param re
     * @param im
     */
    @Override
    public void set(double re, double im) {
        realPart = re;
        imanginaryPart = im;
    }

    /**
     * Parses the given string value and sets the real and imaginary parts of this number accordingly.<br/>
     * The string format is "re+imi", where re and im are numbers (floating point or integer) and 'i' is a special symbol
     * denoting imaginary part (if present, it's always the last character in the string).<br/>
     * Both '+' and '-' symbols can be the first characters of re and im; but '*' symbol is NOT allowed.<br/>
     * Correct examples: "-5+2i", 1+i", "+4-i", "i", "-3i", "3". Incorrect examples: "1+2*i", "2+2", "j".<br/>
     * Note: explicit exception generation is an OPTIONAL requirement for this task,
     * but NumberFormatException can be thrown by {@link Double#parseDouble(String)}).<br/>
     * Note: it is not reasonable to use regex while implementing this method: the parsing logic is too complicated.
     *
     * @param value
     * @throws NumberFormatException if the given string value is incorrect
     */
    @Override
    public void set(String value) throws NumberFormatException {
        if (value.endsWith("i")) {

            value = value.substring(0, value.length() - 1);

            if (value.endsWith("+")) {
                this.imanginaryPart = 1;
                value = value.substring(0, value.length() - 1);
                this.realPart = Double.parseDouble(value);
                return;
            } else if (value.endsWith("-")) {
                this.imanginaryPart = -1;
                value = value.substring(0, value.length() - 1);
                this.realPart = Double.parseDouble(value);
                return;
            } else {
                int tempSign1 = 1;
                if (value.startsWith("+")) {
                    value = value.substring(1, value.length());
                } else if (value.startsWith("-")) {
                    tempSign1 = -1;
                    value = value.substring(1, value.length());
                }
                String[] str = value.split("\\+");
                int tempSign2 = 1;

                if (str.length == 1) {
                    str = value.split("-");
                    tempSign2 = -1;
                }

                if (str.length == 1) {
                    this.realPart = 0;
                    this.imanginaryPart = tempSign1 * Double.parseDouble(str[0]);
                    return;
                }
                this.realPart = tempSign1 * Double.parseDouble(str[0]);
                this.imanginaryPart = tempSign2 * Double.parseDouble(str[1]);
            }
        } else {
            this.realPart = Double.parseDouble(value);
            this.imanginaryPart = 0;
            return;
        }

    }

    /**
     * Creates and returns a copy of this object: <code>x.copy().equals(x)</code> but <code>x.copy()!=x</code>.
     *
     * @return the copy of this number
     * @see #clone()
     */
    @Override
    public ComplexNumber copy() {
        return new ComplexNumberImpl(this.realPart, this.imanginaryPart);
    }

    /**
     * Creates and returns a copy of this object: the same as {@link #copy()}.<br/>
     * Note: when implemented in your class, this method overrides the {@link Object#clone()} method but changes
     * the visibility and the return type of the Object's method: the visibility modifier is changed
     * from protected to public, and the return type is narrowed from Object to ComplexNumber (see also
     * <a href="http://docs.oracle.com/javase/specs/jls/se7/html/jls-8.html#d5e11368">covariant types example from JLS</a>).
     *
     * @return the copy of this number
     * @see Object#clone()
     */
    @Override
    public ComplexNumber clone() throws CloneNotSupportedException {

        return (ComplexNumberImpl) super.clone();
    }

    /**
     * Returns a string representation of this number, which must be compatible with {@link #set(String)}:
     * for any ComplexNumber x, the code <code>x.set(x.toString());</code> must not change x.<br/>
     * For example: 12.5-1.0i or 0.0 or 0.3333333333333333i<br/>
     * If the imaginary part of the number is 0, only "re" must be returned (where re is the real part).<br/>
     * If the real part of the number is 0 and the imaginary part is not 0,
     *  "imi" must be returned (where im is the imaginary part).<br/>
     * Both re and im must be converted to string "as is" - without truncation of last digits,
     * i.e. the number of characters in their string representation is not limited
     *   (it is determined by {@link Double#toString(double)}).
     * @see Object#toString()
     */

    @Override
    public String toString() {
        String reStr="";
        String imStr="";
        if(realPart>0){
            reStr=Double.toString(realPart);
        }
        if(realPart<0){
            reStr=Double.toString(realPart);
        }
        if(imanginaryPart<0){
            reStr=Double.toString(imanginaryPart)+"i";
        }
        if(imanginaryPart>0){
            imStr=Double.toString(imanginaryPart)+"i";
            if(realPart!=0){
                imStr="+"+imStr;
            }
        }

        return reStr+imStr;
    }

    /**
     * Checks whether some other object is "equal to" this number.
     * @param /other Any implementation of {@link ComplexNumber} interface (it may not be instance of ComplexNumberImpl!)
     * @see Object#equals(Object)
     */
    public boolean equals(Object  obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ComplexNumber)) {
            return false;
        }
        ComplexNumber other = (ComplexNumber) obj;
        if(this.getRe()!=other.getRe()){
            return false;
        }
        if(this.getIm()!=other.getIm()){
            return false;
        }
        return true;
    }



    /**
     * Compares this number with the other number by the absolute values of the numbers:
     * x < y if and only if |x| < |y| where |x| denotes absolute value (modulus) of x.<br/>
     * Can also compare the square of the absolute value which is defined as the sum
     * of the real part squared and the imaginary part squared: |re+imi|^2 = re^2 + im^2.
     *
     * @param other the object to be compared with this object.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the given object.
     * @see Comparable#compareTo(Object)
     */
    @Override
    public int compareTo(ComplexNumber other) {
        double firstNum = realPart * realPart + imanginaryPart * imanginaryPart;
        double secondNum = other.getRe() * other.getRe() + other.getIm() * other.getIm();
        if (firstNum > secondNum) {
            return 1;
        } else if (firstNum < secondNum) {
            return -1;
        }
        return 0;
    }

    /**
     * Sorts the given array in ascending order according to the comparison rule defined in
     * {@link #compareTo(ComplexNumber)}.<br/>
     * It's strongly recommended to use {@link //Arrays} utility class here
     * (and do not transform the given array to a double[] array).<br/>
     * Note: this method could be static: it does not use this instance of the ComplexNumber.
     * Nevertheless, it is not static because static methods can't be overridden.
     *
     * @param array an array to sort
     */
    @Override
    public void sort(ComplexNumber[] array) {
        Arrays.sort(array, new Comparator<ComplexNumber>() {
            @Override
            public int compare(ComplexNumber o1, ComplexNumber o2) {
                double firstNum = o1.getRe() * o1.getRe() + o1.getIm() * o1.getIm();
                double secondNum = o2.getRe() * o2.getRe() + o2.getIm() * o2.getIm();
                return o1.compareTo(o2);
            }
        });
    }

    /**
     * Changes the sign of this number. Both real and imaginary parts change their sign here.
     *
     * @return this number (the result of negation)
     */
    @Override
    public ComplexNumber negate() {
        realPart *= -1;
        imanginaryPart *= -1;
        return this;
    }

    /**
     * Adds the given complex number arg2 to this number. Both real and imaginary parts are added.
     *
     * @param arg2 the second operand of the operation
     * @return this number (the sum)
     */
    @Override
    public ComplexNumber add(ComplexNumber arg2) {
        realPart += arg2.getRe();
        imanginaryPart += arg2.getIm();
        return this;
    }

    /**
     * Multiplies this number by the given complex number arg2. If this number is a+bi and arg2 is c+di then
     * the result of their multiplication is (a*c-b*d)+(b*c+a*d)i<br/>            x1 y1            x2 y2
     * The method should work correctly even if arg2==this.
     *
     * @param arg2 the second operand of the operation
     * @return this number (the result of multiplication)
     */
    @Override
    public ComplexNumber multiply(ComplexNumber arg2) {
        double x1 = this.getRe();
        double y1 = this.getIm();
        double x2 = arg2.getRe();
        double y2 = arg2.getIm();
        realPart = x1 * x2 - y1 * y2;
        imanginaryPart = y1 * x2 + x1 * y2;
        return this;
    }
}
