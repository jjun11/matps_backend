package com.kh.matps.dao;

import com.kh.matps.common.Common;
import com.kh.matps.vo.StoreListVO;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class StoreListDAO {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    //식당 정보 조회
    public List <StoreListVO> StoreInfoSelect(String storeIds) {
        List<StoreListVO> list = new ArrayList<>();
        String sql = null;
        System.out.println(("식당 순서 : " +storeIds));
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            if(storeIds.equals(storeIds)) sql = "SELECT * FROM STORE_TB";
            else sql ="SELECT * FROM STORE_TB WHERE store_id = " + "'" + storeIds + "'";
//            stmt.executeQuery("SET NAMES 'UTF8'");
            rs = stmt.executeQuery(sql);


            while (rs.next()) {
                String store_id = rs.getString("STORE_ID");
                String storeType = rs.getString("STORE_TYPE");
                String storeTag = rs.getString("STORE_TAG");
                String storeName = rs.getString("STORE_NAME");
                String storeRegion = rs.getString("STORE_REGION");
                String storeAddr = rs.getString("STORE_ADDR");
                String storeTel = rs.getString("STORE_TEL");
                String storeTime = rs.getString("STORE_TIME");
                String storeParking = rs.getString("STORE_PARKING");
                String storeImg01 = rs.getString("STORE_IMG_01");
                String storeImg02 = rs.getString("STORE_IMG_02");
                String storeImg03 = rs.getString("STORE_IMG_03");
                String storeImg04 = rs.getString("STORE_IMG_04");
                String storeImg05 = rs.getString("STORE_IMG_05");

                StoreListVO vo = new StoreListVO();
                vo.setStoreId(store_id);
                vo.setStoreType(storeType);
                vo.setStoreTag(storeTag);
                vo.setStoreName(storeName);
                vo.setStoreRegion(storeRegion);
                vo.setStoreAddr(storeAddr);
                vo.setStoreTel(storeTel);
                vo.setStoreTime(storeTime);
                vo.setStoreParking(storeParking);
                vo.setStoreImg01(storeImg01);
                vo.setStoreImg02(storeImg02);
                vo.setStoreImg03(storeImg03);
                vo.setStoreImg04(storeImg04);
                vo.setStoreImg05(storeImg05);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    // 식당 정보 삭제(Delete)
    public boolean deleteStoreInfo(int store_Id) {
        int result = 0;
        String sql = "DELETE FROM STORE_TB WHERE store_id = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, store_Id);
            result = pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        if (result == 1) return  true;
        else return false;
    }


    // 식당 정보 수정(update)
    public boolean updateStoreInfo(String store_id, String store_type, String store_tag, String store_name, String store_region, String store_addr, String store_tel, String store_time,  String store_parking, String store_img01, String store_img02, String store_img03, String store_img04, String store_img05) {
        int result =0;
        String sql = "UPDATE STORE_TB SET " +
                "store_type = ?, store_tag = ?, store_name = ?, " +
                "store_region = ?, store_addr = ?, store_tel = ?, " +
                "store_time = ?, store_parking = ?, " +
                "store_img01 = ?, store_img02 = ?, store_img03 = ?, " +
                "store_img04 = ?, store_img05 = ? " +
                "WHERE store_id = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, store_type);
            pStmt.setString(2, store_tag);
            pStmt.setString(3, store_name);
            pStmt.setString(4, store_region);  // store_region 추가
            pStmt.setString(5, store_addr);    // store_addr 추가
            pStmt.setString(6, store_tel);     // store_tel 추가
            pStmt.setString(7, store_time);    // store_time 추가
            pStmt.setString(8, store_parking);
            pStmt.setString(9, store_img01);
            pStmt.setString(10, store_img02);
            pStmt.setString(11, store_img03);
            pStmt.setString(12, store_img04);
            pStmt.setString(13, store_img05);
            pStmt.setString(14, store_id);

            result = pStmt.executeUpdate();
            System.out.println("식당 정보 수정 DB 결과 확인 : " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }


    // 식당 정보 추가(insert)
    public boolean insertStoreInfo(String store_id, String store_type, String store_tag, String store_name, String store_parking, String store_img01, String store_imt02, String store_img03, String store_img04, String store_img05) {
        int result = 0;
        String sql = "INSERT INTO STORE_TB (storeid, store_type, store_tag, store_name, " +
                "store_region, store_addr, store_tel, store_time, " +
                "store_parking, store_img01, store_img02, store_img03, " +
                "store_img04, store_img05) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, store_id);
            pStmt.setString(2, store_type);
            pStmt.setString(3, store_tag);
            pStmt.setString(4, store_name);
            pStmt.setString(5, store_parking);
            pStmt.setString(6, store_img01);
            pStmt.setString(7, store_imt02);
            pStmt.setString(8, store_img03);
            pStmt.setString(9, store_img04);
            pStmt.setString(10, store_img05);

            result = pStmt.executeUpdate();
            System.out.println("식당 정보 추가 DB 결과 확인 : " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }
}
