package community;

import community.dto.CommunityListDTO;
import community.dto.CommunityViewDTO;
import common.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CommunityDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet rs;

    public CommunityDAO() {
        conn = DBUtil.open();
    }

    public ArrayList<CommunityListDTO> list(int page) {

        try {

            int begin = (page - 1) * 10 + 1;
            int end = page * 10;

            String sql = "select seq, title, regdate, viewcnt, id, name, isnew from (select rownum as rnum, c.* from vwCommunity c) where rnum between ? and ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(begin));
            pstmt.setString(2, String.valueOf(end));
            rs = pstmt.executeQuery();

            ArrayList<CommunityListDTO> list = new ArrayList<>();

            while(rs.next()) {
                CommunityListDTO dto = CommunityListDTO.builder()
                                                        .seq(rs.getString("seq"))
                                                        .title(rs.getString("title"))
                                                        .regdate(rs.getString("regdate"))
                                                        .viewcnt(rs.getInt("viewcnt"))
                                                        .name(rs.getString("name"))
                                                        .isnew(rs.getDouble("isnew"))
                                                        .build();

                list.add(dto);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<CommunityListDTO> findTen() {

        try {

            String sql = "select * from (select * from vwCommunity order by viewcnt desc) where rownum <= 10";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);

            ArrayList<CommunityListDTO> list = new ArrayList<>();

            while(rs.next()) {
                CommunityListDTO dto = CommunityListDTO.builder()
                                                        .seq(rs.getString("seq"))
                                                        .title(rs.getString("title"))
                                                        .regdate(rs.getString("regdate"))
                                                        .viewcnt(rs.getInt("viewcnt"))
                                                        .name(rs.getString("name"))
                                                        .isnew(rs.getDouble("isnew"))
                                                        .build();

                list.add(dto);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String save(String title, String content, String id) {
        try {
            String sql = "insert into tblCommunity(seq, title, content, regdate, viewcnt, id) values(seqCommunity.nextVal, ?, ?, default, default, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setString(3, id);
            int result = pstmt.executeUpdate();

            if (result > 0) {
                return getSeq(title, content, id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getSeq(String title, String content, String id) {
        try {
            String sql = "select max(seq) as seq from tblCommunity where title = ? and content = ? and id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setString(3, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("seq");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public CommunityViewDTO find(String seq) {

        try {
        String sql = "select * from vwCommunity where seq = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, seq);
        rs = pstmt.executeQuery();

        CommunityViewDTO dto = null;

        if(rs.next()) {
            dto = CommunityViewDTO.builder()
                    .seq(rs.getString("seq"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .regdate(rs.getString("regdate"))
                    .viewcnt(rs.getInt("viewcnt"))
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

    public int delete(String seq) {

        try {
            String sql = "delete from tblCommunity where seq = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, seq);
            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(String title, String content, String seq) {
        try {
            String sql = "update tblCommunity set title = ?, content = ? where seq = ?";
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

    public void addViewCnt(String seq) {

        try {
            String sql = "update tblCommunity set viewcnt = viewcnt + 1 where seq = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, seq);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<CommunityListDTO> findBySearch(String type, String keyword, int page) {

        try {
            int begin = (page - 1) * 10 + 1;
            int end = page * 10;

            String sql = String.format("select * from (select rownum as rnum, c.* from vwCommunity c where %s like '%%%s%%') where rnum between ? and ?", type, keyword);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(begin));
            pstmt.setString(2, String.valueOf(end));
            rs = pstmt.executeQuery();

            ArrayList<CommunityListDTO> list = new ArrayList<>();

            while(rs.next()) {
                CommunityListDTO dto = CommunityListDTO.builder()
                        .seq(rs.getString("seq"))
                        .title(rs.getString("title"))
                        .regdate(rs.getString("regdate"))
                        .viewcnt(rs.getInt("viewcnt"))
                        .name(rs.getString("name"))
                        .isnew(rs.getDouble("isnew"))
                        .build();

                list.add(dto);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getSearchCount (String type, String keyword) {

        try {
            String sql = String.format("select count(*) as cnt from vwCommunity where %s like '%%%s%%'", type, keyword);
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

    public int getTotalCount() {

        try {
            String sql = "select count(*) as cnt from vwCommunity";
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

}
