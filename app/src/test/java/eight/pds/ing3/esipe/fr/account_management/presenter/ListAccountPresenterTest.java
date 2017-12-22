package eight.pds.ing3.esipe.fr.account_management.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivestreams.Subscription;

import java.util.List;

import eight.pds.ing3.esipe.fr.account_management.model.Account;
import eight.pds.ing3.esipe.fr.account_management.view.ListAccountActivity;
import eight.pds.ing3.esipe.fr.account_management.view.ListActivityView;

import static org.mockito.Mockito.*;

/**
 * Created by flesguer on 22/12/2017.
 */
public class ListAccountPresenterTest {
    @Mock
    ListActivityView listActivityView;
    @Mock
    Subscription subscription;
    @Mock
    List<Account> accountList;
    @InjectMocks
    ListAccountPresenter listAccountPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAttachView() throws Exception {
        listAccountPresenter.attachView(new ListAccountActivity());
    }

    @Test
    public void testDetachView() throws Exception {
        listAccountPresenter.detachView();
    }
/*
    @Test
    public void testLoadAccounts() throws Exception {
        when(listActivityView.getContext()).thenReturn(null);

        listAccountPresenter.loadAccounts("jwtToken");
    }
    */
}

