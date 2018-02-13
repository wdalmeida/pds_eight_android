package eight.pds.ing3.esipe.fr.account_management.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertThat;

import okhttp3.OkHttpClient;

import static org.mockito.Mockito.*;
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
