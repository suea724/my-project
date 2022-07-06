package study.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
public class StudyListDTO {
    private String seq;
    private String title;
    private String category;
    private String startdate;
    private String name;
    private int viewcnt;
    private ArrayList<String> langs;

    @Builder
    public StudyListDTO(String seq, String title, String category, String startdate, String name, int viewcnt, ArrayList<String> langs) {
        this.seq = seq;
        this.title = title;
        this.category = category;
        this.startdate = startdate;
        this.name = name;
        this.viewcnt = viewcnt;
        this.langs = langs;
    }
}
