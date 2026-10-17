package behaviouralDesignPattern.momentoPattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IteratorDP {
    public static void main(String[] args) {
        PlayList playlist = new PlayList();
        playlist.addSong("Song 1");
        playlist.addSong("Song 2 Fav");
        playlist.addSong("Song 3");
        playlist.addSong("Song 4 Fav");
        playlist.addSong("Song 5");

        // Simple Playlist Iterator
        System.out.println("Simple Playlist:");
        PlayListIterator simpleIterator = playlist.iterator("simple");
        while (simpleIterator.hasNext()) {
            System.out.println("Playing: " + simpleIterator.next());
        }

        // Shuffled Playlist Iterator
        System.out.println("\nShuffled Playlist:");
        PlayListIterator shuffledIterator = playlist.iterator("shuffled");
        while (shuffledIterator.hasNext()) {
            System.out.println("Playing: " + shuffledIterator.next());
        }

        // Favorites Playlist Iterator
        System.out.println("\nFavorites Playlist:");
        PlayListIterator favoritesIterator = playlist.iterator("favorites");
        while (favoritesIterator.hasNext()) {
            System.out.println("Playing: " + favoritesIterator.next());
        }
    }
}

interface PlayListIterator {
    public boolean hasNext();
    public String next();
}

class PlayList {
    public List<String> songs;
    public PlayList() {
        songs = new ArrayList<>();
    }
    public PlayList(List<String> songs) {
        this.songs = songs;
    }
    public void addSong(String song) {
        songs.add(song);
    }
    public PlayListIterator iterator(String type) {
        if (type.equals("simple")) {
            return new SimplePlayListIterator(this);
        } else if (type.equals("shuffled")) {
            return new ShufflePlayListIterator(this);
        } else if (type.equals("favorites")) {
            return new FavoritePlayListIterator(this);
        } else return null;
    }
}

class SimplePlayListIterator implements PlayListIterator {
    private final PlayList playList;
    private int index;
    public SimplePlayListIterator(PlayList playList) {
        this.playList = playList;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < this.playList.songs.size();
    }

    @Override
    public String next() {
        return this.playList.songs.get(index++);
    }
}

class ShufflePlayListIterator implements PlayListIterator {
    private final PlayList playList;
    private int index;
    public ShufflePlayListIterator(PlayList playList) {
        this.playList = playList;
        Collections.shuffle(this.playList.songs);
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < playList.songs.size();
    }

    @Override
    public String next() {
        return this.playList.songs.get(index++);
    }
}

class FavoritePlayListIterator implements PlayListIterator {
    private final PlayList playList;
    private int index;
    public FavoritePlayListIterator(PlayList playList) {
        this.playList = playList;
        this.index = 0;
        this.playList.songs = filter(this.playList.songs);
    }

    private List<String> filter(List<String> songs) {
        List<String> newPlaylist = new ArrayList<>();
        for (String song : songs) {
            String[] words = song.split(" ");
            if (words[words.length - 1].toLowerCase().equals("fav")) newPlaylist.add(song);
        }
        return newPlaylist;
    }
    @Override
    public boolean hasNext() {
        return index < playList.songs.size();
    }

    @Override
    public String next() {
        return this.playList.songs.get(index++);
    }
}