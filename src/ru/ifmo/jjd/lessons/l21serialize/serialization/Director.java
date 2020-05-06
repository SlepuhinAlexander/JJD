package ru.ifmo.jjd.lessons.l21serialize.serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

public class Director extends Human implements Externalizable {
    private String speech;
    private int rating;

    public Director(String speech) {
        this.speech = speech;
    }

    public Director() {
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Director)) return false;
        Director director = (Director) o;
        return Objects.equals(speech, director.speech);
    }

    @Override
    public int hashCode() {
        return speech != null ? speech.hashCode() : 0;
    }


    @Override
    public String toString() {
        return "Director{" +
               "speech='" + speech + '\'' +
               ", rating=" + rating +
               ", name='" + name + '\'' +
               ", age=" + age +
               '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        /*
         * Явно описываем последовательность действий, которую нужно сделать при сериализации
         * */
        out.writeUTF(speech);
        out.writeInt(rating);
        out.writeInt(age);

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        /*
         * Явно описываем последовательность действий, которую нужно сделать при десериализации
         * */
        speech = in.readUTF();
        rating = in.readInt();
        age = in.readInt();
    }
}
