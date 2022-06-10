package com.bingo.spadedemo.track;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bingo.spade.Spade;
import com.bingo.spade.TrackExtraName;
import com.bingo.spade.utils.Utils;

public class DefaultFinder {

    public ViewTrace find(View view) {
        if (Spade.getTrace(view) != null) {
            return new ViewTrace(view.getContext(), Spade.getTrace(view));
        }
        return new ViewTrace(view.getContext(), Data.getEvent(getName(view)));
    }


    public static String getName(View view) {
        ViewTree viewTree = generateViewTree(view);

        StringBuilder sb = new StringBuilder(viewTree.generateAllPath());

        String md5 = Utils.toMD5(sb.toString());

        Log.d("Track", "TrackName: " + sb + ",MD5: " + md5);

        return md5;
    }


    private static ViewTree generateViewTree(View view) {
        ViewTree viewTree = new ViewTree(view.getClass().getSimpleName());

        if (view.getId() != View.NO_ID) {
            String idName;
            try {
                idName = view.getResources().getResourceEntryName(view.getId());
            } catch (Resources.NotFoundException exception) {
                idName = null;
            }
            if (!TextUtils.isEmpty(idName)) {
                viewTree.setId(idName);
            }
        }
        if (view.getParent() != null&&(!view.getParent().getClass().getSimpleName().equals("DecorView"))&& view.getParent() instanceof ViewGroup && ((ViewGroup) view.getParent()).getId() != android.R.id.content) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();

            int index = -1;
            if (viewGroup instanceof RecyclerView) {
                index = ((RecyclerView) viewGroup).getChildAdapterPosition(view);
            } else if (viewGroup instanceof AdapterView) {
                index = ((AdapterView) viewGroup).getPositionForView(view);
            } else if (viewGroup instanceof ViewPager) {
                index = ((ViewPager) viewGroup).getCurrentItem();
            } else {
//                index = getSameIndex(view, viewGroup);
            }
            if (index != -1) {
                viewTree.setIndex(index);
            }
            ViewTree parent = generateViewTree(viewGroup);
            parent.setChild(viewTree);
            viewTree.setParent(parent);
        } else {
            ViewTree parent = new ViewTree(getActivityName(view));
            parent.setChild(viewTree);
            viewTree.setParent(parent);
        }

        if (view.getTag(com.bingo.spade.R.id.key_extra_name) != null) {
            viewTree.setExtra((String) view.getTag(com.bingo.spade.R.id.key_extra_name));
        }

        return viewTree;

    }


    public static String getActivityName(View view) {

        Activity activity = getActivity(view);
        if (activity != null) {
            StringBuilder sb = new StringBuilder(activity.getClass().getSimpleName());
            if (activity instanceof TrackExtraName) {
                TrackExtraName extraName = (TrackExtraName) activity;
                if (!TextUtils.isEmpty(extraName.getExtraName())) {
                    sb.append(extraName.getExtraName());
                }
            }

            return sb.toString();
        } else {
            return view.getContext().getClass().getSimpleName();
        }
    }

    @Nullable
    public static Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }


    /**
     * 优化index，View相对于同层级的相同类型的view来说排在第几个；
     *
     * @param view
     * @param viewGroup
     * @return
     */
    public static int getSameIndex(View view, ViewGroup viewGroup) {
        int index = 0;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View cView = viewGroup.getChildAt(i);
            if (view.getClass().equals(cView.getClass())) {
                if (view.equals(cView)) {
                    return index;
                }
                index++;
            }
        }
        return index;
    }


}