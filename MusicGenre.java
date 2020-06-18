public enum MusicGenre {
    RAP,
    POST_ROCK,
    BRIT_POP;

    public static boolean existence(String name) {
        boolean f = false;
        for (MusicGenre genre : MusicGenre.values()) {
            if (genre.name().equals(name)) {
                f = true;
            }
        }
        return f;
    }
}

