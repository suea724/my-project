package community;

import common.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Dummy {

    public static void main(String[] args) {

        Connection conn;
        ResultSet rs;
        PreparedStatement pstmt;
        Statement stmt;
        String sql;

        conn = DBUtil.open();

        // 임의 회원 정보
        String name = "";
        String id = "";
        String pw = "1111";

        // 임의 글
        String title = "";
        String content = "내용입니다.";

        try {
            // 임의의 회원 생성
            for (int i = 0; i < 100 ; i ++) {
                name = getRandomName();
                id = getRandomId();

                sql = "insert into tblUser values(?, ?, ?, default, default, default)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, id);
                pstmt.setString(2, pw);
                pstmt.setString(3, name);
                pstmt.executeUpdate();
            }

            // 회원의 모든 아이디
            ArrayList<String> idList = new ArrayList<>();

            sql = "select id from tblUser";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                idList.add(rs.getString("id"));
            }

            // 임의의 글 추가
            for (int i = 0 ; i < 50 ; i ++) {
                title = getRandomTitle();
                sql = "insert into tblCommunity values(seqCommunity.nextVal, ?, ?, default, default, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, title);
                pstmt.setString(2, content);
                pstmt.setString(3, idList.get((int)(Math.random() * idList.size())));
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 회원 임의 이름
    private static String getRandomName() {

        ArrayList<String> surname = new ArrayList<>(Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안",
                "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차", "주",
                "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염", "양",
                "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕", "금",
                "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용"));

        ArrayList<String> name = new ArrayList<>(Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다",
                "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박",
                "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순",
                "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위",
                "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준",
                "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형",
                "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비",
                "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼",
                "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠",
                "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련"));

        Collections.shuffle(surname);
        Collections.shuffle(name);

        return surname.get(0) + name.get(0) + name.get(1);
    }

    // 회원 임의 아이디
    private static String getRandomId() {
        String id = "";
        String str = "abcdefghijklmnopqrstuvwxyz";

        for(int i = 0 ; i < 6 ; i ++) {
            id += str.charAt((int)(Math.random() * str.length()));
        }

        id += (int)(Math.random() * 99) + 1;

        return id;
    }

    private static String getRandomTitle() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("신입 개발자입니다.", "안녕하세요~", "개발자 취업 조언 부탁드립니다.", "노트북 추천해주세요", "포트폴리오 이정도면 되나요?", "A회사 면접 후기입니다.", "면접에서 받았던 질문 리스트 공유합니다.", "팀 프로젝트로 개발한 웹서비스 소개합니다!"));

        return list.get((int)(Math.random() * list.size()));
    }
}
