package domain;

import json.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    protected ArrayList<Tuple<String, Integer>> exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);

        this.exams = new ArrayList<>();
        Collections.addAll(this.exams, exams);
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject output = new JsonObject();
        output.add(new JsonPair("name", new JsonString(this.name)));
        output.add(new JsonPair("surname", new JsonString(this.surname)));
        output.add(new JsonPair("year", new JsonNumber(this.year)));

        JsonObject[] examsArray = new JsonObject[this.exams.size()];

        int i = 0;
        for (Tuple<String, Integer> exam: this.exams) {
            JsonObject examObject = new JsonObject();
            examObject.add(new JsonPair("course", new JsonString(exam.key)));
            examObject.add(new JsonPair("mark", new JsonNumber(exam.value)));
            examObject.add(new JsonPair("passed", new JsonBoolean(exam.value >= 3)));

            examsArray[i++] = examObject;
        };

        output.add(new JsonPair("exams", new JsonArray(examsArray)));

        return output;
    }
}