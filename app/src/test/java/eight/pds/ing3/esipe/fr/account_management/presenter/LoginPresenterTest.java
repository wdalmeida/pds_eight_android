package eight.pds.ing3.esipe.fr.account_management.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivestreams.Subscription;

import eight.pds.ing3.esipe.fr.account_management.view.LoginActivity;
import eight.pds.ing3.esipe.fr.account_management.view.LoginMvpView;

import static org.mockito.Mockito.*;

/**
 * Created by flesguer on 27/22/2017.
 */
public class LoginPresenterTest {
    @Mock
    LoginMvpView loginMvpView;
    @Mock
    Subscription subscription;
    @InjectMocks
    LoginPresenter loginPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAttachView() throws Exception {
        loginPresenter.attachView(new LoginActivity());
    }

    @Test
    public void testDetachView() throws Exception {
        loginPresenter.detachView();
    }
/*
    @Test
    public void testAuthenticate() throws Exception {
        when(loginMvpView.getContext()).thenReturn(null);

        loginPresenter.authenticate("userId", "password");
    }*/
}
