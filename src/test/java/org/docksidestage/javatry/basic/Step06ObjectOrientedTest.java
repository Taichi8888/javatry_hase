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

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketType;
import org.docksidestage.bizfw.basic.objanimal.*;
import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.loud.AlarmClock;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;
import org.docksidestage.bizfw.basic.objanimal.runner.FastRunner;
import org.docksidestage.javatry.basic.st6.dbms.AbstractDbms;
import org.docksidestage.javatry.basic.st6.dbms.St6MySql;
import org.docksidestage.javatry.basic.st6.dbms.St6PostgreSql;
import org.docksidestage.javatry.basic.st6.os.Mac;
import org.docksidestage.javatry.basic.st6.os.Windows;
import org.docksidestage.unit.PlainTestCase;

// #1on1 書いては壊して書いては壊して、その過程で色々な発想が舞い込んできて by jflute (2025/07/22)
// https://jflute.hatenadiary.jp/entry/20150727/fivebooks

// done hase 全体的に無言のコメントアウトが多いので... by jflute (2025/07/07)
// まあ実務なら消すと思いますが、javatryは勉強用なのでコメントアウトで途中経過を残すのは良いです。
// ただ、やりかけなのか？勉強用に残してるのか？わからないと読み手が迷いますね。
// 説明を入れるまでもなく残すような場合は、「おもいでコメントアウト」してもらえると嬉しいです。
/* e.g.
    // おもいで
    //String sea = null;
    Integer land = ...
*/
/**
 * The test of object-oriented. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author tahasega
 */
public class Step06ObjectOrientedTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        About Object
    //                                                                        ============
    // -----------------------------------------------------
    //                                        Against Object
    //                                        --------------
    /**
     * Fix several mistakes (except simulation) in buying one-day passport and in-park process. <br>
     * (OneDayPassportを買って InPark する処理の中で、(simulationを除いて)間違いがいくつかあるので修正しましょう)
     */
    public void test_objectOriented_aboutObject_againstObject() {
        // done hase まだ一個だけ間違いがそのまま残っていますね。本当にあと一個だけですね by jflute (2025/07/07)
        // do in park後のalreadyInチェックでしょうか！ by hase (2025/07/08)
        // done hase いや、もっと直接的でベタな間違いです。見つけたら笑っちゃうような by jflute (2025/07/09)

        // [ticket booth info]
        // simulation: actually these variables should be more wide scope
        int oneDayPrice = 7400;
        int quantity = 10;
        int salesProceeds = 0;
        // [buy one-day passport]
        // simulation: actually this money should be from customer
        int handedMoney = 10000;
        if (quantity <= 0) {
            throw new IllegalStateException("Sold out");
        }
        if (handedMoney < oneDayPrice) {
            throw new IllegalStateException("Short money: handedMoney=" + handedMoney);
        }
        --quantity;
        // done [いいね]売上加算気付けてる　by tanaryo
        // done hase ちょっと dead codeの警告が出てしまっています。salesProceeds が初期値固定nullだからですね by jflute (2025/07/07)
        // ここは模擬コードなので仕方ないですが、step5であったように初期値0にしちゃってもいいかなと。
        salesProceeds += oneDayPrice;
        log(salesProceeds);
        // [ticket info]
        // simulation: actually these variables should be more wide scope
        int displayPrice = oneDayPrice; //
        boolean alreadyIn = false;
        // other processes here...
        Ticket oneDayPass = new Ticket(TicketType.ONE_DAY);
        // do in parkの前にチェック by hase (2025/07/08)
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice); // ここかあああ by hase (2025/07/09)
            // done hase [いいね] ここは見逃しランキングNo.1の箇所です。みんな正常の処理はよく見るのですが... by jflute (2025/07/10)
            // 例外メッセージとかはあまり見ないでしょう。でも、本番で本当にその例外が発生する状況になったら...
            // 「あー、出てる値が全然違うからデバッグできーん」になっちゃってピンチになるのです。
            // 確かに...この問題以外にも例外処理を試していない箇所がありました、気をつけます by hase (2025/07/10)
        }
        //do in park here!!!
        oneDayPass.doInPark();
        alreadyIn = true;
        // [final process]
        saveBuyingHistory(quantity, salesProceeds, displayPrice, alreadyIn);
    }

    private void saveBuyingHistory(int quantity, Integer salesProceeds, int displayPrice, boolean alreadyIn) {
        if (alreadyIn) {
            // simulation: only logging here (normally e.g. DB insert)
            showTicketBooth(quantity, salesProceeds);
            showYourTicket(displayPrice, alreadyIn);
        }
    }

    private void showTicketBooth(int quantity, Integer salesProceeds) {
        log("Ticket Booth: quantity={}, salesProceeds={}", quantity, salesProceeds);
    }

    private void showYourTicket(int displayPrice, boolean alreadyIn) {
        log("Ticket: displayPrice={}, alreadyIn={}", displayPrice, alreadyIn);
    }

    // -----------------------------------------------------
    //                                          Using Object
    //                                          ------------
    /**
     * Read (analyze) this code and compare with the previous test method, and think "what is object?". <br>
     * (このコードを読んで(分析して)、一つ前のテストメソッドと比べて、「オブジェクトとは何か？」を考えてみましょう)
     */
    public void test_objectOriented_aboutObject_usingObject() {
        // [ticket booth info]
        TicketBooth booth = new TicketBooth();
        // [buy one-day passport]
        // if step05 has been finished, you can use this code by jflute (2019/06/15)
        Ticket ticket = booth.buyOneDayPassport(10000).getTicket();
        // [do in park now!!!]
        ticket.doInPark();
        // [final process]
        saveBuyingHistory(booth, ticket);
    }

    private void saveBuyingHistory(TicketBooth booth, Ticket ticket) {
        if (ticket.isAlreadyIn()) {
            // only logging here (normally e.g. DB insert)
            doShowTicketBooth(booth);
            doShowYourTicket(ticket);
        }
    }

    private void doShowTicketBooth(TicketBooth booth) {
        log("Ticket Booth: quantity={}, salesProceeds={}", booth.getQuantity(), booth.getSalesProceeds());
    }

    private void doShowYourTicket(Ticket ticket) {
        log("Your Ticket: displayPrice={}, alreadyIn={}", ticket.getTicketPrice(), ticket.isAlreadyIn());
    }

    // write your memo here:
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // what is object?
    // クラスを細分化し、オブジェクトを作成することで、コードに登場する変数の粒度が揃って読みやすくなる。（凝集度）
    // ・・・役割・責務の分離
    // オブジェクトを作成すると、再利用可能なパーツとして何回でも利用することができ、コードが短くなる。（再利用性）
    // ・・・変更への強さ
    // _/_/_/_/_/_/_/_/_/_/
    //「データ（状態）」と「それを操作する手続き（振る舞い）」をひとまとめにし、特定の「責務」を持たせた、自律したプログラム部品
    // _/_/_/_/_/_/_/_/_/_/
    // done jflute 概念的な話なので、1on1のときにフォローします (2025/07/07)
    // [1on1でのフォロー] #1on1
    // 細分化、ひとまとめ、特定の「責務」の粒度は？ (何でひとまとめなのか？基準)
    // TicketBuyResultの例、これは概念でしか表現できないもの。
    // あと、チケット種別っていうのも概念。チケット種別は何を持つ？
    // 業務概念がオブジェクトになる。その概念に紐づく関連するデータがひとまとめになる!? (トップダウン)
    // データから関連性を見つけて、概念を探し出す。(ボトムアップ)
    // それで、ちょうどいいオブジェクトを見出す。
    // みたいな話。
    // #1on1: チケット種別の話から、オブジェクトとは？がとても大事という話。(2025/07/11)

    // ===================================================================================
    //                                                              Polymorphism Beginning
    //                                                              ======================
    /**
     * What string is sea and land variable at the method end? <br>
     * (メソッド終了時の変数 sea, land の中身は？)
     */
    public void test_objectOriented_polymorphism_1st_concreteOnly() {
        Dog dog = new Dog();
        BarkedSound sound = dog.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = dog.getHitPoint();
        log(land); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_2nd_asAbstract() {
        Animal animal = new Dog();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_3rd_fromMethod() {
        Animal animal = createAnyAnimal();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
    }

    private Animal createAnyAnimal() {
        return new Dog();
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_4th_toMethod() {
        Dog dog = new Dog();
        doAnimalSeaLand_for_4th(dog);
    }

    private void doAnimalSeaLand_for_4th(Animal animal) {
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_5th_overrideWithSuper() {
        Animal animal = new Cat();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => nya-
        int land = animal.getHitPoint();
        log(land); // your answer? => 5
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_6th_overriddenWithoutSuper() {
        Creature creature = new Zombie();
        log(creature instanceof UndeadMonster); // true
        int diaryCount = ((Zombie) creature).getUndeadDiary().getBreatheInCount();
        log(diaryCount); // 0
        BarkedSound sound = creature.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => uooo
        int land = creature.getHitPoint();
        log(land); // your answer? => -1
        creature.bark();
        log(creature.getHitPoint()); // your answer? => -1
        diaryCount = ((Zombie) creature).getUndeadDiary().getBreatheInCount();
        log(diaryCount); // 1
    }

    /**
     * What is happy if you can assign Dog or Cat instance to Animal variable? <br>
     * (Animal型の変数に、DogやCatなどのインスタンスを代入できると何が嬉しいのでしょう？)
     */
    public void test_objectOriented_polymorphism_7th_whatishappy() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // what is happy?
        // 変更に強いしなやかなプログラム。柔軟性、拡張性。
        // Animal animal = の右辺を変えるだけで、そのほかは何も変えずとも振る舞いを変えられる。
        // _/_/_/_/_/_/_/_/_/_/
        // done hase [いいね] 良いですね。シンプルに要点を抑えてる感じで by jflute (2025/07/07)
        // done jflute ここも1on1のときに少しだけフォロー。抽象化の話を。 (2025/07/07)
        // [1on1でのフォロー] 日常での抽象化、めちゃ使ってる。それをプログラミングでも使いたいってだけ。
    }
    
    // done jflute 1on1ここまで、次回ここから (2025/07/08)

    // ===================================================================================
    //                                                              Polymorphism Interface
    //                                                              ======================
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_dispatch() {
        Loudable loudable = new Zombie();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => uooo
        String land = ((Zombie) loudable).bark().getBarkWord();
        String sea2 = ((Zombie) loudable).soundLoudly();
//        String sea3 = loudable.bark().getBarkWord(); loudableはbark()メソッドを持っていない
        log(land); // your answer? => uooo
        log(sea2);
    }//インターフェースは継承と違って、hasの関係。クラスに特定の機能を実装させる。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_hierarchy() {
        Loudable loudable = new AlarmClock();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => jiri jiri jiri---
        boolean land = loudable instanceof Animal;
        boolean land2 = loudable instanceof Loudable;
        log(land); // your answer? => false
        log(land2); // your answer? => true
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_partImpl() {
        Animal seaAnimal = new Cat();
        Creature landAnimal = new Zombie();
        boolean sea = seaAnimal instanceof FastRunner;
        log(sea); // your answer? => true
        boolean land = landAnimal instanceof FastRunner;
        log(land); // your answer? => false
    } //instanceofはそのオブジェクトが特定のクラスのインスタンスであるか、または特定のインターフェースを実装しているか。

    /**
     * Make Dog class implement FastRunner interface. (the method implementation is same as Cat class) <br>
     * (DogもFastRunnerインターフェースをimplementsしてみましょう (メソッドの実装はCatと同じで))
     */
    public void test_objectOriented_polymorphism_interface_runnerImpl() {
        Animal pagu = new Dog();
        boolean sea = pagu instanceof Dog;
        boolean land = pagu instanceof FastRunner;
        log(sea); // true
        log(land); // true
    }

    /**
     * What is difference as concept between abstract class and interface? <br>
     * (抽象クラスとインターフェースの概念的な違いはなんでしょう？)
     */
    public void test_objectOriented_polymorphism_interface_whatisdifference() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // what is difference?
        // 両方ともクラスの設計図を作る。
        // 抽象クラスはis-aの関係（親子関係）。
        // インターフェースは継承と違って、can-doの関係。クラスに特定の機能を実装させる。
        // インターフェースは複数実装できるが、抽象クラスは1つしか継承できない。
        // _/_/_/_/_/_/_/_/_/_/
        // done hase [ふぉろー] いいですね。わかりやすくまとまっています。 by jflute (2025/07/07)
        // ただ、ここで書いてるのは、概念的な違い (コンセプトの違い) というよりも機能的な違いになりますかね。
        // done jflute ここも1on1にてフォローする (2025/07/07)
        // #1on1, コンセプトの違いの話。Javaの多重継承の話。
        // #1on1, 操作フォーカス以外のinterfaceの紹介: ColorBox, AbstractColorBox
    }

    // ===================================================================================
    //                                                                 Polymorphism Making
    //                                                                 ===================
    /**
     * Make concrete class of Animal, which is not FastRunner, in "objanimal" package. (implementation is as you like) <br>
     * (FastRunnerではないAnimalクラスのコンクリートクラスをobjanimalパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeConcrete() {
        Elephant zou = new Elephant();
        Zombie zombie = new Zombie();
        log(zou.isBien()); //false
        zou.fight(zombie);
        log(zou.isBien()); // true
        log(zou.getHitPoint()); // 19
        zou.fight(zombie);
        log(zou.getHitPoint()); // 16
        zombie.fight(zou);
        log(zou.getHitPoint()); // 10 鼻炎x2回攻撃
    }

    /**
     * Make interface implemented by part of Animal concrete class in new package under "objanimal" package. (implementation is as you like) <br>
     * (Animalクラスの一部のコンクリートクラスだけがimplementsするインターフェースをobjanimal配下の新しいパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeInterface() {
        Animal zou = new Elephant();
        Zombie zombie = new Zombie();
        zou.fight(zombie);
        log(zou.getHitPoint()); // 9
        zou.fight(zombie);
        log(zou.getHitPoint()); // 6
        ((Elephant)zou).ride();
        log(zou.getHitPoint()); // 5
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Extract St6MySql, St6PostgreSql (basic.st6.dbms)'s process to abstract class (as super class and subclass) <br>
     * (St6MySql, St6PostgreSql (basic.st6.dbms) から抽象クラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_generalization_extractToAbstract() {
        // done hase [いいね] 伝統的なテンプレートメソッドパターン、しっかりできてますね！ by jflute (2025/07/07)
        AbstractDbms mysql = new St6MySql();
        AbstractDbms postgresql = new St6PostgreSql();
        log(mysql.buildPagingQuery(3,5));
        log(postgresql.buildPagingQuery(3,5));
    } // 同じ型宣言、同じメソッドで違う出力得られた。ちょっとお得かも。

    /**
     * Extract St6OperationSystem (basic.st6.os)'s process to concrete classes (as super class and subclass) <br>
     * (St6OperationSystem (basic.st6.os) からコンクリートクラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_specialization_extractToConcrete() {
        Mac mac = new Mac("abc123");
        Windows windows = new Windows("def456");
        log(mac.getFileSeparator());
        log(windows.getUserDirectory());
    } // done hase Mac, Windows, oldWindows以外のOSが親クラスのコンストラクタから作られた時に例外処理することに詰まった。
    // 今のままだと、親クラスのコンストラクタから作られたもの全てが例外処理されてしまうが、親クラスのif文は消したい。
    // 30分詰まったので保留。
    // done [comment] OSが何であるかという情報はどこで担保する？
    // 今だとコンストラクタの引数で指定しているが他にもありそうかも？(灯台下暗し的な)　by tanaryo (2025/7/5)
    // 親クラスを抽象クラスにして、OSごとのコンクリートクラスを作成することで、OSが何か保証できるということですか？
    // done hase [いいね] 出遅れたけど、今のコードの構造はとても自然で良いです(^^。 by jflute (2025/07/07)

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Extract Animal's bark() process as BarkingProcess class to also avoid large abstract class. <br>
     * (抽象クラス肥大化を抑制するためにも、Animalのbark()のプロセス(処理)をBarkingProcessクラスとして切り出しましょう)
     */
    public void test_objectOriented_writing_withDelegation() {
        Animal dog = new Dog();
        log(dog.bark().getBarkWord());
    }

    /**
     * Put barking-related classes, such as BarkingProcess and BarkedSound, into sub-package. <br>
     * (BarkingProcessやBarkedSoundなど、barking関連のクラスをサブパッケージにまとめましょう)
     * <pre>
     * e.g.
     *  objanimal
     *   |-barking
     *   |  |-BarkedSound.java
     *   |  |-BarkingProcess.java
     *   |-loud
     *   |-runner
     *   |-Animal.java
     *   |-Cat.java
     *   |-Dog.java
     *   |-...
     * </pre>
     */
    public void test_objectOriented_writing_withPackageRefactoring() {
        Animal dog = new Dog();
        log(dog.bark().getBarkWord());
    }
    // #1on1: フラットな階層とパッケージ分けのお話、そして現場のジレンマ
    // #1on1: 枠組みを最初から作っておくか？話 (AnimalBarkingProcessにつながる話)

    /**
     * Is Zombie correct as subclass of Animal? Analyze it in thirty seconds. (thinking only) <br>
     * (ゾンビは動物クラスのサブクラスとして適切でしょうか？30秒だけ考えてみましょう (考えるだけでOK))
     */
    public void test_objectOriented_zoo() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // is it correct?
        // オーバーライドしすぎていてあまり継承の意味があまりない気がする。
        // メソッドの名前自体は同じなので、元animalだったのを伝える目的でサブクラス化するは有効かも。
        // _/_/_/_/_/_/_/_/_/_/
        // done hase [ふぉろー] is-aの関係が成り立つかどうか？ってところですね by jflute (2025/07/07)
        // was-aですね...もはやAnimalではないので適切でないと思いました by hase (2025/07/08)
        // done jflute 1on1にて補足する予定。バイオハザードの話をする (2025/07/07)
        // バイオの世界で考えると、ゾンビはanimalじゃなくて、animalの状態と言えるかも？
        
        // #1on1: ソースコードを追う指スキルがあるかどうか？でわかりやすさ変わってくる
        // #1on1: 的を得たオブジェクトになってないのに階層構造を作ってもよけいわかりづらい
        // しっかり階層化するのであれば、やり切ることが大切
        // #1on1: 整理整頓のお話: プログラミングから整理整頓を学ぶ
        // #1on1: 抽象度のお話。抽象度のコントロール、的を得た抽象度を見つけることが大切
    }
}
