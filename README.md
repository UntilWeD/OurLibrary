# 우리의 도서관

## 1. 프로젝트 소개
- 프로젝트 이름 : 우리의 도서관
- 프로젝트 설명 : 기본적인 커뮤니티 웹사이트로서 사용자들끼리 게시글과 댓글, 좋아요를 통해 서로 소통하며 카테고리에 따라 여러 다양한 경험을 할 수 있게 합니다.


### 개발 팀 
- 김진우 


### 개발 기간
- 2025.07.01 ~ 2024.12.12


### 사용 기술 
- JSP/Servlet
- JavaScript
- BootStrap5
- JDBC
- DB : MySQL

### ERD
<img width="1750" height="1122" alt="Image" src="https://github.com/user-attachments/assets/5196576a-3860-4688-9c6a-c21770d1ffcd" />


## 2. 기능


### 유저
1) 유저 관리는 세션으로 클라이언트가 로그인하지 않았다면 로그인화면, 했다면 웹사이트의 홈화면을 보여줍니다.
2) 유저는 회원가입, 로그인, 로그아웃, 유저정보 수정, 회원탈퇴 등 여러 기본적인 기능을 사용할 수 있습니다. 
<br/>
<br/>
<br/>
1) 유저 관리는 세션으로 클라이언트가 로그인하지 않았다면 로그인화면, 했다면 웹사이트의 홈화면을 보여줍니다.
- 로그인 전 <br/>
<img width="2095" height="1757" alt="Image" src="https://github.com/user-attachments/assets/f88c165a-685c-477a-9cf1-b409986750cc" />
<br/>
<br/>
- 로그인 후<br/>
<img width="3070" height="1942" alt="Image" src="https://github.com/user-attachments/assets/62744231-c292-433e-8278-a17f7c5f045f" />
<br/>
<br/>
<br/>
2) 유저는 이외에도 로그아웃, 유저정보 수정, 회원탈퇴 등 여러 기본적인 기능을 사용할 수 있습니다. 
-회원가입 <br/>
<img width="1622" height="1547" alt="Image" src="https://github.com/user-attachments/assets/aee534c4-2e67-4739-8b03-b32c8315937b" />
<br/>
<br/>
-유저정보 수정 <br/>
<img width="3130" height="1742" alt="Image" src="https://github.com/user-attachments/assets/31c54b23-2ee5-42e8-a005-ca299655e284" />
<br/>
<br/>
-회원탈퇴 <br/>
<img width="1715" height="1110" alt="Image" src="https://github.com/user-attachments/assets/f1e8ff3b-1c0a-4eec-8571-004129a8a0e6" />
<br/>
<br/>
<br/>

### 커뮤니티
<br/>
#### 카테고리 <br/>
1) 기본적으로 큰 카테고리들이 나오고, 이를 부모로 삼는 카테고리들이 존재합니다. 이러한 많은 카테고리들이 있기에 검색어를 사용하여 검색할 수 있습니다.
<br/>
2) 이런식으로 카테고리로 들어간다면 유저가 이동한 카테고리 정보를 로컬스토리지에 저장하고 이를 바탕으로 자주 가는 카테고리에서 로컬스토리지로부터 값을 꺼내 볼 수 있습니다.
<br/>
<br/>
<br/>
1) 기본적으로 큰 카테고리들이 나오고, 이를 부모로 삼는 카테고리들이 존재합니다. 이러한 많은 카테고리들이 있기에 검색어를 사용하여 검색할 수 있습니다.
<br/>
-카테고리 홈 <br/>
<img width="2305" height="1792" alt="카테고리홈" src="https://github.com/user-attachments/assets/ad34bade-f668-41d1-a150-1481cb506c06" />
<br/>
<br/>
-카테고리 검색 <br/>
<img width="2260" height="1702" alt="카테고리검색" src="https://github.com/user-attachments/assets/19a586d7-3d69-460d-8869-dd099d965a92" />
<br/>
<br/>
<br/>
2) 이런식으로 카테고리로 들어간다면 유저가 이동한 카테고리 정보를 로컬스토리지에 저장하고 이를 바탕으로 자주 가는 카테고리에서 로컬스토리지로부터 값을 꺼내 볼 수 있습니다.<br/>
- 카테고리 홈에서 이동 <br/>
<img width="2352" height="965" alt="카테고리이동_1" src="https://github.com/user-attachments/assets/b10238a7-b6d3-4f09-92c2-4378132958c3" />
<br/>
<br/>
- 메인 홈에서 출력 (카테고리이름 - 카테고리 조회횟수) <br/>
<img width="1520" height="322" alt="카테고리이동_2" src="https://github.com/user-attachments/assets/78cc1949-5f2e-446b-8b00-ab7222bbaed1" />
<br/>
<br/>
<br/>

#### 게시글 
<br/>


1) 글들을 리스트로 확인할 수 있고 20개가 넘어간다면 다음 페이지를 보여줍니다. <br/>
2) 제목과 작성자에 따라 게시글을 검색할 수 있습니다. <br/>
3) 사용자는 게시글을 작성할 수 있고 현재 로그인한 계정이 작성한 포스트만 수정 및 삭제할 수 있습니다. <br/>
4) 게시글에는 파일을 올릴 수 있고 이 파일들은 다른 사용자들이 다운로드 할 수 있습니다.<br/>
5) 파일들은 uuid를 사용해서 다른 이름으로 저장되고 각 포스트 id로 구분되어 저장됩니다. <br/>
<br/>
<br/>
<br/>


1) 글들을 리스트로 확인할 수 있고 20개가 넘어간다면 다음 페이지를 보여줍니다.
2) 
<br/>

<img width="2432" height="1997" alt="게시글리스트" src="https://github.com/user-attachments/assets/1bfe9bb0-cfb9-41ee-bbce-79c24dfbd6da" />
<br/>
<br/>
3) 제목과 작성자에 따라 게시글을 검색할 수 있습니다. 
<br/>
<img width="2385" height="992" alt="게시글동적검색" src="https://github.com/user-attachments/assets/582f0d7b-1294-4317-881e-0aef67d71bec" />
<br/>
<br/>

4) 사용자는 게시글을 작성할 수 있고 현재 로그인한 계정이 작성한 포스트만 수정 및 삭제할 수 있습니다.  <br/>
<img width="2440" height="1525" alt="게시글수정삭제" src="https://github.com/user-attachments/assets/ecbb70e1-2565-4d46-82ef-082ed8ffc35a" />
<br/>
<br/>
- 기존에 올라와있는 파일 수정 및 삭제 가능 <br/>
<img width="2302" height="1682" alt="게시글파일수정" src="https://github.com/user-attachments/assets/c1913313-006e-48d4-b4a5-8565e92f1b46" />
<br/>
<br/>
4) 게시글에는 파일을 올릴 수 있고 이 파일들은 다른 사용자들이 다운로드 할 수 있습니다. <br/>
- 수정 중 파일업로드 <br/>
<img width="2455" height="1525" alt="게시글파일업로드" src="https://github.com/user-attachments/assets/a5bf6f4d-b870-4af2-8270-87ad218d7bcf" />
<br/>
<br/>
- 파일 다운로드  <br/>
<img width="2405" height="1150" alt="게시글파일다운" src="https://github.com/user-attachments/assets/54cfa192-56b9-4547-97b0-aaae397ffdf5" />
<br/>
<br/>
<br/>
5) 파일들은 uuid를 사용해서 다른 이름으로 저장되고 각 포스트 id로 구분되어 저장됩니다.  <br/>
<img width="1695" height="870" alt="게시글물리파일저장" src="https://github.com/user-attachments/assets/7f0ebcdb-6aa9-46db-ba1e-4c9e312af6b0" />
<br/>
<br/>

#### 댓글

<br/>
1) 게시글마다 각 댓글이 있고 사용자는 해당 게시글에 대하여 작성 할 수 있습니다. <br/>
2) 사용자는 현재 로그인한 계정의 id와 작성된 댓글의 id를 비교하여 수정 및 삭제를 할 수 있습니다.
<br/>
<br/>
1) 게시글마다 각 댓글이 있고 사용자는 해당 게시글에 대하여 작성 할 수 있습니다. <br/>

- 댓글 작성전 <br/>
<img width="2420" height="1647" alt="댓글작성_1" src="https://github.com/user-attachments/assets/df17d930-de33-414e-82b8-5d5349dfa5df" />
<br/>
<br/>
- 댓글 작성 후 <br/>
<img width="2465" height="1965" alt="댓글작성_2" src="https://github.com/user-attachments/assets/02334a12-c53c-4c48-aeb7-33d84b3bcaa5" />
<br/>
<br/>
- 댓글 페이지 <br/>
<img width="2347" height="1407" alt="댓글페이지" src="https://github.com/user-attachments/assets/fd26dae2-d511-4b27-9eba-a1f33b74ba1d" />
<br/>
<br/>
<br/>


2) 사용자는 현재 로그인한 계정의 id와 작성된 댓글의 id를 비교하여 수정 및 삭제를 할 수 있습니다. <br/>

-댓글수정 <br/>
<img width="2305" height="685" alt="댓글수정" src="https://github.com/user-attachments/assets/497c2ee3-d6b8-4832-b2af-3028626f323c" />
<br/>
<br/>
<br/>

#### 좋아요 
<br/>

1) 사용자는 하루마다 한 게시글에 좋아요 및 싫어요를 누를 수 있습니다. <br/>
<br/>
<br/>

1) 사용자는 하루마다 한 게시글에 좋아요 및 싫어요를 누를 수 있습니다. <br/>
<img width="2297" height="1730" alt="게시글좋아요" src="https://github.com/user-attachments/assets/7100de8a-632f-45ae-a4af-230503464d6b" />
<br/>
<br/>
<br/>

### 백엔드

#### 트랜잭션

1) 게시글을 작성하면서 첨부파일을 업로드하게되면 데이터베이스에 기록되게 되는데 만약 게시글을 삭제 하던 도중 오류가 생겨 첨부파일관련 db를 삭제하였지만 서버 컴퓨터의 물리 파일들은 삭제가 되지 않는 경우가 있는데 
2) 이를 해결하기 위해 Connection을 서비스단에서 불러와서 같은 커넥션으로 다른 DAO 메서드들을 호출하여 하나의 커넥션만 사용하도록 하였습니다. 이외에도 여러 DAO 메서드들을 사용하며 데이터의 동시성에 문제가 생길법한 메서드에 트랜잭션을 적용하였습니다.
<br/>
<br/>
1) 게시글을 작성하면서 첨부파일을 업로드하게되면 데이터베이스에 기록되게 되는데 만약 게시글을 삭제 하던 도중 오류가 생겨 첨부파일관련 db를 삭제하였지만 서버 컴퓨터의 물리 파일들은 삭제가 되지 않는 경우가 있는데 
 이를 해결하기 위해 Connection을 서비스단에서 불러와서 같은 커넥션으로 다른 DAO 메서드들을 호출하여 하나의 커넥션만 사용하도록 하였습니다. 이외에도 여러 DAO 메서드들을 사용하며 데이터의 동시성에 문제가 생길법한 메서드에 트랜잭션을 적용하였습니다.
<img width="2300" height="685" alt="트랜잭션" src="https://github.com/user-attachments/assets/b6649dfe-c802-41c4-b743-ef1a315a8a8c" />
<br/>
<br/>
<br/>

#### 예외처리

1) 예외들은 서블릿계층에서 처리하는 것이 역할상 옳다고 생각하여 예외가 들어온다면 예외의 종류에 따라 메시지를 담고 에러페이지로 이동합니다. 
- 비즈니스 상 예외는 페이지에서 안내메시지로 뜨게 할려 했지만 시간상 오래 걸릴것같아 중요한것만 적용하였습니다.
<img width="2862" height="715" alt="예외처리_1" src="https://github.com/user-attachments/assets/a66fa61f-5b39-4b73-aa87-59353c2132e7" />
<br/>
<br/>
- ServletException으로 공통화 처리된건 필터에서 처리
<img width="2132" height="1210" alt="예외처리_2" src="https://github.com/user-attachments/assets/4f08316c-f3ce-4a77-adf6-01e56aefed4a" />
<br/>
<br/>
<br/>


## 3. 개발회고 

<br/>

 기존에 스프링 부트만 사용하다보니 많은 기능들이 편리하고 자동화되거나 애너테이션으로 처리해서 안에 내부가 어떻게 작동하고 있는 지 잘 몰랐다. 어떠한 기능이 어떻게 하여 편리하게 된건지도 잘 몰랐기에 이번에 JSP/Servlet, 톰캣을 사용해서
익혀보자는 느낌으로 프로젝트를 시작하게 되었다. 또한 테이블에 집계속성 (commentCount, likeCount...)을 넣으면 어떤식으로 힘들어지는 지도 테스트하고 싶어서 erd를 그렇게 설계하였다. 
<br/>

 진행하다보니 나름 jsp/servlet으로 웹사이트의 값을 동적으로 바꾸는게 생각보다 흥미로웠다. 특히 늘 웹을 공부하다보면 정적 웹사이트와 동적 웹사이트를 얘기하는게 이해가 되지 않았는데 이번에 진행하면서 모델을 jsp에 보내면서 당연시하던
동적인 웹사이트가 얼마나 힘든것이였는지 느끼게 되었다. 
<br/>

 또한 스프링이 당연히 해주었던 DI와 트랜잭션관리의 부재가 뼈아프게 느껴졌다. 특히 스프링 프레임워크에서 싱글톤으로 의존성주입을 해줬던 것과 달리 각각의 서블릿마다 서비스, 리포지토리를 주입해주어야 했고 만약 기능을 구현하던 도중 수정사항이 
발생한다면 각 서블릿마다 초기화부분을 일일히 수정해야 했다. 또한 트랜잭션도 서비스단위에서 트랜잭션을 관리할려면 데이터베이스에 대한 커넥션을 가져와야 했기에 계층간 경계도 모호해졌다. 또 트랜잭션이 필요없는 메서드들이 있거나 하나의 메서드만 쓰이는 경우에
커넥션을 쓴다면 이는 낭비이기에 다형성을 사용한 여러 메서드들을 만들어야 했다. 
<br/>

하지만 점점 개발하면서 애정이 들어가 여러 사용자경험을 올리는 기능(자주 가는 카테고리, 웹이동 없이 동적으로 불러오기..)등을 만들었고 다른 기능들도 생각이 났지만 아직 JSP/Servlet이기에 더 추가하면 본래 프로젝트를 시작하려던 목적에 어긋나
여기서 마치게 되었다. 후에 이 프로젝트를 바탕으로 JSP + 스프링 프레임워크로 업그레이드하여 진행하여 여러 기능들과 복잡한 기능들을 추가하여 성능적으로도 깊이 파볼려 한다.  후에는 리액트 + 스프링 부트로 실서비스를 운영하고 싶기도 하다.
<br/>

