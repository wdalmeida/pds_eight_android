package fr.esipe.ing3.pds.eight.bem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import fr.esipe.ing3.pds.eight.bem.account_management.AccountAdapter;
import fr.esipe.ing3.pds.eight.bem.account_management.model.Account;



/**
 * Created by flesguer on 23/01/2018.
 */
public class AccountAdapterTest {
    @Mock
    List<Account> accountList;
    //no need for constructor
    private String accountId = "1";
    private String type = "courant";
    private float balance = 500;
    Account testAccount = new Account();

    @InjectMocks
	AccountAdapter accountAdapter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    /*
        @Test
        public void testOnCreateViewHolder() throws Exception {
            //AccountAdapter.AccountViewHolder result = accountAdapter.onCreateViewHolder(null, 0);
            AccountAdapter.AccountViewHolder result = accountAdapter.onCreateViewHolder(null, 0);

            Assert.assertEquals(new AccountAdapter.AccountViewHolder(null), result);
        }

        @Test
        public void testOnBindViewHolder() throws Exception {
            accountAdapter.onBindViewHolder(new AccountAdapter.AccountViewHolder(null), 0);
        }
    */
    @Test
    public void testGetItemCount() throws Exception {
        int result = accountAdapter.getItemCount();
        Assert.assertEquals(0, result);
    }
}
