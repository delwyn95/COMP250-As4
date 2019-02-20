//Sodelwyn Yit
//260639778
package assignment4;




import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
    // num of entries to the table
    private int numEntries=0;
    // num of buckets 
    private int numBuckets;
    // load factor needed to check for rehashing 
    private static final double MAX_LOAD_FACTOR = 0.75;
    // ArrayList of buckets. Each bucket is a LinkedList of HashPair
    private ArrayList<LinkedList<HashPair<K,V>>> buckets;

    // constructor
    public MyHashTable(int initialCapacity) {
        numBuckets = initialCapacity;
        buckets = new ArrayList<LinkedList<HashPair<K,V>>>();
        for (int i=0; i<numBuckets; i++) {
            buckets.add(new LinkedList<HashPair<K,V>>());
        }
    }
    
    public int size() {
        return this.numEntries;
    }
    
    public int numBuckets() {
        return this.numBuckets;
    }
    
    /**
     * Returns the buckets vairable. Usefull for testing  purposes.
     */
    public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
        return this.buckets;
    }
    /**
     * Given a key, return the bucket position for the key. 
     */
    public int hashFunction(K key) {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */

    public V put(K key, V value) {
        //  ADD YOUR CODE BELOW HERE
        int hashValue = hashFunction(key);

        LinkedList<HashPair<K,V>> bucket = buckets.get(hashValue);

        for (HashPair<K,V> entry: bucket) {
            // Overwrite the value
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        // We didn't overwrite, so we need to create a new node in the LL
        // Add it to the linkedlist (bucket)
        HashPair<K, V> pair = new HashPair<>(key, value);
        bucket.add(pair);
        numEntries++;
        return null;
        //  ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Get the value corresponding to key. Expected average runtime = O(1)
     */
    
    public V get(K key) {
        //ADD YOUR CODE BELOW HERE
        int hashValue = hashFunction(key);
        LinkedList<HashPair<K,V>> bucket = buckets.get(hashValue);
        //if Key exists:
        for (HashPair<K,V> entry : bucket){
            if (entry.getKey().equals(key)) return entry.getValue();
        }
        return null;
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Remove the HashPair correspoinding to key . Expected average runtime O(1) 
     */
    public V remove(K key) {
        //ADD YOUR CODE BELOW HERE
        int hashValue = hashFunction(key);
        V oldValue;
        LinkedList<HashPair<K,V>> bucket = buckets.get(hashValue);
        for (HashPair<K,V> entry : bucket){
            if (entry.getKey().equals(key)){
                oldValue = entry.getValue();
                // Remove the hash pair from the bucket
                bucket.remove(entry);
                numEntries--;
                return oldValue;
            }
        }

        return null;//remove
        //ADD YOUR CODE ABOVE HERE
    }
    
    // Method to double the size of the hashtable if load factor increases
    // beyond MAX_LOAD_FACTOR.
    // Made public for ease of testing.
    
    public void rehash() {
        //ADD YOUR CODE BELOW HERE


        ArrayList<LinkedList<HashPair<K,V>>> oldBuckets = buckets;
        MyHashTable<K,V> resizedHashTable = new MyHashTable<K,V>(this.numBuckets()*2);


        // Iterate through each bucket of the initial hashtable
        for (LinkedList<HashPair<K,V>> bucket : oldBuckets){
            // Iterate through each Hashpair of the bucket (LL)
            for (HashPair<K,V> pair : bucket) {
                // Add the Hashpair to the new hashtable
                resizedHashTable.put(pair.getKey(),pair.getValue());
            }
        }
        this.buckets = resizedHashTable.getBuckets();
        this.numBuckets *= 2;
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Return a list of all the keys present in this hashtable.
     */
    
    public ArrayList<K> keys() {
        //ADD YOUR CODE BELOW HERE
        ArrayList<K> keyValues = new ArrayList<>();
        for (LinkedList<HashPair<K,V>> bucket: buckets)
            for (HashPair<K,V> pair : bucket){
                keyValues.add(pair.getKey());
            }
        return keyValues;
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(n)
     */
    public ArrayList<V> values() {
        //ADD CODE BELOW HERE
        //doubling Values as keys+value, to keep track of dupliates and only store unique values.
        MyHashTable<V,V> hashTable = new MyHashTable<V,V>(this.numBuckets());
        ArrayList<V> uniqueValues = new ArrayList<V>();

        for (LinkedList<HashPair<K,V>> bucket : buckets) {
            for (HashPair<K, V> pair : bucket) {
                V tempValue = pair.getValue();
                // if == null -> new node created
                if (hashTable.put(tempValue, tempValue) == (null)) {
                    uniqueValues.add(tempValue);
                }
            }
        }
        return uniqueValues;
        //ADD CODE ABOVE HERE
    }
    
    
    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }
    
    
    private class MyHashIterator implements Iterator<HashPair<K,V>> {
        private LinkedList<HashPair<K,V>> entries;
        private MyHashIterator() {
            //
            entries = new LinkedList<HashPair<K,V>>();
            // Create a single linkedlist that merged each bucket together
            // Iterate through each bucket
            for (LinkedList<HashPair<K,V>> bucket: getBuckets()) {
                // Add each element of the bucket to the single linkedlist
                entries.addAll(bucket);
            }
        }

        @Override
        public boolean hasNext() {
            //return true if entry is true (non-empty)
            if (entries.size() > 0)
                return true;
            return false;// remove

        }
        
        @Override
        public HashPair<K,V> next() {
            //returns (remove first entry)
            return entries.pop();

        }
        
    }
}