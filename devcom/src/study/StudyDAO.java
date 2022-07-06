package study;

import common.DBUtil;
import study.dto.StudyListDTO;
import study.dto.StudyViewDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StudyDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet rs;

    public StudyDAO() {
        conn = DBUtil.open();
    }

    public ArrayList<StudyListDTO> list() {

        try {
            String sql = "select * from vwStudy";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            ArrayList<StudyListDTO> list = new ArrayList<>();

            while (rs.next()) {

                String seq = rs.getString("seq");

                ArrayList<String> langList = listLang(seq);

                StudyListDTO dto = StudyListDTO.builder()
                        .seq(seq)
                        .title(rs.getString("title"))
                        .category(rs.getString("category"))
                        .name(rs.getString("name"))
                        .viewcnt(rs.getInt("viewcnt"))
                        .startdate(rs.getString("startdate"))
                        .langs(langList)
                        .build();

                list.add(dto);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<String> listLang(String seq) {

        try {
            String sql = "select l.lang from tblStudy s inner join tblStudyLanguage sl on s.seq = sl.sseq inner join tblLanguage l on l.seq = sl.lseq where s.seq = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, seq);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<String> list = new ArrayList<>();

            while(rs.next()) {
                list.add(rs.getString("lang"));
            }

            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public int add(StudyViewDTO dto) {
        try {
            String sql = "insert into tblStudy values(seqStudy.nextVal, ?, ?, ?, ?, ?, ?, ?, ?, default, default, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getCategory());
            pstmt.setString(2, dto.getProgressway());
            pstmt.setString(3, dto.getRecruitment());
            pstmt.setString(4, dto.getStartdate());
            pstmt.setString(5, dto.getDuration());
            pstmt.setString(6, dto.getContact());
            pstmt.setString(7, dto.getTitle());
            pstmt.setString(8, dto.getContent());
            pstmt.setString(9, dto.getId());

            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public StudyViewDTO get(String seq) {
        try {
            String sql = "select * from vwStudy where seq = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, seq);
            ResultSet rs = pstmt.executeQuery();

            StudyViewDTO dto = StudyViewDTO.builder().build();

            if (rs.next()) {
                dto.setSeq(rs.getString("seq"));
                dto.setCategory(rs.getString("category"));
                dto.setProgressway(rs.getString("progressway"));
                dto.setRecruitment(rs.getString("recruitment"));
                dto.setStartdate(rs.getString("startdate"));
                dto.setDuration(rs.getString("duration"));
                dto.setContact(rs.getString("contact"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setRegdate(rs.getString("regdate"));
                dto.setViewcnt(rs.getInt("viewcnt"));
                dto.setName(rs.getString("name"));
                dto.setId(rs.getString("id"));
            }
            return dto;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getMaxSeq() {
        try {
            String sql = "select max(seq) as seq from tblStudy";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getString("seq");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
