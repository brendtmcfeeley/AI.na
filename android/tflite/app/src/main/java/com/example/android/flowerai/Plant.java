package com.example.android.flowerai;

public class Plant {
    String common_name;
    String conservation_status;
    String family;
    String name;
    String native_status;

    Plant (String commonName, String conservationStatus, String family, String name, String nativeStatus) {
        this.common_name = commonName;
        this.conservation_status = conservationStatus;
        this.family = family;
        this.name = name;
        this.native_status = nativeStatus;
    }
}
