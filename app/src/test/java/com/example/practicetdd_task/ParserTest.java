package com.example.practicetdd_task;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.Parser;

import java.util.ArrayList;
import java.util.List;

public class ParserTest {

    @Before
    public void setUp() {

        Parser parser = new BaseParser();
    }

    @Test
    public void parse() {
        String data = "123\ntrue\napple\nm";

        List<Object> expected = new ArrayList<>();
        expected.add(123);
        expected.add(true);
        expected.add("apple");
        expected.add('m');

        List<Object> actual = parser.parse(data);

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
