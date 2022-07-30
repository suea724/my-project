package qna;

import common.DBUtil;
import community.dto.CommunityListDTO;
import qna.dto.AnswerDTO;
import qna.dto.QnaListDTO;
import qna.dto.QnaViewDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class QnaDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet rs;

    public QnaDAO() {
        conn = DBUtil.open();
    }

    public ArrayList<QnaListDTO> list(int page) {

        int begin = (page - 1) * 10 + 1;
        int end = page * 10;

        try {

        String sql = "select * from (select rownum as rnum, a.* from (select * from vwQuestionList) a) where rnum between ? and ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, begin);
        pstmt.setInt(2, end);
        rs = pstmt.executeQuery();

        ArrayList<QnaListDTO> list = new ArrayList<>();

        while(rs.next()) {
            QnaListDTO dto = QnaListDTO.builder()
                    .seq(rs.getString("seq"))
                    .title(rs.getString("title"))
                    .regdate(rs.getString("regdate"))
                    .viewcnt(rs.getInt("viewcnt"))
                    .status(rs.getString("status"))
                    .answercnt(rs.getString("answercnt"))
                    .name(rs.getString("name"))
                    .isNew(rs.getString("isnew"))
                    .build();

            list.add(dto);
        }
        return list;

    } catch (Exception e) {
        e.printStackTrace();
    }
        return null;
    }

    public int getTotalCount() {
        try {
            String sql = "select count(*) as cnt from tblQuestion";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt("cnt");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getSearchCount(String type, String keyword) {
        try {
            String sql = String.format("select count(*) as cnt from vwQuestionList where %s like '%%%s%%'", type, keyword);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt("cnt");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<QnaListDTO> findBySearch(String type, String keyword, int page) {
        try {
            int begin = (page - 1) * 10 + 1;
            int end = page * 10;

            String sql = String.format("select * from (select rownum as rnum, a.* from (select * from vwQuestionList where %s like '%%%s%%') a) where rnum between ? and ?", type, keyword);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, begin);
            pstmt.setInt(2, end);
            rs = pstmt.executeQuery();

            ArrayList<QnaListDTO> list = new ArrayList<>();

            while(rs.next()) {
                QnaListDTO dto = QnaListDTO.builder()
                        .seq(rs.getString("seq"))
                        .title(rs.getString("title"))
                        .regdate(rs.getString("regdate"))
                        .viewcnt(rs.getInt("viewcnt"))
                        .status(rs.getString("status"))
                        .answercnt(rs.getString("answercnt"))
                        .name(rs.getString("name"))
                        .isNew(rs.getString("isnew"))
                        .build();

                list.add(dto);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addViewCnt(String seq) {
        try {
            String sql = "update tblQuestion set viewcnt = viewcnt + 1 where seq = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, seq);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public QnaViewDTO findQuestion(String seq) {
        try {
            String sql = "select * from vwQuestion where seq = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, seq);
            rs = pstmt.executeQuery();

            QnaViewDTO dto = null;

            if(rs.next()) {
                dto = QnaViewDTO.builder()
                        .seq(rs.getString("seq"))
                        .title(rs.getString("title"))
                        .content(rs.getString("content"))
                        .regdate(rs.getString("regdate"))
                        .viewcnt(rs.getInt("viewcnt"))
                        .status(rs.getString("status"))
                        .name(rs.getString("name"))
                        .id(rs.getString("id"))
                        .build();

            }
            return dto;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<AnswerDTO> findAnswers(String seq) {
        try {
            String sql = "select * from vwAnswer where qseq = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, seq);
            rs = pstmt.executeQuery();

            ArrayList<AnswerDTO> list = new ArrayList<>();

            while(rs.next()) {

                AnswerDTO dto = AnswerDTO.builder()
                                    .seq(rs.getString("seq"))
                                    .content(rs.getString("content"))
                                    .regdate(rs.getString("regdate"))
                                    .name(rs.getString("name"))
                                    .id(rs.getString("id"))
                                    .build();

                list.add(dto);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int addAnswer(AnswerDTO dto) {
        try {
            String sql = "insert into tblAnswer values (seqAnswer.nextval, ?, ?, ?, default)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getId());
            pstmt.setString(2, dto.getQseq());
            pstmt.setString(3, dto.getContent());

            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getLatestAnswerSeq() {
        try {
            String sql = "select max(seq) as maxseq from tblAnswer";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getString("maxseq");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public AnswerDTO getLatestAnswer(String latestAnswerSeq) {
        try {
            String sql = "select * from vwAnswer where seq = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, latestAnswerSeq);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                AnswerDTO answerDTO = AnswerDTO.builder()
                        .seq(rs.getString("seq"))
                        .qseq(rs.getString("qseq"))
                        .content(rs.getString("content"))
                        .regdate(rs.getString("regdate"))
                        .id(rs.getString("id"))
                        .name(rs.getString("name"))
                        .build();

                return answerDTO;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updateSolved(String seq) {
        try {
            String sql = "update tblQuestion set status = '해결' where seq = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, seq);
            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteAnswers(String seq) {
        try {
            String sql = "delete from tblAnswer where qseq = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, seq);
            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(String seq) {
        try {
            String sql = "delete from tblQuestion where seq = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, seq);
            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int save(String title, String content, String id) {
        try {
            String sql = "insert into tblQuestion values (seqQuestion.nextval, ?, ?, ?, default, default, default)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, title);
            pstmt.setString(3, content);

            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getMaxSeq() {
        try {
            String sql = "select max(seq) as max from tblQuestion";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
                return rs.getString("max");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int deleteAnswer(String seq) {
        try {
            String sql = "delete from tblAnswer where seq = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, seq);
            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateQuestion(String title, String content, String seq) {
        try {
            String sql = "update tblQuestion set title = ?, content = ? where seq = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setString(3, seq);
            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
