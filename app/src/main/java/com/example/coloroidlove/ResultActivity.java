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

    // base를 정할 base 변수(웜 쿨 판단), 퍼스널 결과에 맞는 결과를 가져오기 위한 인덱스 변수
    int base;
    int index;

    // 결과 프로필 배열
    Integer[] reusltWarmImg = {R.drawable.result_springlight, R.drawable.result_springlight,
            R.drawable.result_fallmute, R.drawable.result_fallstrong, R.drawable.result_falldeep}; // 웜 결과 이미지
    Integer[] reusltCoolImg = {R.drawable.result_summerlight, R.drawable.result_summermute, R.drawable.result_summerbright, R.drawable.result_summerlowbrightmute,
            R.drawable.result_wintertrue,   R.drawable.result_winterbright,  R.drawable.result_winterdeep}; // 쿨 결과 이미지

    // 결과 자세한 설명
    /*
     봄라이트, 봄브라이트
     여름라이트, 여름뮤트, 여름브라이트, 여름브라이트딥
     가을뮤트, 가을스트롱, 가을딥
     겨울트루, 겨울브라이트, 겨울딥
     */



    // 출력
    String[] WarmDesc = {
            "파스텔 톤 컬러 중에서 맑고 깨끗한 느낌의 컬러~\n" + "살랑살랑하고 깨끗한 느낌이 도는\n마치 솜사탕 같은 컬러가 찰떡!",
            "또렷하고 생기 있는 이미지가 강하다!\n" +"\n" +"피부 톤이 매우 밝고, 비비드한 컬러가 잘 어울리는 것이 특징!",
            "가을 뮤트는 스트롱톤보다는 더 부드러운, 중간 밝기, 중간 채도의 색상이 더 잘 어울리는 타입이다. 부드럽고 은은한 이미지로 ‘크림 라떼’가 떠올라요!",
            "가을 스트롱은 컬러 스펙트럼이 가장 넓은 톤이다. 워스트 컬러가 거의 없다고 볼 수 있는 엄청난 톤! 스트롱 웜톤인 만큼 옐로 베이스를 픽하세요!",
            "어두운 색상이 잘어울리는 타입! 원색에 검은색이 섞인 낮은 명채도의 색들로 구성되어 있고 대체로 섹시하고 고급스러운 분위기~. "};
    String[] CoolDesc = {
            "회색 기와 푸른 기가 살짝 도는 고명도의 \n은은한 파스텔 컬러" + "맑고, 청량하고, 싱그러운 느낌!",
            "회색 기가 많이 섞인 톤 다운된 파스텔 계열이 찰떡!\n" + "\n" + "우아+단아한 분위기는 세련되고 시크한 이미지까지",
            "여름 브라이트는 여름 ‘트루’ 톤이라고도 하는데, 생기 가득하면서 청량한 느낌을 가진 톤이이며 채도가 높은 원색 계열이 잘 받는 톤!",
            "쿨한 느낌이지만 대비가 강하지 않고 그레이가 섞인 부드러운 이미지 소프트 서머를 대표 하는 컬러로는 명도가 높고 채도가 낮은 컬러!",
            "창백한 느낌의 피부와 흑단 같은 헤러컬러로 백설공주 분위기~ 카리스마 있고 도시적인 스타일링이 가능하고 블랙&화이트로 코디하면 찰떡!!",
            "겨울 쿨톤 중에서 가장 화려한 타입이며\n쿨 베이스의 고채도 컬러나 선명한 컬러로 스타일링을 하는게 베스트! 쿨톤의 시크한 매력을 살리기에 딱!",
            "뭔가 멋있어보이는 분위기를 가진...! 자칭 걸크러쉬분들이 떠오르는 퍼스널 컬러이다! 다크함이 매력인 겨울딥은 낮은 채도 컬러의 포인트 컬러로 얼굴빛 살아나요!."
    };

    // 대표적인 연예인
    String[] WarmArtist = {
                    "수지, 송혜교", // 봄라이트
                    "아이유, 박민영", // 봄브라이트
                    "제니, 박신혜", // 가을뮤트
                    "김민주, 조이, 해찬, ", // 가을스트롱
                    "크리스탈, 이효리, 예지"}; // 가을딥
    String[] CoolArtist = {
            "손예진, 정채연, 김태리", // 여름라이트
            "장원영, 김고은, 김연아", // 여름 뮤트
            "아이린, 나연", // 여름 브라이트
            "이광수, 육성재, 로운", // 여름 저명도뮤트
            "김서형, 선미", // 겨울트루
            "비니, 채영", // 겨울브라이트
            "김혜수, 디오, 이다희" // 겨울딥
    };

    // 추천 아이템
    String[] WarmItem ={
                "자연갈색헤어컬러, 골드 악세사리", // 봄라이트
                "자연갈색헤어컬러, 골드 악세사리", // 봄브라이트
                "말린장미 립스틱, 골드 악세사리", // 가을뮤트
                "클래식레드 립스틱, 블랙 헤어", // 가을스트롱
                "오렌지브라운 블러셔, 스킨 톤의 립"}; // 가을딥
    String[] CoolItem = {
                "자연갈색헤어컬러, 실버 악세사리", // 여름라이트
                "흑발, 로즈골드 악세사리", // 여름뮤트
                "핑크계열 블러셔, 하얀색 계열의 옷", // 여름브라이트
                "핑크베이스의 파운데이션 매트한 립", // 여름저명도뮤트
                "블랙 헤어 컬러, 대비가 강한 스타일링", // 겨울트루
                "딥블랙 헤어 컬러, 화려한 스타일링 ", // 겨울브라이트
                "가죽 소재 옷, 블랙&화이트룩" // 겨울딥
    };

    // 추천 색깔
    String[] WarmColor = {
            "연노랑, 연분홍, 연두, 연하늘", // 봄라이트
            "쨍한레드, 쨍한초록, 쨍한노랑", // 봄브라이트
            "말린장미, 녹차색,  누드한갈색", // 가을뮤트
            "겨자색, 토마토색", // 가을스트롱
            "다크브라운, 오트밀"}; // 가을딥
    String[] CoolColor = {
            "코랄, 딸기우유, 회색", // 여름라이트
            "말린장미, 회색, 블루", // 여름뮤트
            "라벤더, 핫핑크, 코발트블루", // 여름브라이트
            "말린장미, 팥죽색", // 여름저명도뮤트
            "버건디핑크, 쿨베이스핑크", // 겨울트루
            "핑크레드, 푸시아, 퍼플", // 겨울브라이트
            "푸른버건디, 딥한핏빛" // 겨울딥
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // 결과 받아오기
        Intent intent = getIntent();
        personal = intent.getStringExtra("퍼스널컬러");
        nickname = intent.getStringExtra("닉네임");
        base = intent.getIntExtra("베이스컬러",1);
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


        res_name.setText(nickname+"님의 결과");
        res_title.setText(personal);

        if(base==0){ // warm
            res_img.setImageDrawable(ContextCompat.getDrawable(this, reusltWarmImg[index]));
            res_desc.setText(WarmDesc[index]);
            res_artist.setText("대표적인 연예인 : " + WarmArtist[index]);
            res_item.setText("추천 아이템 : " + WarmItem[index]);
            res_color.setText("추천 컬러 : " + WarmColor[index]);
        }else{ // cool
            res_img.setImageDrawable(ContextCompat.getDrawable(this, reusltCoolImg[index]));
            res_desc.setText(CoolDesc[index]);
            res_artist.setText("대표적인 연예인 : " + CoolArtist[index]);
            res_item.setText("추천 아이템 : " + CoolItem[index]);
            res_color.setText("추천 컬러 : " + CoolColor[index]);
        }

        insertStudent();

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

        System.out.println(base);
        // profile 랜덤
        //"INSERT INTO usersTB(name, result, base, polarImg, profileImg) VALUES('황수고', '겨울트루', 1, 4, 2)";
        String qry = "INSERT INTO usersTB(name, result, base, polarImg, profileImg) VALUES('"+nickname+"', '" + personal + "', " + 1 + ", " + index + ", " + 3 + ")";
        database.execSQL(qry); //만들어준 쿼리문 실행
    }
}
