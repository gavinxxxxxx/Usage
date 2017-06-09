package gavin.usage.test;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

import gavin.usage.base.BaseFragment;
import gavin.usage.R;
import gavin.usage.databinding.TestFargScrollingBinding;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * 测试
 *
 * @author gavin.xiong 2017/4/25
 */
public class ScrollingFragment extends BaseFragment<TestFargScrollingBinding> {

    ImageView ivActionLove;

    private AnimatorSet scaleAnim;

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
        binding.toolbar.getMenu().findItem(R.id.nav_operate).getActionView().setOnClickListener(this::doLoveAnim);
        ivActionLove = binding.toolbar.getMenu().findItem(R.id.nav_operate).getActionView().findViewById(R.id.ivActionLove);
        ivActionLove.setImageResource(ivActionLove.isSelected() ? R.drawable.vt_favorite_24dp : R.drawable.vt_favorite_border_24dp);
    }

    /**
     * 喜欢/取消喜欢动画
     */
    private void doLoveAnim(View v) {
        Observable.timer(350, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
//                    mCompositeDisposable.add(disposable);
                    v.setEnabled(false);
                    ivActionLove.setSelected(!ivActionLove.isSelected());
                    if (scaleAnim == null) {
                        initAnim();
                    }
                    scaleAnim.start();
                })
                .map(arg0 -> {
                    ivActionLove.setImageResource(ivActionLove.isSelected()
                            ? R.drawable.vt_favorite_24dp : R.drawable.vt_favorite_border_24dp);
                    return arg0;
                })
                .delay(350, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .subscribe(arg0 -> v.setEnabled(true));
    }

    /**
     * 初始化属性动画
     */
    private void initAnim() {
        scaleAnim = new AnimatorSet().setDuration(700);
        scaleAnim.setInterpolator(new AnticipateOvershootInterpolator());
        scaleAnim.playTogether(
                ObjectAnimator.ofFloat(ivActionLove, View.SCALE_X, 1f, 0f, 1f),
                ObjectAnimator.ofFloat(ivActionLove, View.SCALE_Y, 1f, 0f, 1f));
    }
}
