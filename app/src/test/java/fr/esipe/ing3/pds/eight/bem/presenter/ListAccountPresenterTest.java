package fr.esipe.ing3.pds.eight.bem.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivestreams.Subscription;

import java.util.List;

import fr.esipe.ing3.pds.eight.bem.account_management.model.Account;
import fr.esipe.ing3.pds.eight.bem.account_management.presenter.ListAccountPresenter;
import fr.esipe.ing3.pds.eight.bem.account_management.view.ListAccountActivity;
import fr.esipe.ing3.pds.eight.bem.account_management.view.ListActivityView;

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

