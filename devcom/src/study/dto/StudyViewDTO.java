package study.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
public class StudyViewDTO {

    private String seq;
    private String category;
    private String progressway;
    private String recruitment;
    private String startdate;
    private String duration;
    private String contact;
    private String title;
    private String content;
    private String regdate;
    private int viewcnt;
    private String name;
    private String id;

    private ArrayList<String> langs;

    @Builder
    public StudyViewDTO(String seq, String category, String progressway, String recruitment, String startdate, String duration, String contact, String title, String content, String regdate, int viewcnt, String name, ArrayList<String> langs, String id) {
        this.seq = seq;
        this.category = category;
        this.progressway = progressway;
        this.recruitment = recruitment;
        this.startdate = startdate;
        this.duration = duration;
        this.contact = contact;
        this.title = title;
        this.content = content;
        this.regdate = regdate;
        this.viewcnt = viewcnt;
        this.name = name;
        this.langs = langs;
        this.id = id;
    }
}
