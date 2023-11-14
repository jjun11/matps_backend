package com.kh.matps.controller;



import com.kh.matps.dao.StoreListDAO;
import com.kh.matps.vo.StoreListVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
// 3000번포트로 넘어갔다가 8011번포트로 넘어가면 에러인데 이걸 풀기위해 CrossOrigin으로 선언하여 풀어줌
@RestController
// restful-API ??? 컨트롤러


@RequestMapping("/stores")

public class StoreListController {

    @GetMapping("/storename")
    public String getHello() {
        return "GET  방식에 대해서 응답 확인.";
    }
    @GetMapping("/board/{pageNo}/{commNo}")
    public String getVariable(@PathVariable String pageNo, @PathVariable String commNo) {
        return "GET 방식 : " + pageNo + "/" + commNo;
    }

    @PostMapping ("/search")
    //Param 값으로 값을 매핑해오기.
    public String searchStores(
            @RequestParam String store_id,
            @RequestParam String store_type,
            @RequestParam String store_tag,
            @RequestParam String store_name,
            @RequestParam String store_parking)

    {
        return "번호 " + store_id +  "타입 : " + store_type + "," + "태그 : " + store_tag + "상호명 : " +  store_name
                + store_parking + "주차유무 : ";
    }


    @GetMapping("/StoreInfo")
    public ResponseEntity<List<StoreListVO>> getStoreList(@RequestParam List<String> storeIds) {
        List<StoreListVO> list = new ArrayList<>();
        StoreListDAO dao = new StoreListDAO();

        // STORE_ID 목록을 처리합니다 (유효성 검사를 추가할 수 있습니다).
        for (String STORE_ID : storeIds) {
            List<StoreListVO> storeInfo = dao.StoreInfoSelect(STORE_ID);
            list.addAll(storeInfo);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }




//
//    // 식당 정보 삭제
//    @DeleteMapping("/deleteStoreInfo/{store_id}")
//    public ResponseEntity<String> deleteStoreInfo(@PathVariable int store_id) {
//        boolean result = StoreListDAO.deleteStoreInfo(store_id);
//        if (result) {
//            return new ResponseEntity<>("Success", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // 식당 정보 수정
//    @PutMapping("/updateStoreInfo/{store_id}")
//    public ResponseEntity<String> updateStoreInfo(
//            @PathVariable String store_id,
//            @RequestParam String store_type,
//            @RequestParam String store_tag,
//            @RequestParam String store_name,
//            @RequestParam String store_region,
//            @RequestParam String store_addr,
//            @RequestParam String store_tel,
//            @RequestParam String store_time,
//            @RequestParam String store_parking,
//            @RequestParam String store_img01,
//            @RequestParam String store_img02,
//            @RequestParam String store_img03,
//            @RequestParam String store_img04,
//            @RequestParam String store_img05) {
//        boolean result = StoreListDAO.updateStoreInfo(
//                store_id, store_type, store_tag, store_name,
//                store_region, store_addr, store_tel, store_time,
//                store_parking, store_img01, store_img02, store_img03,
//                store_img04, store_img05);
//
//        if (result) {
//            return new ResponseEntity<>("Success", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // 식당 정보 추가
//    @PostMapping("/insertStoreInfo")
//    public ResponseEntity<String> insertStoreInfo(
//            @RequestParam String store_id,
//            @RequestParam String store_type,
//            @RequestParam String store_tag,
//            @RequestParam String store_name,
//            @RequestParam String store_parking,
//            @RequestParam String store_img01,
//            @RequestParam String store_img02,
//            @RequestParam String store_img03,
//            @RequestParam String store_img04,
//            @RequestParam String store_img05) {
//        boolean result = storeListDAO.insertStoreInfo(
//                store_id, store_type, store_tag, store_name,
//                store_parking, store_img01, store_img02, store_img03,
//                store_img04, store_img05);
//
//        if (result) {
//            return new ResponseEntity<>("Success", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}