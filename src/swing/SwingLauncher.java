package swing;

import jdbc.ConnectionManager;
import swing.articleView.MainPage;

public class SwingLauncher
{
    private LoginView loginView;

    public static void main(String[] args)
    {
        ConnectionManager.getConnection();
        SwingLauncher main = new SwingLauncher();
        main.initializeLoginView();
    }

    public void initializeLoginView()
    {
        loginView = new LoginView();
        loginView.setMain(this);
    }

    public void showFrameTest(int userId)
    {
        loginView.dispose();
        new MainPage(userId);
    }
}
