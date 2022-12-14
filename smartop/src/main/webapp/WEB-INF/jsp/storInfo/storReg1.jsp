<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>  
<meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="keywords" content="">
        <meta name="title" content="DASAN">
        <meta name="description" content="">
        <meta property="og:title" content="">
        <meta property="og:description" content="">
        <title>매장 등록</title>
    
        <%-- <jsp:include page="../include/include.jsp" />   --%>
<div class="flex">
                <h2 class="ti">매장 추가</h2>
                <a href="#" class="ml-auto btn btn-sz1 bgColor4" onclick="alertPop();">저장</a>
            </div>

            <div class="cardCont">
                <h3>기본정보</h3>
                <div class="formbox flex">
                    <div class="wid49p">
                        <label for="brandNm">브랜드명</label>
                        <div class="form-inp"><input type="text" class="inp" id="brandNm" value="맥도날드"></div>
                    </div>
                    <div class="wid49p ml-auto">
                        <label for="strNm">매장명</label>
                        <div class="form-inp"><input type="text" class="inp" id="strNm" value="선릉점"></div>
                    </div>
                </div>

                <div class="formbox flex">
                    <div class="wid49p">
                        <label for="brandNm">브랜드명</label>
                        <div class="form-sel">
                            <select id="sel001" class="select">
                                <option value="">맥도날드</option>
                            </select>
                        </div>
                    </div>
                    <div class="wid49p ml-auto">
                        <label for="brandNm">매장명</label>
                        <div class="form-sel">
                            <select id="sel001" class="select">
                                <option value="">맥도날드</option>
                            </select>
                        </div>
                    </div>
                </div>

                <!-- 노희승 부장님 추가 요청사항 2022.12.04 -->
                <div class="formbox flex">
                    <div class="wid49p">
                        <label for="prsdNm">대표자명</label>
                        <div class="form-inp"><input type="text" class="inp" id="prsdNm" value="제이미"></div>
                    </div>
                    <div class="wid49p ml-auto">
                        <label for="prsdCl">전화번호</label>
                        <div class="form-inp"><input type="text" class="inp" id="prsdCl" value="000-0000-0000"></div>
                    </div>
                </div>

                <div class="formbox">
                    <label for="prsdEml">이메일</label>
                    <div class="form-inp"><input type="text" class="inp" id="prsdEml" value="info@gmail.com"></div>
                </div>

                <div class="formbox">
                    <label for="strAddr">주소</label>
                    <div class="form-inp"><input type="text" class="inp mb5" id="strAddr" placeholder="주"></div>
                    <div class="form-inp"><input type="text" class="inp mb5" placeholder="도시명"></div>
                    <div class="form-inp"><input type="text" class="inp" placeholder="우편번호"></div>
                </div>

                <div class="formbox">
                    <label>제품 유형</label>                    
                    <!-- 체크박스 영역 - label for과 input id 값이 같아야됨 [S]-->
                    <span class="chkbox mr30">
                        <input type="checkbox" id="th001">
                        <label for="th001" class="th_chk">매장</label>
                    </span>
                    <!-- 체크박스 영역 [E]-->
                    <span class="chkbox mr30">
                        <input type="checkbox" id="th002" checked>
                        <label for="th002" class="th_chk">포장</label>
                    </span>
                    <span class="chkbox mr30">
                        <input type="checkbox" id="th003" checked>
                        <label for="th003" class="th_chk">웹오더</label>
                    </span>
                    <span class="chkbox mr30">
                        <input type="checkbox" id="th004" checked>
                        <label for="th004" class="th_chk">디지털 사이니지</label>
                    </span>
                </div>

                <div class="formbox">
                    <label for="">매장상태</label>
                    <div class="form-sel">
                        <select id="sel001" class="select">
                            <option value="">일반 (영업중)</option>
                        </select>
                    </div>
                </div>

                <div class="formbox flex">
                    <div class="wid49p">
                        <label for="strTax">판매 세금</label>
                        <div class="form-inp"><input type="text" class="inp" id="strTax" value="10%"></div>
                    </div>
                    <div class="wid49p ml-auto">
                        <label for="brandNm">판매 세금 영수증 표기여부</label>
                        <div class="form-sel">
                            <select id="sel001" class="select">
                                <option value="">사용</option>
                            </select>
                        </div>
                    </div>
                </div>

                <!-- 노희승 부장님 추가 요청사항 2022.12.04 -->
                <div class="formbox flex">
                    <div class="wid49p">
                        <label for="brandNm">판매 세금</label>
                        <div class="form-sel">
                            <select id="sel001" class="select">
                                <option value="">10%</option>
                            </select>
                        </div>
                    </div>
                    <div class="wid49p ml-auto">
                        <label for="brandNm">판매 세금 영수증 표기여부</label>
                        <div class="form-sel">
                            <select id="sel001" class="select">
                                <option value="">사용</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="formbox">
                    <label>라이센싱 발급 리스트</label>
<!-- 버튼 관련하여 협의중......                     -->
                    <div class="mb10">
                        <select id="sel002" class="select wid15p">
                            <option value="">포스</option>
                        </select>

                        <select id="sel002" class="select">
                            <option value="">수량</option>
                        </select>
                    </div>

                    <div>
                        <select id="sel002" class="select wid15p">
                            <option value="">포스</option>
                        </select>

                        <select id="sel002" class="select">
                            <option value="">수량</option>
                        </select>

                        <button type="button" class="btn btn-sz3 bgColor7">추가</button>
                    </div>

                </div>                
            </div>

<!-- 팝업창 띄울대 딤드참 뒷 배경 -->
<div class="mask"></div>
<script>
    $("#barndSave").click(function(){
        $("#pop1").show();
        // dimed 창 띄우는 함수
        dimmedWindow()
    });
    </script>
</body>
</html>