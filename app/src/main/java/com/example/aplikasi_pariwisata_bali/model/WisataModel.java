package com.example.aplikasi_pariwisata_bali.model;

import android.os.Parcel;
import android.os.Parcelable;

public class WisataModel implements Parcelable {
    private final String name;
    private final String location;
    private final String description;
    private final Integer image;

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public Integer getImage() {
        return image;
    }

    public WisataModel(String name, String location, String desc, Integer img) {
        this.name = name;
        this.location = location;
        this.description = desc;
        this.image = img;
    }

    public WisataModel(Parcel in) {
        name = in.readString();
        location = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            image = null;
        } else {
            image = in.readInt();
        }
    }

    public static final Creator<WisataModel> CREATOR = new Creator<WisataModel>() {
        @Override
        public WisataModel createFromParcel(Parcel in) {
            return new WisataModel(in);
        }

        @Override
        public WisataModel[] newArray(int size) {
            return new WisataModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(location);
        parcel.writeString(description);
        if (image == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(image);
        }
    }
}
