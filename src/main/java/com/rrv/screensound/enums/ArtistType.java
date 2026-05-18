package com.rrv.screensound.enums;

public enum ArtistType {
    SOLO("Solo"),
    DUO("Dupla"),
    BAND("Banda");

    public final String displayName;

    ArtistType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
