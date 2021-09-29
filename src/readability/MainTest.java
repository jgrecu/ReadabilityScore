/*
 * Copyright (c) 2021. Jeremy Grecu
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package readability;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
class MainTest {

    @Test
    void main() {

    }

    @Test
    void getText() {
        assertEquals("expected result", Main.getText("in.txt"));
//        Exception exception = assertThrows(IOException.class, () -> Main.getText("into.txt"));
//        assertEquals("expected result", exception.getMessage());
    }

    @Test
    void countSyllablesRegex() {
        assertEquals(1, Main.countSyllablesRegex("the"));
        assertEquals(4, Main.countSyllablesRegex("disfranchised"));
        assertEquals(1, Main.countSyllablesRegex("rain"));
        assertEquals(1, Main.countSyllablesRegex("since"));
        assertEquals(3, Main.countSyllablesRegex("eloquent"));
    }

    String[] words = {"disfranchised", "rain", "room", "eloquent"};
    @Test
    void countSyllables() {
        assertEquals(9, Main.countSyllables(words));
    }

    @Test
    void countPolySyllables() {
        assertEquals(2, Main.countPolySyllables(words));
    }

    @Test
    void getARIScore() {
        assertEquals(7.081616266944735, Main.getARIScore(137, 14, 687));
        assertEquals(28.42285714285714, Main.getARIScore(157, 35, 1587));
        assertEquals(16.938377077865262, Main.getARIScore(127, 36, 987));
    }

    @Test
    void getFKScore() {
        assertEquals(6.314019812304483, Main.getFKScore(137, 14, 210));
    }

    @Test
    void getSMOGScore() {
        assertEquals(9.424239791934728, Main.getSMOGScore(14, 17));
    }

    @Test
    void getCLScore() {
        assertEquals(10.661021897810219, Main.getCLScore(137, 14, 687));
    }

    @Test
    void calculateAge() {
        assertEquals(16, Main.calculateAge(10.66));
        assertEquals(13, Main.calculateAge(7.8));
        assertEquals(12, Main.calculateAge(6.31));
        assertEquals(15, Main.calculateAge(9.42));
        assertEquals(24, Main.calculateAge(15.6));
    }
}