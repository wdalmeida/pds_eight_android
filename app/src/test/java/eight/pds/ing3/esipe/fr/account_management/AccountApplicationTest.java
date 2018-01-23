package eight.pds.ing3.esipe.fr.account_management;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import eight.pds.ing3.esipe.fr.account_management.model.AccountService;
import io.reactivex.Scheduler;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.Mockito.*;

/**
 * Created by flesguer on 09/02/2018.
 */
public class AccountApplicationTest {
    @Mock
    AccountService accountService;
    @Mock
    Scheduler subscriberScheduler;
    @InjectMocks
    AccountApplication accountApplication;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
/*
    @Test
    public void testGet() throws Exception {
        AccountApplication result = AccountApplication.get(null);
        Assert.assertEquals(new AccountApplication(), result);
    }
*/

    //works well
    @Test
    public void testGetAccountService() throws Exception {
        AccountService result = accountApplication.getAccountService();
        Assert.assertEquals(accountService, result);
    }
/*
    @Test
    public void testDefaultSubscriberScheduler() throws Exception {
        Scheduler result = accountApplication.defaultSubscriberScheduler();
        Assert.assertEquals(null, result);
    }
    */
}
