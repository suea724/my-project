package community.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class CommunityViewDTO {

    private String seq;
    private String title;
    private String content;
    private String regdate;
    private int viewcnt;
    private String name;
    private String id;

    @Builder

    public CommunityViewDTO(String seq, String title, String content, String regdate, int viewcnt, String name, String id) {
        this.seq = seq;
        this.title = title;
        this.content = content;
        this.regdate = regdate;
        this.viewcnt = viewcnt;
        this.name = name;
        this.id = id;
    }
}
