-- Members
INSERT INTO members(member_id, member_password, gender, birth, name, phone_number, create_at, modify_at, role_name, enable) VALUES ("lee     ID", "leePa     ssword", "남자", "1998-01-01", "조나단", "010-1212-3434", NOW(), NOW(), "ROLE_USER", 1);
INSERT INTO members(member_id, member_password, gender, birth, name, phone_number, create_at, modify_at, role_name, enable) VALUES ("leeID", "leePassword", "남자", "1998-01-01", "조나단", "010-1212-3434", NOW(), NOW(), "ROLE_USER", 1);
INSERT INTO members(member_id, member_password, gender, birth, name, phone_number, create_at, modify_at, role_name, enable) VALUES ("patID", "patPassword", "여자", "2000-01-01", "파트리샤", "010-5656-7878", NOW(), NOW(), "ROLE_USER", 1);
INSERT INTO members(member_id, member_password, gender, birth, name, phone_number, create_at, modify_at, role_name, enable) VALUES ("kimID", "kimPassword", "남자", "2002-01-01", "김찬영", "010-1357-2468", NOW(), NOW(), "ROLE_USER", 1);
INSERT INTO members(member_id, member_password, gender, birth, name, phone_number, create_at, modify_at, role_name, enable) VALUES ("qwrfeq", "qwrfeq", "남자", "1910-10-01", "이신", "010-1010-1010", NOW(), NOW(), "ROLE_USER", 1);
INSERT INTO members(member_id, member_password, gender, birth, name, phone_number, create_at, modify_at, role_name, enable) VALUES ("reqw", "$2a$12$XxN07ZjCSK9tk9zSxpOnZOR/hOAISz1cipobKShGK.sLI90eERZLi", "남자", "1910-10-02", "마법사", "010-1012-1012", NOW(), NOW(), "ROLE_ADMIN", 1);
INSERT INTO members(member_id, member_password, gender, birth, name, phone_number, create_at, modify_at, role_name, enable) VALUES ("qoddl2", "qoddl2", "여자", "1910-10-03", "김뱅", "010-1013-1013", NOW(), NOW(), "ROLE_USER", 1);
INSERT INTO members(member_id, member_password, gender, birth, name, phone_number, create_at, modify_at, role_name, enable) VALUES ("Qkddl3", "Qkddl3", "여자", "1950-10-01", "박빵", "010-1015-1015", NOW(), NOW(), "ROLE_USER", 1);
INSERT INTO members(member_id, member_password, gender, birth, name, phone_number, create_at, modify_at, role_name, enable) VALUES ("seojun04", "jun0985", "남자", "2004-06-09", "김서준", "010-5268-9853", NOW(), NOW(), "ROLE_USER", 1);
INSERT INTO members(member_id, member_password, gender, birth, name, phone_number, create_at, modify_at, role_name, enable) VALUES ("jiwoo98", "qwerty98", "여자", "1998-05-05", "박지우", "010-5789-5236", NOW(), NOW(), "ROLE_USER", 1);
INSERT INTO members(member_id, member_password, gender, birth, name, phone_number, create_at, modify_at, role_name, enable) VALUES ("ddoddo11", "zxcv89", "남자", "2001-04-28", "최도윤", "010-4562-7536", NOW(), NOW(), "ROLE_USER", 1);

-- Categories
INSERT INTO categories(name) VALUES ("액션");
INSERT INTO categories(name) VALUES ("애니메이션");
INSERT INTO categories(name) VALUES ("공포");
INSERT INTO categories(name) VALUES ("스릴러");
INSERT INTO categories(name) VALUES ("코미디");
INSERT INTO categories(name) VALUES ("범죄");

-- Movies
INSERT INTO movies(movie_name, poster) VALUES ("내부자들", "/images/insiders.png");
INSERT INTO movies(movie_name, poster) VALUES ("육사오", "/images/sixfourfive.png");
INSERT INTO movies(movie_name, poster) VALUES ("범죄도시2", "/images/crimecity2.png");
INSERT INTO movies(movie_name, poster) VALUES ("극한직업", "/images/extreamjob.png");
INSERT INTO movies(movie_name, poster) VALUES ("존윅4", "/images/JohnWick4.png");
INSERT INTO movies(movie_name, poster) VALUES ("분노의 질주 홉스&쇼", "/images/분노의질주.png");
INSERT INTO movies(movie_name, poster) VALUES ("겨울왕국", "/images/겨울왕국.png");
INSERT INTO movies(movie_name, poster) VALUES ("코코", "/images/코코.png");
INSERT INTO movies(movie_name, poster) VALUES ("컨저링", "/images/the_conjuring.png");
INSERT INTO movies(movie_name, poster) VALUES ("어스", "/images/us.png");
INSERT INTO movies(movie_name, poster) VALUES ("메이즈 러너", "/images/the_maze_runner.png");
INSERT INTO movies(movie_name, poster) VALUES ("유주얼 서스펙트", "/images/usual_suspects.png");

-- Theaters
INSERT INTO theaters VALUES(1, "1관");
INSERT INTO theaters VALUES(2, "2관");
INSERT INTO theaters VALUES(3, "3관");

-- Schedules
INSERT INTO schedules VALUES(1, "2023/03/15", "09:00",1,1);
INSERT INTO schedules VALUES(2, "2023/03/15", "12:00",3,1);
INSERT INTO schedules VALUES(3, "2023/03/15", "15:00",7,1);
INSERT INTO schedules VALUES(4, "2023/03/15", "18:00",9,1);
INSERT INTO schedules VALUES(5, "2023/03/15", "21:00",10,1);

-- Seats
INSERT INTO seats(schedules_seq, classification, number) VALUES(1, "A", 1);
INSERT INTO seats(schedules_seq, classification, number) VALUES(1, "A", 2);
INSERT INTO seats(schedules_seq, classification, number) VALUES(1, "A", 3);
INSERT INTO seats(schedules_seq, classification, number) VALUES(1, "A", 4);
INSERT INTO seats(schedules_seq, classification, number) VALUES(1, "B", 1);
INSERT INTO seats(schedules_seq, classification, number) VALUES(1, "B", 2);
INSERT INTO seats(schedules_seq, classification, number) VALUES(1, "B", 3);
INSERT INTO seats(schedules_seq, classification, number) VALUES(1, "B", 4);
INSERT INTO seats(schedules_seq, classification, number) VALUES(1, "C", 1);
INSERT INTO seats(schedules_seq, classification, number) VALUES(1, "C", 2);
INSERT INTO seats(schedules_seq, classification, number) VALUES(1, "C", 3);
INSERT INTO seats(schedules_seq, classification, number) VALUES(1, "C", 4);
INSERT INTO seats(schedules_seq, classification, number) VALUES(2, "A", 1);
INSERT INTO seats(schedules_seq, classification, number) VALUES(2, "A", 2);
INSERT INTO seats(schedules_seq, classification, number) VALUES(2, "A", 3);
INSERT INTO seats(schedules_seq, classification, number) VALUES(2, "A", 4);
INSERT INTO seats(schedules_seq, classification, number) VALUES(2, "B", 1);
INSERT INTO seats(schedules_seq, classification, number) VALUES(2, "B", 2);
INSERT INTO seats(schedules_seq, classification, number) VALUES(2, "B", 3);
INSERT INTO seats(schedules_seq, classification, number) VALUES(2, "B", 4);
INSERT INTO seats(schedules_seq, classification, number) VALUES(2, "C", 1);
INSERT INTO seats(schedules_seq, classification, number) VALUES(2, "C", 2);
INSERT INTO seats(schedules_seq, classification, number) VALUES(2, "C", 3);
INSERT INTO seats(schedules_seq, classification, number) VALUES(2, "C", 4);

-- Comment
INSERT INTO comments VALUES(1, 1, 1, "정말 재밌어요. 강추합니다!", NOW(), NOW());
INSERT INTO comments VALUES(2, 2, 1, "정말 강추해요. 재밌습니다!", NOW(), NOW());
INSERT INTO comments VALUES(3, 3, 1, "강추 정말입니다. 재밌어요!", NOW(), NOW());
INSERT INTO comments VALUES(4, 4, 2, "재미 강추입니다. 정말로요!", NOW(), NOW());
INSERT INTO comments VALUES(5, 5, 3, "재미 정말입니다. 강추에요!", NOW(), NOW());

-- Payments
INSERT INTO payments values(1,3,6,"1234123412341234",1,1,1,6000,NOW());

-- Seat_reservations
INSERT INTO seat_reservations VALUES(1, 3, true);
INSERT INTO seat_reservations VALUES(2, 2, true);
INSERT INTO seat_reservations VALUES(3, 1, true);

-- Enrollment_Seats
INSERT INTO enrollment_seats(payments_seq, seats_seq) VALUES(1, 3);
INSERT INTO enrollment_seats(payments_seq, seats_seq) VALUES(1, 2);
INSERT INTO enrollment_seats(payments_seq, seats_seq) VALUES(1, 1);


-- Movie_Descriptions
INSERT INTO movie_descriptions
(movies_seq, categories_seq, story, running_time, director, actor, age_limit)
    VALUE(1, 6,
          '\“복수극으로 가자고, 화끈하게\”
          유력한 대통령 후보와 재벌 회장, 그들을 돕는 정치깡패 안상구\(이병헌\). 뒷거래의 판을 짠 이는 대한민국 여론을 움직이는 유명 논설주간 이강희\(백윤식\)다. 더 큰 성공을 원한 안상구는 이들의 비자금 파일로 거래를 준비하다 발각되고, 이 일로 폐인이 되어 버려진다.

          \“넌 복수를 원하고,
          난 정의를 원한다. 그림 좋잖아?\”
          빽 없고 족보가 없어 늘 승진을 눈 앞에 두고 주저 앉는 검사 우장훈\(조승우\). 마침내 대선을 앞둔 대대적인 비자금 조사의 저격수가 되는 기회를 잡는다. 그러나 비자금 파일을 가로챈 안상구 때문에 수사는 종결되고, 우장훈은 책임을 떠안고 좌천된다.

          자신을 폐인으로 만든 일당에게 복수를 계획하는 정치깡패 안상구. 비자금 파일과 안상구라는 존재를 이용해 성공하고 싶은 무족보 검사 우장훈. 그리고 비자금 스캔들을 덮어야 하는 대통령 후보와 재벌, 그들의 설계자 이강희

          과연 살아남는 자는 누가 될 것인가\?',
          130,
          "우민호",
          "이병헌, 조승우, 백윤식",
          19
    );

INSERT INTO movie_descriptions
(movies_seq, categories_seq, story, running_time, director, actor, age_limit)
    VALUE(3, 6,
          "\“느낌 오지? 이 놈 잡아야 하는 거\”

          가리봉동 소탕작전 후 4년 뒤,
          금천서 강력반은 베트남으로 도주한 용의자를 인도받아 오라는 미션을 받는다.

          괴물형사 \‘마석도\’\(마동석\)와 \‘전일만\’\(최귀화\) 반장은 현지 용의자에게서 수상함을 느끼고,
          그의 뒤에 무자비한 악행을 벌이는 \‘강해상\’\(손석구\)이 있음을 알게 된다.

          \‘마석도\’와 금천서 강력반은 한국과 베트남을 오가며
          역대급 범죄를 저지르는 \‘강해상\’을 본격적으로 쫓기 시작하는데...

          나쁜 놈들 잡는 데 국경 없다!
          통쾌하고 화끈한 범죄 소탕 작전이 다시 펼쳐진다!",
          106,
          "이상용",
          "마동석, 손석구, 최귀화",
          15
    );

INSERT INTO movie_descriptions
(movies_seq, categories_seq, story, running_time, director, actor, age_limit)
    VALUE(4, 5,
          "낮에는 치킨장사! 밤에는 잠복근무!
          지금까지 이런 수사는 없었다!
          불철주야 달리고 구르지만 실적은 바닥, 급기야 해체 위기를 맞는 마약반!
          더 이상 물러설 곳이 없는 팀의 맏형 고반장은 국제 범죄조직의 국내 마약 밀반입 정황을 포착하고 장형사, 마형사, 영호, 재훈까지 4명의 팀원들과 함께 잠복 수사에 나선다.
          마약반은 24시간 감시를 위해 범죄조직의 아지트 앞 치킨집을 인수해 위장 창업을 하게 되고, 뜻밖의 절대미각을 지닌 마형사의 숨은 재능으로 치킨집은 일약 맛집으로 입소문이 나기 시작한다.
          수사는 뒷전, 치킨장사로 눈코 뜰 새 없이 바빠진 마약반에게 어느 날 절호의 기회가 찾아오는데…
          범인을 잡을 것인가, 닭을 잡을 것인가!
          2019년 새해, 출동!",
          111,
          "이병헌",
          "류승룡, 이하늬, 진선규",
          15
    );

INSERT INTO movie_descriptions
(movies_seq, categories_seq, story, running_time, director, actor, age_limit)
    VALUE(2, 5,
          "\“45개 번호 중에 6개 맞히면 1등인 육사오라는 종이쪼가리,
          내가 주웠지 말입니다\”

          우연히 1등 당첨 로또를 주운 말년 병장 \‘천우\’.
          심장이 터질듯한 설렘도 잠시, 순간의 실수로 바람을 타고 군사분계선을 넘어간 로또.
          바사삭 부서진 멘탈을 부여잡고…기필코 다시 찾아야 한다\!

          우연히 남쪽에서 넘어온 1등 당첨 로또를 주운 북한 병사 \‘용호\’.
          이거이 남조선 인민의 고혈을 쥐어 짜내는 육사오라는 종이쪼가리란 말인가\?
          근데 무려 당첨금이 57억이라고?!

          당첨금을 눈앞에서 놓칠 위기에 처한 \‘천우\’와
          북에선 한낱 종이쪼가리일 뿐일 로또를 당첨금으로 바꿔야 하는 \‘용호\’.
          여기에 예상치 못한 멤버들\(?\)까지 합류하고 57억을 사수하기 위한 3:3팀이 결성되는데…

          주운 자 VS 또 주운 자
          아슬아슬 선 넘는 지분 협상이 시작된다!",
          113,
          "박규태",
          "고경표, 이이경",
          12
    );


INSERT INTO movie_descriptions
(movies_seq, categories_seq, story, running_time, director, actor, age_limit)
    VALUE(5, 1,
          "죽을 위기에서 살아난 \‘존 윅\’은 \‘최고 회의\’를 쓰러트릴 방법을 찾아낸다. 비로소 완전한 자유의 희망을
          보지만, NEW 빌런 \‘그라몽 후작\’과 전 세계의 최강 연합은 \'존 윅\'의 오랜 친구까지 적으로 만들어 버리고,
          새로운 위기에 놓인 \‘존 윅\’은 최후의 반격을 준비하는데,, 레전드 액션 블록버스터 <존 윅>의 새로운 챕터가 열린다!",
          169,
          '채드 스타헬스키',
          "키아누 리브스, 견자단, 빌 스카스가드",
          18
    );

INSERT INTO movie_descriptions
(movies_seq, categories_seq, story, running_time, director, actor, age_limit)
    VALUE(6, 1,
          "공식적으로만 세상을 4번 구한 전직 베테랑 경찰 \‘루크 홉스\’(드웨인 존슨) 분노 조절 실패로 쫓겨난 전직 특수요원 \‘데카드 쇼\’(제이슨 스타뎀) 99.9% 완벽히 다른 두 남자는 전 세계를 위협하는 불가능한 미션을 해결하기 위해 어쩔 수 없이 한 팀이 되고 마는데…",
          134,
          '데이비드 리치',
          '드웨인 존슨, 제이슨 스타뎀',
          12
    );

INSERT INTO movie_descriptions
(movies_seq, categories_seq, story, running_time, director, actor, age_limit)
    VALUE(7, 2,
          '서로가 최고의 친구였던 자매 \‘엘사\’와 \‘안나\’. 하지만 언니 \‘엘사\’에게는 하나뿐인 동생에게조차 말 못할 비밀이 있다. 모든 것을 얼려버리는 신비로운 힘이 바로 그것. ‘엘사’는 통제할 수 없는 자신의 힘이 두려워 왕국을 떠나고, 얼어버린 왕국의 저주를 풀기 위해 \‘안나\’는 언니를 찾아 환상적인 여정을 떠나는데……',
          108,
          '크리스 벅',
          '엘사, 안나, 올라프',
          0
    );

INSERT INTO movie_descriptions
(movies_seq, categories_seq, story, running_time, director, actor, age_limit)
    VALUE(8, 2,
          '뮤지션을 꿈꾸는 소년 미구엘은 전설적인 가수 에르네스토의 기타에 손을 댔다 \‘죽은 자들의 세상\’에 들어가게 된다. 그리고 그곳에서 만난 의문의 사나이 헥터와 함께 상상조차 못했던 모험을 시작하게 되는데… 과연 \‘죽은 자들의 세상\’에 숨겨진 비밀은? 그리고 미구엘은 무사히 현실로 돌아올 수 있을까?',
          104,
          '리 언크리치',
          '미겔 리베라, 헥토르, 이멜다',
          0
    );

INSERT INTO movie_descriptions
(movies_seq, categories_seq, story, running_time, director, actor, age_limit)
    VALUE(9, 3,
          "믿을 수 없겠지만... 이것은 실화다!
              1971년 로드 아일랜드, 해리스빌. 페론 가족은 꿈에 그리던 새 집으로 이사를 간다. 물론 1863년에 그 집에서
              일어난 끔찍한 살인 사건을 전혀 몰랐다. 또한 그 이후에 일어난 많은 무서운 사건에 대해서도 알지 못했다.
              이 가족은 그 집에서 겪은 일이 너무 무서워서 한 마디라도 외부에 언급하는 것을 거절했었다. 지금까지는...",
          112,
          "제임스 완",
          "베라 파미가, 패트릭 윌슨, 로저 페론, 저드슨 셔먼",
          15
    );


INSERT INTO movie_descriptions
(movies_seq, categories_seq, story, running_time, director, actor, age_limit)
    VALUE(10, 3,
          "현대를 배경으로 애들레이드와 게이브 윌슨(루피타 뇽오와 윈스턴 듀크)은
              아이들을 데리고 캘리포니아 북부에 있는 애들레이드의 고향 해변가로 여름 휴가를 떠난다.
              다음날 합류한 타일러 가족(엘리자베스 모스와 팀 하이데커)과 함께 해변에서 하루를 보내지만,
              애들레이드는 과거에 겪은 트라우마 증상이 심해지며 나쁜 일이 일어날 것이라는 공포에 시달린다.
              밤이 되고 윌슨은 차도에 조용히 서있는 네명의 손을 맞잡은 뭔가를 보게 되는데...",
          116,
          "조던 필",
          "루피타 뇽어, 윈스턴 듀크, 엘리자베스 모스, 야히아 압둘마틴 2세",
          15
    );




INSERT INTO movie_descriptions
(movies_seq, categories_seq, story, running_time, director, actor, age_limit)
    VALUE(11, 4,
          "삭제된 기억, 거대한 미로로 둘러싸인 낯선 공간

          어느날 갑자기 자신의 이름을 제외한 모든 기억이 삭제된 채 의문의 장소로 보내진 소년 '토마스'는 미로에 갇힌 그곳에서 자신과
          같은 처지에 놓인 다른 소년들을 만난다.그들은 매일 밤 살아 움직이는 미로에서 정체를 알 수 없는 죽음의 존재와 대립하며,
          이 지옥으로부터 빠져나갈 탈출구인 미로의 지도를 그려왔었다.그러던 어느 날, 닫혀있어야 하는 미로의 문들이 열리고
          그들은 마지막 선택의 기로에 놓이게 되는데…
          ",
          113,
          "웨스 볼",
          "딜런 오브라이언, 카야 스코델라리오, 토마스 브로디 생스터, 이기홍",
          12
    );

INSERT INTO movie_descriptions
(movies_seq, categories_seq, story, running_time, director, actor, age_limit)
    VALUE(12, 4,
          "산페드로 부두 폭발 사고, 유일한 생존자, 사라진 수천 만 달러! 그리고 베일에 가려진 인물 ‘카이저 소제’…
          수사관 데이브 쿠얀은 유일한 생존자인 ‘버벌’로부터 폭발 사고의 유력한 용의자로 지목되었던 5인에 대한 진술을 듣는다.
          ‘버벌’의 진술과 함께 속속 드러나는 지난 6주간 있었던 그들의 화려한 범죄 행각, 하지만 정작 용의자들도 모르게 그들 위에
          존재했던 전설 속 악마 ‘카이저 소제’의 존재가 부각되며 쿠얀은 혼란에 빠진다...

          치밀한 설계, 완벽하게 짜여진 범죄 시간을 거스른 치열한 두뇌싸움이 시작된다!
          ",
          113,
          "브라이언 싱어",
          "케빈 스페이시, 가브리엘 번, 베니시오 델 토로",
          18
    );
