package gavin.usage.test;

import android.os.Bundle;
import android.support.annotation.Nullable;

import gavin.usage.base.BaseFragment;
import gavin.usage.R;
import gavin.usage.databinding.TestFargScrollingBinding;

/**
 * 测试
 *
 * @author gavin.xiong 2017/4/25
 */
public class ScrollingFragment extends BaseFragment<TestFargScrollingBinding> {

    public static ScrollingFragment newInstance() {
        return new ScrollingFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_farg_scrolling;
    }

    @Override
    protected void afterCreate(@Nullable Bundle savedInstanceState) {
        binding.toolbar.inflateMenu(R.menu.activity_main_drawer);
    }
}
