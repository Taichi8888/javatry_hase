package org.docksidestage.javatry.basic.st6.dbms;

/**
 * SQLのクエリのもと
 * @author tahasega
 */
public abstract class AbstractDbms { // MySQL, PostgreSQLでpagingQueryの書き方が少し違う
    public String buildPagingQuery(int pageSize, int pageNumber) {
        int offset = pageSize * (pageNumber - 1);
        return eachPagingQuery(pageSize, offset);
    }
    // TODO hase publicである必要がないです by jflute (2025/07/07)
    public abstract String eachPagingQuery(int pageSize, int offset);
}
