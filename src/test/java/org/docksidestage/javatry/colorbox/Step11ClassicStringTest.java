/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.colorbox;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom.GuardianBox;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom.SecretBox;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, not using Stream API. <br>
 * Show answer by log() for question of javadoc. <br>
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author tahasega
 */
public class Step11ClassicStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() { // example, so begin from the next method
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            ColorBox colorBox = colorBoxList.get(0);
            BoxColor boxColor = colorBox.getColor();
            String colorName = boxColor.getColorName();
            int answer = colorName.length();
            log(answer + " (" + colorName + ")"); // also show name for visual check
        } else {
            log("*not found");
        }
    } // 5 (green)

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMax_colorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String maxColorName = null;
        for (ColorBox colorBox : colorBoxList) {
            String colorName = colorBox.getColor().getColorName();
            if (maxColorName == null || colorName.length() > maxColorName.length()) {
                maxColorName = colorName;
            }
        }
        log("色の名前が一番長いのは" + maxColorName);
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax_stringContent() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String maxStringContent = null;
        for (ColorBox colorBox : colorBoxList) {
            int spaceCount = colorBox.getSpaceList().size(); // カラーボックスの段数
            for (int i = 0; i < spaceCount; i++) {
                Object content = colorBox.getSpaceList().get(i).getContent();
                if (content instanceof String) {
                    String stringContent = (String) content;
                    if (maxStringContent == null //
                            || stringContent.length() > maxStringContent.length()) {
                        maxStringContent = stringContent;
                    }
                }
            }
        }
        log(maxStringContent != null ? "一番長い文字列は" + maxStringContent : "文字列は入ってないよ");
    }

    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (latter if same length) <br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (同じ長さのものがあれば後の方を))
     */
    public void test_length_findSecondMax_contentToString() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String maxStringContent = null;
        String secondMaxStringContent = null;
        for (ColorBox colorBox : colorBoxList) {
            int spaceCount = colorBox.getSpaceList().size();
            for (int i = 0; i < spaceCount; i++) {
                Object content = colorBox.getSpaceList().get(i).getContent();
                if (content instanceof String) {
                    String stringContent = (String) content;
                    if (maxStringContent == null //
                            || stringContent.length() > maxStringContent.length()) {
                        maxStringContent = stringContent;
                    } else if (secondMaxStringContent == null //
                            || stringContent.length() > secondMaxStringContent.length()) {
                        secondMaxStringContent = stringContent;
                    }
                }
            }
        }
        log(secondMaxStringContent != null ? "2番目に長い文字列は" + secondMaxStringContent : "文字列は2つ以上入ってないよ");
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int totalLength = 0;
        for (ColorBox colorBox : colorBoxList) {
            int spaceCount = colorBox.getSpaceList().size();
            for (int i = 0; i < spaceCount; i++) {
                Object content = colorBox.getSpaceList().get(i).getContent();
                if (content instanceof String) {
                    String stringContent = (String) content;
                    totalLength += stringContent.length();
                }
            }
        }
        log("文字列の長さの合計は" + totalLength);
    }

    // ===================================================================================
    //                                                                      Pickup Methods
    //                                                                      ==============
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            int spaceCount = colorBox.getSpaceList().size();
            for (int i = 0; i < spaceCount; i++) {
                Object content = colorBox.getSpaceList().get(i).getContent();
                if (content instanceof String) {
                    String stringContent = (String) content;
                    if (stringContent.startsWith("Water")) {
                        log(colorBox.getColor().getColorName());
                        break; // Waterなんとかが2つ入っていても2回表示しないため
                    }
                }
            }
        }
    }

    /**
     * What number character is starting with the late "ど" of string containing two or more "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            int spaceCount = colorBox.getSpaceList().size();
            for (int i = 0; i < spaceCount; i++) {
                Object content = colorBox.getSpaceList().get(i).getContent();
                if (content instanceof String) {
                    String stringContent = (String) content;
                    int firstIndex = stringContent.indexOf("ど");
                    int lastIndex = stringContent.lastIndexOf("ど");
                    if (lastIndex != -1 && firstIndex != lastIndex) { // 二つ以上含む
                        log("最後の「ど」は" + (lastIndex + 1) + "文字目 (" + stringContent + ")");
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                                 Welcome to Guardian
    //                                                                 ===================
    /**
     * What is total length of text of GuardianBox class in color-boxes? <br>
     * (カラーボックスの中に入っているGuardianBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToGuardian() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int totalLength = 0;
        for (ColorBox colorBox : colorBoxList) {
            int spaceCount = colorBox.getSpaceList().size();
            for (int i = 0; i < spaceCount; i++) {
                Object content = colorBox.getSpaceList().get(i).getContent();
                if (content instanceof GuardianBox) {
                    GuardianBox guardianBox = (GuardianBox) content;
                    guardianBox.wakeUp();
                    guardianBox.allowMe();
                    guardianBox.open();
                    try {
                        totalLength += guardianBox.getText().length();
                    } catch (Exception e) {
                        log("GuardianBoxのtextの取得に失敗: " + e.getMessage());
                    }
                }
            }
        }
        log("GuardianBoxのtextの長さの合計は" + totalLength);

    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            int spaceCount = colorBox.getSpaceList().size();
            for (int i = 0; i < spaceCount; i++) {
                Object content = colorBox.getSpaceList().get(i).getContent();
                if (content instanceof java.util.Map) {
                    java.util.Map<?, ?> map = (java.util.Map<?, ?>) content;
                    StringBuilder stringMap = new StringBuilder("map:{ ");
                    boolean valueContainsMap = false;
                    for (java.util.Map.Entry<?, ?> entry : map.entrySet()) {
                        if (entry.getValue() instanceof Map) {
                            valueContainsMap = true;
                        }
                        stringMap.append(entry.getKey()).append(" = ").append(entry.getValue()).append(" ; ");
                    }
                    stringMap.append("}");
                    if (!valueContainsMap){
                        log(stringMap.toString());
                    }
                }
            }
        }
    } // map.entrySet()でentry(キーとバリューのセット)を持ってくる。順番は保証されないらしい。

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            int spaceCount = colorBox.getSpaceList().size();
            for (int i = 0; i < spaceCount; i++) {
                Object content = colorBox.getSpaceList().get(i).getContent();
                if (content instanceof java.util.Map) {
                    java.util.Map<?, ?> map = (java.util.Map<?, ?>) content;
                    StringBuilder stringMap = new StringBuilder("map:{ ");
                    boolean valueContainsMap = false;
                    for (java.util.Map.Entry<?, ?> entry : map.entrySet()) {
                        if (entry.getValue() instanceof Map) {
                            valueContainsMap = true;
                        }
                        stringMap.append(entry.getKey()).append(" = ").append(entry.getValue()).append(" ; ");
                    }
                    stringMap.append("}");
                    if (valueContainsMap){
                        log(stringMap.toString());
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    // these are very difficult exercises so you can skip
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            if (colorBox.getColor().getColorName().equals("white")) {
                Object content = colorBox.getSpaceList().get(0).getContent();
                if (content instanceof SecretBox) {
                    SecretBox secretBox = (SecretBox) content;
                    String text = secretBox.getText();
                    Map<String, Object> map = parseStringToMap(text);
                    log(map.toString());
                    log(secretBox.toString());
                }
            }
        }
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            if (colorBox.getColor().getColorName().equals("white")) {
                Object middleContent = colorBox.getSpaceList().get(1).getContent();
                Object lowerContent = colorBox.getSpaceList().get(2).getContent();
                if (middleContent instanceof SecretBox && lowerContent instanceof SecretBox) {
                    SecretBox middleSecretBox = (SecretBox) middleContent;
                    SecretBox lowerSecretBox = (SecretBox) lowerContent;
                    String middleText = middleSecretBox.getText();
                    String lowerText = lowerSecretBox.getText();
                    Map<String, Object> middleMap = parseStringToMap(middleText);
                    Map<String, Object> lowerMap = parseStringToMap(lowerText);
                    log("Middle SecretBox: " + middleMap);
                    log("Lower SecretBox: " + lowerMap);
                }
            }
        }
    }

    // StringをMapに変換するメソッド
    // valueがMapのときは再帰的に処理する
    private static Map<String, Object> parseStringToMap(String text) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<String> entries = splitEntries(text);
        for (String entry : entries) {
            String[] parts = entry.split("=", 2); // 最初の"="で分割
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();
                if (value.startsWith("map:{") && value.endsWith("}")) {
                    map.put(key, parseStringToMap(value)); // 再帰！！
                } else {
                    map.put(key, value);
                }
            }
        }
        return map;
    }

    // 文字列を分割してMapのエントリーごとにリストにするメソッド
    private static List<String> splitEntries(String text) {
        List<String> entries = new ArrayList<>();
        StringBuilder entry = new StringBuilder();
        int nestLevel = 0; // valueがMapのときの階層を管理
        if (!text.startsWith("map:{ ") || !text.endsWith(" }")) {
            throw new IllegalArgumentException("Invalid format: " + text);
        }
        for (int i = 6; i < text.length() - 2; i++) { // 最初の"map:{ "、最後の" }"を除外
            char c = text.charAt(i);
            if (c == '{') {
                nestLevel++;
                entry.append(c);
            } else if (c == '}') {
                nestLevel--;
                entry.append(c);
            } else if (c == ';' && nestLevel == 0) {
                entries.add(entry.toString().trim());
                entry.setLength(0);
            } else {
                entry.append(c);
            }
        }
        if (entry.length() > 0) {
            entries.add(entry.toString().trim());
        }
        return entries;
    }
}
