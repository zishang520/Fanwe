package com.fanwe.live.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;

import com.fanwe.hybrid.activity.BaseTitleActivity;
import com.fanwe.hybrid.fragment.BaseFragment;
import com.fanwe.library.adapter.SDFragmentPagerAdapter;
import com.fanwe.library.common.SDSelectManager;
import com.fanwe.library.customview.SDViewPager;
import com.fanwe.library.utils.SDResourcesUtil;
import com.fanwe.library.view.SDTabUnderline;
import com.fanwe.library.view.select.SDSelectViewManager;
import com.fanwe.live.R;
import com.fanwe.live.dao.UserModelDao;
import com.fanwe.live.fragment.LiveSociatyApplyFragment;
import com.fanwe.live.fragment.LiveSociatyLogoutFragment;
import com.fanwe.live.fragment.LiveSociatyMembersFragment;
import com.fanwe.live.model.UserModel;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 公会成员列表
 * Created by Administrator on 2016/9/26.
 */

public class LiveSociatyMembersListActivity extends BaseTitleActivity
{
    @ViewInject(R.id.vpg_content)
    private SDViewPager vpg_content;
    @ViewInject(R.id.ll_SDTab)
    private LinearLayout ll_SDTab;
    @ViewInject(R.id.tab_live_menb)
    private SDTabUnderline tab_live_menb;//公会成员
    @ViewInject(R.id.tab_live_apply)
    private SDTabUnderline tab_live_apply;//成员申请
    @ViewInject(R.id.tab_live_logout)
    private SDTabUnderline tab_live_logout;//成员退出申请

    private SDSelectViewManager<SDTabUnderline> selectViewManager;
    private SparseArray<BaseFragment> arrFragment;

    private LiveSociatyMembersFragment memberFra;
    private LiveSociatyApplyFragment applyFra;
    private LiveSociatyLogoutFragment logoutFra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sociaty_members_list);
        initView();
    }

    private void initView()
    {
        initTitle();

        UserModel dao = UserModelDao.query();
        if (dao.getSociety_chieftain() != 1)//是否公会长；0：否、1：是
        {
            ll_SDTab.setVisibility(View.GONE);
            vpg_content.setLocked(true);
        }

        memberFra = new LiveSociatyMembersFragment();
        applyFra = new LiveSociatyApplyFragment();
        logoutFra = new LiveSociatyLogoutFragment();
        memberFra.setMembRsCount(tab_live_menb,tab_live_apply,tab_live_logout);
        applyFra.setApplyRsCount(tab_live_apply,tab_live_menb,tab_live_logout);
        logoutFra.setLogoutRsCount(tab_live_apply,tab_live_menb,tab_live_logout);
        selectViewManager = new SDSelectViewManager<>();
        arrFragment = new SparseArray<>();
        arrFragment.put(0,memberFra);
        arrFragment.put(1,applyFra);
        arrFragment.put(2,logoutFra);

        initSDViewPager();
        initTabs();
    }

    private void initTitle()
    {
        mTitle.setMiddleTextTop("成员列表");
        mTitle.setLeftImageLeft(R.drawable.ic_arrow_left_main_color);
        mTitle.setOnClickListener(this);
    }

    private void initSDViewPager() {
        vpg_content.setOffscreenPageLimit(1);
        List<String> listModel = new ArrayList<>();
        listModel.add("");
        listModel.add("");
        listModel.add("");

        vpg_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                selectViewManager.performClick(position);
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });

        vpg_content.setAdapter(new LivePagerAdapter(listModel, this, getSupportFragmentManager()));
    }

    private void initTabs()
    {
        tab_live_menb.setTextTitle("公会成员(" + 0 + ")");
        tab_live_menb.getViewConfig(tab_live_menb.mIvUnderline).setBackgroundColorNormal(Color.TRANSPARENT).setBackgroundColorSelected(SDResourcesUtil.getColor(R.color.res_main_color));
        tab_live_menb.getViewConfig(tab_live_menb.mTvTitle).setTextColorNormal(SDResourcesUtil.getColor(R.color.res_text_gray_m)).setTextColorSelected(SDResourcesUtil.getColor(R.color.res_main_color));

        tab_live_apply.setTextTitle("成员申请(" + 0 + ")");
        tab_live_apply.getViewConfig(tab_live_apply.mIvUnderline).setBackgroundColorNormal(Color.TRANSPARENT).setBackgroundColorSelected(SDResourcesUtil.getColor(R.color.res_main_color));
        tab_live_apply.getViewConfig(tab_live_apply.mTvTitle).setTextColorNormal(SDResourcesUtil.getColor(R.color.res_text_gray_m)).setTextColorSelected(SDResourcesUtil.getColor(R.color.res_main_color));

        tab_live_logout.setTextTitle("退出申请(" + 0 + ")");
        tab_live_logout.getViewConfig(tab_live_apply.mIvUnderline).setBackgroundColorNormal(Color.TRANSPARENT).setBackgroundColorSelected(SDResourcesUtil.getColor(R.color.res_main_color));
        tab_live_logout.getViewConfig(tab_live_apply.mTvTitle).setTextColorNormal(SDResourcesUtil.getColor(R.color.res_text_gray_m)).setTextColorSelected(SDResourcesUtil.getColor(R.color.res_main_color));

        SDTabUnderline[] items = new SDTabUnderline[]{tab_live_menb, tab_live_apply, tab_live_logout};

        selectViewManager.addSelectCallback(new SDSelectManager.SelectCallback<SDTabUnderline>()
        {

            @Override
            public void onNormal(int index, SDTabUnderline item)
            {
            }

            @Override
            public void onSelected(int index, SDTabUnderline item)
            {
                switch (index)
                {
                    case 0:
                        vpg_content.setCurrentItem(0);
                        memberFra.refreshViewer();
                        break;
                    case 1:
                        vpg_content.setCurrentItem(1);
                        applyFra.refreshViewer();
                        break;
                    case 2:
                        vpg_content.setCurrentItem(2);
                        logoutFra.refreshViewer();
                        break;
                    default:
                        break;
                }
            }
        });
        selectViewManager.setItems(items);
        selectViewManager.setSelected(0, true);
    }

    private class LivePagerAdapter extends SDFragmentPagerAdapter<String>
    {

        public LivePagerAdapter(List<String> listModel, Activity activity, FragmentManager fm)
        {
            super(listModel, activity, fm);
        }

        @Override
        public Fragment getItemFragment(int position, String model)
        {
            return arrFragment.get(position);
        }
    }

}
