package user;

import lombok.Builder;
import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String pw;
    private String name;
    private String grade;
    private String regdate;
    private String pic;

    @Builder
    public UserDTO(String id, String pw, String name, String grade, String regdate, String pic) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.grade = grade;
        this.regdate = regdate;
        this.pic = pic;
    }
}
