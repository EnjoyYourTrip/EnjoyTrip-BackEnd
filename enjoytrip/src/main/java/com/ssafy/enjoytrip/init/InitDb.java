//package com.ssafy.enjoytrip.init;
//
//import com.ssafy.enjoytrip.domain.answer.model.Answer;
//import com.ssafy.enjoytrip.domain.answer.service.AnswerService;
//import com.ssafy.enjoytrip.domain.hotplace.model.HotPlace;
//import com.ssafy.enjoytrip.domain.hotplace.model.dto.HotPlaceRegisterRequest;
//import com.ssafy.enjoytrip.domain.hotplace.service.HotPlaceService;
//import com.ssafy.enjoytrip.domain.itinerary.model.Itinerary;
//import com.ssafy.enjoytrip.domain.itinerary.model.ItineraryDetail;
//import com.ssafy.enjoytrip.domain.itinerary.service.ItineraryService;
//import com.ssafy.enjoytrip.domain.member.model.Member;
//import com.ssafy.enjoytrip.domain.member.model.Role;
//import com.ssafy.enjoytrip.domain.member.service.MemberService;
//import com.ssafy.enjoytrip.domain.question.model.Question;
//import com.ssafy.enjoytrip.domain.question.service.QuestionService;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class InitDb {
//    private final InitService initService;
//
//    @PostConstruct
//    public void init() {
//        log.info("memberInit 시작");
//        initService.memberInit();
//        log.info("memberInit 종료");
//
//        log.info("questionInit 시작");
//        initService.questionInit();
//        log.info("questionInit 종료");
//
//        log.info("answerInit 시작");
//        initService.answerInit();
//        log.info("answerInit 종료");
//
//        log.info("hotPlaceInit 시작");
////        initService.hotPlaceInit();
//        log.info("hotPlaceInit 종료");
//
//
//        log.info("itineraryInit 시작");
//        initService.itineraryInit();
//        log.info("itineraryInit 종료");
//    }
//
//    @Component
//    @RequiredArgsConstructor
//    @Transactional
//    static class InitService {
//        private final MemberService memberService;
//        private final QuestionService questionService;
//        private final AnswerService answerService;
//        private final HotPlaceService hotPlaceService;
//        private final ItineraryService itineraryService;
//
//        public void memberInit() {
//            Member adminMember = Member.createMember("admin", "admin", "admin", "adminNickname", "admin@email");
//            adminMember.setRole(Role.ADMIN);
//            memberService.insertMember(adminMember);
//
//            for (int i = 2; i <= 6; i++) {
//                Member member = Member.createMember("싸피인" + i, "id" + i, "password" + i, "나는싸피" + i, "email" + i);
//                memberService.insertMember(member);
//            }
//            Member member1 = Member.createMember("인호현", "ihh", "1234", "hohyunNickName", "inhohyun36@gmail.com");
//            memberService.insertMember(member1);
//            Member member2 = Member.createMember("남혁준", "zzunId", "1234", "zzunNickName", "hyeokzzun@gmail.com");
//            memberService.insertMember(member2);
//        }
//
//        public void questionInit() {
//            for (int i = 2; i <= 6; i++) {
//                for (int j = 1; j <= 100; j++) {
//                    Question question = Question.createQuestion(i + "가 쓴 질문제목" + j, "질문내용", Long.parseLong(String.valueOf(i)));
//                    questionService.insertQuestion(question);
//                }
//            }
//        }
//
//        public void answerInit() {
//            for (int i = 2; i <= 6; i++) {
//                Answer answer = Answer.createAnswer(Long.parseLong(String.valueOf(i)), 1L, "답변내용" + (i - 1));
//                answerService.insertAnswer(answer);
//            }
//        }
//
//        public void hotPlaceInit() {
//            for (int i = 2; i <= 6; i++) {
//                for (int j = 1; j <= 20; j++) {
//                    HotPlaceRegisterRequest hotPlace = new HotPlaceRegisterRequest();
//                    hotPlace.setMemberId(Long.parseLong(String.valueOf(i)));
//                    hotPlace.setTitle("HotPlace 제목" + j);
//                    hotPlace.setContent("HotPlace 내용" + j);
//                    hotPlace.setAddress("Address " + j);
//                    hotPlaceService.write(hotPlace);
//
//                    // Create file information and associate it with the hot place
//                    Map<String, Object> fileParams = new HashMap<>();
//                    fileParams.put("saveFolder", "folder" + j);
//                    fileParams.put("originalFile", "originalFile" + j + ".jpg");
//                    fileParams.put("saveFile", "saveFile" + j + ".jpg");
//                    fileParams.put("hotPlaceId", hotPlace.getHotplaceId());
//                    hotPlaceService.writeFile(fileParams);
//                }
//            }
//        }
//
//        public void itineraryInit() {
//            String[][] data = {
//                    {"125266", "국립 청태산자연휴양림", "강원도 횡성군 둔내면 청태산로 610", "37.52251412000000000", "128.29191150000000000"},
//                    {"125406", "비슬산자연휴양림", "대구광역시 달성군 유가읍 일연선사길 61", "35.69138039000000000", "128.51597740000000000"},
//                    {"125407", "불정자연휴양림", "경상북도 문경시 불정길 180", "36.61882624000000000", "128.13426590000000000"},
//                    {"125408", "청송 자연휴양림 퇴적암층 (청송 국가지질공원)", "경상북도 청송군 부남면 청송로 3478-96", "36.31791942000000000", "129.05372060000000000"},
//                    {"125409", "국립 유명산자연휴양림", "경기도 가평군 설악면 유명산길 79-53", "37.59336321000000000", "127.49120140000000000"},
//                    {"125410", "국립 청옥산자연휴양림", "경상북도 봉화군 석포면 청옥로 1552-163", "37.03918876000000000", "128.99565180000000000"},
//                    {"125411", "금원산자연휴양림", "경상남도 거창군 위천면 금원산길 412", "35.73148789000000000", "127.79789800000000000"},
//                    {"125412", "국립 용대자연휴양림", "강원도 인제군 북면 용대리 연화동길", "38.21823377000000000", "128.31310520000000000"},
//                    {"125413", "국립 가리왕산자연휴양림", "강원도 정선군 정선읍 가리왕산로 707", "37.42684713000000000", "128.57003940000000000"},
//                    {"125414", "세심자연휴양림", "전라북도 임실군 삼계면 임삼로 485", "35.51825771000000000", "127.23336750000000000"},
//                    {"125416", "국립 덕유산자연휴양림", "전라북도 무주군 무풍면 구천동로 530-62", "35.90814315000000000", "127.81480280000000000"},
//                    {"125417", "국립 대관령자연휴양림", "강원 강릉시 성산면 어흘리 삼포암길 133", "37.70927691000000000", "128.78154790000000000"},
//                    {"125418", "국립 신불산폭포자연휴양림", "울산광역시 울주군 상북면 청수골길 175", "35.52599451000000000", "129.02323190000000000"},
//                    {"125419", "국립 천관산자연휴양림", "전라남도 장흥군 관산읍 칠관로 842-1150", "34.54513393000000000", "126.89988520000000000"},
//                    {"125420", "국립 삼봉자연휴양림", "강원도 홍천군 내면 삼봉휴양길 276", "37.85213473000000000", "128.46086970000000000"},
//                    {"125421", "유치자연휴양림", "전라남도 장흥군 유치면 휴양림길 154", "34.76804945000000000", "126.83238010000000000"},
//                    {"125423", "봉황자연휴양림", "충북 충주시 가금면 수룡봉황길 540", "37.06016432000000000", "127.82469210000000000"},
//                    {"125428", "칠갑산자연휴양림", "충청남도 청양군 대치면 칠갑산로 668-103", "36.43161349000000000", "126.84382270000000000"},
//                    {"125429", "만인산푸른학습원", "대전광역시 동구 산내로 106", "36.20214492000000000", "127.45459920000000000"},
//                    {"125430", "국립 통고산자연휴양림", "경상북도 울진군 금강송면 불영계곡로 880", "36.93371296000000000", "129.19176800000000000"},
//                    {"125431", "남이자연휴양림", "충청남도 금산군 남이면 느티골길 200", "36.05938483000000000", "127.37689050000000000"},
//                    {"125432", "조령산자연휴양림", "충청북도 괴산군 연풍면 새재로 1795", "36.81160532000000000", "128.04705980000000000"},
//                    {"125433", "황새울관광농원", "경기도 용인시 처인구 백암면 황새울로 242", "37.11224690000000000", "127.35051400000000000"},
//                    {"125434", "청암관광농원", "경기도 이천시 부발읍 황무로1720번길 74", "37.26828187000000000", "127.51610850000000000"},
//                    {"125435", "새말관광농원", "강원도 횡성군 우천면 전재로90번길 49", "37.43749775000000000", "128.08054150000000000"},
//                    {"125437", "상양관광농원", "충청남도 보령시 주교면 문화마을길 178-58", "36.36981622000000000", "126.59177200000000000"},
//                    {"125442", "웅치관광농원", "전라남도 보성군 웅치면 서동길 227", "34.72147978000000000", "127.00387730000000000"},
//                    {"125443", "청화산농원", "경상북도 상주시 화북면 문장로 2199-11", "36.60024906000000000", "127.89905960000000000"},
//                    {"125445", "생각하는 정원", "제주특별자치도 제주시 한경면 녹차분재로 675", "33.32540095000000000", "126.25517540000000000"},
//                    {"125447", "보리나라 학원농장", "전라북도 고창군 공음면 학원농장길 158-6", "35.37614542000000000", "126.54363230000000000"},
//                    {"125448", "안면도자연휴양림", "충청남도 태안군 안면읍 안면대로 3195-6", "36.49674766000000000", "126.35883970000000000"},
//                    {"125449", "남한산성도립공원 [유네스코 세계문화유산]", "경기도 광주시 남한산성면 남한산성로 731", "37.47666846000000000", "127.18838500000000000"},
//                    {"125452", "청계산", "서울특별시 서초구 원터길 경기도 성남시ㆍ과천시ㆍ의왕시", "37.44168677000000000", "127.05436760000000000"},
//                    {"125453", "천마산군립공원", "경기도 남양주시 화도읍 묵현로 105-37", "37.66661214000000000", "127.29020640000000000"},
//                    {"125455", "관음산", "경기도 포천시 이동면", "38.02857314000000000", "127.31110230000000000"},
//                    {"125457", "수리산도립공원", "경기도 군포시 속달로 347-4 안산시, 안양시", "37.34804766000000000", "126.90053980000000000"},
//                    {"125458", "운길산", "경기도 남양주시 조안면 북한강로433번길 186", "37.57107346000000000", "127.30237470000000000"},
//                    {"125459", "축령산", "경기도 남양주시 수동면 축령산로 299 가평군 상면 행현리", "37.75484397000000000", "127.31325730000000000"},
//                    {"125460", "왕방산", "경기도 포천시 신북면 깊이울로 234-106", "37.91623634000000000", "127.16425510000000000"},
//                    {"125461", "주금산", "경기도 포천시 내촌면 능곡청정길 50 남양주시 수동면, 가평군 상면", "37.78354493000000000", "127.24469050000000000"},
//                    {"125462", "가덕산", "경기 가평군 북면 강원도 춘천시 서면", "37.92023219000000000", "127.56830980000000000"},
//                    {"125463", "가리산(포천)", "경기도 포천시 이동면 장암리", "38.04330089000000000", "127.40280460000000000"},
//                    {"125464", "국망봉자연휴양림(포천)", "경기도 포천시 이동면 늠바위길 207-28", "38.02889134000000000", "127.39621950000000000"},
//                    {"125465", "도드람산(저명산)", "경기도 이천시 마장면 서이천로", "37.26700478000000000", "127.39256410000000000"},
//                    {"125467", "대금산(경기)", "경기 가평군 가평읍 두밀리 하면 현리", "37.81305334000000000", "127.43632220000000000"},
//                    {"125468", "마니산(강화)", "인천광역시 강화군 화도면 마니산로675번길 18", "37.63246729000000000", "126.42378650000000000"},
//                    {"125469", "명성산", "경기도 포천시 이동면 도평리", "38.09567437000000000", "127.35042170000000000"},
//                    {"125470", "용문산 백운봉", "경기도 양평군 용문면 연수리 산79 (일대)", "37.51963938000000000", "127.54296550000000000"},
//                    {"125472", "수덕산", "경기 가평군 북면 도대리", "37.91689543000000000", "127.51240670000000000"},
//                    {"125473", "현등산(운악산)", "경기 가평군 하면 운악청계로 589번길 73 포천시 화현면 화동로 180", "37.87728547000000000", "127.32446940000000000"},
//                    {"125474", "중원산", "경기도 양평군 용문면 조계골길", "37.55699688000000000", "127.58193860000000000"},
//                    {"125476", "통방산", "경기 가평군 설악면 양평군 서종면", "37.63320516000000000", "127.45580900000000000"},
//                    {"125477", "화야산", "경기 가평군 청평면 삼회리 설악면 회곡리", "37.67067252000000000", "127.42783820000000000"},
//                    {"125483", "몽덕산", "경기도 가평군 북면 화악리 강원도 춘천시 서면", "37.95497046000000000", "127.60305560000000000"},
//                    {"125484", "유명산", "경기도 가평군 설악면 양평군 옥천면", "37.58916643000000000", "127.48851560000000000"},
//                    {"125485", "용문산", "경기도 양평군 용문면 용문산로 782", "37.55116953000000000", "127.56886220000000000"},
//                    {"125489", "비금계곡", "경기 남양주시 수동면 내방리 수동관광지 내", "37.75754021000000000", "127.27695780000000000"},
//                    {"125490", "수동국민관광지", "경기도 남양주시 수동면 비룡로 1635", "37.75797346000000000", "127.27523850000000000"},
//                    {"125492", "백운계곡 (한탄강 국가지질공원)", "경기도 포천시 이동면 도평리 35", "38.07261983000000000", "127.41030580000000000"},
//                    {"125493", "명지계곡", "경기도 가평군 북면 적목리 산 45", "37.93503712000000000", "127.47774260000000000"},
//                    {"125495", "용계골", "경기도 양평군 용문면 용문산로 512", "37.55575823000000000", "127.58753820000000000"},
//                    {"125498", "승봉도 이일레 해변", "인천광역시 옹진군 자월면 승봉로116번길", "37.16559165000000000", "126.30127710000000000"},
//                    {"125502", "강화도", "인천광역시 강화군 강화읍 강화대로", "37.74650234000000000", "126.48774410000000000"},
//                    {"125503", "제부도", "경기도 화성시 서신면 해안길 421-12", "37.16865802000000000", "126.62448380000000000"},
//                    {"125504", "덕적도 갈대 군락지", "인천광역시 옹진군 덕적면 덕적북로 130", "37.22762620000000000", "126.14466750000000000"},
//                    {"125505", "백령도", "인천광역시 옹진군 백령면 백령로 일대", "37.96440164000000000", "124.67668330000000000"},
//                    {"125510", "신흥레저타운", "경기도 양주시 장흥면 삼상리", "37.71782500000000000", "126.94843610000000000"},
//                    {"125512", "팔달공원", "경기도 수원시 팔달구 팔달산로 108", "37.27739670000000000", "127.01047620000000000"},
//                    {"125513", "산장관광지", "경기도 가평군 상면 덕현산장길 74", "37.75432920000000000", "127.41355930000000000"},
//                    {"125519", "자유공원(인천)", "인천광역시 중구 제물량로232번길 46", "37.47529463000000000", "126.62227550000000000"},
//                    {"125521", "고모저수지", "경기도 포천시 소흘읍 죽엽산로 452", "37.79907221000000000", "127.16382520000000000"},
//                    {"125522", "백로주유원지", "경기도 포천시 영중면 호국로 2671-22", "37.98186621000000000", "127.24179610000000000"},
//                    {"125523", "포천 산정호수", "경기도 포천시 영북면 산정호수로411번길 104", "38.06883809000000000", "127.32210290000000000"},
//                    {"125524", "낙원역사공원", "경기도 안성시 낙원길 78", "37.00541339000000000", "127.27325730000000000"},
//                    {"125525", "풍혈산유원지", "경기도 포천시 영중면 성장로 268", "38.02047358000000000", "127.28328570000000000"},
//                    {"125527", "청계저수지 (청계호수)", "경기도 포천시 일동면 운악청계로1480번길 45", "37.94308372000000000", "127.34370740000000000"},
//                    {"125528", "흥국사(남양주)", "경기도 남양주시 덕릉로1071번길 58", "37.68452880000000000", "127.09856500000000000"},
//                    {"125530", "청계사(경기)", "경기도 의왕시 청계로 475", "37.41200653000000000", "127.03475920000000000"},
//                    {"125531", "용주사(화성)", "경기도 화성시 용주로 136", "37.21213749000000000", "127.00509800000000000"},
//                    {"125532", "백련사(강화)", "인천광역시 강화군 하점면 고려산로61번길 270", "37.75102592000000000", "126.43728480000000000"},
//                    {"125533", "보문사(강화)", "인천광역시 강화군 삼산남로828번길 44 보문사", "37.68834231000000000", "126.32096940000000000"},
//                    {"125534", "강화 전등사", "인천광역시 강화군 길상면 전등사로 37-41", "37.63169342000000000", "126.48291450000000000"},
//                    {"125535", "청룡사(안성)", "경기도 안성시 서운면 청룡길 140", "36.91363814000000000", "127.28883620000000000"},
//                    {"125536", "칠장사(안성)", "경기도 안성시 죽산면 칠장로 399-18", "37.02640486000000000", "127.39661500000000000"},
//                    {"125537", "용문사(용문산)", "경기도 양평군 용문면 용문산로 782", "37.55021690000000000", "127.57099260000000000"},
//                    {"125538", "현등사(가평)", "경기도 가평군 조종면 현등사길 34", "37.87058637000000000", "127.33092460000000000"},
//                    {"125539", "상원사(용문산)", "경기도 양평군 용문면 상원사길 292", "37.54856399000000000", "127.55308870000000000"},
//                    {"125540", "연천 숭의전지", "경기도 연천군 미산면 숭의전로 382-27", "38.02376753000000000", "126.97302510000000000"},
//                    {"125541", "천진암 성지", "경기도 광주시 퇴촌면 천진암로 1203", "37.42441416000000000", "127.38279390000000000"},
//                    {"125543", "제암리 3.1운동 순국기념관", "경기도 화성시 향남읍 제암길 50", "37.12615655000000000", "126.89221340000000000"},
//                    {"125546", "남양주 광릉(세조,정희왕후) [유네스코 세계문화유산]", "경기도 남양주시 진접읍 광릉수목원로 354", "37.75283338000000000", "127.17674210000000000"},
//                    {"125547", "권율장군묘", "경기도 양주시 장흥면 권율로 223", "37.73358429000000000", "126.94960500000000000"},
//                    {"125548", "남양주 홍릉(고종과 명성황후)과 유릉(순종과 순명,순정 황후) [유네스코 세계문화유산]", "경기도 남양주시 홍유릉로 352-1", "37.63155642000000000", "127.20962310000000000"},
//                    {"125549", "고양 벽제관지", "경기도 고양시 덕양구 벽제관로 34-16", "37.70426460000000000", "126.90034370000000000"},
//                    {"125551", "고양 서삼릉(장경왕후) [유네스코 세계문화유산]", "경기도 고양시 덕양구 서삼릉길 233-126", "37.66399094000000000", "126.86852550000000000"},
//                    {"125552", "고양 서오릉 [유네스코 세계문화유산]", "경기도 고양시 덕양구 서오릉로 334-32", "37.62361011000000000", "126.90078200000000000"},
//                    {"125553", "파주 윤관장군묘", "경기도 파주시 광탄면 혜음로 930", "37.76772029000000000", "126.85527660000000000"},
//                    {"125555", "수원 화성 [유네스코 세계문화유산]", "경기도 수원시 장안구 영화동 320-2", "37.28735113000000000", "127.01211470000000000"},
//                    {"125557", "화산서원(포천)", "경기도 포천시 가산면 가산로 227-40", "37.84188688000000000", "127.17564790000000000"},
//                    {"125558", "김포향교", "경기도 김포시 북변중로25번길 38", "37.62472880000000000", "126.70992440000000000"}
//            };
//
//            for (int i = 0; i < data.length; i++) {
//                String[] entry = data[i];
//                Long memberId = (i % 5) + 2L; // Assign memberId in a round-robin fashion from 2 to 6
//                List<ItineraryDetail> itineraryDetails = new ArrayList<>();
//
//                // Adding at least 3 ItineraryDetails
//                for (int j = 0; j < 5; j++) {
//                    int index = (i + j) % data.length;
//                    itineraryDetails.add(ItineraryDetail.createItineraryDetail(null, j + 1, Integer.parseInt(data[index][0])));
//                }
//
//                Itinerary itinerary = Itinerary.createItinerary(
//                        entry[1] + "에서 시작하는 즐거운 여행", // title
//                        entry[2] + "에서 경치를 즐기고 좋은 사람들과 좋은 시간 보내기!", // content
//                        LocalDate.now(),
//                        LocalDate.now().plusDays(5),
//                        memberId,
//                        itineraryDetails
//                );
//
//                itineraryService.insertItinerary(itinerary);
//                Long itineraryId = itineraryService.findLastItineraryId(itinerary.getMemberId());
//
//                for (ItineraryDetail detail : itineraryDetails) {
//                    detail.setItineraryId(itineraryId);
//                    itineraryService.insertItineraryDetail(detail);
//                }
//            }
//        }
//
//
//    }
//}
//
