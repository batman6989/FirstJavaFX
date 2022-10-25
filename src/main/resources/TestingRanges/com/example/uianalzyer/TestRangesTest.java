package com.example.uianalzyer;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;


import static org.junit.jupiter.api.Assertions.*;

class TestRangesTest {

    @Test
    public void test() throws FileNotFoundException {
        File poem = new File("C:\\Users\\Johnn\\IdeaProjects\\UIAnalzyer\\src\\main\\java\\com\\example\\uianalzyer\\TheRaven.html");

        int range = -500;

        Fileconverter.textT(poem,range);

    }

    @Test
    void main() {
    }
}