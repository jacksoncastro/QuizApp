package br.com.jackson.quizapp.enums;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jackson on 03/12/16.
 */

public enum LevelEnum implements Parcelable {

    EASY(5), MEDIUM(10), HARD(15);

    public static final Creator<LevelEnum> CREATOR = new Creator<LevelEnum>() {
        @Override
        public LevelEnum createFromParcel(Parcel in) {
            return LevelEnum.values()[in.readInt()];
        }

        @Override
        public LevelEnum[] newArray(int size) {
            return new LevelEnum[size];
        }
    };

    private final int amount;

    LevelEnum(int amount) {
        this.amount = amount;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static LevelEnum getLevelByPosition(int position) {
        for (LevelEnum levelEnum : LevelEnum.values()) {
            if (levelEnum.ordinal() == position) {
                return levelEnum;
            }
        }
        return LevelEnum.EASY;
    }

    public int getAmount() {
        return amount;
    }
}
