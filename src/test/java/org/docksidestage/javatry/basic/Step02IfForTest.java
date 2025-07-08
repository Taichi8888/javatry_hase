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
package org.docksidestage.javatry.basic;

import java.util.ArrayList;
import java.util.List;

import org.docksidestage.unit.PlainTestCase;

// done hase ↑unusedのimport文があります by jflute (2025/07/08)
// [1on1でのフォロー] IDEが、それなりに警告とかお知らせとかしてくれるので注目しましょう

/**
 * The test of if-for. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author tahasega
 */
public class Step02IfForTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        if Statement
    //                                                                        ============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_if_basic() { // example, so begin from the next method
        int sea = 904;
        if (sea >= 904) {
            sea = 2001;
        }
        log(sea); // your answer? => 2001
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_else_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else {
            sea = 7;
        }
        log(sea); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (sea >= 904) {
            sea = 7;
        } else if (sea >= 903) {
            sea = 8;
        } else {
            sea = 9;
        }
        log(sea); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_nested() {
        boolean land = false;
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (land && sea >= 904) {
            sea = 7;
        } else if (sea >= 903 || land) {
            sea = 8;
            if (!land) {
                land = true;
            } else if (sea <= 903) {
                sea++;
            }
        } else if (sea == 8) {
            sea++;
            land = false;
        } else {
            sea = 9;
        }
        if (sea >= 9 || (sea > 7 && sea < 9)) {
            sea--;
        }
        if (land) {
            sea = 10;
        }
        log(sea); // your answer? => 10
        // done jflute 1on1にて、ソースコードリーディング話のフォローする予定 (2025/07/01)
        // (↑これは、くぼ自身のtodoなので、そのまま残しておいてください)
        // [1on1でのフォロー] 目的ベースのソースコードリーディング (その中で逆読みすることもある)
    }

    // ===================================================================================
    //                                                                       for Statement
    //                                                                       =============
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_inti_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (int i = 0; i < stageList.size(); i++) {
            String stage = stageList.get(i);
            if (i == 1) {
                sea = stage;
            }
        }
        log(sea); // your answer? => dockside
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            sea = stage;
        }
        log(sea); // your answer? => magiclamp
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_continueBreak() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // your answer? => hangar
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_listforeach_basic() {
        List<String> stageList = prepareStageList();
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> {
            if (sb.length() > 0) {
                return;
            }
            if (stage.contains("i")) {
                sb.append(stage);
            }
        });
        String sea = sb.toString();
        log(sea); // your answer? => dockside
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Make list containing "a" from list of prepareStageList() and show it as log by loop. (without Stream API) <br>
     * (prepareStageList()のリストから "a" が含まれているものだけのリストを作成して、それをループで回してログに表示しましょう。(Stream APIなしで))
     */
    public void test_iffor_making() {
        List<String> stageList = prepareStageList();
        List<String> showList = new ArrayList<>();
        for (String stage : stageList){
            if (stage.contains("a")){
                showList.add(stage);
            }
        }
        for (String show : showList){
            log(show);
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Change foreach statement to List's forEach() (keep result after fix) <br>
     * (foreach文をforEach()メソッドへの置き換えてみましょう (修正前と修正後で実行結果が同じになるように))
     */
//    private Boolean loopFlag = true;
//    private String land = null;

    public void test_iffor_refactor_foreach_to_forEach() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea);
        // done hase stageList の内容が変わったとしても、結果が変わらないようにしたいですね by jflute (2025/07/01)
        // 例えば、stageList に hangar が無くなった場合、同じ結果になるか？
        // stageList に bongar という stage が新しく追加されて場合、同じ結果になるか？
        // 難しいですが、もう少し考えてチャレンジしてみてください。
        // done hase [いいね] おおぉぉ実現できてますね、すごい。インスタンス変数を使ってほぼ同じ構造を作ったという感じで by jflute (2025/07/01)
        // done hase 修行++: ただ、とあるメソッド内の一部のロジックでインスタンス変数の手を借りるのはちょっと大げさになりますので... by jflute (2025/07/01)
        // ここはチャレンジ案件という感じで、インスタンス変数を使わない方法も考えてみてください。
        // ヒント出しておきます。Immutableなクラスがあるということは、Mutableなクラスもあるということで。
        // step1でもMutableなクラス登場しましたね。
        StringBuilder land = new StringBuilder();
        if (stageList.size() == 0) {
            String landNull = null;
            log(landNull);
            return;
        }
        stageList.forEach(stage -> {
            if (stage.startsWith("br")) {
                return;
            }
            if (land.toString().contains("ga")) {
                return;
            }
            land.delete(0, land.length());
            land.append(stage);
        });
        log(land.toString());
        // 新しいオブジェクトを作らずに、直接中身を変えるからミュータブルな変数は扱えるううう！
        // Stringは変数の再代入をしなければいけないから危険なんですね
        // done hase [いいね] ちょっとトリッキーですけどね。今回はプログラミングパズルということで。 by jflute (2025/07/01)
        // land変数自体はローカル変数ですが、その変数が参照しているインスタンス(StringBuilder)自体は自由なもので、
        // 代入次第ではメソッドが終わった後も生き続けるインスタンスになり得るので、
        // (Javaさんからすると)Lambdaの中で書き換えられても別に良いだろうということです。
        // というか、実際にLambda式で参照していますから、仮にそのLambda式がメソッド終了後も生き続けても、
        // land変数は消えてなくなりますが、そのLambda式がStringBuilderインスタンスを参照し続けて生かすことになりますね。
        // gaのbreakの代わりにreturnで空回しするってのもよく思いつきましたGood。
        // (hase) ありがとうございます！mutableな変数は自由だから、lambda式の中で生き続けても問題ないのですね。
        // この問題を通じてミュータブル、イミュータブルについてjavaの扱いが少し分かってきた気がします、ありがとうございます！
        // 見返すと、リストが空のときnullが返っていなかったので加筆しましたm

//        stageList.forEach(stage -> {
//            // done hase Lambda式の中は単なるメソッドと捉えて良いので、returnで1回の呼び出しは終了できます by jflute (2025/07/01)
//            // (複数のループの中の1ループの処理をreturnするだけなので、全体のループが終わるわけではないですが)
//            // それを使えば、空ifのelse if技を使わないで済みます。(空ifはあまり一般的には見ないので避けたいところで)
//            if (stage.startsWith("br")) { // do nothing
//            } else if (loopFlag) { // until break
//                land = stage;
//                if (stage.contains("ga")) { // break no mane
//                    loopFlag = false;
//                }
//            }
//        });
//        log(land);

        // (hase)学び：forEachメソッドは、ループ処理じゃないからbreak, continueの命令がない。
        // (hase)voidだからreturnでbreakの真似ができない。
        // done jflute なぜメソッド内で定義したseaが使えず、外で定義したインスタンス変数landだけ使えるのかわかりませんでした。
        //  仕様上、finalじゃないとラムダ式の中で参照できないから仕方ない、という認識で良いでしょうか？by hase (2025/07/01)
        // done hase [へんじ] まあ仕様上という意味ではそう決められてるからとしかいいようがないですが... by jflute (2025/07/01)
        // なぜ、Javaはラムダ式の中でローカル変数を書き換えられないようにしたのか？って設計思想で言うと...
        //
        // Lambda式ってのは本質的には単なるオブジェクトなので、何かのクラスをnewした普通のインスタンスなんですね。
        // なので、forEachの中でどのように扱われるか？ってのはわからないというか保証がないわけです。
        // もしかしたら、stageListのインスタンス変数で保持されて、しばらくインスタンスが生き続けるかもしれない...
        //
        // 極端な話、このtestメソッドが終わった後も生き続けてるかもしれなくて、そのときcallされたら...
        // すでに終わってるメソッドのローカル変数を書き換えるみたいな処理がLambdaの中に残ってるという状態になって、
        // 論理的な矛盾が発生するわけです。
        //
        // forEach()の中で別スレッドで実行されるかもしれない。
        // となると、本来メソッドの1呼び出しで閉じてるはずのローカル変数が複数スレッドで書き換えられることもあるかもしれない。
        // そんなことしないでしょってところなんですが、そういうのを文法上は可能にしてしまうと地球レベルで考えると、
        // 変な実装で複雑なことが起きるかもしれない。何百万のユーザーを想定するJavaからすればそれは制限しておきたいと。
        //
        // って、究極Java設計した人じゃないとわからないことですが、このような話を昔に聞いたことがあり、
        // まあ論理的にも確かにそうだなとは解釈しています。Javaはわりと安全寄りの言語ということもあり。

        // (hase) 壮大な背景を教えていただき、ありがとうございます！！
        // まだ完全には理解できていませんが、開発者のリスクマネジメントで再代入を危惧したのだとイメージすることができました。
        
        // [1on1でのフォロー] for文とforEach()のメリデメ
        // 「新しいから使うではなく、向いているから使う」の感覚を持って欲しい。
    }

    /**
     * Make your original exercise as question style about if-for statement. <br>
     * (if文for文についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * 何が出力されるでしょうか？
     * 
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_iffor_yourExercise() {
        List<String> stageList = prepareStageList();

        // done hase [いいね] 良いエクササイズですね、ちゃんと読まないとできない^^ by jflute (2025/07/01)
        // (hase)ありがとうございます！コメントいただけると自由演技のセクションもやる気が出ます。
        stageList.forEach(stage -> {
            int len = stage.length();
            List<String> showList = new ArrayList<>();
            if (len >= 8){
                showList.add(stage);
            }
            showList.forEach(show -> {
                log(show);
            });
        });
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private List<String> prepareStageList() {
        List<String> stageList = new ArrayList<>();
        stageList.add("broadway");
        stageList.add("dockside");
        stageList.add("braganza");
        stageList.add("hangar");
        stageList.add("magiclamp");
        stageList.add("bongar");
        return stageList;
    }
}
