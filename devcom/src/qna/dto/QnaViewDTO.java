package qna.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class QnaViewDTO {
    private String seq;
    private String title;
    private String content;
    private String regdate;
    private int viewcnt;
    private String status;
    private String name;
    private String id;

    @Builder

    public QnaViewDTO(String seq, String title, String content, String regdate, int viewcnt, String status, String name, String id) {
        this.seq = seq;
        this.title = title;
        this.content = content;
        this.regdate = regdate;
        this.viewcnt = viewcnt;
        this.status = status;
        this.name = name;
        this.id = id;
    }
}
