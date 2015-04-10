package com.necrolore.util;


/**
 * A pair of objects
 * 
 * @author NecroTheif
 *
 * @param <A>   The object type of the first object in the pair
 * @param <B>   The object type of the second object in the pair
 */
public class Pair<A, B> {

	/* The two objects that make up the pair */
		protected A A;
		protected B B;
		
		
	/**
	 * Creates a pair object holding the two given objects
	 * 
	 * @param B    The first object of the pair
	 * @param A   The second object of the pair
	 */
	public Pair(A A, B B){
		
		/* Initialize class variables to the given objects */
			this.A = A;
			this.B = B;
		
	}// End two-argument constructor for Pair


	/**
	 * Gets the first object of the pair
	 * 
	 * @return the first object in the pair
	 */
	public A getA() {
		
		/* Return the Pair's first object */
			return A;
		
	}// End method getFirstObject


	/**
	 * Changes the first object of the pair to the given value
	 * 
	 * @param A   the new first object in the pair
	 */
	public void setA(A A) {
		
		/* Set the Pair's first object to the given one */
			this.A = A;
		
	}// End method setFirstObject


	/**
	 * Gets the second object of the pair
	 * 
	 * @return the second object in the pair
	 */
	public B getB() {
		
		/* Return the Pair's second object */
			return B;
		
	}// End method getSecondObject


	/**
	 * Changes the second object of the pair to the given value
	 * 
	 * @param B   the new second object in the pair
	 */
	public void setB(B B) {
		
		/* Set the Pair's second object to the given one */
			this.B = B;
		
	}// End method setSecondObject
	
	
}// End class Pair
