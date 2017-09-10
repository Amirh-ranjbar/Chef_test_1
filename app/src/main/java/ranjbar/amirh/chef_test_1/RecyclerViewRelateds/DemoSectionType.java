package ranjbar.amirh.chef_test_1.RecyclerViewRelateds;

import jp.satorufujiwara.binder.Section;

public enum DemoSectionType implements Section {
  ITEM {
    @Override
    public int position() {
      return 0;
    }
  }
}
