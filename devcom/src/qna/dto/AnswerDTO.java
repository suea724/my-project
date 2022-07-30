package qna.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AnswerDTO {
    private String seq;
    private String qseq;
    private String content;
    private String regdate;
    private String name;
    private String id;

    @Builder
    public AnswerDTO(String seq, String qseq, String content, String regdate, String name, String id) {
        this.seq = seq;
        this.qseq = qseq;
        this.content = content;
        this.regdate = regdate;
        this.name = name;
        this.id = id;
    }
}
