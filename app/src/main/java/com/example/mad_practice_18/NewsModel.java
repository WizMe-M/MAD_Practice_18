package com.example.mad_practice_18;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsModel implements Parcelable {
    public String Header;
    public String Datetime;
    public String Text;
    public String Author;

    public NewsModel() {
    }

    public NewsModel(String header, String datetime, String text, String author) {
        Header = header;
        Datetime = datetime;
        Text = text;
        Author = author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(Header);
        out.writeString(Text);
        out.writeString(Datetime);
        out.writeString(Author);
    }

    public static final Parcelable.Creator<NewsModel> CREATOR = new Parcelable.Creator<NewsModel>() {
        public NewsModel createFromParcel(Parcel in) {
            return new NewsModel(in);
        }

        public NewsModel[] newArray(int size) {
            return new NewsModel[size];
        }
    };

    /**
     * recreate object from parcel
     */
    private NewsModel(Parcel in) {
        Header = in.readString();
        Text = in.readString();
        Datetime = in.readString();
        Author = in.readString();
    }
}
