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
package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.barking.BarkingProcess;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The object for animal(動物).
 * @author jflute
 * @author tahasega
 */
public abstract class Animal implements Loudable {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(Animal.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected int hitPoint; // is HP
    protected BarkingProcess barkingProcess;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Animal() {
        hitPoint = getInitialHitPoint();
        barkingProcess = new BarkingProcess();
    }

    protected int getInitialHitPoint() {
        return 10; // as default
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    public BarkedSound bark() {
        return this.barkingProcess.bark(this);
    } // 引数にthis渡すのが冗長な気がする。
    // breatheInとかもまとめて外に出しても良いかも→HP管理がめんどくさい。
    // done [いいね] 外に出す考え方は良さそう
    // そもそもbarkingProcessを作成した理由はAnimalのbark()のプロセスをそっちに切り出すため
    // なので、animalクラスにbarkに関する処理が多く残っているのは望ましくない
    // barkingProcessにどこまで責務を持たせるか
    // ただし、現状downHitPoint()はAnimalの責務なので、そこはBarkingProcessに持たせられない
    // うまくその処理を委譲できないかな・・・・ by tanaryo (2025/7/5

    public void breatheIn() { // actually depends on barking
        logger.debug("...Breathing in for barking"); // dummy implementation
        downHitPoint();
    }

//    public void prepareAbdominalMuscle() { // also actually depends on barking
//        logger.debug("...Using my abdominal muscle for barking"); // dummy implementation
//        downHitPoint();
//    }

    public abstract String getBarkWord();
    // BarkingProcessは「鳴く行為の手順」を担当させ、動物ごとに異なる鳴き声の取得getBarkWord()や、
    // 鳴く行為よりも抽象度の高いdownHitPoint()はAnimalに残しました。
    // breathInは迷ったのですが、Animalの子クラスであるZombieでoverrideしていて、
    // barkではない別のところでも使える可能性高いかなと思い、Animalに残しました。(hase 25/7/7)

//    public BarkedSound doBark(String barkWord) {
//        downHitPoint();
//        return new BarkedSound(barkWord);
//    }

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    public void downHitPoint() {
        --hitPoint;
        if (hitPoint <= 0) {
            throw new IllegalStateException("I'm very tired, so I want to sleep" + getBarkWord());
        }
    }

    public void fight() {
        downHitPoint();
    }

    // ===================================================================================
    //                                                                               Loud
    //                                                                              ======
    @Override
    public String soundLoudly() {
        return bark().getBarkWord();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getHitPoint() {
        return hitPoint;
    }
}
