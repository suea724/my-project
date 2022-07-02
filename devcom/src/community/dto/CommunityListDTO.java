package community.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class CommunityListDTO {
    private String seq;
    private String title;
    private String regdate;
    private int viewcnt;
    private String name;
    private double isnew;

    @Builder
    public CommunityListDTO(String seq, String title, String regdate, int viewcnt, String name, double isnew) {
        this.seq = seq;
        this.title = title;
        this.regdate = regdate;
        this.viewcnt = viewcnt;
        this.name = name;
        this.isnew = isnew;
    }
}
