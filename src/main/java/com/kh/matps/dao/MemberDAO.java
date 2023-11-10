package com.kh.matps.dao;

import com.kh.matps.common.Common;
import com.kh.matps.vo.MemberVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    // id 회원 가입 여부 확인
    public boolean regMemberCheck(String id) {
        System.out.println("DAO 진입 전 회원 가입 여부 확인 ID : " + id);
        boolean isNotReg = false; // 가입 여부를 담을 변수
        try { // DB 연결
            System.out.println("DAO 진입 회원 가입 여부 확인 ID : " + id);
            conn = Common.getConnection();  // Common 클래스의 getConnection 메소드를 호출하여 DB 연결
            stmt = conn.createStatement(); // Statement 객체 얻기
            String sql = "SELECT * FROM CUSTOMER_TB WHERE MY_ID = " + "'" + id +"'"; // 쿼리문 작성
            rs = stmt.executeQuery(sql); // 쿼리문 수행 결과를 rs에 저장
            System.out.println("INPUT_ID : " + id);
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
        System.out.println("NAME : " + getName);
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            if(getName.equals("ALL")) sql = "SELECT * FROM CUSTOMER_TB";
            else sql = "SELECT * FROM CUSTOMER_TB WHERE MY_ID = " + "'" + getName + "'";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String id = rs.getString("MY_ID");
                String pwd = rs.getString("MY_PWD");
                String mail = rs.getString("MY_EMAIL");
                String name = rs.getString("MY_NAME");
                String nick = rs.getString("MY_NICKNAME");
                String gender = rs.getString("MY_GENDER");
                String profile_img = rs.getString("MY_PROFILE_IMG");


                MemberVO vo = new MemberVO();
                vo.setMy_id(id);
                vo.setMy_pw(pwd);
                vo.setMy_email(mail);
                vo.setMy_name(name);
                vo.setMy_nickname(nick);
                vo.setMy_gender(gender);
                vo.setMy_profile_img(profile_img);
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

    // 회원 가입
    public boolean memberRegister(String id, String pwd, String mail, String name, String nick, String gender) {
        int result = 0;
        String sql = "INSERT INTO CUSTOMER_TB(MY_ID, MY_PWD,MY_EMAIL, MY_NAME, MY_NICKNAME, MY_GENDER) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            pStmt.setString(2, pwd);
            pStmt.setString(3, mail);
            pStmt.setString(4, name);
            pStmt.setString(5, nick);
            pStmt.setString(6, gender);
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

}
