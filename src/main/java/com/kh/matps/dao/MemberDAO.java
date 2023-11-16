package com.kh.matps.dao;

import com.kh.matps.common.Common;
import com.kh.matps.vo.MemberVO;
import com.kh.matps.vo.ReviewVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    // id 중복 여부 확인
    public boolean regMemberCheckId(String id) {
        System.out.println("DAO 진입 전 회원 가입 여부 확인 ID : " + id);
        boolean isNotReg = false; // 가입 여부를 담을 변수
        try { // DB 연결
            System.out.println("DAO 진입 회원 가입 여부 확인 ID : " + id);
            conn = Common.getConnection();  // Common 클래스의 getConnection 메소드를 호출하여 DB 연결
            stmt = conn.createStatement(); // Statement 객체 얻기
            String sql = "SELECT * FROM CUSTOMER_TB WHERE MY_ID = " + "'" + id +"'"; // 쿼리문 작성
            rs = stmt.executeQuery(sql); // 쿼리문 수행 결과를 rs에 저장
            System.out.println("INPUT_ID : " + id);
            if(rs.next()) isNotReg = false; // 쿼리문 수행 결과가 있으면 false
            else isNotReg = true; // 쿼리문 수행 결과가 없으면 true
        } catch(Exception e) { // 예외 처리
            e.printStackTrace(); // 예외 메시지 출력
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        System.out.println("TRUE");
        System.out.println("what");
        System.out.println("FALSE");
        System.out.println(isNotReg);
        return isNotReg; // 가입 되어 있으면 false, 가입이 안되어 있으면 true
    }

    // 닉네임 중복 여부 확인
    public boolean regMemberCheckNick(String nick) {
        System.out.println("DAO 진입 전 회원 가입 여부 확인 NICK : " + nick);
        boolean isNotReg = false; // 가입 여부를 담을 변수
        try { // DB 연결
            System.out.println("DAO 진입 회원 가입 여부 확인 NICK : " + nick);
            conn = Common.getConnection();  // Common 클래스의 getConnection 메소드를 호출하여 DB 연결
            stmt = conn.createStatement(); // Statement 객체 얻기
            String sql = "SELECT * FROM CUSTOMER_TB WHERE MY_NICKNAME = " + "'" + nick +"'"; // 쿼리문 작성
            rs = stmt.executeQuery(sql); // 쿼리문 수행 결과를 rs에 저장
            System.out.println("INPUT_NICKNAME : " + nick);
            if(rs.next()) isNotReg = false;
            else isNotReg = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        System.out.println("TRUE");
        System.out.println("what");
        System.out.println("FALSE");
        System.out.println(isNotReg);
        return isNotReg; // 가입 되어 있으면 false, 가입이 안되어 있으면 true
    }
    // 로그인 체크
    public boolean loginCheck(String id, String pwd) {
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement(); // Statement 객체 얻기
            String sql = "SELECT * FROM CUSTOMER_TB WHERE MY_ID = " + "'" + id + "'";
            rs = stmt.executeQuery(sql);

            System.out.println("INPUT_ID : " + id);
            System.out.println("INPUT_PWD : " + pwd);

            while(rs.next()) { // 읽은 데이타가 있으면 true
                String sqlId = rs.getString("MY_ID"); // 쿼리문 수행 결과에서 ID값을 가져 옴
                String sqlPwd = rs.getString("MY_PW");
                System.out.println("MY_ID : " + sqlId);
                System.out.println("MY_PWD : " + sqlPwd);
                if(id.equals(sqlId) && pwd.equals(sqlPwd)) {
                    Common.close(rs);
                    Common.close(stmt);
                    Common.close(conn);
                    return true;
                }
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch(Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    // 회원정보 조회
    public List<MemberVO> memberSelect(String getName) {
        List<MemberVO> list = new ArrayList<>();
        String sql = null;
        System.out.println("memberSelect - NAME : " + getName);
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            if(getName.equals("ALL")) sql = "SELECT * FROM CUSTOMER_TB";
            else sql = "SELECT * FROM CUSTOMER_TB WHERE MY_ID = " + "'" + getName + "'";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String id = rs.getString("MY_ID");
                String pwd = rs.getString("MY_PW");
                String mail = rs.getString("MY_EMAIL");
                String name = rs.getString("MY_NAME");
                String nick = rs.getString("MY_NICKNAME");
                String gender = rs.getString("MY_GENDER");
                String profile_img = rs.getString("MY_PROFILE_IMG");


                MemberVO vo = new MemberVO(); // MemberVO 객체 생성
                vo.setMy_id(id); // MemberVO 객체에 id 저장
                vo.setMy_pw(pwd); // MemberVO 객체에 pwd 저장
                vo.setMy_email(mail); // MemberVO 객체에 mail 저장
                vo.setMy_name(name); // MemberVO 객체에 name 저장
                vo.setMy_nickname(nick); // MemberVO 객체에 nick 저장
                vo.setMy_gender(gender); // MemberVO 객체에 gender  저장
                vo.setMy_profile_img(profile_img); // MemberVO 객체에 profile_img 저장
                list.add(vo); // 리스트에 추가
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 회원 가입
    public boolean memberRegister(String id, String pwd, String mail, String name, String nick, String gender, String profile_img) {
        int result = 0;
        String sql = "INSERT INTO CUSTOMER_TB(MY_ID, MY_PW, MY_EMAIL, MY_NAME, MY_NICKNAME, MY_GENDER, MY_PROFILE_IMG) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            pStmt.setString(2, pwd);
            pStmt.setString(3, mail);
            pStmt.setString(4, name);
            pStmt.setString(5, nick);
            pStmt.setString(6, gender);
            pStmt.setString(7, profile_img);
            result = pStmt.executeUpdate();
            System.out.println("회원 가입 DB 결과 확인 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if(result == 1) return true;
        else return false;
    }

    // 회원 정보 수정
    public boolean memberUpdate(String id, String nick, String profile_img) {
        int result = 0;
        String sql = "UPDATE CUSTOMER_TB SET MY_NICKNAME = ?, MY_PROFILE_IMG = ? WHERE MY_ID = ?";
        System.out.println("멤버업데이트");
        System.out.println("id : " + id + "nick : " + nick + "profile_img : " + profile_img);
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, nick);
            pStmt.setString(2, profile_img);
            pStmt.setString(3, id);
            result = pStmt.executeUpdate();
            System.out.println("회원 정보 수정 DB 결과 확인 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if(result == 1) return true;
        else return false;
    }

    //회원 탈퇴
    public boolean memberDelete(String id) {
        int result = 0;
        String sql = "DELETE FROM CUSTOMER_TB WHERE MY_ID = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            result = pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        if(result == 1) return true;
        else return false;
    }

    // 내가 쓴 리뷰 조회
    public List<ReviewVO> reviewRegister(String getNick) {
        List<ReviewVO> list = new ArrayList<>();
        String sql = null;
        System.out.println("리뷰 조회 시도 ID : " + getNick);
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT * FROM REVIEW_TB WHERE USER_NICKNAME = " + "'" + getNick + "'"; // 리뷰테이블에서 MY_ID 가 nick 인 모든것을 조회
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String id = rs.getString("REVIEW_ID");
                String profile_img = rs.getString("USER_PROFILE_IMG");
                String nick = rs.getString("USER_NICKNAME");
                String date = rs.getString("REVIEW_DATE");
                String score = rs.getString("REVIEW_SCORE");
                String text = rs.getString("REVIEW_TEXT");
                String img_01 = rs.getString("REVIEW_IMG_01");
                String img_02 = rs.getString("REVIEW_IMG_02");
                String img_03 = rs.getString("REVIEW_IMG_03");
                String img_04 = rs.getString("REVIEW_IMG_04");
                String img_05 = rs.getString("REVIEW_IMG_05");
                String storeName = rs.getString("STORE_NAME");

                ReviewVO vo = new ReviewVO();
                vo.setReview_id(id);
                vo.setUser_profile_img(profile_img);
                vo.setUser_nickname(nick);
                vo.setReview_date(date);
                vo.setReview_score(score);
                vo.setReview_text(text);
                vo.setReview_img01(img_01);
                vo.setReview_img02(img_02);
                vo.setReview_img03(img_03);
                vo.setReview_img04(img_04);
                vo.setReview_img05(img_05);
                vo.setStore_name(storeName);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
