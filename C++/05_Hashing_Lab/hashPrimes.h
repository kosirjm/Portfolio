/**
 *
 * This is a helpful list of good hash table sizes, from 
 * http://planetmath.org/goodhashtableprimes
 *
 * Each time you call grow, select the next item from this list that is larger
 * than the current backing array size
 *
 */

#define NUM_HASH_PRIMES 26

unsigned long hashPrimes[] = {53,
			      97,
			      193,
			      389,
			      769,
			      1543,
			      3079,
			      6151,
			      12289,
			      24593,
			      49157,
			      98317,
			      196613,
			      393241,
			      786433,
			      1572869,
			      3145739,
			      6291469,
			      12582917,
			      25165843,
			      50331653,
			      100663319,
			      201326611,
			      402653189,
			      805306457,
			      1610612741
};
