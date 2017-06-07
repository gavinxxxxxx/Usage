package gavin.usage.app.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import gavin.usage.app.setting.PermissionFragment;
import gavin.usage.base.BaseActivity;
import gavin.usage.R;
import gavin.usage.databinding.ActMainBinding;

public class MainActivity extends BaseActivity<ActMainBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.act_main;
    }

    @Override
    protected void afterCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadRootFragment(R.id.holder, PermissionFragment.newInstance());
        }
    }
}
