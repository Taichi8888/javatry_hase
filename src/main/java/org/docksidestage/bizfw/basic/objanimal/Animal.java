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

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
// BarkingProcess移行によりひとまず使わなくなったが、また新しいメソッド作成時に使う可能性あり by hase (2025/07/08)

import org.docksidestage.bizfw.basic.objanimal.barking.AnimalBarkingProcess;
import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;

/**
 * The object for animal(動物).
 * @author jflute
 * @author tahasega
 */
public abstract class Animal extends Creature {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
//    private static final Logger logger = LoggerFactory.getLogger(Animal.class);
//    BarkingProcess移行によりひとまず使わなくなったが、また新しいメソッド作成時に使う可能性あり by hase (2025/07/08)

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
// おもいで：Creatureクラスに移行by hase (2025/07/09)
//    protected int hitPoint; // is HP
//    // done hase final付けられるなら付けておきましょう。newされて以降変わることはないということを示すためにも by jflute (2025/07/07)
    protected final AnimalBarkingProcess animalBarkingProcess;
    
    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Animal() {
// おもいで：Creatureクラスに移行by hase (2025/07/09)
//        hitPoint = getInitialHitPoint();
        animalBarkingProcess = new AnimalBarkingProcess(this);
    }

    @Override
    protected int getInitialHitPoint() {
        return 10; // as default
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
// おもいで：Creatureクラスに移行by hase (2025/07/09)
//    UndeadBarkingProcess作って再び戻ってきた by hase (2025/07/15)
    @Override
    public BarkedSound bark() {
        return this.animalBarkingProcess.bark();
    }
    // 引数にthis渡すのが冗長な気がする。
    // breatheInとかもまとめて外に出しても良いかも→HP管理がめんどくさい。
    // done [いいね] 外に出す考え方は良さそう
    // そもそもbarkingProcessを作成した理由はAnimalのbark()のプロセスをそっちに切り出すため
    // なので、animalクラスにbarkに関する処理が多く残っているのは望ましくない
    // barkingProcessにどこまで責務を持たせるか
    // ただし、現状downHitPoint()はAnimalの責務なので、そこはBarkingProcessに持たせられない
    // うまくその処理を委譲できないかな・・・・ by tanaryo (2025/7/5

    // done hase 修行++: コメントで "depends on barking" とありますから、barkのための「息継ぎ」なので... by jflute (2025/07/07)
    // やはり、BarkingProcessに置きたいメソッドですね。Zombieのオーバーライドがあるから取り残されているんでしょうが...
    // 他のケースで、prepareAbdominalMuscle()をオーバーライドしたい動物がいたらキリがないです。
    // BarkingProcess側に持っていっても、Zombieの振る舞いが維持できるような仕組みを考えてみましょう。
// おもいで：barkingProcessを作成する前
//    public void breatheIn() { // actually depends on barking
//        logger.debug("...Breathing in for barking"); // dummy implementation
//        downHitPoint();
//    }

//    public void prepareAbdominalMuscle() { // also actually depends on barking
//        logger.debug("...Using my abdominal muscle for barking"); // dummy implementation
//        downHitPoint();
//    }

    public abstract String getBarkWord();
    // BarkingProcessは「鳴く行為の手順」を担当させ、動物ごとに異なる鳴き声の取得getBarkWord()や、
    // 鳴く行為よりも抽象度の高いdownHitPoint()はAnimalに残しました。
    // breathInは迷ったのですが、Animalの子クラスであるZombieでoverrideしていて、
    // barkではない別のところでも使える可能性高いかなと思い、Animalに残しました。(hase 25/7/7)
    // done hase [いいね] クラス名がProcessですからね。鳴き声はデータでもあるのでAnimalで良いと思います by jflute (2025/07/07)
    // downHitPoint()は、それこそ fight() が使ってますからね(^^。

//    public BarkedSound doBark(String barkWord) {
//        downHitPoint();
//        return new BarkedSound(barkWord);
//    }

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========

    @Override
    public void downHitPoint() {
        --hitPoint;
        if (hitPoint <= 0) {
            throw new IllegalStateException("I'm very tired, so I want to sleep, " + getBarkWord());
        }
    }

    // ===================================================================================
    //                                                                               Fight
    //                                                                           =========
    @Override
    public void fight(Creature creature) {
        creature.downHitPoint();
        downHitPoint();
    }

    // ===================================================================================
    //                                                                               Loud
    //                                                                              ======
// おもいで：Creatureクラスに移行by hase (2025/07/09)
//    @Override
//    public String soundLoudly() {
//        return bark().getBarkWord();
//    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
// おもいで：Creatureクラスに移行by hase (2025/07/09)
//    public int getHitPoint() {
//        return hitPoint;
//    }
}
