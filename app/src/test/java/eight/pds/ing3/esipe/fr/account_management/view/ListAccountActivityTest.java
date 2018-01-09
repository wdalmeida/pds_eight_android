package eight.pds.ing3.esipe.fr.account_management.view;

import android.arch.lifecycle.LifecycleRegistry;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v4.app.FragmentController;
import android.support.v4.app.SupportActivity;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import eight.pds.ing3.esipe.fr.account_management.model.Account;
import eight.pds.ing3.esipe.fr.account_management.model.AccountService;
import eight.pds.ing3.esipe.fr.account_management.presenter.ListAccountPresenter;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by flesguer on 09/01/2018.
 */
public class ListAccountActivityTest {
    @Mock
    ListAccountPresenter presenter;
    @Mock
    RecyclerView accountRecyclerView;
    @Mock
    TextView nameTextView;
    @Mock
    AppCompatDelegate mDelegate;
    @Mock
    Resources mResources;
    @Mock
    Handler mHandler;
    @Mock
    FragmentController mFragments;
    @Mock
    SparseArrayCompat<String> mPendingFragmentActivityResults;
    @Mock
    SimpleArrayMap<Class<? extends SupportActivity.ExtraData>, SupportActivity.ExtraData> mExtraDataMap;
    @Mock
    LifecycleRegistry mLifecycleRegistry;
    @InjectMocks
    ListAccountActivity listAccountActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
/*
    @Test
    public void testOnCreate() throws Exception {
        listAccountActivity.onCreate(null);
    }

    @Test
    public void testShowAccountList() throws Exception {
        listAccountActivity.showAccountList(Arrays.<Account>asList(new Account()));
    }
*/
    //works well
    @Test
    public void testGetContext() throws Exception {
        Context result = listAccountActivity.getContext();
        //Assert.assertEquals(null, result);

        assertThat(result, instanceOf(ListAccountActivity.class));


    }
}
