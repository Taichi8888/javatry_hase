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

import java.io.IOException;

import org.docksidestage.bizfw.basic.objanimal.Dog;
import org.docksidestage.bizfw.basic.objanimal.Zombie;
import org.docksidestage.bizfw.basic.supercar.SupercarClient;
import org.docksidestage.javatry.basic.st7.St7BasicExceptionThrower;
import org.docksidestage.javatry.basic.st7.St7ConstructorChallengeException;
import org.docksidestage.unit.PlainTestCase;

// TODO jflute 1on1にて、全体的にエラーハンドリングのフォローする (2025/07/09)
// done hase javadocのauthorお願いします！ by jflute (2025/07/09)
/**
 * The test of variable. <br>
 * Operate as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りに実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author tahasega
 */
public class Step07ExceptionTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_exception_basic_catchfinally() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        StringBuilder sea = new StringBuilder();
        try {
            thrower.land();
            sea.append("dockside");
        } catch (IllegalStateException e) {
            sea.append("hangar");
        } finally {
            sea.append("broadway");
        }
        log(sea); // your answer? => hangarbroadway
    } // finallyはcatchしてもしなくても実行される。エラーが発生しなかったらdocksidebroadwayになる。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_basic_message() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        String sea = null;
        try {
            thrower.land();
            fail("no way here");
        } catch (IllegalStateException e) {
            sea = e.getMessage();
        }
        log(sea); // your answer? => oneman at showbase
    } // Throwable(String message)のコンストラクタでメッセージを設定している。

    /**
     * What class name and method name and row number cause the exception? (you can execute and watch logs) <br>
     * (例外が発生したクラス名とメソッド名と行番号は？(実行してログを見て良い))
     */
    public void test_exception_basic_stacktrace() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        try {
            thrower.land();
            fail("no way here");
        } catch (IllegalStateException e) {
            log(e);
        }
        // your answer? => St7BasicExceptionThrowerクラス、onemanメソッド、40行目
    }

    // ===================================================================================
    //                                                                           Hierarchy
    //                                                                           =========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_exception_hierarchy_Runtime_instanceof_RuntimeException() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof RuntimeException;
        log(sea); // your answer? => true
    } // IllegalStateException extends RuntimeException

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Exception() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Exception;
        log(sea); // your answer? => true
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Error() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Error;
        log(sea); // your answer? => false
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Throwable() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Throwable;
        log(sea); // your answer? => true
    } // instanceofは全てのスーパークラスを確認する。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Throwable_instanceof_Exception() {
        Object exp = new Throwable("mystic");
        boolean sea = exp instanceof Exception;
        log(sea); // your answer? => false
    } // instanceofはメソッドではなくキーワード（if, for, class, tryなど）
    // だからoが大文字にならない（キャメルケースというらしい）

    // ===================================================================================
    //                                                                         NullPointer
    //                                                                         ===========
    // TODO jflute 1on1にてnullPoの話 (2025/08/06)
    /**
     * What variable (is null) causes the NullPointerException? And what row number? (you can execute and watch logs) <br>
     * (NullPointerが発生する変数(nullだった変数)と、発生する行番号は？(実行してログを見ても良い))
     */
    public void test_exception_nullpointer_basic() {
        try {
            String sea = "mystic";
            String land = sea.equals("mystic") ? null : "oneman";
            String lowerCase = land.toLowerCase();
            log(lowerCase);
        } catch (NullPointerException e) {
            log(e);
        }
        // your answer? => land.toLowerCase()でnullに対して変更を加えようとしているから。c言語と同じ？
    } // sea.equals("mystic") ? null : "oneman"; 三項演算子

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_nullpointer_headache() {
        try {
            String sea = "mystic";
            String land = !!!sea.equals("mystic") ? null : "oneman";
            String piari = !!!sea.equals("mystic") ? "plaza" : null;
            int sum = land.length() + piari.length();
            log(sum);
        } catch (NullPointerException e) {
            log(e);
        }
        // your answer? => NullPointerExceptionの中身
    }

    /**
     * Refactor to immediately understand what variable (is null) causes the NullPointerException by row number in stack trace. <br>
     * (どの変数がNullPointerを引き起こしたのか(nullだったのか)、スタックトレースの行番号だけでわかるようにリファクタリングしましょう)
     */
    public void test_exception_nullpointer_refactorCode() {
        try {
            String sea = "mystic";
            String land = !!!sea.equals("mystic") ? null : "oneman";
            String piari = !!!sea.equals("mystic") ? "plaza" : null;
            int sum = land.length();
            sum += piari.length();
            log(sum);
        } catch (NullPointerException e) {
            log(e);
        }
    } // (Step07ExceptionTest.java:166)

    // ===================================================================================
    //                                                                   Checked Exception
    //                                                                   =================
    /**
     * Show canonical path of new java.io.File(".") by log(), and if I/O error, show message and stack-trace instead <br>
     * (new java.io.File(".") の canonical path を取得してログに表示、I/Oエラーの時はメッセージとスタックトレースを代わりに表示)
     */
    public void test_exception_checkedException_basic() {
        try {
            String path = new java.io.File("").getCanonicalPath();
            log(path);
            throw new IOException("Invalid file path");
            // done hase 実際に↓のように一時的にthrowさせてStackTraceがちゃんと出ているか確認してみてください by jflute (2025/07/09)
            // 出てるは出ていますが、ちょっと出方が見やすくないですね。
            //throw new IOException();
        } catch (IOException e) {
            log(e.getMessage());
            for (StackTraceElement element : e.getStackTrace()) {
                log("  at " + element.toString());
            }
            // done hase [いいね] おおぉ、すごい。よく頑張りましたね。 by jflute (2025/07/15)
            // スタックトレースというデータの本質的なところがよく理解できたんじゃないかなと。
            // ちなみに、log(e); とするだけで、全部出てくるようになってます(^^。
            log(e); // 本当だ！！by hase (2025/07/15)
        }
    } // ファイル操作、データベース接続、ネットワーク通信などでは、コンパイル時に強制的に例外処理を要求される。（チェック例外）
    // ファイルが存在しない、接続できない、などの事前に想定できる例外状況が多いので、チェック例外が使われる。
    // RuntimeExceptionは「プログラム自体のバグ」で発生することが多い。毎回try-catch処理すると煩雑になるため、チェック例外から除外。
    
    // #1on1: チェック例外が流行ってない話 (2025/08/06)
    // getCanonicalPath()で例外が発生する確率は？したとしてリカバリできるか？ (今の時代)
    // UncheckedIOExceptionの話。

    // ===================================================================================
    //                                                                               Cause
    //                                                                               =====
    // TODO jflute 1on1にてネスト例外の話 (2025/08/06)
    /**
     * What string is sea variable in the catch block?
     * And What is exception class name displayed at the last "Caused By:" of stack trace? <br>
     * (catchブロックの変数 sea, land の中身は？また、スタックトレースの最後の "Caused By:" で表示されている例外クラス名は？)
     */
    public void test_exception_cause_basic() {
        String sea = "mystic";
        String land = "oneman";
        try {
            throwCauseFirstLevel();
            fail("always exception but none");
        } catch (IllegalStateException e) {
            Throwable cause = e.getCause();
            sea = cause.getMessage();
            land = cause.getClass().getSimpleName();
            log(sea); // your answer? => Failed to call the third help method: symbol=-1
            log(land); // your answer? => IllegalArgumentException
            log(e); // your answer? => Failed to call the second help method: symbol=1
        }
    } // Caused by: java.lang.NumberFormatException: For input string: "piari"
    // エラー追うの大変すぎる......
    // Caused byを追うと、例外が発生した原因を特定できる。
    // 例外の原因を追うために、例外のメッセージに情報を含めることが重要。

    private void throwCauseFirstLevel() {
        int symbol = Integer.MAX_VALUE - 0x7ffffffe; // 1
        try {
            throwCauseSecondLevel(symbol);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Failed to call the second help method: symbol=" + symbol, e);
        }
    }

    private void throwCauseSecondLevel(int symbol) {
        try {
            --symbol; // 0
            symbol--; // -1
            if (symbol < 0) {
                throwCauseThirdLevel(symbol);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to call the third help method: symbol=" + symbol, e);
        }
    }

    private void throwCauseThirdLevel(int symbol) {
        if (symbol < 0) {
            Integer.valueOf("piari"); // NumberFormatException
        }
    }

    // ===================================================================================
    //                                                                         Translation
    //                                                                         ===========
    /**
     * Execute this test and read exception message and write situation and cause on comment for non-programmer. <br>
     * テストを実行して例外メッセージを読んで、プログラマーでない人にもわかるように状況と原因をコメント上に書きましょう。
     */
    public void test_exception_translation_debugChallenge() {
        try {
            new SupercarClient().buySupercar();
            fail("always exception but none");
        } catch (RuntimeException e) {
            log("*No hint here for training.", e);
            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // What happens? Write situation and cause here. (何が起きた？状況と原因をここに書いてみましょう)
            // - - - - - - - - - -
            // 【ログ】The kawaii face is already unsupported so we cannot make it.
            // 【結論】スーパーカーを買おうとしたが、所望のステアリングホイールを作るためのkawaii faceネジがサ終していて、注文できなかった。
            // 【具体】
            //  スーパーカーを買いにディーラーに来た。
            //  seaみたいなステアリングホイールが良いと言った。
            //  ディーラーは、要望を聞いてpiariというカタログキーのスーパーカーを注文した。
            //  スーパーカー作る人は、注文されたスーパーカーを作るために、ステアリングホイールをタイヤ作る人に依頼した。
            //  タイヤ作る人は、注文されたステアリングホイールを作るために、特別なネジをネジ作る人に依頼した。
            //  ネジ作る人は、注文されたのがkawaii faceネジだったので、作ることができなかった。
            // _/_/_/_/_/_/_/_/_/_/
        }
    }
    // TODO jflute 1on1でフォロー予定 (2025/07/15)

    /**
     * Improve exception handling in supercar's classes to understand the situation
     * by only exception information as far as possible. <br>
     * できるだけ例外情報だけでその状況が理解できるように、Supercarのクラスたちの例外ハンドリングを改善しましょう。
     */
    public void test_exception_translation_improveChallenge() {
        try {
            new SupercarClient().buySupercar(); // you can fix the classes
            fail("always exception but none");
        } catch (RuntimeException e) {
            log("*No hint here for training.", e);
        }
    }
    // 考えコメント by hase (2025/07/10)
    // 例外情報だけで状況が理解できるようにするために、買えなかった経緯を一つずつ遡るのがいいと思いました。
    // その都度、別の種類の例外を投げてキャッチする方が汎用性が高いと思い、たくさんのエラーを作りました。（例えば、kawaii faceを使うDoorが出現した時など）
    // 2025/07/10ここまで by hase
    // Caused byの情報で追うことを忘れていました、、、by hase (2025/07/11)
    // 取り急ぎ、修正したのでお願いします m

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Fix terrible (YABAI in Japanese) exception handling. (you can modify exception class) <br>
     * (やばい例外ハンドリングがあるので修正しましょう (例外クラスを修正してOK))
     */
    public void test_exception_writing_constructorChallenge() {
        try {
            helpSurprisedYabaiCatch();
        } catch (St7ConstructorChallengeException e) {
            log("Thrown by help method", e); // should show also "Caused-by" information
        }
    }

    private void helpSurprisedYabaiCatch() {
        try {
            helpThrowIllegalState();
        } catch (IllegalStateException e) {
            // done hase せっかくなので、Exception側にもauthorを追加しましょう〜 by jflute (2025/07/15)
            throw new St7ConstructorChallengeException("Failed to save dog.", e);
        }
    }

    private void helpThrowIllegalState() {
        Zombie zombie = new Zombie();
        Dog dog = new Dog();
        for (int i = 0; i < 3; i++) {
            zombie.fight(dog);
        }
        if (dog.getHitPoint() < 5) { // simulate something illegal
            String law = "生類憐れみの令"; // important to debug
            throw new IllegalStateException("something illegal: law = " + law);
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What is the concept difference between Exception and Error? Write it on comment. <br>
     * (ExceptionとErrorのコンセプトの違いはなんでしょうか？コメント上に書きましょう)
     */
    public void test_exception_zone_differenceExceptionError() {
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // Write here. (ここに書いてみましょう)
        // - - - - - - - - - -
        // Exceptionは事前に想定して対応するもの、Errorは事前に想定できなくて後々対応するもの、のイメージです。by hase (2025/07/11)
        // Exceptionはプログラム内で発生して、プログラム内で処理できるもの。by hase (2025/07/15)
        // ErrorはJVMやシステムなど、プログラム外で発生して、プログラム内で回復できないもの。
        // 事前に想定できるIOExceptionなどは事前に想定してcatchすべきで、RuntimeExceptionは想定できないものも多くcatchしなくても良い。
        // 調べてこのような理解になりました。
        // _/_/_/_/_/_/_/_/_/_/
        // done hase ↑論理的におおよそそんな感じではありますが、実際は... by jflute (2025/07/15)
        // Exception, 半分はそうなんですけど半分は事前に想定できないものもあって、それだけの違いではないという感じではありますね。
        // ただ、チェック例外は確実に「事前に想定」と言えます。RuntimeExceptionは自由度が高くて...
        // done jflute 1on1にてフォロー予定 (2025/07/15)
        // #1on1: チェック例外とRuntime例外の世の中の実質の話。
        // RuntimeExceptionの中で事前想定のものとそうじゃないもの区分けしてる。
        //
        // #1on1: 「プログラム外で発生して」→「発生自体はプログラム内だけど、原因がプログラム外にあることが多い」
        //
        // Errorは発生したらどうにも(その場では自動的に)リカバリできない。
        // → 発生した瞬間にもう(システム)エラーであることが確定している
        //
        // Exceptionは例外、ダメなことなのか？
        // → 起きにくいだけであって、正常なこともある!? by hase
        // → 発生した瞬間には、ダメかどうかまだわからない
        // e.g. throw new TicketSoldOutException("Sold out");
        // → throwした瞬間には、まだ(システム)エラーなのかまだわからない
        // SoldOutが普通にありえるレアケースのイベントなのか？そこに来たらシステムダメってことのなのか？
        // 呼び出し側じゃないとわからない、呼び出し側が責任を持っている。
        //
        // TypeScriptの例外の仕組みで使われるErrorオブジェクトの話。
    }
}
