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
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.docksidestage.javatry.basic.st8.St8DbFacade;
import org.docksidestage.javatry.basic.st8.St8Member;
import org.docksidestage.javatry.basic.st8.St8Withdrawal;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Java8 functions. <br>
 * Operate as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りに実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step08Java8FunctionTest extends PlainTestCase {

    // ===================================================================================
    //                                                                              Lambda
    //                                                                              ======
    // -----------------------------------------------------
    //                                              Callback
    //                                              --------
    /**
     * Are all the strings by log() methods in callback processes same? (yes or no) <br>
     * (コールバック処理の中で出力しているログの文字列はすべて同じでしょうか？ (yes or no))
     */
    public void test_java8_lambda_callback_basic() {
        String title = "over";

        // #1on1: step2に引き続き、コールバックここでも理解を深める (2025/09/02)
        log("...Executing named class callback(!?)");
        helpCallbackConsumer(new St8BasicConsumer(title)); // broadway\n dockside: over\n hangar

        log("...Executing anonymous class callback");
        helpCallbackConsumer(new Consumer<String>() {
            public void accept(String stage) {
                log(stage + ": " + title);
            }
        }); // 匿名クラスのコールバック：名前のないクラスをその場で作り、accept()メソッドを実装している。by hase (2025/07/11)
        // helpCallbackConsumer(Consumer<String> oneArgLambda)なので、
        // 名前のないクラスのオブジェクトがoneArgLambdaに代入されて、accept()メソッドが呼ばれる。

        log("...Executing lambda block style callback");
        helpCallbackConsumer(stage -> {
            log(stage + ": " + title);
        }); // lambdaブロックスタイルのコールバック：引数を受け取るstageにdocksideが代入される。by hase (2025/07/11)

        log("...Executing lambda expression style callback");
        helpCallbackConsumer(stage -> log(stage + ": " + title));
        // lambda式のコールバック：引数を受け取るstageにdocksideが代入される。by hase (2025/07/11)
        // 関数型インタフェースをインスタンス化するときにめんどい記述しなくても済むような記法、らしい

        // your answer? => yes

        // cannot reassign because it is used at callback process
        // title = "wave"; 「ローカル変数は、finalまたは事実上のfinalである必要があります」：コールバック内で使われているため by hase (2025/07/11)
    }
// hase勉強用 (2025/07/11)
// 関数型インターフェース：メソッドが1つだけのインターフェース
    // 例：Consumer<T>, Supplier<T>, Function<T, R>
// ラムダ式の記法：(引数) -> { 処理 }
    // 例：stage -> log(stage + ": " + title);
// 関数型インターフェースの型の引数や変数に、ラムダ式を代入できる
    // 例：helpCallbackConsumer(stage -> log(stage + ": " + title));

    /**
     * What is order of strings by log(). (write answer as comma-separated) <br>
     * (ログに出力される文字列の順番は？ (カンマ区切りで書き出しましょう))
     */
    public void test_java8_lambda_callback_order() {
        log("harbor");
        helpCallbackConsumer(stage -> {
            log(stage);
        });
        log("lost river");
        // your answer? => harbor, broadway, dockside, hangar, lost river
    }

    private class St8BasicConsumer implements Consumer<String> {

        private final String title;

        public St8BasicConsumer(String title) {
            this.title = title;
        }

        @Override
        public void accept(String stage) {
            log(stage + ": " + title);
        }
    }

    private void helpCallbackConsumer(Consumer<String> oneArgLambda) {
        log("broadway");
        oneArgLambda.accept("dockside");
        log("hangar");
    }

    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_java8_lambda_callback_valueRoad() {
        String label = "number";
        String sea = helpCallbackFunction(number -> {
            return label + ": " + number;
        });
        log(sea); // your answer? => number: 7
    } // Function<Integer, String> oneArgLambda：Integer型の引数を一つ受け取り、Stringを返す関数型インターフェース。by hase (2025/07/11)
    // 拡張版itoaってこと？？

    private String helpCallbackFunction(Function<Integer, String> oneArgLambda) {
        return oneArgLambda.apply(7);
    }

    // -----------------------------------------------------
    //                                         Convert Style
    //                                         -------------
    /**
     * Change callback style like this:
     * <pre>
     * o sea: to lambda block style
     * o land: to lambda expression style
     * o piari: to lambda block style
     * </pre>
     * (このようにコールバックスタイルを変えてみましょう:)
     * <pre>
     * o sea: BlockのLambda式に
     * o land: ExpressionのLambda式に
     * o piari: BlockのLambda式に
     * </pre>
     */
    public void test_java8_lambda_convertStyle_basic() {
        helpCallbackSupplier(new Supplier<String>() { // sea
            public String get() {
                return "broadway";
            }
        });

        // ↑をBlockのlambda式に
        helpCallbackSupplier(() -> {
            return "broadway";
        }); // get()メソッドは引数がないので() ->と書くらしい by hase (2025/07/11)

        helpCallbackSupplier(() -> { // land
            return "dockside";
        });

        // ↑をExpressionのlambda式に
        helpCallbackSupplier(() -> "dockside");

        helpCallbackSupplier(() -> "hangar"); // piari

        // ↑をBlockのlambda式に
        helpCallbackSupplier(() -> {
            return "hangar";
        });
    }

    private void helpCallbackSupplier(Supplier<String> oneArgLambda) {
        String supplied = oneArgLambda.get();
        log(supplied);
    }

    // ===================================================================================
    //                                                                            Optional
    //                                                                            ========
    /**
     * Are the strings by two log() methods same? (yes or no) <br>
     * (二つのlog()によって出力される文字列は同じでしょうか？ (yes or no))
     */
    public void test_java8_optional_concept() {
        St8Member oldmember = new St8DbFacade().oldselectMember(1);
        if (oldmember != null) {
            log(oldmember.getMemberId(), oldmember.getMemberName()); // 1, broadway
        }
        Optional<St8Member> optMember = new St8DbFacade().selectMember(1);
            // ofNullable() によって、nullかもしれない値をOptionalに変換している（値が無ければ空のOptionalオブジェクトが入る）by hase (2025/07/11)
        if (optMember.isPresent()) { // ↑によって安全に使える
            St8Member member = optMember.get();
            log(member.getMemberId(), member.getMemberName()); // 1, broadway
        }
        // your answer? => yes
    }

    /**
     * Are the strings by two log() methods same? (yes or no) <br>
     * (二つのlog()によって出力される文字列は同じでしょうか？ (yes or no))
     */
    public void test_java8_optional_ifPresent() {
        Optional<St8Member> optMember = new St8DbFacade().selectMember(1);
        if (optMember.isPresent()) {
            St8Member member = optMember.get();
            log(member.getMemberId(), member.getMemberName()); // 1, broadway
        }
        optMember.ifPresent(member -> {
            // ↑selectMember()の戻り値はnullじゃないと保証されている()から、ifPresent()によるnullチェックは不要ではないか？ by hase (2025/07/11)
            log(member.getMemberId(), member.getMemberName()); // 1, broadway
        });
        // your answer? => yes
    }

    /**
     * What string is sea, land, piari, bonvo, dstore, amba variables at the method end? <br>
     * (メソッド終了時の変数 sea, land, piari, bonvo, dstore, amba の中身は？)
     */
    public void test_java8_optional_map_flatMap() {
        St8DbFacade facade = new St8DbFacade();

        // traditional style
        St8Member oldmemberFirst = facade.oldselectMember(1);
        String sea;
        if (oldmemberFirst != null) {
            St8Withdrawal withdrawal = oldmemberFirst.oldgetWithdrawal();
            if (withdrawal != null) {
                sea = withdrawal.oldgetPrimaryReason(); // music
                if (sea == null) {
                    sea = "*no reason1: the PrimaryReason was null";
                }
            } else {
                sea = "*no reason2: the Withdrawal was null";
            }
        } else {
            sea = "*no reason3: the selected Member was null";
        }

        // TODO jflute そのうち1on1にて、DBFluteハンズオンsection2のとき照らし合わせて説明 (2025/10/01)
        Optional<St8Member> optMemberFirst = facade.selectMember(1);

        // map style
        String land = optMemberFirst.map(mb -> mb.oldgetWithdrawal())
                .map(wdl -> wdl.oldgetPrimaryReason())
                .orElse("*no reason: someone was not present");

        // flatMap style
        String piari = optMemberFirst.flatMap(mb -> mb.getWithdrawal())
                .flatMap(wdl -> wdl.getPrimaryReason())
                .orElse("*no reason: someone was not present");

        // flatMap and map style
        String bonvo = optMemberFirst.flatMap(mb -> mb.getWithdrawal())
                .map(wdl -> wdl.oldgetPrimaryReason())
                .orElse("*no reason: someone was not present");

        String dstore = facade.selectMember(2)
                .flatMap(mb -> mb.getWithdrawal())
                .map(wdl -> wdl.oldgetPrimaryReason())
                .orElse("*no reason: someone was not present");

        String amba = facade.selectMember(3)
                .flatMap(mb -> mb.getWithdrawal())
                .flatMap(wdl -> wdl.getPrimaryReason())
                .orElse("*no reason: someone was not present");

        int defaultWithdrawalId = -1;
        Integer miraco = facade.selectMember(2)
                .flatMap(mb -> mb.getWithdrawal())
                .map(wdl -> wdl.getWithdrawalId()) // ID here
                .orElse(defaultWithdrawalId);

        log(sea); // your answer? => music
        log(land); // your answer? => music
        log(piari); // your answer? => music
        log(bonvo); // your answer? => music
        log(dstore); // your answer? => "*no reason: someone was not present"
        log(amba); // your answer? => () ←wrong "*no reason: someone was not present"
        log(miraco); // your answer? => 12
    }

    /**
     * What string is sea variables at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_java8_optional_orElseThrow() {
        Optional<St8Member> optMember = new St8DbFacade().selectMember(2);
        St8Member member = optMember.orElseThrow(() -> new IllegalStateException("over"));
        String sea = "the";
        try {
            String reason = member.getWithdrawal().map(wdl -> wdl.oldgetPrimaryReason()).orElseThrow(() -> {
                return new IllegalStateException("wave");
            });
            sea = reason;
        } catch (IllegalStateException e) {
            sea = e.getMessage();
        }
        log(sea); // your answer? => wave
    }

    // ===================================================================================
    //                                                                          Stream API
    //                                                                          ==========
    /**
     * What string is sea, land variables at the method end? <br>
     * (メソッド終了時の変数 sea, land の中身は？)
     */
    public void test_java8_stream_concept() {
        // TODO jflute そのうち1on1にて、DBFluteハンズオンsection3?4?のときに補足 (2025/10/01)
        List<St8Member> memberList = new St8DbFacade().selectMemberListAll();
        List<String> oldfilteredNameList = new ArrayList<>();
        for (St8Member member : memberList) {
            if (member.getWithdrawal().isPresent()) {
                oldfilteredNameList.add(member.getMemberName());
            }
        }
        String sea = oldfilteredNameList.toString();
        log(sea); // your answer? => broadway, dockside

        List<String> filteredNameList = memberList.stream() //
                .filter(mb -> mb.getWithdrawal().isPresent()) //
                .map(mb -> mb.getMemberName()) //
                .collect(Collectors.toList());
        String land = filteredNameList.toString();
        log(land); // your answer? => broadway, dockside
    } // stream(): コレクションをストリームに変換 by hase (2025/07/14)
    // filter(): 条件でフィルタリング
    // map(): 値を変換
    // collect(): ストリームをコレクションに変換

    /**
     * What string is sea, variables at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_java8_stream_map_flatMap() {
        List<St8Member> memberList = new St8DbFacade().selectMemberListAll();
        int sea = memberList.stream()
                .filter(mb -> mb.getWithdrawal().isPresent())
                .flatMap(mb -> mb.getPurchaseList().stream())
                .filter(pur -> pur.getPurchaseId() > 100)
                .mapToInt(pur -> pur.getPurchasePrice())
                .distinct()
                .sum();
        log(sea); // your answer? => 600
    } // distinct() で重複が排除されるので100 + 200 + 300 = 600 by hase (2025/07/14)

    // *Stream API will return at Step12 again, it's worth the wait!
}
