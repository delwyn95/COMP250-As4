//Sodelwyn Yit
//260639778
package assignment4;

import java.util.ArrayList;

public class MusicStore {
    //ADD YOUR CODE BELOW HERE
    private ArrayList<Song> songList;
    private String title;
    private String artist;
    private int year;
    private MyHashTable<String, ArrayList<Song>> titleTable;
    private MyHashTable<String, ArrayList<Song>> artistTable;
    private MyHashTable<Integer, ArrayList<Song>> yearTable;

    //ADD YOUR CODE ABOVE HERE

    public MusicStore(ArrayList<Song> songs) {
        //ADD YOUR CODE BELOW HERE
        int numofSongs = songs.size();
        titleTable = new MyHashTable<String, ArrayList<Song>>(numofSongs);
        artistTable = new MyHashTable<String, ArrayList<Song>>(numofSongs);
        yearTable = new MyHashTable<Integer, ArrayList<Song>>(numofSongs);

        for (Song song : songs){
        //Populate the three HashTable based for each song in the input list
            ArrayList<Song> titleList = titleTable.get(song.getTitle());
            if (titleList == null){
                ArrayList<Song> newtitleList = new ArrayList<Song>();
                newtitleList.add(song);
                titleTable.put(song.getTitle(), newtitleList);
            }
            else{
                titleList.add(song);
                titleTable.put(song.getTitle(), titleList);
            }
            ArrayList<Song> artistList = artistTable.get(song.getArtist());
            if (artistList == null){
                ArrayList<Song> newArtistList = new ArrayList<Song>();
                newArtistList.add(song);
                artistTable.put(song.getArtist(), newArtistList);
            }
            else{
                artistList.add(song);
                artistTable.put(song.getArtist(), artistList);
            }

            ArrayList<Song> yearList = yearTable.get(song.getYear());
            if (yearList == null) {
                ArrayList<Song> newYearList = new ArrayList<Song>();
                newYearList.add(song);
                yearTable.put(song.getYear(), newYearList);
            }
            else {
                yearList.add(song);
                yearTable.put(song.getYear(), yearList);
            }
        }
        //ADD YOUR CODE ABOVE HERE
    }

    /**
     * Add Song s to this MusicStore
     */
    public void addSong(Song s) {

        // Get the current List of songs for a specific title
        ArrayList<Song> listOfSongsForSpecificTitle = titleTable.get(s.getTitle());
        listOfSongsForSpecificTitle.add(s);
        titleTable.put(s.getTitle(), listOfSongsForSpecificTitle);

        // Get the current List of songs for a specific artist
        ArrayList<Song> listOfSongsForSpecificArtist = artistTable.get(s.getArtist());
        listOfSongsForSpecificArtist.add(s);
        artistTable.put(s.getArtist(), listOfSongsForSpecificArtist);

        // Get the current List of songs for a specific year
        ArrayList<Song> listOfSongsForSpecificYear = yearTable.get(s.getYear());
        listOfSongsForSpecificYear.add(s);
        yearTable.put(s.getYear(), listOfSongsForSpecificYear);

    }
    
    /**
     * Search this MusicStore for Song by title and return any one song 
     * by that title 
     */
    public Song searchByTitle(String title) {
//        String searched = title;
    	ArrayList<Song> bucket = titleTable.get(title);
    	if (bucket.size() > 0)
    		return titleTable.get(title).get(0);
    	else
    		return null;
    }
    
    /**
     * Search this MusicStore for song by `artist' and return an 
     * ArrayList of all such Songs.
     */
    public ArrayList<Song> searchByArtist(String artist) {
        //ADD CODE BELOW HERE
        return artistTable.get(artist);
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicSotre for all songs from a `year'
     *  and return an ArrayList of all such  Songs  
     */
    public ArrayList<Song> searchByYear(Integer year) {
        //ADD CODE BELOW HERE
        return yearTable.get(year);
        //ADD CODE ABOVE HERE
        
    }
}
