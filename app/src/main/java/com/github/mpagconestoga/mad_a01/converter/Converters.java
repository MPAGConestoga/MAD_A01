
// Reference: https://developer.android.com/training/data-storage/room/referencing-data
package com.github.mpagconestoga.mad_a01.converter;

import androidx.room.TypeConverter;

import com.github.mpagconestoga.mad_a01.objects.Category;

import java.util.Date;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Category fromCategory(String value) { return value == null ? null : new Category(value); }

    @TypeConverter
    public static String categoryToString(Category category) { return category == null ? null : category.getName(); }
}

