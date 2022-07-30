package qna.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class QnaListDTO {
    private String seq;
    private String title;
    private String regdate;
    private int viewcnt;
    private String name;
    private String status;
    private String answercnt;
    private String isNew;

    @Builder
    public QnaListDTO(String seq, String title, String regdate, int viewcnt, String name, String status, String answercnt, String isNew) {
        this.seq = seq;
        this.title = title;
        this.regdate = regdate;
        this.viewcnt = viewcnt;
        this.name = name;
        this.status = status;
        this.answercnt = answercnt;
        this.isNew = isNew;
    }
}
