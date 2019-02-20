# COMP250-As4


Implementing MyHashTable method:

1) put() method that takes a key and a value as input. (O(1))
2) get() method which takes a key as input and returns the value associated with it. (O(1))
3) remove() method that takes a key as input and removes from the table the entry (i.e. the HashPair) associated to this key. (O(1))
4) rehash() method which takes no input and modiﬁes the table so that it contains double the number of buckets. (O(1))
5) keys() method which takes no input and returns an ArrayList containing all the keys in the table (O(n))
6) values() method which takes no input and returns an ArrayList containing all the unique values in the table. (O(n))


MyMusicStore utilizing the MyHashTable method implemented containing:
1) addSong() which takes a Song as input and adds it to the MusicStore (O(1))
2) method searchByTitle() which takes a String as input and returns the Song with the provided title (O(1))
3) searchByArtist() which takes a String as input representing an artist and returns an ArrayList of Songs
containing all the songs in the MusicStore performed by the given artist (O(1))
4) searchByYear() which takes an Integer as input representing a year and returns an ArrayList of Songs containing
all the songs in the MusicStore produced in the given year. (O(1))
