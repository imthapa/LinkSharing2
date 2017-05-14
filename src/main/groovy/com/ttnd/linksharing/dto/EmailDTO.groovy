package com.ttnd.linksharing.dto

/**
 * Created by ishwar on 14/5/17.
 */
class EmailDTO {
//    List<String> to;
    String to
    String subject;
    String view;
    String content


    @Override
    public String toString() {
        return "EmailDTO{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", view='" + view + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
