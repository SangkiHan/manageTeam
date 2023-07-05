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

#### UserController 사용자관리 API 
+   /api/user/v1/save 회원가입 및 회원수정 API
+   /api/user/v1/findUserInfo 사용자를 상세 조회 API
+   /api/user/v1/existsByRsdntRgnmb 주민등록번호로 이미 등록된 사용자인지 체크 API

#### MemberController 팀원관리 API 
+   /api/member/v1/save 팀원 저장 및 수정 API
+   /api/member/v1/status 팀원의 상태를 변경 API
+   /api/member/v1/findById 팀원 정보를 상세 조회 API
+   /api/member/v1/findAll 팀원들의 목록 조회 API
+   /api/member/v1/existsByRsdntRgnmb 주민번호로 등록되어있는 팀원인지 체크 API

#### TeamController 팀관리 API 
+   /api/team/v1/save 팀 저장 및 수정 API
+   /api/team/v1/status 팀 활성화 상태 변경 API
+   /api/team/v1/findById 팀정보 상세 조회 API
+   /api/team/v1/findAll 팀 목록 조회 API

#### GymController 팀관리 API 
+   /api/gym/v1/save 체육관 저장 및 수정 API
+   /api/gym/v1/findById 체육관 정보 상세 조회 API

#### ReservationController 체육관 예약등록 API 
+   /api/reservation/v1/save 체육관 예약 저장 및 수정 API
+   /api/reservation/v1/cancel 체육관 예약 취소 API
+   /api/reservation/v1/findAll 등록된 체육관 예약목록을 조회 API

#### ReservationTeamController 팀 예약 관리 API 
+   /api/reservationTeam/v1/save 특정팀의 체육관 예약 API
+   /api/reservationTeam/v1/findAllbyTeam 특정팀이 예약한 체육관 목록을 조회 API
