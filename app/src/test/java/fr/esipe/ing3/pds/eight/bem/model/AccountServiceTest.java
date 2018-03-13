package fr.esipe.ing3.pds.eight.bem.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertThat;

import fr.esipe.ing3.pds.eight.bem.account_management.model.AccountService;
import okhttp3.OkHttpClient;

import static org.hamcrest.CoreMatchers.instanceOf;

/**
 * Created by flesguer on 14/12/2017.
 */
public class AccountServiceTest {
    @Mock
    OkHttpClient client;
    @InjectMocks
    public AccountService.Creator creator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNewAccountService() throws Exception {
        AccountService result = creator.newAccountService();
        assertThat(result, instanceOf(AccountService.class));
    }
}
