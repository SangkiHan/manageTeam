### 농구팀 관리 및 체육관 시스템
queryDsl 연습 및 실습용 프로젝트이다.

------------
### 개발환경
    
+   IDE : Eclipse
+   Spring boot 2.7.10
+	Java : jdk-11.0.17
+   JPA
+   Mysql 8

------------
### API

------------
#### MemberController 팀원관리 API 
+   /api/member/v1/save 팀원 저장 및 수정 API
+   /api/member/v1/status 팀원의 상태를 변경 API
+   /api/member/v1/findById 팀원 정보를 상세 조회 API
+   /api/member/v1/findAll 팀원들의 목록 조회 API
+   /api/member/v1/existsByRsdntRgnmb 주민번호로 등록되어있는 팀원인지 체크 API
