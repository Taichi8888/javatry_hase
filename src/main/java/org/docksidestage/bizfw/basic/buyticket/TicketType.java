package org.docksidestage.bizfw.basic.buyticket;

/**
 * チケットの種類（価格、使用可能日数、夜間限定フラグ）を表すクラス
 * @author tahasega
 */
public enum TicketType {
    ONE_DAY(7400, 1, false),
    TWO_DAY(13200, 2, false),
    FOUR_DAY(22400, 4, false),
    TWO_NIGHT(7400, 2, true);
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int ticketPrice; // チケットの価格
    private final int maxDays; // チケットの使用可能日数
    private final boolean onlyNightAvailable; // 夜間限定フラグ

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    TicketType(int ticketPrice, int maxDays, boolean onlyNightAvailable) {
        this.ticketPrice = ticketPrice;
        this.maxDays = maxDays;
        this.onlyNightAvailable = onlyNightAvailable;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getTicketPrice() {
        return ticketPrice;
    }

    public int getMaxDays() {
        return maxDays;
    }

    public  boolean isOnlyNightAvailable() {
        return onlyNightAvailable;
    }
}
