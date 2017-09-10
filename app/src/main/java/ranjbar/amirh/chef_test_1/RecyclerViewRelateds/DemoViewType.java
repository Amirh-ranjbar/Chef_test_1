package ranjbar.amirh.chef_test_1.RecyclerViewRelateds;

import jp.satorufujiwara.binder.ViewType;

public enum DemoViewType implements ViewType {
  TITLE, LANDSCAPE_ITEM, LANDSCAPE_TILE, LANDSCAPE_DESCRIPTION,

  PAGE;

  @Override public int viewType() {
    return ordinal();
  }
}
