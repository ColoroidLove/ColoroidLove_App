package com.example.coloroidlove;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.coloroidlove.CameraActivity;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class ResultActivity  extends BaseActivity {

    ImageView res_img;
    TextView res_title,res_desc, res_name,res_artist,res_item, res_color;
    Button btnEnd;

    // insert시 필요한 닉네임과 결과
    String personal;
    String nickname;

    // base를 정할 base 변수, 퍼스널 결과에 맞는 결과를 가져오기 위한 인덱스 변수
    int base;
    int index;


    // 결과 배열
    Integer[] profileImg = {R.drawable.profile_1, R.drawable.profile_2, R.drawable.profile_3, R.drawable.profile_4, R.drawable.profile_5}; // profile
    // 결과 자세한 설명
    /*
     봄라이트, 봄브라이트
     여름라이트, 여름뮤트, 여름브라이트, 여름브라이트딥
     가을뮤트, 가을스트롱, 가을딥
     겨울트루, 겨울브라이트, 겨울딥
     */
    // insert에 들어가야할 사진
    Integer[] WarmPolar = {R.drawable.polar_springlight, R.drawable.polar_springbright,
            R.drawable.polar_fallmute, R.drawable.polar_fallstrong, R.drawable.polar_falldeep};
    Integer[] CoolPolar = {
            R.drawable.polar_summerlight, R.drawable.polar_summermute, R.drawable.polar_summerbright, R.drawable.polar_summerlowerbrightmute,
            R.drawable.polar_wintertrue, R.drawable.polar_winterbright, R.drawable.polar_winterdeep};


    // 출력
    String[] WarmDesc = {
            "파스텔 톤 컬러 중에서 맑고 깨끗한 느낌의 컬러~\n" + "살랑살랑하고 깨끗한 느낌이 도는\n마치 솜사탕 같은 컬러가 찰떡!!",
            "또렷하고 생기 있는 이미지가 강하다!\n" +"\n" +"피부 톤이 매우 밝고, 비비드한 컬러가 잘 어울리는 것이 특징이다.",
            "가을 뮤트는 스트롱톤보다는 더 부드러운, 중간 밝기, 중간 채도의 색상이 더 잘 어울리는 타입이다. 부드럽고 은은한 이미지로 ‘크림 라떼’가 떠오르며 내추럴 메이크업과 쉬머펄로 분위기를 더욱 더 살릴 수 있다. ",
            "가을 스트롱은 가을 중심에 있는 컬러로 가을의 특징들을 가장 많이 담아내고 있는 톤입니다. 가을 스트롱은 컬러 스펙트럼이 가장 넓은 톤이다. 워스트 컬러가 거의 없다고 볼 수 있는 엄청난 톤이다. \n" +
                    "\n" +
                    "메이크업을 할 때 치크는 차분환 코랄, 오렌지 컬러로 립 컬러는 레드, 오렌지, 벽돌 컬러, 베이스는 스트롱 웜톤인 만큼 옐로 베이스를 픽하는 것이 효과적이다.",
            " 가을 딥 톤은 뮤트톤보다는 색감이 더 들어가고, 어두운 색상이 잘어울리는 타입! 원색에 검은색이 섞인 낮은 명채도의 색들로 구성되어 있고 대체로 섹시하고 고급스러운 분위기를 띈다. "};
    String[] CoolDesc = {
            "회색 기와 푸른 기가 살짝 도는 고명도의 은은한 파스텔 컬러\n" + "\n" + "맑고, 청량하고, 싱그러운 느낌!",
            "회색 기가 많이 섞인 톤 다운된 파스텔 계열이 찰떡!\n" + "\n" + "우아+단아한 분위기는 세련되고 시크한 이미지까지",
            "여름 브라이트는 여름 ‘트루’ 톤이라고도 하는데, 생기 가득하면서 청량한 느낌을 가진 톤이다. \n" + "\n" +
                    "채도가 높은 원색 계열이 잘 받는 톤이다. 대표적으로 핫핑크나 코발트 블루같은 고명도 고채도의 밝고 선명한 색상이 베스트이다.\n" + "\n" +
                    "화장할 때는 아이메이크업보다 블러셔에 핑크나 라벤더 색으로 포인트를 주면 생기 넘치고 에너지 가득한 이미지를 더해 줄 수 있다. 립 컬러는 옅고 차분한 색상보다 밝고 쨍한 컬러감이 더 울린다.",
            "여름뮤트톤은 세 가지 타입 중에서 대비에 가장 약한 타입이다. \n소프트 서머를 대표 하는 컬러로는 명도가 높고 채도가 낮은 컬러들이다. \n쿨한 느낌이지만 대비가 강하지 않고 그레이가 섞인 부드러운 이미지이다.",
            "겨울 트루는 여름 쿨톤에 비해 좀 더 푸른기가 돌고 쨍한 컬러이다.\n" + "창백한 느낌의 피부와 흑단 같은 헤러컬러로 백설공주 분위기를 낼 수 있다. \n카리스마 있고 도시적인 스타일링이 가능하며 쿨하고 비비드한 컬러는 실패할 확률이 적다. \n블랙&화이트로 코디하면 찰떡!!",
            "겨울 브라이트는 겨울 쿨톤 중에서 가장 화려한 타입이며\n쿨 베이스의 고채도 컬러나 선명한 컬러로 스타일링을 하는 게 좋다. \n립 컬러로 화려한 핫핑크, 핏빛 버건, 핏빛 레드를 사용하면 \n쿨톤의 시크한 매력을 살리기에 좋을 것이다.",
            "겨울 딥은 어둡고 진한 플럼, 핏빛레드, 버건디 레드가 잘어울린다.\n 뭔가 멋있어보이는 분위기를 가진.. 자칭 걸크러쉬분들이 떠오르는 퍼스널 컬리이다. \n다크함이 매력인 겨울딥은 낮은 채도 컬러의 포인트 컬러로 얼굴빛이 살아난다. "
    };

    // 대표적인 연예인
    String[] WarmArtist = {
                    "수지, 송혜교", // 봄라이트
                    "아이유, 박민영", // 봄브라이트
                    "블랙핑크 제니, 박신혜, 한효주, 하연수", // 가을뮤트
                    "김민주, 레드벨벳 조이, 예리, NCT 해찬, BTS 뷔", // 가을스트롱
                    "크리스탈, 이효리, ITZY 예지, 김유정, 김세정"}; // 가을딥
    String[] CoolArtist = {
            "손예진, 정채연, 김태리", // 여름라이트
            "장원영, 김고은, 김연아", // 여름 뮤트
            "레드벨벳 아이린, 트와이스 나연", // 여름 브라이트
            "이광수, 비투비 육성재, 하이라이트 윤두준, SF9 로운", // 여름 저명도뮤트
            "김서형, 선미", // 겨울트루
            "오마이걸 비니, 트와이스 채영, 하이라이트 양요섭", // 겨울브라이트
            "김혜수, 엑소 디오, (여자)아이들 전소연, 이다희, 이말년" // 겨울딥
    };

    // 추천 아이템
    String[] WarmItem ={
                "자연갈색헤어컬러, 골드컬러악세사리", // 봄라이트
                "자연갈색헤어컬러, 골드컬러악세사리", // 봄브라이트
                "말린 장미 MLBB 립스틱, 골드색의 악세사리", // 가을뮤트
                "클래식레드 립스틱, 블랙 헤어, 골드 악세사리", // 가을스트롱
                "오렌지 브라운 컬러의 블러셔, 스킨 톤의 립, 짙은 갈색 아이라이너"}; // 가을딥
    String[] CoolItem = {
                "자연갈색헤어컬러, 실버 악세사리", // 여름라이트
                "흑발, 로즈골드악세사리", // 여름뮤트
                "핑크계열 블러셔, 하얀색 계열의 옷, 어두운 계열의 머리 색, 실버 악세사리", // 여름브라이트
                "핑크베이스의 파운데이션, 펄감이 없는 아이섀도우, 매트한 립", // 여름저명도뮤트
                "블랙 헤어 컬러, 대비가 강한 스타일링, 핑크베이스 파운데이션", // 겨울트루
                "악세서리 실버, 딥블랙 헤어 컬러, 화려한 패턴 스타일링, 블랙 아이라이너", // 겨울브라이트
                "가죽 소재 옷, 블랙&화이트룩" // 겨울딥
    };

    // 추천 색깔
    String[] WarmColor = {
            "연노랑, 연분홍, 연두, 연하늘", // 봄라이트
            "쨍한레드, 쨍한초록, 쨍한노랑", // 봄브라이트
            "말린 장미, 녹차색,  누드한 갈색", // 가을뮤트
            "겨자색, 토마토색", // 가을스트롱
            "다크브라운, 오트밀 컬러"}; // 가을딥
    String[] CoolColor = {
            "코랄, 딸기우유, 회색", // 여름라이트
            "말린장미, 회색, 블루", // 여름뮤트
            "라벤더 색, 핫핑크, 코발트 블루", // 여름브라이트
            "말린 장미, 팥 죽 색", // 여름저명도뮤트
            "버건디핑크, 쿨베이스 핑크", // 겨울트루
            "핑크레드, 푸시아, 퍼플", // 겨울브라이트
            "푸른 버건디, 딥한 핏빛 립" // 겨울딥
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // 결과 받아오기
        Intent intent = getIntent();
        personal = intent.getStringExtra("퍼스널컬러");
        nickname = intent.getStringExtra("닉네임");
        base = intent.getIntExtra("베이스컬러",0);
        index = intent.getIntExtra("컬러인덱스",0);


        res_img = findViewById(R.id.res_img); // 프로필 이미지 랜덤
        res_name  = findViewById(R.id.res_name); //이름
        res_title  = findViewById(R.id.res_title); //결과
        res_desc  = findViewById(R.id.res_desc); //설명
        res_artist  = findViewById(R.id.res_artist); //대표적인 연예인
        res_item  = findViewById(R.id.res_item); //추천 아이템
        res_color  = findViewById(R.id.res_color); // 추천 컬러
        btnEnd=findViewById(R.id.btnEnd); //종료하기
        System.out.println("닉네임 값 확인 : "+nickname);

        res_img.setImageDrawable(ContextCompat.getDrawable(this, profileImg[0]));
        res_name.setText(nickname+"님의 결과");
        res_title.setText(personal);

        if(base==0){ // warm
            // 프로필 사진 랜덤 1~5
            res_desc.setText(WarmDesc[index]);
            res_artist.setText("대표적인 연예인 : " + WarmArtist[index]);
            res_item.setText("추천 아이템 : " + WarmItem[index]);
            res_color.setText("추천 컬러 : " + WarmColor[index]);
        }else{ // cool
            res_desc.setText(CoolDesc[index]);
            res_artist.setText("대표적인 연예인 : " + CoolArtist[index]);
            res_item.setText("추천 아이템 : " + CoolItem[index]);
            res_color.setText("추천 컬러 : " + CoolColor[index]);
        }

        //res_img.setImageDrawable();
/*        res_name.setText(nickname+"님의 결과");
        res_title.setText(personal);*/
        // res_desc.setText();
         //res_artist.setText("대표적인 연예인 : " + );
        //res_item.setText("추천 아이템 : " + );
        // res_color.setText("추천 컬러 : " + );

        //insertStudent();

        //테스트 종료
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoClass(MainActivity.class);
            }
        });

    }


    void insertStudent(){
        //Dbhelper의 쓰기모드 객체를 가져옴
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();

        String qry = "INSERT INTO usersTB(name, result) VALUES('"+nickname+"', '" + personal + "')";
        database.execSQL(qry); //만들어준 쿼리문 실행
    }
}
