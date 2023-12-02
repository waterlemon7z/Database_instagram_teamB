package swing;

import jdbc.ConnectionManager;

public class MainLogin
{
    private LoginView loginView;
    private MainPage mainPage;

    public static void main(String[] args)
    {
        // 메인 클래스 실행
        ConnectionManager.getConnection();
        MainLogin main = new MainLogin();
        main.initializeLoginView(); // 로그인창 초기화 및 보이기
    }

    // 로그인 뷰 초기화 및 보이기
    public void initializeLoginView()
    {
        loginView = new LoginView();
        loginView.setMain(this); // 로그인 뷰에 메인 클래스 보내기
    }

    // 로그인 성공 후 테스트 프레임 보이기
    public void showFrameTest(int userId)
    {
        loginView.dispose(); // 로그인 창 닫기
        mainPage = new MainPage(userId); // 테스트 프레임 오픈
    }
}
