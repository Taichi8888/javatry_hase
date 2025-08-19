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

import org.docksidestage.bizfw.basic.buyticket.*;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth.TicketShortMoneyException;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of class. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう) <br>
 * 
 * If ambiguous requirement exists, you can determine specification that seems appropriate. <br>
 * (要件が曖昧なところがあれば、適切だと思われる仕様を決めても良いです)
 * 
 * @author jflute
 * @author tahasega
 */
public class Step05ClassTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          How to Use
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_class_howToUse_basic() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(7400);
        int sea = booth.getQuantity();
        log(sea); // your answer? => 9
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_overpay() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => 7400
        // log(booth.getQuantity()); => 9：リセットされる
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_noSales() {
        TicketBooth booth = new TicketBooth();
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => 0
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_wrongQuantity() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // your answer? => Failed to buy one-day passport: money=7399
    }

    private Integer doTest_class_ticket_wrongQuantity() {
        TicketBooth booth = new TicketBooth();
        int handedMoney = 7399;
        try {
            booth.buyOneDayPassport(handedMoney);
            fail("always exception but none");
        } catch (TicketShortMoneyException continued) {
            log("Failed to buy one-day passport: money=" + handedMoney, continued);
        }
        return booth.getQuantity();
    }

    // ===================================================================================
    //                                                                           Let's fix
    //                                                                           =========
    /**
     * Fix the problem of ticket quantity reduction when short money. (Don't forget to fix also previous exercise answers) <br>
     * (お金不足でもチケットが減る問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_ticketQuantityReduction() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // should be max quantity, visual check here
    }

    /**
     * Fix the problem of sales proceeds increased by handed money. (Don't forget to fix also previous exercise answers) <br>
     * (受け取ったお金の分だけ売上が増えていく問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_salesProceedsIncrease() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // should be same as one-day price, visual check here
    }

    /*
     * Make method for buying two-day passport (price is 13200). (which can return change as method return value)
     * (TwoDayPassport (金額は13200) も買うメソッドを作りましょう (戻り値でお釣りをちゃんと返すように))
     */
    public void test_class_letsFix_makeMethod_twoDay() {
        // uncomment after making the method
        TicketBooth booth = new TicketBooth();
        int money = 14000;
        TicketBuyResult result = booth.buyTwoDayPassport(money);
        int change = result.getChange();
        Integer sea = booth.getSalesProceeds() + change;
        log(sea); // should be same as money

        // and show two-day passport quantity here
        log("quantity: {}", booth.getQuantity()); // 9
        log("two-day passport price: {}", booth.getSalesProceeds()); // 13200
    }

    /**
     * Recycle duplicate logics between one-day and two-day by e.g. private method in class. (And confirm result of both before and after) <br>
     * (OneDayとTwoDayで冗長なロジックがあったら、クラス内のprivateメソッドなどで再利用しましょう (修正前と修正後の実行結果を確認))
     */
    public void test_class_letsFix_refactor_recycle() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        log(booth.getQuantity(), booth.getSalesProceeds()); // should be same as before-fix
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Now you cannot get ticket if you buy one-day passport, so return Ticket class and do in-park. <br>
     * (OneDayPassportを買ってもチケットをもらえませんでした。戻り値でTicketクラスを戻すようにしてインしましょう)
     */
    public void test_class_moreFix_return_ticket() {
//         uncomment out after modifying the method
        TicketBooth booth = new TicketBooth();
        Ticket oneDayPassport = booth.buyOneDayPassport(10000).getTicket();
        log(oneDayPassport.getTicketPrice()); // should be same as one-day price
        log(oneDayPassport.isAlreadyIn()); // should be false
        oneDayPassport.doInPark();
        log(oneDayPassport.isAlreadyIn()); // should be true
    }

    /**
     * Now also you cannot get ticket if two-day passport, so return class that has ticket and change. <br>
     * (TwoDayPassportもチケットをもらえませんでした。チケットとお釣りを戻すクラスを作って戻すようにしましょう)
     */
    public void test_class_moreFix_return_whole() {
        // uncomment after modifying the method
        TicketBooth booth = new TicketBooth();
        int handedMoney = 20000;
        TicketBuyResult buyResult = booth.buyTwoDayPassport(handedMoney);
        int change = buyResult.getChange();
        log(buyResult.getTicket().getTicketPrice() + change); // should be same as money
    }

    /**
     * Now you can use only one in spite of two-day passport, so fix Ticket to be able to handle plural days. <br>
     * (TwoDayPassportなのに一回しか利用できません。複数日数に対応できるようにTicketを修正しましょう)
     */
    public void test_class_moreFix_usePluralDays() {
        TicketBooth booth = new TicketBooth();
        int handedMoney = 20000;
        Ticket twoDayPassport = booth.buyTwoDayPassport(handedMoney).getTicket();
        log(twoDayPassport.getDaysLeft());
        twoDayPassport.doInPark();
        log(twoDayPassport.isAlreadyIn());
        log(twoDayPassport.getDaysLeft());
        twoDayPassport.doInPark();
        log(twoDayPassport.isAlreadyIn());
        log(twoDayPassport.getDaysLeft());
    }

    /**
     * Accurately determine whether type of bought ticket is two-day passport or not by if-statement. (fix Ticket classes if needed) <br>
     * (買ったチケットの種別がTwoDayPassportなのかどうかをif文で正確に判定してみましょう。(必要ならTicketクラスたちを修正))
     */
    public void test_class_moreFix_whetherTicketType() {
        // uncomment when you implement this exercise
        TicketBooth booth = new TicketBooth();
        Ticket oneDayPassport = booth.buyOneDayPassport(10000).getTicket();
        showTicketIfNeeds(oneDayPassport);
        Ticket twoDayPassport = booth.buyTwoDayPassport(20000).getTicket();
        showTicketIfNeeds(twoDayPassport);
        Ticket twoDayPassport2 = booth.buyTwoDayPassport(30000).getTicket();
        twoDayPassport2.doInPark();
        showTicketIfNeeds(twoDayPassport2); // 1回使っていてもtwo-day passportとして判定できるか
    }

//     uncomment when you implement this exercise
    private void showTicketIfNeeds(Ticket ticket) {
        // done hase まず、金額をハードコードすると、料金改定がされたら破綻します。少なくとも定数を利用しましょう by jflute (2025/07/07)
        // あと、別の日数でたまたま2Dayと同じ料金のチケットが新しく登場したとき破綻します。
        // (hase 25/7/7) 以下のように修正しました。
        // チケットの残り日数を判定するために2という数字を使っていますが、これもマジックナンバーになるのでしょうか...
        // Boothの中で、TWO_DAY_DAYS_LEFTという定数を定義した方が良いですか？
        // done hase まあ、日数に関しては、TWO_DAYという概念から2以外の値になることはないので、ハードコードでも良いと思います by jflute (2025/07/07)
        // マジックナンバーというのは、一つのとある数字に値とは無関係の意味を持たせるもので、例えば「-1なら存在しないを示す」とかそういうのです。
        // done hase DaysLeftはあくまで残数なので、残り1日TwoDayPassportだとヒットしなくなってしまいます by jflute (2025/07/07)
        // そのTicketのチケット種別としての入園可能日数を連れてこないとですね。
        // done hase 一方で、チケットの種別を判定するのに、随分と判定要素が必要になってしまっています。 by jflute (2025/07/07)
        // 単純に実装抜けがあると怖いですし、一方でキリがない問題もあります。価格と日数と夜かどうか？以外の要素が出てきたときに破綻します。
        // 修行++: 純粋に「チケット種別」って概念があって、そのTicketのチケット種別が「TwoDayPassport」って判定できると良いですね。
// おもいで：price, days, onlyNightをticketで管理していた時代
//        if (ticket.getTicketPrice() == TicketBooth.TWO_DAY_PRICE
//                && ticket.getDaysLeft() == 2
//                && !ticket.isOnlyNightAvailable()) { // write determination for two-day passport
        // #1on1 == で判定できている理由
        // そもそも "==" と equals() の違い:
        //  o "==" はインスタンスが同一のもの
        //  o equals() はインスタンスが同一だろうが別だろうが、クラスの内容が同じかどうか？ (業務的なイコール)
        // https://dbflute.seasar.org/ja/manual/topic/programming/java/beginners.html#equalsequal
        // enumに関しては、"==" でも ".equals()" と結果がおなじになる。
        // if (ticket.getTicketType().equals(TicketType.TWO_DAY) {
        if (ticket.getTicketType() == TicketType.TWO_DAY) {
            log("two-day passport");
        } else {
            log("other");
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Fix it to be able to buy four-day passport (price is 22400). <br>
     * (FourDayPassport (金額は22400) のチケットも買えるようにしましょう)
     */
    public void test_class_moreFix_wonder_four() {
        TicketBooth booth = new TicketBooth();
        Ticket fourDayPassport = booth.buyFourDayPassport(25000).getTicket();
        fourDayPassport.doInPark();
        log(fourDayPassport.isAlreadyIn());
        fourDayPassport.doInPark();
        log(fourDayPassport.isAlreadyIn());
        fourDayPassport.doInPark();
        log(fourDayPassport.isAlreadyIn());
        fourDayPassport.doInPark();
        log(fourDayPassport.isAlreadyIn());
    }

    /**
     * Fix it to be able to buy night-only two-day passport (price is 7400), which can be used at only night. <br>
     * (NightOnlyTwoDayPassport (金額は7400) のチケットも買えるようにしましょう。夜しか使えないようにしましょう)
     */
    public void test_class_moreFix_wonder_night() {
        TicketBooth booth = new TicketBooth();
        Ticket ticket = booth.buyTwoNightPassport(10000).getTicket();
        log(ticket.isOnlyNightAvailable());
        try {
            ticket.doInPark(); // should throw exception
        } catch (IllegalStateException e) {
            log("Cannot use day park with night-only ticket: " + e.getMessage());
        }
    }

    /**
     * Refactor if you want to fix (e.g. is it well-balanced name of method and variable?). <br>
     * (その他、気になるところがあったらリファクタリングしてみましょう (例えば、バランスの良いメソッド名や変数名になっていますか？))
     */
    public void test_class_moreFix_yourRefactoring() {
        TicketBooth booth = new TicketBooth();
        Ticket ticket = booth.buyTwoNightPassport(10000).getTicket();
        log(ticket.isOnlyNightAvailable());
        try {
            ticket.doInPark();
        } catch (Exception e) {
            log("Could not use this ticket. " + e.getMessage());
        }
        log(ticket.getDaysLeft()); // 17時前：2, 17時以降：1
        // done hase 修行++: UnitTestで、現在日時を差し替えて両方一気にテストしたい by jflute (2025/07/11)
        // 一方で、一般ユーザーは doInPark() のままで、現在日時を差し替えるってのはできないようにしたい。
        // これは最後でOKです。(hint: step6以降の知識が必要なので、つまりjavatryのbasic範囲内の知識でできる)

        // TestTicketクラスを作成し、テストの時はtestHourにて時刻を（開発者だけが）設定できる仕様にしました。by hase (2025/07/18)
        // 一般ユーザーはTicketクラスのdoInPark()を使うので、入園時刻はコントロールできない。
        // 夜間フラグを操作できるように時刻の引数を受け取る仕様(isNightTime(int time)、doInPark(int time))にすることも考えたが、
        // テスト用に全く別のメソッドを作成するのはおかしいと思い、OverrideできるgetCurrentHour()を作りました。
        // 元のメソッドに変更があった時にも面倒くさいと思い。。
        // done hase (自分用) TestTicketも購入して手に入れられるべき。by hase (2025/07/18)
        // でもわざわざTestTicketBuyResultなどを作るのは面倒、いかにせむ。
        // done hase 修行#: 現状でも十分テストにはなっているけど、確かにBoothからの一気通貫でやりたいですね by jflute (2025/07/22)
        // #1on1: もし、Ticketにもっと購入情報が入るような状態であれば、BoothからのTicketで試したくなるだろう。
        TestTicket testTicket = new TestTicket(TicketType.TWO_NIGHT);
        try {
            testTicket.doInPark();
            log("Today is horror night!");
        } catch (Exception e) {
            log("Could not enter. " + e.getMessage());
        }
        log(testTicket.getDaysLeft());
    }

    /**
     * Write intelligent comments on source code to the main code in buyTicket package. <br>
     * (buyTicketパッケージのクラスに、気の利いたコメントを追加してみましょう)
     */
    public void test_class_moreFix_yourSuperComments() {
        TicketBooth booth = new TicketBooth();
        TicketBuyResult result1 = booth.buyTwoNightPassport(32000);
        log(result1.getChange());
        log(result1.getTicket().isOnlyNightAvailable());
    }

    // done hase ↑のエクササイズが↓に変わりました。ぜひやってみてください by jflute (2025/07/11)
    /**
     * Write intelligent JavaDoc comments seriously on the public classes/constructors/methods of the Ticket class. <br>
     * (Ticketクラスのpublicなクラス/コンストラクター/メソッドに、気の利いたJavaDocコメントを本気で書いてみましょう)
     */
    public void test_class_moreFix_yourSuperJavaDoc() {
        // your confirmation code here
    }

    // TestTicketBoothのテスト
    public void test_testTicketBooth_buyTestTicket() {
        TestTicketBooth testBooth = new TestTicketBooth();
        int handedMoney = 10000;
        TicketBuyResult result = testBooth.doBuyTicket(handedMoney, TicketType.TWO_NIGHT);
        TestTicket testTicket = (TestTicket) result.getTicket();

        // チケットの価格・お釣り・残日数・夜間フラグを確認
        log(testTicket.getTicketPrice()); // 7400
        log(result.getChange()); // 2600
        log(testTicket.getDaysLeft()); // 2
        log(testTicket.isOnlyNightAvailable()); // true

        // 入園処理のテスト
        try {
            testTicket.doInPark();
            log("Today is horror night!");
        } catch (Exception e) {
            log("Could not enter. " + e.getMessage());
        }

        // 売上・残数の確認
        log(testBooth.getSalesProceeds()); // 7400
        log(testBooth.getQuantity()); // 9
    }
}
